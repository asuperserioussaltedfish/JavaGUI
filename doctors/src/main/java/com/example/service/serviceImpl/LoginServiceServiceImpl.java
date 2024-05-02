package com.example.service.serviceImpl;

import com.example.pojo.Doctor;
import com.example.service.LoginService;
import com.example.utils.DatabaseUtil;
import com.example.utils.UserThreadLocal;
import com.example.vo.ErrorCode;
import com.example.vo.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: SaltedFish
 * @Description: 登录实现类
 */
public class LoginServiceServiceImpl implements LoginService {
    @Override
    public Result login(Doctor doctor) {
        try {
            //用户为空
            if(doctor == null ) {
                return Result.fail(ErrorCode.USER_NOT_EXIST.getCode(),ErrorCode.USER_NOT_EXIST.getMsg());
            }else if(isEmpty(doctor.getAccount())){
                return Result.fail(ErrorCode.USERNAME_IS_NULL.getCode(),ErrorCode.USERNAME_IS_NULL.getMsg());
            }else if(isEmpty(doctor.getPassword())){
                return Result.fail(ErrorCode.PASSWORD_IS_NULL.getCode(),ErrorCode.PASSWORD_IS_NULL.getMsg());
            }
            String dataPassword = findUserByUserAccount(doctor.getAccount()).getPassword();
            String inputPassword = doctor.getPassword();

            if(dataPassword.equals(inputPassword)) {
                //放入用户线程池中
                UserThreadLocal.put(doctor);
                return Result.success(null);
            } else {
                return Result.fail(ErrorCode.USER_LOGIN_ERROR.getCode(),ErrorCode.USER_LOGIN_ERROR.getMsg());
            }
        } catch (Exception e) {
            return Result.fail(ErrorCode.FAIL.getCode(),ErrorCode.FAIL.getMsg());
        }
    }
    @Override
    public Doctor findUserByUserAccount(String databaseUsername) {
        String sql = "select * from doctors where account = ?";
        Connection conn = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Doctor user=null;
        try {
            conn = DatabaseUtil.getConnection();
            //PreparedStatement允许在执行前预定义SQL语句结构
            pstmt = conn.prepareStatement(sql);
            // 设置查询参数
            pstmt.setString(1, databaseUsername);
            //向数据库发送 SQL 查询
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Long doctorId = rs.getLong("doctor_id");
                String account = rs.getString("account");
                String adminPassword = rs.getString("doctor_password");
                Integer age = rs.getInt("age");
                String gender = rs.getString("gender");
                String adminName=rs.getString("doctor_name");
                String avatar=rs.getString("avatar");
                String department=rs.getString("department");
                String education=rs.getString("education");
                user = new Doctor(doctorId, account, adminPassword,adminName,age,gender,education,department,avatar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(conn);
        }
        return user;
    }

    /**
     * 判断是否为空
     * @param str 被判断的字符串
     * @return 是否为null或空字符串
     */
    public boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
}
