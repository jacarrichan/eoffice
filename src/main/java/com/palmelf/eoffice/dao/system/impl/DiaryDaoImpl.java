package com.palmelf.eoffice.dao.system.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.system.DiaryDao;
import com.palmelf.eoffice.model.system.Diary;

import java.util.List;

public class DiaryDaoImpl extends BaseDaoImpl<Diary> implements DiaryDao {
	public DiaryDaoImpl() {
		super(Diary.class);
	}

	public List<Diary> getSubDiary(String userIds, PagingBean pb) {
		String hql = "from Diary vo where vo.appUser.userId in (" + userIds
				+ ") and vo.diaryType=1";
		return findByHql(hql, null, pb);
	}
}
