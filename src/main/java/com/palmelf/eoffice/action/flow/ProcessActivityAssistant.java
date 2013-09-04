package com.palmelf.eoffice.action.flow;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.palmelf.core.jbpm.pv.ParamField;
import com.palmelf.core.util.AppUtil;
import com.palmelf.core.util.XmlUtil;

public class ProcessActivityAssistant {
	private static final Log logger = LogFactory
			.getLog(ProcessActivityAssistant.class);

	public static Map<String, ParamField> constructMobileFieldMap(
			String processName, String activityName) {
		String fieldsXmlLoaction = ProcessActivityAssistant
				.getMobileFieldsAbsPath(processName, activityName);
		return ProcessActivityAssistant.genFieldMap(processName, activityName,
				fieldsXmlLoaction);
	}

	public static Map<String, ParamField> constructFieldMap(String processName,
			String activityName) {
		String fieldsXmlLoaction = ProcessActivityAssistant.getFieldAbsPath(
				processName, activityName);
		return ProcessActivityAssistant.genFieldMap(processName, activityName,
				fieldsXmlLoaction);
	}

	public static Map<String, ParamField> genFieldMap(String processName,
			String activityName, String fieldsXmlLoaction) {
		InputStream is = null;
		Map<String, ParamField> map = new LinkedHashMap<String, ParamField>();
		try {
			is = new FileInputStream(fieldsXmlLoaction);
		} catch (Exception ex) {
			ProcessActivityAssistant.logger
					.warn("error when read the file from " + activityName
							+ "-fields.xml, the reason is not upload ");
		}

		if (is == null) {
			try {
				is = new FileInputStream(
						ProcessActivityAssistant
								.getCommonFieldsAbsPath(activityName));
			} catch (Exception ex) {
				ProcessActivityAssistant.logger
						.warn("error when read the file from 通用、表单-fields.xml, the reason is not upload ");
			}
		}

		Document doc = XmlUtil.load(is);
		if (doc != null) {
			Element fields = doc.getRootElement();
			List<Element> els = fields.elements();
			for (Element el : els) {
				String name = el.attribute("name").getValue();

				Attribute attLabel = el.attribute("label");
				Attribute attType = el.attribute("type");
				Attribute attLength = el.attribute("length");
				Attribute attIsShowed = el.attribute("isShowed");

				String label = attLabel == null ? name : attLabel.getValue();
				String type = attType == null ? "varchar" : attType.getValue();
				Integer length = Integer.valueOf(attLength == null ? 0
						: new Integer(attLength.getValue()).intValue());
				Short isShowed = Short.valueOf((short) ((attIsShowed == null)
						|| ("true".equals(attIsShowed.getValue())) ? 1 : 0));

				ParamField pf = new ParamField(name, type, label, length,
						isShowed);
				map.put(name, pf);
			}
		}

		return map;
	}

	public static String getStartFormPath(String processName) {
		return "/" + processName + "/开始.vm";
	}

	public static String getFormPath(String processName, String activityName) {
		return "/" + processName + "/" + activityName + ".vm";
	}

	public static String getFieldAbsPath(String processName, String activityName) {
		return AppUtil.getFlowFormAbsolutePath() + processName + "/"
				+ activityName + "-fields.xml";
	}

	public static String getMobileFieldsAbsPath(String processName,
			String activityName) {
		return AppUtil.getMobileFlowFlowAbsPath() + processName + "/"
				+ activityName + "-fields.xml";
	}

	public static String getFieldStartAbsPath(String processName) {
		return AppUtil.getFlowFormAbsolutePath() + processName
				+ "/开始-fields.xml";
	}

	public static String getCommonFormPath(String activityName) {
		if ("开始".equals(activityName)) {
			return "/通用/开始.vm";
		}
		return "/通用/表单.vm";
	}

	public static String getCommonFieldsAbsPath(String activityName) {
		String absPath = AppUtil.getFlowFormAbsolutePath();

		if ("开始".equals(activityName)) {
			return absPath + "通用/开始-fields.xml";
		}
		return absPath + "通用/表单-fields.xml";
	}
}
