package com.palmelf.eoffice.dao.document;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.document.Document;
import com.palmelf.eoffice.model.system.AppUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract interface DocumentDao extends BaseDao<Document> {
	public abstract List<Document> findByIsShared(Document paramDocument,
			Date paramDate1, Date paramDate2, Long paramLong1,
			ArrayList<Long> paramArrayList, Long paramLong2,
			PagingBean paramPagingBean);

	public abstract List<Document> findByPublic(String paramString,
			Document paramDocument, Date paramDate1, Date paramDate2,
			AppUser paramAppUser, PagingBean paramPagingBean);

	public abstract List<Document> findByPersonal(Long paramLong,
			Document paramDocument, Date paramDate1, Date paramDate2,
			String paramString, PagingBean paramPagingBean);

	public abstract List<Document> findByFolder(String paramString);

	public abstract List<Document> searchDocument(AppUser paramAppUser,
			String paramString, boolean paramBoolean, PagingBean paramPagingBean);
}
