package com.catty.lepus.dto.user;

import com.catty.lepus.entity.BaseEntityNew;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author catty
 * @Date 2022/11/13 17:55
 **/
@ApiModel(value="用户登录对象",description="用户对象user")
@Data
public class LoginUserDto extends BaseEntityNew {

    @ApiModelProperty(value="用户名",name="username",required=true,dataType="String",notes="唯一不可重复",example="test")
    private String username;

    @ApiModelProperty(value="密码",name="password",required=true,dataType="String",notes="字母+数字，6-18位",example="test123")
    private String password;

}
