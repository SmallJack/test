package com._520it.wms.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductStock extends BaseDomain {
	private BigDecimal price;
	private BigDecimal storeNumber;
	private BigDecimal amount;
	private Date incomeDate;
	private Date outcomeDate;
	private Product product;
	private Depot depot;

}
