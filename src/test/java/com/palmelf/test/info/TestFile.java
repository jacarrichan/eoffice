package com.palmelf.test.info;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TestFile {
	public static String getContents(File aFile) {
		StringBuilder contents = new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null;

				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return contents.toString();
	}

	public static void setContents(File aFile, String aContents)
			throws FileNotFoundException, IOException {
		if (aFile == null) {
			throw new IllegalArgumentException("File should not be null.");
		}
		if (!aFile.exists()) {
			throw new FileNotFoundException("File does not exist: " + aFile);
		}
		if (!aFile.isFile()) {
			throw new IllegalArgumentException("Should not be a directory: "
					+ aFile);
		}
		if (!aFile.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: "
					+ aFile);
		}

		Writer output = new BufferedWriter(new FileWriter(aFile));
		try {
			output.write(aContents);
		} finally {
			output.close();
		}
	}

	public static void main(String[] aArguments) throws IOException {
		File testFile = new File("D:\\temp\\blah.txt");
		if (!testFile.exists()) {
			testFile.createNewFile();
		}
		System.out.println("Original file contents: " + getContents(testFile));
		setContents(testFile,
				"The content of this file has been overwritten...");
		System.out.println("New file contents: " + getContents(testFile));
	}
}
