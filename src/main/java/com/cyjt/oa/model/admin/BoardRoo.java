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
@Table(name = "boardroo")
public class BoardRoo extends BaseModel {

	private static final long serialVersionUID = 4710322599302697704L;
	private Long roomId;
	private String roomName;
	private String roomDesc;
	private Long containNum;

	@Id
	@GeneratedValue
	@Column(name = "roomId", unique = true, nullable = false)
	public Long getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	@Column(name = "roomName", nullable = false, length = 128)
	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Column(name = "roomDesc", length = 4000)
	public String getRoomDesc() {
		return this.roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	@Column(name = "containNum", nullable = false)
	public Long getContainNum() {
		return this.containNum;
	}

	public void setContainNum(Long containNum) {
		this.containNum = containNum;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BoardRoo)) {
			return false;
		}
		BoardRoo boardRoo = (BoardRoo) obj;
		return new EqualsBuilder().append(this.roomId, boardRoo.roomId).append(this.roomName, boardRoo.roomName)
				.append(this.roomDesc, boardRoo.roomDesc).append(this.containNum, boardRoo.containNum).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.roomId).append(this.roomName)
				.append(this.roomDesc).append(this.containNum).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("roomId", this.roomId).append("roomName", this.roomName)
				.append("roomDesc", this.roomDesc).append("containNum", this.containNum).toString();
	}
}
