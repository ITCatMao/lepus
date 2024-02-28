package com.catty.lepus.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description token的过期时间
 * @Author catty
 * @Date 2022/11/13 18:01
 **/
@Data
public class Token implements Serializable {

    private static final long serialVersionUID = 4043470238789599973L;

    private String token;

    /**
     * 到期时间
     */
    private Date expireTime;

}
