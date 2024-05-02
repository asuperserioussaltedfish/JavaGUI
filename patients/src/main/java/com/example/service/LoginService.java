package com.example.service;

import com.example.pojo.Doctor;
import com.example.pojo.Patient;
import com.example.vo.Result;

/**
 * @Author: SaltedFish
 * @Description: 登录逻辑操作
 */
public interface LoginService {
    /**
     * 登录
     * @param patient 用户
     * @return Result
     */
    Result login(Patient patient);
    /**
     * 通过username查找用户
     * @param databaseUsername 用户名
     * @return User
     */
    Patient findUserByUserAccount(String databaseUsername);
}
