package com.cyjt.core.web.listener;

import com.cyjt.core.util.AppUtil;
import javax.servlet.ServletContextEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

/**
 * 在启动Web容器时，自动装配Spring applicationContext.xml的配置信息。
 * 
 * @author Jacarri
 * 
 */
public class StartupListener extends ContextLoaderListener {
	private static Log logger = LogFactory.getLog(StartupListener.class);

	public void contextInitialized(ServletContextEvent event) {
		logger.debug("---------------------------------------start");
		System.out.println("contextInitialized............"
				+ Thread.currentThread().getContextClassLoader().getResource("").getPath());
		super.contextInitialized(event);
		AppUtil.init(event.getServletContext());
		boolean isAynMenu = AppUtil.getIsSynMenu();
		if (isAynMenu)
			AppUtil.synMenu();
		logger.debug("---------------------------------------END");
	}
}
