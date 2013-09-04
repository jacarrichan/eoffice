package com.palmelf.eoffice.model.admin;

import java.sql.Date;

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
@Table(name = "goods_apply")
public class GoodsApply extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4754768018318356354L;
	public static Short PASS_APPLY = 1;
	public static Short NOTPASS_APPLY = 2;
	public static Short INIT_APPLY = 0;
	private Long applyId;
	private Date applyDate;
	private String applyNo;
	private Integer useCounts;
	private String proposer;
	private Long userId;
	private String notes;
	private Short approvalStatus;
	private OfficeGoods officeGoods;

	public GoodsApply() {
	}

	public GoodsApply(Long in_applyId) {
		this.setApplyId(in_applyId);
	}
	@Transient
	public Long getGoodsId() {
		return this.getOfficeGoods() == null ? null : this.getOfficeGoods().getGoodsId();
	}

	public void setGoodsId(Long aValue) {
		if (aValue == null) {
			this.officeGoods = null;
		} else if (this.officeGoods == null) {
			this.officeGoods = new OfficeGoods(aValue);
			this.officeGoods.setVersion(new Integer(0));
		} else {
			this.officeGoods.setGoodsId(aValue);
		}
	}

	@Id
	@GeneratedValue
	@Column(name = "applyId", unique = true, nullable = false)
	public Long getApplyId() {
		return this.applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsId")
	public OfficeGoods getOfficeGoods() {
		return this.officeGoods;
	}

	public void setOfficeGoods(OfficeGoods officeGoods) {
		this.officeGoods = officeGoods;
	}

	@Column(name = "applyDate", nullable = false, length = 19)
	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "applyNo", nullable = false, length = 128)
	public String getApplyNo() {
		return this.applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	@Column(name = "useCounts", nullable = false)
	public Integer getUseCounts() {
		return this.useCounts;
	}

	public void setUseCounts(Integer useCounts) {
		this.useCounts = useCounts;
	}

	@Column(name = "proposer", nullable = false, length = 32)
	public String getProposer() {
		return this.proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	@Column(name = "notes", length = 500)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "approvalStatus", nullable = false)
	public Short getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(Short approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof GoodsApply)) {
			return false;
		}
		GoodsApply rhs = (GoodsApply) object;
		return new EqualsBuilder().append(this.applyId, rhs.applyId).append(this.applyDate, rhs.applyDate)
				.append(this.applyNo, rhs.applyNo).append(this.useCounts, rhs.useCounts)
				.append(this.userId, rhs.userId).append(this.proposer, rhs.proposer).append(this.notes, rhs.notes)
				.append(this.approvalStatus, rhs.approvalStatus).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.applyId).append(this.applyDate)
				.append(this.applyNo).append(this.useCounts).append(this.proposer).append(this.userId)
				.append(this.notes).append(this.approvalStatus).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("applyId", this.applyId).append("applyDate", this.applyDate)
				.append("applyNo", this.applyNo).append("useCounts", this.useCounts).append("proposer", this.proposer)
				.append("userId", this.userId).append("notes", this.notes)
				.append("approvalStatus", this.approvalStatus).toString();
	}
}
