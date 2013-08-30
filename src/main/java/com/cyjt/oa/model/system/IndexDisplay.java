package com.cyjt.oa.model.system;

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
@Table(name = "index_display")
public class IndexDisplay extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1538501175466580671L;
	private Long indexId;
	private String portalId;
	private Integer colNum;
	private Integer rowNum;
	private AppUser appUser;

	public IndexDisplay() {
	}

	public IndexDisplay(Long in_indexId) {
		this.setIndexId(in_indexId);
	}

	@Id
	@GeneratedValue
	@Column(name = "indexId", unique = true, nullable = false)
	public Long getIndexId() {
		return this.indexId;
	}

	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "portalId", nullable = false, length = 64)
	public String getPortalId() {
		return this.portalId;
	}

	public void setPortalId(String portalId) {
		this.portalId = portalId;
	}

	@Column(name = "colNum", nullable = false)
	public Integer getColNum() {
		return this.colNum;
	}

	public void setColNum(Integer colNum) {
		this.colNum = colNum;
	}

	@Column(name = "rowsNum", nullable = false)
	public Integer getRowNum() {
		return this.rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	@Transient
	public Long getUserId() {
		return this.getAppUser() == null ? null : this.getAppUser().getUserId();
	}

	public void setUserId(Long aValue) {
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
		if (!(object instanceof IndexDisplay)) {
			return false;
		}
		IndexDisplay rhs = (IndexDisplay) object;
		return new EqualsBuilder().append(this.indexId, rhs.indexId).append(this.portalId, rhs.portalId)
				.append(this.colNum, rhs.colNum).append(this.rowNum, rhs.rowNum).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.indexId).append(this.portalId)
				.append(this.colNum).append(this.rowNum).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("indexId", this.indexId).append("portalId", this.portalId)
				.append("colNum", this.colNum).append("rowNum", this.rowNum).toString();
	}
}
