package com.palmelf.eoffice.model.arch;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.AppUser;

@Entity
@Table(name = "borrow_record")
public class BorrowRecord extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6517521725959944520L;

	@Expose
	private String borrowRemark;

	@Expose
	private Long checkId;

	@Expose
	private String checkName;

	@Expose
	private String checkContent;

	@Expose
	private Long recordId;

	@Expose
	private Date borrowDate;

	@Expose
	private String borrowType;

	@Expose
	private String borrowReason;

	@Expose
	private String checkUserName;

	@Expose
	private Date checkDate;

	@Expose
	private Date returnDate;

	@Expose
	private Short returnStatus;

	@Expose
	private String borrowNum;

	@Expose
	private AppUser appUser;
	private Set<BorrowFileList> borrowFileLists = new HashSet<BorrowFileList>();

	public BorrowRecord() {
	}

	public BorrowRecord(Long in_recordId) {
		this.setRecordId(in_recordId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "recordId", unique = true, nullable = false)
	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checkUserId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "borrowRemark", length = 128)
	public String getBorrowRemark() {
		return this.borrowRemark;
	}

	public void setBorrowRemark(String borrowRemark) {
		this.borrowRemark = borrowRemark;
	}

	@Column(name = "checkId")
	public Long getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}

	@Column(name = "checkName", length = 64)
	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	@Column(name = "checkContent", length = 128)
	public String getCheckContent() {
		return this.checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	@Column(name = "borrowDate", length = 19)
	public Date getBorrowDate() {
		return this.borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	@Column(name = "borrowType", length = 64)
	public String getBorrowType() {
		return this.borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}

	@Column(name = "borrowReason", length = 64)
	public String getBorrowReason() {
		return this.borrowReason;
	}

	public void setBorrowReason(String borrowReason) {
		this.borrowReason = borrowReason;
	}

	@Column(name = "checkUserName", length = 64)
	public String getCheckUserName() {
		return this.checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	@Column(name = "checkDate", length = 19)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "returnDate", length = 19)
	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Column(name = "returnStatus")
	public Short getReturnStatus() {
		return this.returnStatus;
	}

	public void setReturnStatus(Short returnStatus) {
		this.returnStatus = returnStatus;
	}

	@Column(name = "borrowNum", length = 128)
	public String getBorrowNum() {
		return this.borrowNum;
	}

	public void setBorrowNum(String borrowNum) {
		this.borrowNum = borrowNum;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "borrowRecord")
	public Set<BorrowFileList> getBorrowFileLists() {
		return this.borrowFileLists;
	}

	public void setBorrowFileLists(Set<BorrowFileList> borrowFileLists) {
		this.borrowFileLists = borrowFileLists;
	}

	@Transient
	public Long getCheckUserId() {
		return this.getAppUser() == null ? null : this.getAppUser().getUserId();
	}

	public void setCheckUserId(Long aValue) {
		if (aValue == null) {
			this.appUser = null;
		} else if (this.appUser == null) {
			this.appUser = new AppUser(aValue);
			this.appUser.setVersion(new Integer(0));
		} else {
			this.appUser.setUserId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof BorrowRecord)) {
			return false;
		}
		BorrowRecord rhs = (BorrowRecord) object;
		return new EqualsBuilder().append(this.recordId, rhs.recordId)
				.append(this.borrowRemark, rhs.borrowRemark)
				.append(this.checkId, rhs.checkId)
				.append(this.checkName, rhs.checkName)
				.append(this.checkContent, rhs.checkContent)
				.append(this.borrowDate, rhs.borrowDate)
				.append(this.borrowType, rhs.borrowType)
				.append(this.borrowReason, rhs.borrowReason)
				.append(this.checkUserName, rhs.checkUserName)
				.append(this.checkDate, rhs.checkDate)
				.append(this.returnDate, rhs.returnDate)
				.append(this.returnStatus, rhs.returnStatus)
				.append(this.borrowNum, rhs.borrowNum).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.recordId)
				.append(this.borrowRemark).append(this.checkId)
				.append(this.checkName).append(this.checkContent)
				.append(this.borrowDate).append(this.borrowType)
				.append(this.borrowReason).append(this.checkUserName)
				.append(this.checkDate).append(this.returnDate)
				.append(this.returnStatus).append(this.borrowNum).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("recordId", this.recordId)
				.append("borrowRemark", this.borrowRemark)
				.append("checkId", this.checkId)
				.append("checkName", this.checkName)
				.append("checkContent", this.checkContent)
				.append("borrowDate", this.borrowDate)
				.append("borrowType", this.borrowType)
				.append("borrowReason", this.borrowReason)
				.append("checkUserName", this.checkUserName)
				.append("checkDate", this.checkDate)
				.append("returnDate", this.returnDate)
				.append("returnStatus", this.returnStatus)
				.append("borrowNum", this.borrowNum).toString();
	}
}
