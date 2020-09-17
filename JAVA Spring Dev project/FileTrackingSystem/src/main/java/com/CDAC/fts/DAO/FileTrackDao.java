package com.CDAC.fts.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.CDAC.fts.CONNECTION.MyCon;
import com.CDAC.fts.DTO.FileDto;
import com.CDAC.fts.DTO.FileTrackDto;
import com.CDAC.fts.DTO.UserDto;

public class FileTrackDao {
	private MyCon mcon;
	@Autowired
	private HttpSession session;
	//private HttpServletRequest request;

	public FileTrackDao() {
		mcon = new MyCon();
	}
	
	public int insertIntoFileTrack(FileTrackDto ftdto,Date allotdat)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dat=sdf.format(allotdat);
		int flag = 0;
		try 
		{
			Connection con = mcon.getConn();
			String sql = "insert into filetracktable(FileId,FileFrom,FileTo,FileAllocatedDate,FileInputRemarks) values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,ftdto.getEmpNameFrom());
			ps.setString(2,ftdto.getEmpName());
			ps.setString(3,dat);
			ps.setString(4,ftdto.getInputRemarks());
			flag = ps.executeUpdate();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	
	public int updateFileTrack(FileDto fdt)
	{
		int res=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dat=sdf.format(new Date());
		System.out.println("simple date : "+dat);
		String outputMsg=fdt.getFtdto().getOutputRemarks();
		
		try 
		{
			Connection con = mcon.getConn();
			String sql1="select FileTo from filetracktable where fileid=?";
			PreparedStatement ps1=con.prepareStatement(sql1);
			ps1.setInt(1,fdt.getId());
			ResultSet rs=ps1.executeQuery();
			while(rs.next())
			{
				fdt.setFtdto(new FileTrackDto());
				fdt.getFtdto().setEmpName(rs.getString(1));
			}
			String sql = "update FileTrackTable set FileIsSubmitted=?,FileSubmittedOn=?,FileOutputRemarks=? where FileId=? AND FileTo=? AND FileIsSubmitted='F'";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,"T");
			ps.setString(2,dat);
			ps.setString(3,outputMsg);
			ps.setInt(4,fdt.getId());
			ps.setString(5,fdt.getFtdto().getEmpName());
			System.out.println(dat+"----"+outputMsg+"---"+fdt.getId()+"---"+fdt.getFtdto().getEmpName());
			 res =ps.executeUpdate();
			System.out.println(res+" rows updated");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return res;
		
	}
	
	
	public void insertIntoFileTrackAgain(FileDto fdt)
	{
		PreparedStatement ps;
		SimpleDateFormat sdf;
		sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dat=sdf.format(new Date());
		try 
		{
			Connection con = mcon.getConn();
			String sql1="select fileto,fileoutputremarks,filesubmittedon from filetracktable where DATE(FileSubmittedOn)=? and fileid=?";
			ps=con.prepareStatement(sql1);
			ps.setString(1,dat);
			ps.setInt(2,fdt.getId());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				fdt.getFtdto().setEmpNameFrom(rs.getString(1));
				fdt.getFtdto().setInputRemarks(rs.getString(2));
				Timestamp timestamp = rs.getTimestamp(3);
				fdt.getFtdto().setAllottedOn(new java.util.Date(timestamp.getTime()));
			}
			System.out.println(fdt.getFtdto().getAllottedOn());
			sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dat1=sdf.format(fdt.getFtdto().getAllottedOn());
			String sql = "insert into FileTrackTable(FileId,FileFrom,FileTo,FileAllocatedDate,FileInputRemarks) values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1,fdt.getId());
			ps.setString(2,fdt.getFtdto().getEmpNameFrom());
			ps.setString(3,fdt.getEdt().getEmpName());
			ps.setString(4,dat1);
			ps.setString(5,fdt.getFtdto().getInputRemarks());
			ps.executeUpdate();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	public ArrayList<FileTrackDto> createdFiles()
	{
		
		ArrayList<FileTrackDto> list = new ArrayList<FileTrackDto>();
		Connection con = mcon.getConn();
		String sql = "select distinct FileTable.FileId,FileTable.Filename,FileTable.Description,FileTable.FileCreatedOn,FileTable.FilePriority,FileTable.FileLanguage,FileTable.Remarks from FileTable,FileTrackTable where filetracktable.FileIsSubmitted='F'";
		PreparedStatement ps;
		
		try 
		{		
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			FileTrackDto ftdo = new FileTrackDto();
			ftdo.setFdto(new FileDto());
			ftdo.setFileNo(rs.getInt(1));
			ftdo.getFdto().setName(rs.getString(2));
			ftdo.getFdto().setDesc(rs.getString(3));
			Timestamp timestamp = rs.getTimestamp(4);
			ftdo.getFdto().setCreatedOn(new java.util.Date(timestamp.getTime()));
			ftdo.getFdto().setPriority(rs.getString(5));
			ftdo.getFdto().setLanguage(rs.getString(6));
			ftdo.setInputRemarks(rs.getString(7));
			list.add(ftdo);
		}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<FileTrackDto> completedFiles()
	{
		PreparedStatement ps;
		int fileid=0;
		//session=request.getSession(false);
		ArrayList<FileTrackDto> list = new ArrayList<FileTrackDto>();
		Connection con = mcon.getConn();
		try 
		{
			
			String sql = "select distinct FileTable.FileId,FileTable.FileName,FileTable.Description,FileTrackTable.FileAllocatedDate,FileTable.FilePriority,FileTable.FileLanguage,FileTrackTable.FileInputRemarks "
					+ "			from FileTable,FileTrackTable where filetracktable.fileid=fileTable.fileid and fileTo='admin'";
		    
		    ps = con.prepareStatement(sql);
		//	ps.setString(1,uname.getUsername());
			ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			FileTrackDto ftdo = new FileTrackDto();
			ftdo.setFdto(new FileDto());
			ftdo.setFileNo(rs.getInt(1));
			ftdo.getFdto().setName(rs.getString(2));
			ftdo.getFdto().setDesc(rs.getString(3));
			Timestamp timestamp = rs.getTimestamp(4);
			ftdo.getFdto().setCreatedOn(new java.util.Date(timestamp.getTime()));
			ftdo.getFdto().setPriority(rs.getString(5));
			ftdo.getFdto().setLanguage(rs.getString(6));
			ftdo.setInputRemarks(rs.getString(7));
			list.add(ftdo);
			
		}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<FileTrackDto> pendingFiles(HttpSession session)
	{
		String uname=(String) session.getAttribute("uname");
		ArrayList<FileTrackDto> list = new ArrayList<FileTrackDto>();
		Connection con = mcon.getConn();
		String sql = "select distinct FileTable.FileId,FileTable.Filename,FileTable.Description,FileTable.FileCreatedOn,FileTable.FilePriority,FileTable.FileLanguage,FiletrackTable.FileInputRemarks,FileTrackTable.FileFrom from FileTable,FileTrackTable where filetracktable.Fileid=filetable.fileid and filetracktable.fileissubmitted='F' and filetracktable.fileto='"+uname+"'";
		PreparedStatement ps;
		try 
		{
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			FileTrackDto ftdo = new FileTrackDto();
			ftdo.setFdto(new FileDto());
			ftdo.setFileNo(rs.getInt(1));
			ftdo.getFdto().setName(rs.getString(2));
			ftdo.getFdto().setDesc(rs.getString(3));
			Timestamp timestamp = rs.getTimestamp(4);
			ftdo.getFdto().setCreatedOn(new java.util.Date(timestamp.getTime()));
			ftdo.getFdto().setPriority(rs.getString(5));
			ftdo.getFdto().setLanguage(rs.getString(6));
			ftdo.setInputRemarks(rs.getString(7));
			ftdo.setEmpNameFrom(rs.getString(8));
			list.add(ftdo);
		}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<FileTrackDto> submittedFiles(HttpSession session)
	{
		PreparedStatement ps;
		int fileid=0;
		System.out.println("in DAO : "+(String)session.getAttribute("uname"));
		String uname=(String) session.getAttribute("uname");
		ArrayList<FileTrackDto> list = new ArrayList<FileTrackDto>();
		Connection con = mcon.getConn();
		try {
		String sql = "select distinct FileTable.FileId,FileTable.FileName,FileTable.Description,FileTracktable.FileAllocatedDate,FileTable.FilePriority,FileTable.FileLanguage,FileTrackTable.FileInputRemarks,FileTrackTable.FileTo "
				+ "from FileTable,FileTrackTable where filetracktable.filefrom='"+uname+"' and filetracktable.Fileid=filetable.fileid";
		
		
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			FileTrackDto ftdo = new FileTrackDto();
			ftdo.setFdto(new FileDto());
			ftdo.setFileNo(rs.getInt(1));
			ftdo.getFdto().setName(rs.getString(2));
			ftdo.getFdto().setDesc(rs.getString(3));
			Timestamp timestamp = rs.getTimestamp(4);
			ftdo.setSubmittedOn(new java.util.Date(timestamp.getTime()));
			ftdo.getFdto().setPriority(rs.getString(5));
			ftdo.getFdto().setLanguage(rs.getString(6));
			ftdo.setInputRemarks(rs.getString(7));
			ftdo.setEmpName(rs.getString(8));
			list.add(ftdo);
			System.out.println(new java.util.Date(timestamp.getTime()));			
		}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<FileTrackDto> trackFile(int id){
		ArrayList<FileTrackDto> list = new ArrayList<FileTrackDto>();
		try {
			Connection con = mcon.getConn();
			String query="select FileTable.Filename, FileTrackTable.Fileto , FileTrackTable.fileallocateddate from FileTable,FileTrackTable where FileTable.FileId=? AND filetracktable.FileId=filetable.FileId";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				FileTrackDto ftdo = new FileTrackDto();
				ftdo.setFdto(new FileDto());
				ftdo.getFdto().setName(rs.getString(1));
				
				ftdo.setEmpName(rs.getString(2));
				Timestamp timestamp = rs.getTimestamp(3);
				ftdo.setAllottedOn(new java.util.Date(timestamp.getTime()));
				list.add(ftdo);
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	
}
