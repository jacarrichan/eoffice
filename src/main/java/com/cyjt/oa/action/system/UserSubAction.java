package com.cyjt.oa.action.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.UserSub;
import com.cyjt.oa.service.system.AppUserService;
import com.cyjt.oa.service.system.UserSubService;


import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class UserSubAction extends BaseAction {

	@Resource
	private UserSubService userSubService;
	private UserSub userSub;

	@Resource
	private AppUserService appUserService;
	private Long subId;

	public Long getSubId() {
		return this.subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public UserSub getUserSub() {
		return this.userSub;
	}

	public void setUserSub(UserSub userSub) {
		this.userSub = userSub;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.addFilter("Q_subAppUser.delFlag_SN_EQ", "0");
		List list = this.userSubService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "subAppUser.accessionTime" });
		buff.append(serializer.exclude(
				new String[] { "subAppUser.password", "class" })
				.serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String my() {
		String userId = this.getRequest().getParameter("userId");
		System.out.println("userId~~~" + userId);
		List<UserSub> mySubList = this.userSubService.findByUser(new Long(
				userId));
		StringBuffer buff = new StringBuffer("{success:true,result:[");
		for (UserSub sub : mySubList) {
			buff.append("{userId:").append(sub.getSubAppUser().getUserId());
			buff.append(",fullname:'")
					.append(sub.getSubAppUser().getFullname()).append("'")
					.append(",title:'").append(sub.getSubAppUser().getTitle())
					.append("'},");
		}
		if (mySubList.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String combo() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		List<UserSub> list = this.userSubService.getAll(filter);
		StringBuffer buff = new StringBuffer("[");
		for (UserSub sub : list) {
			buff.append("['" + sub.getSubAppUser().getUserId().toString()
					+ "','" + sub.getSubAppUser().getFullname() + "'],");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.userSubService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		UserSub userSub = this.userSubService.get(this.subId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(userSub));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String subUserIds = this.getRequest().getParameter("subUserIds");
		String[] strSubUserId = subUserIds.split(",");
		for (String element : strSubUserId) {
			UserSub usb = new UserSub();
			usb.setUserId(ContextUtil.getCurrentUserId());
			Long subUserId = new Long(element);
			AppUser subAppUser = this.appUserService.get(subUserId);
			usb.setSubAppUser(subAppUser);
			this.userSubService.save(usb);
		}
		this.setJsonString("{success:true}");
		return "success";
	}

	public String addMy() {
		String userId = this.getRequest().getParameter("userId");
		System.out.println("~~~userId:" + userId);
		String userSubId = this.getRequest().getParameter("subUserIds");
		AppUser user = this.appUserService.get(new Long(userId));
		this.userSubService.delSubUser(user.getUserId());
		if (StringUtils.isNotEmpty(userSubId)) {
			String[] uIds = userSubId.split("[,]");
			if (uIds != null) {
				for (String uId : uIds) {
					AppUser grantUserSub = this.appUserService
							.get(new Long(uId));
					UserSub subUser = new UserSub();
					subUser.setUserId(user.getUserId());
					subUser.setSubAppUser(grantUserSub);
					this.userSubService.save(subUser);
				}
			}
		}
		this.setJsonString("{sucess:true}");
		return "success";
	}
}
