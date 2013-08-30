package com.cyjt.oa.model.flow;

public class ExtFormItem {
	private String name;
	private String xtype;
	private boolean allowBlank;
	private String fieldLabel;
	private String format;
	private int maxLength;
	private boolean hidden;

	public String getIsShowed() {
		if (this.hidden) {
			return "false";
		}
		return "true";
	}

	public String getType() {
		if (("textfield".equals(this.xtype)) || ("textarea".equals(this.xtype))) {
			return "varchar";
		}
		if ("datefield".equals(this.xtype)) {
			return "date";
		}
		if ("radio".equals(this.xtype)) {
			return "varchar";
		}
		if ("checkbox".equals(this.xtype)) {
			return "varchar";
		}
		if ("numberfield".equals(this.xtype)) {
			return "decimal";
		}

		return "varchar";
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXtype() {
		return this.xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public boolean isAllowBlank() {
		return this.allowBlank;
	}

	public void setAllowBlank(boolean allowBlank) {
		this.allowBlank = allowBlank;
	}

	public String getFieldLabel() {
		return this.fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public boolean isHidden() {
		return this.hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
}
