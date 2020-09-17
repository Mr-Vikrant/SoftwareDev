package com.CDAC.fts;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.CDAC.fts.DTO.EmployeeDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("In add");
		/*int x=Integer.parseInt(request.getParameter("t1"));
		int y=Integer.parseInt(request.getParameter("t2"));
		int z=x+y;*/
		ModelAndView mv=new ModelAndView("home");
		EmployeeDto emp = new  EmployeeDto();
		emp.setEmpDesignation("PE");
		emp.setEmpName("CDAC");
		//mv.setViewName("add");
		mv.addObject("emp",emp);
		return mv;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("In home");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value = "/abc", method = RequestMethod.GET)
	public String abc(Locale locale, Model model) {
		System.out.println("In abc");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
}
