package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

public class ExcelReader {
	
	private FileInputStream fileInputStream;
    private Workbook workbook;

    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        if(cell != null) {
        	return cell.getStringCellValue();
        }else {
        	return "";
        }
        
    }
    
    public String getCellData(int sheetIndex, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        if(cell != null) {
        	return cell.getStringCellValue();
        }else {
        	return "";
        }
    }

    public int getRowNumbers(String sheetName) {
        Sheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        return sheet.getLastRowNum();
    }

    public int getColNumbers(String sheetName, int rowNumber) {
        Sheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        Row row = sheet.getRow(rowNumber);
        return row.getLastCellNum();
    }

	public ExcelReader(String path) {
		try {
            this.fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.workbook = WorkbookFactory.create(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
