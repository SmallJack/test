package com._520it.wms.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.SaleAccount;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.mapper.SaleAccountMapper;
import com._520it.wms.mapper.StockOutcomeBillItemMapper;
import com._520it.wms.mapper.StockOutcomeBillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import com._520it.wms.service.IStockOutcomeBillService;
import com._520it.wms.util.UserContext;

import lombok.Setter;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
	@Setter
	private StockOutcomeBillMapper mapper;

	@Setter
	private ProductStockMapper productStockMapper;

	@Setter
	private StockOutcomeBillItemMapper stockOutcomeBillItemMapper;
	
	@Setter
	private SaleAccountMapper saleAccountMapper;

	public void delete(Long id) {
		stockOutcomeBillItemMapper.deleteAllByStockOutcomeBillId(id);
		mapper.delete(id);
	}

	public void save(StockOutcomeBill bill) {
		bill.setInputTime(new Date());
		bill.setInputUser(UserContext.getCurrentUser());
		List<StockOutcomeBillItem> items = bill.getItems();

		BigDecimal totalNumber = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;

		for (StockOutcomeBillItem StockOutcomeBillItem : items) {
			BigDecimal amount = StockOutcomeBillItem.getNumber().multiply(StockOutcomeBillItem.getSalePrice())
					.setScale(2, RoundingMode.HALF_UP);
			StockOutcomeBillItem.setAmount(amount);
			totalNumber = totalNumber.add(StockOutcomeBillItem.getNumber());
			totalAmount = totalAmount.add(amount);
		}

		bill.setTotalAmount(totalAmount);
		bill.setTotalNumber(totalNumber);
		mapper.save(bill);
		for (StockOutcomeBillItem StockOutcomeBillItem : items) {
			StockOutcomeBillItem.setBill(bill);
			stockOutcomeBillItemMapper.save(StockOutcomeBillItem);
		}
	}

	public StockOutcomeBill get(Long id) {
		return mapper.get(id);
	}

	public List<StockOutcomeBill> list() {
		return mapper.list();
	}

	public void update(StockOutcomeBill bill) {
		StockOutcomeBill oldBill = mapper.get(bill.getId());
		if (oldBill.getStatus() == 0) {
			stockOutcomeBillItemMapper.deleteAllByStockOutcomeBillId(bill.getId());
			List<StockOutcomeBillItem> items = bill.getItems();
			BigDecimal totalNumber = BigDecimal.ZERO;
			BigDecimal totalAmount = BigDecimal.ZERO;

			for (StockOutcomeBillItem item : items) {
				BigDecimal amount = item.getNumber().multiply(item.getSalePrice().setScale(2, RoundingMode.HALF_UP));
				item.setAmount(amount);
				totalAmount = totalAmount.add(item.getAmount());
				totalNumber = totalNumber.add(item.getNumber());
				item.setBill(bill);
				stockOutcomeBillItemMapper.save(item);
			}
			bill.setTotalAmount(totalAmount);
			bill.setTotalNumber(totalNumber);

		}
		mapper.update(bill);
	}

	@Override
	public PageResult pageQuery(StockOutcomeBillQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if (count <= 0) {
			return PageResult.emptyResult;
		}
		List<StockOutcomeBill> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), listData);
		return pageResult;
	}

	@Override
	public void audit(Long id) {
		StockOutcomeBill stockOutcomeBill = mapper.get(id);
		stockOutcomeBill.setAuditor(UserContext.getCurrentUser());
		stockOutcomeBill.setAuditTime(new Date());
		stockOutcomeBill.setStatus(1);
		mapper.updateStatus(stockOutcomeBill);
		List<StockOutcomeBillItem> items = stockOutcomeBill.getItems();
		for (StockOutcomeBillItem stockOutcomeBillItem : items) {
			ProductStock ps = productStockMapper.selectByDeoptIdAndProductId(stockOutcomeBill.getDepot().getId(), stockOutcomeBillItem.getProduct().getId());
			if (ps == null) {
					throw new RuntimeException(stockOutcomeBill.getDepot().getName()+"仓库中,没有货品:"+stockOutcomeBillItem.getProduct().getName());
			}
			if(ps.getStoreNumber().compareTo(stockOutcomeBillItem.getNumber()) < 0) {
				throw new RuntimeException(stockOutcomeBill.getDepot().getName()+"仓库中,货品:"+stockOutcomeBillItem.getProduct().getName()+",货品存库不足"+stockOutcomeBillItem.getNumber());
			}
			ps.setStoreNumber(ps.getStoreNumber().subtract(stockOutcomeBillItem.getNumber()));
			ps.setAmount(ps.getStoreNumber().multiply(ps.getPrice()));
			productStockMapper.update(ps);
			
			SaleAccount sa = new SaleAccount();
			sa.setVdate(new Date());
			sa.setNumber(stockOutcomeBillItem.getNumber());
			sa.setCostPrice(ps.getPrice());
			sa.setCostAmount(sa.getNumber().multiply(sa.getCostAmount().setScale(2, RoundingMode.HALF_UP)));
			sa.setSalePrice(stockOutcomeBillItem.getSalePrice());
			
			
			sa.setSaleAmount(stockOutcomeBillItem.getAmount());
			
			sa.setProduct(stockOutcomeBillItem.getProduct());
			sa.setSaleman(stockOutcomeBill.getInputUser());
			sa.setClient(stockOutcomeBill.getClient());
			
			saleAccountMapper.save(sa);
			
		}

	}
}
