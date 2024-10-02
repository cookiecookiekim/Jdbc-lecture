package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 { // 24-10-02 (수) 2교시 rset.getString 시 클래스로 묶어서 사용하기

    public static void main(String[] args) { // List , Arraylist에 넣어서....

        /* index. 1. EMPLOYEE 테이블에서 조회할 사원의 성씨를 입력받아 해당하는 성을 가지고
        *   있는 사원 정보 모두 출력 - ex) '김' 입력하면 김씨 다 출력 */

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        EmployeeDTO emp = null;

        List<EmployeeDTO> arr = null;
        Scanner sc = new Scanner(System.in);
        String query = "SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?,'%')";

        System.out.print("성을 입력하세요 : ");
        String result = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,result); // 참고 : result 뒤에 % 넣어도 됨.
            rset = pstmt.executeQuery();
            arr = new ArrayList<>();

            while (rset.next()) {
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

                arr.add(emp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        for (EmployeeDTO dto : arr) {
            System.out.println(dto);
        }
    }
}
