package com.CDAC.fts.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.CDAC.fts.CONNECTION.MyCon;
import com.CDAC.fts.DTO.EmployeeDto;
import com.CDAC.fts.DTO.FileDto;
import com.CDAC.fts.DTO.FileTrackDto;

public class FileDao {
     private MyCon mcon;
     private int id;
     public FileDao() {
		                mcon=new MyCon();
	                  }

	public void insertFile(String path, FileDto fdt)
     {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String dat=sdf.format(date);
    	 try {		
				       Connection con=mcon.getConn(); 
	                   PreparedStatement ps=con.prepareStatement("insert into filetable (FileRefNo,Filename,Description,FilePriority,FileLanguage,FileLastEdit,FilePath,FileCreatedOn,Remarks) values(?,?,?,?,?,?,?,?,?)");
	                  
	                   ps.setString(1,fdt.getRefNo());
	                   ps.setString(2,fdt.getName());
	                   ps.setString(3,fdt.getDesc());
	                   ps.setString(4,fdt.getPriority());
	                   ps.setString(5,fdt.getLanguage());
	                   ps.setString(6,dat);
	                   ps.setString(7,path);
	                   ps.setString(8,dat);
	                   ps.setString(9,fdt.getRemarks());
	                   int i=ps.executeUpdate();
	                   System.out.println(i+" row inserted");   
				   }
    	  
				 catch (SQLException e) {
						e.printStackTrace();
					} 
     }
	public void insertIntoFileTrackByAdmin(FileDto fdto)
	{
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String dat=sdf.format(date);
		
		
		try 
		{
			Connection con = mcon.getConn();
			String sql1="select FileId from filetable where Filename=?";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1,fdto.getName());
			ResultSet rs=ps1.executeQuery();
			while(rs.next())
			{
			 id=rs.getInt(1);
			}
			System.out.println("ID= "+id);
			String sql = "insert into filetracktable(FileId,FileFrom,FileTo,FileAllocatedDate,FileInputRemarks) values(?,'admin',?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ps.setString(2,fdto.getEdt().getEmpName());
			ps.setString(3,dat);
			ps.setString(4,fdto.getRemarks());
		    ps.executeUpdate();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public String downloadFile(int fileid)
	{
		Connection con = mcon.getConn();
		String path="";
		String sql="select filepath from filetable where fileid=?";
		try 
		{
			PreparedStatement ps=con.prepareStatement(sql);
		    ps.setInt(1,fileid);
		    ResultSet rs=ps.executeQuery();
		    while(rs.next())
		    {
		    	path= rs.getString(1);
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
