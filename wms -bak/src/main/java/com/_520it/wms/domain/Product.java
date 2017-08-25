package com._520it.wms.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Product extends BaseDomain {
	@ObjectProp("货品名称")
	private String name;
	@ObjectProp("货品编码")
	private String sn;
	@ObjectProp("成本价格")
	private BigDecimal costPrice;
	@ObjectProp("销售价格")
	private BigDecimal salePrice;
	@ObjectProp("图片")
	private String imagePath;
	@ObjectProp("货品介绍")
	private String intro;
	@ObjectProp("品牌")
	private Brand brand;

	public String getSmallImagePath() {
		String fileName = imagePath.substring(0, imagePath.lastIndexOf(".")) + "_small";
		String ext = imagePath.substring(imagePath.lastIndexOf("."), imagePath.length());
		return fileName + ext;
	}

	public String getJsonString() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("costPrice", costPrice);
		map.put("brandName", brand.getName());
		map.put("salePrice", salePrice);
		return JSON.toJSONString(map);
	}

}
