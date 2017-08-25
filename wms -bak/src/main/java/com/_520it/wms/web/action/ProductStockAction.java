package com._520it.wms.web.action;

import java.util.List;

import com._520it.wms.domain.Brand;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IProductService;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class ProductStockAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IProductStockService service;

	@Setter
	private IBrandService brandService;

	@Setter
	private IDepotService depotService;

	@Getter
	private ProductStockQueryObject qo = new ProductStockQueryObject();

	@Getter
	private ProductStock productStock = new ProductStock();

	@RequiredPermission("productStock列表")
	public String execute() {
		try {
			put("depots", depotService.list());
			put("brands", brandService.list());
			put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}
}
