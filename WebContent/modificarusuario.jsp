<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar datos de usuario</title>
</head>
<body>
<h3>Modificar los datos de usuario</h3>
	<form name="registro" action="ServletsModificar" method="post">
		<table>
			<tr>
				<td><label>Usuario:</label></td>
				<td><input type="text" name="usuario" value="${usuarioActual.usuario }" /></td>
			</tr>
			<tr>
				<td><label>Telefono:</label></td>
				<td><input type="text" name="telefono" value="${usuarioActual.telefono }"/></td>
			</tr>
			<tr>
				<td><label>Email:</label></td>
				<td><input type="email" name="email" value="${usuarioActual.mail }"/></td>
			</tr>
			<tr>
				<td><label>Contraseña:</label></td>
				<td><input type="password" name="password1" value="${usuarioActual.contrasena }"/></td>
			</tr>
			<tr>
				<td><label>Repite contraseña:</label></td>
				<td><input type="password" name="password2" value="${usuarioActual.contrasena }"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Modificar" /></td>
			</tr>
		</table>
	</form>
</body>
</html>