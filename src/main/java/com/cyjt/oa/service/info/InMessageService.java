package com.cyjt.oa.service.info;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.info.InMessage;
import com.cyjt.oa.model.info.ShortMessage;
import java.util.Date;
import java.util.List;

public abstract interface InMessageService extends BaseService<InMessage> {
	public abstract InMessage findByRead(Long paramLong);

	public abstract Integer findByReadFlag(Long paramLong);

	public abstract List<InMessage> findAll(Long paramLong,
			PagingBean paramPagingBean);

	public abstract List findByUser(Long paramLong, PagingBean paramPagingBean);

	public abstract List<InMessage> searchInMessage(Long paramLong,
			InMessage paramInMessage, ShortMessage paramShortMessage,
			Date paramDate1, Date paramDate2, PagingBean paramPagingBean);

	public abstract InMessage findLatest(Long paramLong);
}
