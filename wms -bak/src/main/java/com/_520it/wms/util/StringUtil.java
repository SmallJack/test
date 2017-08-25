package com._520it.wms.util;

public class StringUtil {
	private StringUtil() {
	}

	public static Boolean hasLength(String str) {
		return str != null && !"".equals(str);
	}

}
