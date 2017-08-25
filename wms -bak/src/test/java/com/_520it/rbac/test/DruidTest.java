package com._520it.rbac.test;

import org.junit.Test;

import com.alibaba.druid.filter.config.ConfigTools;

public class DruidTest {

	@Test
	public void testName() throws Exception {
		//lCzd9geWAuAuJtLhpaG/J+d28H8KiMFAWopxXkYpMNdUai6Xe/LsPqMQeg5MIrmvtMa+hzycdRhWs29ZUPU1IQ==
		System.out.println(ConfigTools.encrypt("admin"));
	}
}
