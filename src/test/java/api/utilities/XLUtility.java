package api.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	String path;
	FileInputStream fi;
	FileOutputStream fo;
	XSSFWorkbook workBook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	XLUtility(String path) {
		this.path = path;

	}

	public int getRowCount(String sheetName) throws IOException {

		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workBook.close();
		fi.close();
		return rowCount;

	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {

		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workBook.close();
		fi.close();
		return cellCount;

	}

	public String getCellData(String sheetName, int rowNum, int cellNow) throws IOException {

		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNow);

		DataFormatter formate = new DataFormatter();
		String data;
		try {
			data = formate.formatCellValue(cell);
		} catch (Exception e) {
			data="";
		}
		
		workBook.close();
		fi.close();
		return data;
	}

}
