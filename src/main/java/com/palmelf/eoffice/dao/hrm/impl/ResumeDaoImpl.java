package com.palmelf.eoffice.dao.hrm.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.hrm.ResumeDao;
import com.palmelf.eoffice.model.hrm.Resume;

public class ResumeDaoImpl extends BaseDaoImpl<Resume> implements ResumeDao {
	public ResumeDaoImpl() {
		super(Resume.class);
	}
}
