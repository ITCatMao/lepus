package com.catty.lepus.exception;

import com.catty.lepus.constants.enums.StatusCode;

/**
 * @Description TODO
 * @Author catty
 * @Date 2022/11/22 09:59
 **/
public class ResultException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;

    private StatusCode status;


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultException(String message, StatusCode status) {
        super(message);
        this.status = status;
    }

    public ResultException(final String message) {
        this.message = message;
    }

    public static void throwEx(String message) {
        throw new ResultException(message);
    }

}
