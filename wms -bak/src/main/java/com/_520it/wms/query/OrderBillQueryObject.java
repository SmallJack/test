package com._520it.wms.query;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderBillQueryObject extends QueryObject {
	private Long supplierId = -1l;
	private Date beginDate;
	private Date endDate;
	private Integer status = -1;

}
