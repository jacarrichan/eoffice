package com.cyjt.oa.dao.archive;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.archive.ArchHasten;
import java.util.Date;

public abstract interface ArchHastenDao extends BaseDao<ArchHasten> {
	public abstract Date getLeastRecordByUser(Long paramLong);
}
