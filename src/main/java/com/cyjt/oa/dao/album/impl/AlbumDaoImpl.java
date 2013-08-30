/**
* <p>Title: Album</p>
* <p>Description: Album</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: RedAntSoft.com</p>
* <p>@author: RedAntSoft</p>
* <p>@version 1.0beta </p>
**/

package com.cyjt.oa.dao.album.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.album.AlbumDao;
import com.cyjt.oa.model.album.*;
import java.util.List;

public class AlbumDaoImpl extends BaseDaoImpl<Album> implements AlbumDao {
	public AlbumDaoImpl() {
		super(Album.class);
	}
}
