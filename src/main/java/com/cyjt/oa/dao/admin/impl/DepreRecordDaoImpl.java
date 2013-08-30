package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.DepreRecordDao;
import com.cyjt.oa.model.admin.DepreRecord;
import java.util.Date;

public class DepreRecordDaoImpl extends BaseDaoImpl<DepreRecord> implements
		DepreRecordDao {
	public DepreRecordDaoImpl() {
		super(DepreRecord.class);
	}

	public Date findMaxDate(Long assetsId) {
		String hql = "select max(vo.calTime) from DepreRecord vo where vo.fixedAssets.assetsId=?";
		Date date = (Date) findUnique(hql, new Object[] { assetsId });
		return date;
	}
}
