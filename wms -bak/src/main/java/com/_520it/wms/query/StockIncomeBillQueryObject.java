package com._520it.wms.query;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockIncomeBillQueryObject extends QueryObject {
	private Date beginTime;
	private Date endTime;
	private Long depotId = -1l;
	private Long status = -1l;
}
