package com._520it.wms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com._520it.wms.domain.Role;
import com._520it.wms.query.QueryObject;

public interface RoleMapper {

	void save(Role r);

	void delete(Long id);

	void update(Role r);

	Role get(Long id);

	List<Role> list();

	List<Role> getListData(QueryObject qo);

	long getTotalCount(QueryObject qo);

	/**
	 * 保存角色和权限的关系数据
	 * @param id  角色编号
	 * @param id2 权限编号
	 */
	void saveRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

	/**
	 * 删除中间表中的数据
	 * @param id
	 */
	void deleteRelation(Long id);

	/**
	 * 保存角色和菜单的关系
	 * @param id
	 * @param id2
	 */
	void saveRoleMenuRelation(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

	/**
	 * 删除角色和菜单的关系数据
	 * @param id
	 */
	void deleteRoleMenuRelation(Long id);
}
