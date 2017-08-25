package com._520it.rbac.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com._520it.wms.domain.Department;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceTest {

	@Autowired
	private IDepartmentService service;

	@Test
	public void testSave() {
		Department dept = new Department();
		dept.setName("研发部");
		dept.setSn("DEPT001");
		service.save(dept);
	}

	@Test
	public void testDelete() {
		service.delete(1L);
	}

	@Test
	public void testUpdate() {
		Department dept = new Department();
		dept.setId(1L);
		dept.setName("销售部");
		dept.setSn("DEPT002");
		service.update(dept);
	}

	@Test
	public void testGet() {
		Department dept = service.get(1L);
		System.out.println(dept);
	}

	@Test
	public void testList() {
		List<Department> list = service.list();
		for (Department dept : list) {
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