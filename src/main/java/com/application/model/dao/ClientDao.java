package com.application.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ClientDao {
	
	
	
	
	public List ListeClient()
	{
		
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "From Client ";
		     Query query = session.createQuery(hql);
		     results = query.list();
		     session.getTransaction().commit();
		  }
		  catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     e.printStackTrace(); 
		  }finally {
			  session.close();
		  }
		  return results;
		
	}
	
	public List ListeClient(String name)
	{
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = " From Client C where C.nom_client like '"+name+"'";
		     Query query = session.createQuery(hql);
		     results = query.list();
		     session.getTransaction().commit();
		  }
		  catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     e.printStackTrace(); 
		  }finally {
			  session.close();
		  }
		  return results;
	}

}
