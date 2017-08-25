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
public class SaleChartQueryObject {
	private Date beginDate;
	private Date endDate;
	private Long clientId = -1l;
	private Long brandId = -1L;
	private String keyword;
	private String groupByType = "sm.name";

	public static final Map<String, String> GROUPBYTYPEMAP = new LinkedHashMap<>();

	static {
		GROUPBYTYPEMAP.put("sm.name", "销售人员");
		GROUPBYTYPEMAP.put("p.name", "货品名称");
		GROUPBYTYPEMAP.put("c.name", "客户名称");
		GROUPBYTYPEMAP.put("b.name", "货品品牌");
		GROUPBYTYPEMAP.put("date_format(sa.vdate,'%y-%m')", "销售日期(月)");
		GROUPBYTYPEMAP.put("date_format(sa.vdate,'%y-%m-%d')", "销售日期(日)");
	}

}
