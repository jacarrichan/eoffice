package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.ArchTemplateDao;
import com.palmelf.eoffice.model.archive.ArchTemplate;
import com.palmelf.eoffice.service.archive.ArchTemplateService;
import com.palmelf.eoffice.service.system.FileAttachService;

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
