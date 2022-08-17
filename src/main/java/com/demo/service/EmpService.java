package com.demo.service;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.EmpRepository;
import com.demo.dao.EmpRepository2;
import com.demo.entity.FileOne;
import com.demo.entity.FileTwo;
import com.demo.read.ReadEmp1;
import com.demo.read.ReadEmp2;

@Service
public class EmpService {
	@Autowired
	private EmpRepository er;
	@Autowired
	private EmpRepository2 er2;

	public List<FileOne> getEmp() {
		List<FileOne> list = er.findAll();
		return list;
	}

	public boolean save1(String fname) {
		System.out.println(fname);
		boolean b = false;
		ReadEmp1 r1 = new ReadEmp1();
		List<FileOne> list = null;
		try {
			list = r1.getDataFromExcel(fname);
			System.out.println(list);
		} catch (Exception e) {

		}
		for (FileOne f1 : list) {
			System.out.println(f1);
			er.save(f1);
			b = true;
		}
		return b;
	}

	public boolean save2(String fname) {
		System.out.println(fname);
		boolean b = false;
		ReadEmp2 r2 = new ReadEmp2();
		List<FileTwo> list = null;
		try {
			list = r2.getDataFromExcel(fname);
			System.out.println(list);
		} catch (Exception e) {

		}
		for (FileTwo f2 : list) {
			System.out.println(f2);
			er2.save(f2);
			b = true;
		}
		return b;
	}

	public List<FileOne> getFile1() {
		List<FileOne> file1 = er.findAll();
		return file1;
	}

	public List<FileTwo> getFile2() {
		List<FileTwo> file2 = er2.findAll();
		return file2;
	}

	public String createOutPutExcel(String order) {
		try {
			String[] columns = { "col1", "col2", "col3", "col4", "col5", "col6", "col7", "col8", "col9" };
			String fileName = "generated-file.xlsx";
			Workbook workbook = new XSSFWorkbook();
			List<FileOne> list1 = er.findAll();
			List<FileTwo> list2 = er2.findAll();
			CreationHelper createHelper = workbook.getCreationHelper();
			// Create a Sheet
			Sheet sheet = workbook.createSheet("Employee");

			// create a font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.RED.getIndex());

			// create a cellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Create a Row
			Row headerRow = sheet.createRow(0);

			// create cell
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			// create Other rows and cells with employees data
			int rowNum = 1;
			for (FileOne f1 : list1) {
				int j = 2;
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(f1.getCol1());
				row.createCell(1).setCellValue(f1.getCol2());
				row.createCell(2).setCellValue(f1.getCol3());
				row.createCell(3).setCellValue(f1.getCol4());
				for (FileTwo f2 : list2) {
					if (j == rowNum) {
						row.createCell(4).setCellValue(f2.getCol5());
						row.createCell(5).setCellValue(f2.getCol6());
						row.createCell(6).setCellValue(f2.getCol7());
						row.createCell(7).setCellValue(f2.getCol8());
						row.createCell(8).setCellValue(f2.getCol9());
					}
					j++;
				}
			}
			// Resize all columns to fit the content size
			for (int i = 0; i < columns.length; i++)
				sheet.autoSizeColumn(i);

			System.out.println("==============");
			FileOutputStream fileOut = new FileOutputStream("/home/luisp/Documentos/Archivos" + fileName);
			workbook.write(fileOut);
			fileOut.close();
			// Closing the Workbook
			workbook.close();
			return fileName;
		} catch (Exception e) {
			return "";
		}

	}
}