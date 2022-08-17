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

import com.itcsoluciones.entity.AltaOsde;

public class ReadExcelOsde {

	public List<AltaOsde> getDataFromExcel(String fileName)
			throws IOException, EncryptedDocumentException, InvalidFormatException {

		Workbook workbook = WorkbookFactory.create(new File("/home/luisp/Documentos/prueba-carga/" + fileName));
				
		Sheet sheet = workbook.getSheetAt(0);

		DataFormatter dataFormatter = new DataFormatter();

		List<AltaOsde> listemp = new ArrayList<>();

		Iterator<Row> rowIterator = sheet.rowIterator();

		int p = 0;

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			p++;
			if (p > 1) {
				AltaOsde altaOsde = new AltaOsde();

				altaOsde.setCol1(dataFormatter.formatCellValue(row.getCell(0)));
				altaOsde.setCol2(dataFormatter.formatCellValue(row.getCell(1)));
				altaOsde.setCol3(dataFormatter.formatCellValue(row.getCell(2)));
				altaOsde.setCol4(dataFormatter.formatCellValue(row.getCell(3)));
				

				listemp.add(altaOsde);

			}

		}

		workbook.close();
		//System.out.println(listemp);
		return listemp;
	}

}
