package com.palmelf.commons.util;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 打印classpath
 * 
 * @author Jacarri
 * 
 */
public class PrintClasspath {
	public static void main(String[] args) {

		// Get the System Classloader
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();

		// Get the URLs
		URL[] urls = ((URLClassLoader) sysClassLoader).getURLs();

		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].getFile());
		}

	}
}