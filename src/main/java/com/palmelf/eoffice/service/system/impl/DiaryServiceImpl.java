package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.system.DiaryDao;
import com.palmelf.eoffice.model.system.Diary;
import com.palmelf.eoffice.service.system.DiaryService;

import java.util.List;

public class DiaryServiceImpl extends BaseServiceImpl<Diary> implements
		DiaryService {
	private DiaryDao dao;

	public DiaryServiceImpl(DiaryDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Diary> getAllBySn(PagingBean pb) {
		return null;
	}

	public List<Diary> getSubDiary(String userIds, PagingBean pb) {
		return this.dao.getSubDiary(userIds, pb);
	}
}
