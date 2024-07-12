<%@page import="com.generation.nh.model.House"%>
<%@page import="com.generation.nh.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="java.util.List,com.generation.nh.view.*"    
%>
<%
	// la pagina che mi includerà dovrà contenere l'attributo houses
	List<House> houses = (List<House>) request.getAttribute("houses");
	User user = (User) request.getSession().getAttribute("user");
	Language language = (Language) request.getSession().getAttribute("language");

%>
<!-- 
	io NON SONO UNA PAGINA WEB COMPLETA
	io sono un PEZZETTO DI PAGINA
	da INCLUDERE IN UN'ALTRA


 -->
<%for(House h:houses){ %>
<div class="w3-container house">
	<div class="w3-col m2 l2">
		<img class="previewimage" src="img/<%=h.getImage() %>" />
	</div>					
	<div class="w3-col m10 l10">
		<h3> <%=h.getName() %></h3>
		<%=h.getCity() %> <br />
		<%=h.getAddress() %> <br />
		<%=h.getFloor() %><%=language.translate("FLOOR").toLowerCase() %> <br />
		<%=h.getPrice() %> &euro; <%=language.translate("XNIGHT").toLowerCase() %> <br />
		
		<div style="margin-top:20px;float:right;margin-right:10px">
			
			<a href="Index?controller=house&cmd=detail&id=<%=h.getId() %>" class="w3-btn w3-amber">
				<%=language.translate("SEEMORE") %>
			</a>
			<% if(user.getRole().equalsIgnoreCase("admin")){ %>
				<a href="Index?cmd=delete&controller=house&id=<%=h.getId() %>" class="w3-btn w3-amber">
					<%=language.translate("DELETE") %>
				</a>
			<%} %>
			
			
		</div>
	</div>
</div>
<%} %>			