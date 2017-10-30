<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.um.es/aadd/tld" prefix="aadd" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hola mundo</title>
</head>
<body>
	Hola mundo
<aadd:enlace texto="Ir a google" url="http://www.google.es"></aadd:enlace>
<aadd:recorrerUsuarios usuarios="<%=session.getAttribute("usuario")%>">
	<%= usu %>
	<%= pwd %>
</aadd:recorrerUsuarios>
<%System.out.println("Hola mundo");%>
</body>
</html>