package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Depot extends BaseDomain {
	private String location;
	private String name;
}
