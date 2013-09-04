package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.system.Diary;

import java.util.List;

public abstract interface DiaryService extends BaseService<Diary> {
	public abstract List<Diary> getAllBySn(PagingBean paramPagingBean);

	public abstract List<Diary> getSubDiary(String paramString,
			PagingBean paramPagingBean);
}
