package com.example.service;

import com.example.pojo.Doctor;
import com.example.vo.Result;

import java.util.List;

/**
 * @author SaltedFish
 */
public interface ManageDoctorsService {
    /**
     * 增加医生
     * @param doctor 医生
     * @return Result
     */
    Result addDoctor(Doctor doctor);
    /**
     * 删除医生(根据医生编号)
     * @param doctorId 医生编号
     * @return Result
     */
    Result deleteDoctor(Long doctorId);
    /**
     * 修改医生信息
     * @param doctor 医生
     * @return Result
     */
    Result updateDoctor(Doctor doctor);
    /**
     * 获取所有医生信息
     * @return Result
     */
    List<Doctor> getAllDoctor();
    /**
     * 修改医生预约时间
     * @param timeStart 开始时间
     * @param timeEnd 结束时间
     * @param doctorId 医生id
     * @return Result
     */
    Result updateDoctorSchedule(Long timeStart,Long timeEnd,Long doctorId);

    /**
     * 增加医生上班时间
     * @param timeStart 上班时间
     * @param timeEnd 下班时间
     * @param doctorId 医生id
     * @return Result
     */
    Result addDoctorSchedule(Long timeStart,Long timeEnd,Long doctorId);
}
