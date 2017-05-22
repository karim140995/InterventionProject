package com.application.modules.commercial;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.application.model.dao.ClientDao;
import com.application.model.dao.InterventionDao;
import com.example.model.pojo.Client;
import com.example.model.pojo.Intervention;
import com.vaadin.terminal.UserError;

public class AddInterventionWindowPresenter implements AddInterventionWindowPresenterInterface {

	InterventionDao model=new InterventionDao();
	ClientDao client_model= new ClientDao();
	AddInterventionWindowView view;
	InterventionListeView liste_view;
	List<Client> list_client;
	
	
	public AddInterventionWindowPresenter(InterventionListeView interventionListeView) {
		// TODO Auto-generated constructor stub
		this.liste_view=interventionListeView;
	}

	
	public void add() {
		// TODO Auto-generated method stub
		Boolean check=EmptyFieldsCheck();
		if(check)
		{
			Intervention intervention = new Intervention();
			intervention.setObjet(view.objet.getValue().toString());
			intervention.setPayement(view.Payement.getValue().toString());
			intervention.setPriorité(view.Priorité.value());
			intervention.setDescription(view.description.getValue().toString());
			intervention.setRemarque(view.remarque.getValue().toString());
			intervention.setEtat("Non affectée");
			System.out.println(view.Nom_Client.getValue());
			intervention.setClient_idclient(Integer.parseInt(view.Nom_Client.getValue().toString()));
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			intervention.setDate_ajout(date);
			model.addIntervention(intervention);
			//Add the new Item to the Table
			
			TableActionsView Actions_view= new TableActionsView();
			TableActionsPresenter Actions_presenter= new TableActionsPresenter();
			InterventionDao Actions_model= new InterventionDao();
			Actions_presenter.setView(Actions_view);
			Actions_presenter.setModel(Actions_model);
			Actions_presenter.setId(intervention.getIdintervention());
			
			
			
			
			
			Actions_presenter.setListe_view(liste_view);
			Actions_presenter.setIntervention(intervention);
			Actions_view.setPresenter(Actions_presenter);
			InterventionListeView.intervention_liste.addItem(new Object[] {
					    intervention.getIdintervention(),intervention.getObjet(),intervention.getPayement(),intervention.getPriorité(),intervention.getEtat(),intervention.getDescription(),intervention.getRemarque(),intervention.getDate_ajout(),view.Nom_Client.getItemCaption(view.Nom_Client.getValue()), Actions_view},intervention.getIdintervention());
			(view.subwindow.getParent()).removeWindow(view.subwindow);
		}
	}
	
	public void setModel(InterventionDao model)
	{
		this.model=model;
		
	}
	
	public void setView(AddInterventionWindowView view)
	{
		this.view=view;
		
	}
	
	public boolean EmptyFieldsCheck()
	{
		boolean check=true;
		if(view.objet.getValue().equals(""))
		{
			view.objet.setComponentError(new UserError("Veuillez saisir ce champ"));
	        check=false;
		}
		if(view.Payement.getValue().equals(""))
		{
			view.Payement.setComponentError(new UserError("Veuillez saisir ce champ"));
	        check=false;
		}
		if(view.Priorité.value().equals(""))
		{
			view.Priorité.setComponentError(new UserError("Veuillez saisir ce champ"));
	        check=false;
		}
		if(view.description.getValue().equals(""))
		{
			view.description.setComponentError(new UserError("Veuillez saisir ce champ"));
			check=false;
		}
		if (view.Nom_Client.getValue()==null || view.Nom_Client.getValue().toString().equals(""))
		{
			view.Nom_Client.setComponentError(new UserError("Veuillez saisir ce champ"));
			check=false;
		}
		if (!(check))
		{
			return false;
		}
		return true;
		
	}
	

	public void list_client() {
		// TODO Auto-generated method stub
		list_client= client_model.ListeClient();
		Iterator<Client> iterator = list_client.iterator();
		while(iterator.hasNext())
		{
			Client client=iterator.next();
			view.Nom_Client.addItem(client.getIdclient());
			view.Nom_Client.setItemCaption(client.getIdclient(),client.getNom_client());
		}	
		
	} 
	
	
	
	
	
}
