package com.catty.lepus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.catty.lepus.constants.enums.StatusCode;
import com.catty.lepus.dao.BizMapper;
import com.catty.lepus.dao.TestCaseMapper;
import com.catty.lepus.dto.dir.DirCreateDto;
import com.catty.lepus.dto.dir.DirNodeDto;
import com.catty.lepus.entity.Biz;
import com.catty.lepus.exception.ResultException;
import com.catty.lepus.service.DirService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @Description TODO
 * @Author catty
 * @Date 2022/11/15 22:37
 **/
@Slf4j
@Service
public class DirServiceImpl implements DirService {

    @Resource
    BizMapper bizMapper;

    @Resource
    TestCaseMapper caseMapper;


    /**
     * 增加文件夹
     *
     * @param request 请求体
     * @return 树结构文件夹
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DirNodeDto addDir(DirCreateDto request, Integer createUserId) {
        DirNodeDto root = getDirTree(request.getProductLineId(), request.getChannel());
        checkNodeExists(request.getText(), request.getParentId(), root);
        DirNodeDto dir = getDir(request.getParentId(), root);
        if (dir == null) {
            throw new ResultException("目录节点获取为空", StatusCode.INTERNAL_ERROR);
        }
        List<DirNodeDto> children = dir.getChildren();
        DirNodeDto newDir = new DirNodeDto();
        newDir.setCreateUserId(createUserId);
        newDir.setId(UUID.randomUUID().toString().substring(0, 8));
        newDir.setText(request.getText());
        newDir.setParentId(dir.getId());
        children.add(newDir);
        bizMapper.updateContent(request.getProductLineId(), JSONObject.toJSONString(root), request.getChannel());
        return root;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DirNodeDto addDir(DirCreateDto request) {
        DirNodeDto root = getDirTree(request.getProductLineId(), request.getChannel());
        checkNodeExists(request.getText(), request.getParentId(), root);
        DirNodeDto dir = getDir(request.getParentId(), root);
        if (dir == null) {
            throw new ResultException("目录节点获取为空", StatusCode.INTERNAL_ERROR);
        }
        List<DirNodeDto> children = dir.getChildren();
        DirNodeDto newDir = new DirNodeDto();
        newDir.setId(UUID.randomUUID().toString().substring(0, 8));
        newDir.setText(request.getText());
        newDir.setParentId(dir.getId());
        children.add(newDir);
        bizMapper.updateContent(request.getProductLineId(), JSONObject.toJSONString(root), request.getChannel());
        return root;
    }

    /**
     * 查询文件树
     *
     * @param productLineId 业务线id
     * @param channel       渠道 默认为1
     * @return 树
     */
    @Override
    public DirNodeDto getDirTree(Long productLineId, Integer channel, Integer createUserId) {
        Biz dbBiz = null;
        try {
            dbBiz = bizMapper.selectOne(productLineId, channel);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据productLindId获取biz失败", e.getMessage());
        }
        // 如果有，那么就直接返回
        if (dbBiz != null) {
            return JSONObject.parseObject(dbBiz.getContent(), DirNodeDto.class);
        }

        // 如果没有，则会自动生成root
        DirNodeDto root = new DirNodeDto();
        root.setId("root");
        root.setText("主文件夹");

        Set<String> ids = caseMapper.findCaseIdsInBiz(productLineId, channel);

        DirNodeDto child = new DirNodeDto();
        child.setId("-1");
        child.setCreateUserId(createUserId);
        child.setParentId(root.getId());
        child.setText("未分类用例集");
        child.setCaseIds(ids);
        root.getChildren().add(child);

        Biz biz = new Biz();
        biz.setProductLineId(productLineId);
        biz.setChannel(channel);
        biz.setContent(JSONObject.toJSONString(root));
        bizMapper.insert(biz);
        root.getCaseIds().addAll(child.getCaseIds());
        return root;
    }
    @Override
    public DirNodeDto getDirTree(Long productLineId, Integer channel) {
        Biz dbBiz = null;
        try {
            dbBiz = bizMapper.selectOne(productLineId, channel);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据productLindId获取biz失败", e.getMessage());
        }
        // 如果有，那么就直接返回
        if (dbBiz != null) {
            return JSONObject.parseObject(dbBiz.getContent(), DirNodeDto.class);
        }

        // 如果没有，则会自动生成root
        DirNodeDto root = new DirNodeDto();
        root.setId("root");
        root.setText("主文件夹");

        Set<String> ids = caseMapper.findCaseIdsInBiz(productLineId, channel);

        DirNodeDto child = new DirNodeDto();
        child.setId("-1");
        child.setParentId(root.getId());
        child.setText("未分类用例集");
        child.setCaseIds(ids);
        root.getChildren().add(child);

        Biz biz = new Biz();
        biz.setProductLineId(productLineId);
        biz.setChannel(channel);
        biz.setContent(JSONObject.toJSONString(root));
        bizMapper.insert(biz);
        root.getCaseIds().addAll(child.getCaseIds());
        return root;
    }


    /**
     * 校验同级节点下是否存在相同名字的子节点
     *
     * @param text     节点名称
     * @param parentId 父节点id
     * @param dirNodeDto  节点内容
     */
    private void checkNodeExists(final String text, final String parentId, final DirNodeDto dirNodeDto) {
        if (parentId.equalsIgnoreCase(dirNodeDto.getId())) {
            List<DirNodeDto> childrenNodes = dirNodeDto.getChildren();
            if (childrenNodes.stream().anyMatch(node -> text.equalsIgnoreCase(node.getText()))) {
                throw new ResultException("目标节点已存在", StatusCode.NODE_ALREADY_EXISTS);
            }
        }
        List<DirNodeDto> childrenNodes = dirNodeDto.getChildren();
        childrenNodes.forEach(node -> checkNodeExists(text, parentId, node));
    }

    @Override
    public DirNodeDto getDir(String bizId, DirNodeDto root) {
        if (root == null) {
            return null;
        }
        if (bizId.equals(root.getId())) {
            return root;
        }

        List<DirNodeDto> children = root.getChildren();
        for (DirNodeDto child : children) {
            DirNodeDto dir = getDir(bizId, child);
            if (dir != null) {
                return dir;
            }
        }
        return null;
    }
}
