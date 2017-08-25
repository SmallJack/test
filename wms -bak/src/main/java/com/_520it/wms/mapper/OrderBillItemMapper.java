package com._520it.wms.mapper;

import java.util.List;

import com._520it.wms.domain.OrderBillItem;

public interface OrderBillItemMapper {
	void save(OrderBillItem entity);

	List<OrderBillItem> getItemsByBillId(Long billId);

	void deleteByBillId(Long id);

}