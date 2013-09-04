package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.BoardRooDao;
import com.palmelf.eoffice.model.admin.BoardRoo;

public class BoardRooDaoImpl extends BaseDaoImpl<BoardRoo> implements
		BoardRooDao {
	public BoardRooDaoImpl() {
		super(BoardRoo.class);
	}
}
