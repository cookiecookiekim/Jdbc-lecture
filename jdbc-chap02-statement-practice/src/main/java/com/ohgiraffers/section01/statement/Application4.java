package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.common.JTBCTemplate.close;
import static com.ohgiraffers.common.JTBCTemplate.getConnection;

public class Application4 {

    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        EmployeeDTO emp = null;

        List<EmployeeDTO> empList = null;

        String query = "SELECT * FROM EMPLOYEE";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            empList = new ArrayList<>();

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

                empList.add(emp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(stmt);
            close(rset);
        }
        for (EmployeeDTO as : empList) {
            System.out.println("as = " + as);
        }
    }
}
