package com.palmelf.eoffice.service.archive;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.archive.ArchivesDoc;

import java.util.List;

public abstract interface ArchivesDocService extends BaseService<ArchivesDoc> {
	public abstract List<ArchivesDoc> findByAid(Long paramLong);
}
