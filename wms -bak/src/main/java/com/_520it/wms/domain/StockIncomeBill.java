package com._520it.wms.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StockIncomeBill extends BaseDomain {
	private String sn;
	private Date vdate;
	private Integer status = 0;
	private BigDecimal totalAmount;
	private BigDecimal totalNumber;
	private Date auditTime;
	private Date inputTime;
	private Employee inputUser;
	private Employee auditor;
	private Depot depot;
	List<StockIncomeBillItem> items = new ArrayList<>();
}
