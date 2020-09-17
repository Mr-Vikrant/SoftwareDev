<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="com.CDAC.fts.DTO.FileDto"%>
<%@page import="com.CDAC.fts.DAO.FileDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.CDAC.fts.DTO.FileTrackDto"%>
<%@page import="com.CDAC.fts.DAO.FileTrackDao"%>
<%@page import="com.CDAC.fts.DTO.UserDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN PROFILE</title>
<style>
	.mail-box {
    border-collapse: collapse;
    border-spacing: 0;
    display: table;
    table-layout: fixed;
    width: 100%;
}
.mail-box aside {
    display: table-cell;
    float: none;
    height: 100%;
    padding: 0;
    vertical-align: top;
}
.mail-box .sm-side {
    background: none repeat scroll 0 0 #e5e8ef;
    border-radius: 4px 0 0 4px;
    width: 25%;
}
.mail-box .lg-side {
    background: none repeat scroll 0 0 #fff;
    border-radius: 0 4px 4px 0;
    width: 75%;
}
.mail-box .sm-side .user-head {
    background: none repeat scroll 0 0 #00a8b3;
    border-radius: 4px 0 0;
    color: #fff;
    min-height: 80px;
    padding: 10px;
}
.user-head .inbox-avatar {
    float: left;
    width: 65px;
}
.user-head .inbox-avatar img {
    border-radius: 4px;
}
.user-head .user-name {
    display: inline-block;
    margin: 0 0 0 10px;
}
.user-head .user-name h5 {
    font-size: 14px;
    font-weight: 300;
    margin-bottom: 0;
    margin-top: 15px;
}
.user-head .user-name h5 a {
    color: #fff;
}
.user-head .user-name span a {
    color: #87e2e7;
    font-size: 12px;
}
a.mail-dropdown {
    background: none repeat scroll 0 0 #80d3d9;
    border-radius: 2px;
    color: #01a7b3;
    font-size: 10px;
    margin-top: 20px;
    padding: 3px 5px;
}
.inbox-body {
    padding: 20px;
}
.btn-compose {
    background: none repeat scroll 0 0 #ff6c60;
    color: #fff;
    padding: 12px 0;
    text-align: center;
    width: 100%;
}
.btn-compose:hover {
    background: none repeat scroll 0 0 #f5675c;
    color: #fff;
}
ul.inbox-nav {
    display: inline-block;
    margin: 0;
    padding: 0;
    width: 100%;
}
.inbox-divider {
    border-bottom: 1px solid #d5d8df;
}
ul.inbox-nav li {
    display: inline-block;
    line-height: 45px;
    width: 100%;
}
ul.inbox-nav li a {
    color: #6a6a6a;
    display: inline-block;
    line-height: 45px;
    padding: 0 20px;
    width: 100%;
}
ul.inbox-nav li a:hover, ul.inbox-nav li.active a, ul.inbox-nav li a:focus {
    background: none repeat scroll 0 0 #d5d7de;
    color: #6a6a6a;
}
ul.inbox-nav li a i {
    color: #6a6a6a;
    font-size: 16px;
    padding-right: 10px;
}
ul.inbox-nav li a span.label {
    margin-top: 13px;
}
ul.labels-info li h4 {
    color: #5c5c5e;
    font-size: 13px;
    padding-left: 15px;
    padding-right: 15px;
    padding-top: 5px;
    text-transform: uppercase;
}
ul.labels-info li {
    margin: 0;
}
ul.labels-info li a {
    border-radius: 0;
    color: #6a6a6a;
}
ul.labels-info li a:hover, ul.labels-info li a:focus {
    background: none repeat scroll 0 0 #d5d7de;
    color: #6a6a6a;
}
ul.labels-info li a i {
    padding-right: 10px;
}
.nav.nav-pills.nav-stacked.labels-info p {
    color: #9d9f9e;
    font-size: 11px;
    margin-bottom: 0;
    padding: 0 22px;
}
.inbox-head {
    background: none repeat scroll 0 0 #41cac0;
    border-radius: 0 4px 0 0;
    color: #fff;
    min-height: 80px;
    padding: 20px;
}
.inbox-head h3 {
    display: inline-block;
    font-weight: 300;
    margin: 0;
    padding-top: 6px;
}
.inbox-head .sr-input {
    border: medium none;
    border-radius: 4px 0 0 4px;
    box-shadow: none;
    color: #8a8a8a;
    float: left;
    height: 40px;
    padding: 0 10px;
}
.inbox-head .sr-btn {
    background: none repeat scroll 0 0 #00a6b2;
    border: medium none;
    border-radius: 0 4px 4px 0;
    color: #fff;
    height: 40px;
    padding: 0 20px;
}
.table-inbox {
    border: 1px solid #d3d3d3;
    margin-bottom: 0;
}
.table-inbox tr td {
    padding: 12px !important;
}
.table-inbox tr td:hover {
    cursor: pointer;
}
.table-inbox tr td .fa-star.inbox-started, .table-inbox tr td .fa-star:hover {
    color: #f78a09;
}
.table-inbox tr td .fa-star {
    color: #d5d5d5;
}
.table-inbox tr.unread td {
    background: none repeat scroll 0 0 #f7f7f7;
    font-weight: 600;
}
ul.inbox-pagination {
    float: right;
}
ul.inbox-pagination li {
    float: left;
}
.mail-option {
    display: inline-block;
    margin-bottom: 10px;
    width: 100%;
}
.mail-option .chk-all, .mail-option .btn-group {
    margin-right: 5px;
}
.mail-option .chk-all, .mail-option .btn-group a.btn {
    background: none repeat scroll 0 0 #fcfcfc;
    border: 1px solid #e7e7e7;
    border-radius: 3px !important;
    color: #afafaf;
    display: inline-block;
    padding: 5px 10px;
}
.inbox-pagination a.np-btn {
    background: none repeat scroll 0 0 #fcfcfc;
    border: 1px solid #e7e7e7;
    border-radius: 3px !important;
    color: #afafaf;
    display: inline-block;
    padding: 5px 15px;
}
.mail-option .chk-all input[type="checkbox"] {
    margin-top: 0;
}
.mail-option .btn-group a.all {
    border: medium none;
    padding: 0;
}
.inbox-pagination a.np-btn {
    margin-left: 5px;
}
.inbox-pagination li span {
    display: inline-block;
    margin-right: 5px;
    margin-top: 7px;
}
.fileinput-button {
    background: none repeat scroll 0 0 #eeeeee;
    border: 1px solid #e6e6e6;
}
.inbox-body .modal .modal-body input, .inbox-body .modal .modal-body textarea {
    border: 1px solid #e6e6e6;
    box-shadow: none;
}
.btn-send, .btn-send:hover {
    background: none repeat scroll 0 0 #00a8b3;
    color: #fff;
}
.btn-send:hover {
    background: none repeat scroll 0 0 #009da7;
}
.modal-header h4.modal-title {
    font-family: "Open Sans",sans-serif;
    font-weight: 300;
}
.modal-body label {
    font-family: "Open Sans",sans-serif;
    font-weight: 400;
}
.heading-inbox h4 {
    border-bottom: 1px solid #ddd;
    color: #444;
    font-size: 18px;
    margin-top: 20px;
    padding-bottom: 10px;
}
.sender-info {
    margin-bottom: 20px;
}
.sender-info img {
    height: 30px;
    width: 30px;
}
.sender-dropdown {
    background: none repeat scroll 0 0 #eaeaea;
    color: #777;
    font-size: 10px;
    padding: 0 3px;
}
.view-mail a {
    color: #ff6c60;
}
.attachment-mail {
    margin-top: 30px;
}
.attachment-mail ul {
    display: inline-block;
    margin-bottom: 30px;
    width: 100%;
}
.attachment-mail ul li {
    float: left;
    margin-bottom: 10px;
    margin-right: 10px;
    width: 150px;
}
.attachment-mail ul li img {
    width: 100%;
}
.attachment-mail ul li span {
    float: right;
}
.attachment-mail .file-name {
    float: left;
}
.attachment-mail .links {
    display: inline-block;
    width: 100%;
}

