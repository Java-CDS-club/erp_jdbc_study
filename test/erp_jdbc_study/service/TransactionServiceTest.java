package erp_jdbc_study.service;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_jdbc_study.dto.Department;
import erp_jdbc_study.dto.Employee;
import erp_jdbc_study.dto.Title;
import erp_jdbc_study.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionServiceTest {

	private static TransactionService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		service = new TransactionService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		service = null;
	}

	@Test
	public void test01TransAddEmpAndDept_DeptFail() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int res = 0;
		Department dept = new Department(1, "마케팅", 8);// 존재하는 부서
		Employee emp = new Employee(1004, "수지", new Title(5), new Employee(1003), 1500000, dept, new Date());
		res = service.transAddEmpAndDept(emp, dept);
		Assert.assertNotEquals(2, res);
	}

	@Test
	public void test02TransAddEmpAndDept_EmpFail() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int res = 0;
		Department dept = new Department(5, "마케팅", 8);
		Employee emp = new Employee(4377, "수지", new Title(5), new Employee(1003), 1500000, dept, new Date());// 존재하는 사원

		res = service.transAddEmpAndDept(emp, dept);
		Assert.assertNotEquals(2, res);
	}

	@Test
	public void test03TransAddEmpAndDept_Success() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department dept = new Department(5, "마케팅", 8);
		Employee emp = new Employee(1004, "수지", new Title(5), new Employee(1003), 1500000, dept, new Date());

		int res = service.transAddEmpAndDept(emp, dept);
		Assert.assertEquals(2, res);
	}

	@Test
	public void test04TransRemoveEmpAndDept_DeptFail() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		Department dept = new Department(10);// 존재하지않 부서
		Employee emp = new Employee(1004);

		int res = service.transRemoveEmpAndDept(emp, dept);
		Assert.assertNotEquals(2, res);
	}
	
	@Test
	public void test05TransRemoveEmpAndDept_EmployeeFail() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		Department dept = new Department(5);
		Employee emp = new Employee(9000);

		int res = service.transRemoveEmpAndDept(emp, dept);
		Assert.assertNotEquals(2, res);
	}
	
	@Test
	public void test06TransRemoveEmpAndDept_Success() {
		Department dept = new Department(5);
		Employee emp = new Employee(1004);

		int res = service.transRemoveEmpAndDept(emp, dept);
		Assert.assertEquals(2, res);
	}
	
}
