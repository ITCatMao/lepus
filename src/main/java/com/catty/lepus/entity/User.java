package com.catty.lepus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "user")
@ApiModel(value = "测试用户")
public class User extends BaseEntityNew {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键", required = true)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "username")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 盐
     */
    private String salt;

    //    @TableField
    @Column(name = "authority_name")
    private String authorityName;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 渠道
     */
    private Integer channel;

    /**
     * 业务线
     */
    @Column(name = "product_line_id")
    @ApiModelProperty(value = "业务线")
    private Long productLineId;

    /**
     * 注册时间
     */
    @Column(name = "gmt_created")
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreated;

    /**
     * 更新时间
     */
    @Column(name = "gmt_updated")
    @ApiModelProperty(value = "更新时间", required = true)
    private Date gmtUpdated;

}