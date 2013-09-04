package com.palmelf.eoffice.dao.system.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.system.FileAttachDao;
import com.palmelf.eoffice.model.system.FileAttach;

public class FileAttachDaoImpl extends BaseDaoImpl<FileAttach> implements
		FileAttachDao {
	public FileAttachDaoImpl() {
		super(FileAttach.class);
	}

	public void removeByPath(final String filePath) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session
						.createQuery("delete from FileAttach fa where fa.filePath = ?");
				query.setString(0, filePath);
				return Integer.valueOf(query.executeUpdate());
			}
		});
	}

	public FileAttach getByPath(String filePath) {
		String hql = "from FileAttach fa where fa.filePath = ?";
		return (FileAttach) this.findUnique(hql, new Object[] { filePath });
	}

	public List<FileAttach> fileList(PagingBean pb, String fileType, boolean bo) {
		String creator = ContextUtil.getCurrentUser().getFullname();
		ArrayList paramList = new ArrayList();
		paramList.add(creator);
		String hql = "select f from FileAttach f where f.creator = ? and ";
		if ((fileType != null) && (!fileType.equals(""))) {
			hql = hql + "f.fileType like ? and ";
			paramList.add(fileType);
		}
		if (bo) {
			hql = hql + "f.ext not in('bmp','png','jpg','gif','tiff') ";
		} else {
			hql = hql + "f.ext in('bmp','png','jpg','gif','tiff') ";
		}
		hql = hql + "order by f.createtime DESC ";
		this.logger.debug("FileAttach：" + hql);
		return this.findByHql(hql, paramList.toArray(), pb);
	}

	public List<FileAttach> fileList(String fileType) {
		String creator = ContextUtil.getCurrentUser().getFullname();
		ArrayList paramList = new ArrayList();
		String hql = "select f from FileAttach f where f.creator = ? and ";
		paramList.add(creator);
		if (!fileType.isEmpty()) {
			hql = hql + "f.fileType like ? ";
			paramList.add(fileType);
		}
		hql = hql + "order by f.createtime DESC ";
		this.logger.debug(hql);
		return this.findByHql(hql, paramList.toArray());
	}
}
