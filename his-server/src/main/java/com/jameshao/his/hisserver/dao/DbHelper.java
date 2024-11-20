package com.jameshao.his.hisserver.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbHelper {

    public static Connection getConnection(){
        Connection conn = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接数据库
            // "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai";
            String url = "jdbc:mysql://localhost:3306/his?serverTimezone=Asia/Shanghai";
            conn = DriverManager.getConnection(url, "root", "root");
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConn(Connection conn,ResultSet rs, PreparedStatement pst){
        try {
            if (rs != null)
                rs.close();

            if (pst != null)
                pst.close();

            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
