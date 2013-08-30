package com.cyjt.oa.dao.communicate.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.communicate.MailFolderDao;
import com.cyjt.oa.model.communicate.MailFolder;
import java.util.List;

public class MailFolderDaoImpl extends BaseDaoImpl<MailFolder> implements
		MailFolderDao {
	public MailFolderDaoImpl() {
		super(MailFolder.class);
	}

	public List<MailFolder> getUserFolderByParentId(Long userId, Long parentId) {
		String hql = "from MailFolder mf where mf.appUser.userId=? and parentId=?";
		return findByHql(hql, new Object[] { userId, parentId });
	}

	public List<MailFolder> getAllUserFolderByParentId(Long userId,
			Long parentId) {
		String hql = "from MailFolder mf where mf.appUser.userId=? and parentId=? or userId is null";
		return findByHql(hql, new Object[] { userId, parentId });
	}

	public List<MailFolder> getFolderLikePath(String path) {
		String hql = "from MailFolder mf where mf.path like ?";
		return findByHql(hql, new Object[] { path + '%' });
	}
}
