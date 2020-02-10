package erp_jdbc_study.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_jdbc_study.dao.impl.EmployeeDaoImpl;
import erp_jdbc_study.dto.Department;
import erp_jdbc_study.dto.Employee;
import erp_jdbc_study.dto.Title;
import erp_jdbc_study.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	private EmployeeDao dao;
	
	@Before
	public void setUp() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = EmployeeDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = null;
	} 
	
	@Test
	public void test1SelectEmployeeByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Employee> list = dao.selectEmployeeByAll();
		Assert.assertNotEquals(0, list.size());
	}

	@Test
	public void test2InsertEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Calendar c = Calendar.getInstance();
		Date hireDate = new Date(c.getTimeInMillis());
		Employee emp = new Employee(1004, "이유영", new Title(2), new Employee(1003), 2000000, new Department(2), "rootroot", hireDate);
		emp.setPic(getImage("lyy.jpg"));
		LogUtil.prnLog(emp);
		int res = dao.insertEmployee(emp);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test3SelectEmployeeByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = dao.selectEmployeeByNo(new Employee(1004));
		if (emp.getPic() != null) {
			getImageToPic(emp.getPic(), emp.getEmpNo());// 프로젝트 폴더의 pics폴더에 사원번호.jpg파일이 생성
		}
		Assert.assertNotNull(emp);
		LogUtil.prnLog(emp);
		LogUtil.prnLog(String.format("%1$tF - %1$tT %1$tY년 %1$tm월 %1$td일 %1$tH시 %1$tM분 %1$tS초 ", emp.getHireDate()));
		
		
		//Date를 Calendar 로 변환하여 년/월/일 시/분/초 추출하기
		Calendar hire_date = Calendar.getInstance();
		hire_date.setTime(emp.getHireDate());
		
		LogUtil.prnLog(String.format("%d 년", hire_date.get(Calendar.YEAR)));
		LogUtil.prnLog(String.format("%d 월", hire_date.get(Calendar.MONTH) + 1));
		LogUtil.prnLog(String.format("%d 일", hire_date.get(Calendar.DAY_OF_MONTH)));
		LogUtil.prnLog(String.format("%d시 %d분 %d초", hire_date.get(Calendar.HOUR_OF_DAY), hire_date.get(Calendar.MINUTE), hire_date.get(Calendar.SECOND)));
	}
	
	@Test
	public void test4LoginProcess() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = new Employee(1004);
		emp.setPasswd("rootroot");
		Employee loginEmp = dao.loginProcess(emp);
		Assert.assertNotNull(loginEmp);
		LogUtil.prnLog(loginEmp);
	}
	
	@Test
	public void test5UpdateEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = new Employee(1004, "이유영", new Title(3), new Employee(4377), 2500000, new Department(1), "root", new Date());
		int res = dao.updateEmployee(emp);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test6ChangePassword() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = new Employee(1004);
		emp.setPasswd("root");
		int res = dao.updateEmployee(emp);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test7DeleteEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = new Employee(1004);
		int res = dao.deleteEmployee(emp);
		Assert.assertEquals(1, res);
	}
	
	private void getImageToPic(byte[] pic, int empNo) {
		File picsDir = new File(System.getProperty("user.dir") + File.separator + "pics");
		if (!picsDir.exists()) {
			picsDir.mkdir();
		}
		
		File file = new File(picsDir, empNo + ".jpg");
		try (FileOutputStream fis = new FileOutputStream(file)) {
			fis.write(pic);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] getImage(String imgName) {
		byte[] pic = null;
		File file = new File(System.getProperty("user.dir")+File.separator+"images", imgName);
		try(InputStream is = new FileInputStream(file)){
			pic = new byte[is.available()];
			is.read(pic);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}

}
