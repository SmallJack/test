package com._520it.wms.service;

import java.util.List;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockOutcomeBillQueryObject;

public interface IStockOutcomeBillService {
	void delete(Long id);

	void save(StockOutcomeBill entity);

	StockOutcomeBill get(Long id);

	List<StockOutcomeBill> list();

	void update(StockOutcomeBill entity);

	PageResult pageQuery(StockOutcomeBillQueryObject qo);

	void audit(Long id);
}
