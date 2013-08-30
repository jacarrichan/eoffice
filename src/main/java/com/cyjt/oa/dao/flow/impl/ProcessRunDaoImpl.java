package com.cyjt.oa.dao.flow.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.flow.ProcessRunDao;
import com.cyjt.oa.model.flow.ProcessRun;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class ProcessRunDaoImpl extends BaseDaoImpl<ProcessRun> implements
		ProcessRunDao {
	public ProcessRunDaoImpl() {
		super(ProcessRun.class);
	}

	public ProcessRun getByPiId(String piId) {
		String hql = "from ProcessRun pr where pr.piId=?";
		return (ProcessRun) findUnique(hql, new Object[] { piId });
	}

	public List<ProcessRun> getByDefId(Long defId, PagingBean pb) {
		String hql = " from ProcessRun pr where pr.proDefinition.defId=? ";
		return findByHql(hql, new Object[] { defId }, pb);
	}

	public List<ProcessRun> getByUserIdSubject(Long userId, String subject,
			PagingBean pb) {
		ArrayList params = new ArrayList();
		String hql = "select distinct pr from ProcessRun as pr join pr.processForms as pf where pf.creatorId=? order by pr.createtime desc";
		params.add(userId);
		if (StringUtils.isNotEmpty(subject)) {
			hql = hql + " and pr.subject like ?";
			params.add("%" + subject + "%");
		}

		return findByHql(hql, params.toArray(), pb);
	}
}
