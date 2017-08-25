package com._520it.wms.web.action;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.OrderBill;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.service.impl.SupplierServiceImpl;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com._520it.wms.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class OrderBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IOrderBillService service;

	@Setter
	private ISupplierService supplierService;

	@Getter
	private OrderBillQueryObject qo = new OrderBillQueryObject();

	@Getter
	private OrderBill orderBill = new OrderBill();

	@InputConfig(methodName = "input")
	@RequiredPermission("采购订单管理列表")
	public String execute() {
		try {
			put("suppliers", supplierService.list());
			put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("采购订单管理编辑")
	public String input() {
		try {
			put("suppliers", supplierService.list());
			if (orderBill.getId() != null) {
				orderBill = service.get(orderBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("采购订单管理保存/更新")
	public String saveOrUpdate() {
		try {
			if (orderBill.getId() == null) {
				service.save(orderBill);
				addActionMessage("保存成功");
			} else {
				service.update(orderBill);
				addActionMessage("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("采购订单管理删除")
	public String delete() throws Exception {
		try {
			if (orderBill.getId() != null) {
				service.delete(orderBill.getId());
				putMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	@RequiredPermission("采购订单管理审核")
	public String audit() throws Exception {
		try {
			if (orderBill.getId() != null) {
				Employee currentUser = UserContext.getCurrentUser();
				orderBill.setAuditor(currentUser);
				service.audit(orderBill.getId());
				putMsg("审核成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("审核失败");
		}
		return NONE;
	}

	public String show() throws Exception {
		orderBill = service.get(orderBill.getId());
		return "show";
	}

}
