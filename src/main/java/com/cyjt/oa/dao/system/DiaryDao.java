package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.system.Diary;
import java.util.List;

public abstract interface DiaryDao extends BaseDao<Diary> {
	public abstract List<Diary> getSubDiary(String paramString,
			PagingBean paramPagingBean);
}
