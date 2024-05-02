package com.example.service.serviceImpl;

import com.example.pojo.Doctor;
import com.example.service.ManageDoctorsService;
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
public class ManageDoctorsServiceServiceImpl implements ManageDoctorsService {
    @Override
    public Result addDoctor(Doctor doctor) {
        boolean isSuccess = false;
        String sql = "INSERT INTO doctors (account, doctor_name, gender, age, education, doctor_password, department, avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, doctor.getAccount());
            pstmt.setString(2, doctor.getDoctorName());
            pstmt.setString(3, doctor.getGender());
            pstmt.setInt(4, doctor.getAge());
            pstmt.setString(5, doctor.getEducation());
            pstmt.setString(6, doctor.getPassword());
            pstmt.setString(7, doctor.getDepartment());
            pstmt.setString(8, doctor.getAvatar());
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
        if (isSuccess){
            return Result.success(true);
        }else{
            return Result.fail(0,"增加失败");
        }
    }

    @Override
    public Result deleteDoctor(Long doctorId) {
        String checkSql = "SELECT COUNT(*) FROM doctors WHERE doctor_id = ?";
        String deleteSql = "DELETE FROM doctors WHERE doctor_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setLong(1, doctorId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    // 医生不存在
                    return Result.fail(0, "删除失败：医生不存在");
                }
            }
            // 医生存在，执行删除操作
            pstmt.close(); // 关闭上一个PreparedStatement
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setLong(1, doctorId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // 删除操作成功
                return Result.success(true);
            } else {
                // 医生存在，但删除失败（可能已被同时删除）
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
    public Result updateDoctor(Doctor doctor) {
        boolean isSuccess = false;
        String sql = "UPDATE doctors SET account = ?, doctor_name = ?, age = ?, gender = ?, education = ?, doctor_password = ?, department = ?, avatar = ? WHERE doctor_id = ?";
        Connection conn = null;
        PreparedStatement pstmt ;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);

            // 绑定具体的对象属性到预定义的SQL语句参数位置
            pstmt.setString(1, doctor.getAccount());
            pstmt.setString(2, doctor.getDoctorName());
            pstmt.setInt(3, doctor.getAge());
            pstmt.setString(4, doctor.getGender());
            pstmt.setString(5, doctor.getEducation());
            pstmt.setString(6, doctor.getPassword());
            pstmt.setString(7, doctor.getDepartment());
            pstmt.setString(8, doctor.getAvatar());
            pstmt.setLong(9, doctor.getId());
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
    public List<Doctor> getAllDoctor() {
        List<Doctor> allDoctor = new ArrayList<>();
        String sql = "select * from doctors";
        Connection conn = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            conn = DatabaseUtil.getConnection();
            //PreparedStatement允许在执行前预定义SQL语句结构
            pstmt = conn.prepareStatement(sql);
            //向数据库发送 SQL 查询
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String account = rs.getString("account");
                Long doctorId = rs.getLong("doctor_id");
                String doctorName = rs.getString("doctor_name");
                String gender = rs.getString("gender");
                Integer age = rs.getInt("age");
                String education=rs.getString("education");
                String doctorPassword =rs.getString("doctor_password");
                String department=rs.getString("department");
                String avatar=rs.getString("avatar");
                allDoctor.add(new Doctor(doctorId,account, doctorPassword,doctorName,age,gender,
                        education,department,avatar));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(conn);
        }
        return allDoctor;
    }

    @Override
    public Result updateDoctorSchedule(Long timeStart,Long timeEnd,Long doctorId) {
        boolean isSuccess;
        String sql = "UPDATE doctor_schedule SET time_start = ?, time_end = ? WHERE doctor_id = ?";
        isSuccess = isSuccess(timeStart, timeEnd, doctorId, false, sql);
        if (isSuccess){
            return Result.success(true);
        }else{
            return Result.fail(0,"修改失败");
        }
    }

    @Override
    public Result addDoctorSchedule(Long timeStart, Long timeEnd, Long doctorId) {
        boolean isSuccess;
        String sql = "INSERT INTO doctor_schedule (time_start, time_end, doctor_id) VALUES (?, ?, ?)";
        isSuccess = isSuccess(timeStart, timeEnd, doctorId, false, sql);
        if (isSuccess){
            return Result.success(true);
        }else{
            return Result.fail(0,"添加失败");
        }
    }

    private boolean isSuccess(Long timeStart, Long timeEnd, Long doctorId, boolean isSuccess, String sql) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, timeStart);
            pstmt.setLong(2, timeEnd);
            pstmt.setLong(3, doctorId);
            int rowsAffected = pstmt.executeUpdate();
            isSuccess = rowsAffected > 0;
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
        return isSuccess;
    }
}
