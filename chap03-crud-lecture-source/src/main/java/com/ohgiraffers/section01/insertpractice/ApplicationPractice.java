package com.ohgiraffers.section01.insertpractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class ApplicationPractice {

    public static void main(String[] args) {

        Connection con = getConnection();

        Scanner sc = new Scanner(System.in);
        System.out.print("추가할 메뉴명을 입력하세요 : ");
        String menuName = sc.nextLine();
        System.out.print("추가할 메뉴의 가격을 입력하세요 : ");
        int menuPrice = sc.nextInt();
        System.out.print("카테고리 코드를 입력하세요 : ");
        int categoryCode = sc.nextInt();
        System.out.print("Y / N 입력하세요 : ");
        String orderableStatus = sc.nextLine();

        int result = 0;
        PreparedStatement pstmt = null;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("insertMenu");
            pstmt = con.prepareStatement(query);

            pstmt.setString(1,menuName);
            pstmt.setInt(2,menuPrice);
            pstmt.setInt(3,categoryCode);
            pstmt.setString(4,orderableStatus);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }

        System.out.println("result = " + result);
        if (result > 0) {
            System.out.println("메뉴 등록 성공");
        } else {
            System.out.println("메뉴 등록 실패");
        }
    }
}
