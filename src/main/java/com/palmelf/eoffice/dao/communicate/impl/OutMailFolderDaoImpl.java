package com.palmelf.eoffice.dao.communicate.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.communicate.OutMailFolderDao;
import com.palmelf.eoffice.model.communicate.OutMailFolder;

import java.util.List;

public class OutMailFolderDaoImpl extends BaseDaoImpl<OutMailFolder> implements
		OutMailFolderDao {
	public OutMailFolderDaoImpl() {
		super(OutMailFolder.class);
	}

	public List<OutMailFolder> getAllUserFolderByParentId(Long userId,
			Long parentId) {
		String hql = "from OutMailFolder mf where mf.appUser.userId=? and parentId=? or userId is null";
		return findByHql(hql, new Object[] { userId, parentId });
	}

	public List<OutMailFolder> getUserFolderByParentId(Long userId,
			Long parentId) {
		String hql = "from OutMailFolder mf where mf.appUser.userId=? and parentId=?";
		return findByHql(hql, new Object[] { userId, parentId });
	}

	public List<OutMailFolder> getFolderLikePath(String path) {
		String hql = "from OutMailFolder mf where mf.path like ?";
		return findByHql(hql, new Object[] { path + '%' });
	}
}
