package com.palmelf.eoffice.action.admin;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.admin.DepreRecord;
import com.palmelf.eoffice.model.admin.FixedAssets;
import com.palmelf.eoffice.service.admin.DepreRecordService;
import com.palmelf.eoffice.service.admin.FixedAssetsService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class DepreRecordAction extends BaseAction {

	@Resource
	private DepreRecordService depreRecordService;
	private DepreRecord depreRecord;

	@Resource
	private FixedAssetsService fixedAssetsService;
	private Long recordId;
	static int YEARS = 11;

	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public DepreRecord getDepreRecord() {
		return this.depreRecord;
	}

	public void setDepreRecord(DepreRecord depreRecord) {
		this.depreRecord = depreRecord;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<DepreRecord> list = this.depreRecordService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), new String[] { "calTime" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.depreRecordService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		DepreRecord depreRecord = this.depreRecordService.get(this.recordId);
		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(depreRecord));
		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	public String depreciate() {
		String strAssetsId = this.getRequest().getParameter("ids");
		if (StringUtils.isNotEmpty(strAssetsId)) {
			FixedAssets fixedAssets = this.fixedAssetsService.get(new Long(strAssetsId));
			BigDecimal cruValue = fixedAssets.getAssetCurValue();
			BigDecimal assetValue = fixedAssets.getAssetValue();
			short method = fixedAssets.getDepreType().getCalMethod().shortValue();
			Integer i = Integer.valueOf(0);
			if (method == 1) {
				BigDecimal yearRate = new BigDecimal(0);
				yearRate = fixedAssets.getDepreRate();
				BigDecimal monthRate = yearRate.divide(new BigDecimal(12), 2, 2);
				Date lastCalTime = this.depreRecordService.findMaxDate(new Long(strAssetsId));
				if (lastCalTime == null) {
					lastCalTime = fixedAssets.getStartDepre();
				}
				Integer deprePeriod = fixedAssets.getDepreType().getDeprePeriod();
				BigDecimal value = assetValue.multiply(new BigDecimal(deprePeriod.toString())).multiply(monthRate);
				GregorianCalendar gc1 = new GregorianCalendar();
				gc1.setTime(lastCalTime);
				GregorianCalendar gcYears = new GregorianCalendar();
				gcYears.setTime(fixedAssets.getStartDepre());
				Integer years = Integer.valueOf(fixedAssets.getIntendTerm().intValue());
				gcYears.add(1, years.intValue());
				while (deprePeriod.intValue() >= 1) {
					gc1.add(2, deprePeriod.intValue());
					Date curDate = gc1.getTime();
					if ((curDate.after(new Date())) || (curDate.after(gcYears.getTime()))) {
						break;
					}
					i = Integer.valueOf(i.intValue() + 1);
					DepreRecord depreR = new DepreRecord();
					depreR.setFixedAssets(fixedAssets);
					depreR.setCalTime(curDate);
					cruValue = cruValue.subtract(value);
					if (cruValue.compareTo(new BigDecimal("0.001")) == -1) {
						break;
					}
					depreR.setDepreAmount(value);
					this.depreRecordService.save(depreR);
				}
			} else if (method == 2) {
				i = Integer.valueOf(i.intValue() + 1);
				String cruCalDate = this.getRequest().getParameter("cruCalDate");
				if (StringUtils.isNotEmpty(cruCalDate)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date calDate = new Date();
					try {
						calDate = sdf.parse(cruCalDate);
					} catch (ParseException e) {
						e.printStackTrace();
						this.setJsonString("{success:false}");
						return "success";
					}
					BigDecimal total = new BigDecimal(1).subtract(
							fixedAssets.getRemainValRate().divide(new BigDecimal(100))).multiply(
							fixedAssets.getAssetValue());
					BigDecimal per = total.divide(fixedAssets.getIntendWorkGross(), 2, 2);
					cruValue = cruValue.subtract(per.multiply(this.depreRecord.getWorkCapacity()));

					this.depreRecord.setCalTime(calDate);
					this.depreRecord.setFixedAssets(fixedAssets);
					this.depreRecord.setDepreAmount(per.multiply(this.depreRecord.getWorkCapacity()));
					this.depreRecordService.save(this.depreRecord);
				} else {
					this.setJsonString("{success:false}");
					return "success";
				}
			} else if (method == 3) {
				Integer deprePeriod = fixedAssets.getDepreType().getDeprePeriod();
				Date lastCalTime = this.depreRecordService.findMaxDate(new Long(strAssetsId));
				if (lastCalTime == null) {
					lastCalTime = fixedAssets.getStartDepre();
				}
				Date startDepre = fixedAssets.getStartDepre();
				GregorianCalendar gc1 = new GregorianCalendar();
				GregorianCalendar gcYear = new GregorianCalendar();
				GregorianCalendar gcYears = new GregorianCalendar();
				BigDecimal yearRate = new BigDecimal(2).divide(fixedAssets.getIntendTerm(), 2, 3);
				BigDecimal monthRate = yearRate.divide(new BigDecimal(12), 2, 3);
				gcYear.setTime(startDepre);
				Integer years = Integer.valueOf(fixedAssets.getIntendTerm().intValue());
				if (years.intValue() > 2) {
					gcYear.add(1, years.intValue() - 2);
				}
				gcYears.setTime(startDepre);
				gcYears.add(1, years.intValue());
				gc1.setTime(lastCalTime);
				Integer flag = Integer.valueOf(0);
				BigDecimal twoYearValue = new BigDecimal(0);
				while (deprePeriod.intValue() > 0) {
					DepreRecord depreR = new DepreRecord();
					BigDecimal bd = new BigDecimal(0);
					Date last = gc1.getTime();
					gc1.add(2, deprePeriod.intValue());
					if ((gc1.getTime().after(new Date())) || (gc1.getTime().after(gcYears.getTime()))) {
						break;
					}
					i = Integer.valueOf(i.intValue() + 1);
					if (!gc1.getTime().after(gcYear.getTime())) {
						bd = cruValue.multiply(monthRate).multiply(new BigDecimal(deprePeriod.toString()));
						cruValue = cruValue.subtract(bd);
					} else {
						GregorianCalendar lastGc = new GregorianCalendar();
						lastGc.setTime(last);
						Integer j = Integer.valueOf(0);
						Date lastDate = lastGc.getTime();
						Date gcDate = gcYear.getTime();

						while (lastDate.before(gcDate)) {
							lastGc.add(2, 1);
							cruValue = cruValue.subtract(cruValue.multiply(monthRate));
							bd = bd.add(cruValue.multiply(monthRate));
							j = Integer.valueOf(j.intValue() + 1);
						}
						if (deprePeriod.intValue() - j.intValue() > 0) {
							if (flag.intValue() == 0) {
								twoYearValue = cruValue.subtract(cruValue.multiply(fixedAssets.getRemainValRate()
										.divide(new BigDecimal(100), 2, 2)));
							}
							flag = Integer.valueOf(flag.intValue() + 1);
							Integer w = Integer.valueOf(deprePeriod.intValue() - j.intValue());
							if (fixedAssets.getIntendTerm().intValue() > 1) {
								bd = bd.add(twoYearValue.divide(new BigDecimal(24), 2, 3).multiply(
										new BigDecimal(w.toString())));
								cruValue = cruValue.subtract(twoYearValue.divide(new BigDecimal(24), 2, 3).multiply(
										new BigDecimal(w.toString())));
							} else {
								bd = bd.add(twoYearValue.divide(new BigDecimal(12), 2, 3).multiply(
										new BigDecimal(w.toString())));
								cruValue = cruValue.subtract(twoYearValue.divide(new BigDecimal(12), 2, 3).multiply(
										new BigDecimal(w.toString())));
							}
						}
					}

					Date calTime = gc1.getTime();
					depreR.setCalTime(calTime);
					depreR.setFixedAssets(fixedAssets);
					depreR.setDepreAmount(bd);
					this.depreRecordService.save(depreR);
				}
			} else if (method == 4) {
				Integer deprePeriod = fixedAssets.getDepreType().getDeprePeriod();
				Date lastCalTime = this.depreRecordService.findMaxDate(new Long(strAssetsId));
				if (lastCalTime == null) {
					lastCalTime = fixedAssets.getStartDepre();
				}
				Date startDepre = fixedAssets.getStartDepre();
				BigDecimal intendTerm = fixedAssets.getIntendTerm();
				BigDecimal total = intendTerm.multiply(intendTerm.add(new BigDecimal(1))).divide(new BigDecimal(2));
				GregorianCalendar gc1 = new GregorianCalendar();
				GregorianCalendar gcStart = new GregorianCalendar();
				gcStart.setTime(startDepre);
				gc1.setTime(lastCalTime);
				GregorianCalendar gcYears = new GregorianCalendar();
				gcYears.setTime(fixedAssets.getStartDepre());
				Integer years = Integer.valueOf(fixedAssets.getIntendTerm().intValue());
				gcYears.add(1, years.intValue());
				BigDecimal stValue = fixedAssets.getAssetValue().multiply(
						new BigDecimal(1).subtract(fixedAssets.getRemainValRate().divide(new BigDecimal(100), 2, 2)));
				while (deprePeriod.intValue() > 0) {
					Date last = gc1.getTime();
					GregorianCalendar gcLast = new GregorianCalendar();
					gcLast.setTime(last);
					gc1.add(2, deprePeriod.intValue());

					BigDecimal depValue = new BigDecimal(0);
					Integer jian = Integer.valueOf(gc1.get(1) - gcLast.get(1));
					if ((gc1.getTime().after(new Date())) || (gc1.getTime().after(gcYears.getTime()))) {
						break;
					}
					i = Integer.valueOf(i.intValue() + 1);
					Integer be = Integer.valueOf(gc1.get(1) - gcStart.get(1));
					BigDecimal rate = intendTerm.subtract(new BigDecimal(be.intValue())).divide(total, 2, 2);
					if (jian.intValue() == 0) {
						depValue = stValue.multiply(rate).multiply(
								new BigDecimal(deprePeriod.intValue()).divide(new BigDecimal(12), 2, 2));
						cruValue = cruValue.subtract(depValue);
					} else {
						Integer beLast = Integer.valueOf(gcLast.get(1) - gcStart.get(1));
						BigDecimal rateLast = intendTerm.subtract(new BigDecimal(beLast.intValue()))
								.divide(total, 2, 2);
						Integer months = Integer.valueOf(DepreRecordAction.YEARS - gcLast.get(2));
						depValue = stValue.multiply(rateLast).multiply(
								new BigDecimal(months.intValue()).divide(new BigDecimal(12), 2, 2));

						Integer monthsNextYear = Integer.valueOf(gc1.get(2) + 1);
						depValue = depValue.add(stValue.multiply(rate).multiply(
								new BigDecimal(monthsNextYear.intValue()).divide(new BigDecimal(12), 2, 2)));
						cruValue = cruValue.subtract(depValue);
					}
					DepreRecord depreR = new DepreRecord();
					Date cruDate = gc1.getTime();
					depreR.setCalTime(cruDate);
					depreR.setFixedAssets(fixedAssets);
					depreR.setDepreAmount(depValue);
					this.depreRecordService.save(depreR);
				}
			}
			fixedAssets.setAssetCurValue(cruValue);
			this.fixedAssetsService.save(fixedAssets);
			if (i.intValue() == 0) {
				this.setJsonString("{success:false,message:'还没到折算时间!'}");
			} else {
				this.setJsonString("{success:true}");
			}
		} else {
			this.setJsonString("{success:false}");
		}

		return "success";
	}

	public String work() {
		String id = this.getRequest().getParameter("ids");
		if (StringUtils.isNotEmpty(id)) {
			Long assetsId = new Long(id);
			FixedAssets fixedAssets = this.fixedAssetsService.get(assetsId);
			Date lastCalTime = this.depreRecordService.findMaxDate(assetsId);
			if (lastCalTime == null) {
				lastCalTime = fixedAssets.getStartDepre();
			}
			if (lastCalTime != null) {
				Integer deprePeriod = fixedAssets.getDepreType().getDeprePeriod();
				GregorianCalendar gc1 = new GregorianCalendar();
				gc1.setTime(lastCalTime);
				gc1.add(2, deprePeriod.intValue());
				Date cruCalTime = gc1.getTime();
				if (cruCalTime.before(new Date())) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String strLastDate = sdf.format(lastCalTime);
					String strCruDate = sdf.format(cruCalTime);
					String unit = fixedAssets.getWorkGrossUnit();
					BigDecimal defPerWorkGross = fixedAssets.getDefPerWorkGross();
					this.setJsonString("{success:true,lastCalTime:'" + strLastDate + "',cruCalTime:'" + strCruDate
							+ "',workGrossUnit:'" + unit + "',defPerWorkGross:'" + defPerWorkGross.toString() + "'}");
				} else {
					this.setJsonString("{success:false,message:'还没到折算时间!'}");
				}
			} else {
				this.setJsonString("{success:false,message:'未设定开始执行折算时间!'}");
			}
		} else {
			this.setJsonString("{success:false,message:'请联系管理员!'}");
		}

		return "success";
	}
}
