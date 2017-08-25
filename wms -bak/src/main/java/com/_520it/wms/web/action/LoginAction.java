package com._520it.wms.web.action;

import com._520it.wms.domain.Employee;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.util.UserContext;

import lombok.Setter;

public class LoginAction extends BaseAction {

	@Setter
	private String username;
	@Setter
	private String password;
	@Setter
	private IEmployeeService service;

	@Override
	public String execute() throws Exception {
		Employee e = service.checkLogin(username, password);
		if (e == null) {
			//添加错误信息
			addActionError("亲,您的账号或者密码错误");
			return LOGIN;
		} else {
			//跳转到主页面
			//将登陆用户添加到Session中
			UserContext.setCurrentUser(e);
			return "main";
		}
	}
}
