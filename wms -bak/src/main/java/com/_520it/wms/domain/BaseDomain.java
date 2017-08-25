package com._520it.wms.domain;

import java.io.Serializable;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

//抽取通用的实体对象
@Getter
@Setter
public class BaseDomain implements Serializable {
	@ObjectProp("编号")
	protected Long id;
}
