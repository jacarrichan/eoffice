package com.palmelf.eoffice.action.arch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.arch.ArchFond;
import com.palmelf.eoffice.model.arch.ArchRoll;
import com.palmelf.eoffice.model.system.Dictionary;
import com.palmelf.eoffice.service.arch.ArchFondService;
import com.palmelf.eoffice.service.arch.ArchRollService;
import com.palmelf.eoffice.service.arch.BorrowFileListService;
import com.palmelf.eoffice.service.arch.BorrowRecordService;
import com.palmelf.eoffice.service.arch.RollFileListService;
import com.palmelf.eoffice.service.arch.RollFileService;
import com.palmelf.eoffice.service.system.DictionaryService;
import com.palmelf.eoffice.service.system.FileAttachService;


import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class ArchReportAction extends BaseAction {

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

	@Resource
	private DictionaryService dictionaryService;
	private String year;
	private String itemName;

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String yearReportArch() {
		try {
			SimpleDateFormat yearToDate = new SimpleDateFormat("yyyy");
			SimpleDateFormat yearToString = new SimpleDateFormat("yyyy-MM-dd");
			Date yearStart = yearToDate.parse(this.year);
			long afterTime = yearStart.getTime() / 1000L + 31449600L;
			Date yearEnd = new Date(afterTime * 1000L);

			String yearStartStr = yearToString.format(yearStart);
			String yearEndStr = yearToString.format(yearEnd);

			int fondTotal = 0;

			int archTotal = 0;

			int rollTotal = 0;

			int fileTotal = 0;

			int thisYearRollTotal = 0;

			int thisYearFileTotal = 0;

			int start = 0;
			int limit = 0;

			QueryFilter fondTotalFilter = new QueryFilter(this.getRequest());
			fondTotalFilter.getPagingBean().setStart(Integer.valueOf(start));
			fondTotalFilter.getPagingBean().setPageSize(limit);
			fondTotalFilter.addFilter("Q_createTime_D_LE", yearEndStr);
			this.archFondService.getAll(fondTotalFilter);
			fondTotal = fondTotalFilter.getPagingBean().getTotalItems();

			QueryFilter rollTotalFilter = new QueryFilter(this.getRequest());
			rollTotalFilter.getPagingBean().setStart(Integer.valueOf(start));
			rollTotalFilter.getPagingBean().setPageSize(limit);
			rollTotalFilter.addFilter("Q_createTime_D_LE", yearEndStr);
			this.archRollService.getAll(rollTotalFilter);
			rollTotal = rollTotalFilter.getPagingBean().getTotalItems();

			QueryFilter fileTotalFilter = new QueryFilter(this.getRequest());
			fileTotalFilter.getPagingBean().setStart(Integer.valueOf(start));
			fileTotalFilter.getPagingBean().setPageSize(limit);
			fileTotalFilter.addFilter("Q_createTime_D_LE", yearEndStr);
			this.rollFileService.getAll(fileTotalFilter);
			fileTotal = fileTotalFilter.getPagingBean().getTotalItems();

			QueryFilter thisYearRollTotalFilter = new QueryFilter(
					this.getRequest());
			thisYearRollTotalFilter.getPagingBean().setStart(
					Integer.valueOf(start));
			thisYearRollTotalFilter.getPagingBean().setPageSize(limit);
			thisYearRollTotalFilter
					.addFilter("Q_createTime_D_GE", yearStartStr);
			thisYearRollTotalFilter.addFilter("Q_createTime_D_LE", yearEndStr);
			this.archRollService.getAll(thisYearRollTotalFilter);
			thisYearRollTotal = thisYearRollTotalFilter.getPagingBean()
					.getTotalItems();

			QueryFilter thisYearFileTotalFilter = new QueryFilter(
					this.getRequest());
			thisYearFileTotalFilter.getPagingBean().setStart(
					Integer.valueOf(start));
			thisYearFileTotalFilter.getPagingBean().setPageSize(limit);
			thisYearFileTotalFilter
					.addFilter("Q_createTime_D_GE", yearStartStr);
			thisYearFileTotalFilter.addFilter("Q_createTime_D_LE", yearEndStr);
			this.rollFileService.getAll(thisYearFileTotalFilter);
			thisYearFileTotal = thisYearFileTotalFilter.getPagingBean()
					.getTotalItems();

			Map data = new HashMap();
			data.put("fondTotal", Integer.valueOf(fondTotal));
			data.put("archTotal", Integer.valueOf(rollTotal + fileTotal));
			data.put("rollTotal", Integer.valueOf(rollTotal));
			data.put("fileTotal", Integer.valueOf(fileTotal));
			data.put("thisYearRollTotal", Integer.valueOf(thisYearRollTotal));
			data.put("thisYearFileTotal", Integer.valueOf(thisYearFileTotal));

			StringBuffer sb = new StringBuffer("{success:true,data:");
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			sb.append(gson.toJson(data));
			sb.append("}");
			this.setJsonString(sb.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String yearReportFile() {
		try {
			SimpleDateFormat yearToDate = new SimpleDateFormat("yyyy");
			SimpleDateFormat yearToString = new SimpleDateFormat("yyyy-MM-dd");
			Date yearStart = yearToDate.parse(this.year);
			long afterTime = yearStart.getTime() / 1000L + 31449600L;
			Date yearEnd = new Date(afterTime * 1000L);
			String yearStartStr = yearToString.format(yearStart);
			String yearEndStr = yearToString.format(yearEnd);

			QueryFilter fondFilter = new QueryFilter(this.getRequest());
			fondFilter.addFilter("Q_createTime_D_GE", yearStartStr);
			fondFilter.addFilter("Q_createTime_D_LE", yearEndStr);
			List<ArchFond> fondList = this.archFondService.getAll(fondFilter);

			List<Dictionary> itemList = this.dictionaryService
					.getByItemName(this.itemName);

			int start = 0;
			int limit = 0;
			List list = new ArrayList();
			for (ArchFond fond : fondList) {
				Map fond_Map = new HashMap();
				fond_Map.put("afNo", fond.getAfNo());
				for (Dictionary d : itemList) {
					QueryFilter thisYearFileTotalFilter = new QueryFilter(
							this.getRequest());
					thisYearFileTotalFilter.getPagingBean().setStart(
							Integer.valueOf(start));
					thisYearFileTotalFilter.getPagingBean().setPageSize(limit);
					thisYearFileTotalFilter.addFilter("Q_createTime_D_GE",
							yearStartStr);
					thisYearFileTotalFilter.addFilter("Q_createTime_D_LE",
							yearEndStr);
					thisYearFileTotalFilter.addFilter("Q_timeLimit_S_LK",
							d.getItemValue());
					thisYearFileTotalFilter.addFilter(
							"Q_archRoll.archFondId_L_EQ", fond.getArchFondId()
									.toString());
					this.rollFileService.getAll(thisYearFileTotalFilter);
					int thisYearAndDicFileTotal = thisYearFileTotalFilter
							.getPagingBean().getTotalItems();
					fond_Map.put(d.getDicId().toString(),
							Integer.valueOf(thisYearAndDicFileTotal));
				}

				list.add(fond_Map);
			}

			StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
					.append(fondFilter.getPagingBean().getTotalItems()).append(
							",result:");

			Gson gson = new Gson();

			buff.append(gson.toJson(list));

			buff.append("}");
			this.jsonString = buff.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String yearReportTidy() {
		try {
			SimpleDateFormat yearToDate = new SimpleDateFormat("yyyy");
			SimpleDateFormat yearToString = new SimpleDateFormat("yyyy-MM-dd");
			Date yearStart = yearToDate.parse(this.year);
			long afterTime = yearStart.getTime() / 1000L + 31449600L;
			Date yearEnd = new Date(afterTime * 1000L);
			String yearStartStr = yearToString.format(yearStart);
			String yearEndStr = yearToString.format(yearEnd);

			QueryFilter fondFilter = new QueryFilter(this.getRequest());
			fondFilter.addFilter("Q_createTime_D_GE", yearStartStr);
			fondFilter.addFilter("Q_createTime_D_LE", yearEndStr);
			List<ArchFond> fondList = this.archFondService.getAll(fondFilter);

			List<Dictionary> itemList = this.dictionaryService
					.getByItemName(this.itemName);

			int start = 0;
			int limit = 0;
			List list = new ArrayList();
			for (ArchFond fond : fondList) {
				Map fond_Map = new HashMap();
				fond_Map.put("afNo", fond.getAfNo());
				boolean add = false;
				for (Dictionary d : itemList) {
					QueryFilter thisYearFileTotalFilter = new QueryFilter(
							this.getRequest());
					thisYearFileTotalFilter.getPagingBean().setStart(
							Integer.valueOf(start));
					thisYearFileTotalFilter.getPagingBean().setPageSize(limit);
					thisYearFileTotalFilter.addFilter("Q_tidyTime_D_GE",
							yearStartStr);
					thisYearFileTotalFilter.addFilter("Q_tidyTime_D_LE",
							yearEndStr);
					thisYearFileTotalFilter.addFilter("Q_timeLimit_S_LK",
							d.getItemValue());
					thisYearFileTotalFilter.addFilter(
							"Q_archRoll.archFondId_L_EQ", fond.getArchFondId()
									.toString());
					thisYearFileTotalFilter
							.addFilter("Q_archStatus_SN_EQ", "1");

					this.rollFileService.getAll(thisYearFileTotalFilter);
					int thisYearAndDicFileTotal = thisYearFileTotalFilter
							.getPagingBean().getTotalItems();
					fond_Map.put(d.getDicId().toString(),
							Integer.valueOf(thisYearAndDicFileTotal));
					if (thisYearAndDicFileTotal <= 0) {
						continue;
					}
					add = true;
				}

				if (add) {
					list.add(fond_Map);
				}

			}

			StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
					.append(fondFilter.getPagingBean().getTotalItems()).append(
							",result:");

			Gson gson = new Gson();

			buff.append(gson.toJson(list));

			buff.append("}");
			this.jsonString = buff.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String yearReportBorrowMain() {
		int start = 0;
		int limit = 0;

		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.getPagingBean().setStart(Integer.valueOf(start));
		filter.getPagingBean().setPageSize(limit);
		this.borrowFileListService.getAll(filter);

		int totalCount = filter.getPagingBean().getTotalItems();

		Map data = new HashMap();
		data.put("totalCount", Integer.valueOf(totalCount));

		StringBuffer sb = new StringBuffer("{success:true,data:");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		sb.append(gson.toJson(data));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	public String yearReportBorrowYear() {
		try {
			SimpleDateFormat yearToDate = new SimpleDateFormat("yyyy");
			SimpleDateFormat yearToString = new SimpleDateFormat("yyyy-MM-dd");
			Date yearStart = yearToDate.parse(this.year);
			long afterTime = yearStart.getTime() / 1000L + 31449600L;
			Date yearEnd = new Date(afterTime * 1000L);
			String yearStartStr = yearToString.format(yearStart);
			String yearEndStr = yearToString.format(yearEnd);

			int start = 0;
			int limit = 0;

			QueryFilter totalCountFilter = new QueryFilter(this.getRequest());
			totalCountFilter.getPagingBean().setStart(Integer.valueOf(start));
			totalCountFilter.getPagingBean().setPageSize(limit);
			totalCountFilter.addFilter("Q_borrowRecord.borrowDate_D_GE",
					yearStartStr);
			totalCountFilter.addFilter("Q_borrowRecord.borrowDate_D_LE",
					yearEndStr);
			this.borrowFileListService.getAll(totalCountFilter);
			int totalCount = totalCountFilter.getPagingBean().getTotalItems();

			QueryFilter rollTotalFilter = new QueryFilter(this.getRequest());
			rollTotalFilter.getPagingBean().setStart(Integer.valueOf(start));
			rollTotalFilter.getPagingBean().setPageSize(limit);
			rollTotalFilter.addFilter("Q_borrowRecord.borrowDate_D_GE",
					yearStartStr);
			rollTotalFilter.addFilter("Q_borrowRecord.borrowDate_D_LE",
					yearEndStr);
			rollTotalFilter.addFilter("Q_listType_S_EQ", "案卷");
			this.borrowFileListService.getAll(rollTotalFilter);
			int rollTotal = rollTotalFilter.getPagingBean().getTotalItems();

			QueryFilter fileTotalFilter = new QueryFilter(this.getRequest());
			fileTotalFilter.getPagingBean().setStart(Integer.valueOf(start));
			fileTotalFilter.getPagingBean().setPageSize(limit);
			fileTotalFilter.addFilter("Q_borrowRecord.borrowDate_D_GE",
					yearStartStr);
			fileTotalFilter.addFilter("Q_borrowRecord.borrowDate_D_LE",
					yearEndStr);
			fileTotalFilter.addFilter("Q_listType_S_EQ", "文件");
			this.borrowFileListService.getAll(fileTotalFilter);
			int fileTotal = fileTotalFilter.getPagingBean().getTotalItems();

			Map data = new HashMap();
			data.put("totalCount", totalCount);
			data.put("rollTotal", Integer.valueOf(rollTotal));
			data.put("fileTotal", fileTotal);

			StringBuffer sb = new StringBuffer("{success:true,data:");
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			sb.append(gson.toJson(data));
			sb.append("}");
			this.setJsonString(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String yearReportBorrowDetail() {
		try {
			SimpleDateFormat yearToDate = new SimpleDateFormat("yyyy");
			SimpleDateFormat yearToString = new SimpleDateFormat("yyyy-MM-dd");
			Date yearStart = yearToDate.parse(this.year);
			long afterTime = yearStart.getTime() / 1000L + 31449600L;
			Date yearEnd = new Date(afterTime * 1000L);
			String yearStartStr = yearToString.format(yearStart);
			String yearEndStr = yearToString.format(yearEnd);

			this.logger.debug(this.itemName);
			List<Dictionary> itemList = this.dictionaryService
					.getByItemName(this.itemName);
			int start = 0;
			int limit = 0;
			Map map = new HashMap();
			for (Dictionary d : itemList) {
				QueryFilter rollTotalFilter = new QueryFilter(this.getRequest());
				rollTotalFilter.getPagingBean()
						.setStart(Integer.valueOf(start));
				rollTotalFilter.getPagingBean().setPageSize(limit);
				rollTotalFilter.addFilter("Q_borrowRecord.borrowDate_D_GE",
						yearStartStr);
				rollTotalFilter.addFilter("Q_borrowRecord.borrowDate_D_LE",
						yearEndStr);
				rollTotalFilter.addFilter("Q_listType_S_EQ", "案卷");
				rollTotalFilter.addFilter("Q_borrowRecord.borrowReason_S_EQ",
						d.getItemValue());

				this.borrowFileListService.getAll(rollTotalFilter);
				int rollTotal = rollTotalFilter.getPagingBean().getTotalItems();

				QueryFilter fileTotalFilter = new QueryFilter(this.getRequest());
				fileTotalFilter.getPagingBean()
						.setStart(Integer.valueOf(start));
				fileTotalFilter.getPagingBean().setPageSize(limit);
				fileTotalFilter.addFilter("Q_borrowRecord.borrowDate_D_GE",
						yearStartStr);
				fileTotalFilter.addFilter("Q_borrowRecord.borrowDate_D_LE",
						yearEndStr);
				fileTotalFilter.addFilter("Q_listType_S_EQ", "文件");
				fileTotalFilter.addFilter("Q_borrowRecord.borrowReason_S_EQ",
						d.getItemValue());
				this.borrowFileListService.getAll(fileTotalFilter);
				int fileTotal = fileTotalFilter.getPagingBean().getTotalItems();

				map.put("rollTotal" + d.getDicId(), Integer.valueOf(rollTotal));
				map.put("fileTotal" + d.getDicId(), Integer.valueOf(fileTotal));
			}

			StringBuffer buff = new StringBuffer("{success:true")
					.append(",data:");

			Gson gson = new Gson();

			buff.append(gson.toJson(map));

			buff.append("}");
			this.jsonString = buff.toString();
			this.logger.debug("jsonString=" + this.jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String rollReportByFond() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<ArchFond> fondList = this.archFondService.getAll(filter);
		List list = new ArrayList();

		for (ArchFond af : fondList) {
			int caseNums = af.getArchRolls().size();
			af.setCaseNums(Integer.valueOf(caseNums));
			if ((af.getCaseNums() == null) || (af.getCaseNums().equals(""))) {
				this.archFondService.save(af);
			}
			Map m = new HashMap();
			m.put("name", af.getAfNo());
			m.put("num", Integer.valueOf(caseNums));
			list.add(m);
		}

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer json = new JSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
				"createTime", "updateTime" });
		buff.append(json.serialize(list));

		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String rollReportByTimeLimit() {
		this.logger.debug("itemName=" + this.itemName);

		List<Dictionary> itemList = this.dictionaryService
				.getByItemName(this.itemName);
		int start = 0;
		int limit = 0;
		List list = new ArrayList();
		int d_total = 0;
		for (Dictionary d : itemList) {
			QueryFilter rollTotalFilter = new QueryFilter(this.getRequest());
			rollTotalFilter.getPagingBean().setStart(Integer.valueOf(start));
			rollTotalFilter.getPagingBean().setPageSize(limit);
			rollTotalFilter.addFilter("Q_timeLimit_S_LK", d.getItemValue()
					.trim());

			this.archRollService.getAll(rollTotalFilter);
			int rollTotal = rollTotalFilter.getPagingBean().getTotalItems();
			d_total += rollTotal;
			Map map = new HashMap();
			map.put("name", d.getItemValue());
			map.put("num", Integer.valueOf(rollTotal));
			map.put("isTotal", Boolean.valueOf(false));
			list.add(map);
		}

		QueryFilter allFilter = new QueryFilter(this.getRequest());
		allFilter.getPagingBean().setStart(Integer.valueOf(start));
		allFilter.getPagingBean().setPageSize(limit);
		this.archRollService.getAll(allFilter);
		Object map = new HashMap();
		((Map) map).put("name", "其它");
		((Map) map).put(
				"num",
				Integer.valueOf(allFilter.getPagingBean().getTotalItems()
						- d_total));
		((Map) map).put("isTotal", Boolean.valueOf(false));
		list.add(map);

		StringBuffer buff = new StringBuffer("{success:true")
				.append(",result:");

		Gson gson = new Gson();

		buff.append(gson.toJson(list));

		buff.append("}");
		this.jsonString = buff.toString();

		return (String) "success";
	}

	public String fileReportByRoll() {
		List<Map<String, Object>> allList = new ArrayList<Map<String, Object>>();

		QueryFilter filter = new QueryFilter(this.getRequest());
		List<ArchRoll> list = this.archRollService.getAll(filter);
		for (ArchRoll ar : list) {
			int fileNums = ar.getRollFiles().size();

			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", ar.getRollNo());
			m.put("nums", Integer.valueOf(fileNums));
			m.put("isTotal", Boolean.valueOf(false));
			allList.add(m);
		}

		int start = 0;
		int limit = 0;

		QueryFilter fileFilter = new QueryFilter(this.getRequest());
		fileFilter.getPagingBean().setStart(Integer.valueOf(start));
		fileFilter.getPagingBean().setPageSize(limit);
		fileFilter.addFilter("Q_archRoll_NULL", "");
		this.rollFileService.getAll(fileFilter);

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", "其它");
		m.put("nums",
				Integer.valueOf(fileFilter.getPagingBean().getTotalItems()));
		m.put("isTotal", Boolean.valueOf(false));
		allList.add(m);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer json = new JSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "createTime", "updateTime", "setupTime",
						"endTime", "startTime" });
		buff.append(json.serialize(allList));

		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String fileReportByTimeLimit() {
		this.logger.debug("itemName=" + this.itemName);

		List<Dictionary> itemList = this.dictionaryService
				.getByItemName(this.itemName);
		int start = 0;
		int limit = 0;
		List<Object> list = new ArrayList();
		int d_total = 0;
		for (Dictionary d : itemList) {
			Map<String, Object> map = new HashMap<String, Object>();

			QueryFilter rollTotalFilter = new QueryFilter(this.getRequest());
			rollTotalFilter.getPagingBean().setStart(Integer.valueOf(start));
			rollTotalFilter.getPagingBean().setPageSize(limit);
			rollTotalFilter.addFilter("Q_timeLimit_S_LK", d.getItemValue()
					.trim());

			this.rollFileService.getAll(rollTotalFilter);
			int rollTotal = rollTotalFilter.getPagingBean().getTotalItems();
			d_total += rollTotal;
			map.put("name", d.getItemValue());
			map.put("nums", Integer.valueOf(rollTotal));
			map.put("isTotal", Boolean.valueOf(false));
			list.add(map);
		}

		QueryFilter allFilter = new QueryFilter(this.getRequest());
		allFilter.getPagingBean().setStart(Integer.valueOf(start));
		allFilter.getPagingBean().setPageSize(limit);
		this.rollFileService.getAll(allFilter);
		Object map = new HashMap();
		((Map) map).put("name", "其它");
		((Map) map).put(
				"nums",
				Integer.valueOf(allFilter.getPagingBean().getTotalItems()
						- d_total));
		((Map) map).put("isTotal", Boolean.valueOf(false));
		list.add(map);

		StringBuffer buff = new StringBuffer("{success:true")
				.append(",result:");

		Gson gson = new Gson();

		buff.append(gson.toJson(list));

		buff.append("}");
		this.jsonString = buff.toString();

		return (String) "success";
	}
}
