package com.ohgiraffers.section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate { // 24-09-30 (월) 3교시

    /* title. jdbc 커넥션 정보를 하나의 틀로 만들어 필요한 곳에서 호출 */

    // 싱글톤 인스턴스
    public static Connection getConnection () {

        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            con = DriverManager.getConnection(url, prop); // prop 타입으로도 전달 가능
                                                  // driver와 url은 그대로 전달해야함.

        } catch (IOException e) { // FileReader의 트라이 캐치
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) { // Class.forName(driver)의 트라이 캐치
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } // finally 로 close(닫아)주면 사용할 수 없음.
        // → con return이 안 된다.
        // close 하려면 return과 다른 곳으로 빼야 한다.
        return con;
    }

    public static void close (Connection con) { // close 메서드를 따로 만들어 준다.
        // & 비트 연산자 : 좌항과 우항 모두 비교 (좌항이 false더라도 우항을 비교해줌)
        try {
            if (con != null & con.isClosed()){
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
