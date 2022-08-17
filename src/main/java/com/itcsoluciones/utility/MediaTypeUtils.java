package com.itcsoluciones.utility;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;

public class MediaTypeUtils {
	public static MediaType getMediaTypeForFileName(ServletContext servletContext,String fileName)
	{
		String mimeType=servletContext.getMimeType(fileName);
		try
		{
			MediaType mediaType=MediaType.parseMediaType(mimeType);
			return mediaType;
		}
		catch (Exception e) {
			// TODO: handle exception
			return MediaType.MULTIPART_FORM_DATA;
		}
	}

}