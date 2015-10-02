package com.walmart.logistics.weekly.dashboard.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DashboardUtility {

	public static Sheet getSheet(String sheet, HSSFWorkbook workbook) {
		return sheet.length() > 1 ? workbook.getSheet(sheet) : workbook
				.getSheetAt(Integer.parseInt(sheet));
	}
	
	public static Workbook getWorkBook(String filePath) throws IOException {
		if(!filePath.contains(".xlsx")){
			NPOIFSFileSystem fs = new NPOIFSFileSystem(
					new File(filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), false);
			fs.close();
			return wb;	
		} else {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
			fileInputStream.close();
			return xssfWorkbook;	
		}
	}
}
