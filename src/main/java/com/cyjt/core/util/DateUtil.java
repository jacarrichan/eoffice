package com.cyjt.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	private static final Log logger = LogFactory.getLog(DateUtil.class);

	public static Calendar setStartDay(Calendar cal) {
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		return cal;
	}

	public static Calendar setEndDay(Calendar cal) {
		cal.set(11, 23);
		cal.set(12, 59);
		cal.set(13, 59);
		return cal;
	}

	public static void copyYearMonthDay(Calendar destCal, Calendar sourceCal) {
		destCal.set(1, sourceCal.get(1));
		destCal.set(2, sourceCal.get(2));
		destCal.set(5, sourceCal.get(5));
	}

	public static String formatEnDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

		return sdf.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
	}

	public static Date parseDate(String dateString) {
		Date date = null;
		try {
			date = DateUtils.parseDate(dateString, new String[] {
					"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" });
		} catch (Exception ex) {
			logger.error("Pase the Date(" + dateString + ") occur errors:"
					+ ex.getMessage());
		}
		return date;
	}
}
