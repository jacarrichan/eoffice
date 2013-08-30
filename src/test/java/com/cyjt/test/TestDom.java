package com.cyjt.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class TestDom {
	public static void main(String args[]) throws SAXException, IOException {
		DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dombuilder = domfac.newDocumentBuilder();
			String s = "<input type='text'/>";
			String str = "<input type='text' id= 'tets' name= 'name' value='1' datatype='int'/>";
			String str2 = "<P align='center'><FONT size='4'>汉字</FONT></P>";
			Document doc = dombuilder.parse(new ByteArrayInputStream(str2
					.getBytes("8859_1")));
			NodeList no = doc.getElementsByTagName("P");
			System.out.print(no.getLength());
			Element input = (Element) no.item(0);
			System.out.print(input.getAttribute("align"));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
