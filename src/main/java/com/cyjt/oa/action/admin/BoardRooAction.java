package com.cyjt.oa.action.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.admin.BoardRoo;
import com.cyjt.oa.service.admin.BoardRooService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public class BoardRooAction extends BaseAction {

	@Resource
	private BoardRooService boardRooService;
	private BoardRoo boardRoo;
	private Long roomId;

	public Long getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public BoardRoo getBoardRoo() {
		return this.boardRoo;
	}

	public void setBoardRoo(BoardRoo boardRoo) {
		this.boardRoo = boardRoo;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<BoardRoo> list = this.boardRooService.getAll(filter);
		Type type = new TypeToken<List<BoardRoo>>() {
		}.getType();
		StringBuffer bf = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		Gson gson = new Gson();
		bf.append(gson.toJson(list, type)).append("}");
		setJsonString(bf.toString());
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.boardRooService.remove(Long.valueOf(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String del() {
		this.boardRooService.remove(this.roomId);
		this.jsonString = "{success:true}";
		return "success";
	}

	@Override
	public String save() {
		this.boardRooService.save(this.boardRoo);
		this.jsonString = "{success:true,msg:'保存成功'}";
		return "success";
	}

	public String get() {
		BoardRoo boardRoo = this.boardRooService.get(this.roomId);
		Gson gson = new Gson();
		StringBuffer bf = new StringBuffer("{success:true,data:");
		bf.append(gson.toJson(boardRoo)).append("}");
		setJsonString(bf.toString());
		return "success";
	}
}
