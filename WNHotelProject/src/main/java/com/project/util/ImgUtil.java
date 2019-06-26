package com.project.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public class ImgUtil {

	public static String SaveImg(MultipartFile img,HttpServletRequest req) {
		String path = req.getServletContext().getRealPath("/images");
		String newName =new Date().getTime()+"_"+UUID.randomUUID().toString();
		File file = new File(path, newName);
		try {
			img.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(file.getAbsolutePath());
		return newName;
	}
}
