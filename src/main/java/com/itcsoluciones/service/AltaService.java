package com.itcsoluciones.service;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	// Metodo para guardar archivo OSDE en directorio para almacenamiento y lectura

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
		for (AltaOsde archivoOsde : list) {
			System.out.println(archivoOsde);
			altaOsdeRepo.save(archivoOsde);
			b = true;
		}
		return b;
	}

	// Metodo para guardar archivo X en directorio para almacenamiento y lectura

	/*
	 * public boolean guardarDirectorio2(String fileName) {
	 * System.out.println(fileName); boolean b = false; LecturaExcelPrueba
	 * lecturaExcelPrueba = new LecturaExcelPrueba(); List<AltaPrueba> list = null;
	 * try { list = lecturaExcelPrueba.getDataFromExcel(fileName);
	 * System.out.println(list); } catch (Exception e) {
	 * 
	 * } for (AltaPrueba archivoPrueba : list) { System.out.println(archivoPrueba);
	 * altaPruebaRepo.save(archivoPrueba); b = true; } return b; }
	 */

	// Busqueda de registros en tabla temporal para excel de respuesta (OSDE)
	public List<AltaOsde> getFile1() {
		List<AltaOsde> file1 = altaOsdeRepo.findAll();
		return file1;
	}

	// Busqueda de registros en tabla temporal para excel de respuesta (X FINANCIADOR)
	/*
	 * public List<AltaPrueba> getFile2() { List<AltaPrueba> file2 =
	 * altaPruebaRepo.findAll(); return file2; }
	 */

	// Metodo para crear archivo Excel por consulta a la base de datos.

	public String crearExcelRespuesta(String order) {
		try {
			String[] headers = { "Operador", "Filial", "Delegacion", "POS", "Cod. Prestador", "Nombre", "Especialidad",
					"Cuit Prestador", "Calle" };
			String fileName = "altas-respuesta.xlsx";
			Workbook workbook = new XSSFWorkbook();
			List<AltaOsde> list1 = altaOsdeRepo.findAll();
			// List<AltaPrueba> list2 = altaPruebaRepo.findAll();
			CreationHelper createHelper = workbook.getCreationHelper();
			// Create a Sheet
			Sheet sheet = workbook.createSheet("Alta OSDE");

			// crear una fuente para celdas de encabezado
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			// crear un estilo de celda con la fuente
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// crear celda
			Row headerRow = sheet.createRow(0);

			// create cell
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
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
				row.createCell(4).setCellValue(f1.getCodigo_prestador());
				row.createCell(5).setCellValue(f1.getNombre());
				row.createCell(6).setCellValue(f1.getEspecialidad());
				row.createCell(7).setCellValue(f1.getCuit_prestador());
				row.createCell(8).setCellValue(f1.getCalle());

				j++;
			}
			// Cambiar el tamaño de las columnas para que se ajusten al tamaño del contenido
			for (int i = 0; i < headers.length; i++)
				sheet.autoSizeColumn(i);

			System.out.println("==============");

			// Directorio para guardar archivo generado
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss:");
			FileOutputStream fileOut = new FileOutputStream("/home/luisp/Documentos/prueba-carga/" + (dtf.format(LocalDateTime.now())+ fileName));
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