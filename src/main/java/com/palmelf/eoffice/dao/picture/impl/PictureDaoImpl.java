/**
* <p>Title: Picture</p>
* <p>Description: Picture</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: RedAntSoft.com</p>
* <p>@author: RedAntSoft</p>
* <p>@version 1.0beta </p>
**/

package com.palmelf.eoffice.dao.picture.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.picture.PictureDao;
import com.palmelf.eoffice.model.picture.*;

import java.util.List;

public class PictureDaoImpl extends BaseDaoImpl<Picture> implements PictureDao {
	public PictureDaoImpl() {
		super(Picture.class);
	}
}
