package com._520it.wms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.mapper.OrderBillItemMapper;
import com._520it.wms.mapper.OrderBillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.util.UserContext;

import lombok.Setter;

public class OrderBillServiceImpl implements IOrderBillService {
	@Setter
	private OrderBillMapper mapper;

	@Setter
	private OrderBillItemMapper orderBillItemMapper;

	public void delete(Long id) {
		mapper.deleteAllOrderItemByBillId(id);
		mapper.delete(id);
	}

	public void save(OrderBill orderBill) {
		orderBill.setInputUser(UserContext.getCurrentUser());
		orderBill.setInputTime(new Date());
		List<OrderBillItem> items = orderBill.getItems();

		System.out.println(items);

		BigDecimal totalNumber = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (OrderBillItem item : items) {
			BigDecimal amount = item.getCostPrice().multiply(item.getNumber());
			item.setAmount(amount);                                                                                                                                                                                                           
			totalNumber = totalNumber.add(item.getNumber());
			totalAmount = totalAmount.add(item.getAmount());
		}
		orderBill.setTotalAmount(totalAmount);
		orderBill.setTotalNumber(totalNumber);

		mapper.save(orderBill);

		for (OrderBillItem item : items) {
			item.setBillId(orderBill.getId());
			orderBillItemMapper.save(item);
		}
	}

	public OrderBill get(Long id) {
		return mapper.get(id);
	}

	public void update(OrderBill orderBill) {

		OrderBill old = mapper.get(orderBill.getId());
		if (old.getStatus() == 0) {
			orderBillItemMapper.deleteByBillId(old.getId());
			BigDecimal totalNumber = BigDecimal.ZERO;
			BigDecimal totalAmount = BigDecimal.ZERO;

			for (OrderBillItem item : orderBill.getItems()) {
				System.out.println(item);
				BigDecimal amount = item.getCostPrice().multiply(item.getNumber());
				item.setAmount(amount);
				totalNumber = totalNumber.add(item.getNumber());
				totalAmount = totalAmount.add(item.getAmount());
				item.setBillId(orderBill.getId());
				orderBillItemMapper.save(item);
			}
			orderBill.setTotalAmount(totalAmount);
			orderBill.setTotalNumber(totalNumber);

			mapper.update(orderBill);
		}

	}

	@Override
	public PageResult pageQuery(OrderBillQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if (count <= 0) {
			return PageResult.emptyResult;
		}
		List<OrderBill> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), listData);
		return pageResult;
	}

	@Override
	public void audit(Long id) {
		OrderBill orderBill = mapper.get(id);
		orderBill.setAuditor(UserContext.getCurrentUser());
		orderBill.setAuditTime(new Date());
		orderBill.setStatus(1);
		mapper.updateStatus(orderBill);
	}
}
