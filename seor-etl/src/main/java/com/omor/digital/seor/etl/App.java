package com.omor.digital.seor.etl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.omor.digital.seor.etl.mac.MacProductsLoader;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String baseFilePath = "/Users/mdomor.faruque/eclipse-workspace-2/data/mac/";

		Map<String, String> pathAndType = new HashMap<String, String>();
		//pathAndType.put("assorted.txt", "assorted");
		//pathAndType.put("lipsticks.txt", "lipsticks");
		//pathAndType.put("lipgloss.txt", "lipgloss");
		//pathAndType.put("powder.txt", "powder");
		//pathAndType.put("mascara.txt", "mascara");
		pathAndType.put("eyelash.txt", "eyelash");
		pathAndType.put("concealer.txt", "concealer");
		
		Set<String> key = pathAndType.keySet();
		

		MacProductsLoader macProductsLoader = new MacProductsLoader("trt-mac-products");
		try {

			for (String string : key) {
				macProductsLoader.processMacProducts(baseFilePath+string,pathAndType.get(string));
			}

		} catch (Exception e) {
			System.out.println("Error : Couldnot load data to dynamodb " + e.getMessage());
		}

	}
}
