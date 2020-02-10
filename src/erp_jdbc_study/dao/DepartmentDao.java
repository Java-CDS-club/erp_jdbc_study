package erp_jdbc_study.dao;

import java.util.List;

import erp_jdbc_study.dto.Department;

public interface DepartmentDao {
	Department selectDepartmentByNo(Department dept);
	List<Department> selectDepartmentByAll();
	
	int insertDepartment(Department dept);
	int updateDepartment(Department dept);
	int deleteDepartment(Department dept);
}
