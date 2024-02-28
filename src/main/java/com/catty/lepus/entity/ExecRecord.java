package com.catty.lepus.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "exec_record")
public class ExecRecord extends BaseEntityNew {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 执行的用例id
     */
    @Column(name = "case_id")
    private Long caseId;

    /**
     * 用例名称
     */
    private String title;

    /**
     * 执行环境： 0、测试环境 1、预发环境 2.线上环境 3.冒烟qa 4.冒烟rd
     */
    private Integer env;

    /**
     * 用例状态 0-正常 1-删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 执行个数
     */
    @Column(name = "pass_count")
    private Integer passCount;

    /**
     * 需执行总个数
     */
    @Column(name = "total_count")
    private Integer totalCount;

    /**
     * 成功个数
     */
    @Column(name = "success_count")
    private Integer successCount;

    /**
     * 不执行个数
     */
    @Column(name = "ignore_count")
    private Integer ignoreCount;

    /**
     * 阻塞个数
     */
    @Column(name = "block_count")
    private Integer blockCount;

    /**
     * 失败个数
     */
    @Column(name = "fail_count")
    private Integer failCount;

    /**
     * 用例创建人
     */
    private String creator;

    /**
     * 用例修改人
     */
    private String modifier;

    /**
     * 执行人
     */
    private String executors;

    /**
     * 描述
     */
    private String description;

    /**
     * 圈选用例内容
     */
    @Column(name = "choose_content")
    private String chooseContent;

    /**
     * 记录创建时间
     */
    @Column(name = "gmt_created")
    private Date gmtCreated;

    /**
     * 记录修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 预计开始时间
     */
    @Column(name = "expect_start_time")
    private Date expectStartTime;

    /**
     * 预计结束时间
     */
    @Column(name = "expect_end_time")
    private Date expectEndTime;

    /**
     * 实际开始时间
     */
    @Column(name = "actual_start_time")
    private Date actualStartTime;

    /**
     * 实际结束时间
     */
    @Column(name = "actual_end_time")
    private Date actualEndTime;

    /**
     * 负责人
     */
    private String owner;

    /**
     * 任务执行内容
     */
    @Column(name = "case_content")
    private String caseContent;

 }