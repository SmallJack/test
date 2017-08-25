package com._520it.wms.service.impl;

import java.util.List;
import java.util.Map;

import com._520it.wms.mapper.ChartMapper;
import com._520it.wms.query.OrderBillChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IChartService;

import lombok.Setter;

public class ChartServiceImpl implements IChartService {

	@Setter
	private ChartMapper mapper;

	public List<Map<String, Object>> queryOrderBillChart(OrderBillChartQueryObject qo) {
		return mapper.queryOrderBillChart(qo);
	}

	@Override
	public List<Map<String, Object>> querySaleChart(SaleChartQueryObject qo) {
		
		return mapper.querySaleChart(qo);
	}

}
