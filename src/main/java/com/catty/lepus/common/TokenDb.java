package com.catty.lepus.common;

import com.catty.lepus.dto.TokenDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description TODO
 * @Author catty
 * @Date 2022/11/13 18:39
 **/

@Component
public class TokenDb {

    //key就是token字符串
    private Map<String, TokenDto> tokenMap = new HashMap<>();

    public int getTokenMapSize() {
        return tokenMap.size();
    }

    public TokenDto getTokenDto(String token) {
        if(token==null){
            return new TokenDto();
        }

//        if (StringUtils.isEmpty(token)) {
//            return new TokenDto();
//        }

        return tokenMap.get(token);
    }

    //也可以实现成登录用户互踢，2种方式，1是id前后缀，2是id-token=map的key-value
    public TokenDto addTokenDto(String token, TokenDto tokenDto) {

        if (Objects.isNull(tokenDto)) {
            return tokenDto;
        }

        return tokenMap.put(token, tokenDto);
    }

    //移除token，退出登录接口
    public TokenDto removeTokenDto(String token) {

        if (Objects.isNull(token)) {
            return null;
        }

        return tokenMap.remove(token);
    }

    public boolean isLogin(String token) {
        return tokenMap.get(token) != null;
    }
}

