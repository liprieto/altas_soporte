package com.itcsoluciones.read;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.itcsoluciones.entity.AltaPrueba;

public class LecturaExcelPrueba {

	public List<AltaPrueba> getDataFromExcel(String fname)
			throws IOException, EncryptedDocumentException, InvalidFormatException {

		Workbook workbook = WorkbookFactory.create(new File("C://upload//" + fname));

		Sheet sheet = workbook.getSheetAt(0);

		DataFormatter dataFormatter = new DataFormatter();

		List<AltaPrueba> listemp = new ArrayList<>();

		Iterator<Row> rowIterator = sheet.rowIterator();

		int p = 0;

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			p++;
			if (p > 1) {
				AltaPrueba f2 = new AltaPrueba();

				f2.setNombre(dataFormatter.formatCellValue(row.getCell(0)));
				f2.setApellido(dataFormatter.formatCellValue(row.getCell(1)));
				f2.setCuit(dataFormatter.formatCellValue(row.getCell(2)));
				f2.setTelefono(dataFormatter.formatCellValue(row.getCell(3)));
				f2.setDireccion(dataFormatter.formatCellValue(row.getCell(4)));
				listemp.add(f2);
				// System.out.println(listemp);
			}

		}

		workbook.close();
		return listemp;
	}

}
