package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.BoardRooDao;
import com.cyjt.oa.model.admin.BoardRoo;

public class BoardRooDaoImpl extends BaseDaoImpl<BoardRoo> implements
		BoardRooDao {
	public BoardRooDaoImpl() {
		super(BoardRoo.class);
	}
}
