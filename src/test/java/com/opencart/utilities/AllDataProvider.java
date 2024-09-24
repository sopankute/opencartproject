package com.opencart.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class AllDataProvider {

//	DataProvider 1
	@DataProvider(name = "loginData")
	public String[][] getData() throws IOException {

		String path = ".\\testData\\logindata.xlsx";

		ExcelUtils xlutil = new ExcelUtils(path);

		int totalrow = xlutil.getRowCount("login");
		int totalcol = xlutil.getCellCount("login", 1);

		String data[][] = new String[totalrow][totalcol];

		for (int i = 1; i <= totalrow; i++) { // row

			for (int j = 0; j < totalcol; j++) { // cell
				data[i - 1][j] = xlutil.getCellData("login", i, j); // array start form 0=i-1=1-1
			}
		}

		return data;
	}

//	DataProvider 2

//	DataProvider 3

}
