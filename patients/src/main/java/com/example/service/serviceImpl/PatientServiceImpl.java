package com.example.service.serviceImpl;

import com.example.pojo.Doctor;
import com.example.pojo.Message;
import com.example.pojo.Patient;
import com.example.service.PatientService;
import com.example.utils.DatabaseUtil;
import com.example.vo.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*`PatientServiceImpl` 类实现了 `PatientService` 接口，提供了一系列患者相关的服务，
包括更新密码、预约医生、发送和检索留言、取消预约，以及通过姓名或账号查询医生和患者信息的数据库操作。
 */
/**
 * @author SaltedFish
 */
public class PatientServiceImpl implements PatientService {
    @Override
    public Result updatePassword(String oldPassword, String newPassword) {
        boolean isSuccess = false;
        PreparedStatement pstmt ;
        Connection conn = null;
        String sql = "UPDATE patients SET patients_password = ? WHERE patients_password  = ?";
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, oldPassword);
            pstmt.executeUpdate();
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(conn);
        }
        if (isSuccess){
            return Result.success(true);
        }else{
            return Result.fail(0,"修改失败");
        }

    }

    @Override
    public List<Message> retrieveUnreadMessagesForDoctor(Long patientId) {
        String sql = "SELECT * FROM messages WHERE receiver_id = ? AND messages_status = 0";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Message> unreadMessages = new ArrayList<>();

        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, patientId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Message message = new Message();
                message.setContent(rs.getString("content"));
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
                message.setMessageTime(rs.getLong("message_time"));
                message.setMessagesStatus(rs.getInt("messages_status"));
                unreadMessages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DatabaseUtil.closeConnection(conn);
        }
            return unreadMessages;
        }
    /**
     * 预约医生
     * @param patientId 患者ID
     * @param doctorId 医生ID
     * @param appointmentTime 预约时间戳
     * @return 预约结果
     */
    @Override
    public boolean bookAppointment(Long patientId, Long doctorId, Long appointmentTime) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_time, appointment_status) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false); // 开始事务
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, patientId);
            pstmt.setLong(2, doctorId);
            pstmt.setLong(3, appointmentTime);
            pstmt.setInt(4, 0); // 默认预约状态为0（未完成）

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 1) {
                conn.commit(); // 提交事务
                result = true; // 表示预约成功
            } else {
                conn.rollback(); // 如果没有影响到1行数据，回滚事务
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // 如果发生异常，回滚事务
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DatabaseUtil.closeConnection(conn);
        }
        return result;
}

    @Override
    public Result leaveMessageToDoctor(Long senderId, Long receiverId, String content, Long messageTime, int status) {
        String sql = "INSERT INTO messages (content, sender_id, receiver_id, message_time, messages_status) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, content);
            pstmt.setLong(2, senderId);
            pstmt.setLong(3, receiverId);
            pstmt.setLong(4, messageTime);
            pstmt.setInt(5, status);
            int rowsAffected = pstmt.executeUpdate();
            isSuccess = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseUtil.closeConnection(conn);
        }
        if (isSuccess){
            return Result.success(true);
        } else {
            return Result.fail(0, "更新失败");
        }
    }

    @Override
    public Result cancelAppointment(Long doctorId, Long patientId) {
        return null;
    }

    @Override
    public Doctor selectDoctorByName(String name) {
        String sql = "SELECT * FROM doctors WHERE doctor_name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Doctor doctor = null;

        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                doctor = new Doctor();
                doctor.setAccount(rs.getString("account"));
                doctor.setId(rs.getLong("doctor_id"));
                doctor.setDoctorName(rs.getString("doctor_name"));
                doctor.setGender(rs.getString("gender"));
                doctor.setAge(rs.getInt("age"));
                doctor.setEducation(rs.getString("education"));
                doctor.setPassword(rs.getString("doctor_password"));
                doctor.setDepartment(rs.getString("department"));
                doctor.setAvatar(rs.getString("avatar"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(conn);
        }
        return doctor;
    }

    @Override
    public Patient selectPatientByAccount(String account) {
        Patient patient = null;
        String sql = "SELECT * FROM patients WHERE account = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, account);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                patient = new Patient();
                newPatinet(patient, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Patient> objects = new ArrayList<>();
        objects.add(patient);
        return objects.get(0);

    }
    private void newPatinet(Patient patient, ResultSet rs) throws SQLException {
        patient.setId(rs.getLong("patient_id"));
        patient.setAccount(rs.getString("account"));
        patient.setPatientName(rs.getString("patient_name"));
        patient.setGender(rs.getString("gender"));
        patient.setAge(rs.getInt("age"));
        patient.setPassword(rs.getString("patient_password"));
        patient.setAvatar(rs.getString("avatar"));
    }
    @Override
    public List<Message> retrieveMessagesForDoctor(Long doctorId) {
        String sql = "SELECT * FROM messages WHERE receiver_id = ? ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Message> unreadMessages = new ArrayList<>();

        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, doctorId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Message message = new Message();
                message.setContent(rs.getString("content"));
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
                message.setMessageTime(rs.getLong("message_time"));
                message.setMessagesStatus(rs.getInt("messages_status"));
                unreadMessages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DatabaseUtil.closeConnection(conn);
        }
        return unreadMessages;
    }
}
