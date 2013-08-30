package com.cyjt.oa.model.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "department")
public class Department extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9163156446182214934L;

	@Expose
	private Long depId;

	@Expose
	private String depName;

	@Expose
	private String depDesc;

	@Expose
	private Integer depLevel;

	@Expose
	private Long parentId;

	@Expose
	private String path;

	public Department() {
	}

	public Department(Long depId) {
		this.setDepId(depId);
	}

	@Id
	@GeneratedValue
	@Column(name = "depId", unique = true, nullable = false)
	public Long getDepId() {
		return this.depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	@Column(name = "depName")
	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	@Column(name = "depDesc")
	public String getDepDesc() {
		return this.depDesc;
	}

	public void setDepDesc(String depDesc) {
		this.depDesc = depDesc;
	}

	@Column(name = "depLevel")
	public Integer getDepLevel() {
		return this.depLevel;
	}

	public void setDepLevel(Integer depLevel) {
		this.depLevel = depLevel;
	}

	@Column(name = "parentId")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "path")
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
