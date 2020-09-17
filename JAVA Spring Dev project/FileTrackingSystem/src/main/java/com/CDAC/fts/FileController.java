package com.CDAC.fts;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.CDAC.fts.DAO.FileDao;
import com.CDAC.fts.DAO.FileTrackDao;
import com.CDAC.fts.DTO.FileDto;
import com.CDAC.fts.DTO.FileTrackDto;

@Controller
public class FileController {
	String message;
    @RequestMapping(value="/fileins")
    public ModelAndView adminFileCreationAndForward(@RequestParam("fileToUpload") MultipartFile file,@ModelAttribute("file") FileDto fdt,HttpSession session)
    {
    	System.out.println("hellobrotherADMIN");
    	//String path="C:\\apache-tomcat-8.0.44\\webapps\\Admin\\";
        String path="C:\\apache-tomcat-8.0.44\\webapps\\Admin\\"+fdt.getRefNo()+"\\";
        new File(path).mkdir();
        String filename=file.getOriginalFilename();
        String finalpath=path+filename;
        FileDao fd=new FileDao();
      
        fd.insertFile(finalpath,fdt);
           
        try {
        	byte barr[]=file.getBytes();
        	BufferedOutputStream bout=new BufferedOutputStream(
        			new FileOutputStream(path+File.separator+filename));
        	Object o=bout;
        	bout.write(barr);
        	
        	bout.flush();
        	bout.close();
        	
        }
        catch (Exception e) {
			System.out.println(e);
		}
        fd.insertIntoFileTrackByAdmin(fdt);
       
    	message="File inserted";
    	return new ModelAndView("admin_profile","message",message);
    	
    }
    @RequestMapping(value="/empfiletransact")
    public ModelAndView empFileTransaction(@RequestParam("fileToUpload") MultipartFile file,@ModelAttribute("file") FileDto fdt,HttpSession session,HttpServletRequest request)
    {
    	System.out.println("hellobrotherEMPLOYEE");
    	System.out.println(fdt.getEdt().getEmpName());
        String path="C:\\apache-tomcat-8.0.44\\webapps\\Admin\\"+fdt.getRefNo()+"\\";
        new File(path).mkdir();
        String filename=file.getOriginalFilename();
        try {
        	byte barr[]=file.getBytes();
        	BufferedOutputStream bout=new BufferedOutputStream(
        			new FileOutputStream(path+File.separator+filename));
        	Object o=bout;
        	bout.write(barr);
        	
        	bout.flush();
        	bout.close();
        	
        }
        
        catch (Exception e) {
			System.out.println(e);
		}
        
      
        FileTrackDao ftdao=new FileTrackDao();
        if(ftdao.updateFileTrack(fdt) ==1 ) {
        	ftdao.insertIntoFileTrackAgain(fdt);
        	String uname=(String) session.getAttribute("uname");
            System.out.println("username="+uname);
            return new ModelAndView("emp_profile","uname",uname);
        }
        else {
        	return new ModelAndView("emp_profile","errorMsg","Failed To update.");
        }
        
    }
    @RequestMapping(value="/empforward")
    public ModelAndView empForward(FileDto fdt,HttpSession session,HttpServletRequest request)
    {
    	  FileTrackDao ftdao=new FileTrackDao();
          if(ftdao.updateFileTrack(fdt) ==1 ) {
          	ftdao.insertIntoFileTrackAgain(fdt);
          	String uname=(String) session.getAttribute("uname");
              System.out.println("username="+uname);
              return new ModelAndView("emp_profile","uname",uname);
          }
          else {
          	return new ModelAndView("emp_profile","errorMsg","Failed To update.");
          }
    }
     
    @RequestMapping(value="/trackfile",method= {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView trackFile(@RequestParam("id") int id,HttpSession session)
    {
    	session.setAttribute("id",id);
    	return new ModelAndView("TrackPage");
    }
    
}
