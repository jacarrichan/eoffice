package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.BoardTypeDao;
import com.cyjt.oa.model.admin.BoardType;
import com.cyjt.oa.service.admin.BoardTypeService;

public class BoardTypeServiceImpl extends BaseServiceImpl<BoardType> implements
		BoardTypeService {
	private BoardTypeDao dao;

	public BoardTypeServiceImpl(BoardTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
