package com.palmelf.eoffice.dao.archive;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.archive.ArchivesHandle;

import java.util.List;

public abstract interface ArchivesHandleDao extends BaseDao<ArchivesHandle> {
	public abstract ArchivesHandle findByUAIds(Long paramLong1, Long paramLong2);

	public abstract List<ArchivesHandle> findByAid(Long paramLong);
}
