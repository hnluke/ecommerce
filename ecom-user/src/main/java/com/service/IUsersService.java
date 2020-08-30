package com.service;

import com.model.Users;

public interface IUsersService {

    // 验证用户名和密码
    public boolean verify(String userName, String pwd);

    // 更新用户账户余额
    public boolean updateUserBalance(Users users);

    public Users findUserByUserName(String userName);
}
