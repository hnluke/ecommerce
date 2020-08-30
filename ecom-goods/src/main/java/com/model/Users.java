package com.model;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public class Users implements Serializable {

    public final static long serialVersionUID = 1002L;

    private Integer userId;
    private String userName;
    private String userPwd;
    private String userAddr;
    private String userPhone;
    private String userPrio;
    private Double userAccount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPrio() {
        return userPrio;
    }

    public void setUserPrio(String userPrio) {
        this.userPrio = userPrio;
    }

    public Double getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Double userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userAddr='" + userAddr + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPrio='" + userPrio + '\'' +
                ", userAccount=" + userAccount +
                '}';
    }
}
