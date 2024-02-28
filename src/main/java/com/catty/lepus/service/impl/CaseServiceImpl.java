//package com.catty.lepus.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.catty.lepus.constants.SystemConstant;
//import com.catty.lepus.dao.BizMapper;
//import com.catty.lepus.dao.TestCaseMapper;
//import com.catty.lepus.dto.testcase.CreateCaseDto;
//import com.catty.lepus.entity.Biz;
//import com.catty.lepus.dto.dir.DirNodeDto;
//import com.catty.lepus.entity.TestCase;
//import com.catty.lepus.service.CaseService;
//import com.catty.lepus.service.DirService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.util.*;
//
///**
// * @Description TODO
// * @Author catty
// * @Date 2022/11/15 17:19
// **/
//@Slf4j
//@Service
//public class CaseServiceImpl implements CaseService {
//
////    @Autowired
//    @Resource
//    private TestCaseMapper caseMapper;
//
////    @Resource
//    @Resource
//    private DirService dirService;
//
////    @Autowired
//    @Resource
//    private BizMapper bizMapper;
//
//    /**
//     * 用例新建或者复制
//     *
//     * @param request 请求体
//     * @return 创建的测试用例的caseId
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Long insertOrDuplicateCase(CreateCaseDto request) {
//        TestCase testcase = buildCase(request);
//        caseMapper.insert(testcase);
//        // 可能会多个加入  所以不要使用dirService.addCase()
//        DirNodeDto tree = dirService.getDirTree(testcase.getProductLineId(), testcase.getChannel());
//        List<String> addBizs = Arrays.asList(request.getBizId().split(SystemConstant.COMMA));
//        updateDFS(packageTree(tree), String.valueOf(testcase.getId()), new HashSet<>(addBizs), new HashSet<>());
//        updateBiz(testcase, tree);
//        return testcase.getId();
//    }
//
//    /**
//     * 新建/复制时，构建新的用例
//     *
//     * @param request 请求体
//     * @return 新的请求体
//     * @see #insertOrDuplicateCase
//     */
//    private TestCase buildCase(CreateCaseDto request) {
//        String content = request.getCaseContent();
//        // 如果是复制
//        if (request.getId() != null) {
//            TestCase testCase = caseMapper.selectOne(request.getId());
//            if (testCase == null) {
//                try {
//                    throw new Exception("用例不存在");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            content = testCase.getCaseContent();
//        }
//
//        TestCase ret = new TestCase();
//        ret.setTitle(request.getTitle());
//        ret.setRequirementId(request.getRequirementId());
//        ret.setBizId(request.getBizId());
//        ret.setGroupId(1L);
//        ret.setProductLineId(request.getProductLineId());
//        ret.setDescription(request.getDescription());
//        ret.setCreator(request.getCreator());
//        ret.setModifier(ret.getCreator());
//        ret.setChannel(request.getChannel());
//        ret.setExtra(SystemConstant.EMPTY_STR);
//        ret.setGmtCreated(new Date());
//        ret.setGmtModified(new Date());
//        ret.setCaseContent(content);
//        return ret;
//    }
//
//    /**
//     * 更新json体
//     *
//     * @param node   树
//     * @param addSet 需要新增caseId的set
//     * @param rmSet  需要删减caseId的set
//     */
//    private void updateDFS(DirNodeDto node, String caseId, Set<String> addSet, Set<String> rmSet) {
//        if (CollectionUtils.isEmpty(node.getChildren())) {
//            return;
//        }
//
//        for (int i = 0; i < node.getChildren().size(); i++) {
//            DirNodeDto childNode = node.getChildren().get(i);
//            if (addSet.contains(childNode.getId())) {
//                childNode.getCaseIds().add(caseId);
//            }
//            if (rmSet.contains(childNode.getId())) {
//                childNode.getCaseIds().remove(caseId);
//            }
//            updateDFS(childNode, caseId, addSet, rmSet);
//        }
//    }
//
//    /**
//     * 更新文件夹
//     *
//     * @param testCase 测试用例
//     * @param tree     树
//     */
//    public void updateBiz(TestCase testCase, DirNodeDto tree) {
//        Biz biz = bizMapper.selectOne(testCase.getProductLineId(), testCase.getChannel());
//        biz.setContent(JSON.toJSONString(tree));
//        biz.setGmtModified(new Date());
//        bizMapper.update(biz);
//    }
//
//
//    /**
//     * dir-封装一下树的开头，这样树的头结点也可以进行插入
//     */
//    private DirNodeDto packageTree(DirNodeDto node) {
//        DirNodeDto pack = new DirNodeDto();
//        pack.getChildren().add(node);
//        return pack;
//    }
//
//}
