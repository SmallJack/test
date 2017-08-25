package com._520it.wms.mapper;

import java.util.List;

import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.domain.StockOutcomeBillItem;

public interface StockOutcomeBillItemMapper {
	void save(StockOutcomeBillItem entity);

	void deleteAllByStockOutcomeBillId(Long stockOutcomeBillId);

	List<StockIncomeBillItem> getItemsByBillId(Long stockOutcomeBillId);
}