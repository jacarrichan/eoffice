package com.palmelf.test.system;

import com.palmelf.eoffice.dao.system.AppUserDao;
import com.palmelf.eoffice.dao.system.DiaryDao;
import com.palmelf.eoffice.model.system.Diary;
import com.palmelf.test.BaseTestCase;

import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class DiaryDaoTestCase extends BaseTestCase {

	@Resource
	private DiaryDao diaryDao;

	@Resource
	private AppUserDao appUserDao;

	@Test
	@Rollback(false)
	public void add() {
		Diary diary = new Diary();

		diary.setAppUser(this.appUserDao.get(new Long(1L)));
		diary.setContent("DEST");
		diary.setDayTime(new Date());
		diary.setDiaryType((short) 1);

		this.diaryDao.save(diary);
	}
}
