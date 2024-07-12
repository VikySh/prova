<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.generation.bazar.model.*,java.util.List"
    %>
<%
	List<Product> products = (List<Product>)
			request.getAttribute("products");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>
</head>
<body>
<% for(Product p:products){ %>
	<%=p.getName() %> - <%=p.getPrice() %> &euro;

<% } %>


</body>
</html>