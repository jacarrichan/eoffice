package com.cyjt.oa.action.communicate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.communicate.MobileMsg;
import com.cyjt.oa.service.communicate.MobileMsgService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public class MobileMsgAction extends BaseAction {

	@Resource
	private MobileMsgService mobileMsgService;
	private MobileMsg mobileMsg;
	private Long msgId;

	public Long getMsgId() {
		return this.msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public MobileMsg getMobileMsg() {
		return this.mobileMsg;
	}

	public void setMobileMsg(MobileMsg mobileMsg) {
		this.mobileMsg = mobileMsg;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<MobileMsg> list = this.mobileMsgService.getAll(filter);

		Type type = new TypeToken<List<MobileMsg>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

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
				this.mobileMsgService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		MobileMsg mobileMsg = this.mobileMsgService.get(this.msgId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(mobileMsg));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.mobileMsgService.save(this.mobileMsg);
		setJsonString("{success:true}");
		return "success";
	}
}
