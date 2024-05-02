package com.example.service.serviceImpl;

import com.example.pojo.Doctor;
import com.example.pojo.Patient;
import com.example.service.DoctorService;
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
public class DoctorServiceImpl implements DoctorService {
    @Override
    public Result updatePassword(String oldPassword, String newPassword,Long id) {
        boolean isSuccess = false;
        PreparedStatement pstmt ;
        Connection conn = null;
        String sql = "UPDATE doctors SET doctor_password = ? WHERE doctor_password = ?and doctor_id=?";
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, oldPassword);
            pstmt.setLong(3,id);

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
    public List<Doctor> getPatientByDoctorAccount(String patientName) {
        Doctor patient = null;
        String sql = "SELECT * FROM doctors WHERE account = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, patientName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                patient = new Doctor();
                patient.setId(rs.getLong("doctor_id"));
                patient.setAccount(rs.getString("account"));
                patient.setDoctorName(rs.getString("doctor_name"));
                patient.setGender(rs.getString("gender"));
                patient.setAge(rs.getInt("age"));
                patient.setEducation(rs.getString("education"));
                patient.setPassword(rs.getString("doctor_password"));
                patient.setDepartment(rs.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        ArrayList<Doctor> objects = new ArrayList<>();
        objects.add(patient);
        return objects;
    }
}
