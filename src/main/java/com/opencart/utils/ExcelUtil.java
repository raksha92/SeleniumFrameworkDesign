package com.opencart.utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExcelUtil {

	private static String FILE_PATH = "C:\\Users\\A\\eclipse-workspace\\FrameworkDesignSessions\\src\\test\\resources\\testdata\\OpenCartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	private static int rowCount;
	private static int columnCount;
	
	public static Object [][] getTestData(String sheetName) {
		Object data [][] = null;
		try {
			FileInputStream ip = new FileInputStream(FILE_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			rowCount = sheet.getLastRowNum();
			columnCount = sheet.getRow(0).getLastCellNum();
			
			data = new Object[rowCount][columnCount];
			
			for(int rowNo=0; rowNo<rowCount; rowNo++) {
				for(int columnNo=0; columnNo<columnCount; columnNo++) {
					data [rowNo][columnNo] = sheet.getRow(rowNo+1).getCell(columnNo).toString();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
