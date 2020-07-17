package com.learn.spring.mybatisservice.common.exception;

/**
 * Created By Saurav Kumar on 7/7/2020
 */

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String msg) {
        super(msg);
    }
}
