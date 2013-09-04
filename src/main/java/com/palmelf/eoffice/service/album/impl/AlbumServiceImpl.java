/**
* <p>Title: Album</p>
* <p>Description: Album</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: RedAntSoft.com</p>
* <p>@author: RedAntSoft</p>
* <p>@version 1.0beta </p>
**/

package com.palmelf.eoffice.service.album.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.album.AlbumDao;
import com.palmelf.eoffice.model.album.*;
import com.palmelf.eoffice.service.album.AlbumService;

import java.util.List;

public class AlbumServiceImpl extends BaseServiceImpl<Album> implements AlbumService {
	private AlbumDao dao;

	public AlbumServiceImpl(AlbumDao dao) {
		super(dao);
	}
}
