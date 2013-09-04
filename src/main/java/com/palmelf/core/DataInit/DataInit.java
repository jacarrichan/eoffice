package com.palmelf.core.DataInit;

import com.palmelf.core.model.BaseModel;
import com.palmelf.core.service.BaseService;
import com.palmelf.core.util.AppUtil;
import com.palmelf.core.util.XmlUtil;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;

public class DataInit {
	private static Log logger = LogFactory.getLog(DataInit.class);
	protected static SimpleDateFormat df = new SimpleDateFormat();

	public static void init(String absPath) {
		// String confPath = absPath + "/WEB-INF/classes/conf";
		String confPath = absPath+"conf";
		String dataInitFile = confPath + "/data-init.xml";
		Document rootDoc = XmlUtil.load(dataInitFile);
		if (rootDoc != null) {
			Element rootEl = rootDoc.getRootElement();
			initNode(rootEl, null);
		}
	}

	public static void initNode(Element rootEl, Object parentObj) {
		if (rootEl != null) {
			Iterator model_It = rootEl.elementIterator();
			while (model_It.hasNext()) {
				Element modelEl = (Element) model_It.next();

				String _class = modelEl.attributeValue("class");
				String _service = modelEl.attributeValue("service");
				String _description = modelEl.attributeValue("description");

				List propertyList = modelEl.selectNodes("property");
				Iterator modelIt = propertyList.iterator();

				List setList = modelEl.selectNodes("set");
				Iterator setIt = setList.iterator();
				try {
					BaseModel model = (BaseModel) Class.forName(_class).newInstance();
					BaseService service = (BaseService) AppUtil.getBean(_service);

					Map nodeMap = convertNodeToMap(modelIt, parentObj);

					model = (BaseModel) convertMapToBean(Class.forName(_class), nodeMap);

					model = (BaseModel) service.save(model);
					service.flush();

					String primary_key = modelEl.attributeValue("primary-key");
					String key_type = modelEl.attributeValue("key-type");
					Map beanMap = convertBeanToMap(model);
					Object key_value = beanMap.get(primary_key);
					while (setIt.hasNext()) {
						Element setEl = (Element) setIt.next();
						initNode(setEl, key_value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static Map<Object, Object> convertNodeToMap(Iterator<Element> modelIt, Object parentObj) {
		Map beanMap = new HashMap();
		if (modelIt != null) {
			while (modelIt.hasNext()) {
				Element propertyEl = modelIt.next();
				String type = propertyEl.attributeValue("type");
				String name = propertyEl.attributeValue("name");
				String value = propertyEl.attributeValue("value");
				String foreign_key = propertyEl.attributeValue("foreign-key");

				Object valueObj = null;
				if ((foreign_key != null) && (foreign_key.equals("true")))
					valueObj = parentObj;
				else {
					valueObj = convertValueObj(type, value, propertyEl);
				}

				beanMap.put(name, valueObj);
			}
		}

		return beanMap;
	}

	private static <T> Map<Object, Object> convertBeanToMap(Object bean) throws IntrospectionException {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				try {
					Object result = readMethod.invoke(bean, new Object[0]);
					returnMap.put(propertyName, result);
				} catch (Exception e) {
					logger.debug("解析方法名:" + readMethod + ",有误!");
				}

			}

		}

		return returnMap;
	}

	private static <T> T convertMapToBean(Class<T> type, Map<Object, Object> map) throws IntrospectionException,
			InstantiationException, IllegalAccessException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Object t = type.newInstance();

		for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
			String propertyName = descriptor.getName();
			if (!map.containsKey(propertyName))
				continue;
			try {
				descriptor.getWriteMethod().invoke(t, new Object[] { map.get(propertyName) });
			} catch (Exception e) {
				logger.info("属性:" + propertyName + ",或值:" + map.get(propertyName) + ":有误!");
				e.printStackTrace();
			}
		}

		return (T) t;
	}

	private static Object convertValueObj(String type, String value, Element propertyEl) {
		Object valueObj = null;
		if ((type.equals("java.util.Date")) || (type.equals("Date"))) {
			String date_format = propertyEl.attributeValue("date-format");
			df.applyPattern(date_format);

			String today_value = propertyEl.attributeValue("today-value");
			if ((today_value != null) && (today_value.equals("true"))) {
				valueObj = new Date();
			} else if ((value != null) && (!value.equals(""))) {
				try {
					valueObj = df.parse(value);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		} else if ((type.equals("java.lang.Long")) || (type.equals("Long"))) {
			if (value != null)
				valueObj = new Long(value);
		} else if ((type.equals("java.lang.Short")) || (type.equals("Short"))) {
			if (value != null)
				valueObj = new Short(value);
		} else if ((type.equals("java.lang.Double")) || (type.equals("Double"))) {
			if (value != null)
				valueObj = new Double(value);
		} else if ((type.equals("java.lang.Float")) || (type.equals("Float"))) {
			valueObj = new Float(value);
		} else if ((type.equals("java.lang.Integer")) || (type.equals("Integer"))) {
			if (value != null)
				valueObj = new Integer(value);
		} else if (value != null) {
			valueObj = new String(value);
		}
		return valueObj;
	}
}
