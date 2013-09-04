package com.palmelf.eoffice.dao.archive;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.archive.ArchivesDoc;

import java.util.List;

public abstract interface ArchivesDocDao extends BaseDao<ArchivesDoc> {
	public abstract List<ArchivesDoc> findByAid(Long paramLong);
}
