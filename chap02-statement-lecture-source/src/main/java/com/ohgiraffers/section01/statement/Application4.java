package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 {  // 24-09-30 (월) 5교시, EmployeeDTO 연동 (전체 사원 정보)

    public static void main(String[] args) {

        /* title. 전체 사원 정보를 EmployeeDTO를 통해 객체에 담아, 출력 */
        Connection con = getConnection(); // 1단계

        Statement stmt = null; // 2단계

        ResultSet rset = null; // 3단계
        // 1. 회원 한 명 정보를 담을 DTO
        EmployeeDTO emp = null; // null로 초기화
        // 2. 한명의 정보들을 하나의 인스턴스로 묶기 위한 List
        List<EmployeeDTO> empList = null;

        String query = "SELECT * FROM EMPLOYEE";


        try {

            //
            stmt = con.createStatement();

            rset = stmt.executeQuery(query);

            empList = new ArrayList<>(); // 인스턴스는 반복문 밖에 있어야....

            /* 조회한 결과를 객체에 담기 */
            while (rset.next()) { // rset에 행이 존재한다면
                emp = new EmployeeDTO();

                emp.setEmpId(rset.getString("EMP_ID"));
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

                empList.add(emp); // 자료가 없어질때까지 담고 읽히고 반복
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }
        for (EmployeeDTO oneEmployee : empList) {
            System.out.println("oneEmployee = " + oneEmployee);
        }

    }
}
