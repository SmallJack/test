package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ObjectProp("客户")
public class Client extends BaseDomain {
	@ObjectProp("客户名称")
	private String name;
	@ObjectProp("客户编码")
	private String sn;
	@ObjectProp("联系电话")
	private String phone;
}
