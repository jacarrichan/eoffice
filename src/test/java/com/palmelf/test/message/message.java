package com.palmelf.test.message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.eoffice.dao.info.InMessageDao;
import com.palmelf.eoffice.dao.info.ShortMessageDao;
import com.palmelf.eoffice.model.info.InMessage;
import com.palmelf.test.BaseTestCase;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;

public class message extends BaseTestCase {

	@Resource
	private InMessageDao dao;

	@Resource
	private ShortMessageDao dao2;

	@Test
	public void set() {
		List<?> list = this.dao.findByUser(new Long(1L));

		List<InMessage> listk = new ArrayList<InMessage>();

		for (int i = 0; i < list.size(); i++) {
			InMessage inMessage = (InMessage) ((Object[]) list.get(i))[0];

			listk.add(inMessage);
		}

		Gson gson = new Gson();
		Type type = new TypeToken<List<InMessage>>() {
		}.getType();
		System.out.println(gson.toJson(listk, type) + list.size());
	}
}
