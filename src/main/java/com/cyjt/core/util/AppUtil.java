package com.cyjt.core.util;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.cyjt.core.DataInit.DataInit;
import com.cyjt.core.jbpm.jpdl.ProcessInit;
import com.cyjt.core.model.OnlineUser;
import com.cyjt.core.web.filter.SecurityInterceptorFilter;
import com.cyjt.oa.model.system.AppFunction;
import com.cyjt.oa.model.system.AppRole;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.Company;
import com.cyjt.oa.model.system.FunUrl;
import com.cyjt.oa.model.system.SysConfig;
import com.cyjt.oa.service.system.AppFunctionService;
import com.cyjt.oa.service.system.CompanyService;
import com.cyjt.oa.service.system.FunUrlService;
import com.cyjt.oa.service.system.SysConfigService;

public class AppUtil implements ApplicationContextAware {
	private static Log logger = LogFactory.getLog(AppUtil.class);

	private static Map<String, Object> configMap = new HashMap<String, Object>();

	private static ServletContext servletContext = null;

	private static Map<String, OnlineUser> onlineUsers = new LinkedHashMap<String, OnlineUser>();
	private static ApplicationContext appContext;
	private static Document lefMenuDocument = null;

	private static Document publicDocument = null;

	private static Set<String> publicMenuIds = null;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		AppUtil.appContext = applicationContext;
	}

	public static Document getLeftMenuDocument() {
		return AppUtil.lefMenuDocument;
	}

	public static void setLeftMenuDocument(Document doc) {
		AppUtil.lefMenuDocument = doc;
	}

	public static Document getPublicDocument() {
		return AppUtil.publicDocument;
	}

	public static void setPublicDocument(Document pubDoc) {
		AppUtil.publicDocument = pubDoc;
	}

	public static void setPublicMenuIds(Set<String> pubIds) {
		AppUtil.publicMenuIds = pubIds;
	}

	public static Object getBean(String beanId) {
		return AppUtil.appContext.getBean(beanId);
	}

	public static Map<String, OnlineUser> getOnlineUsers() {
		return AppUtil.onlineUsers;
	}

	public static void removeOnlineUser(String sessionId) {
		AppUtil.onlineUsers.remove(sessionId);
	}

	public static void addOnlineUser(String sessionId, AppUser user) {
		if (!AppUtil.onlineUsers.containsKey(sessionId)) {
			OnlineUser onlineUser = new OnlineUser();
			onlineUser.setFullname(user.getFullname());
			onlineUser.setSessionId(sessionId);
			onlineUser.setUsername(user.getUsername());
			onlineUser.setUserId(user.getUserId());
			if (!user.getUserId().equals(AppUser.SUPER_USER)) {
				onlineUser.setDepPath("." + user.getDepartment().getPath());
			}
			Set<AppRole> roles = user.getRoles();
			StringBuffer roleIds = new StringBuffer(",");
			for (AppRole role : roles) {
				roleIds.append(role.getRoleId() + ",");
			}
			onlineUser.setRoleIds(roleIds.toString());
			onlineUser.setTitle(user.getTitle());
			AppUtil.onlineUsers.put(sessionId, onlineUser);
		}
	}

	public static String getAppAbsolutePath() {
		return AppUtil.servletContext.getRealPath("/");
	}

	public static String getFlowFormAbsolutePath() {
		String path = (String) AppUtil.configMap.get("app.flowFormPath");
		if (path == null) {
			path = "/WEB-INF/FlowForm/";
		}
		return AppUtil.getAppAbsolutePath() + path;
	}

	public static String getMobileFlowFlowAbsPath() {
		return AppUtil.getAppAbsolutePath() + "/mobile/flow/FlowForm/";
	}

	public static void reloadSecurityDataSource() {
		SecurityInterceptorFilter securityInterceptorFilter = (SecurityInterceptorFilter) AppUtil
				.getBean("securityInterceptorFilter");
		securityInterceptorFilter.loadDataSource();
	}

	public static void init(ServletContext in_servletContext) {
		AppUtil.servletContext = in_servletContext;
		String configPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "conf";

		// 读取属性配置文件
		// String filePath =
		// AppUtil.servletContext.getRealPath("WEB-INF/classes/conf/");
		String configFilePath = configPath + "/config.properties";
		// String configFilePath =
		// AppUtil.class.getClassLoader().getSystemResource("conf/config.properties").getPath();
		Properties props = new Properties();
		try {
			FileInputStream fis = new FileInputStream(configFilePath);
			Reader r = new InputStreamReader(fis, "UTF-8");
			props.load(r);
			Iterator it = props.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				AppUtil.configMap.put(key, props.get(key));
			}
		} catch (Exception ex) {
			AppUtil.logger.error(ex.getMessage());
		}

		AppUtil.reloadSysConfig();
		// 读取公司信息
		CompanyService companyService = (CompanyService) AppUtil.getBean("companyService");
		List cList = companyService.findCompany();
		if (cList.size() > 0) {
			Company company = (Company) cList.get(0);
			AppUtil.configMap.put("app.logoPath", company.getLogo());
			AppUtil.configMap.put("app.companyName", company.getCompanyName());
		}
		// 读取主页面左菜单配置信息
		String xslStyle = AppUtil.servletContext.getRealPath("/js/menu") + "/menu-left.xsl";
		Document doc = AppUtil.getOrignalMenuDocument();
		try {
			Document finalDoc = XmlUtil.styleDocument(doc, xslStyle);
			AppUtil.setLeftMenuDocument(finalDoc);
		} catch (Exception ex) {
			AppUtil.logger.error("menux.xml trasform has error:" + ex.getMessage());
		}
		String publicStyle = AppUtil.servletContext.getRealPath("/js/menu") + "/menu-public.xsl";
		try {
			Document publicDoc = XmlUtil.styleDocument(doc, publicStyle);
			HashSet<String> pubIds = new HashSet<String>();
			Element rootEl = publicDoc.getRootElement();
			List<Element> idNodes = rootEl.selectNodes("/Menus//*");
			for (int i = 0; i < idNodes.size(); i++) {
				Element el = idNodes.get(i);
				Attribute attr = el.attribute("id");
				if (attr != null) {
					pubIds.add(attr.getValue());
				}
			}

			AppUtil.setPublicMenuIds(pubIds);
			AppUtil.setPublicDocument(publicDoc);
		} catch (Exception ex) {
			AppUtil.logger.error("menu.xml + menu-public.xsl transform has error:" + ex.getMessage());
		}

		if (AppUtil.isSetupMode()) {
			String absPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			AppUtil.logger.info("初始化数据~\t开始...");
			// DataInit.init(AppUtil.getAppAbsolutePath());
			DataInit.init(absPath);
			AppUtil.logger.info("初始化数据~\t结束...");

			AppUtil.logger.info("开始初始化系统的缺省流程...");
			ProcessInit.initFlows(absPath);
			// ProcessInit.initFlows(AppUtil.getAppAbsolutePath());
			AppUtil.logger.info("结束初始化系统的缺省流程...");
			// PropertiesUtil.writeKey(configFilePath, "setupMode", "false");
		} else {
			logger.debug("没有设置成安装模式,将不会初始化数据");
		}
	}

	/**
	 * 获取原始菜单配置信息
	 * 
	 * @return
	 */
	public static Document getOrignalMenuDocument() {
		String menuFilePath = AppUtil.servletContext.getRealPath("/js/menu") + "/menu.xml";
		Document doc = XmlUtil.load(menuFilePath);
		return doc;
	}

	public static Document getGrantMenuDocument() {
		String xslStyle = AppUtil.servletContext.getRealPath("/js/menu") + "/menu-grant.xsl";
		Document finalDoc = null;
		try {
			finalDoc = XmlUtil.styleDocument(AppUtil.getOrignalMenuDocument(), xslStyle);
		} catch (Exception ex) {
			AppUtil.logger.error("menu.xml + menu-grant.xsl transform has error:" + ex.getMessage());
		}
		return finalDoc;
	}

	public static Document getPublicMenuDocument() {
		return AppUtil.publicDocument;
	}

	public static Set<String> getPublicMenuIds() {
		return AppUtil.publicMenuIds;
	}

	public static void synMenu() {
		AppFunctionService appFunctionService = (AppFunctionService) AppUtil.getBean("appFunctionService");
		FunUrlService funUrlService = (FunUrlService) AppUtil.getBean("funUrlService");

		List funNodeList = AppUtil.getOrignalMenuDocument().getRootElement().selectNodes("/Menus/Items//Item/Function");

		for (int i = 0; i < funNodeList.size(); i++) {
			Element funNode = (Element) funNodeList.get(i);

			String key = funNode.attributeValue("id");

			String name = funNode.attributeValue("text");

			AppFunction appFunction = appFunctionService.getByKey(key);

			if (appFunction == null) {
				appFunction = new AppFunction(key, name);
			} else {
				appFunction.setFunName(name);
			}

			List urlNodes = funNode.selectNodes("./url");

			appFunctionService.save(appFunction);

			for (int k = 0; k < urlNodes.size(); k++) {
				Node urlNode = (Node) urlNodes.get(k);
				String path = urlNode.getText();
				FunUrl fu = funUrlService.getByPathFunId(path, appFunction.getFunctionId());
				if (fu == null) {
					fu = new FunUrl();
					fu.setUrlPath(path);
					fu.setAppFunction(appFunction);
					funUrlService.save(fu);
				}
			}
		}
	}

	public static boolean getIsSynMenu() {
		String synMenu = (String) AppUtil.configMap.get("isSynMenu");

		return "true".equals(synMenu);
	}

	public static Map<String, Object> getSysConfig() {
		return AppUtil.configMap;
	}

	public static void reloadSysConfig() {
		SysConfigService sysConfigService = (SysConfigService) AppUtil.getBean("sysConfigService");
		List<SysConfig> list = sysConfigService.getAll();
		for (SysConfig conf : list) {
			AppUtil.configMap.put(conf.getConfigKey(), conf.getDataValue());
		}
	}

	public static String getCompanyLogo() {
		String defaultLogoPath = "/images/";
		String path = (String) AppUtil.configMap.get("app.logoPath");
		if (StringUtils.isNotEmpty(path)) {
			defaultLogoPath = "/attachFiles/" + path;
		}
		return defaultLogoPath;
	}

	public static String getCompanyName() {
		String defaultName = "ThinkPad";
		String companyName = (String) AppUtil.configMap.get("app.companyName");
		if (StringUtils.isNotEmpty(companyName)) {
			defaultName = companyName;
		}
		return defaultName;
	}

	public static boolean isSetupMode() {
		String isSetupMode = (String) AppUtil.configMap.get("setupMode");

		return "true".equals(isSetupMode);
	}
}
