package com.catty.lepus.exception;

import com.catty.lepus.constants.enums.StatusCode;

/**
 * @Description TODO
 * @Author catty
 * @Date 2022/11/22 09:59
 **/
public class ResultException extends RuntimeException {
    private StatusCode status;

    public ResultException(String message, StatusCode status) {
        super(message);
        this.status = status;
    }

}
