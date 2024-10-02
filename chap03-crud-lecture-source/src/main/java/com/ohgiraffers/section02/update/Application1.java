package com.ohgiraffers.section02.update;

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

public class Application1 { // 24-10-02 (수) 5교시 데이터베이스 모델링 (update)

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 메뉴 번호를 입력해 주세요 : ");
        int menuCode = sc.nextInt();
        System.out.print("바꾸고 싶은 메뉴의 이름을 입력해 주세요 : ");
        sc.nextLine();
        String menuName = sc.nextLine();
        System.out.print("바꾸고 싶은 메뉴의 가격을 입력해 주세요 : ");
        int menuPrice = sc.nextInt();

        MenuDTO changeMenu = new MenuDTO();
        changeMenu.setMenuCode(menuCode);
        changeMenu.setMenuName(menuName);
        changeMenu.setMenuPrice(menuPrice);

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("updateMenu"); // key 값 생성

            pstmt = con.prepareStatement(query); // 쿼리문을 실행(가져오겠)하겠다.

            pstmt.setString(1,changeMenu.getMenuName()); // 채워주기
            pstmt.setInt(2,changeMenu.getMenuPrice());
            pstmt.setInt(3,changeMenu.getMenuCode());

            result = pstmt.executeUpdate(); // 실행 이후 결과값을 담겠다.

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }
        if (result > 0) {
            System.out.println(changeMenu.getMenuName() + "번 메뉴 변경 성공");
        } else {
            System.out.println("변경 실패!");
        }
    }
}
