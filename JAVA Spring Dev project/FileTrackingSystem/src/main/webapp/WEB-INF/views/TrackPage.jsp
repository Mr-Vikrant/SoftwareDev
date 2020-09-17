<%@page import="com.CDAC.fts.DTO.FileTrackDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.CDAC.fts.DAO.FileTrackDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>TRACK RECORD</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <style>
body, html {
    height: 100%;
}

.bg { 
    /* The image used */
    background-image: url("src/main/webapp/WEB-INF/views/pics/CDAC-Logo.jpg");

    /* Full height */
    height: 100%; 

    /* Center and scale the image nicely */
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
  </style>
</head>
<body>

<div align="center" class="container">
<br>
  <h2>File Track Record</h2>  <br><br>         
  <table class="table table-striped">
    <thead>
        <tr>	
                            		<th>File Name</th>
                            		<th>File To</th>
                            		<th>File Allocation Date</th>
                            	
         </tr>
    </thead>
    <tbody>
     <%
                            	FileTrackDao ftdao = new FileTrackDao();
                            	ArrayList<FileTrackDto> list = ftdao.trackFile((Integer)session.getAttribute("id"));
                            	for(FileTrackDto ft:list)
                            	{
                            %>
                              <tr>
                                  <td><%=ft.getFdto().getName() %></td>
                                  <td><%=ft.getEmpName() %></td> 
                                  <td><%=ft.getAllottedOn() %></td>                              
                              </tr>
                              <% 
                              } 
                            	session.invalidate();
                              %>
                          </tbody>
  </table>
</div>

</body>
</html>
