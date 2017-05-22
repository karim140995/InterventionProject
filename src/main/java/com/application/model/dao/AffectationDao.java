package com.application.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.model.pojo.Affectation;
import com.example.model.pojo.Intervention;

public class AffectationDao {
	
	
	
	public void deleteAffectation(Integer id)
	{
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			 Transaction tx = null;
		  try {

		     tx = session.beginTransaction();
		     // do some work
		     Affectation affectation = new Affectation();
		     affectation.setIdaffectation(id);
		     //Update etat
		     String hql="Update Intervention i set i.etat='Non affectée' where i.idintervention in (Select i.idintervention from "
		     		+ "Affectation a,Intervention i where a.idaffectation="+id+" and i.idintervention=a.intervention_idintervention) ";
;
		     Query query= session.createQuery(hql);
		     query.executeUpdate();
		     session.delete(affectation);		     
		     session.getTransaction().commit();
		  }
		  catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     e.printStackTrace(); 
		  }finally {
			  session.close();
		  }
		
	}
	
	//Used when deleting an intervention
	public void DropCascade(Integer id)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 Transaction tx = null;
	  try {

	     tx = session.beginTransaction();
	     // do some work
	     String hql="delete from Affectation a where a.intervention_idintervention="+id;
	     Query query= session.createQuery(hql);
	     query.executeUpdate();
	     session.getTransaction().commit();
	  }
	  catch (Exception e) {
	     if (tx!=null) tx.rollback();
	     e.printStackTrace(); 
	  }finally {
		  session.close();
	  }
		
	}
	
	
	
	public void addAffectation(Affectation affectation)
	  {
			 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			 Transaction tx = null;
		  try {
		     tx = session.beginTransaction();
		     session.save(affectation);
		     session.getTransaction().commit();
		  }
		  catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     e.printStackTrace(); 
		  }finally {
			  session.close();
		  }

	  }
	
	public List listAffectation()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = 
		    		 "select a.idaffectation,a.description,i.nom,i.prenom,int.date_cloture,int.heuredebut_cloture,int.heurefin_cloture,c.nom_client from Affectation a, Intervenant i, Intervention int, Client c "+  
		    		 "where int.etat like 'En cours' and a.intervention_idintervention=int.idintervention and a.intervenant_idintervenant=i.idintervenant and c.idclient=int.client_idclient";
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
