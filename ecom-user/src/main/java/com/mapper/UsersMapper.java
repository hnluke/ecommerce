package com.mapper;

import com.model.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {
    /**
     * 新增用户
     * @param users
     * @return
     */
    public boolean insertUser(Users users);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public boolean deleteUser(Integer userId);

    /**
     * 修改用户信息
     * @param users
     * @return
     */
    public boolean updateUser(Users users);

    /**
     * 查询所有用户
     * @return
     */
    public List<Users> findAll();

    /**
     * 根据用户名来查询用户
     * @return
     */
    public Users findUserByUserName(String userName);
}
