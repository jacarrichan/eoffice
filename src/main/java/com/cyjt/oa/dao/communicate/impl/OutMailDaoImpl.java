package com.cyjt.oa.dao.communicate.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.communicate.OutMailDao;
import com.cyjt.oa.model.communicate.OutMail;

public class OutMailDaoImpl extends BaseDaoImpl<OutMail> implements OutMailDao {
	public OutMailDaoImpl() {
		super(OutMail.class);
	}

	public List<OutMail> findByFolderId(Long folderId) {
		String hql = "from OutMail where folderId = ?";
		return this.findByHql(hql, new Object[] { folderId });
	}

	public Long CountByFolderId(Long folderId) {
		String hql = "select count(*) from OutMail where folderId =?";

		Object count = this.findUnique(hql, new Object[] { folderId });

		return new Long(count.toString());
	}

	public Map getUidByUserId(Long userId) {
		String hql = "select om.uid from OutMail om where om.userId =?";
		List uidList = this.findByHql(hql, new Object[] { userId });

		Map uidMap = new HashMap();
		for (Object uid : uidList) {
			uidMap.put(uid, "Y");
		}

		return uidMap;
	}
}
