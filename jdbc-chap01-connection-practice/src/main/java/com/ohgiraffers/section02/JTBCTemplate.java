package com.ohgiraffers.section02;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JTBCTemplate {

    public static Connection getConnection () {

        Connection con = null;

        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String drive = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(drive);
            con = DriverManager.getConnection(url,prop);



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    public static void close (Connection con) {
        try {
            if (con != null & con.isClosed()) {
                close(con);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
