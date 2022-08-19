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

	// public static final String SAMPLE_XLS_FILE_PATH = "E://Excel/anish.xls";
	// public static final String SAMPLE_XLSX_FILE_PATH = "E://Excel/anish.xlsx";

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

				f2.setCol5(dataFormatter.formatCellValue(row.getCell(0)));
				f2.setCol6(dataFormatter.formatCellValue(row.getCell(1)));
				f2.setCol7(dataFormatter.formatCellValue(row.getCell(2)));
				f2.setCol8(dataFormatter.formatCellValue(row.getCell(3)));
				f2.setCol9(dataFormatter.formatCellValue(row.getCell(4)));
				listemp.add(f2);
				// System.out.println(listemp);
			}

		}

		workbook.close();
		return listemp;
	}

}
