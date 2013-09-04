package com.palmelf.core.jbpm.pv;

public class ParamField {
	public static final String FIELD_TYPE_DATE = "date";
	public static final String FIELD_TYPE_DATETIME = "datetime";
	public static final String FIELD_TYPE_INT = "int";
	public static final String FIELD_TYPE_LONG = "long";
	public static final String FIELD_TYPE_DECIMAL = "decimal";
	public static final String FIELD_TYPE_VARCHAR = "varchar";
	public static final String FIELD_TYPE_BOOL = "bool";
	public static final String FIELD_TYPE_TEXT = "text";
	public static final String FIELD_TYPE_FILE = "file";
	private String name;
	private String type;
	private String label;
	private Integer length;
	private Short isShowed = 1;
	private String value;

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		if ("bool".equals(this.type)) {
			if (value != null)
				this.value = "1";
			else
				this.value = "0";
		} else
			this.value = value;
	}

	public ParamField(String name, String type, String label, Integer length,
			Short isShowed) {
		this.name = name;
		this.type = type;
		this.label = label;
		this.length = length;
		this.isShowed = isShowed;
	}

	public ParamField() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Short getIsShowed() {
		return this.isShowed;
	}

	public void setIsShowed(Short isShowed) {
		this.isShowed = isShowed;
	}
}
