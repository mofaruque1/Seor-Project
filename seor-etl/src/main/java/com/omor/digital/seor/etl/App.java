package com.omor.digital.seor.etl;

import com.omor.digital.seor.etl.mac.MacProductsLoader;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String baseFilePath = "/Users/mdomor.faruque/eclipse-workspace-2/data/mac/";
		String[] filePath = { "assorted.txt", "lipsticks.txt", "lipgloss.txt", "powder.txt", "mascara.txt"};
		String[] productType = { "assorted", "lipsticks", "lipgloss", "powder", "mascara" };

		MacProductsLoader macProductsLoader = new MacProductsLoader("trt-mac-products");
		try {
			for (int i = 0; i < productType.length; i++) {
				macProductsLoader.processMacProducts(baseFilePath+filePath[i], productType[i]);
			}

		} catch (Exception e) {
			System.out.println("Error : Couldnot load data to dynamodb " + e.getMessage());
		}

	}
}
