package com.catty.lepus.dto.user;

import com.catty.lepus.entity.BaseEntityNew;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 用户注册请求类
 * @Author catty
 * @Date 2022/9/26 23:08
 **/
@Data
@ApiModel(value = "用户注册类", description = "请求类")
public class AddUserDto extends BaseEntityNew {
    /**
     * 用户名
     */
    @ApiModelProperty(value="username", example="muxue",required=true)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value="password", example="123456",required=true)
    private String password;

}
