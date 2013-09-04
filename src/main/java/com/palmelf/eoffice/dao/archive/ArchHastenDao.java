package com.palmelf.eoffice.dao.archive;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.archive.ArchHasten;

import java.util.Date;

public abstract interface ArchHastenDao extends BaseDao<ArchHasten> {
	public abstract Date getLeastRecordByUser(Long paramLong);
}
