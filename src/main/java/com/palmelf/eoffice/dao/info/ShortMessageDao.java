package com.palmelf.eoffice.dao.info;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.info.ShortMessage;

import java.util.Date;
import java.util.List;

public abstract interface ShortMessageDao extends BaseDao<ShortMessage> {
	public abstract List<ShortMessage> findAll(Long paramLong,
			PagingBean paramPagingBean);

	public abstract List<ShortMessage> findByUser(Long paramLong);

	public abstract List searchShortMessage(Long paramLong,
			ShortMessage paramShortMessage, Date paramDate1, Date paramDate2,
			PagingBean paramPagingBean);
}
