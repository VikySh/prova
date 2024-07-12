<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"%>
<%
	String message = (String) request.getAttribute("message");	

%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="ISO-8859-1">
			<title>Result list</title>
			<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
			<!--  import main.css -->
			<link rel="stylesheet" href="main.css">
			<style>
				.previewimage
				{
					width:95%;
					height:auto;
					border-radius:10px;
	
				}
				
				.house
				{
					margin-top:1%;
					padding:0.2%;
					border:1px solid #999;
					border-radius:10px;
				}
			</style>
		</head>
		<body>
			<div class="w3-container">
				<!--  menu -->
				<div class="w3-col m2 l2">
					<!--  import menu -->
					<jsp:include page="menu.jsp" />
				</div>			
				<div class="w3-col m8 l8">
					<h1 class="w3-red">
						<%=message %>
					</h1>
					<a href="Index" class="w3-btn w3-amber"> Back to home </a>
				</div>
				<div class="w3-col m2 l2">
					&nbsp; <!--  spazio vuoto -->
				</div>			
			</div>
		</body>
</html>