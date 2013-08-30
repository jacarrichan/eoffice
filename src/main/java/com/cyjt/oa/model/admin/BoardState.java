package com.cyjt.oa.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "boardstate")
public class BoardState extends BaseModel {

	private static final long serialVersionUID = 7664191567863333467L;
	private Integer stateId;
	private String stateName;

	public BoardState() {
	}

	public BoardState(Integer in_stateId) {
		this.stateId = in_stateId;
	}

	@Id
	@GeneratedValue
	@Column(name = "stateId", unique = true, nullable = false)
	public Integer getStateId() {
		return this.stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	@Column(name = "stateName", nullable = false, length = 128)
	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.stateId).append(this.stateName).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BoardType)) {
			return false;
		}
		BoardState bs = (BoardState) obj;
		return new EqualsBuilder().append(this.stateId, bs.stateId).append(this.stateName, bs.stateName).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("stateId", this.stateId).append("stateName", this.stateName).toString();
	}
}
