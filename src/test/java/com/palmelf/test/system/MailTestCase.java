package com.palmelf.test.system;

import com.palmelf.core.engine.MailEngine;
import com.palmelf.test.BaseTestCase;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.junit.Test;

public class MailTestCase extends BaseTestCase {

	@Resource
	private MailEngine mailEngine;

	@Test
	public void testMail() {
		String tempPath = "mail/flowMail.vm";
		Map model = new HashMap();
		String subject = "来自未央区城管办公系统的待办任务提醒";
		this.mailEngine.sendTemplateMail(tempPath, model, subject, null,
				new String[] { "chenshangxuan@gmail.com" }, null, null, null,
				null, true);
	}
}
