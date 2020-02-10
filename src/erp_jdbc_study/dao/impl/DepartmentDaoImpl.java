package erp_jdbc_study.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_jdbc_study.dao.DepartmentDao;
import erp_jdbc_study.ds.MySqlDataSource;
import erp_jdbc_study.dto.Department;
import erp_jdbc_study.util.LogUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();
	
	private DepartmentDaoImpl() {}

	public static DepartmentDaoImpl getInstance() {
		return instance;
	}

	@Override
	public Department selectDepartmentByNo(Department dept) {
		String sql = "select dept_no, dept_name, floor from department where dept_no=?";
		try (Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, dept.getDeptNo());
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					 return getDepartment(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Department> selectDepartmentByAll() {
		String sql = "select dept_no, dept_name, floor from department";
		try (Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			if (rs.next()) {
				List<Department> list = new ArrayList<>();
				do {
					list.add(getDepartment(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("dept_no");
		String deptName = rs.getString("dept_name");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public int insertDepartment(Department dept) {
		String sql = "insert into department values(?, ?, ?)";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, dept.getDeptNo());
			pstmt.setString(2, dept.getDeptName());
			pstmt.setInt(3, dept.getFloor());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateDepartment(Department dept) {
		String sql = "update department set dept_name=?, floor=? where dept_no = ?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, dept.getDeptName());
			pstmt.setInt(2, dept.getFloor());
			pstmt.setInt(3, dept.getDeptNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteDepartment(Department dept) {
		String sql = "delete from department where dept_no = ?";
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, dept.getDeptNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
