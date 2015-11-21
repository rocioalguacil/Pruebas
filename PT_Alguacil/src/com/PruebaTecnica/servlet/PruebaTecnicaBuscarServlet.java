package com.PruebaTecnica.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PruebaTecnica.model.Usuario;
import com.PruebaTecnica.service.PruebaTecnicaService;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public class PruebaTecnicaBuscarServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException 
	{

		ClientConfig clientConfig = new ClientConfig();
		
        HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
        List<Usuario> usuarios = client.getList("cacheUsuarios");
        
        if(usuarios!=null){
        	if (usuarios.size()!=0){
	            String userId = request.getParameter("id");
	            String tipo = request.getParameter("tipo");
	            
	            if(tipo.equals("ident")){
	            	request.getSession().setAttribute("us", usuarios.get(Integer.parseInt(userId)-1));
	                request.getSession().removeAttribute("usuarios");
	
	            }
	            else if(tipo.equals("nombre")){
	            	Iterator<Usuario> it = usuarios.iterator();
	            	
	            	ArrayList<Usuario> aux = new ArrayList<Usuario>();
	            	while(it.hasNext()){
	            		Usuario u = (Usuario) it.next();
	            		if ((u.getNombre()).equals(userId)){
	            			aux.add(u);
	            		}
	            	}
	            	
	            	request.getSession().setAttribute("usuarios", aux);
	                request.getSession().removeAttribute("us");
	
	            } else {
	            	request.getSession().setAttribute("ok", "La caché está vacía");
	            }
        	}
        } else {
        	request.getSession().setAttribute("ok", "Los datos no se han cargado en la cache");
        }
		
		 response.sendRedirect("index.jsp");
	
	}
}
