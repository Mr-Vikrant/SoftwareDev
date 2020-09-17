package com.CDAC.fts.DTO;

import java.util.Date;

public class FileTrackDto {
	private String empName;
	private String empNameFrom;
	private int fileNo;
	private Date allottedOn;
	private String isSubmitted;
	private Date submittedOn;
	private String inputRemarks;
	private String outputRemarks;
	private FileDto fdto;
	
	public FileDto getFdto() {
		return fdto;
	}

	public void setFdto(FileDto fdto) {
		this.fdto = fdto;
	}

	public FileTrackDto() {
			
	}
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNameFrom() {
		return empNameFrom;
	}

	public void setEmpNameFrom(String empNameFrom) {
		this.empNameFrom = empNameFrom;
	}

	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public Date getAllottedOn() {
		return allottedOn;
	}
	public void setAllottedOn(Date allottedOn) {
		this.allottedOn = allottedOn;
	}
	public String isSubmitted() {
		return isSubmitted;
	}
	public void setSubmitted(String isSubmitted) {
		this.isSubmitted = isSubmitted;
	}
	public Date getSubmittedOn() {
		return submittedOn;
	}
	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}
	public String getInputRemarks() {
		return inputRemarks;
	}
	public void setInputRemarks(String inputRemarks) {
		this.inputRemarks = inputRemarks;
	}
	public String getOutputRemarks() {
		return outputRemarks;
	}
	public void setOutputRemarks(String outputRemarks) {
		this.outputRemarks = outputRemarks;
	}
	
	
}
