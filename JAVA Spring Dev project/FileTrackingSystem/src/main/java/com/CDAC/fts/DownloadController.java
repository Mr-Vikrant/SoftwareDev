package com.CDAC.fts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CDAC.fts.DAO.FileDao;
import com.CDAC.fts.DAO.FileTrackDao;
import com.CDAC.fts.DTO.FileDto;

@Controller
@RequestMapping("/download")
public class DownloadController {
	@Autowired
	 ServletContext context;

	 @RequestMapping("/pdf/{fileName:.+}")
	 public void downloader(HttpServletRequest request, HttpServletResponse response,@PathVariable("fileName") int fileName) {
	  
	  System.out.println("Downloading file :- " + fileName);
      FileDao fdao=new FileDao();
     
	  /*String downloadFolder = context.getRealPath("/WEB-INF/download/");*/
	  
      String fileStr=fdao.downloadFile(fileName);
      System.out.println("fileStr: "+ fileStr);
      Path file = Paths.get(fileStr);
	  // Check if file exists
	  if (Files.exists(file)) {
	   // set content type 
	   response.setContentType("application/pdf");
	   // add response header
	   response.addHeader("Content-Disposition", "attachment; filename=" + fileName+".pdf");
	   try {
	    //copies all bytes from a file to an output stream
	    Files.copy(file, response.getOutputStream());
	    //flushes output stream
	    response.getOutputStream().flush();
	   } catch (IOException e) {
	    System.out.println("Error :- " + e.getMessage());
	   }
	  } else {
	   System.out.println("Sorry File not found!!!!");
	  }
	 }
}
