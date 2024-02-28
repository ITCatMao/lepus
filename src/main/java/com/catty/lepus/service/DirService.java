package com.catty.lepus.service;


import com.catty.lepus.dto.dir.DirCreateDto;
import com.catty.lepus.dto.dir.DirNodeDto;

/**
 * 文件夹接口
 *
 * @author catty
 * @date 2022/11/15
 */
public interface DirService {

    /**
     * @Description 增加文件夹
     * @Date 22:30 2024/2/26
     * @Param [request, createUserId]
     * @return com.catty.lepus.dto.dir.DirNodeDto
     **/
    DirNodeDto addDir(DirCreateDto request, Integer createUserId);

    /**
     * 查询文件树
     *
     * @param productLineId 业务线id
     * @param channel       渠道 默认为1
     * @return 树
     */
    DirNodeDto getDirTree(Long productLineId, Integer channel, Integer createUserId);


    /**
     * 获取一个节点的内容
     *
     * @param bizId 节点id
     * @param root  要搜索的树
     * @return 树
     */
    DirNodeDto getDir(String bizId, DirNodeDto root);
}
