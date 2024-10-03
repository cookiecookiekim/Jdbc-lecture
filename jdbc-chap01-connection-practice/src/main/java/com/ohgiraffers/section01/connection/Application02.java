package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application02 {

    public static void main(String[] args) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/employee";
        String user = "ohgiraffers";
        String password = "ohgiraffers";

        Connection con = null; // con 초기화

        try {
            Class.forName(driver); // 드라이버 읽히기
            con = DriverManager.getConnection(url,user,password); // 정보 전달


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }
}
