package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application01 { // 24-09-30 (월) 2교시, JDBC 연결 및 사용하기

    public static void main(String[] args) { // JDBC는 공식이므로 외워도 된다.

        /* comment. DB 접속을 위한 Connection 인스턴스 생성 */
        // 처음에 일단 null로 초기화 진행
        Connection con = null; // 초기화 이유 : finally 블럭에서 자원 해제를 위한 과정

        /* comment. 사용할 드라이버 등록 */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // → JDBC 사용할 건데, Mysql 툴에 맞춰진 드라이버를 사용하겠다.

            /* comment.  connection 객체는 인터페이스이기 때문에 직접적인 인스턴스 생성 불가 */
            con = DriverManager.getConnection("jdbc:mysql://localhost/employee", "ohgiraffers", "ohgiraffers");
                // "com.mysql.cj.jdbc.Driver"에 DriverManager가 있는데 위에 스키마에 접근하겠다.

            System.out.println("con = " + con); // com.mysql.cj.jdbc.ConnectionImpl@6c40365cc 주소값 출력

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); // 예외처리가 필수이므로 try catch 구문으로 감싸주기
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) { // 만약 con이 존재한다면
                try {
                    con.close(); // con을 닫아 주겠다.
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
