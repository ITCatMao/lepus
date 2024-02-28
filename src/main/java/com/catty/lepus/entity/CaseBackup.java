package com.catty.lepus.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "case_backup")
public class CaseBackup extends BaseEntityNew {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用例集id
     */
    @Column(name = "case_id")
    private Long caseId;

    /**
     * 用例名称
     */
    private String title;

    /**
     * 用例保存人1
     */
    private String creator;

    /**
     * 用例保存时间
     */
    @Column(name = "gmt_created")
    private Date gmtCreated;

    /**
     * 扩展字段
     */
    private String extra;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "case_content")
    private String caseContent;

    /**
     * 任务执行内容
     */
    @Column(name = "record_content")
    private String recordContent;

 }