package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemMenuQueryObject extends QueryObject {

	// 父分类id
	private Long parentId;

	private String parentSn;
}
