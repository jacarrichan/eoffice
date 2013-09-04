package com.palmelf.eoffice.action.arch;

import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.BeanUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.arch.RollFileList;
import com.palmelf.eoffice.model.system.FileAttach;
import com.palmelf.eoffice.service.arch.RollFileListService;
import com.palmelf.eoffice.service.system.FileAttachService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

import java.util.List;
import javax.annotation.Resource;

public class RollFileListAction extends BaseAction {

	@Resource
	private RollFileListService rollFileListService;
	private RollFileList rollFileList;

	@Resource
	private FileAttachService fileAttachService;
	private Long listId;

	public Long getListId() {
		return this.listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	public RollFileList getRollFileList() {
		return this.rollFileList;
	}

	public void setRollFileList(RollFileList rollFileList) {
		this.rollFileList = rollFileList;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<RollFileList> list = this.rollFileListService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer serializer = new JSONSerializer();
		serializer
				.exclude(new String[] { "rollFile", "fileAttach.createTime" })
				.transform(new DateTransformer("yyyy-MM-dd"),
						new String[] { "fileAttach.createTime", "createTime" });
		buff.append(serializer.serialize(list));

		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] listIds = getRequest().getParameterValues("listIds");
		FileAttach fileAttach;
		if ((listIds != null) && (listIds.length > 0)) {
			for (String id : listIds) {
				if ((id != null) && (!id.equals(""))) {
					this.rollFileList = (this.rollFileListService.get(new Long(
							id)));
					fileAttach = this.rollFileList.getFileAttach();

					this.rollFileListService.remove(this.rollFileList);
					this.fileAttachService.removeByPath(fileAttach
							.getFilePath());
				}
			}
		}
		String[] fileIds = getRequest().getParameterValues("fileIds");
		if ((fileIds != null) && (fileIds.length > 0)) {
			for (String id : fileIds) {
				if ((id != null) && (!id.equals(""))) {
					fileAttach = this.fileAttachService.get(new Long(id));

					this.fileAttachService.removeByPath(fileAttach
							.getFilePath());
				}
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		RollFileList rollFileList = this.rollFileListService.get(this.listId);

		StringBuffer sb = new StringBuffer("{success:true,data:");

		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude(new String[] { "rollFile" }).transform(
				new DateTransformer("yyyy-MM-dd"),
				new String[] { "fileAttach.createtime" });
		sb.append(serializer.serialize(rollFileList));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		if (this.rollFileList.getListId() == null) {
			this.rollFileListService.save(this.rollFileList);
		} else {
			RollFileList orgRollFileList = this.rollFileListService
					.get(this.rollFileList.getListId());
			try {
				BeanUtil.copyNotNullProperties(orgRollFileList,
						this.rollFileList);
				this.rollFileListService.save(orgRollFileList);
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return "success";
	}
}
