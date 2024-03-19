package com.catty.lepus.service.impl;

import com.catty.lepus.common.Token;
import com.catty.lepus.common.TokenDb;
import com.catty.lepus.constants.UserConstants;
import com.catty.lepus.constants.enums.StatusCode;
import com.catty.lepus.dao.TestTaskMapper;
import com.catty.lepus.dao.UserMapper;
import com.catty.lepus.dto.ResultDto;
import com.catty.lepus.dto.TokenDto;
import com.catty.lepus.dto.user.AddUserDto;
import com.catty.lepus.dto.user.LoginUserDto;
import com.catty.lepus.entity.User;
import com.catty.lepus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @Description TODO
 * @Author catty
 * @Date 2022/9/26 23:23
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenDb tokenDb;

    @Override
    public ResultDto<User> register(AddUserDto userReq) {
        String userName = userReq.getUsername();
        String passWord = userReq.getPassword();

        //1.校验用户名是否已经存在
        User resultUser = userMapper.selectByUserName(userName);
        if (Objects.nonNull(resultUser)) {
            return ResultDto.fail("用户名已存在");
        }

        User user = new User();

        //2.生成盐，密码MD5加密存储再保存到数据库中
        String salt = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
        user.setSalt(salt);
        String newPwd = DigestUtils.md5DigestAsHex((UserConstants.md5Hex_sign + userName + passWord).getBytes());
        user.setUsername(userName);
        user.setPassword(newPwd);
        user.setChannel(1);
        user.setProductLineId(1L);
        user.setIsDelete(0);
        user.setGmtCreated(new Date());
        user.setGmtUpdated(new Date());
        userMapper.insert(user);
        return ResultDto.success("注册成功", user);
    }

//    @Override
//    public ResultDto<User> update(AddUserDto userDto) {
//        return userMapper.updateByPrimaryKeySelective(userDto);
//    }

    @Override
    public ResultDto<Token> login(LoginUserDto loginReq) {
        String userName = loginReq.getUsername();
        String passWord = loginReq.getPassword();
        log.info("登录username=" + userName + ";" + "登录密码=" + passWord);
        //1.检查数据库中是否存在该用户
        User resultUser = userMapper.selectByUserName(userName);
        if (Objects.isNull(resultUser)) {
            return ResultDto.fail("用户不存在");
        }
        //2.校验密码是否正确
        String mdPWD = DigestUtils.md5DigestAsHex((UserConstants.md5Hex_sign + userName + passWord).getBytes());
        if (!resultUser.getPassword().equals(mdPWD)) {
            return ResultDto.fail("密码错误，请重新输入密码");
        }
        //3.若存在，则创建Token对象，生成token并将相关信息存入TokenDto
        Token token = new Token();
        String tokenStr = DigestUtils.md5DigestAsHex((System.currentTimeMillis() + userName + passWord).getBytes());
        token.setToken(tokenStr);
        token.setExpireTime(new Date(new Date().getTime() + 6000000));

        TokenDto tokenDto = new TokenDto();
        tokenDto.setUserId(resultUser.getId());
        tokenDto.setUserName(userName);
        tokenDto.setToken(tokenStr);
        tokenDto.setAuthorityName(resultUser.getAuthorityName());

        tokenDb.addTokenDto(tokenStr, tokenDto);

        return ResultDto.success("成功", token);
    }
}
