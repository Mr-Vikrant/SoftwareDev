package com.CDAC.fts.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.CDAC.fts.DTO.EmployeeDto;

public class EmployeeDao {
		
		private DataSource ds;
		private JdbcTemplate template;
		private String insertSql,updateSql,selectSql;
		
		 	{
		        insertSql="insert into EmployeeTable values(?,?,?,?,?,?,?,?)";
		        updateSql="update EmployeeTable set username=?,empid=?,empname=?,empemail=? where empdesignation=?";
		        selectSql="select * from EmployeeTable where empdesignation=?";
		 	}
		 	
		 	public JdbcTemplate getTemplate() {
				return template;
			}

			public void setTemplate(JdbcTemplate template) {
				this.template = template;
			}

			public void setDatasource(DataSource ds)
		    {
		        this.ds=ds;
		        template = new JdbcTemplate(ds);
		    }
		 	
		 	public void insert(EmployeeDto dto)
		 	{
		 		template.update(insertSql,new Object[]{dto.getEmpId(),dto.getEmpName(),
				dto.getEmpDesignation(),dto.getEmpEmail()});	
		 	}
		
		 	public void update(EmployeeDto dto)
		 	{
		        template.update(updateSql,new Object[] {dto.getEmpId(),dto.getEmpName(),dto.getEmpEmail(),dto.getEmpDesignation()});
		    }
		 	
		 	public List<EmployeeDto> selectAll()
		 	{
		 		return template.query(selectSql,new RowMapper<EmployeeDto>() {
					
					@Override
					public EmployeeDto mapRow(ResultSet rs,int rownumber)throws SQLException{
						EmployeeDto dt = new EmployeeDto();
						
						dt.setEmpId(rs.getInt(1));
						dt.setEmpName(rs.getString(2));
						dt.setEmpDesignation(rs.getString(3));
						dt.setEmpEmail(rs.getString(4));
						return dt;
					}
				});
					}
		 	}
