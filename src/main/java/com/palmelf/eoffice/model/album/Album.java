/**
* <p>Title: Album</p>
* <p>Description: Album</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: RedAntSoft.com</p>
* <p>@author: RedAntSoft</p>
* <p>@version 1.0beta </p>
**/


package com.palmelf.eoffice.model.album;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="album")
public class Album implements Serializable {
	@Id 
	@GeneratedValue 
	@Column(name = "albumId", unique = true, nullable = false)
	private Long albumId;
	@Column(name="name",length=255)
	private String name;
	@Column(name="userId",length=20)
	private String userId;
	@Column(name="facePicPath",length=255)
	private String facePicPath;
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFacePicPath() {
		return facePicPath;
	}
	public void setFacePicPath(String facePicPath) {
		this.facePicPath = facePicPath;
	}

}
