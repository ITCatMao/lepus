package com.catty.lepus.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "test_task")
public class TestTask extends BaseEntityNew {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建人id
     */
    @Column(name = "create_user_id")
    private Integer createUserId;

    /**
     * 用例数量
     */
    @Column(name = "case_count")
    private Integer caseCount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 任务类型 1 单个执行测试任务 2 一键执行测试的任务
     */
    @Column(name = "task_type")
    private Byte taskType;

    /**
     * 用例状态 0 无效 1 未执行  2 执行成功  3 执行失败 4 阻碍
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * Jenkins执行测试时的命令脚本
     */
    @Column(name = "test_command")
    private String testCommand;
}