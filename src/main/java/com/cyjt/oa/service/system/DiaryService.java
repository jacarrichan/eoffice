package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.system.Diary;
import java.util.List;

public abstract interface DiaryService extends BaseService<Diary> {
	public abstract List<Diary> getAllBySn(PagingBean paramPagingBean);

	public abstract List<Diary> getSubDiary(String paramString,
			PagingBean paramPagingBean);
}
