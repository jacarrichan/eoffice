package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.BoardTypeDao;
import com.palmelf.eoffice.model.admin.BoardType;
import com.palmelf.eoffice.service.admin.BoardTypeService;

public class BoardTypeServiceImpl extends BaseServiceImpl<BoardType> implements
		BoardTypeService {
	private BoardTypeDao dao;

	public BoardTypeServiceImpl(BoardTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
