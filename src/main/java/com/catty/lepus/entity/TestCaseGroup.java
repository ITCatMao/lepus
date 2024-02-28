package com.catty.lepus.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "test_case_group")
public class TestCaseGroup extends BaseEntityNew {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用例集id
     */
    @Column(name = "group_id")
    private Long groupId;

    /**
     * 用例名称
     */
    private String title;

    /**
     * 用例描述
     */
    private String description;

    /**
     * 用例状态 0-正常 1-删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 用例集创建人
     */
    private String creator;

    /**
     * 用例集修改人
     */
    private String modifier;

    /**
     * 记录创建时间
     */
    @Column(name = "gmt_created")
    private Date gmtCreated;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 扩展字段
     */
    private String extra;

    /**
     * 业务线id 默认0
     */
    @Column(name = "product_line_id")
    private Long productLineId;

    /**
     * 0-需求用例，1-核心用例，2-冒烟用例
     */
    @Column(name = "case_type")
    private Integer caseType;

    /**
     * 模块节点id
     */
    @Column(name = "module_node_id")
    private Long moduleNodeId;

    /**
     * 需求id
     */
    @Column(name = "requirement_id")
    private String requirementId;

    /**
     * 冒烟case的id
     */
    @Column(name = "smk_case_id")
    private Long smkCaseId;

    /**
     * 渠道标志 现默认1
     */
    private Integer channel;

    /**
     * 关联的文件夹id
     */
    @Column(name = "biz_id")
    private String bizId;

    @Column(name = "case_content")
    private String caseContent;
}