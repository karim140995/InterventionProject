package com.application.modules.admin;

import java.util.List;

import com.application.model.dao.IntervenantDao;
import com.application.model.dao.UserDao;
import com.example.model.pojo.Intervenant;
import com.example.model.pojo.Roles;
import com.example.model.pojo.Users;
import com.vaadin.terminal.UserError;

public class AdminListPresenter implements AdminListPresenterInterface {

	
	private AdminListView view;
	private UserDao model= new UserDao();
	private IntervenantDao model2=new IntervenantDao();
	
	
	@Override
	public void ajoute_utilisateur() {
		// TODO Auto-generated method stub
		Boolean check= check();
		if(check)
		{	
			try{
			Users user= new Users();
			user.setPassword(view.password.getValue().toString());
			user.setusername(view.nickname.getValue().toString());
			user.setEnabled(1);
			Roles role=new Roles();
			role.setRole(view.role.getItemCaption(view.role.getValue()));
			role.setUsername(view.nickname.getValue().toString());
			model.add_user(user, role);
	    	Integer id= (Integer) view.role.getValue();
	    	if(id==3)
	    	{
	    		Intervenant intervenant=new Intervenant();
	    		intervenant.setNom(view.nom.getValue().toString());
	    		intervenant.setPrenom(view.prenom.getValue().toString());
	    		intervenant.setEmail(view.email.getValue().toString());
	    		intervenant.setUsers_idusers(view.nickname.getValue().toString());
	    		model2.AddIntervenant(intervenant);
	    	}
			liste_utilisateur();

			}catch(Exception e){view.showErrorMessage();}
						
		}

	}

	@Override
	public void liste_utilisateur() {
		// TODO Auto-generated method stub
		view.utilisateur_liste.removeAllItems();
		List<Object[]> list= (List<Object[]>) model.listusers();
		if(list!=null)
		{
			
			 for (Object[] result : list) {	 
				 view.add_Row(result);
			 }
		}		
		
	}

	@Override
	public void supprimer_utilisateur() {
		// TODO Auto-generated method stub
		try{
		String login=view.utilisateur_liste.getContainerProperty(view.utilisateur_liste.getValue(),"Login").getValue().toString();
		model.delete_user(login);
		}catch(Exception e){System.out.println("Delete failed");} 
		liste_utilisateur();
		
	}

	private Boolean check() {
		// TODO Auto-generated method stub
		boolean check=true;
    	Integer id= (Integer) view.role.getValue();
		if(view.nickname.getValue().equals(""))
		{
			view.nickname.setComponentError(new UserError("Veuillez saisir ce champ"));
	        check=false;
		}
		if(view.password.getValue().equals(""))
		{
			view.password.setComponentError(new UserError("Veuillez saisir ce champ"));
	        check=false;
		}
		if(view.nom.getValue().equals("")&&(id==3))
		{
			view.nom.setComponentError(new UserError("Veuillez saisir ce champ"));
			check=false;
		}
		if(view.prenom.getValue().equals("")&&(id==3))
		{
			view.prenom.setComponentError(new UserError("Veuillez saisir ce champ"));
			check=false;
		}
		if(view.email.getValue().equals("")&&(id==3))
		{
			view.email.setComponentError(new UserError("Veuillez saisir ce champ"));
			check=false;
		}
		
		return check;
	}

	public AdminListView getView() {
		return view;
	}

	public void setView(AdminListView view) {
		this.view = view;
	}

}
