package com.catty.lepus.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name="authority")
public class Authority extends BaseEntityNew {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限名称，ROLE_开头，全大写
     */
    @Column(name = "authority_name")
    private String authorityName;

    /**
     * 权限描述
     */
    @Column(name = "authority_desc")
    private String authorityDesc;

    /**
     * 权限内容，可访问的url，多个时用,隔开
     */
    @Column(name = "authority_content")
    private String authorityContent;

    /**
     * 创建时间
     */
    @Column(name = "gmt_created")
    private Date gmtCreated;

    /**
     * 修改时间
     */
    @Column(name = "gmt_updated")
    private Date gmtUpdated;

}