.fileinput-button {
    float: left;
    margin-right: 4px;
    overflow: hidden;
    position: relative;
}
.fileinput-button input {
    cursor: pointer;
    direction: ltr;
    font-size: 23px;
    margin: 0;
    opacity: 0;
    position: absolute;
    right: 0;
    top: 0;
    transform: translate(-300px, 0px) scale(4);
}
.fileupload-buttonbar .btn, .fileupload-buttonbar .toggle {
    margin-bottom: 5px;
}
.files .progress {
    width: 200px;
}
.fileupload-processing .fileupload-loading {
    display: block;
}
* html .fileinput-button {
    line-height: 24px;
    margin: 1px -3px 0 0;
}
* + html .fileinput-button {
    margin: 1px 0 0;
    padding: 2px 15px;
}
@media (max-width: 767px) {
.files .btn span {
    display: none;
}
.files .preview * {
    width: 40px;
}
.files .name * {
    display: inline-block;
    width: 80px;
    word-wrap: break-word;
}
.files .progress {
    width: 20px;
}
.files .delete {
    width: 60px;
}
}
ul {
    list-style-type: none;
    padding: 0px;
    margin: 0px;
}

.modal-content {
    position: relative;
    top: 162px;
}
 
 .caret.caret-up {
    border-top-width: 0;
    border-bottom: 4px solid #fff;
  }
  .btn-track{
	   background-color:#555555;
	}
	.btn-group-lg>.btn, .btn-lg {
    padding: 10px 16px;
    font-size: 16px;
    line-height: 1.3333333;
    border-radius: 6px;
    color: white;
    }
