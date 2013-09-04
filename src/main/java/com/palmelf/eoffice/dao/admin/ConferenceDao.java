package com.palmelf.eoffice.dao.admin;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.admin.Conference;

import java.util.Date;
import java.util.List;

public abstract interface ConferenceDao extends BaseDao<Conference> {
	public abstract List<Conference> getConfTopic(String paramString,
			PagingBean paramPagingBean);

	public abstract Conference send(Conference paramConference,
			String paramString1, String paramString2, String paramString3,
			String paramString4);

	public abstract Conference temp(Conference paramConference,
			String paramString1, String paramString2, String paramString3,
			String paramString4);

	public abstract String baseUserIdSearchFullName(String paramString);

	public abstract String judgeBoardRoomNotUse(Date paramDate1,
			Date paramDate2, Long paramLong);

	public abstract String apply(Long paramLong, String paramString,
			boolean paramBoolean);
}
