package com._520it.wms.mapper;

import java.util.List;

import com._520it.wms.domain.Permission;
import com._520it.wms.query.QueryObject;

public interface PermissionMapper {

	void save(Permission p);

	void delete(Long id);

	List<Permission> list();

	List<Permission> getListData(QueryObject qo);

	long getTotalCount(QueryObject qo);
	/**
	 * 根据角色编号查找权限信息
	 * @param roleId 角色编号
	 * @return
	 */
	List<Permission> getByRoleId(Long roleId);

	
}
