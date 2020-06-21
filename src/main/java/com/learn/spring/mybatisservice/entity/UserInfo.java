package com.learn.spring.mybatisservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class UserInfo {
    private Long userId;
    private String userName;
    private Long userContact;
    private String userEmail;
}
