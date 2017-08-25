package com._520it.wms.domain;

import java.util.ArrayList;
import java.util.List;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ObjectProp("系统菜单")
public class SystemMenu extends BaseDomain {

	@ObjectProp("名称")
	private String name;
	@ObjectProp("URL")
	private String url;
	@ObjectProp("编码")
	private String sn;
	//父菜单:多对一
	private SystemMenu parent;

	//所有的自菜单:一对多
	private List<SystemMenu> childs = new ArrayList<>();

	public String getParentName() {
		//调用getParent()方法触发延迟加载
		if (getParent() == null) {
			return "根目录";
		} else {
			return parent.getName();
		}
	}

	@Override
	public String toString() {
		return "SystemMenu [name=" + name + ", url=" + url + ", sn=" + sn + "]";
	}

}
