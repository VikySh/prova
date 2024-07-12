<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="com.generation.nh.view.*"    
%>
    
<%
	Language language = (Language) request.getSession().getAttribute("language");
%>
<!--  action è la servlet a cui invierò dei parametri -->
<div style="margin-top:20px;padding:10px">
	<form action="Index">
		<input type="hidden" name="controller" value="house" />
		<input type="hidden" name="cmd" value="search" />
		<!--  casella di testo -->
		<input type="text" 
			class="w3-input"
			name="city"
			style="width:40%;display:inline-block"
			placeholder="<%=language.translate("CITYSEARCHMESSAGE") %>" 
		/>
		<input type="number"
			class="w3-input"
			name="budget"
			style="width:30%;display:inline-block"
			placeholder="<%=language.translate("BUDGETSEARCHMESSAGE") %>"
		/>
		<input type="submit" class="w3-btn w3-amber"
		value="<%=language.translate("SEARCH") %>" />
		<a href="Index?cmd=change&language=ita&controller=language" class="w3-btn w3-silver">
			ITA
		</a>
		<a href="Index?cmd=change&language=eng&controller=language" class="w3-btn w3-silver">
			ENG
		</a>
		
		
	</form>					
</div>
