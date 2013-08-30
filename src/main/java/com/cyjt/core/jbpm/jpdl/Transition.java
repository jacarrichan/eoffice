package com.cyjt.core.jbpm.jpdl;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Transition {
	private Point labelPosition;
	private List<Point> lineTrace = new LinkedList();
	private String label;
	private String to;

	public Transition(String label, String to) {
		this.label = label;
		this.to = to;
	}

	public void addLineTrace(Point lineTrace) {
		if (lineTrace != null)
			this.lineTrace.add(lineTrace);
	}

	public Point getLabelPosition() {
		return this.labelPosition;
	}

	public void setLabelPosition(Point labelPosition) {
		this.labelPosition = labelPosition;
	}

	public List<Point> getLineTrace() {
		return this.lineTrace;
	}

	public void setLineTrace(List<Point> lineTrace) {
		this.lineTrace = lineTrace;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
