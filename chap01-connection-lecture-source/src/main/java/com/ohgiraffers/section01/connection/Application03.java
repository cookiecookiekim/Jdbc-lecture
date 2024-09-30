package com.ohgiraffers.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application03 { // 24-09-30 (월) 2교시 , jdbc-config.properties에서 파일 가져와서 사용하기
                            // → 예민한 정보를 숨겨서 사용하기.

    public static void main(String[] args) {

        /* index. 1. Properties 파일을 읽기 위해 Properties 객체 생성 */
        Properties prop = new Properties();
        Connection con = null;

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/section01/connection/jdbc-config.properties"));
            System.out.println("prop = " + prop);
            // {password=ohgiraffers, driver=com.mysql.cj.jdbc.Driver, user=ohgiraffers, url=jdbc:mysql://localhost/employee} 출력
            // map의 특징 : 저장 순서 일정하지 않음.
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password =  prop.getProperty("password");
            // 경로 기입
            Class.forName(driver);
         con = DriverManager.getConnection(url,user, password);

        } catch (IOException e) {
            throw new RuntimeException(e);
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
