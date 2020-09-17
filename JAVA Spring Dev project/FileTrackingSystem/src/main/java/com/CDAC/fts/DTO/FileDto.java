package com.CDAC.fts.DTO;

import java.util.Date;

public class FileDto {
	
	private int id;
	private String refNo;
	private String name;
	private String desc;
	private String priority;
	private String language;
	private Date lastEdited;
    private String path;
    private Date createdOn;
    private String remarks;
    private EmployeeDto edt;
    private FileTrackDto ftdto;
    private UserDto udto;
    
    
	public UserDto getUdto() {
		return udto;
	}
	public void setUdto(UserDto udto) {
		this.udto = udto;
	}
	public FileTrackDto getFtdto() {
		return ftdto;
	}
	public void setFtdto(FileTrackDto ftdto) {
		this.ftdto = ftdto;
	}
	public EmployeeDto getEdt() {
		return edt;
	}
	public void setEdt(EmployeeDto edt) {
		this.edt = edt;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Date getLastEdited() {
		return lastEdited;
	}
	public void setLastEdited(Date lastEdited) {
		this.lastEdited = lastEdited;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
    
    
}
