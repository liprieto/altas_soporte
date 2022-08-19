package com.itcsoluciones.service;

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

import com.itcsoluciones.entity.AltaOsde;
import com.itcsoluciones.entity.AltaPrueba;
import com.itcsoluciones.read.LecturaExcelOsde;
import com.itcsoluciones.read.LecturaExcelPrueba;
import com.itcsoluciones.repository.AltaOsdeRepository;
import com.itcsoluciones.repository.AltaPruebaRepository;

@Service
public class AltaService {
	@Autowired
	private AltaOsdeRepository altaOsdeRepo;
	@Autowired
	private AltaPruebaRepository altaPruebaRepo;

	public List<AltaOsde> getAltas() {
		List<AltaOsde> list = altaOsdeRepo.findAll();
		return list;
	}
	
	//Metodo para guardar archivo OSDE en directorio para almacenamiento y lectura
	
	public boolean guardarDirectorio1(String fileName) {
		System.out.println(fileName);
		boolean b = false;
		LecturaExcelOsde lecturaExcelOsde = new LecturaExcelOsde();
		List<AltaOsde> list = null;
		try {
			list = lecturaExcelOsde.getDataFromExcel(fileName);
			System.out.println(list);
		} catch (Exception e) {

		}
		for (AltaOsde f1 : list) {
			System.out.println(f1);
			altaOsdeRepo.save(f1);
			b = true;
		}
		return b;
	}
	
	//Metodo para guardar archivo X en directorio para almacenamiento y lectura
	
	public boolean guardarDirectorio2(String fileName) {
		System.out.println(fileName);
		boolean b = false;
		LecturaExcelPrueba r2 = new LecturaExcelPrueba();
		List<AltaPrueba> list = null;
		try {
			list = r2.getDataFromExcel(fileName);
			System.out.println(list);
		} catch (Exception e) {

		}
		for (AltaPrueba f2 : list) {
			System.out.println(f2);
			altaPruebaRepo.save(f2);
			b = true;
		}
		return b;
	}

	public List<AltaOsde> getFile1() {
		List<AltaOsde> file1 = altaOsdeRepo.findAll();
		return file1;
	}

	public List<AltaPrueba> getFile2() {
		List<AltaPrueba> file2 = altaPruebaRepo.findAll();
		return file2;
	}
	
	//Metodo para crear archivo Excel por consulta a la base de datos. 
	
	public String createOutPutExcel(String order) {
		try {
			String[] columns = { "col1", "col2", "col3", "col4", "col5", "col6", "col7", "col8", "col9" };
			String fileName = "generated-file.xlsx";
			Workbook workbook = new XSSFWorkbook();
			List<AltaOsde> list1 = altaOsdeRepo.findAll();
			List<AltaPrueba> list2 = altaPruebaRepo.findAll();
			CreationHelper createHelper = workbook.getCreationHelper();
			// Create a Sheet
			Sheet sheet = workbook.createSheet("Employee");

			// crear una fuente para celdas de encabezado
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.RED.getIndex());

			// crear un estilo de celda con la fuente
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// crear celda
			Row headerRow = sheet.createRow(0);

			// create cell
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			// Crear filas y celdas con datos
			int rowNum = 1;
			for (AltaOsde f1 : list1) {
				int j = 2;
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(f1.getOperador());
				row.createCell(1).setCellValue(f1.getFilial());
				row.createCell(2).setCellValue(f1.getDelegacion());
				row.createCell(3).setCellValue(f1.getPos());
				for (AltaPrueba f2 : list2) {
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
			// Cambiar el tamaño de las columnas para que se ajusten al tamaño del contenido
			for (int i = 0; i < columns.length; i++)
				sheet.autoSizeColumn(i);

			System.out.println("==============");
			
			//Directorio para guardar archivo generado
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