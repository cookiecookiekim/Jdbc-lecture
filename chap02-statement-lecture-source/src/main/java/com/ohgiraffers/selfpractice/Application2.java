package com.ohgiraffers.selfpractice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.selfpractice.JDBCTemplat2.getConnection;

public class Application2 {

    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        try {
            stmt = con.createStatement();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
