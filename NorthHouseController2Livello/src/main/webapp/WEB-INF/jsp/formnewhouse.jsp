<%@page import="com.generation.nh.model.House"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List,com.generation.nh.view.*"%>
<%
	List<House> houses = (List<House>) request.getAttribute("houses");
	Language language = (Language) request.getSession().getAttribute("language");
	

%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="ISO-8859-1">
			<title>NH</title>
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
					<h2> <%=language.translate("INSERTNEWHOUSE") %> </h2>
					<form action="Index">
						<input type="hidden" name="controller" value="house" />
						<input type="hidden" name="cmd" value="newhouse" />
						<%=language.translate("NAME") %>
						<input type="text" name="name" class="w3-input" />
						<%=language.translate("CITY") %>
						<input type="text" name="city" class="w3-input" />
						<%=language.translate("ADDRESS") %>
						<input type="text" name="address" class="w3-input" />
						<%=language.translate("FLOOR") %>
						<input type="number" name="floor" class="w3-input" />
						<%=language.translate("PRICE") %>
						<input type="number" name="price" class="w3-input" />
						<%=language.translate("IMAGE") %>
						<select name=image class="w3-input">
							<option value="bellagio.jpeg"> Bellagio 			</option>
							<option value="varenna.jpeg"> Varenna 				</option>
							<option value="trezzo.jpeg"> Trezzo 				</option>
							<option value="montevecchia.jpeg"> Montevecchia 	</option>
							<option value="milano.jpeg"> Milano 				</option>
							<option value="bergamo.jpeg"> Bergamo 				</option>
						</select>
						<%=language.translate("DESCRIPTION") %>
						<textarea name="description" class="w3-input" rows="4"></textarea>					
						<input type="submit" class="w3-btn w3-orange" value="Save" />				
					</form>
					
					
				</div>
				<div class="w3-col m2 l2">
					&nbsp; <!--  spazio vuoto -->
				</div>			
			</div>
		</body>
</html>