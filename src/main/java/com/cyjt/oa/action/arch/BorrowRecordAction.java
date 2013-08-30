package com.cyjt.oa.action.arch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.arch.BorrowFileList;
import com.cyjt.oa.model.arch.BorrowRecord;
import com.cyjt.oa.service.arch.BorrowFileListService;
import com.cyjt.oa.service.arch.BorrowRecordService;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class BorrowRecordAction extends BaseAction {

	@Resource
	private BorrowRecordService borrowRecordService;
	private BorrowRecord borrowRecord;

	@Resource
	private BorrowFileListService borrowFileListService;
	private Long recordId;

	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public BorrowRecord getBorrowRecord() {
		return this.borrowRecord;
	}

	public void setBorrowRecord(BorrowRecord borrowRecord) {
		this.borrowRecord = borrowRecord;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<BorrowRecord> list = this.borrowRecordService.getAll(filter);

		Type type = new TypeToken<List<BorrowRecord>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd").create();

		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.borrowRecordService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		BorrowRecord borrowRecord = this.borrowRecordService.get(this.recordId);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd").create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(borrowRecord));
		sb.append("}");
		setJsonString(sb.toString());
		this.logger.debug("sb:" + sb.toString());
		return "success";
	}

	@Override
	public String save() {
		if (this.borrowRecord.getRecordId() == null) {
			this.borrowRecordService.save(this.borrowRecord);
		} else {
			BorrowRecord orgBorrowRecord = this.borrowRecordService
					.get(this.borrowRecord.getRecordId());
			try {
				Set borrowFileLists = orgBorrowRecord.getBorrowFileLists();
				BeanUtil.copyNotNullProperties(orgBorrowRecord,
						this.borrowRecord);
				orgBorrowRecord.setBorrowFileLists(borrowFileLists);
				this.borrowRecordService.save(orgBorrowRecord);
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}

		}

		String params = getRequest().getParameter("params");
		if (StringUtils.isNotEmpty(params)) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			BorrowFileList[] bfl = (BorrowFileList[]) gson.fromJson(params,
					new TypeToken<BorrowFileList[]>() {
					}.getType());
			if ((bfl != null) && (bfl.length > 0)) {
				for (BorrowFileList l : bfl) {
					l.setRecordId(this.borrowRecord.getRecordId());

					this.borrowFileListService.save(l);
				}

			}

		}

		setJsonString("{success:true,recordId:"
				+ this.borrowRecord.getRecordId() + "}");

		return "success";
	}

	public String check() {
		BorrowRecord r = this.borrowRecordService.get(this.borrowRecord
				.getRecordId());
		r.setReturnStatus(this.borrowRecord.getReturnStatus());
		r.setCheckId(ContextUtil.getCurrentUserId());
		r.setCheckName(ContextUtil.getCurrentUser().getUsername());
		this.borrowRecordService.save(r);

		setJsonString("{success:true,recordId:" + r.getRecordId() + "}");

		return "success";
	}
}
