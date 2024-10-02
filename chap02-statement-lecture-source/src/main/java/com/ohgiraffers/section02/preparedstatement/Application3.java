package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 { // 24-10-02 (수) 2교시 rset.getString 시 클래스로 묶어서 사용하기

    public static void main(String[] args) {

        Connection con = getConnection(); // 연결 통로

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        EmployeeDTO emp = null; // 한명의 회원 정보를 관리할 DTO 사용

        Scanner sc = new Scanner(System.in);

        System.out.print("조회하실 사번을 입력해 주세요 : ");
        String empId = sc.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,empId); // 세팅하기
            rset = pstmt.executeQuery(); // 실행해준 쿼리문을 rset에 담아주기.

            if (rset.next()) { // 결과가 존재한다면
                emp = new EmployeeDTO(); // DTO 객체 생성

                emp.setEmpId(rset.getString("EMP_ID")); // rset 결과값을 emp DTO에 담아준다.
                emp.setEmpName(rset.getString("EMP_NAME"));
                emp.setEmpNo(rset.getString("EMP_NO"));
                emp.setEmail(rset.getString("EMAIL"));
                emp.setPhone(rset.getString("PHONE"));
                emp.setDeptCode(rset.getString("DEPT_CODE"));
                emp.setJobCode(rset.getString("JOB_CODE"));
                emp.setSalLevel(rset.getString("SAL_LEVEL"));
                emp.setSalary(rset.getInt("SALARY"));
                emp.setBonus(rset.getDouble("BONUS"));
                emp.setManagerId(rset.getString("MANAGER_ID"));
                emp.setHireDate(rset.getDate("HIRE_DATE"));
                emp.setEntDate(rset.getDate("ENT_DATE"));
                emp.setEntYn(rset.getString("ENT_YN"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }
        System.out.println("emp = " + emp);
        // EmployeeDTO{empId='201', empName='송종기', empNo='631156-1548654', email='song_jk@greedy.com',
        // phone='01045686656', deptCode='D9', jobCode='J2', salLevel='S1', salary=6000000, bonus=0.0, managerId='200', hireDate=2001-09-01, entYn='N'} 출력
    }
}
