package com.cyjt.oa.service.hrm.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.hrm.ResumeDao;
import com.cyjt.oa.model.hrm.Resume;
import com.cyjt.oa.service.hrm.ResumeService;

public class ResumeServiceImpl extends BaseServiceImpl<Resume> implements
		ResumeService {
	private ResumeDao dao;

	public ResumeServiceImpl(ResumeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
