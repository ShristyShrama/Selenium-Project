package com.simplilearn.Pizza_Hut;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	 public String readURLFromExcel(String filePath, String sheetName, int rowNumber, int cellNumber) throws IOException {
	        FileInputStream fis = new FileInputStream(filePath);
	        Workbook workbook = new XSSFWorkbook(fis);
	        Sheet sheet = workbook.getSheet(sheetName);
	        Row row = sheet.getRow(rowNumber);
	        Cell cell = row.getCell(cellNumber);

	        String url = cell.getStringCellValue();

	        workbook.close();
	        fis.close();

	        return url;
	    }
} 
