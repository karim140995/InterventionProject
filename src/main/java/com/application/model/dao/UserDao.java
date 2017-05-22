package com.application.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.model.pojo.Intervention;
import com.example.model.pojo.Roles;
import com.example.model.pojo.Users;

public class UserDao {
	
	
	 public List listusers()
	  {		
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		  Transaction tx = null;
		  List results=null;
		  try {
			 tx = session.beginTransaction(); 
		     String hql = "select u.username,u.password,r.role from Users u, Roles r where r.username=u.username";
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
	 public void add_user(Users user, Roles role)
	 {

		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 Transaction tx = null;
	  try {
	     tx = session.beginTransaction();
	     session.save(user);
	     session.save(role);
	     session.getTransaction().commit();
	  }
	  catch (Exception e) {
	     if (tx!=null) tx.rollback();
	     e.printStackTrace(); 
	  }finally {
		  session.close();
	  }
		 
	 }
	 public void delete_user(String login)
	 {
		 
			 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			 Transaction tx = null;
		  try {
	
		     tx = session.beginTransaction();
		     Users user=new Users();
		     //Delete Role
		     String hql="delete from Roles where username like '"+login+"'";
		     Query query= session.createQuery(hql);
		     query.executeUpdate();
		     //Delete user 		     
		     user.setusername(login);
		     session.delete(user);
		     //Delete intervenant data		     
		     hql="delete from Intervenant where users_idusers like '"+login+"'";
		     query=session.createQuery(hql);
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
	
	
	

}
