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

public class ExcelUtilities {
	public FileInputStream oFis;
	public FileOutputStream oFos;
	public XSSFWorkbook oWorkbook;
	public XSSFSheet oSheet;
	public XSSFRow oRow;
	public XSSFCell oCell;
	public CellStyle oStyle;
	String path;
	
	public ExcelUtilities(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		oFis=new FileInputStream(path);
		oWorkbook=new XSSFWorkbook(oFis);
		oSheet=oWorkbook.getSheet(sheetName);
		int lastRowNum = oSheet.getLastRowNum();
		oWorkbook.close();
		oFis.close();
		return lastRowNum;
	}
	
	public int getCellCount(String sheetName,int rowNum) throws IOException {
		oFis=new FileInputStream(path);
		oWorkbook=new XSSFWorkbook(oFis);
		oSheet = oWorkbook.getSheet(sheetName);
		oRow=oSheet.getRow(rowNum);
	   int cellNum=oRow.getLastCellNum();
	   oWorkbook.close();
	   oFis.close();
	   return cellNum;		
		
	}
	
	public String getCellData(String sheetName,int RowNum,int CellNum) throws IOException {
		oFis=new FileInputStream(path);
		oWorkbook=new XSSFWorkbook(oFis);
		oSheet=oWorkbook.getSheet(sheetName);
		oRow=oSheet.getRow(RowNum);		
		oCell=oRow.getCell(CellNum);
		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			data=formatter.formatCellValue(oCell);
		}catch(Exception e) {
			data="";
		}
		oWorkbook.close();
		oFis.close();
		return data;
	}
	
	public void setCellData(String sheetName,int RowNum,int cellNum,String data) throws IOException {
		File file=new File(path);
		if(!file.exists()) {
			oWorkbook=new XSSFWorkbook();
			oFos=new FileOutputStream(path);
			oWorkbook.write(oFos);
		}
		oFis=new FileInputStream(path);
		oWorkbook=new XSSFWorkbook(oFis);
		if(oWorkbook.getSheetIndex(sheetName)==-1) {
			//If sheet does not exist then create new sheet
			oWorkbook.createSheet(sheetName);
			oSheet=oWorkbook.getSheet(sheetName);
			}
		
		if(oSheet.getRow(RowNum)==null) {
			oSheet.createRow(RowNum);
			oSheet.getRow(RowNum);
		}
		
		oCell=oRow.createCell(cellNum);
		oCell.setCellValue(data);
		oFos=new FileOutputStream(path);
		oWorkbook.write(oFos);
		oWorkbook.close();
		oFis.close();
		oFos.close();  
		
	}
	
	public void fillGreenColor(String sheetName,int RowNum,int ColNum) throws IOException {
		oFis=new FileInputStream(path);
		oWorkbook=new XSSFWorkbook(oFis);
		oSheet=oWorkbook.getSheet(sheetName);
		oRow=oSheet.getRow(RowNum);
		oCell=oRow.getCell(ColNum);
		oStyle = oWorkbook.createCellStyle();
		oStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		oStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		oCell.setCellStyle(oStyle);
		oFos=new FileOutputStream(path);
		oWorkbook.write(oFos);
		oWorkbook.close();
		oFis.close();
		oFos.close();
		
	}
	
	public void fillRedColor(String sheetName,int RowNum,int CellNum) throws IOException {
		oFis=new FileInputStream(path);
		oWorkbook=new XSSFWorkbook(oFis);
		oSheet=oWorkbook.getSheet(sheetName);
		oRow=oSheet.getRow(RowNum);
		oCell=oRow.getCell(CellNum);
		oStyle=oWorkbook.createCellStyle();
		oStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		oStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		oCell.setCellStyle(oStyle);
		oFos=new FileOutputStream(path);
		oWorkbook.write(oFos);
		oWorkbook.close();
		oFis.close();
		oFos.close();
		
		
	}
	
	

}
