package com.example.service.serviceImpl;

import com.example.pojo.Doctor;
import com.example.pojo.Patient;
import com.example.service.LoginService;
import com.example.utils.DatabaseUtil;
import com.example.utils.UserThreadLocal;
import com.example.vo.ErrorCode;
import com.example.vo.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*LoginServiceServiceImpl 类实现了 LoginService 接口，提供登录功能
，包括验证用户信息、检查账号和密码是否为空，以及根据用户名从数据库获取用户信息的逻辑。
*/
/**
 * @Author: SaltedFish
 * @Description: 登录实现类
 */
public class LoginServiceServiceImpl implements LoginService {
    @Override
    public Result login(Patient patient) {
        try {
            //用户为空
            if(patient == null ) {
                return Result.fail(ErrorCode.USER_NOT_EXIST.getCode(),ErrorCode.USER_NOT_EXIST.getMsg());
            }else if(isEmpty(patient.getAccount())){
                return Result.fail(ErrorCode.USERNAME_IS_NULL.getCode(),ErrorCode.USERNAME_IS_NULL.getMsg());
            }else if(isEmpty(patient.getPassword())){
                return Result.fail(ErrorCode.PASSWORD_IS_NULL.getCode(),ErrorCode.PASSWORD_IS_NULL.getMsg());
            }
            String dataPassword = findUserByUserAccount(patient.getAccount()).getPassword();
            String inputPassword = patient.getPassword();

            if(dataPassword.equals(inputPassword)) {
                //放入用户线程池中
                UserThreadLocal.put(patient);
                return Result.success(null);
            } else {
                return Result.fail(ErrorCode.USER_LOGIN_ERROR.getCode(),ErrorCode.USER_LOGIN_ERROR.getMsg());
            }
        } catch (Exception e) {
            return Result.fail(ErrorCode.FAIL.getCode(),ErrorCode.FAIL.getMsg());
        }
    }
    @Override
    public Patient findUserByUserAccount(String databaseUsername) {
        String sql = "select * from patients where account = ?";
        Connection conn = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Patient user=null;
        try {
            conn = DatabaseUtil.getConnection();
            //PreparedStatement允许在执行前预定义SQL语句结构
            pstmt = conn.prepareStatement(sql);
            // 设置查询参数
            pstmt.setString(1, databaseUsername);
            //向数据库发送 SQL 查询
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Long doctorId = rs.getLong("patient_id");
                String account = rs.getString("account");
                String adminPassword = rs.getString("patient_password");
                Integer age = rs.getInt("age");
                String gender = rs.getString("gender");
                String adminName=rs.getString("patient_name");
                String avatar=rs.getString("avatar");
                user = new Patient(doctorId, account, adminPassword,adminName,age,gender,avatar);
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
