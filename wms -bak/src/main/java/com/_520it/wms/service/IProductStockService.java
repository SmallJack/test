package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductStockQueryObject;

public interface IProductStockService {
	void delete(Long id);
	
	void save(ProductStock entity);
	
    ProductStock get(Long id);
    
    List<ProductStock> list();
    
	void update(ProductStock entity);
	
	PageResult pageQuery(ProductStockQueryObject qo);
}
