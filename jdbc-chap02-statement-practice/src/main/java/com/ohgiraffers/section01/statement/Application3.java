package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.*;
import java.util.Scanner;

import static com.ohgiraffers.common.JTBCTemplate.close;
import static com.ohgiraffers.common.JTBCTemplate.getConnection;

public class Application3 {

    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        EmployeeDTO emp = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("입력할 사번은 ? : ");
        String empId = sc.nextLine();
        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

        try {
            stmt = con.createStatement();

            rset = stmt.executeQuery(query);

            if (rset.next()) {
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
            close(con);
            close(stmt);
            close(rset);
        }
        System.out.println("emp = " + emp);
    }
}
