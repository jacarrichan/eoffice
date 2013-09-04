package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.BoardTypeDao;
import com.palmelf.eoffice.model.admin.BoardType;

public class BoardTypeDaoImpl extends BaseDaoImpl<BoardType> implements
		BoardTypeDao {
	public BoardTypeDaoImpl() {
		super(BoardType.class);
	}
}
