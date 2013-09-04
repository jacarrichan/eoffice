package com.palmelf.core.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanUtil {
	private static Log log = LogFactory.getLog(BeanUtil.class);

	public static void copyNotNullProperties(Object dest, Object orig)
			throws IllegalAccessException, InvocationTargetException {
		BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();

		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}
		if (log.isDebugEnabled()) {
			log.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
		}

		if ((orig instanceof DynaBean)) {
			DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();

				if ((!beanUtils.getPropertyUtils().isReadable(orig, name))
						|| (!beanUtils.getPropertyUtils().isWriteable(dest,
								name)))
					continue;
				Object value = ((DynaBean) orig).get(name);
				beanUtils.copyProperty(dest, name, value);
			}
		} else if ((orig instanceof Map)) {
			Iterator entries = ((Map) orig).entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				String name = (String) entry.getKey();
				if (beanUtils.getPropertyUtils().isWriteable(dest, name))
					beanUtils.copyProperty(dest, name, entry.getValue());
			}
		} else {
			PropertyDescriptor[] origDescriptors = beanUtils.getPropertyUtils()
					.getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ("class".equals(name)) {
					continue;
				}
				if ((!beanUtils.getPropertyUtils().isReadable(orig, name))
						|| (!beanUtils.getPropertyUtils().isWriteable(dest,
								name)))
					continue;
				try {
					Object value = beanUtils.getPropertyUtils()
							.getSimpleProperty(orig, name);
					if (value != null)
						beanUtils.copyProperty(dest, name, value);
				} catch (NoSuchMethodException localNoSuchMethodException) {
				}
			}
		}
	}
}
