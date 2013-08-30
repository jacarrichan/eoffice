package com.cyjt.oa.dao.document.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.document.DocPrivilegeDao;
import com.cyjt.oa.model.document.DocPrivilege;
import com.cyjt.oa.model.system.AppRole;
import com.cyjt.oa.model.system.AppUser;

public class DocPrivilegeDaoImpl extends BaseDaoImpl<DocPrivilege> implements
		DocPrivilegeDao {
	public DocPrivilegeDaoImpl() {
		super(DocPrivilege.class);
	}

	public List<DocPrivilege> getAll(DocPrivilege docPrivilege, Long folderId,
			PagingBean pb) {
		StringBuffer hql = new StringBuffer("from DocPrivilege vo where 1=1");
		ArrayList list = new ArrayList();
		if (folderId != null) {
			hql.append(" and vo.docFolder.folderId=?");
			list.add(folderId);
		}
		if (docPrivilege != null) {
			if (docPrivilege.getUdrName() != null) {
				hql.append(" and vo.udrName=?");
				list.add(docPrivilege.getUdrName());
			}
			if (docPrivilege.getFlag() != null) {
				hql.append(" and vo.flag=?");
				list.add(docPrivilege.getFlag());
			}
		}
		return this.findByHql(hql.toString(), list.toArray(), pb);
	}

	public List<DocPrivilege> getByPublic(DocPrivilege docPrivilege, Long urdId) {
		StringBuffer sb = new StringBuffer("from DocPrivilege vo where 1=1");
		return this.findByHql(sb.toString());
	}

	public List<Integer> getRightsByFolder(AppUser user, Long folderId) {
		List rights = new ArrayList();
		List list = new ArrayList();
		StringBuffer buff = new StringBuffer(
				"from DocPrivilege vo where vo.docFolder.folderId=?");
		list.add(folderId);
		buff.append(" and (");
		if (user != null) {
			buff.append("(vo.udrId=? and vo.flag=1)");
			list.add(Integer.valueOf(Integer.parseInt(user.getUserId()
					.toString())));
		}
		if (user.getDepartment() != null) {
			buff.append(" or (vo.udrId=? and vo.flag=2)");
			list.add(Integer.valueOf(Integer.parseInt(user.getDepartment()
					.getDepId().toString())));
		}
		Iterator it;
		if ((user.getRoles() != null) && (user.getRoles().size() > 0)) {
			Set roles = user.getRoles();
			StringBuffer sb = new StringBuffer();
			it = roles.iterator();
			while (it.hasNext()) {
				sb.append(((AppRole) it.next()).getRoleId() + ",");
			}
			if (roles.size() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
			if (sb != null) {
				buff.append(" or (vo.udrId in (" + sb + ") and vo.flag=3)");
			}
		}
		buff.append(" )");
		List<DocPrivilege> docPr = this.findByHql(buff.toString(),
				list.toArray());
		if (docPr != null) {
			for (DocPrivilege doc : docPr) {
				rights.add(doc.getRights());
			}
		}
		return rights;
	}

	public Integer getRightsByDocument(AppUser user, Long docId) {
		List list = new ArrayList();
		StringBuffer buff = new StringBuffer(
				"select pr from Document doc,DocFolder docF,DocPrivilege pr where doc.docFolder=docF and pr.docFolder=docF and pr.rights>0 and doc.docId=?");
		list.add(docId);
		buff.append(" and (");
		if (user != null) {
			buff.append("(pr.udrId=? and pr.flag=1)");
			list.add(Integer.valueOf(Integer.parseInt(user.getUserId()
					.toString())));
		}
		if (user.getDepartment() != null) {
			buff.append(" or (pr.udrId=? and pr.flag=2)");
			list.add(Integer.valueOf(Integer.parseInt(user.getDepartment()
					.getDepId().toString())));
		}
		if ((user.getRoles() != null) && (user.getRoles().size() > 0)) {
			Set roles = user.getRoles();
			StringBuffer sb = new StringBuffer();
			Iterator it = roles.iterator();
			while (it.hasNext()) {
				sb.append(((AppRole) it.next()).getRoleId() + ",");
			}
			if (roles.size() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
			if (sb != null) {
				buff.append(" or (pr.udrId in (" + sb + ") and pr.flag=3)");
			}
		}
		buff.append(" )");
		List<DocPrivilege> docPr = this.findByHql(buff.toString(),
				list.toArray());
		Integer right = Integer.valueOf(0);
		if (docPr != null) {
			for (DocPrivilege doc : docPr) {
				right = Integer.valueOf(right.intValue()
						| doc.getRights().intValue());
			}
		}
		return right;
	}

	public Integer countPrivilege() {
		String hql = "from DocPrivilege pr";
		List list = this.findByHql(hql);
		return Integer.valueOf(list.size());
	}
}
