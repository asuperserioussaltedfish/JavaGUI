package com.example.service.serviceImpl;

import com.example.pojo.Appointment;
import com.example.pojo.Message;
import com.example.pojo.Patient;
import com.example.pojo.PatientIllness;
import com.example.service.ManagePatientService;
import com.example.utils.DatabaseUtil;
import com.example.vo.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SaltedFish
 */

public class ManagePatientServiceServiceImpl implements ManagePatientService {
    @Override
    public Result addPatient(Patient patient) {
        boolean isSuccess = false;
        String sql = "INSERT INTO patients (account, patient_name, gender, age, patient_password) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, patient.getAccount());
            pstmt.setString(2, patient.getPatientName());
            pstmt.setString(3, patient.getGender());
            pstmt.setInt(4, patient.getAge());
            pstmt.setString(5, patient.getPassword());
//            pstmt.setString(6, patient.getAvatar());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
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
        if (isSuccess) {
            return Result.success(true);
        } else {
            return Result.fail(0, "增加失败");
        }
    }

    @Override
    public Result deletePatient(Long patientId) {
        String checkSql = "SELECT COUNT(*) FROM patients WHERE patient_id = ?";
        String deleteSql = "DELETE FROM patients WHERE patient_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setLong(1, patientId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return Result.fail(0, "删除失败：患者不存在");
                }
            }
            pstmt.close();
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setLong(1, patientId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // 删除操作成功
                return Result.success(true);
            } else {
                return Result.fail(0, "删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.fail(0, "删除失败：" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Result updatePatient(Patient patient,String account) {
        boolean isSuccess = false;
        String sql = "UPDATE patients SET patient_name = ?, gender = ?, age = ?, patient_password = ?WHERE account = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, patient.getPatientName());
            pstmt.setString(2, patient.getGender());
            pstmt.setInt(3, patient.getAge());
            pstmt.setString(4, patient.getPassword());
//            pstmt.setString(5, patient.getAvatar());
            pstmt.setString(5,account);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
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
        if (isSuccess) {
            return Result.success(true);
        } else {
            return Result.fail(0, "更新失败");
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                newPatinet(patient, rs);
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return patients;
    }

    @Override
    public Result leaveMessageToPatient(Long senderId, Long receiverId, String content, Long messageTime, int status) {
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
        if (isSuccess) {
            return Result.success(true);
        } else {
            return Result.fail(0, "更新失败");
        }
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE doctor_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, doctorId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getLong("doctor_id"),
                        rs.getLong("patient_id"),
                        rs.getLong("appointment_time"),
                        rs.getInt("appointment_status")
                );
                appointments.add(appointment);
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

        return appointments;
    }

    @Override
    public List<Patient> getPatientByPatientName(String patientName) {
        if (patientName == null || "".equals(patientName) || " ".equals(patientName)) {
            return getAllPatients();
        }

        Patient patient = null;
        String sql = "SELECT * FROM patients WHERE patient_name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, patientName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                patient = new Patient();
                newPatinet(patient, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        ArrayList<Patient> objects = new ArrayList<>();
        objects.add(patient);
        return objects;

    }

    @Override
    public List<Patient> getPatientByPatientId(Long id) {
        Patient patient = null;
        String sql = "SELECT * FROM patients WHERE patient_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                patient = new Patient();
                newPatinet(patient, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        ArrayList<Patient> objects = new ArrayList<>();
        objects.add(patient);
        return objects;
    }

    private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DatabaseUtil.closeConnection(conn);
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
    public List<Message> retrieveUnreadMessagesForDoctor(Long receiverId) {
        String sql = "SELECT * FROM messages WHERE receiver_id = ? AND messages_status = 0";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Message> unreadMessages = new ArrayList<>();

        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, receiverId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Message message = new Message();
                message.setContent(rs.getString("content"));
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
                message.setMessageTime(rs.getLong("message_time"));
                message.setMessageStatus(rs.getInt("messages_status"));
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

    @Override
    public List<Message> retrieveReadMessagesForDoctor(Long receiverId) {
        String sql = "SELECT * FROM messages WHERE receiver_id = ? AND messages_status = 1";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Message> unreadMessages = new ArrayList<>();

        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, receiverId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Message message = new Message();
                message.setContent(rs.getString("content"));
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
                message.setMessageTime(rs.getLong("message_time"));
                message.setMessageStatus(rs.getInt("messages_status"));
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
                message.setMessageStatus(rs.getInt("messages_status"));
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

    @Override
    public List<Message> retrieveMessagesForDoctor1(Long doctorId, Long patientId) {
        String sql = "SELECT * FROM messages WHERE receiver_id = ? and sender_id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Message> unreadMessages = new ArrayList<>();

        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, doctorId);
            pstmt.setLong(2, patientId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Message message = new Message();
                message.setContent(rs.getString("content"));
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
                message.setMessageTime(rs.getLong("message_time"));
                message.setMessageStatus(rs.getInt("messages_status"));
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

    @Override
    public List<PatientIllness> selectIllnessById(Long id) {

        PatientIllness patient = null;
        String sql = "SELECT * FROM illness WHERE patient_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                patient = new PatientIllness();
                patient.setId(rs.getLong("id"));
                patient.setPatientId(rs.getLong("patient_id"));
                patient.setIllness(rs.getString("illness"));
                patient.setIllnessTime(rs.getLong("illness_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        ArrayList<PatientIllness> objects = new ArrayList<>();
        objects.add(patient);
        return objects;
    }

    @Override
    public Result addIllness(PatientIllness illness) {
        boolean isSuccess = false;
        String sql = "INSERT INTO illness ( patient_id, illness_time, illness) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, illness.getPatientId());
            pstmt.setLong(2, illness.getIllnessTime());
            pstmt.setString(3, illness.getIllness());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
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
        if (isSuccess) {
            return Result.success(true); // 操作成功
        } else {
            return Result.fail(0, "添加失败");
        }
    }

    @Override
    public List<Message> getMessageById(Long doctorId) {
        String sql = "SELECT * FROM messages WHERE message_id = ? AND messages_status = 0";
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
                message.setMessageStatus(rs.getInt("messages_status"));
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

    @Override
    public List<Appointment> getAppointmentsByDoctor1(Long doctorId, String name) {
        Patient patient = getPatientByPatientName(name).get(0);

        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE doctor_id = ? and patient_id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, doctorId);
            pstmt.setLong(2,patient.getId());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getLong("patient_id"),
                        rs.getLong("doctor_id"),
                        rs.getLong("appointment_time"),
                        rs.getInt("appointment_status")
                );
                appointments.add(appointment);
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

        return appointments;
    }
    @Override
    public List<Appointment> getAppointmentsByDoctor2(Long doctorId, Long patientId) {

        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE doctor_id = ? and patient_id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, doctorId);
            pstmt.setLong(2,patientId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getLong("patient_id"),
                        rs.getLong("doctor_id"),
                        rs.getLong("appointment_time"),
                        rs.getInt("appointment_status")
                );
                appointments.add(appointment);
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

        return appointments;
    }
}

