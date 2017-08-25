package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Permission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

public interface IEmployeeService {
	void save(Employee e);

	void delete(Long id);

	void update(Employee e);

	Employee get(Long id);

	List<Employee> list();

	PageResult pageQuery(QueryObject qo);

	/**
	 * 登陆校验操作
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	Employee checkLogin(String username, String password);

	/**
	 * 批量删除操作
	 * 
	 * @param ids
	 *            要删除数据的id
	 */
	void batchDelete(Long[] ids);

}
