package com.cyjt.oa.dao.archive;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.archive.ArchivesDoc;
import java.util.List;

public abstract interface ArchivesDocDao extends BaseDao<ArchivesDoc> {
	public abstract List<ArchivesDoc> findByAid(Long paramLong);
}
