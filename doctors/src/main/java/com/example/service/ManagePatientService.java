package com.example.service;

import com.example.pojo.*;
import com.example.vo.Result;

import java.util.List;

/**
 * @author SaltedFish
 */
public interface ManagePatientService {
    /**
     * 增加患者
     * @param patient 患者
     * @return Result
     */
    Result addPatient(Patient patient);
    /**
     * 删除患者
     * @param patientId 患者编号
     * @return Result
     */
    Result deletePatient(Long patientId);
    /**
     * 修改患者信息
     * @param patient 患者
     * @return Result
     */
    Result updatePatient(Patient patient,String account);
    /**
     * 获取所有患者信息
     * @return Result
     */
    List<Patient> getAllPatients();
    /**
     * 医生给患者留言
     *
     * @param senderId    发送者ID（医生ID）
     * @param receiverId  接收者ID（患者ID）
     * @param content     留言内容
     * @param messageTime 留言时间
     * @param status      留言状态
     * @return 是否留言成功
     */
    Result leaveMessageToPatient(Long senderId, Long receiverId, String content, Long messageTime, int status);

    /**
     * 获取患者预约自己的信息
     * @param doctorId 医生id
     * @return List<Appointment>
     */
    List<Appointment> getAppointmentsByDoctor(Long doctorId);

    /**
     * 通过患者姓名获取患者信息
     * @param patientName 患者姓名
     * @return 患者信息
     */
    List<Patient> getPatientByPatientName(String patientName);
    /**
     * 通过患者id获取患者信息
     * @param id 患者id
     * @return 患者信息
     */
    List<Patient> getPatientByPatientId(Long id);

    /**
     * 检索医生的未读邮件
     * @param doctorId 医生id
     * @return List<Message>
     */
    List<Message> retrieveUnreadMessagesForDoctor(Long doctorId);
    /**
     * 检索医生的已读邮件
     * @param doctorId 医生id
     * @return List<Message>
     */
    List<Message> retrieveReadMessagesForDoctor(Long doctorId);
    /**
     * 检索医生的所有邮件
     * @param doctorId 医生id
     * @return List<Message>
     */
    List<Message> retrieveMessagesForDoctor(Long doctorId);
    /**
     * 检索某个人发给医生的邮件
     * @param doctorId 医生id
     * @return List<Message>
     */
    List<Message> retrieveMessagesForDoctor1(Long doctorId,Long patientId);
    /**
     * 通过 id 查找病情
     * @param id id
     * @return List<PatientIllness>
     */
    List<PatientIllness> selectIllnessById(Long id);
    /**
     * 增加患者患病情况
     * @param patient 患者
     * @return Result
     */
    Result addIllness(PatientIllness patient);
     List<Message> getMessageById(Long doctorId);
    List<Appointment> getAppointmentsByDoctor1(Long doctorId,String name);
    List<Appointment> getAppointmentsByDoctor2(Long doctorId,Long patientId);

}
