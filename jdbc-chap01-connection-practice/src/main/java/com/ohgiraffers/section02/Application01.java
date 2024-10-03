package com.ohgiraffers.section02;

import java.sql.Connection;

import static com.ohgiraffers.section02.JTBCTemplate.getConnection;

public class Application01 {

    public static void main(String[] args) {

        Connection con = getConnection();
        System.out.println("con = " + con);
        Connection con2 = getConnection();
        System.out.println("con2 = " + con2);

    }
}
