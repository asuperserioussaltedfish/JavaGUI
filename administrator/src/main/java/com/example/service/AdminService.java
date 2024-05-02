package com.example.service;

import com.example.vo.Result;

/**
 * @author SaltedFish
 */
public interface AdminService {
    /**
     * 修改管理员密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return Result
     */
    Result updatePassword(String oldPassword,String newPassword);
}
