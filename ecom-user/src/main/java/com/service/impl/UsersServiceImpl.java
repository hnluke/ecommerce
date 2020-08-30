package com.service.impl;

import com.mapper.UsersMapper;
import com.model.Users;
import com.service.IUsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UsersServiceImpl implements IUsersService {

    @Resource
    UsersMapper usersMapper;
    @Override
    public boolean verify(String userName, String pwd) {
        if(userName != null && !("".equals(userName.trim()))) {
            Users users = usersMapper.findUserByUserName(userName);
            if(users.getUserPwd().equals(pwd)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updateUserBalance(Users users) {
        return usersMapper.updateUser(users);
    }

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public Users findUserByUserName(String userName) {
        return usersMapper.findUserByUserName(userName);
    }
}
