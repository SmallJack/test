package com._520it.wms.service.impl;

import java.util.List;


import com._520it.wms.page.PageResult;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IProductStockService;
import lombok.Setter;
public class ProductStockServiceImpl implements IProductStockService {
	@Setter
	private ProductStockMapper mapper;
	
	public void  delete(Long id) {
		  mapper.delete(id);
	}

	public void save(ProductStock entity) {
		  mapper.save(entity);
	}

	public ProductStock get(Long id) {
		return mapper.get(id);
	}

	public List<ProductStock> list() {
		return mapper.list();
	}

	public void update(ProductStock entity) {
		  mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(ProductStockQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if(count<=0){
			return PageResult.emptyResult;
		}
		List<ProductStock> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), listData);
		return pageResult;
	}
}
