package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.BoardRooDao;
import com.palmelf.eoffice.model.admin.BoardRoo;
import com.palmelf.eoffice.service.admin.BoardRooService;

public class BoardRooServiceImpl extends BaseServiceImpl<BoardRoo> implements
		BoardRooService {
	private BoardRooDao dao;

	public BoardRooServiceImpl(BoardRooDao dao) {
		super(dao);
		this.dao = dao;
	}
}
