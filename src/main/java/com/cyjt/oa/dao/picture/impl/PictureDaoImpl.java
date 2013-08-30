/**
* <p>Title: Picture</p>
* <p>Description: Picture</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: RedAntSoft.com</p>
* <p>@author: RedAntSoft</p>
* <p>@version 1.0beta </p>
**/

package com.cyjt.oa.dao.picture.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.picture.PictureDao;
import com.cyjt.oa.model.picture.*;
import java.util.List;

public class PictureDaoImpl extends BaseDaoImpl<Picture> implements PictureDao {
	public PictureDaoImpl() {
		super(Picture.class);
	}
}
