package erp_jdbc_study.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import erp_jdbc_study.ds.MySqlDataSource;
import erp_jdbc_study.dto.Department;
import erp_jdbc_study.dto.Employee;
import erp_jdbc_study.util.LogUtil;

public class TransactionService {

	public int transAddEmpAndDept(Employee emp, Department dept) {
		String deptSql = "insert into department values(?, ?, ?)";
		String empSql = "insert into employee(emp_no, emp_name, title, manager, salary, dept, hire_date) values(?, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		Connection con = null;
		try {
			con = MySqlDataSource.getConnection();
			con.setAutoCommit(false);
			try(PreparedStatement deptPstmt = con.prepareStatement(deptSql);){
				deptPstmt.setInt(1, dept.getDeptNo());
				deptPstmt.setString(2, dept.getDeptName());
				deptPstmt.setInt(3, dept.getFloor());
				LogUtil.prnLog(deptPstmt);
				result = deptPstmt.executeUpdate();
			}
			
			try(PreparedStatement empPstmt = con.prepareStatement(empSql);){
				empPstmt.setInt(1, emp.getEmpNo());
				empPstmt.setString(2, emp.getEmpName());
				empPstmt.setInt(3, emp.getTitle().getTitleNo());
				empPstmt.setInt(4, emp.getManager().getEmpNo());
				empPstmt.setInt(5, emp.getSalary());
				empPstmt.setInt(6, emp.getDept().getDeptNo());
				empPstmt.setTimestamp(7, new Timestamp(emp.getHireDate().getTime()));
				LogUtil.prnLog(empPstmt);
				result += empPstmt.executeUpdate();
			}
			
			if (result == 2) {
				con.commit();
				con.setAutoCommit(true);
				LogUtil.prnLog("result " + result + " commit()");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
				LogUtil.prnLog("result " + result + " rollback()");
			} catch (SQLException ex) {}
		} finally {
			try { if (con != null) con.close();	} catch (SQLException ex) {}
		}
		return result;
	}

	public int transRemoveEmpAndDept(Employee emp, Department dept) {
		String deleteDeptSQL = "delete from department where dept_no = ?";
		String deleteEmpSQL = "delete from employee where emp_no = ?";
		// 1. 사원삭제
		// 2. 부서삭제(사원이 소속된)
		int res = -1;
		Connection con = null;
		try {
			con = MySqlDataSource.getConnection();
			con.setAutoCommit(false);
			try(PreparedStatement pstmt = con.prepareStatement(deleteEmpSQL);){
				pstmt.setInt(1, emp.getEmpNo());
				LogUtil.prnLog(pstmt);
				res = pstmt.executeUpdate();
			}
			try(PreparedStatement pstmt = con.prepareStatement(deleteDeptSQL);){
				pstmt.setInt(1, dept.getDeptNo());
				LogUtil.prnLog(pstmt);
				res += pstmt.executeUpdate();
			}

			LogUtil.prnLog(res);
			if (res == 2) {
				con.commit();
				LogUtil.prnLog("result " + res + " commit()");
			} else {
				throw new SQLException(res+"");
			}
		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
				LogUtil.prnLog("result " + e.getMessage() + " rollback()");
			} catch (SQLException ex) {}
		}
		return res;
	}
}
