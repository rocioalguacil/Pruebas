<%@page import="java.util.List"%>
<%@page import="com.PruebaTecnica.model.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prueba tecnica</title>

<script type="text/javascript">

function comprobar(){
	var tipo1 = document.getElementsByName("tipo")[0];
	var tipo2 = document.getElementsByName("tipo")[1];
	
	if (!tipo1.checked && !tipo2.checked){
		alert("Elija un campo de búsqueda (Identificador o Nombre)");
		return false;
	} else {
		return true;
	}
}
  
</script>
</head>
<body> 

	<h1>Prueba Técnica - Hazelcast</h1>
	
	<form method="post" action="PruebaTecnicaServlet">
		<h5>Nota: lo primero que se debe hacer es pulsar el botón "Cargar Datos" y después ya podrá usar el buscador.</h5>
 		<input type="submit" name="cargar" value="Cargar Datos" />
 		<input type="submit" name="borrar" value="Borrar Datos" />
 		
 		<%String ok =  (String) session.getAttribute("ok");
 		  if(ok!=null){%>
 			  <h4 style="color:#ff0000;"><%=ok%></h4>
 		  <%}
 		%>
	</form>
	
	<h3>Buscador</h3>

	<form method="post" action="PruebaTecnicaBuscarServlet" name="buscador" onsubmit="return comprobar()">
		<input type="radio" name="tipo" value="ident"> Identificador (numérico)&nbsp;
		<input type="radio" name="tipo" value="nombre"> Nombre (texto): <br/>
	 	<input type="text" name="id" /> &nbsp;&nbsp;&nbsp; <input type="submit" value="Buscar" />
	 
	 	
		 <%
		 List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuarios");
		 if(usuarios!=null){%>
			

			<br/><br/>
		 	
		 	<h4>Resultado</h4>
		 
		 	<table style="border-radius: 5px; border:solid 1px; padding: 5px; width: max-content;">
				 <thead>
					 <tr>
						 <th>ID</th>
						 <th>Nombre</th>
						 <th>Teléfono</th>
						 <th>Empresa</th>
						 <th>IBAN</th>					
					 </tr>
				 </thead>
				 <tbody>
				 <% for (Usuario u : usuarios) {
		 %>
					 <tr>
						 <td><%=u.getId()%></td>
						 <td><%=u.getNombre()%></td>
						 <td><%=u.getTelefono()%></td>
						 <td><%=u.getEmpresa()%></td>
						 <td><%=u.getIban()%></td>
					 </tr>
					 <%}%>
				<tbody>
			</table>
		 
		 <%}%>

		<%Usuario us = (Usuario)session.getAttribute("us");
	 	if(us!=null){%>
			<br/><br/>
		 	
		 	<h4>Resultado</h4>
		 
		 	<table style="border-radius: 5px; border:solid 1px; padding: 5px; width: max-content;">
				 <thead>
					 <tr>
						 <th>ID</th>
						 <th>Nombre</th>
						 <th>Teléfono</th>
						 <th>Empresa</th>
						 <th>IBAN</th>					
					 </tr>
				 </thead>
				 <tbody>
					 <tr>
						 <td><%=us.getId()%></td>
						 <td><%=us.getNombre()%></td>
						 <td><%=us.getTelefono()%></td>
						 <td><%=us.getEmpresa()%></td>
						 <td><%=us.getIban()%></td>
					 </tr>
				<tbody>
			</table>
			<%} %>	
 	</form>
</body>
</html>