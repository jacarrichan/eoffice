package com.palmelf.test.jbpm;

import com.palmelf.core.util.XmlUtil;

public class GsonTestCase {
	public static void main(String[] args) {
		test();
	}

	public static void test() {
		String path = "D:/WorkSpace/WS20110612/eoffice_gov/src/com/cyjt/test/jbpm/jbpmdef.xml";

		String defXml = XmlUtil.load(path).getRootElement().asXML();

		System.out.println("xml:" + defXml);
	}
}
