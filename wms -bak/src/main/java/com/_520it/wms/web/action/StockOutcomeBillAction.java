package com._520it.wms.web.action;

import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockOutcomeBillService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com._520it.wms.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class StockOutcomeBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IStockOutcomeBillService service;

	@Setter
	private IDepotService depotService;

	@Setter
	private IClientService clientService;
	@Getter
	private StockOutcomeBillQueryObject qo = new StockOutcomeBillQueryObject();

	@Getter
	private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();

	@RequiredPermission("销售出库出库列表")
	@InputConfig(methodName = "input")
	public String execute() {
		try {
			put("depots", depotService.list());
			put("clients", clientService.list());
			put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("销售出库出库编辑")
	public String input() {
		try {
			put("depots", depotService.list());
			put("clients", clientService.list());
			if (stockOutcomeBill.getId() != null) {
				stockOutcomeBill = service.get(stockOutcomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	public String audit() throws Exception {
		try {
			if (stockOutcomeBill.getId() != null) {
				service.audit(stockOutcomeBill.getId());
				putMsg("审核成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

	@RequiredPermission("销售出库出库保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockOutcomeBill.getId() == null) {
				service.save(stockOutcomeBill);
				addActionMessage("保存成功");
			} else {
				service.update(stockOutcomeBill);
				addActionMessage("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("销售出库出库删除")
	public String delete() throws Exception {
		try {
			if (stockOutcomeBill.getId() != null) {
				service.delete(stockOutcomeBill.getId());
				putMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	public String show() throws Exception {
		try {
			if (stockOutcomeBill.getId() != null) {
				stockOutcomeBill = service.get(stockOutcomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查看失败");
		}
		return "show";
	}

}
