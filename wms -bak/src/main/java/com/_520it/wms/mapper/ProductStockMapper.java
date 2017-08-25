package com._520it.wms.mapper;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.query.ProductStockQueryObject;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ProductStockMapper {
	void save(ProductStock entity);
	
	void update(ProductStock entity);
	
	void delete(Long id);
	
    ProductStock get(Long id);
    
	List<ProductStock> list();
	
    Long getTotalCount(ProductStockQueryObject qo);
    
    List<ProductStock> getListData(ProductStockQueryObject qo);

	ProductStock selectByDeoptIdAndProductId( @Param("depotId") Long depotId,@Param("productId")  Long productId);
}