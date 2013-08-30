package com.cyjt.oa.model.admin;

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

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "book_bor_ret")
public class BookBorRet extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2962211267207980703L;
	private Long recordId;
	private Date borrowTime;
	private Date returnTime;
	private Date lastReturnTime;
	private String borrowIsbn;
	private String bookName;
	private String registerName;
	private String fullname;
	private BookSn bookSn;

	public BookBorRet() {
	}

	public BookBorRet(Long in_recordId) {
		this.setRecordId(in_recordId);
	}
	@Transient
	public Long getBookSnId() {
		return this.getBookSn() == null ? null : this.getBookSn().getBookSnId();
	}

	public void setBookSnId(Long aValue) {
		if (aValue == null) {
			this.bookSn = null;
		} else if (this.bookSn == null) {
			this.bookSn = new BookSn(aValue);
			this.bookSn.setVersion(new Integer(0));
		} else {
			this.bookSn.setBookSnId(aValue);
		}
	}

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
	@JoinColumn(name = "bookSnId")
	public BookSn getBookSn() {
		return this.bookSn;
	}

	public void setBookSn(BookSn bookSn) {
		this.bookSn = bookSn;
	}

	@Column(name = "borrowTime", nullable = false, length = 19)
	public Date getBorrowTime() {
		return this.borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	@Column(name = "returnTime", nullable = false, length = 19)
	public Date getReturnTime() {
		return this.returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	@Column(name = "lastReturnTime", length = 19)
	public Date getLastReturnTime() {
		return this.lastReturnTime;
	}

	public void setLastReturnTime(Date lastReturnTime) {
		this.lastReturnTime = lastReturnTime;
	}

	@Column(name = "borrowIsbn", nullable = false, length = 128)
	public String getBorrowIsbn() {
		return this.borrowIsbn;
	}

	public void setBorrowIsbn(String borrowIsbn) {
		this.borrowIsbn = borrowIsbn;
	}

	@Column(name = "bookName", nullable = false, length = 128)
	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Column(name = "registerName", nullable = false, length = 32)
	public String getRegisterName() {
		return this.registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	@Column(name = "fullname", nullable = false, length = 32)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof BookBorRet)) {
			return false;
		}
		BookBorRet rhs = (BookBorRet) object;
		return new EqualsBuilder().append(this.recordId, rhs.recordId).append(this.borrowTime, rhs.borrowTime)
				.append(this.returnTime, rhs.returnTime).append(this.lastReturnTime, rhs.lastReturnTime)
				.append(this.borrowIsbn, rhs.borrowIsbn).append(this.bookName, rhs.bookName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.recordId).append(this.borrowTime)
				.append(this.returnTime).append(this.lastReturnTime).append(this.borrowIsbn).append(this.bookName)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("recordId", this.recordId).append("borrowTime", this.borrowTime)
				.append("returnTime", this.returnTime).append("lastReturnTime", this.lastReturnTime)
				.append("borrowIsbn", this.borrowIsbn).append("bookName", this.bookName).toString();
	}
}
