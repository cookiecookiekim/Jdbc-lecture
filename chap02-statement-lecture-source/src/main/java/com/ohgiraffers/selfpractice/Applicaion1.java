package com.ohgiraffers.selfpractice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.selfpractice.JDBCTemplat2.close;
import static com.ohgiraffers.selfpractice.JDBCTemplat2.getConnection;

public class Applicaion1 {

    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
            while (rset.next()) {

                System.out.println(rset.getString("EMP_ID")+ rset.getString("EMP_NAME"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(stmt);
            close(rset);
        }

    }


}
