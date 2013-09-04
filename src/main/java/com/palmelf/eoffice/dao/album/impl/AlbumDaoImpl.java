/**
* <p>Title: Album</p>
* <p>Description: Album</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: RedAntSoft.com</p>
* <p>@author: RedAntSoft</p>
* <p>@version 1.0beta </p>
**/

package com.palmelf.eoffice.dao.album.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.album.AlbumDao;
import com.palmelf.eoffice.model.album.*;

import java.util.List;

public class AlbumDaoImpl extends BaseDaoImpl<Album> implements AlbumDao {
	public AlbumDaoImpl() {
		super(Album.class);
	}
}
