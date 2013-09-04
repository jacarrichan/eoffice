package com.palmelf.eoffice.model.hrm;

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
import com.palmelf.eoffice.model.system.FileAttach;

@Entity
@Table(name = "contract_Attach")
public class ContractAttach extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6447222336283099311L;
	private Long fileId;
	private FileAttach fileAttach;
	private UserContract userContract;

	public ContractAttach() {
	}

	public ContractAttach(Long in_fileId) {
		this.setFileId(in_fileId);
	}

	@Id
	@GeneratedValue
	@Column(name = "fileId", unique = true, nullable = false)
	public Long getFileId() {
		return this.fileId;
	}

	public void setFileId(Long aValue) {
		if (aValue == null) {
			this.fileAttach = null;
		} else {
			this.fileAttach = new FileAttach(aValue);
			this.fileAttach.setVersion(new Integer(0));
		}
		this.fileId = aValue;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fileId", insertable = false, updatable = false)
	public FileAttach getFileAttach() {
		return this.fileAttach;
	}

	public void setFileAttach(FileAttach fileAttach) {
		this.fileAttach = fileAttach;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contractId")
	public UserContract getUserContract() {
		return this.userContract;
	}

	public void setUserContract(UserContract userContract) {
		this.userContract = userContract;
	}

	@Transient
	public Long getContractId() {
		return this.getUserContract() == null ? null : this.getUserContract()
				.getContractId();
	}

	public void setContractId(Long aValue) {
		if (aValue == null) {
			this.userContract = null;
		} else if (this.userContract == null) {
			this.userContract = new UserContract(aValue);
			this.userContract.setVersion(new Integer(0));
		} else {
			this.userContract.setContractId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ContractAttach)) {
			return false;
		}
		ContractAttach rhs = (ContractAttach) object;
		return new EqualsBuilder().append(this.fileId, rhs.fileId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.fileId)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fileId", this.fileId)
				.toString();
	}
}
