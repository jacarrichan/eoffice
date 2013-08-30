package com.cyjt.oa.dao.communicate.impl;

import com.cyjt.core.Constants;
import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.communicate.MailBoxDao;
import com.cyjt.oa.model.communicate.MailBox;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class MailBoxDaoImpl extends BaseDaoImpl<MailBox> implements MailBoxDao {
	public MailBoxDaoImpl() {
		super(MailBox.class);
	}

	public Long CountByFolderId(Long folderId) {
		String hql = "select count(*) from MailBox where folderId =?";

		Object count = findUnique(hql, new Object[] { folderId });

		return new Long(count.toString());
	}

	public List<MailBox> findByFolderId(Long folderId) {
		String hql = "from MailBox where folderId = ?";
		return findByHql(hql, new Object[] { folderId });
	}

	public List<MailBox> findBySearch(String searchContent, PagingBean pb) {
		ArrayList params = new ArrayList();

		StringBuffer hql = new StringBuffer(
				"from MailBox mb where mb.delFlag = ? and mb.appUser.userId =?");
		params.add(Constants.FLAG_UNDELETED);
		params.add(ContextUtil.getCurrentUserId());

		if (StringUtils.isNotEmpty(searchContent)) {
			hql.append(" and (mb.mail.subject like ? or mb.mail.content like ?)");
			params.add("%" + searchContent + "%");
			params.add("%" + searchContent + "%");
		}

		hql.append(" order by mb.sendTime desc");
		return findByHql(hql.toString(), params.toArray(), pb);
	}
}
