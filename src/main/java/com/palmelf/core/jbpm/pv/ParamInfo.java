package com.palmelf.core.jbpm.pv;

import java.io.Serializable;

public class ParamInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String label;
	private String name;
	private Object value;
	private boolean isShow;

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isShow() {
		return this.isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}
}
