package generator;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClassInfo {
	private String packageName;//包名
	private String className;//类简单名称
	private String objectName;//类名首字母小写
	private String objectCNName;//类的中文名称
	//获取类中所有的字段英文名称和中文名称
	private Map<String, String> fieldMap = new HashMap<String, String>();

	public ClassInfo(Class<?> clazz) throws Exception {
		//获取包名--->com._520it.wms.domain -->com._520it.wms
		this.packageName = clazz.getPackage().getName();
		this.packageName = this.packageName.substring(0, this.packageName.lastIndexOf("."));
		//获取类的简单名称--->Cat--cat
		this.className = clazz.getSimpleName();
		//获取类的首字母小写--->cat
		this.objectName = this.className.substring(0, 1).toLowerCase() + this.className.substring(1);
		//获取类的中文名称
		ObjectProp objProp = clazz.getAnnotation(ObjectProp.class);
		//获取到类的中文名称
		this.objectCNName = objProp == null ? objectName : objProp.value();
		//获取类中所有的字段英文名称和中文名称
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
		//获取到所有的属性描述信息
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			if ("id".equals(pd.getName())) {
				continue;
			}
			objProp = clazz.getDeclaredField(pd.getName()).getAnnotation(ObjectProp.class);
			fieldMap.put(pd.getName(), objProp == null ? pd.getName() : objProp.value());
		}
	}

}
