package com.cyjt.oa.model.flow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.model.Transition;


public class Transform {
	private static final Log logger = LogFactory.getLog(Transform.class);
	private String name;
	private String destination;
	private String nodeType;
	private String source;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Transform() {
	}

	public Transform(Transition jbpmtran) {
		try {
			this.name = jbpmtran.getName();
			this.source = jbpmtran.getSource().getName();
			this.nodeType = jbpmtran.getSource().getType();
			this.destination = jbpmtran.getDestination().getName();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	public String getNodeType() {
		return this.nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
}
