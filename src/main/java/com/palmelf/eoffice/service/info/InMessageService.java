package com.palmelf.eoffice.service.info;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.info.InMessage;
import com.palmelf.eoffice.model.info.ShortMessage;

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
