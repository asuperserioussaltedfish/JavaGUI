package com.example.service;

import com.example.pojo.Doctor;
import com.example.pojo.Patient;
import com.example.vo.Result;

import java.util.List;

/**
 * @author SaltedFish
 */
public interface DoctorService {
    /**
     * 修改医生密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param id 用户id
     * @return Result
     */
    Result updatePassword(String oldPassword,String newPassword,Long id);

    /**
     * 通过医生账号获取医生信息
     * @param doctorAccount 医生账号
     * @return 医生
     */
    List<Doctor> getPatientByDoctorAccount(String doctorAccount);
}
