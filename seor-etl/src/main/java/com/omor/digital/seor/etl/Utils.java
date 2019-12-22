package com.omor.digital.seor.etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	public static String getFileContentAsString(String fileUrl) {

		BufferedReader bufferedReader = null;
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			bufferedReader = new BufferedReader(new FileReader(fileUrl));
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			System.out.println("Could not load file content | seor-etl | Utils.java | error message : "+e.getMessage());
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return stringBuilder.toString();
	}
}
