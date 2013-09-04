/**
* <p>Title: Album</p>
* <p>Description: Album</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: RedAntSoft.com</p>
* <p>@author: RedAntSoft</p>
* <p>@version 1.0beta </p>
**/

package com.palmelf.eoffice.action.album;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.BeanUtil;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.album.Album;
import com.palmelf.eoffice.model.arch.ArchRoll;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.album.AlbumService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class AlbumAction extends BaseAction {

	@Resource
	private AlbumService albumService;
	private Album album;
	private Long albumId;

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<Album> list = this.albumService.getAll(filter);
		Type type = new TypeToken<List<Album>>() {}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.albumService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		Long albumId = Long.valueOf(this.getRequest().getParameter("albumId"));
		this.setAlbum(this.albumService.get(albumId));
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(this.album));
		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		AppUser user = ContextUtil.getCurrentUser();
		album.setUserId(Long.toString(user.getUserId()));
		if (this.album.getAlbumId() == null) {
			this.albumService.save(this.album);
		} else {
			Album album = this.albumService.get(this.album.getAlbumId());
			try {
				BeanUtil.copyNotNullProperties(album, this.album);
				this.albumService.save(album);
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return "success";
	}









//----------------geter seter----------------------
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}

}
