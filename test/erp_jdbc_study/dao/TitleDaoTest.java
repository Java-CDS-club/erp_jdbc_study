package erp_jdbc_study.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_jdbc_study.dao.impl.TitleDaoImpl;
import erp_jdbc_study.dto.Title;
import erp_jdbc_study.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	private TitleDao dao;
	
	@Before
	public void setUp() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = TitleDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = null;
	} 
	
	@Test
	public void test1SelectTitleByNo() {
		Title t = dao.selectTitleByNo(new Title(1));
		Assert.assertNotNull(t);
		LogUtil.prnLog(t);
	}

	@Test
	public void test2SelectTitleByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Title> list = dao.selectTitleByAll();
		Assert.assertNotNull(list);
		
		for(Title t : list) LogUtil.prnLog(t);
	}

	@Test
	public void test3InsertTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title newTitle = new Title(6, "인턴");
		int res = dao.insertTitle(newTitle);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test4UpdateTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title newTitle = new Title(6, "계약직");
		int res = dao.updateTitle(newTitle);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test5DeleteTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title newTitle = new Title(6);
		int res = dao.deleteTitle(newTitle);
		Assert.assertEquals(1, res);
	}

}
