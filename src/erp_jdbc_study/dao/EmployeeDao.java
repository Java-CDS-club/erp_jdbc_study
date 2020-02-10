package erp_jdbc_study.dao;

import java.util.List;

import erp_jdbc_study.dto.Employee;

public interface EmployeeDao {
	Employee selectEmployeeByNo(Employee emp);
	List<Employee> selectEmployeeByAll();
	
	int insertEmployee(Employee emp);
	int updateEmployee(Employee emp);
	int deleteEmployee(Employee emp);
	
	Employee loginProcess(Employee emp);
}
