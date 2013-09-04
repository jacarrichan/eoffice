package com.palmelf.core.struts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter {
	private static final Log logger = LogFactory.getLog(DateConverter.class);
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String[] ACCEPT_DATE_FORMATS = { "yyyy-MM-dd HH:mm:ss",
			"yyyy-MM-dd" };

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (logger.isDebugEnabled()) {
			logger.debug("converFromString....");
			if (values != null) {
				logger.debug("convert from  :" + values[0]);
			}
		}
		if ((values[0] == null) || (values[0].trim().equals(""))) {
			return null;
		}

		try {
			return DateUtils.parseDate(values[0], ACCEPT_DATE_FORMATS);
		} catch (Exception ex) {
			logger.debug("parse date error:" + ex.getMessage());
		}

		return null;
	}

	@Override
	public String convertToString(Map context, Object o) {
		if ((o instanceof Date)) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			try {
				return format.format((Date) o);
			} catch (RuntimeException e) {
				return "";
			}
		}
		return "";
	}
}
