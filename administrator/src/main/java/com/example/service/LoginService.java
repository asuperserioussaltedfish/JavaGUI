package com.example.service;

import com.example.pojo.Admin;
import com.example.vo.Result;

/**
 * @Author: SaltedFish
 * @Description: 登录逻辑操作
 */
public interface LoginService {
    /**
     * 登录
     * @param admin 用户
     * @return Result
     */
    Result login(Admin admin);
    /**
     * 通过username查找用户
     * @param databaseUsername 用户名
     * @return User
     */
    Admin findUserByUserAccount(String databaseUsername);
}
