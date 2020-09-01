package com.wang.util.MyExceptionResolver.MyException;

/**
 * 自定义异常
 */
public class SysException extends Exception{
    private String message;

    public SysException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
