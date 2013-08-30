package com.cyjt.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;

public class StringUtil {
	public static StringBuilder formatMsg(CharSequence msgWithFormat,
			boolean autoQuote, Object[] args) {
		int argsLen = args.length;
		boolean markFound = false;

		StringBuilder sb = new StringBuilder(msgWithFormat);

		if (argsLen > 0) {
			for (int i = 0; i < argsLen; i++) {
				String flag = "%" + (i + 1);
				int idx = sb.indexOf(flag);

				while (idx >= 0) {
					markFound = true;
					sb.replace(idx, idx + 2, toString(args[i], autoQuote));
					idx = sb.indexOf(flag);
				}
			}

			if ((args[(argsLen - 1)] instanceof Throwable)) {
				StringWriter sw = new StringWriter();
				((Throwable) args[(argsLen - 1)])
						.printStackTrace(new PrintWriter(sw));
				sb.append("\n").append(sw.toString());
			} else if ((argsLen == 1) && (!markFound)) {
				sb.append(args[(argsLen - 1)].toString());
			}
		}
		return sb;
	}

	public static String toString(Object obj, boolean autoQuote) {
		StringBuilder sb = new StringBuilder();
		if (obj == null) {
			sb.append("NULL");
		} else if ((obj instanceof Object[])) {
			for (int i = 0; i < ((Object[]) obj).length; i++) {
				sb.append(((Object[]) obj)[i]).append(", ");
			}
			if (sb.length() > 0)
				sb.delete(sb.length() - 2, sb.length());
		} else {
			sb.append(obj.toString());
		}

		if ((autoQuote)
				&& (sb.length() > 0)
				&& ((sb.charAt(0) != '[') || (sb.charAt(sb.length() - 1) != ']'))
				&& ((sb.charAt(0) != '{') || (sb.charAt(sb.length() - 1) != '}'))) {
			sb.insert(0, "[").append("]");
		}
		return sb.toString();
	}

	public static StringBuilder formatMsg(String msgWithFormat, Object[] args) {
		return formatMsg(new StringBuilder(msgWithFormat), true, args);
	}

	public static StringBuilder formatMsgNoBracket(String msgWithFormat,
			Object[] args) {
		return formatMsg(new StringBuilder(msgWithFormat), true, args);
	}

	public static String format(String format, Object[] args) {
		return String.format(format, args);
	}

	public static boolean isNum(String str) {
		String regex = "0123456789";

		if (str == null) {
			return false;
		}
		if (str.length() == 0) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (regex.indexOf(str.charAt(i)) == -1)
				return false;
		}
		return true;
	}

	public static String convertQuot(String orgStr) {
		return orgStr.replace("'", "\\'").replace("\"", "\\\"");
	}

	public static synchronized String encryptSha256(String inputStr) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			byte[] digest = md.digest(inputStr.getBytes("UTF-8"));

			return new String(Base64.encodeBase64(digest));
		} catch (Exception e) {
		}
		return null;
	}

	public static String htmlEntityToString(String dataStr) {
		int start = 0;
		int end = 0;
		StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			int system = 10;
			if (start == 0) {
				int t = dataStr.indexOf("&#");
				if (start != t)
					start = t;
			}
			end = dataStr.indexOf(";", start + 2);
			String charStr = "";
			if (end != -1) {
				charStr = dataStr.substring(start + 2, end);

				char s = charStr.charAt(0);
				if ((s == 'x') || (s == 'X')) {
					system = 16;
					charStr = charStr.substring(1);
				}
			}
			try {
				char letter = (char) Integer.parseInt(charStr, system);
				buffer.append(new Character(letter).toString());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			start = dataStr.indexOf("&#", end);
			if (start - end > 1) {
				buffer.append(dataStr.substring(end + 1, start));
			}

			if (start == -1) {
				int length = dataStr.length();
				if (end + 1 != length) {
					buffer.append(dataStr.substring(end + 1, length));
				}
			}
		}
		return buffer.toString();
	}

	public static String stringToHtmlEntity(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			switch (c) {
			case '\n':
				sb.append(c);
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '\'':
				sb.append("&apos;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			default:
				if ((c < ' ') || (c > '~')) {
					sb.append("&#x");
					sb.append(Integer.toString(c, 16));
					sb.append(';');
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	public static String stringToUnicode(String s) {
		String unicode = "";
		char[] charAry = new char[s.length()];
		for (int i = 0; i < charAry.length; i++) {
			charAry[i] = s.charAt(i);
			unicode = unicode + "\\u" + Integer.toString(charAry[i], 16);
		}
		return unicode;
	}

	public static String unicodeToString(String unicodeStr) {
		StringBuffer sb = new StringBuffer();
		String[] str = unicodeStr.toUpperCase().split("\\\\U");
		for (int i = 0; i < str.length; i++)
			if (!str[i].equals("")) {
				char c = (char) Integer.parseInt(str[i].trim(), 16);
				sb.append(c);
			}
		return sb.toString();
	}

	public static void main(String[] args) {
		String vm = "abc.em";
		System.out.println(stringToHtmlEntity(vm));
	}

	public static String html2Text(String inputString) {
		String htmlStr = inputString;
		String textStr = "";
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

			String regEx_html = "<[^>]+>";

			Pattern p_script = Pattern.compile(regEx_script, 2);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");

			Pattern p_style = Pattern.compile(regEx_style, 2);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");

			Pattern p_html = Pattern.compile(regEx_html, 2);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");

			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;
	}

	public static Map<String, String> getMapFromString(String mapString) {
		Map data = new HashMap();
		String[] keyMaps = mapString.split("[|]");
		if (keyMaps != null) {
			for (String keyData : keyMaps) {
				String[] km = keyData.split(":");
				if ((km != null) && (km.length == 2)) {
					data.put(km[0], km[1]);
				}
			}
		}
		return data;
	}
}
