package com._520it.wms.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StockIncomeBillItem extends BaseDomain {
	private BigDecimal costPrice;
	private BigDecimal number;
	private BigDecimal amount;
	private String remark;
	private Product product;
	private StockIncomeBill bill;
}
