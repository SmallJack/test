package com._520it.wms.service.impl;

import java.util.List;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.mapper.RoleMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IRoleService;

import lombok.Setter;

public class RoleServiceImpl implements IRoleService {

	@Setter
	private RoleMapper mapper;

	@Override
	public void save(Role r) {
		mapper.save(r);
		List<Permission> permissions = r.getPermissions();
		//保存角色和权限的关联数据:中间表中
		for (Permission permission : permissions) {
			mapper.saveRelation(r.getId(), permission.getId());
		}
		List<SystemMenu> menus = r.getMenus();
		//保存角色和权限的关联数据:中间表中
		for (SystemMenu menu : menus) {
			mapper.saveRoleMenuRelation(r.getId(), menu.getId());
		}
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public void update(Role r) {
		//更新角色中的数据(name,sn)
		mapper.update(r);
		//更新角色和权限的关系
		//先将拥有的权限删除
		mapper.deleteRelation(r.getId());
		//再将提交过来的权限保存中中间表中
		List<Permission> permissions = r.getPermissions();
		//保存角色和权限的关联数据:中间表中
		for (Permission permission : permissions) {
			mapper.saveRelation(r.getId(), permission.getId());
		}
		//更新角色和菜单的关系
		//先将拥有的菜单删除
		mapper.deleteRoleMenuRelation(r.getId());
		List<SystemMenu> menus = r.getMenus();
		//保存角色和权限的关联数据:中间表中
		for (SystemMenu menu : menus) {
			mapper.saveRoleMenuRelation(r.getId(), menu.getId());
		}
	}

	@Override
	public Role get(Long id) {
		return mapper.get(id);
	}

	@Override
	public List<Role> list() {
		return mapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = mapper.getTotalCount(qo);
		if (totalCount == 0) {
			return PageResult.emptyResult;
		}
		List<Role> listData = mapper.getListData(qo);

		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount.intValue(), listData);
	}

}
