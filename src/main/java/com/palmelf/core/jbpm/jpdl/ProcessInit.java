package com.palmelf.core.jbpm.jpdl;

import com.palmelf.core.util.AppUtil;
import com.palmelf.core.util.XmlUtil;
import com.palmelf.eoffice.model.flow.ProDefinition;
import com.palmelf.eoffice.service.flow.JbpmService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;

public class ProcessInit {
	private static Log logger = LogFactory.getLog(ProcessInit.class);

	public static void initFlows(String absPath) {
		try {
			JbpmService jbpmService = (JbpmService) AppUtil
					.getBean("jbpmService");
			List<ProDefinition> defs = getProDefinitions(absPath);
			for (ProDefinition def : defs) {
				logger.debug("初始化系统流程:" + def.getName());
				jbpmService.saveOrUpdateDeploy(def);
			}
		} catch (Exception ex) {
			logger.debug("init flows:" + ex.getMessage());
		}
	}

	public static List<ProDefinition> getProDefinitions(String absPath)
			throws IOException {
//		String jpdlPath = absPath + "/WEB-INF/classes/jpdl";
		String jpdlPath = absPath + "/jpdl";
		String initFlowFile = jpdlPath + "/initFlow.xml";

		List<ProDefinition> proDefinitions = new ArrayList<ProDefinition>();
		Document rootDoc = XmlUtil.load(initFlowFile);

		if (rootDoc != null) {
			Element defEl = rootDoc.getRootElement();
			if (defEl != null) {
				Iterator<?> processIt = defEl.elementIterator();
				while (processIt.hasNext()) {
					ProDefinition proDefinition = new ProDefinition();
					Element processEl = (Element) processIt.next();
					String name = processEl.attributeValue("name");
					proDefinition.setName(name);
					proDefinition.setCreatetime(new Date());

					Element descEl = (Element) processEl
							.selectSingleNode("./description");
					if (descEl != null) {
						String description = descEl.getText();
						proDefinition.setDescription(description);
						proDefinition.setIsDefault(ProDefinition.IS_DEFAULT);
					}
					Element jpdlLocEl = (Element) processEl
							.selectSingleNode("./jpdlLocation");
					if (jpdlLocEl != null) {
						String jpdlLocation = jpdlLocEl.getText().trim();
						File jpdlFile = new File(jpdlPath + "/" + jpdlLocation);
						if (jpdlLocation != null) {
							File timeFile = new File(
									jpdlPath
											+ "/"
											+ jpdlLocation.replace(".jpdl.xml",
													".time"));
							logger.debug("流程文件===="
									+ jpdlFile.getAbsolutePath()
									+ "\t timeFile"
									+ timeFile.getAbsolutePath());
							boolean isNotExist = false;
							if (!timeFile.exists()) {
								timeFile.createNewFile();
								isNotExist = true;
							}

							if ((isNotExist)
									|| (jpdlFile.lastModified() > timeFile
											.lastModified())) {
								String defXml = XmlUtil.load(jpdlFile).asXML();
								proDefinition.setDefXml(defXml);
								logger.debug(proDefinition.getName()
										+ "\t部署流程中----");
								proDefinitions.add(proDefinition);
							}
							timeFile.setLastModified(jpdlFile.lastModified());
						}
					}
				}
			} else {
				logger.error("默认的流程文件没有内容");
			}
		} else {
			logger.error("找不到默认的流程文件");
		}

		return proDefinitions;
	}
}
