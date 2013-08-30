package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.BoardTypeDao;
import com.cyjt.oa.model.admin.BoardType;

public class BoardTypeDaoImpl extends BaseDaoImpl<BoardType> implements
		BoardTypeDao {
	public BoardTypeDaoImpl() {
		super(BoardType.class);
	}
}
