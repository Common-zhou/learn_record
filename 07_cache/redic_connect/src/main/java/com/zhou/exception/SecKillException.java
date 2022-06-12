package com.zhou.exception;

/**
 * @author zhoubing
 * @date 2022-06-12 16:10
 */
public class SecKillException extends RuntimeException {

    private int code;

    public SecKillException(String message, int code) {
        super(message);
        this.code = code;
    }
}
