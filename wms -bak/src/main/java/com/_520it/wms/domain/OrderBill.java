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
@ObjectProp("采购订单管理")
public class OrderBill extends BaseDomain {
	@ObjectProp("订单编号")
	private String sn;
	@ObjectProp("业务时间")
	private Date vdate;
	@ObjectProp("审核状态")
	private Integer status = 0;
	@ObjectProp("采购总金额")
	private BigDecimal totalAmount;
	@ObjectProp("采购总数量")
	private BigDecimal totalNumber;
	@ObjectProp("审核时间")
	private Date auditTime;
	@ObjectProp("录入时间")
	private Date inputTime;
	@ObjectProp("录人人")
	private Employee inputUser;
	@ObjectProp("审核人")
	private Employee auditor;
	@ObjectProp("供应商")
	private Supplier supplier;
	@ObjectProp("订单明细")

	public static final Integer STATUS_NOMAL = 0;
	public static final Integer STATUS_NO = 1;

	List<OrderBillItem> items = new ArrayList<>();

}
