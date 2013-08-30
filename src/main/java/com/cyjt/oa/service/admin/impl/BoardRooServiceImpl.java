package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.BoardRooDao;
import com.cyjt.oa.model.admin.BoardRoo;
import com.cyjt.oa.service.admin.BoardRooService;

public class BoardRooServiceImpl extends BaseServiceImpl<BoardRoo> implements
		BoardRooService {
	private BoardRooDao dao;

	public BoardRooServiceImpl(BoardRooDao dao) {
		super(dao);
		this.dao = dao;
	}
}
