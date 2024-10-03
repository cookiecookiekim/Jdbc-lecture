package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        Properties prop = new Properties();

        int result = 0;
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("insertMenu");

            Scanner sc = new Scanner(System.in);

            System.out.print("이름를 입력하세요 : ");
            String menuName = sc.nextLine();
            System.out.print("가격를 입력하세요 : ");
            int menuPrice = sc.nextInt();
            System.out.print("카테코리 코드를 입력하세요 : ");
            int categoryCode = sc.nextInt();
            System.out.print("판매여부 (y,n) : ");
            sc.nextLine();
            String orderableStatuse = sc.nextLine().toUpperCase();

            pstmt = con.prepareStatement(query);

            pstmt.setString(1,menuName);
            pstmt.setInt(2,menuPrice);
            pstmt.setInt(3,categoryCode);
            pstmt.setString(4,orderableStatuse);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }
        if (result > 0) {
            System.out.println("수정 성공");
        } else {
            System.out.println("수정 실패");
        }
    }
}
