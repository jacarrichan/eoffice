package com.cyjt.oa.service.archive;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.archive.ArchivesDoc;
import java.util.List;

public abstract interface ArchivesDocService extends BaseService<ArchivesDoc> {
	public abstract List<ArchivesDoc> findByAid(Long paramLong);
}
