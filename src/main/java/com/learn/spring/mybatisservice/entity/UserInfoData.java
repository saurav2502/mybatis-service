package com.learn.spring.mybatisservice.entity;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author kumar
 * @project mybatis-service
 * @since 8/1/2020
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserInfoData {
    private String userID;
    private String userName;
    private String userEmail;
    private String userContact;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public UserInfoData() {
    }

    public UserInfoData(String userID, String userName, String userEmail, String userContact) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userContact = userContact;
    }

    @Override public String toString() {
        return "UserInfoData{" +
            "userId='" + userID + '\'' +
            ", userName='" + userName + '\'' +
            ", userEmail='" + userEmail + '\'' +
            ", userContact='" + userContact + '\'' +
            '}';
    }
}
