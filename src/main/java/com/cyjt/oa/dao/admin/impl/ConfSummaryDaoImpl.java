package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.ConfSummaryDao;
import com.cyjt.oa.dao.admin.ConferenceDao;
import com.cyjt.oa.dao.system.FileAttachDao;
import com.cyjt.oa.model.admin.ConfSummary;
import com.cyjt.oa.model.admin.Conference;
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
