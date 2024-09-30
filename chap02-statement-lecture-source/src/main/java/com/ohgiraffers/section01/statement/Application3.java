package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 { // 24-09-30 (월) 5교시, EmployeeDTO 연동 (1명의 정보 조회)

    public static void main(String[] args) {

        /* title. Scanner 사용해서 사번 입력받고, 해당 사번의 사원 정보를 EmployeeDTO를 통해 객체에 담아, 출력 */
        Connection con = getConnection(); // 1단계

        Statement stmt = null; // 2단계

        ResultSet rset = null; // 3단계
        // 1. 회원 한 명 정보를 담을 DTO
        EmployeeDTO emp = null; // null로 초기화

        Scanner sc = new Scanner(System.in);
        System.out.print("조회하실 사번을 입력해 주세요 : ");
        String empId = sc.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

        try {
            stmt = con.createStatement();

            rset = stmt.executeQuery(query);

            /* 조회한 결과를 객체에 담기 */
            if (rset.next()) { // rset에 행이 존재한다면
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
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);

        }
        System.out.println("emp = " + emp);

    }

}
