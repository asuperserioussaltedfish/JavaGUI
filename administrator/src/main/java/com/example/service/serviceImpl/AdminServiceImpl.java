package com.example.service.serviceImpl;

import com.example.service.AdminService;
import com.example.utils.DatabaseUtil;
import com.example.vo.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author SaltedFish
 */
public class AdminServiceImpl implements AdminService {
    @Override
    public Result updatePassword(String oldPassword, String newPassword) {
        boolean isSuccess = false;
        PreparedStatement pstmt ;
        Connection conn = null;
        String sql = "UPDATE admins SET admin_password = ? WHERE admin_password = ?";
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
}
