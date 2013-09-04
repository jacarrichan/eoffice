package com.palmelf.eoffice.action.arch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.BeanUtil;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.arch.BorrowFileList;
import com.palmelf.eoffice.model.arch.BorrowRecord;
import com.palmelf.eoffice.model.arch.RollFile;
import com.palmelf.eoffice.model.arch.RollFileList;
import com.palmelf.eoffice.model.system.FileAttach;
import com.palmelf.eoffice.service.arch.BorrowFileListService;
import com.palmelf.eoffice.service.arch.BorrowRecordService;
import com.palmelf.eoffice.service.arch.RollFileListService;
import com.palmelf.eoffice.service.arch.RollFileService;
import com.palmelf.eoffice.service.system.FileAttachService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class RollFileAction extends BaseAction {

	@Resource
	private RollFileService rollFileService;

	@Resource
	private RollFileListService rollFileListService;

	@Resource
	private FileAttachService fileAttachService;

	@Resource
	private BorrowRecordService borrowRecordService;

	@Resource
	private BorrowFileListService borrowFileListService;
	private RollFile rollFile;
	private Long rollFileId;

	public Long getRollFileId() {
		return this.rollFileId;
	}

	public void setRollFileId(Long rollFileId) {
		this.rollFileId = rollFileId;
	}

	public RollFile getRollFile() {
		return this.rollFile;
	}

	public void setRollFile(RollFile rollFile) {
		this.rollFile = rollFile;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<RollFile> list = this.rollFileService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer json = new JSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
				"fileTime", "createTime", "archFond.createTime",
				"archFond.updateTime", "archRoll.archFond.createTime",
				"archRoll.archFond.updateTime",
				"rollFile.archRoll.archFond.createTime",
				"rollFile.archRoll.archFond.updateTime", "archRoll.startTime",
				"archRoll.endTime", "archRoll.setupTime",
				"archRoll.createTime", "fileAttach.createtime" });

		buff.append(json.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();
		this.logger.debug(this.jsonString);

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				RollFile file = this.rollFileService.get(new Long(id));

				Set rollFileLists = file.getRollFileLists();
				Iterator lists = rollFileLists.iterator();
				while (lists.hasNext()) {
					RollFileList list = (RollFileList) lists.next();
					FileAttach fileAttach = list.getFileAttach();
					this.rollFileListService.remove(list);
					this.rollFileListService.flush();
					this.fileAttachService.removeByPath(fileAttach
							.getFilePath());
				}

				Set<BorrowFileList> borrowFileList_file = file
						.getBorrowFileList();
				Iterator<BorrowFileList> borrows_file = borrowFileList_file
						.iterator();
				while (borrows_file.hasNext()) {
					BorrowFileList borr_file = borrows_file.next();
					this.borrowFileListService.remove(borr_file);
					this.borrowFileListService.flush();

					BorrowRecord record_file = borr_file.getBorrowRecord();
					Set list_file = record_file.getBorrowFileLists();
					if ((list_file == null) || (list_file.size() == 0)) {
						this.borrowRecordService.remove(record_file);
					}

				}

				this.rollFileService.remove(file);
				this.rollFileService.flush();
			}

		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		RollFile rollFile = this.rollFileService.get(this.rollFileId);

		StringBuffer sb = new StringBuffer("{success:true,data:");

		JSONSerializer json = new JSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
				"createTime", "updateTime", "fileTime" });
		sb.append(json.serialize(rollFile));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		Long rid = null;
		if (this.rollFile.getRollFileId() == null) {
			this.rollFileService.save(this.rollFile);
			rid = this.rollFile.getRollFileId();
		} else {
			RollFile orgRollFile = this.rollFileService.get(this.rollFile
					.getRollFileId());
			try {
				Set rollFileList = orgRollFile.getRollFileLists();
				Set<BorrowFileList> borrowFileList = orgRollFile
						.getBorrowFileList();
				BeanUtil.copyNotNullProperties(orgRollFile, this.rollFile);
				orgRollFile.setRollFileLists(rollFileList);
				orgRollFile.setBorrowFileList(borrowFileList);
				this.rollFileService.save(orgRollFile);
				rid = orgRollFile.getRollFileId();
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}

		}

		String params = getRequest().getParameter("params");
		if (StringUtils.isNotEmpty(params)) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			RollFileList[] rls = (RollFileList[]) gson.fromJson(params,
					new TypeToken<RollFileList[]>() {
					}.getType());
			if ((rls != null) && (rls.length > 0)) {
				for (RollFileList rl : rls) {
					rl.setRollFileId(rid);
					this.rollFileListService.save(rl);
				}

			}

		}

		setJsonString("{success:true,rollFileId:"
				+ this.rollFile.getRollFileId() + "}");
		return "success";
	}

	public String updateDownLoad() {
		String params = getRequest().getParameter("params");
		this.logger.debug("params=" + params);
		if (StringUtils.isNotEmpty(params)) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			RollFileList[] rls = (RollFileList[]) gson.fromJson(params,
					new TypeToken<RollFileList[]>() {
					}.getType());
			if ((rls != null) && (rls.length > 0)) {
				for (RollFileList rl : rls) {
					this.rollFileListService.save(rl);
				}

			}

		}

		setJsonString("{success:true}");
		return "success";
	}

	public String tidy() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String params = getRequest().getParameter("params");

		if (StringUtils.isNotEmpty(params)) {
			RollFile[] rfs = (RollFile[]) gson.fromJson(params,
					new TypeToken<RollFile[]>() {
					}.getType());

			if ((rfs != null) && (rfs.length > 0)) {
				for (RollFile rollFile : rfs) {
					RollFile orgRollFile = this.rollFileService.get(rollFile
							.getRollFileId());
					try {
						Set rollFileList = orgRollFile.getRollFileLists();
						Set<BorrowFileList> borrowFileList = orgRollFile
								.getBorrowFileList();
						BeanUtil.copyNotNullProperties(orgRollFile, rollFile);
						orgRollFile.setRollFileLists(rollFileList);
						orgRollFile.setBorrowFileList(borrowFileList);
						orgRollFile.setTidyName(ContextUtil.getCurrentUser()
								.getFullname());
						orgRollFile.setTidyTime(new Date());
						this.rollFileService.save(orgRollFile);
					} catch (Exception ex) {
						this.logger.error(ex.getMessage());
					}
				}
			}

		}

		setJsonString("{success:true}");
		return "success";
	}
}
