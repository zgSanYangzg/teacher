package org.tyrest.core.foundation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: ExcelHelper.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:重新定义方法,使方法闻名知义
 *  TODO
 * 
 *  Notes:
 *  $Id: ExcelHelper.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class ExcelHelper {
	/**
	 * 获得excel文件对象
	 *
	 * @param excelFile
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static Workbook getExcelWorkBook(File excelFile) throws IOException, InvalidFormatException {
		InputStream excelInput = new FileInputStream(excelFile);
		/**
		 * HSSF － 提供读写Microsoft Excel格式档案的功能。 XSSF － 提供读写Microsoft Excel
		 * OOXML格式档案的功能 统一解决方式用WorkbookFactory创建
		 */
		return WorkbookFactory.create(excelInput);
	}

	/**
	 * 获得sheet页数量
	 *
	 * @param workbook
	 * @return
	 */
	public static int getExcelSheets(Workbook workbook) {
		return workbook.getNumberOfSheets();
	}

	/**
	 * 获得sheet也可读取行数
	 *
	 * @param sheet
	 * @return
	 */
	public static int getSheetRows(Sheet sheet) {
		return sheet.getLastRowNum() + 1;
	}

	/**
	 * 获得可读取行数的可读取列数
	 *
	 * @param row
	 * @return
	 */
	public static int getRowColumns(Row row) {
		return row.getLastCellNum();
	}

	/**
	 * TODO.获取workbook中的所有sheet
	 * 
	 * @param workbook
	 * @return
	 */
	public static List<Sheet> getSheetsFromWorkbook(Workbook workbook) {
		List<Sheet> result = new ArrayList<Sheet>();
		int sheetCounts = workbook.getNumberOfSheets();
		Sheet currentSheet = null;
		for (int i = 0; i < sheetCounts; i++) {
			currentSheet = workbook.getSheetAt(i);
			if(currentSheet == null) continue;
			result.add(currentSheet);
		}
		return result;
	}

	public static List<Row> getRowsFromSheet(Sheet sheet) {
		List<Row> rows = new ArrayList<Row>();
		int rowCounts = sheet.getLastRowNum() + 1;
		for (int i = 0; i < rowCounts; i++) {
			rows.add(sheet.getRow(i));
		}
		return rows;
	}

	public static List<Cell> getCellsFromRow(Row row) {
		List<Cell> cells = new ArrayList<Cell>();
		short minColIx = row.getFirstCellNum();
		short maxColIx = row.getLastCellNum();
		for (short colIx = minColIx; colIx < maxColIx; colIx++) {
			cells.add(row.getCell(colIx));
		}
		return cells;
	}
	

	/**
	 * TODO.加载excel的数据结构到内存中
	 * 
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static List<List<Row>> loadExcel2Memory(File excelFile) throws InvalidFormatException, IOException {
		List<List<Row>> excel = new ArrayList<List<Row>>();
		List<Sheet> sheets = getSheetsFromWorkbook(getExcelWorkBook(excelFile));
		for (Sheet sheet : sheets) {
			excel.add(getRowsFromSheet(sheet));
		}
		return excel;
	}
	/**
	 * TODO.获取工作簿中的所有图片
	 * @param workbook
	 * @return
	 */
	public static List<? extends PictureData> getAllPicsFromWorkbook(Workbook workbook){
		List<? extends PictureData> result = workbook.getAllPictures();
		return result;
	}
	
	public static String getValue(Cell cell)
	{
		String value = "";
		switch (cell.getCellType())
		{
		// 数值型
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell))
				{
					Date date = cell.getDateCellValue();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					value = format.format(date);
				}
				else
				{
					value = String.valueOf(cell.getNumericCellValue());
				}
				break;
			// 此行表示单元格的内容为string类型
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			// 公式型
			case Cell.CELL_TYPE_FORMULA:
				value = String.valueOf(cell.getNumericCellValue());
				if (value.equals("NaN"))
				{
					value = cell.getStringCellValue().toString();
				}
				cell.getCellFormula();
				break;
			// 布尔
			case Cell.CELL_TYPE_BOOLEAN:
				value = "" + cell.getBooleanCellValue();
				break;
			// 此行表示该单元格值为空
			case Cell.CELL_TYPE_BLANK:
				value = "";
				break;
			// 故障
			case Cell.CELL_TYPE_ERROR:
				value = "";
				break;
			default:
				value = cell.getStringCellValue().toString();
		}
		return value;
	}
	
	
	public static void main(String[] args) throws InvalidFormatException, IOException {
	}
}

/*
 * $Log: av-env.bat,v $
 */