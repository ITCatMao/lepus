package com.catty.lepus.service;

import com.catty.lepus.common.Token;
import com.catty.lepus.dto.ResultDto;
import com.catty.lepus.dto.user.AddUserDto;
import com.catty.lepus.dto.user.LoginUserDto;
import com.catty.lepus.entity.User;

public interface UserService {

    // 注册方法
    ResultDto<User> register(AddUserDto userReq);

//    //用户更新
//    ResultDto<User> update(AddUserDto userDto);

    //登录方法
    ResultDto<Token> login(LoginUserDto loginReq);
}
