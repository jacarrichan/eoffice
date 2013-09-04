package com.palmelf.core.jbpm.jpdl;

import java.awt.Point;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;

import com.palmelf.core.util.XmlUtil;


public class JpdlModel {
	private final Map<String, Node> nodes = new LinkedHashMap<String, Node>();
	private Set<String> activityNames = new HashSet<String>();
	public Set<String> getActivityNames() {
		return activityNames;
	}

	public void setActivityNames(Set<String> activityNames) {
		this.activityNames = activityNames;
	}

	public static final int RECT_OFFSET_X = -7;
	public static final int RECT_OFFSET_Y = -8;
	public static final int DEFAULT_PIC_SIZE = 48;

	private final static Map<String, Object> nodeInfos = new HashMap<String, Object>();
	static {
		nodeInfos.put("start", "start_event_empty.png");
		nodeInfos.put("end", "end_event_terminate.png");
		nodeInfos.put("end-cancel", "end_event_cancel.png");
		nodeInfos.put("end-error", "end_event_error.png");
		nodeInfos.put("decision", "gateway_exclusive.png");
		nodeInfos.put("fork", "gateway_parallel.png");
		nodeInfos.put("join", "gateway_parallel.png");
		nodeInfos.put("state", null);
		nodeInfos.put("hql", null);
		nodeInfos.put("sql", null);
		nodeInfos.put("java", null);
		nodeInfos.put("script", null);
		nodeInfos.put("task", null);
		nodeInfos.put("sub-process", null);
		nodeInfos.put("custom", null);
	}

	public JpdlModel(String defXml) throws Exception {
		this(XmlUtil.stringToDocument(defXml).getRootElement());
	}

	public JpdlModel(InputStream is) throws Exception {
		this(XmlUtil.load(is).getRootElement());
	}

	@SuppressWarnings("unchecked")
	private JpdlModel(Element rootEl) throws Exception {
		for (final Element el : (List<Element>) rootEl.elements()) {
			final String type = el.getQName().getName();
			if (!nodeInfos.containsKey(type)) { // 不是可展示的节点
				continue;
			}
			String name = null;
			if (el.attribute("name") != null) {
				name = el.attributeValue("name");
			}
			final String[] location = el.attributeValue("g").split(",");
			int x = Integer.parseInt(location[0]);
			int y = Integer.parseInt(location[1]);
			int w = Integer.parseInt(location[2]);
			int h = Integer.parseInt(location[3]);

			if (nodeInfos.get(type) != null) {
				w = DEFAULT_PIC_SIZE;
				h = DEFAULT_PIC_SIZE;
			} else {
				x -= RECT_OFFSET_X;
				y -= RECT_OFFSET_Y;
				w += (RECT_OFFSET_X + RECT_OFFSET_X);
				h += (RECT_OFFSET_Y + RECT_OFFSET_Y);
			}
			final Node node = new Node(name, type, x, y, w, h);
			this.parserTransition(node, el);
			this.nodes.put(name, node);
		}
	}

	@SuppressWarnings("unchecked")
	private void parserTransition(Node node, Element nodeEl) {
		for (final Element el : (List<Element>) nodeEl.elements("transition")) {
			final String label = el.attributeValue("name");
			final String to = el.attributeValue("to");
			final Transition transition = new Transition(label, to);
			final String g = el.attributeValue("g");
			if ((g != null) && (g.length() > 0)) {
				if (g.indexOf(":") < 0) {
					transition.setLabelPosition(this.getPoint(g));
				} else {
					final String[] p = g.split(":");
					transition.setLabelPosition(this.getPoint(p[1]));
					final String[] lines = p[0].split(";");
					for (final String line : lines) {
						transition.addLineTrace(this.getPoint(line));
					}
				}
			}
			node.addTransition(transition);
		}
	}

	private Point getPoint(String exp) {
		if ((exp == null) || (exp.length() == 0)) {
			return null;
		}
		final String[] p = exp.split(",");
		return new Point(Integer.valueOf(p[0]), Integer.valueOf(p[1]));
	}

	/**
	 * @return the nodes
	 */
	public Map<String, Node> getNodes() {
		return this.nodes;
	}

	/**
	 * @return the nodeInfos
	 */
	public static Map<String, Object> getNodeInfos() {
		return nodeInfos;
	}

	@Override
	public String toString() {
		final StringBuffer buf = new StringBuffer();
		buf.append("JpdlModel ( \n");
		buf.append(" nodes = ").append(this.nodes).append("\n");
		buf.append(")");
		return buf.toString();
	}

}
