package com._520it.wms.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ObjectProp("销售出库出库")
public class StockOutcomeBill extends BaseDomain {
	@ObjectProp("出库单编号")
	private String sn;
	@ObjectProp("业务时间")
	private Date vdate;
	@ObjectProp("审核状态")
	private Integer status = 0;
	@ObjectProp("出库总金额")
	private BigDecimal totalAmount;
	@ObjectProp("出库总数量")
	private BigDecimal totalNumber;
	@ObjectProp("审核时间")
	private Date auditTime;
	@ObjectProp("录入时间")
	private Date inputTime;
	@ObjectProp("录入人")
	private Employee inputUser;
	@ObjectProp("审核人")
	private Employee auditor;
	@ObjectProp("客户")
	private Client client;
	@ObjectProp("仓库")
	private Depot depot;
	@ObjectProp("出仓明细")
	List<StockOutcomeBillItem> items = new ArrayList<>();

}
