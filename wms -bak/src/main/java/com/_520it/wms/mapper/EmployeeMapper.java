package com._520it.wms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Permission;
import com._520it.wms.query.QueryObject;

public interface EmployeeMapper {

	void save(Employee e);

	void delete(Long id);

	void update(Employee e);

	Employee get(Long id);

	List<Employee> list();

	List<Employee> getListData(QueryObject qo);

	long getTotalCount(QueryObject qo);

	/**
	 * 删除用户和部门的关系
	 * 
	 * @param id
	 */
	void deleteRelation(Long id);

	/**
	 * 保存员工和角色之间的关系:中间表
	 * 
	 * @param id
	 * @param id2
	 */
	void saveRelation(@Param("empId") Long id, @Param("roleId") Long id2);

	/**
	 * 删除用户和角色的关系
	 * 
	 * @param id
	 */
	void deleteRoleRelation(Long id);

	/**
	 * 登录校验
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	Employee checkLogin(@Param("username") String username, @Param("password") String password);

	void batchDelete(Long[] ids);

	List<String> getByEmployeeId(Long employeeId);
}
