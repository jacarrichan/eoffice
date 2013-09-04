package com.palmelf.eoffice.model.customer;

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

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "cus_connection")
public class CusConnection extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8807229433077030174L;
	private Long connId;
	private String contactor;
	private Date startDate;
	private Date endDate;
	private String content;
	private String notes;
	private String creator;
	private Customer customer;

	public CusConnection() {
	}

	public CusConnection(Long in_connId) {
		this.setConnId(in_connId);
	}

	@Id
	@GeneratedValue
	@Column(name = "connId", unique = true, nullable = false)
	public Long getConnId() {
		return this.connId;
	}

	public void setConnId(Long connId) {
		this.connId = connId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "contactor", nullable = false, length = 32)
	public String getContactor() {
		return this.contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	@Column(name = "startDate", nullable = false, length = 19)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "endDate", nullable = false, length = 19)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "content", nullable = false, length = 512)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "notes", length = 1000)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "creator", nullable = false, length = 32)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Transient
	public Long getCustomerId() {
		return this.getCustomer() == null ? null : this.getCustomer().getCustomerId();
	}

	public void setCustomerId(Long aValue) {
		if (aValue == null) {
			this.customer = null;
		} else if (this.customer == null) {
			this.customer = new Customer(aValue);
			this.customer.setVersion(new Integer(0));
		} else {
			this.customer.setCustomerId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CusConnection)) {
			return false;
		}
		CusConnection rhs = (CusConnection) object;
		return new EqualsBuilder().append(this.connId, rhs.connId).append(this.contactor, rhs.contactor)
				.append(this.startDate, rhs.startDate).append(this.endDate, rhs.endDate)
				.append(this.content, rhs.content).append(this.notes, rhs.notes).append(this.creator, rhs.creator)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.connId).append(this.contactor)
				.append(this.startDate).append(this.endDate).append(this.content).append(this.notes)
				.append(this.creator).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("connId", this.connId).append("contactor", this.contactor)
				.append("startDate", this.startDate).append("endDate", this.endDate).append("content", this.content)
				.append("notes", this.notes).append("creator", this.creator).toString();
	}
}
