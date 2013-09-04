package com.palmelf.eoffice.dao.archive.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.archive.ArchivesDao;
import com.palmelf.eoffice.model.archive.Archives;
import com.palmelf.eoffice.model.system.AppRole;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ArchivesDaoImpl extends BaseDaoImpl<Archives> implements
		ArchivesDao {
	public ArchivesDaoImpl() {
		super(Archives.class);
	}

	public List<Archives> findByUserOrRole(Long userId, Set<AppRole> roles,
			PagingBean pb) {
		Iterator it = roles.iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(((AppRole) it.next()).getRoleId().toString());
		}
		StringBuffer hql = new StringBuffer(
				"select distinct vo1 from Archives vo1,ArchDispatch vo2 where vo2.archives=vo1 and vo2.archUserType=2 and (vo2.userId=?");
		if (sb.length() > 0) {
			hql.append(" or vo2.disRoleId in (" + sb + ")");
		}
		hql.append(") ");
		Object[] objs = { userId };
		return findByHql(hql.toString(), objs, pb);
	}
}