</style>
</head>
<body>

<div class="container">
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js'>
 <div class="mail-box">
                  <aside class="sm-side">
                      <div class="user-head">
                          <a class="inbox-avatar" href="javascript:;">
                              <img  width="64" hieght="60" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png">
                          </a>
                            <div class="user-name">
                              <h5><a href="#">${uname}</a></h5><br>
                              <form action="logout" method="Post">
                              <SCRIPT type="text/javascript">
													    window.history.forward();
													    function noBack() { window.history.forward(); }
													</SCRIPT>
                              	<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-off"></span>Sign Out</button>
                              </form>
                          </div>
                         
                      </div>
                      <div class="inbox-body" id="myModel">
                          <a href="#myModal" data-toggle="modal" title="Compose"    class="btn btn-compose">
                              CREATE FILE
						    </a><br><br><br>
						     <button type="button" class="btn btn-track btn-lg" data-toggle="modal" data-target="#myModal123">Track File</button>
					<!-- Modal -->
					  <div class="modal fade" id="myModal123" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
								
						<div class="modal-body">
          				<div class ="container" align="center">
          				<form action="trackfile" method="post"  target="_blank">
          				ENTER FILE ID :
          				<input type="text" placeholder="Enter File ID" name="id"/>
							<button type="submit" class="btn btn-primary">Track File</button>
							<!-- <ul class="progressbar">
								<li class="active">Admin</li>
								<li>Durgesh</li>
								<li>Sanket Chalke</li>
							</ul> -->
						</form>	
						</div>
        				</div>
					      </div>
					      
					    </div>
					  </div>
						 
						     

                          <!-- Modal -->
                          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade" style="display: none;">
                              <div class="modal-dialog">
                                  <div class="modal-content">
                                      <div class="modal-header">
                                          <button aria-hidden="true" data-dismiss="modal" class="close" id="mybtn" type="button">X</button>
                                         
                                      </div>
                                      <div class="modal-body">
                                         <form role="form" class="form-horizontal" modelAttribute="file" enctype="multipart/form-data" action="fileins" method="post">
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">To</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" placeholder="to" name="edt.empName" id="inputEmail1" class="form-control" required>
                                                  </div>
                                              </div>
                                          
                                            
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">File Name</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" name="name" placeholder="file name" id="inputEmail1" class="form-control" required>
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">Reference Number</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" name="refNo" placeholder=" reference number" id="cc" class="form-control" required>
                                                  </div>
                                              </div>

												<div class="form-group">
													<label class="col-lg-2 control-label">Description</label>
													 <div class="col-lg-10">
                                                      <input type="text" name="desc" placeholder="description" id="inputEmail1" class="form-control" required>
                                                  </div>
												</div>
                                               <div class="form-group">
                                               <label class="col-lg-2 control-label">Select Priority</label>
                                                 <select name="priority">
												  <option value="low">Low</option>
												  <option value="moderate">Moderate</option>
												  <option value="high">High</option>
												  
												</select>
                                               </div>
                                               
                                               <div class="form-group">
                                               <label class="col-lg-2 control-label">Language</label>
                                                 <select name="language">
												  <option value="English">English</option>
												  <option value="Hindi">Hindi</option>
												  <option value="Marathi">Marathi</option> 
												</select>
                                               </div>
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">Remarks</label>
                                                  <div class="col-lg-10">
                                                      <textarea rows="5" cols="15" class="form-control" id="" name="remarks"></textarea>
                                                  </div>
                                              </div>

                                              <div class="form-group">
                                                  <div class="col-lg-offset-2 col-lg-10">
                                                      <span class="btn green fileinput-button">
                                                        <i class="fa fa-plus fa fa-white"></i>
                                                        <span>Attachment</span>
                                                        <input type="file" name="fileToUpload" />
                                                      </span>
                                                     
                                                      <button class="btn btn-send" type="submit">Send</button>
                                                  </div>
                                              </div>
                                              
                                          
                                         </form>
                                      </div>
                                  </div><!-- /.modal-content -->
                              </div><!-- /.modal-dialog -->
                          </div><!-- /.modal -->
                      </div>
                    
              
                     
                      

                  </aside>
                  <aside class="lg-side">
                      <div class="inbox-head">
                          <h3>FILE RECORD</h3>
                          <!-- <form action="#" class="pull-right position">
                              <div class="input-append">
                                  <input type="text" class="sr-input" placeholder="Search Mail">
                                  <button class="btn sr-btn" type="button"><i class="fa fa-search"></i></button>
                              </div>
                          </form> -->
                      </div>
                      <div class="inbox-body">
                         <div class="mail-option">
                            <!--  <div class="chk-all">
                                 <input type="checkbox" class="mail-checkbox mail-group-checkbox">
                                 <div class="btn-group">
                                    
                                 </div>
                             </div> -->

                             <div class="btn-group">
                                <!--  <a data-original-title="Refresh" data-placement="top" data-toggle="dropdown" href="#" class="btn mini tooltips">
                                     <i class=" fa fa-refresh"></i>
                                 </a> -->
                             </div>
                             <div class="btn-group hidden-phone">
                                <!--  <a data-toggle="dropdown" href="#" class="btn mini blue" aria-expanded="false">
                                     More
                                     <i class="fa fa-angle-down "></i>
                                 </a>
                                 <ul class="dropdown-menu">
                                     <li><a href="#"><i class="fa fa-pencil"></i> Mark as Read</a></li>
                                     <li><a href="#"><i class="fa fa-ban"></i> Spam</a></li>
                                     <li class="divider"></li>
                                     <li><a href="#"><i class="fa fa-trash-o"></i> Delete</a></li>
                                 </ul> -->
                             </div>
                             <div class="btn-group">
                                <!--  <a data-toggle="dropdown" href="#" class="btn mini blue">
                                     Move to
                                     <i class="fa fa-angle-down "></i>
                                 </a>
                                 <ul class="dropdown-menu">
                                     <li><a href="#"><i class="fa fa-pencil"></i> Mark as Read</a></li>
                                     <li><a href="#"><i class="fa fa-ban"></i> Spam</a></li>
                                     <li class="divider"></li>
                                     <li><a href="#"><i class="fa fa-trash-o"></i> Delete</a></li>
                                 </ul> -->
                             </div>

                          
                         </div>
                         <td>
                            		<strong>Created Files</strong>
                          </td>
                          <table class="table table-inbox table-hover">
                            <tbody>
                            <tr>
                            		<th class="inbox-small-cells"></th>	
                            		<th>File no.</th>
                            		<th>File Name</th>
                            		<th>Description</th>
                            		<th>Created On</th>
                            		<th>Priority</th>
                            		<th>Language</th>
                            		<th>Remarks</th>
                            	
                            </tr>
                            <%
                            	FileTrackDao ftdao = new FileTrackDao();
                            	ArrayList<FileTrackDto> list = ftdao.createdFiles();
                            	for(FileTrackDto ft:list)
                            	{
                            %>
                              <tr class="unread">
                                  <td class="inbox-small-cells">
                                      <input type="checkbox" class="mail-checkbox">
                                  </td>
                                  <td class="inbox-small-cells"><%=ft.getFileNo()%><i class="fa fa-star"></i></td>
                                  <td class="view-message "><%=ft.getFdto().getName()%></td>
                                  <td><%=ft.getFdto().getDesc()%></td>
                                  <td><%=ft.getFdto().getCreatedOn()%><i class="fa fa-paperclip"></i></td>
                                  <td><%=ft.getFdto().getPriority() %></td>
                                  <td><%=ft.getFdto().getLanguage() %></td>
                                  <td><%=ft.getInputRemarks() %></td>                               
                              </tr>
                              <% 
                              } 
                              %>
                          </tbody>
                          </table>
                         <table>
                         		<tbody>
                          <td>
                            		<strong>Completed Files</strong>
                          </td>
                          <table class="table table-inbox table-hover">
                            <tbody>
                            <tr>
                            		<th class="inbox-small-cells"></th>	
                            		<th>File no.</th>
                            		<th>File Name</th>
                            		<th>Description</th>
                            		<th>Received On</th>                            		
                            		<th>Priority</th>
                            		<th>Language</th>
                            		<th>Remarks</th>
                            	
                            </tr>
                            <%
                            	FileTrackDao ftdao1 = new FileTrackDao();
                            	ArrayList<FileTrackDto> list1 = ftdao1.completedFiles();
                            	for(FileTrackDto ft:list1)
                            	{
                            %>
                              <tr class="unread">
                                  <td class="inbox-small-cells">
                                      <input type="checkbox" class="mail-checkbox">
                                  </td>
                                  <td class="inbox-small-cells"><%=ft.getFileNo()%><i class="fa fa-star"></i></td>
                                  <td class="view-message "><%=ft.getFdto().getName()%></td>
                                  <td><%=ft.getFdto().getDesc()%></td>
                                  <td><%=ft.getFdto().getCreatedOn()%><i class="fa fa-paperclip"></i></td>
                                  <td><%=ft.getFdto().getPriority() %></td>
                                  <td><%=ft.getFdto().getLanguage() %></td>
                                  <td><%=ft.getInputRemarks() %></td>                               
                              </tr>
                               <% 
                              } 
                              %>
                         		</tbody>
                         </table>
                      </div>
                  </aside>
              </div>
</div>
   
</body>
</html>