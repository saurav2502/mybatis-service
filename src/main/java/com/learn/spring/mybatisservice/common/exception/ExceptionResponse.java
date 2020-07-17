package com.learn.spring.mybatisservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created By Saurav Kumar on 7/7/2020
 */

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
    private String statusCode;
    private String status;
}
