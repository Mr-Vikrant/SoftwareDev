package com.CDAC.fts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.runner.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.CDAC.fts.DAO.UserDao;
import com.CDAC.fts.DTO.UserDto;

@Controller
public class UserController {
          
	  @RequestMapping(value = "/login",method= {RequestMethod.POST,RequestMethod.GET})
	  public ModelAndView showLogin(UserDto login,HttpSession session,HttpServletRequest request) {
		  String uname=login.getUsername();	  
		  session.setAttribute("uname",uname);
		  System.out.println(session.getAttribute("uname"));
		  UserDao ud=new UserDao();
		  if(ud.validateAdmin(login)==true)
		  {
			 
			  return new ModelAndView("admin_profile","uname",uname);
		  }
		  else if(ud.validateEmp(login)==true)
		  {
			 
			  return new ModelAndView("emp_profile","uname",uname);
		  }
		  else
		  {
			   String message1="invalid credentials";  
			   return new ModelAndView("home","message",message1);
		  }
	    
	  }
	  @RequestMapping(value="/logout",method=RequestMethod.POST)
		public String logout(HttpSession session,HttpServletResponse response)
		{
			session.invalidate();
			return "redirect:/login";
		}
	}

	

