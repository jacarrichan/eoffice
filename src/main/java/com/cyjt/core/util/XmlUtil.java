package com.cyjt.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {
	private static final Log logger = LogFactory.getLog(XmlUtil.class);

	public static String docToString(Document document) {
		String s = "";
		String y = "";
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			OutputFormat format = new OutputFormat();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			s = out.toString("UTF-8");
			y = new String(s.getBytes(), "UTF-8");
		} catch (Exception ex) {
			logger.error("docToString error:" + ex.getMessage());
		}
		return y;
	}

	public static Document stringToDocument(String s) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(s);
		} catch (Exception ex) {
			logger.error("stringToDocument error:" + ex.getMessage());
		}
		return doc;
	}

	public static boolean docToXmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new FileOutputStream(filename),
					format);
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			flag = false;
			logger.error("docToXmlFile error:" + ex.getMessage());
		}
		return flag;
	}

	public static boolean stringToXmlFile(String str, String filename) {
		boolean flag = true;
		try {
			Document doc = DocumentHelper.parseText(str);
			flag = docToXmlFile(doc, filename);
		} catch (Exception ex) {
			flag = false;
			logger.error("stringToXmlFile error:" + ex.getMessage());
		}
		return flag;
	}

	public static Document load(String filename) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding("UTF-8");
			document = saxReader.read(new File(filename));
		} catch (Exception ex) {
			logger.error("load XML File error:" + ex.getMessage());
		}
		return document;
	}

	public static Document load(File filename) {
		Document document = null;
		String s = "";
		try {
			s = FileUtils.readFileToString(filename, "utf-8");
		} catch (Exception ex) {
			logger.error("load XML File error:" + ex.getMessage());
		}
		document = stringToDocument(s);
		return document;
	}

	public static Document load(InputStream is) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();

			saxReader.setEncoding("UTF-8");
			document = saxReader.read(is);
		} catch (Exception ex) {
			logger.error("load XML File error:" + ex.getMessage());
		}
		return document;
	}

	public static Document load(InputStream is, String encode) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding(encode);
			document = saxReader.read(is);
		} catch (Exception ex) {
			logger.error("load XML File error:" + ex.getMessage());
		}
		return document;
	}

	public static Document styleDocument(Document document, String stylesheet)
			throws Exception {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource(
				stylesheet));

		DocumentSource source = new DocumentSource(document);
		DocumentResult result = new DocumentResult();
		transformer.transform(source, result);

		Document transformedDoc = result.getDocument();
		return transformedDoc;
	}

	public static void main(String[] args) {
		String filePath = "L:/devtools/workspace/eoffice/web/js/menu.xml";
		String style = "L:/devtools/workspace/eoffice/web/js/menu-public.xsl";
		Document doc = load(filePath);
		try {
			Document another = styleDocument(doc, style);
			System.out.println("xml:" + another.asXML());

			Document publicDoc = another;
			Element rootEl = publicDoc.getRootElement();
			List<?> idNodes = rootEl.selectNodes("/Menus//*");

			System.out.println("size:" + idNodes.size());

			for (int i = 0; i < idNodes.size(); i++) {
				Element el = (Element) idNodes.get(i);
				Attribute attr = el.attribute("id");
				if (attr != null) {
					System.out.println("attr:" + attr.getValue());
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
