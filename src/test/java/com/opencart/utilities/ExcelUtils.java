package com.opencart.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	public String path;

	public ExcelUtils(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();

		workbook.close();
		fi.close();
		return rowcount;
	}

	public int getCellCount(String sheetName, int rowNo) throws IOException {

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		int cellcount = row.getLastCellNum();

		workbook.close();
		fi.close();
		return cellcount;
	}

	public String getCellData(String sheetName, int rowNo, int colNo) throws IOException {

		String data;

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		cell = row.getCell(colNo);

		DataFormatter formatter = new DataFormatter();
		try {
			data = formatter.formatCellValue(cell); // returns formated cell value as a String.
		} catch (Exception e) {
			data = "";
		}

		workbook.close();
		fi.close();
		return data;
	}

	public void setCellData(String sheetName, int rowNo, int colNo, String data) throws IOException {

		File xlfile = new File(path);

//		if file exist then proceed to write
		if (xlfile.exists()) {
			fo = new FileOutputStream(path);
			workbook = new XSSFWorkbook();
			workbook.write(fo);
		}

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);

//		if sheet not exist then create sheet
		if (workbook.getSheetIndex(sheetName) == -1) {
			workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);
		}

//		if row not exist then create new row
		if (sheet.getRow(rowNo) == null) {
			sheet.createRow(rowNo);
		}
		row = sheet.getRow(rowNo);

//		create cell
		cell = row.createCell(colNo);
		cell.setCellValue(data);

		fo = new FileOutputStream(path);
		workbook.write(fo);

		workbook.close();
		fi.close();
		fo.close();
	}

	public void fillGreenColor(String sheetName, int rowNo, int colNo) throws IOException {

		fi = new FileInputStream(path);
		fo = new FileOutputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		cell = row.getCell(colNo);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fo);

		workbook.close();
		fi.close();
		fo.close();
	}

	public void fillRedColor(String sheetName, int rowNo, int colNo) throws IOException {

		fi = new FileInputStream(path);
		fo = new FileOutputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		cell = row.getCell(colNo);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fo);

		workbook.close();
		fi.close();
		fo.close();
	}

}
