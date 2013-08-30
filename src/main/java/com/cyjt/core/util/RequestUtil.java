package com.cyjt.core.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	public static final String getErrorUrl(HttpServletRequest request) {
		String errorUrl = (String) request
				.getAttribute("javax.servlet.error.request_uri");
		if (errorUrl == null) {
			errorUrl = (String) request
					.getAttribute("javax.servlet.forward.request_uri");
		}
		if (errorUrl == null) {
			errorUrl = (String) request
					.getAttribute("javax.servlet.include.request_uri");
		}
		if (errorUrl == null) {
			errorUrl = request.getRequestURL().toString();
		}
		return errorUrl;
	}

	public static final StringBuilder getErrorInfoFromRequest(
			HttpServletRequest request, boolean isInfoEnabled) {
		StringBuilder sb = new StringBuilder();
		String errorUrl = getErrorUrl(request);
		sb.append(StringUtil.formatMsg(
				"Error processing url: %1, Referrer: %2, Error message: %3.\n",
				new Object[] { errorUrl, request.getHeader("REFERER"),
						request.getAttribute("javax.servlet.error.message") }));

		Throwable ex = (Throwable) request.getAttribute("exception");
		if (ex == null) {
			ex = (Throwable) request
					.getAttribute("javax.servlet.error.exception");
		}

		if (ex != null) {
			sb.append(StringUtil.formatMsg("Exception stack trace: \n",
					new Object[] { ex }));
		}
		return sb;
	}
}
