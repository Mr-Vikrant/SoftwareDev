package com.CDAC.fts.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.CDAC.fts.CONNECTION.MyCon;
import com.CDAC.fts.DTO.EmployeeDto;
import com.CDAC.fts.DTO.UserDto;

public class UserDao {
	@Autowired
	private JdbcTemplate jt;
	@Autowired
	private DataSource dataSource;
	private String selectSql;
	private MyCon mcon;
    
	      public UserDao() 
	      {
		     mcon=new MyCon();
	      }
		public Boolean validateAdmin(UserDto login){
	    	  String sql="select * from logintable where username=? and password=md5(?) and isAdmin='T'";
	       boolean flag=false;
			try {
				Connection con=mcon.getConn();
	    	    PreparedStatement ps=con.prepareStatement(sql);
	    	    ps.setString(1,login.getUsername());
		    	ps.setString(2,login.getPassword());
	    	    ResultSet rs=ps.executeQuery();
	    	   
	            if(rs.next()==true)
	            {
	        	flag=true;
	            }
	        
			    } 
			catch (SQLException e) {
				                     e.printStackTrace();
			                       } 
			return flag;
		  }  
	      public Boolean validateEmp(UserDto login){
	    	 /* String sql = "select * from logintable where username='" + login.getUsername() + "' and password='" + login.getPassword()
			    + "' and isAdmin='F'";*/
	    	  String sql="select * from logintable where username=? and password=md5(?) and isAdmin='F'";
	          boolean flag=false;
			try {
			     Connection con=mcon.getConn();	   
	    	PreparedStatement ps=con.prepareStatement(sql);
	    	ps.setString(1,login.getUsername());
	    	ps.setString(2,login.getPassword());
	    	ResultSet rs=ps.executeQuery();
	        if(rs.next()==true)
	        {
	        	flag=true;
	        }
	        
			} catch (SQLException e) {
				                     e.printStackTrace();
			                         }
			return flag;
		  }  
}	

