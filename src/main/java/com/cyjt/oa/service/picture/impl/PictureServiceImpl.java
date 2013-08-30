/**
* <p>Title: Picture</p>
* <p>Description: Picture</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: RedAntSoft.com</p>
* <p>@author: RedAntSoft</p>
* <p>@version 1.0beta </p>
**/

package com.cyjt.oa.service.picture.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.picture.PictureDao;
import com.cyjt.oa.model.picture.*;
import com.cyjt.oa.service.picture.PictureService;
import java.util.List;

public class PictureServiceImpl extends BaseServiceImpl<Picture> implements PictureService {
	private PictureDao dao;

	public PictureServiceImpl(PictureDao dao) {
		super(dao);
	}
}
