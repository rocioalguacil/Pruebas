package com.PruebaTecnica.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.PruebaTecnica.SessionFactoryUtil;
import com.PruebaTecnica.model.Usuario;

public class PruebaTecnicaService {

	public List<Usuario> getListaUsuarios(){
        List<Usuario> list = new ArrayList<Usuario>();
        
        Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
 
            list = session.createQuery("from Usuario").list();                        
            session.close();
        return list;
    }
	
}
