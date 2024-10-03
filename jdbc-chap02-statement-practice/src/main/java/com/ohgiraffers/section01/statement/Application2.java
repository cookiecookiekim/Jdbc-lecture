package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JTBCTemplate.close;
import static com.ohgiraffers.common.JTBCTemplate.getConnection;

public class Application2 {

    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        try {
            stmt = con.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.print("조회할 사번은?");
            String empId = sc.nextLine();
            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

            rset = stmt.executeQuery(query);

            if (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + rset.getString("EMP_NAME"));
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
