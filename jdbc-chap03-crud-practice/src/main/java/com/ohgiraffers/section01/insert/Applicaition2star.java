package com.ohgiraffers.section01.insert;

import com.ohgiraffers.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Applicaition2star {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("신규 메뉴의 이름을 입력해 주세요 : ");
        String menuName = sc.nextLine();
        System.out.print("신규 메뉴의 가격을 입력해 주세요 : ");
        int menuPrice = sc.nextInt();
        System.out.print("신규 메뉴의 카테고리 코드를 입력해 주세요 : ");
        int categoryCode = sc.nextInt();
        System.out.print("판매 여부를 지정해 주세요 (Y/N) : ");
        sc.nextLine();
        String orderableStatus = sc.nextLine().toUpperCase();

        MenuDTO newMenu = new MenuDTO();
        newMenu.setMenuName(menuName);
        newMenu.setMenuPrice(menuPrice);
        newMenu.setCategoryCode(categoryCode);
        newMenu.setOrderableStatue(orderableStatus);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("insertMenu");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newMenu.getMenuName());
            pstmt.setInt(2, newMenu.getMenuPrice());
            pstmt.setInt(3, newMenu.getCategoryCode());
            pstmt.setString(4, newMenu.getOrderableStatue());

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
