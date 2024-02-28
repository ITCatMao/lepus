package com.catty.lepus.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name="biz")
public class Biz extends BaseEntityNew {
    /**
     * 文件夹主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 业务线名称
     */
    @Column(name = "product_line_id")
    private Long productLineId;

    /**
     * 渠道
     */
    private Integer channel;

    /**
     * 逻辑删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @Column(name = "gmt_created")
    private Date gmtCreated;

    /**
     * 更新时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 文件数内容
     */
    private String content;

}