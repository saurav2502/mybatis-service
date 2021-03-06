package com.learn.spring.mybatisservice.entity;

/**
 * keeping user details
 * @author kumar
 * @project mybatis-service
 * @since 8/1/2020
 */
public class Users {
    /**
     * ID
     */
    private int ID;
    /**
     * contains user id
     */
    private String userID;

    /**
     * contails user name
     */
    private String userName;

    /**
     * contains user email
     */
    private String userEmail;

    /**
     * contains user contacts
     */
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * {@utility method}
     * @return
     */
    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Users{");
        stringBuilder.append("userID=" + userID);
        stringBuilder.append(", userName=" +userName);
        stringBuilder.append(", userEmail="+userEmail);
        stringBuilder.append(", userContact="+userContact);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
