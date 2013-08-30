package com.cyjt.oa.service.admin;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.admin.Conference;
import java.util.Date;
import java.util.List;

public abstract interface ConferenceService extends BaseService<Conference> {
	public abstract List<Conference> getConfTopic(String paramString,
			PagingBean paramPagingBean);

	public abstract String baseUserIdSearchFullName(String paramString);

	public abstract Conference send(Conference paramConference,
			String paramString1, String paramString2, String paramString3,
			String paramString4);

	public abstract Conference temp(Conference paramConference,
			String paramString1, String paramString2, String paramString3,
			String paramString4);

	public abstract String judgeBoardRoomNotUse(Date paramDate1,
			Date paramDate2, Long paramLong);

	public abstract String apply(Long paramLong, String paramString,
			boolean paramBoolean);
}
