package com.demo.read;

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

import com.demo.entity.FileOne;

public class ReadEmp1 {

	// public static final String SAMPLE_XLS_FILE_PATH = "E://Excel/anish.xls";
	// public static final String SAMPLE_XLSX_FILE_PATH = "E://Excel/anish.xlsx";

	public List<FileOne> getDataFromExcel(String fname)
			throws IOException, EncryptedDocumentException, InvalidFormatException {

		Workbook workbook = WorkbookFactory.create(new File("/home/luisp/Documentos/prueba-carga/" + fname));

		Sheet sheet = workbook.getSheetAt(0);

		DataFormatter dataFormatter = new DataFormatter();

		List<FileOne> listemp = new ArrayList<>();

		Iterator<Row> rowIterator = sheet.rowIterator();

		int p = 0;

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			p++;
			if (p > 1) {
				FileOne f1 = new FileOne();

				f1.setCol1(dataFormatter.formatCellValue(row.getCell(0)));
				f1.setCol2(dataFormatter.formatCellValue(row.getCell(1)));
				f1.setCol3(dataFormatter.formatCellValue(row.getCell(2)));
				f1.setCol4(dataFormatter.formatCellValue(row.getCell(3)));
				

				listemp.add(f1);

			}

		}

		workbook.close();
		// System.out.println(listemp);
		return listemp;
	}

}
