package com.catty.lepus.dto;

import lombok.Data;

/**
 * @Description 登录token保持登录状态
 * @Author catty
 * @Date 2022/11/13 18:29
 **/
@Data
public class TokenDto {

    private Integer userId;

    private String userName;

    private String authorityName;

    private String token;

}
