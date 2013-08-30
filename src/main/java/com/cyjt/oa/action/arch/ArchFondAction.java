package com.cyjt.oa.action.arch;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.arch.ArchFond;
import com.cyjt.oa.model.arch.ArchRoll;
import com.cyjt.oa.model.arch.BorrowFileList;
import com.cyjt.oa.model.arch.BorrowRecord;
import com.cyjt.oa.model.arch.RollFile;
import com.cyjt.oa.model.arch.RollFileList;
import com.cyjt.oa.model.system.FileAttach;
import com.cyjt.oa.service.arch.ArchFondService;
import com.cyjt.oa.service.arch.ArchRollService;
import com.cyjt.oa.service.arch.BorrowFileListService;
import com.cyjt.oa.service.arch.BorrowRecordService;
import com.cyjt.oa.service.arch.RollFileListService;
import com.cyjt.oa.service.arch.RollFileService;
import com.cyjt.oa.service.system.FileAttachService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class ArchFondAction extends BaseAction {

	@Resource
	private ArchFondService archFondService;

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
	private ArchFond archFond;
	private Long archFondId;

	public Long getArchFondId() {
		return this.archFondId;
	}

	public void setArchFondId(Long archFondId) {
		this.archFondId = archFondId;
	}

	public ArchFond getArchFond() {
		return this.archFond;
	}

	public void setArchFond(ArchFond archFond) {
		this.archFond = archFond;
	}

	@Override
	public String list() {
		final QueryFilter filter = new QueryFilter(this.getRequest());
		final List list = this.archFondService.getAll(filter);

		final StringBuffer buff = new StringBuffer(
				"{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		final JSONSerializer json = new JSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
				"createTime", "updateTime" });
		buff.append(json.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String listRollTree() {
		final QueryFilter filter = new QueryFilter(this.getRequest());
		final List<ArchRoll> rollList = this.archRollService.getAll(filter);
		if ((rollList != null) && (rollList.size() > 0)) {
			final ArchRoll archRoll = rollList.get(0);

			final StringBuffer buff = new StringBuffer("[{id:'0',text:'"
					+ archRoll.getAfNo() + "',expanded:true,children:[");

			if (rollList.size() > 0) {
				for (final ArchRoll roll : rollList) {
					buff.append("{id:'" + roll.getRollNo())
							.append("',text:'" + roll.getRollNo())
							.append("',allowChildren:false,leaf :true},");
				}
				buff.deleteCharAt(buff.length() - 1);
			}

			buff.append("]}]");
			this.jsonString = buff.toString();
		}

		return "success";
	}

	public String multiDel() {
		final String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (final String id : ids) {
				this.archFond = (this.archFondService.get(new Long(id)));

				final Set borrowFileList_fond = this.archFond
						.getBorrowFileList();
				final Iterator borrows_fond = borrowFileList_fond.iterator();
				while (borrows_fond.hasNext()) {
					final BorrowFileList borr_fond = (BorrowFileList) borrows_fond
							.next();
					this.borrowFileListService.remove(borr_fond);
					this.borrowFileListService.flush();

					final BorrowRecord record_fond = borr_fond
							.getBorrowRecord();
					final Set list_fond = record_fond.getBorrowFileLists();
					if ((list_fond == null) || (list_fond.size() == 0)) {
						this.borrowRecordService.remove(record_fond);
					}

				}

				final Set archRolls = this.archFond.getArchRolls();
				final Iterator rolls = archRolls.iterator();
				while (rolls.hasNext()) {
					final ArchRoll archRoll = (ArchRoll) rolls.next();
					final Set borrowFileList_roll = archRoll
							.getBorrowFileList();
					final Iterator borrows_roll = borrowFileList_roll
							.iterator();
					while (borrows_roll.hasNext()) {
						final BorrowFileList borr_roll = (BorrowFileList) borrows_roll
								.next();
						this.borrowFileListService.remove(borr_roll);
						this.borrowFileListService.flush();

						final BorrowRecord record_roll = borr_roll
								.getBorrowRecord();
						final Set list_roll = record_roll.getBorrowFileLists();
						if ((list_roll == null) || (list_roll.size() == 0)) {
							this.borrowRecordService.remove(record_roll);
						}

					}

					final Set rollFiles = archRoll.getRollFiles();
					final Iterator files = rollFiles.iterator();
					while (files.hasNext()) {
						final RollFile file = (RollFile) files.next();

						final Set rollFileLists = file.getRollFileLists();
						final Iterator lists = rollFileLists.iterator();
						while (lists.hasNext()) {
							final RollFileList list = (RollFileList) lists
									.next();
							final FileAttach fileAttach = list.getFileAttach();
							this.rollFileListService.remove(list);
							this.rollFileListService.flush();
							this.fileAttachService.removeByPath(fileAttach
									.getFilePath());
						}

						final Set borrowFileList_file = file
								.getBorrowFileList();
						final Iterator borrows_file = borrowFileList_file
								.iterator();
						while (borrows_file.hasNext()) {
							final BorrowFileList borr_file = (BorrowFileList) borrows_file
									.next();
							this.borrowFileListService.remove(borr_file);
							this.borrowFileListService.flush();

							final BorrowRecord record_file = borr_file
									.getBorrowRecord();
							final Set list_file = record_file
									.getBorrowFileLists();
							if ((list_file == null) || (list_file.size() == 0)) {
								this.borrowRecordService.remove(record_file);
							}

						}

						this.rollFileService.remove(file);
						this.rollFileService.flush();
					}
					this.archRollService.remove(archRoll);
					this.archRollService.flush();
				}
				this.archFondService.remove(this.archFond);
				this.archFondService.flush();
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		final ArchFond archFond = this.archFondService.get(this.archFondId);

		final StringBuffer sb = new StringBuffer("{success:true,data:");

		final JSONSerializer json = new JSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
				"createTime", "updateTime" });
		sb.append(json.serialize(archFond));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		if (this.archFond.getArchFondId() == null) {
			this.archFondService.save(this.archFond);
		} else {
			final ArchFond orgArchFond = this.archFondService.get(this.archFond
					.getArchFondId());
			try {
				final Set archRollSet = orgArchFond.getArchRolls();
				final Set borrowFileList = orgArchFond.getBorrowFileList();
				BeanUtil.copyNotNullProperties(orgArchFond, this.archFond);
				orgArchFond.setArchRolls(archRollSet);
				orgArchFond.setBorrowFileList(borrowFileList);
				this.archFondService.save(orgArchFond);
			} catch (final Exception ex) {
				this.logger.error(ex.getMessage());
			}
		}
		this.setJsonString("{success:true}");
		return "success";
	}
}
