/**
 * <p>Title: Picture</p>
 * <p>Description: Picture</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: RedAntSoft.com</p>
 * <p>@author: RedAntSoft</p>
 * <p>@version 1.0beta </p>
 **/

package com.palmelf.eoffice.action.picture;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.jfree.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.picture.Picture;
import com.palmelf.eoffice.service.picture.PictureService;

public class PictureAction extends BaseAction {

	@Resource
	private PictureService pictureService;

	private Picture picture;

	private List<Picture> pictures;

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<Picture> list = this.pictureService.getAll(filter);
		Log.info("======list.size()" + list.size());
		Type type = new TypeToken<List<Picture>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.pictureService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		Long picId = Long.valueOf(this.getRequest().getParameter("picId"));
		this.setPicture(this.pictureService.get(picId));
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(this.picture));
		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		this.pictureService.save(this.picture);
		
		for (Picture p : pictures) {
			this.pictureService.save(p);
        }
		this.setJsonString("{success:true}");
		return "success";
	}

	public PictureService getPictureService() {
		return this.pictureService;
	}

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	public Picture getPicture() {
		return this.picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public List<Picture> getPictures() {
		return this.pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
}
