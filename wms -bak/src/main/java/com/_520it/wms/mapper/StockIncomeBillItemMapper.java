package com._520it.wms.mapper;

import java.util.List;

import com._520it.wms.domain.StockIncomeBillItem;

public interface StockIncomeBillItemMapper {
	void save(StockIncomeBillItem entity);

	void deleteAllByStockIncomeBillId(Long stockIncomeBillId);
	
	List<StockIncomeBillItem> getItemsByBillId(Long stockIncomeBillId);
   

	
}