package com.cyjt.oa.dao.hrm.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.hrm.ResumeDao;
import com.cyjt.oa.model.hrm.Resume;

public class ResumeDaoImpl extends BaseDaoImpl<Resume> implements ResumeDao {
	public ResumeDaoImpl() {
		super(Resume.class);
	}
}
