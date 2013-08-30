package com.cyjt.core.web.listener;

import com.cyjt.core.util.AppUtil;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserSessionListener implements HttpSessionListener {
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		String sessionId = event.getSession().getId();
		AppUtil.removeOnlineUser(sessionId);
	}
}
