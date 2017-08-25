package com._520it.wms.service;

import java.util.List;
import java.util.Map;

import com._520it.wms.query.OrderBillChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;

public interface IChartService {
   List<Map<String,Object>> queryOrderBillChart(OrderBillChartQueryObject qo);
   List<Map<String,Object>> querySaleChart(SaleChartQueryObject qo);
}
