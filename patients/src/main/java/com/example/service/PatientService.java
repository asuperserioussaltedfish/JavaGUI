package com.example.service;

import com.example.pojo.Doctor;
import com.example.pojo.Message;
import com.example.pojo.Patient;
import com.example.vo.Result;

import java.util.List;

/**
 * @author SaltedFish
 */
public interface PatientService {
    /**
     * 修改密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return Result
     */
    Result updatePassword(String oldPassword,String newPassword);
    /**
     * 检索患者的未读邮件
     * @param patientId 患者id
     * @return List<Message>
     */
    List<Message> retrieveUnreadMessagesForDoctor(Long patientId);


    /**
     * 患者给医生发信息
     * @param senderId    发送者ID（患者ID）
     * @param receiverId  接收者ID（医生ID）
     * @param content     留言内容
     * @param messageTime 留言时间
     * @param status      留言状态
     * @return 是否留言成功
     */
    Result leaveMessageToDoctor(Long senderId, Long receiverId, String content, Long messageTime, int status);

    /**
     * 取消预约
     * @param doctorId 医生id
     * @param patientId 患者id
     * @return Result
     */
    Result cancelAppointment(Long doctorId,Long patientId);

    /**
     * 查信息
     * @param name 医生姓名
     * @return Result
     */
    Doctor selectDoctorByName(String  name);
    Patient selectPatientByAccount(String  account);
    List<Message> retrieveMessagesForDoctor(Long doctorId);
    boolean bookAppointment(Long patientId, Long doctorId, Long appointmentTime);
}
