package com._520it.wms.web.action;

import java.util.List;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.Employee;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IRoleService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import lombok.Getter;
import lombok.Setter;

public class EmployeeAction extends BaseAction {

	@Setter
	private IEmployeeService service;
	@Setter
	private IDepartmentService deptService;
	@Setter
	private IRoleService roleService;
	@Getter
	private Employee e = new Employee();
	@Getter
	private QueryObject qo = new EmployeeQueryObject();
	@Getter
	@Setter
	private Long[] ids;

	@RequiredPermission("员工列表")
	@InputConfig(methodName = "input")
	public String execute() {
		try {
			//将所有的部门信息查询到,显示在编辑页面中
			List<Department> depts = deptService.list();
			put("depts", depts);
			PageResult result = service.pageQuery(qo);
			put("result", result);
			//System.out.println(1 / 0);
		} catch (Exception e) {
			e.printStackTrace();
			//将错误信息添加到值栈中,回到页面上将其取出
			addActionError("查询失败,请联系管理员!");
		}

		return LIST;
	}

	@RequiredPermission("删除员工")
	public String delete() throws Exception {
		try {
			service.delete(e.getId());
			putMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}

		return NONE;
	}

	@RequiredPermission("编辑员工")
	public String input() {
		//查询到所有的角色
		List<Role> roles = roleService.list();
		put("roles", roles);
		//将所有的部门信息查询到,显示在编辑页面中
		List<Department> depts = deptService.list();
		put("depts", depts);
		if (e != null && e.getId() != null) {
			e = service.get(e.getId());
		}
		return INPUT;
	}

	@RequiredPermission("保存或者更新员工")
	public String saveOrUpdate() {
		try {
			if (e != null && e.getId() != null) {
				service.update(e);
				addActionMessage("更新成功");
			} else {
				service.save(e);
				addActionMessage("保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或者更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("员工的批量删除")
	public String batchDelete() throws Exception {
		try {
			service.batchDelete(ids);
			putMsg("批量删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("批量删除失败");
		}
		return NONE;
	}
}
