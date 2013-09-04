package com.palmelf.eoffice.service.archive;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.archive.ArchHasten;

import java.util.Date;

public abstract interface ArchHastenService extends BaseService<ArchHasten> {
	public abstract Date getLeastRecordByUser(Long paramLong);
}
