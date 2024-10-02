package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 { // 24-10-02 (수) 1교시 PreparedStatement 이어서

    public static void main(String[] args) {

        /* comment. ?
        *   - placeholder
        *   - ? 의 개수, 시작값(1) */

        Connection con = getConnection();

        PreparedStatement pstmt = null; // Prepared를 쓴다 → 쿼리문에서 ? 를 쓸 수 있다.

        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회하실 사번을 입력해 주세요 : ");
        String empId = sc.nextLine();

        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?"; // ? 부분만 동적으로 change

        try {
            pstmt = con.prepareStatement(query); // pstmt에 쿼리문 전달
            pstmt.setString(1,empId); // ? 첫번째 순선에다가 empId를 넣겠다.

            rset = pstmt.executeQuery();

            if (rset.next()) { // 만약 rset에 조회한 결과문이 존재한다면
                System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
            close(rset);
        }
    }
}
