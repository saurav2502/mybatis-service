package com.learn.spring.mybatisservice.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NonNull
@ToString
public class UserInfo {
    private Long userId;
    private String userName;
    private Long userContact;
    private String userEmail;
    private String name;
    private String blog;
}
