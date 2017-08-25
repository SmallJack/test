package com._520it.wms.util;

import java.util.Set;

import com._520it.wms.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

public class UserContext {
	public class get {

	}
	private UserContext() {
	}
	private static final String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";
	private static final String PERMISSIONS_IN_SESSION = "PERMISSIONS_IN_SESSION";

	//保存登录的用户信息
	public static void setCurrentUser(Employee currentUser) {
		if (currentUser == null) {
			//注销
			ActionContext.getContext().getSession().remove(EMPLOYEE_IN_SESSION);
		} else {
			//登录
			ActionContext.getContext().getSession().put(EMPLOYEE_IN_SESSION, currentUser);
		}
	}
	//保存权限表达式
	public static void setUserPermissions(Set<String> expressionSet) {
		if (expressionSet == null) {
			ActionContext.getContext().getSession().remove(PERMISSIONS_IN_SESSION);
		} else {
			ActionContext.getContext().getSession().put(PERMISSIONS_IN_SESSION, expressionSet);
		}
	}
	//获取当前用户信息
	public static Employee getCurrentUser() {
		return (Employee) ActionContext.getContext().getSession().get(EMPLOYEE_IN_SESSION);
	}
	//获取当前用户拥有的权限表达式
	public static Set<String> getUserPermissions() {
		return (Set<String>) ActionContext.getContext().getSession().get(PERMISSIONS_IN_SESSION);
	}
}
