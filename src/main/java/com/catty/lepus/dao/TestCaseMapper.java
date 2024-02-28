package com.catty.lepus.dao;

import com.catty.lepus.common.MySqlExtensionMapper;
import com.catty.lepus.entity.TestCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @Author catty
 * @Description 用例的相关方法
 * @Date 22:43 2024/2/20
 * @Param
 * @return
 **/
@Repository
public interface TestCaseMapper extends MySqlExtensionMapper<TestCase> {

    /**
     * 拿到渠道下某条业务线所有的caseIds
     *
     * @param productLineId 业务线id
     * @param channel 渠道
     * @return id集合
     */
    Set<String> findCaseIdsInBiz(@Param("productLineId") Long productLineId, @Param("channel") Integer channel);

}