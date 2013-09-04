package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.system.Diary;

import java.util.List;

public abstract interface DiaryDao extends BaseDao<Diary> {
	public abstract List<Diary> getSubDiary(String paramString,
			PagingBean paramPagingBean);
}
