package com.application.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.model.pojo.Intervenant;

public class IntervenantDao {

	public List EmailIntervenant(Integer id)
	{
		
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "select I.email from Intervenant I where idintervenant="+id.toString();
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
	
	public List ListeIntervenant()
	{
		
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "From Intervenant ";
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
   public void AddIntervenant(Intervenant intervenant)
   {
	   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 Transaction tx = null;
	  try {
	     tx = session.beginTransaction();
	     session.save(intervenant);
	     session.getTransaction().commit();
	  }
	  catch (Exception e) {
	     if (tx!=null) tx.rollback();
	     e.printStackTrace(); 
	  }finally {
		  session.close();
	  } 
	   
	   
   }
	
}
