/**
* <p>Title: Picture</p>
* <p>Description: Picture</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: RedAntSoft.com</p>
* <p>@author: RedAntSoft</p>
* <p>@version 1.0beta </p>
**/


package com.cyjt.oa.model.picture;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="picture")
public class Picture implements Serializable {
	@Id 
	@GeneratedValue 
	@Column(name = "picId", unique = true, nullable = false)
	private Long picId;
	@Column(name="title",length=255)
	private String title;
	@Column(name="filePath",length=255)
	private String filePath;
	@Column(name="albumId",length=20)
	private String albumId;
	public Long getPicId() {
		return picId;
	}
	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

}
