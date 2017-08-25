package com._520it.wms.query;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderBillChartQueryObject {
	private Date beginDate;
	private Date endDate;
	private Long supplierId = -1l;
	private Long brandId = -1L;
	private String keyword;
	private String groupByType = "iu.name";

	public static final Map<String, String> GROUPBYTYPEMAP = new LinkedHashMap<>();

	static {
		GROUPBYTYPEMAP.put("iu.name", "订货人员");
		GROUPBYTYPEMAP.put("p.name", "货品名称");
		GROUPBYTYPEMAP.put("s.name", "供应商");
		GROUPBYTYPEMAP.put("b.name", "货品品牌");
		GROUPBYTYPEMAP.put("date_format(bill.vdate,'%y-%m')", "订货日期(月)");
		GROUPBYTYPEMAP.put("date_format(bill.vdate,'%y-%m-%d')", "订货日期(日)");
	}
}
