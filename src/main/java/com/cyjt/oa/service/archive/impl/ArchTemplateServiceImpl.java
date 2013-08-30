package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.ArchTemplateDao;
import com.cyjt.oa.model.archive.ArchTemplate;
import com.cyjt.oa.service.archive.ArchTemplateService;
import com.cyjt.oa.service.system.FileAttachService;
import javax.annotation.Resource;

public class ArchTemplateServiceImpl extends BaseServiceImpl<ArchTemplate>
		implements ArchTemplateService {
	private ArchTemplateDao dao;

	@Resource
	FileAttachService fileAttachService;

	public ArchTemplateServiceImpl(ArchTemplateDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public void remove(Long id) {
		ArchTemplate template = this.dao.get(id);
		remove(template);
		this.fileAttachService.removeByPath(template.getTempPath());
	}
}
