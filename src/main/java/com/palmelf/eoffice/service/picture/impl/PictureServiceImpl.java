/**
* <p>Title: Picture</p>
* <p>Description: Picture</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: RedAntSoft.com</p>
* <p>@author: RedAntSoft</p>
* <p>@version 1.0beta </p>
**/

package com.palmelf.eoffice.service.picture.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.picture.PictureDao;
import com.palmelf.eoffice.model.picture.*;
import com.palmelf.eoffice.service.picture.PictureService;

import java.util.List;

public class PictureServiceImpl extends BaseServiceImpl<Picture> implements PictureService {
	private PictureDao dao;

	public PictureServiceImpl(PictureDao dao) {
		super(dao);
	}
}
