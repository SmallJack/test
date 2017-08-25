package com._520it.wms.web.action;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.query.StockIncomeBillQueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockIncomeBillService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com._520it.wms.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockIncomeBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IStockIncomeBillService service;
	private IDepotService depotService;
	private StockIncomeBillQueryObject qo = new StockIncomeBillQueryObject();
	private StockIncomeBill stockIncomeBill = new StockIncomeBill();

	@RequiredPermission("stockIncomeBill列表")

	@InputConfig(methodName = "input")
	public String execute() {
		try {
			put("depots", depotService.list());
			put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("stockIncomeBill编辑")
	public String input() {
		try {
			put("depots", depotService.list());
			if (stockIncomeBill.getId() != null) {
				stockIncomeBill = service.get(stockIncomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("stockIncomeBill保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockIncomeBill.getId() == null) {
				service.save(stockIncomeBill);
				addActionMessage("保存成功");
			} else {

				service.update(stockIncomeBill);
				addActionMessage("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("stockIncomeBill删除")
	public String delete() throws Exception {
		try {
			if (stockIncomeBill.getId() != null) {
				service.delete(stockIncomeBill.getId());
				putMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	public String audit() throws Exception {
		try {
			if (stockIncomeBill.getId() != null) {
				service.audit(stockIncomeBill.getId());
				putMsg("审核成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	public String show() throws Exception {
		try {
			if (stockIncomeBill.getId() != null) {
				stockIncomeBill = service.get(stockIncomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查看失败");
		}
		return "show";
	}

}
