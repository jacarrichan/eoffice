package com.palmelf.eoffice.model.admin;

import java.math.BigDecimal;
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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "book")
public class Book extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6264332233088368327L;
	private Long bookId;
	private String bookName;
	private String author;
	private String isbn;
	private String publisher;
	private BigDecimal price;
	private String location;
	private String department;
	private Integer amount;
	private Integer leftAmount;
	private BookType bookType;
	private Set<BookSn> bookSns = new HashSet<BookSn>();

	public Book() {
	}

	public Book(Long in_bookId) {
		this.setBookId(in_bookId);
	}

	@Id
	@GeneratedValue
	@Column(name = "bookId", unique = true, nullable = false)
	public Long getBookId() {
		return this.bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	public BookType getBookType() {
		return this.bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	@Column(name = "bookName", nullable = false, length = 128)
	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Column(name = "author", nullable = false, length = 128)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "isbn", nullable = false, length = 64)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "publisher", length = 128)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(name = "price", nullable = false)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "location", nullable = false, length = 128)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "department", nullable = false, length = 64)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "amount", nullable = false)
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Column(name = "leftAmount", nullable = false)
	public Integer getLeftAmount() {
		return this.leftAmount;
	}

	public void setLeftAmount(Integer leftAmount) {
		this.leftAmount = leftAmount;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
	public Set<BookSn> getBookSns() {
		return this.bookSns;
	}

	public void setBookSns(Set<BookSn> bookSns) {
		this.bookSns = bookSns;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Book)) {
			return false;
		}
		Book rhs = (Book) object;
		return new EqualsBuilder().append(this.bookId, rhs.bookId).append(this.bookName, rhs.bookName)
				.append(this.author, rhs.author).append(this.isbn, rhs.isbn).append(this.publisher, rhs.publisher)
				.append(this.price, rhs.price).append(this.location, rhs.location)
				.append(this.department, rhs.department).append(this.amount, rhs.amount).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.bookId).append(this.bookName).append(this.author)
				.append(this.isbn).append(this.publisher).append(this.price).append(this.location)
				.append(this.department).append(this.amount).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("bookId", this.bookId).append("bookName", this.bookName)
				.append("author", this.author).append("isbn", this.isbn).append("publisher", this.publisher)
				.append("price", this.price).append("location", this.location).append("department", this.department)
				.append("amount", this.amount).toString();
	}
}
