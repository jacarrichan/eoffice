package com.cyjt.oa.dao.document.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.document.DocFolderDao;
import com.cyjt.oa.model.document.DocFolder;
import java.util.List;

public class DocFolderDaoImpl extends BaseDaoImpl<DocFolder> implements
		DocFolderDao {
	public DocFolderDaoImpl() {
		super(DocFolder.class);
	}

	public List<DocFolder> getUserFolderByParentId(Long userId, Long parentId) {
		String hql = "from DocFolder df where df.isShared=0 and df.appUser.userId=? and parentId=?";
		return findByHql(hql, new Object[] { userId, parentId });
	}

	public List<DocFolder> getFolderLikePath(String path) {
		String hql = "from DocFolder df where df.path like ?";
		return findByHql(hql, new Object[] { path + '%' });
	}

	public List<DocFolder> getPublicFolderByParentId(Long parentId) {
		String hql = "from DocFolder df where df.isShared=1 and parentId=? ";
		return findByHql(hql, new Object[] { parentId });
	}
}
