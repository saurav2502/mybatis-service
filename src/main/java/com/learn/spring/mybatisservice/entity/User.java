package com.learn.spring.mybatisservice.entity;

import lombok.*;

/**
 * @author Saurav Kumar
 * @project mybatis-service
 * @since 7/15/2020
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NonNull
@ToString
public class User {
    private String name;
    private String blog;
}
