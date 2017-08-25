package com._520it.wms.mapper;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import java.util.List;

public interface StockOutcomeBillMapper {
	void save(StockOutcomeBill entity);
	
	void update(StockOutcomeBill entity);
	
	void delete(Long id);
	
    StockOutcomeBill get(Long id);
    
    void updateStatus(StockOutcomeBill bill);
    
	List<StockOutcomeBill> list();
	
    Long getTotalCount(StockOutcomeBillQueryObject qo);
    
    List<StockOutcomeBill> getListData(StockOutcomeBillQueryObject qo);
}