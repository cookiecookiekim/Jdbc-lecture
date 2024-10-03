package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application01 {

    public static void main(String[] args) {

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버를 읽혀줌
            con = DriverManager.getConnection("jdbc:mysql://localhost/employee", "ohgiraffers","ohgiraffers"); // url과 계정 전달

            System.out.println("con = " + con);
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
