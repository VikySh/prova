<%@page import="com.generation.nh.model.User"%>
<%@page import="com.generation.nh.model.House"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List, com.generation.nh.view.*"%>
<%
	House h = (House) request.getAttribute("house");
	User user = (User) request.getSession().getAttribute("user");
	Language language = (Language) request.getSession().getAttribute("language");

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
				
				.generalinfo
				{
					padding-left:20px;
				}
				
				
				.border1
				{
					border:0px solid black;
				}
				
			</style>
		</head>
		<!--  una form è uno strumento
			 della pagina web, 
			 per raccogliere input degli utenti
			 e trasformarli in parametri
		 -->
		<body>
			<div class="w3-container">
				<div class="w3-col m2 l2 border1">
					<jsp:include page="menu.jsp" />
				</div>			
				<div class="w3-col m7 l7 border1" style="padding-top:30px;">
					<div class="w3-col m4 l4">
						<img src="img/<%=h.getImage() %>"
						style="width:100%;height:auto;border-radius:10px"
						 />
					</div>
					<div class="w3-col m8 l8" style="padding-left:20px">
						<h1> <%=h.getName() %></h1>
						<div class="generalinfo">
							<%=h.getAddress() %>, <%=h.getCity() %> <br />
							<%=language.translate("FLOOR") %> <%=h.getFloor() %> <br />
							<p style="text-align:justify;width:80%">
								<%=h.getDescription() %>							
							</p>
							<a href="Index" class="w3-btn w3-amber">
								<%=language.translate("BACK") %>
							</a>
						</div>
					</div>				
				</div>
				<div class="w3-col m2 l2 border1" style="padding-top:30px">
					<% 
					if(user.isCustomer())
					{%>
						<h3> <%=language.translate("NEWBOOKING") %></h3>									
						<jsp:include page="formnewbooking.jsp" />
						<h3> <%=language.translate("ADDREVIEW") %> </h3>
						<jsp:include page="formnewreview.jsp" />
					<%
					} 
					%>
					<h3> <%=language.translate("PASTREVIEWS") %> </h3>
					<jsp:include page="reviewslist.jsp" />
				</div>			
				<div class="w3-col l1 m1">
					&nbsp;
				</div>	
			</div>
			



		</body>
</html>