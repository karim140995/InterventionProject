package com.application.model.dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.model.pojo.Intervention;
import com.example.model.pojo.Users;

public class InterventionDao {

	
	public void addIntervention(Intervention intervention)
	  {
			 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			 Transaction tx = null;
		  try {
		     tx = session.beginTransaction();
		     session.save(intervention);
		     session.getTransaction().commit();
		  }
		  catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     e.printStackTrace(); 
		  }finally {
			  session.close();
		  }

	  }
	
	  //Used with add affectation and deleted affectation
	  public void updateEtatIntervention(Intervention intervention)
	  {
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			 Transaction tx = null;
		  try {
		     tx = session.beginTransaction();
		     System.out.println(intervention.getIdintervention()+":"+intervention.getEtat());
		     String hql="update Intervention a set a.etat='"+intervention.getEtat()+"' where a.idintervention="+intervention.getIdintervention();
		     Query query = session.createQuery(hql);
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
		
	  public void updateIntervention(Intervention intervention)
	  {
			 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			 Transaction tx = null;
		  try {
		     tx = session.beginTransaction();
		     session.update(intervention);
		     session.getTransaction().commit();
		  }
		  catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     e.printStackTrace(); 
		  }finally {
			  session.close();
		  }
 
	  }
	  
	  public void deleteIntervention(Integer id)
	  {

		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			 Transaction tx = null;
		  try {

		     tx = session.beginTransaction();
		     Intervention intervention = new Intervention();
		     //Delete affectations first		     
		     intervention.setIdintervention(id);
		     session.delete(intervention);
		     session.getTransaction().commit();
		  }
		  catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     e.printStackTrace(); 
		  }finally {
			  session.close();
		  }

	  }

	  public List listeIntervention()
	  {		
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "select I.idintervention,I.objet,I.description,I.remarque,I.payement,I.priorité,I.etat,I.date_cloture,I.date_ajout,I.heuredebut_cloture,I.heurefin_cloture,C.nom_client,I.client_idclient FROM Intervention I, Client C "
		     		+ "where C.idclient=I.client_idclient";
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
	  
	 //Intervenant own interventions 
	  public List listeIntervention(Users user)
	  {
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "select I.idintervention,I.objet,I.description,I.remarque,I.payement,I.priorité,I.etat,I.date_cloture,I.date_ajout,I.heuredebut_cloture,"+
		    		 "I.heurefin_cloture,C.nom_client,I.client_idclient FROM Intervention I, Client C, Intervenant int, Users U, Affectation A "+
				     	"where C.idclient=I.client_idclient and int.users_idusers=U.username and username like '"+user.getusername()+"' and A.intervenant_idintervenant=int.idintervenant "+
					" and A.intervention_idintervention=I.idintervention";
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
	  
	  
	  
	  
	  public List listeIntervention(String nom_client)
	  {
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "select I.idintervention,I.objet,I.description,I.remarque,I.payement,I.priorité,I.etat,I.date_cloture,I.date_ajout,I.heuredebut_cloture,I.heurefin_cloture,C.nom_client,I.client_idclient FROM Intervention I, Client C "
		     		+ "where C.idclient=I.client_idclient and C.nom_client like '"+nom_client+"'";
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

