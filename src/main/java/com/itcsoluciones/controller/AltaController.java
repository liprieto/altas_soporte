package com.itcsoluciones.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itcsoluciones.service.AltaService;
import com.itcsoluciones.write.DescargaExcel;

@RestController
public class AltaController {

	@Autowired
	private AltaService altaService;

	@Autowired
	private ServletContext servletContext;

	private static String directorio1 = "/home/luisp/Documentos/prueba-carga/";
	private static String directorio2 = "/home/luisp/Documentos/prueba-carga/";

	@PostMapping("/uploadfile1")
	public ResponseEntity<String> singleFileUpload(@RequestParam("file") MultipartFile file) {

		try {

			byte[] bytes = file.getBytes();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss:");
			Path path = Paths.get(directorio1 + (dtf.format(LocalDateTime.now())+ file.getOriginalFilename()));
			Files.write(path, bytes);
			System.out.println(path); 
			
			boolean b = altaService.guardarDirectorio1((dtf.format(LocalDateTime.now()) + file.getOriginalFilename()));

			return new ResponseEntity<>("Archivo cargado con éxito", HttpStatus.OK);

		} catch (IOException e) {
			return new ResponseEntity<>("No se pudo almacenar la fecha", HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	//Endpoint para futuro prestador que utilice el microservicio 
	
	/*@PostMapping("/uploadfile2")
	public ResponseEntity<String> singleFileUpload1(@RequestParam("file") MultipartFile file) {

		try {
   
			byte[] bytes = file.getBytes();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss:");
			Path path = Paths.get(directorio2 + (dtf.format(LocalDateTime.now()) + file.getOriginalFilename()));
			Files.write(path, bytes);

			boolean b = altaService.guardarDirectorio2((dtf.format(LocalDateTime.now()) + file.getOriginalFilename()));

			return new ResponseEntity<>("Archivo cargado con éxito", HttpStatus.OK);

		} catch (IOException e) {
			return new ResponseEntity<>("No se pudo almacenar la fecha", HttpStatus.EXPECTATION_FAILED);
		}

	}*/

	@GetMapping("/download")

	public ResponseEntity<InputStreamResource> reporteAltas(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
 
		String order = request.getParameter("order");

		System.out.println(order);

		String fileName = altaService.crearExcelRespuesta(order);

		MediaType mediaType = DescargaExcel.getMediaTypeForFileName(this.servletContext, fileName);
		System.out.println("fileName: " + fileName);
		System.out.println("mediaType: " + mediaType);

		File file = new File(directorio1 + "/" + fileName);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()

				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				// Content-Type
				.contentType(mediaType)
				// Contet-Length
				.contentLength(file.length()) //
				.body(resource);

	}

}
