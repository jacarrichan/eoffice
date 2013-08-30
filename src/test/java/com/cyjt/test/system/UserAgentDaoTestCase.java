package com.cyjt.test.system;

import com.cyjt.oa.dao.system.UserAgentDao;
import com.cyjt.test.BaseTestCase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class UserAgentDaoTestCase extends BaseTestCase {

	@Resource
	private UserAgentDao userAgentDao;
	private File result = new File("web/attachFiles/App.i18n.AppUserView.js");
	private FileWriter writer;
	private PrintWriter pw;
	private ServletContext servletContext;

	@Test
	@Rollback(false)
	public void add() {
		try {
			this.writer = new FileWriter(
					"web/attachFiles/App.i18n.AppUserView.js");
			this.pw = new PrintWriter(this.writer);
			this.pw.println("Ext.ns(\"App.i18n\");");
			this.pw.print("App.i18n.UserView = {");
			Properties prop = new Properties();

			String configFilePath = "web/WEB-INF/classes/i18n/en/en.AppUserView.properties";

			FileInputStream fis = new FileInputStream(configFilePath);
			Reader r = new InputStreamReader(fis, "UTF-8");
			prop.load(r);
			int count = 0;
			for (Iterator it = prop.keySet().iterator(); it.hasNext();) {
				count++;
				String key = (String) it.next();
				String value = prop.getProperty(key);
				this.pw.print(key + ":'" + value + "'");
				if (count < prop.size()) {
					this.pw.print(",");
				}
			}
			this.pw.println("};");
			this.pw.flush();
			this.writer.close();
		} catch (IOException iox) {
			System.err.println(iox);
		}
	}
}
