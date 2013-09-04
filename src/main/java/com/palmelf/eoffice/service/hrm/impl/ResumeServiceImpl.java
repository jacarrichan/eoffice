package com.palmelf.eoffice.service.hrm.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.hrm.ResumeDao;
import com.palmelf.eoffice.model.hrm.Resume;
import com.palmelf.eoffice.service.hrm.ResumeService;

public class ResumeServiceImpl extends BaseServiceImpl<Resume> implements
		ResumeService {
	private ResumeDao dao;

	public ResumeServiceImpl(ResumeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
