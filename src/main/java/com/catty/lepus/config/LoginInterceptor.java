package com.catty.lepus.config;

import com.catty.lepus.common.TokenDb;
import com.catty.lepus.constants.UserConstants;
import com.catty.lepus.dto.TokenDto;
import com.catty.lepus.exception.ResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Description 设置登录过滤器
 * @Author catty
 * @Date 2024/3/8 20:57
 **/
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenDb tokenDb;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {

        String tokenStr = request.getHeader(UserConstants.LOGIN_TOKEN);
        log.info("request.getHeader(UserConstants.LOGIN_TOKEN):" + request.getHeader(UserConstants.LOGIN_TOKEN));


        String requestUri = request.getRequestURI();
        log.info("request.getRequestURI() " + requestUri);
        //如果请求中含有token
        if (StringUtils.isEmpty(tokenStr)) {
            response.setStatus(401);
            ResultException.throwEx("客户端未传token " + requestUri);
        }
        //获取token
        TokenDto tokenDto = tokenDb.getTokenDto(tokenStr);
        log.info("tokenDto: " + tokenDb.getTokenDto(tokenStr));
        //如果user未登录
        if (Objects.isNull(tokenDto)) {
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
            //此处直接抛出异常
            response.setStatus(401);
            ResultException.throwEx("用户未登录");
            return false;
        } else {
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }


}
