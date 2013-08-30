package com.cyjt.oa.model.admin;

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

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "book_sn")
public class BookSn extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2572964710339660336L;
	private Long bookSnId;
	private String bookSN;
	private Short status;
	private Book book;
	private Set<BookBorRet> bookBorRets = new HashSet<BookBorRet>();

	public BookSn() {
	}

	public BookSn(Long in_bookSnId) {
		this.setBookSnId(in_bookSnId);
	}
	@Transient
	public Long getBookId() {
		return getBook() == null ? null : getBook().getBookId();
	}

	public void setBookId(Long aValue) {
		if (aValue == null) {
			this.book = null;
		} else if (this.book == null) {
			this.book = new Book(aValue);
			this.book.setVersion(new Integer(0));
		} else {
			this.book.setBookId(aValue);
		}
	}

	@Id
	@GeneratedValue
	@Column(name = "bookSnId", unique = true, nullable = false)
	public Long getBookSnId() {
		return this.bookSnId;
	}

	public void setBookSnId(Long bookSnId) {
		this.bookSnId = bookSnId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookId")
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Column(name = "bookSN", nullable = false, length = 128)
	public String getBookSn() {
		return this.bookSN;
	}

	public void setBookSn(String bookSn) {
		this.bookSN = bookSn;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bookSn")
	public Set<BookBorRet> getBookBorRets() {
		return this.bookBorRets;
	}

	public void setBookBorRets(Set<BookBorRet> bookBorRets) {
		this.bookBorRets = bookBorRets;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof BookSn)) {
			return false;
		}
		BookSn rhs = (BookSn) object;
		return new EqualsBuilder().append(this.bookSnId, rhs.bookSnId).append(this.bookSN, rhs.bookSN)
				.append(this.status, rhs.status).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.bookSnId).append(this.bookSN).append(this.status)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("bookSnId", this.bookSnId).append("bookSN", this.bookSN)
				.append("status", this.status).toString();
	}
}
