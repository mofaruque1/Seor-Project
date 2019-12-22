package com.omor.digital.seor.etl;

import com.omor.digital.seor.etl.mac.MacProductsLoader;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String[] filePath = { "/Users/mdomor.faruque/eclipse-workspace-2/data/mac/assorted.txt",
				"/Users/mdomor.faruque/eclipse-workspace-2/data/mac/lipsticks.txt",
				"/Users/mdomor.faruque/eclipse-workspace-2/data/mac/lipgloss.txt",

		};
		String[] productType = { "assorted", "lipsticks", "lipgloss" };
		
		
		MacProductsLoader macProductsLoader = new MacProductsLoader("trt-mac-products");
		try {
			for (int i = 0; i < productType.length; i++) {
				macProductsLoader.processMacProducts(filePath[i], productType[i]);
			}
			
		} catch (Exception e) {
			System.out.println("Error : Couldnot load data to dynamodb");
		}

	}
}
