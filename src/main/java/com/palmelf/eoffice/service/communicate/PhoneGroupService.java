package com.palmelf.eoffice.service.communicate;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.communicate.PhoneGroup;

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
