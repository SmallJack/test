package com._520it.wms.web.interceptor;

import com._520it.wms.domain.Employee;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckLoginInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//当没有登录的时候,回到登录页面
		Employee object = UserContext.getCurrentUser();
		if (object == null) {
			return Action.LOGIN;
		}
		//放行
		return invocation.invoke();
	}
}
