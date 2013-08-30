package com.cyjt.oa.action.arch;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.arch.ArchRoll;
import com.cyjt.oa.model.arch.BorrowFileList;
import com.cyjt.oa.model.arch.BorrowRecord;
import com.cyjt.oa.model.arch.RollFile;
import com.cyjt.oa.model.arch.RollFileList;
import com.cyjt.oa.model.system.FileAttach;
import com.cyjt.oa.service.arch.ArchRollService;
import com.cyjt.oa.service.arch.BorrowFileListService;
import com.cyjt.oa.service.arch.BorrowRecordService;
import com.cyjt.oa.service.arch.RollFileListService;
import com.cyjt.oa.service.arch.RollFileService;
import com.cyjt.oa.service.system.FileAttachService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class ArchRollAction extends BaseAction {

	@Resource
	private ArchRollService archRollService;

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
	private ArchRoll archRoll;
	private Long rollId;

	public Long getRollId() {
		return this.rollId;
	}

	public void setRollId(Long rollId) {
		this.rollId = rollId;
	}

	public ArchRoll getArchRoll() {
		return this.archRoll;
	}

	public void setArchRoll(ArchRoll archRoll) {
		this.archRoll = archRoll;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<ArchRoll> list = this.archRollService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer json = new JSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "createTime", "updateTime", "setupTime",
						"endTime", "startTime" });
		buff.append(json.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.archRoll = (this.archRollService.get(new Long(id)));
				Set<BorrowFileList> borrowFileList_roll = this.archRoll
						.getBorrowFileList();
				Iterator<BorrowFileList> borrows_roll = borrowFileList_roll
						.iterator();
				while (borrows_roll.hasNext()) {
					BorrowFileList borr_roll = borrows_roll.next();
					this.borrowFileListService.remove(borr_roll);
					this.borrowFileListService.flush();

					BorrowRecord record_roll = borr_roll.getBorrowRecord();
					Set<BorrowFileList> list_roll = record_roll
							.getBorrowFileLists();
					if ((list_roll == null) || (list_roll.size() == 0)) {
						this.borrowRecordService.remove(record_roll);
					}

				}

				Set<RollFile> rollFiles = this.archRoll.getRollFiles();
				Iterator<RollFile> files = rollFiles.iterator();
				while (files.hasNext()) {
					RollFile file = files.next();

					Set<RollFileList> rollFileLists = file.getRollFileLists();
					Iterator<RollFileList> lists = rollFileLists.iterator();
					while (lists.hasNext()) {
						RollFileList list = lists.next();
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
						Set<BorrowFileList> list_file = record_file
								.getBorrowFileLists();
						if ((list_file == null) || (list_file.size() == 0)) {
							this.borrowRecordService.remove(record_file);
						}

					}

					this.rollFileService.remove(file);
					this.rollFileService.flush();
				}
				this.archRollService.remove(this.archRoll);
				this.archRollService.flush();
			}

		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArchRoll archRoll = this.archRollService.get(this.rollId);

		StringBuffer sb = new StringBuffer("{success:true,data:");

		JSONSerializer json = new JSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "createTime", "updateTime", "setupTime",
						"endTime", "startTime" });

		sb.append(json.serialize(archRoll));

		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		if (this.archRoll.getRollId() == null) {
			this.archRollService.save(this.archRoll);
		} else {
			ArchRoll orgArchRoll = this.archRollService.get(this.archRoll
					.getRollId());
			try {
				Set<RollFile> rollFileSet = orgArchRoll.getRollFiles();
				Set<BorrowFileList> borrowFileList = orgArchRoll
						.getBorrowFileList();
				BeanUtil.copyNotNullProperties(orgArchRoll, this.archRoll);
				orgArchRoll.setRollFiles(rollFileSet);
				orgArchRoll.setBorrowFileList(borrowFileList);
				this.archRollService.save(orgArchRoll);
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		}
		this.setJsonString("{success:true}");
		return "success";
	}
}
