package com.cyjt.oa.service.archive;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.archive.ArchivesHandle;
import java.util.List;

public abstract interface ArchivesHandleService extends
		BaseService<ArchivesHandle> {
	public abstract ArchivesHandle findByUAIds(Long paramLong1, Long paramLong2);

	public abstract List<ArchivesHandle> findByAid(Long paramLong);

	public abstract int countHandler(Long paramLong);
}
