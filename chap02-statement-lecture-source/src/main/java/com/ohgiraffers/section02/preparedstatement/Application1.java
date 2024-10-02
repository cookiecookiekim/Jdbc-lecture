package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 { // 24-10-02 (수) 1교시 PreparedStatement (캐싱)

    public static void main(String[] args) {

        /* comment. PreparedStatement (준비된 statement)
        *   Statement는 SQL 쿼리문을 실행할 때마다 SQL문이 DBMS에 전송되어 DBMS에서 SQL문을 파싱하고 컴파일 하는 과정을 거치게 된다.
        *   PreparedStatement는 최초에 한 번 실행했을 시
        *   SQL문을 파싱하고 컴파일 하지만, 동일한 SQL구문을 여러번 실행하게 되면 최초에 컴파일한 SQL 구문을 재사용하게 된다.
        *   따라서 파싱하고 컴파일 하는 과정을 생략하게 되어 성능 향상된다. */

        Connection con = getConnection(); // 연결통로

        PreparedStatement pstmt = null; // 쿼리문 실행

        ResultSet rset = null; // 쿼리문 실행 결과

        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE";

        try {
            pstmt = con.prepareStatement(query); // pstmt에 쿼리문을 담아줌
            rset = pstmt.executeQuery(); // 담아놓은 쿼리문을 실행해라

            while (rset.next()) { // 하나씩 꺼내서 확인하겠다.
                System.out.println(rset.getString("EMP_ID") + " 번 " + rset.getString("EMP_NAME"));
            } // 값에 접근할 때에는 '컬럼명'으로 접근.

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(con);
            close(pstmt); // 굳이 따로 만들지 않아도 statement(부모)의 상속을 받은 거라 바로 사용 가능./
        }
    }
}
