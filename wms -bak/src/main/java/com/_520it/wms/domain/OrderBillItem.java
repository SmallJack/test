package com._520it.wms.domain;

import java.math.BigDecimal;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ObjectProp("订单明细")
public class OrderBillItem extends BaseDomain {
	private BigDecimal costPrice;
	private BigDecimal number;
	private BigDecimal amount;
	private String remark;
	private Product product;
	private Long billId;
}
