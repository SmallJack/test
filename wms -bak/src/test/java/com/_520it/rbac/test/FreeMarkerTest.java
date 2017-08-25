package com._520it.rbac.test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerTest {

	@Test
	public void test1() throws Exception {
		//建立数据模型
		Map<String, Object> map = new HashMap<>();
		map.put("name", "Neld");
		map.put("age", 10);
		List<String> list = new ArrayList<>();
		list.add("lily");
		list.add("lucy");
		map.put("girls", list);
		//使用FreeMarker来生成合并的文件
		//创建配置对象
		Configuration cfg = new Configuration();
		//设置模板文件所在的目录
		cfg.setDirectoryForTemplateLoading(new File("template"));
		//获取指定的模板文件
		Template tmp = cfg.getTemplate("hello.ftl");
		//解析合并,输出合并之后的文件
		tmp.process(map, new FileWriter(new File("hello.txt")));
	}
}
