package com._520it.wms.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.test.util.JsonExpectationsHelper;

import com._520it.wms.query.ClientQueryObject;
import com._520it.wms.query.OrderBillChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IChartService;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.IProductService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.service.impl.ClientServiceImpl;
import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;

public class ChartAction extends BaseAction {

	@Setter
	private IChartService service;

	@Setter
	private ISupplierService supplierService;

	@Setter
	private IBrandService brandService;

	@Setter
	private IClientService clientService;

	@Getter
	private SaleChartQueryObject sqo = new SaleChartQueryObject();

	@Getter
	private OrderBillChartQueryObject qo = new OrderBillChartQueryObject();

	public String orderChart() {
		put("suppliers", supplierService.list());
		put("brands", brandService.list());
		put("listData", service.queryOrderBillChart(qo));
		put("gruopTypes", OrderBillChartQueryObject.GROUPBYTYPEMAP);
		return "orderChart";
	}

	public String saleChart() {
		put("clients", clientService.list());
		put("brands", brandService.list());
		put("listData", service.querySaleChart(sqo));
		put("gruopTypes", SaleChartQueryObject.GROUPBYTYPEMAP);
		return "saleChart";
	}

	// 销售报表
	public String saleChartByBar() {
		put("clients", clientService.list());
		put("brands", brandService.list());

		List<Map<String, Object>> list = service.querySaleChart(sqo);

		put("listData", list);
		put("gruopTypes", SaleChartQueryObject.GROUPBYTYPEMAP);
		put("gruopType", SaleChartQueryObject.GROUPBYTYPEMAP.get(sqo.getGroupByType()));

		List<Object> groupByTypeList = new ArrayList<>();
		List<Object> totalAmountList = new ArrayList<>();
		for (Map<String, Object> map : list) {
			groupByTypeList.add(map.get("groupByType"));
			totalAmountList.add(map.get("totalAmount"));
		}
		put("gruopTypeList", JSON.toJSONString(groupByTypeList));
		put("totalAmountList", JSON.toJSONString(totalAmountList));

		return "saleChartByBar";
	}

	// 销售报表
	public String saleChartByPie() {
		put("clients", clientService.list());
		put("brands", brandService.list());

		List<Map<String, Object>> list = service.querySaleChart(sqo);

		put("listData", list);
		put("gruopTypes", SaleChartQueryObject.GROUPBYTYPEMAP);

		BigDecimal maxAmount = BigDecimal.ZERO;
		List<Object> groupByTypeList = new ArrayList<>();
		List<Map<String, Object>> data = new ArrayList<>();
		for (Map<String, Object> map : list) {
			groupByTypeList.add(map.get("groupByType"));
			Map<String, Object> dataMap = new HashMap<>();
			data.add(dataMap);

			BigDecimal totalAmount = (BigDecimal) map.get("totalAmount");

		
			dataMap.put("value", map.get("totalAmount"));
			dataMap.put("name", map.get("groupByType"));
			
			if (maxAmount.compareTo(totalAmount) < 0) {
				maxAmount = totalAmount;
			}
		}
		put("dataList", JSON.toJSONString(data));
		put("maxAmount", maxAmount);
		// 当前分组类型
		put("gruopType", SaleChartQueryObject.GROUPBYTYPEMAP.get(sqo.getGroupByType()));

		put("gruopTypeList", JSON.toJSONString(groupByTypeList));

		return "saleChartByPie";
	}
}
