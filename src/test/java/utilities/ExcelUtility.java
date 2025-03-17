package utilities;

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

public class ExcelUtility {

	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	
	public ExcelUtility(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fis = new FileInputStream(this.path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fis.close();
		return rowCount;
	}
	
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fis = new FileInputStream(this.path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fis.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int RowNum, int colNum) throws IOException {
		fis = new FileInputStream(this.path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(RowNum);
		cell = row.getCell(colNum);
		
		DataFormatter dataFormatter = new DataFormatter();
		String data;
		try {
			data = dataFormatter.formatCellValue(cell);
		}catch(Exception e) {
			data="";
		}
		
		wb.close();
		fis.close();
		return data;
	}
	
	
	public void setCellData(String sheetName, int rowNum, int colNum,String value) throws IOException {
		
		
		File file = new File(this.path);
		if(!file.exists()) {              //If File doesn't exist create a new file
			wb = new XSSFWorkbook();
			fos = new FileOutputStream(this.path);
			wb.write(fos);
		}
		
		fis = new FileInputStream(this.path);
		wb = new XSSFWorkbook(fis);
		
		
		if(wb.getSheetIndex(sheetName)==-1) { //If sheet doesn't exist create a new sheet
			wb.createSheet(sheetName);
		}
		
		if(ws.getRow(rowNum)==null) { //If row doesn't exist create a new row
			ws.createRow(rowNum);
		}
		
		row = ws.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(value);
		
		fos = new FileOutputStream(this.path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}
	
	
	public void fillRedColor(String sheetName, int rowNum, int colNum) throws IOException {
		fis = new FileInputStream(this.path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}
	
	
	public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException {
		fis = new FileInputStream(this.path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}
}
