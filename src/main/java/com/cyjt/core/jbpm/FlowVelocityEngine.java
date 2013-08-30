package com.cyjt.core.jbpm;

import com.cyjt.core.util.AppUtil;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.ui.velocity.CommonsLoggingLogSystem;

public class FlowVelocityEngine implements FactoryBean {
	private Log logger = LogFactory.getLog(FlowVelocityEngine.class);
	private Properties velocityProperties;
	private String templatePath;

	public Properties getVelocityProperties() {
		return this.velocityProperties;
	}

	public void setVelocityProperties(Properties velocityProperties) {
		this.velocityProperties = velocityProperties;
	}

	public Object getObject() throws Exception {
		return createVelocityEngine();
	}

	public Class getObjectType() {
		return VelocityEngine.class;
	}

	public boolean isSingleton() {
		return false;
	}

	public VelocityEngine createVelocityEngine() throws IOException,
			VelocityException {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("create flowVelocityEngine... and the path is "
					+ AppUtil.getAppAbsolutePath() + this.templatePath);
		}

		VelocityEngine velocityEngine = new VelocityEngine();

		velocityEngine.setProperty("runtime.log.logsystem",
				new CommonsLoggingLogSystem());

		for (Iterator it = this.velocityProperties.entrySet().iterator(); it
				.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			if (!(entry.getKey() instanceof String)) {
				throw new IllegalArgumentException("Illegal property key ["
						+ entry.getKey() + "]: only Strings allowed");
			}
			velocityEngine.setProperty((String) entry.getKey(),
					entry.getValue());
		}

		velocityEngine.setProperty("runtime.log.logsystem.class",
				"org.apache.velocity.runtime.log.Log4JLogChute");

		velocityEngine.setProperty("runtime.log.logsystem.log4j.logger",
				"velocity");

		velocityEngine.setProperty("file.resource.loader.path",
				AppUtil.getAppAbsolutePath() + this.templatePath);
		try {
			velocityEngine.init();
		} catch (VelocityException ex) {
			throw ex;
		} catch (RuntimeException ex) {
			throw ex;
		} catch (Exception ex) {
			this.logger
					.error("Why does VelocityEngine throw a generic checked exception, after all?",
							ex);
			throw new VelocityException(ex.toString());
		}

		return velocityEngine;
	}

	public String getTemplatePath() {
		return this.templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
}
