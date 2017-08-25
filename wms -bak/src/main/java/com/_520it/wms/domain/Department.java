package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ObjectProp("部门")
public class Department extends BaseDomain {

	@ObjectProp("名称")
	private String name;
	@ObjectProp("编码")
	private String sn;
}
