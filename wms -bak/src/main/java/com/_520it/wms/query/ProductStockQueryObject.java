package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductStockQueryObject extends QueryObject {
	private Long depotId = -1L;
	private Long brandId = -1L;
	private String keyword;

}
