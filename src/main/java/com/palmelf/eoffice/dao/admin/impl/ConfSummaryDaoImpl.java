package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.ConfSummaryDao;
import com.palmelf.eoffice.dao.admin.ConferenceDao;
import com.palmelf.eoffice.dao.system.FileAttachDao;
import com.palmelf.eoffice.model.admin.ConfSummary;
import com.palmelf.eoffice.model.admin.Conference;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;

public class ConfSummaryDaoImpl extends BaseDaoImpl<ConfSummary> implements
		ConfSummaryDao {

	@Resource
	private ConferenceDao confDao;

	@Resource
	private FileAttachDao fileAttachDao;

	public ConfSummaryDaoImpl() {
		super(ConfSummary.class);
	}

	public ConfSummary send(ConfSummary cm, String fileIds) {
		return save(cm, fileIds);
	}

	public ConfSummary save(ConfSummary cm, String fileIds) {
		if ((fileIds != null) && (!fileIds.isEmpty())) {
			Set set = new HashSet();
			for (String s : fileIds.split(",")) {
				set.add(this.fileAttachDao.get(new Long(s)));
			}
			cm.setAttachFiles(set);
		}
		Conference cf = this.confDao.get(cm.getConfId().getConfId());
		cm.setConfId(cf);
		return super.save(cm);
	}
}
