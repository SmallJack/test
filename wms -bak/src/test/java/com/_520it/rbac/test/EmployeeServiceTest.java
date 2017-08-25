package com._520it.rbac.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.Employee;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceTest {

	@Autowired
	private IEmployeeService service;

	@Test
	public void testSave() {
		Department dept = new Department();
		dept.setId(20L);
		Employee e = new Employee();
		e.setPassword("1234");
		e.setAge(17);
		e.setAdmin(true);
		e.setDept(dept);
		e.setEmail("admin@qq.com");
		for (int i = 0; i < 100; i++) {
			e.setName("admin" + (i + 1));
			service.save(e);
		}
	}

	@Test
	public void testDelete() {
		service.delete(1L);
	}

	@Test
	public void testUpdate() {
		Department dept = new Department();
		dept.setId(4L);
		Employee e = new Employee();
		e.setId(1L);
		e.setName("will");
		e.setDept(dept);
		service.update(e);
	}

	@Test
	public void testGet() {
		Employee dept = service.get(1L);
		System.out.println(dept);
	}

	@Test
	public void testList() {
		List<Employee> list = service.list();
		for (Employee dept : list) {
			System.out.println(dept);
		}
	}

	@Test
	public void testPageQuery() throws Exception {
		QueryObject qo = new QueryObject();
		qo.setCurrentPage(1);
		qo.setPageSize(5);
		PageResult result = service.pageQuery(qo);
		System.out.println(result);
	}
}