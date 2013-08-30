package com.cyjt.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BastTest {
	public static void main(String[] args) {
		String s = "PF.CREATORID=? GROUP BY PR.RUNID";
		Pattern p = Pattern.compile(" GROUP BY [\\w|.]+");

		Matcher m = p.matcher(s);
		boolean is = m.find();
		System.out.println("is:" + is);

		int start = m.start();
		int end = m.end();

		System.out.println("start:" + start + "end:" + end
				+ s.substring(start, end));
	}
}
