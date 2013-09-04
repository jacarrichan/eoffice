package com.palmelf.eoffice.model.flow;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.time.DateUtils;

import com.google.gson.annotations.Expose;
import com.palmelf.core.jbpm.pv.ParamField;
import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "form_data")
public class FormData extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8892599330197386809L;

	public static final Short SHOWED = 1;

	public static final Short UNSHOWED = 0;

	@Expose
	private Long dataId;
	private String fieldLabel;
	private String fieldName;
	private Integer intValue;
	private Long longValue;
	private BigDecimal decValue;
	private Date dateValue;
	private String strValue;
	private String blobValue;
	private Short boolValue;
	private String textValue;
	private Short isShowed = 1;
	private String fieldType;
	private ProcessForm processForm;

	public FormData(ParamField pf) {
		this.copyValue(pf);
	}

	public void copyValue(ParamField pf) {
		this.fieldLabel = pf.getLabel();
		this.fieldName = pf.getName();
		this.fieldType = pf.getType();
		this.isShowed = pf.getIsShowed();

		this.setValue(pf.getValue(), pf.getType());
	}
	@Transient
	public Object getValue() {
		if (this.strValue != null) {
			return this.strValue;
		}
		if (this.intValue != null) {
			return this.intValue;
		}
		if (this.longValue != null) {
			return this.longValue;
		}
		if (this.decValue != null) {
			return this.decValue;
		}
		if (this.dateValue != null) {
			return this.dateValue;
		}
		if (this.boolValue != null) {
			return this.boolValue;
		}
		if (this.textValue != null) {
			return this.textValue;
		}
		return null;
	}
	@Transient
	public String getVal() {
		if ("varchar".equals(this.fieldType)) {
			return this.strValue;
		}

		if ("date".equals(this.fieldType)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return this.dateValue == null ? null : sdf.format(this.dateValue);
		}

		if ("datetime".equals(this.fieldType)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return this.dateValue == null ? null : sdf.format(this.dateValue);
		}

		if ("int".equals(this.fieldType)) {
			return this.intValue == null ? null : this.intValue.toString();
		}

		if ("long".equals(this.fieldType)) {
			return this.longValue == null ? null : this.longValue.toString();
		}

		if ("decimal".equals(this.fieldType)) {
			return this.decValue == null ? null : this.decValue.toString();
		}

		if ("text".equals(this.fieldType)) {
			return this.textValue;
		}

		if ("file".equals(this.fieldType)) {
			return this.strValue;
		}

		if ("bool".equals(this.fieldType)) {
			return this.boolValue.shortValue() == 1 ? "是" : "否";
		}

		return null;
	}

	public void setValue(String val, String type) {
		if (val == null) {
			return;
		}
		try {
			if ("varchar".equals(type)) {
				this.strValue = val;
			} else if ("bool".equals(type)) {
				this.boolValue = (short) ("1".equals(val) ? 1 : 0);
			} else if (("date".equals(type)) || ("datetime".equals(type))) {
				this.dateValue = DateUtils.parseDate(val, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" });
			} else if ("decimal".equals(type)) {
				this.decValue = new BigDecimal(val);
			} else if ("int".equals(type)) {
				this.intValue = new Integer(val);
			} else if ("long".equals(type)) {
				this.longValue = new Long(val);
			} else if ("text".equals(type)) {
				this.textValue = val;
			} else if ("file".equals(type)) {
				this.strValue = val;
			}
		} catch (Exception ex) {
			this.logger.warn("setValue error:" + ex.getMessage());
		}
	}

	public FormData() {
	}

	public FormData(Long in_dataId) {
		this.setDataId(in_dataId);
	}

	@Id
	@GeneratedValue
	@Column(name = "dataId", unique = true, nullable = false)
	public Long getDataId() {
		return this.dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formId")
	public ProcessForm getProcessForm() {
		return this.processForm;
	}

	public void setProcessForm(ProcessForm processForm) {
		this.processForm = processForm;
	}

	@Column(name = "fieldLabel", nullable = false, length = 128)
	public String getFieldLabel() {
		return this.fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	@Column(name = "fieldName", nullable = false, length = 64)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "intValue")
	public Integer getIntValue() {
		return this.intValue;
	}

	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}

	@Column(name = "longValue")
	public Long getLongValue() {
		return this.longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	@Column(name = "decValue", precision = 18, scale = 4)
	public BigDecimal getDecValue() {
		return this.decValue;
	}

	public void setDecValue(BigDecimal decValue) {
		this.decValue = decValue;
	}

	@Column(name = "dateValue", length = 19)
	public Date getDateValue() {
		return this.dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	@Column(name = "strValue", length = 5000)
	public String getStrValue() {
		return this.strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	@Column(name = "blobValue")
	public String getBlobValue() {
		return this.blobValue;
	}

	public void setBlobValue(String blobValue) {
		this.blobValue = blobValue;
	}

	@Column(name = "boolValue")
	public Short getBoolValue() {
		return this.boolValue;
	}

	public void setBoolValue(Short boolValue) {
		this.boolValue = boolValue;
	}

	@Column(name = "textValue")
	public String getTextValue() {
		return this.textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	@Column(name = "fieldType", nullable = false, length = 32)
	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "isShowed", nullable = false)
	public Short getIsShowed() {
		return this.isShowed;
	}

	public void setIsShowed(Short isShowed) {
		this.isShowed = isShowed;
	}
	@Transient
	public Long getFormId() {
		return this.getProcessForm() == null ? null : this.getProcessForm().getFormId();
	}

	public void setFormId(Long aValue) {
		if (aValue == null) {
			this.processForm = null;
		} else if (this.processForm == null) {
			this.processForm = new ProcessForm(aValue);
			this.processForm.setVersion(new Integer(0));
		} else {
			this.processForm.setFormId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof FormData)) {
			return false;
		}
		FormData rhs = (FormData) object;
		return new EqualsBuilder().append(this.dataId, rhs.dataId).append(this.fieldLabel, rhs.fieldLabel)
				.append(this.fieldName, rhs.fieldName).append(this.intValue, rhs.intValue)
				.append(this.decValue, rhs.decValue).append(this.dateValue, rhs.dateValue)
				.append(this.strValue, rhs.strValue).append(this.blobValue, rhs.blobValue)
				.append(this.isShowed, rhs.isShowed).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.dataId).append(this.fieldLabel)
				.append(this.fieldName).append(this.intValue).append(this.decValue).append(this.dateValue)
				.append(this.strValue).append(this.blobValue).append(this.isShowed).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("dataId", this.dataId).append("fieldLabel", this.fieldLabel)
				.append("fieldName", this.fieldName).append("intValue", this.intValue)
				.append("decValue", this.decValue).append("dateValue", this.dateValue)
				.append("strValue", this.strValue).append("blobValue", this.blobValue)
				.append("isShowed", this.isShowed).toString();
	}
}
