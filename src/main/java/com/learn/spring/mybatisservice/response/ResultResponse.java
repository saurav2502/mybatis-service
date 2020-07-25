package com.learn.spring.mybatisservice.response;

import org.springframework.http.HttpStatus;

/**
 * @project mybatis-service
 * @uthor kumar
 * @since 7/25/2020
 */
public class ResultResponse<T> {
    private char status;
    private String resultMessage;
    private HttpStatus statusCode;
    private T returnValue;

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public T getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(T returnValue) {
        this.returnValue = returnValue;
    }

    public ResultResponse(char status, String resultMessage, HttpStatus statusCode, T returnValue) {
        this.status = status;
        this.resultMessage = resultMessage;
        this.statusCode = statusCode;
        this.returnValue = returnValue;
    }

    public ResultResponse() {
    }
}
