package com.cyjt.oa.action.archive;

import com.google.gson.Gson;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.archive.ArchDispatch;
import com.cyjt.oa.model.archive.Archives;
import com.cyjt.oa.model.archive.ArchivesDep;
import com.cyjt.oa.model.system.AppRole;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.archive.ArchDispatchService;
import com.cyjt.oa.service.archive.ArchivesDepService;
import com.cyjt.oa.service.archive.ArchivesService;
import com.cyjt.oa.service.system.AppRoleService;
import com.cyjt.oa.service.system.AppUserService;
import flexjson.JSONSerializer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class ArchDispatchAction extends BaseAction {

	@Resource
	private ArchDispatchService archDispatchService;
	private ArchDispatch archDispatch;

	@Resource
	private ArchivesService archivesService;

	@Resource
	private AppUserService appUserService;

	@Resource
	private AppRoleService appRoleService;

	@Resource
	private ArchivesDepService archivesDepService;
	private String dispatchId;
	private String archivesId;
	private Short archUserType;
	private String readFeedback;

	public Short getArchUserType() {
		return this.archUserType;
	}

	public void setArchUserType(Short archUserType) {
		this.archUserType = archUserType;
	}

	public String getReadFeedback() {
		return this.readFeedback;
	}

	public void setReadFeedback(String readFeedback) {
		this.readFeedback = readFeedback;
	}

	public String getArchivesId() {
		return this.archivesId;
	}

	public void setArchivesId(String archivesId) {
		this.archivesId = archivesId;
	}

	public String getDispatchId() {
		return this.dispatchId;
	}

	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}

	public ArchDispatch getArchDispatch() {
		return this.archDispatch;
	}

	public void setArchDispatch(ArchDispatch archDispatch) {
		this.archDispatch = archDispatch;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		List list = this.archDispatchService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] {
				"dispatchTime", "archives.issueDate", "archives.createtime" });
		buff.append(serializer.exclude(new String[] { "class" })
				.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String disList() {
		PagingBean pb = getInitPagingBean();
		List list = this.archDispatchService.findByUser(
				ContextUtil.getCurrentUser(), pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");

		JSONSerializer serializer = JsonUtil
				.getJSONSerializer(new String[] { "dispatchTime" });
		buff.append(serializer.exclude(new String[] { "class" })
				.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.archDispatchService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArchDispatch archDispatch = this.archDispatchService
				.get(Long.parseLong(this.dispatchId));

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(archDispatch));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		Archives archives = this.archivesService.get(Long.parseLong(this.archivesId));
		if (archives != null) {
			ArchDispatch archDispatch = new ArchDispatch();
			AppUser user = ContextUtil.getCurrentUser();
			archDispatch.setArchives(archives);
			archDispatch.setArchUserType(this.archUserType);
			archDispatch.setUserId(user.getUserId());
			archDispatch.setFullname(user.getFullname());
			archDispatch.setDispatchTime(new Date());
			archDispatch.setSubject(archives.getSubject());
			archDispatch.setIsRead(ArchDispatch.HAVE_READ);
			archDispatch.setReadFeedback(this.readFeedback);
			this.archDispatchService.save(archDispatch);

			if (this.archUserType.compareTo(ArchDispatch.IS_DISPATCH) == 0) {
				archives.setStatus(Archives.STATUS_READ);
				this.archivesService.save(archives);
			} else {
				String signUserIds = getRequest().getParameter(
						"undertakeUserIds");
				String cruArchDepId = getRequest().getParameter("cruArchDepId");
				if (StringUtils.isNotEmpty(signUserIds)) {
					String[] signId = signUserIds.split(",");
					int size = signId.length;
					if (size < 2) {
						if ((this.archUserType
								.compareTo(ArchDispatch.IS_UNDERTAKE) == 0)
								&& (StringUtils.isNotEmpty(cruArchDepId))
								&& (cruArchDepId.indexOf("$") == -1)) {
							ArchivesDep archivesDep = this.archivesDepService
									.get(new Long(cruArchDepId));
							StringBuffer sb = new StringBuffer("<div>"
									+ this.readFeedback);
							SimpleDateFormat sdf = new SimpleDateFormat();
							sdf.format(new Date());
							sb.append("--")
									.append(ContextUtil.getCurrentUser()
											.getFullname()).append("--")
									.append(sdf.format(new Date()))
									.append("</div>");
							archivesDep.setHandleFeedback(sb.toString());
							this.archivesDepService.save(archivesDep);
						}

						archives.setStatus(Archives.STATUS_END);
					} else {
						int recordSize = this.archDispatchService
								.countArchDispatch(archives.getArchivesId());
						if ((this.archUserType
								.compareTo(ArchDispatch.IS_UNDERTAKE) == 0)
								&& (StringUtils.isNotEmpty(cruArchDepId))
								&& (cruArchDepId.indexOf("$") == -1)) {
							ArchivesDep archivesDep = this.archivesDepService
									.get(new Long(cruArchDepId));
							StringBuffer sb = new StringBuffer();
							if (archivesDep.getHandleFeedback() != null) {
								sb.append(archivesDep.getHandleFeedback());
							}
							sb.append("<div>" + this.readFeedback);
							SimpleDateFormat sdf = new SimpleDateFormat();
							sdf.format(new Date());
							sb.append("--")
									.append(ContextUtil.getCurrentUser()
											.getFullname()).append("--")
									.append(sdf.format(new Date()))
									.append("</div>");
							archivesDep.setHandleFeedback(sb.toString());
							this.archivesDepService.save(archivesDep);
						}

						if (size == recordSize)
							archives.setStatus(Archives.STATUS_END);
						else {
							archives.setStatus(Archives.STATUS_READING);
						}
					}
				}
				this.archivesService.save(archives);
			}
			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}

	public String read() {
		ArchDispatch archDispatch = this.archDispatchService
				.get(Long.parseLong(this.dispatchId));
		if (archDispatch != null) {
			archDispatch.setReadFeedback(this.readFeedback);
			archDispatch.setIsRead(ArchDispatch.HAVE_READ);
			archDispatch.setDispatchTime(new Date());
			this.archDispatchService.save(archDispatch);
			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}

	public String dispatch() {
		String disUserIds = getRequest().getParameter("disUserIds");
		String disRoleIds = getRequest().getParameter("disRoleIds");
		Archives archives = this.archivesService.get(Long.parseLong(this.archivesId));
		if (archives != null) {
			if (StringUtils.isNotEmpty(disUserIds)) {
				String[] ids = disUserIds.split(",");
				for (String id : ids) {
					AppUser appUser = this.appUserService.get(new Long(id));
					ArchDispatch archDispatch = new ArchDispatch();
					archDispatch.setArchives(archives);
					archDispatch.setUserId(appUser.getUserId());
					archDispatch.setFullname(appUser.getFullname());
					archDispatch.setDispatchTime(new Date());
					archDispatch.setSubject(archives.getSubject());
					archDispatch.setIsRead(ArchDispatch.NOT_READ);
					archDispatch.setArchUserType(ArchDispatch.IS_DISPATCH);
					this.archDispatchService.save(archDispatch);
				}
			}
			if (StringUtils.isNotEmpty(disRoleIds)) {
				String[] ids = disRoleIds.split(",");
				for (String id : ids) {
					AppRole role = this.appRoleService.get(new Long(id));
					ArchDispatch archDispatch = new ArchDispatch();
					archDispatch.setArchives(archives);
					archDispatch.setDisRoleId(role.getRoleId());
					archDispatch.setDisRoleName(role.getRoleName());
					archDispatch.setDispatchTime(new Date());
					archDispatch.setSubject(archives.getSubject());
					archDispatch.setIsRead(ArchDispatch.NOT_READ);
					archDispatch.setArchUserType(ArchDispatch.IS_DISPATCH);
					this.archDispatchService.save(archDispatch);
				}
			}
			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}

	public String applicate() {
		ArchDispatch archDispatch = this.archDispatchService
				.get(Long.parseLong(this.dispatchId));
		if (archDispatch.getUserId() == null) {
			AppUser user = ContextUtil.getCurrentUser();
			archDispatch.setUserId(user.getUserId());
			archDispatch.setFullname(user.getFullname());
			this.archDispatchService.save(archDispatch);
			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}
}
