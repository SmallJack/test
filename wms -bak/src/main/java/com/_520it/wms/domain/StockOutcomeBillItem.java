package com._520it.wms.domain;

import java.math.BigDecimal;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ObjectProp("出库明细")
public class StockOutcomeBillItem  extends BaseDomain  {
	@ObjectProp("价格")
	private BigDecimal salePrice;
	@ObjectProp("数量")
	private BigDecimal number;
	@ObjectProp("金额小计")
	private BigDecimal amount;
	@ObjectProp("备注")
	private String remark;
	@ObjectProp("货品")
	private Product product;
	@ObjectProp("出货订单")
	private StockOutcomeBill bill;

}
