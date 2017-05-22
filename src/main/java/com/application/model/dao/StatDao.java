package com.application.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class StatDao {
	
	

	public List Topintervenant()
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "select count(*), int.nom, int.prenom from Intervenant int, Intervention i, Affectation a where a.intervention_idintervention=i.idintervention and "
		    		+ " int.idintervenant=a.intervenant_idintervenant and i.etat='Clôturée' GROUP BY "
		     		+ "int.idintervenant ";
		     Query query=null;
		     query = session.createQuery(hql);
		     results = query.list();
		     query.setMaxResults(3);
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
	//For all intervenant
	public List EncoursIntervention()
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "select count(*) from Intervention i where i.etat='En cours' ";
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
	//For all Intervenant
	public List CloturéIntervention()
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "select count(*) from Intervention i where i.etat='Clôturée' ";
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
	//For all intervenant
	public List NonAffectéeIntervention()
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "select count(*) from Intervention i where i.etat='Non affectée' ";
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
	
	
	//For a specific intervenant
	public List EncoursIntervention(Integer id)
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "select count(*) from Intervention i, Affectation a where i.etat='En cours' and a.intervention_idintervention=i.idintervention "
		     		+ " and a.intervenant_idintervenant="+id;
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
	
	//For a specific intervenant
	public List CloturéIntervention(Integer id)
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "select count(*) from Intervention i, Affectation a where i.etat='Clôturée' and a.intervention_idintervention=i.idintervention "
			     		+ " and a.intervenant_idintervenant="+id;
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
	
	//For a specific Client
		public List ClientCloturéIntervention(Integer id)
		{
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
			  Transaction tx = null;
			  List results=null;
			  try {
				 tx = session.beginTransaction(); 
			     String hql = "select count(*) from Intervention i, Affectation a where i.etat='Clôturée' and a.intervention_idintervention=i.idintervention "
				     		+ " and i.client_idclient="+id;
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
	//For a specific Client
		public List ClientEncoursIntervention(Integer id)
		{
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
			  Transaction tx = null;
			  List results=null;
			  try {
				 tx = session.beginTransaction(); 
			     String hql = "select count(*) from Intervention i where i.etat Not like 'Clôturée' " +
			    		" and i.client_idclient="+id;
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
