package com.cyjt.oa.service.communicate;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.communicate.PhoneGroup;
import java.util.List;

public abstract interface PhoneGroupService extends BaseService<PhoneGroup> {
	public abstract Integer findLastSn(Long paramLong);

	public abstract PhoneGroup findBySn(Integer paramInteger, Long paramLong);

	public abstract List<PhoneGroup> findBySnUp(Integer paramInteger,
			Long paramLong);

	public abstract List<PhoneGroup> findBySnDown(Integer paramInteger,
			Long paramLong);

	public abstract List<PhoneGroup> getAll(Long paramLong);
}
