package com.catty.lepus.controller;

import com.catty.lepus.common.Token;
import com.catty.lepus.common.TokenDb;
import com.catty.lepus.constants.UserConstants;
import com.catty.lepus.dto.ResultDto;
import com.catty.lepus.dto.TokenDto;
import com.catty.lepus.dto.user.AddUserDto;
import com.catty.lepus.dto.user.LoginUserDto;
import com.catty.lepus.entity.User;
import com.catty.lepus.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description TODO
 * @Author catty
 * @Date 2022/9/26 23:17
 **/
@Slf4j
@Api(tags = "测试用例管理平台-用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenDb tokenDb;


    /**
     * @param addUserDto
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "仅用于测试用户")
    public ResultDto<User> register(@RequestBody AddUserDto addUserDto) {
        log.info("用户注册方法调用入参： " + addUserDto);
        User user = new User();
        BeanUtils.copyProperties(addUserDto, user);
        return userService.register(addUserDto);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "登录方法")
    public ResultDto<Token> login(@RequestBody LoginUserDto loginUserDto) {
        log.info("用户登录方法调用入参： " + loginUserDto);
        User user = new User();
        BeanUtils.copyProperties(loginUserDto, user);
        return userService.login(loginUserDto);
    }

    @ApiOperation(value = "登出接口")
    @DeleteMapping("/logout")
    public ResultDto logout(HttpServletRequest request){
        String token = request.getHeader(UserConstants.LOGIN_TOKEN);
        boolean loginFlag = tokenDb.isLogin(token);

        if(!loginFlag){
            return ResultDto.fail("用户未登录，无需退出");
        }
        TokenDto tokenDto = tokenDb.removeTokenDto(token);

        return ResultDto.success("成功",tokenDto);
    }

    @ApiOperation(value = "是否已经登录接口")
    @GetMapping("/isLogin")
    public ResultDto<TokenDto> isLogin(HttpServletRequest request) {

        String token = request.getHeader(UserConstants.LOGIN_TOKEN);

        boolean loginFlag = tokenDb.isLogin(token);

        TokenDto tokenDto = tokenDb.getTokenDto(token);

        return ResultDto.success("成功",tokenDto);
    }

}
