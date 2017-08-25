package com._520it.wms.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com._520it.wms.page.PageResult;
import com._520it.wms.domain.Depot;
import com._520it.wms.domain.Product;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.mapper.StockIncomeBillItemMapper;
import com._520it.wms.mapper.StockIncomeBillMapper;
import com._520it.wms.query.StockIncomeBillQueryObject;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.util.UserContext;

import lombok.Setter;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
	@Setter
	private StockIncomeBillMapper mapper;

	@Setter
	private ProductStockMapper productStockMapper;

	@Setter
	private StockIncomeBillItemMapper stockIncomeBillItemMapper;

	public void delete(Long id) {
		mapper.delete(id);
	}

	public void save(StockIncomeBill bill) {
		bill.setInputTime(new Date());
		bill.setInputUser(UserContext.getCurrentUser());
		List<StockIncomeBillItem> items = bill.getItems();
		BigDecimal totalNumber = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;

		for (StockIncomeBillItem stockIncomeBillItem : items) {
			BigDecimal amount = stockIncomeBillItem.getNumber().multiply(stockIncomeBillItem.getCostPrice()).setScale(2,
					RoundingMode.HALF_UP);
			stockIncomeBillItem.setAmount(amount);
			totalNumber = totalNumber.add(stockIncomeBillItem.getNumber());
			totalAmount = totalAmount.add(amount);
		}

		bill.setTotalAmount(totalAmount);
		bill.setTotalNumber(totalNumber);

		mapper.save(bill);

		for (StockIncomeBillItem stockIncomeBillItem : items) {
			stockIncomeBillItem.setBill(bill);
			stockIncomeBillItemMapper.save(stockIncomeBillItem);
		}
	}

	public StockIncomeBill get(Long id) {
		return mapper.get(id);
	}

	public List<StockIncomeBill> list() {
		return mapper.list();
	}

	public void update(StockIncomeBill bill) {
		StockIncomeBill stockIncomeBill = mapper.get(bill.getId());

		System.out.println(stockIncomeBill);

		if (stockIncomeBill.getStatus() == 0) {
			stockIncomeBillItemMapper.deleteAllByStockIncomeBillId(bill.getId());
			BigDecimal totalNumber = BigDecimal.ZERO;
			BigDecimal totalAmount = BigDecimal.ZERO;
			List<StockIncomeBillItem> items = bill.getItems();
			for (StockIncomeBillItem stockIncomeBillItem : items) {
				BigDecimal amount = stockIncomeBillItem.getNumber().multiply(stockIncomeBillItem.getCostPrice())
						.setScale(2, RoundingMode.HALF_UP);
				totalNumber = totalNumber.add(stockIncomeBillItem.getNumber());
				totalAmount = totalAmount.add(amount);
				stockIncomeBillItem.setBill(bill);
				stockIncomeBillItemMapper.save(stockIncomeBillItem);
			}
			bill.setTotalAmount(totalAmount);
			bill.setTotalNumber(totalNumber);
			mapper.update(bill);
		}
	}

	@Override
	public PageResult pageQuery(StockIncomeBillQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if (count <= 0) {
			return PageResult.emptyResult;
		}
		List<StockIncomeBill> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), listData);
		return pageResult;
	}

	@Override
	public void audit(Long billId) {
		StockIncomeBill stockIncomeBill = mapper.get(billId);
		stockIncomeBill.setAuditor(UserContext.getCurrentUser());
		stockIncomeBill.setAuditTime(new Date());
		stockIncomeBill.setStatus(1);
		mapper.updateStatus(stockIncomeBill);
		Long depotId = stockIncomeBill.getDepot().getId();
		// ------------------------
		List<StockIncomeBillItem> items = stockIncomeBill.getItems();
		for (StockIncomeBillItem stockIncomeBillItem : items) {
			Long productId = stockIncomeBillItem.getProduct().getId();
			// 根据库存ID和仓库ID查询库存信息
			ProductStock ps = productStockMapper.selectByDeoptIdAndProductId(depotId, productId);
			System.out.println(ps);
			if (ps == null) {
				// 如果库存不存在 则直接保存在到库存
				ps = new ProductStock();
				Product p = new Product();
				p.setId(productId);
				ps.setProduct(p);
				Depot d = new Depot();
				d.setId(depotId);
				ps.setDepot(d);
				ps.setPrice(stockIncomeBillItem.getCostPrice());
				ps.setStoreNumber(stockIncomeBillItem.getNumber());
				ps.setAmount(stockIncomeBillItem.getAmount());
				ps.setIncomeDate(new Date());
				productStockMapper.save(ps);
			} else {
				// 6.如果在库存存,则直接修改库存信息
				ps.setAmount(ps.getAmount().add(stockIncomeBillItem.getAmount()));
				ps.setStoreNumber(ps.getStoreNumber().add(stockIncomeBillItem.getNumber()));
				ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(), 2, RoundingMode.HALF_UP));
				productStockMapper.update(ps);
			}
		}

	}
}
