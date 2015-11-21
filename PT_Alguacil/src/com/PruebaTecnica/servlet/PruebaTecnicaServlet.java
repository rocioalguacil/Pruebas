package com.PruebaTecnica.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PruebaTecnica.model.Usuario;
import com.PruebaTecnica.service.PruebaTecnicaService;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class PruebaTecnicaServlet extends HttpServlet {

	/*protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
			processRequest(request, response);
			}
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String auxCargar = request.getParameter("cargar");
		 String auxBorrar = request.getParameter("borrar");
		 
		 if(auxCargar!=null){
			 cargarDatos(request);
		 }	
		 
		 if(auxBorrar!=null){
			 borrarDatos(request);
		 }
		 
		 response.sendRedirect("index.jsp");
		 
	}
	
	private void borrarDatos(HttpServletRequest request) {
		
		ClientConfig clientConfig = new ClientConfig();
		HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        List<Usuario> usuarios = client.getList("cacheUsuarios");

        usuarios.clear();

        request.getSession().removeAttribute("usuarios");
    	request.getSession().removeAttribute("us");
    	request.getSession().setAttribute("ok", "La caché está vacía");
		
	}

	private void cargarDatos(HttpServletRequest request) {
		
		PruebaTecnicaService service = new PruebaTecnicaService();
		List<Usuario> usuarios = service.getListaUsuarios();
		 
	    HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
	    List<Usuario> us = hazelcastInstance.getList("cacheUsuarios");
	    boolean ok = us.addAll(usuarios);
	    if(ok)
	    	request.getSession().setAttribute("ok", "Datos Cargados en Caché");
	    else
	    	request.getSession().setAttribute("ok", "Problemas al cargar en la caché");
		
	}

}
