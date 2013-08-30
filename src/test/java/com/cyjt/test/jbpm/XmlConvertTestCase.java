package com.cyjt.test.jbpm;

import com.cyjt.core.util.XmlUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

public class XmlConvertTestCase {
	public static void main(String[] args) {
		Document jpdlDoc = DocumentHelper.createDocument();
		Element processEl = jpdlDoc.addElement("process");

		processEl.addAttribute("name", "testProcess");

		Set transitionSet = new HashSet();

		Map nodeMap = parseDrawXml(transitionSet);

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
					.valueOf(new Integer(width).intValue() + 10);

			String height = pNode.attributeValue("h");
			Integer intHeight = Integer
					.valueOf(new Integer(height).intValue() + 10);

			curNewNode.addAttribute("name", pNode.attributeValue("name"));
			curNewNode.addAttribute("g", x + "," + y + "," + intWidth + ","
					+ intHeight);

			newNodeMap.put(id, curNewNode);
		}

		Iterator tranIt = transitionSet.iterator();

		while (tranIt.hasNext()) {
			Element tranEl = (Element) tranIt.next();

			String g = tranEl.attributeValue("g");
			System.out.println("g:" + g);
			String name = tranEl.attributeValue("name");

			Element startNode = (Element) tranEl
					.selectSingleNode("./startConnector/rConnector/Owner/*");
			Element endNode = (Element) tranEl
					.selectSingleNode("./endConnector/rConnector/Owner/*");

			if ((startNode != null) && (endNode != null)) {
				String startRef = startNode.attributeValue("ref");
				String endRef = endNode.attributeValue("ref");
				Element endStartNode;
				Element newStartNode;
				if ((startRef != null) && (endRef != null)) {
					newStartNode = (Element) newNodeMap.get(startRef);
					endStartNode = (Element) newNodeMap.get(endRef);
				} else {
					String startId = startNode.attributeValue("id");
					String endId = startNode.attributeValue("id");
					newStartNode = (Element) newNodeMap.get(startId);
					endStartNode = (Element) newNodeMap.get(endId);
				}
				Element transitionEl = newStartNode.addElement("transition");
				transitionEl.addAttribute("name", name);
				transitionEl.addAttribute("to",
						endStartNode.attributeValue("name"));
				transitionEl.addAttribute("g", g);
			}
		}
	}

	public static Map<String, Element> parseDrawXml(Set transitionSet) {
		Map map = new LinkedHashMap();

		String path = "D:/WorkSpace/WS20110612/eoffice_gov/src/com/cyjt/test/jbpm/NewFile1.xml";

		Document drawDoc = XmlUtil.load(path);
		Element rootEl = drawDoc.getRootElement();
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
