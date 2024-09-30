package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 { // 24-09-30 (월) 4교시

    public static void main(String[] args) { // Connection , Statement, ResultSet은 불러오기 위한 한 세트

        Connection con = getConnection();

        /* comment. Statement
        *   쿼리문을 저장하고 실행할 수 있는 기능을 하는 '인터페이스' */
        Statement stmt = null; // Statement는 통로이다.

        /* comment. ResultSet
        *   select 결과 집합을 받아올 수 있는 인터페이스 */
        ResultSet rset = null;

        try {
            stmt = con.createStatement(); // connection 이용해서 인스턴스 생성

            rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");

            while (rset.next()) { // next()는 한 행씩 찾아서 반환, 데이터가 없으면 탈출
                /* comment. next() : ResultSet을 목록화시켜 행이 존재하면 true
                *   존재하지 않으면 false 반환 */
                System.out.println(rset.getString("EMP_ID") + "번 " + rset.getString("EMP_NAME"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(stmt);
        }

    }

}
