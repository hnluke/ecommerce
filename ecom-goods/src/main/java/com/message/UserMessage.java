package com.message;

import com.model.Users;

import java.io.Serializable;

public class UserMessage implements Serializable {
    public final static long serialVersionUID = 1020L;

    private String messName;            // 信息名称
    private Users users;                // 用户对象

    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "messName='" + messName + '\'' +
                ", users=" + users +
                '}';
    }
}
