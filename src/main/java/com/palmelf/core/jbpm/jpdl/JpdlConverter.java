package com.palmelf.core.jbpm.jpdl;

import com.palmelf.core.util.XmlUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

public class JpdlConverter {
	private static Log logger = LogFactory.getLog(JpdlConverter.class);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Long uuid = Long.valueOf(Math.abs(UUID.randomUUID()
					.getLeastSignificantBits()));
			System.out.println("uid:" + uuid.toString());
		}
	}

	public static String JpdlGen(String drawXml, String processName) {
		if (logger.isDebugEnabled()) {
			logger.debug("drawXml:" + drawXml);
		}

		Document jpdlDoc = DocumentHelper.createDocument();

		jpdlDoc.setXMLEncoding("utf-8");

		Element processEl = jpdlDoc.addElement("process");

		processEl.addAttribute("name", processName);

		Document drawDoc = XmlUtil.stringToDocument(drawXml);
		Element orgRootEl = drawDoc.getRootElement();

		Set transitionSet = new HashSet();

		Map nodeMap = parseDrawXml(transitionSet, orgRootEl);

		Map newNodeMap = new LinkedHashMap();
		Iterator ids = nodeMap.keySet().iterator();

		while (ids.hasNext()) {
			String id = (String) ids.next();
			Element pNode = (Element) nodeMap.get(id);
			Element curNewNode = processEl.addElement(pNode.getQualifiedName());
			String x = pNode.attributeValue("x");
			String y = pNode.attributeValue("y");

			String width = pNode.attributeValue("w");
			Integer intWidth = Integer
					.valueOf(new Integer(width).intValue() + 5);

			String height = pNode.attributeValue("h");
			Integer intHeight = Integer
					.valueOf(new Integer(height).intValue() + 15);

			curNewNode.addAttribute("name", pNode.attributeValue("name"));
			curNewNode.addAttribute("g", new Integer(x).intValue() - 12 + ","
					+ (new Integer(y).intValue() - 8) + "," + intWidth + ","
					+ intHeight);

			newNodeMap.put(id, curNewNode);
		}

		Iterator tranIt = transitionSet.iterator();

		while (tranIt.hasNext()) {
			Element tranEl = (Element) tranIt.next();
			String g = tranEl.attributeValue("g");
			String name = tranEl.attributeValue("name");

			Element startNode = (Element) tranEl
					.selectSingleNode("./startConnector/rConnector/Owner/*");
			Element endNode = (Element) tranEl
					.selectSingleNode("./endConnector/rConnector/Owner/*");

			if ((startNode != null) && (endNode != null)) {
				String startRef = startNode.attributeValue("ref");
				String endRef = endNode.attributeValue("ref");
				Element newEndNode;
				Element newStartNode;
				if ((startRef != null) && (endRef != null)) {
					newStartNode = (Element) newNodeMap.get(startRef);
					newEndNode = (Element) newNodeMap.get(endRef);
				} else {
					String startId = startNode.attributeValue("id");
					String endId = startNode.attributeValue("id");
					newStartNode = (Element) newNodeMap.get(startId);
					newEndNode = (Element) newNodeMap.get(endId);
				}

				Element transitionEl = newStartNode.addElement("transition");
				transitionEl.addAttribute("name", name);
				transitionEl.addAttribute("to",
						newEndNode.attributeValue("name"));
				transitionEl.addAttribute("g", g);

				if ("decision".equals(newStartNode.getQualifiedName())) {
					Element conditionEl = (Element) orgRootEl
							.selectSingleNode("/drawing/figures//decision/conditions/condition[@to='"
									+ name + "']");
					if (conditionEl != null) {
						Element newConditionEl = transitionEl
								.addElement("condition");
						newConditionEl.addAttribute("expr",
								conditionEl.attributeValue("expr"));
					}
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("after convter jbpm xml:" + processEl.asXML());
		}

		return jpdlDoc.asXML();
	}

	private static Map<String, Element> parseDrawXml(Set transitionSet,
			Element rootEl) {
		Map map = new LinkedHashMap();

		List<Element> figures = rootEl.selectNodes("/drawing/figures/*");

		for (Element el : figures) {
			String id = el.attributeValue("id");
			String ref = el.attributeValue("ref");

			if ("transition".equals(el.getQualifiedName())) {
				transitionSet.add(el);
			} else if (id != null) {
				map.put(id, el);
			} else if (ref != null) {
				Node figureNode = rootEl
						.selectSingleNode("/drawing/figures//*[@id='" + ref
								+ "']");
				map.put(ref, figureNode);
			}

		}

		return map;
	}
}
