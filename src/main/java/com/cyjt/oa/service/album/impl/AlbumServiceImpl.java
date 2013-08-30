/**
* <p>Title: Album</p>
* <p>Description: Album</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: RedAntSoft.com</p>
* <p>@author: RedAntSoft</p>
* <p>@version 1.0beta </p>
**/

package com.cyjt.oa.service.album.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.album.AlbumDao;
import com.cyjt.oa.model.album.*;
import com.cyjt.oa.service.album.AlbumService;
import java.util.List;

public class AlbumServiceImpl extends BaseServiceImpl<Album> implements AlbumService {
	private AlbumDao dao;

	public AlbumServiceImpl(AlbumDao dao) {
		super(dao);
	}
}
