package com.cyjt.oa.service.archive;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.archive.ArchHasten;
import java.util.Date;

public abstract interface ArchHastenService extends BaseService<ArchHasten> {
	public abstract Date getLeastRecordByUser(Long paramLong);
}
