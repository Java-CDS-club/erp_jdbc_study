package erp_jdbc_study.dto;

import java.util.Date;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private Employee manager;
	private int salary;
	private Department dept;
	private String passwd;
	private byte[] pic;
	private Date hireDate;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept,
			Date hireDate) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
		this.hireDate = hireDate;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept, String passwd, 
			Date hireDate) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
		this.passwd = passwd;
		this.hireDate = hireDate;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public String toString() {
		return String.format("Employee [%s %s %s %s %s %s %s %s %s]", empNo, empName, title.getTitleNo(),
				manager.getEmpNo(), salary, dept.getDeptNo(), passwd, String.format("%1$tF - %1$tT", hireDate),
				pic != null ? pic.length : null);
	}

}
