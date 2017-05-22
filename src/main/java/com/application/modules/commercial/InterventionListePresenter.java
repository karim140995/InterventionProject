package com.application.modules.commercial;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.sql.Date;

import com.application.model.dao.ClientDao;
import com.application.model.dao.InterventionDao;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;
import com.application.modules.commercial.TableActionsView;
import com.example.model.pojo.Client;
import com.example.model.pojo.Intervention;
public class InterventionListePresenter implements InterventionListePresenterInterface {

	private InterventionListeView view;
	private InterventionDao model;
	private ClientDao client_model=new ClientDao();
	private List list_client;
	@Override
	public Void add_Intervention() {
		// TODO Auto-generated method stub	
		view.ShowAddInterventionWindow();
		return null;
	}

	@Override
	public Void load_table() {
		
		List<Object[]> liste=null;
		//Removing all items	
		InterventionListeView.intervention_liste.removeAllItems();
		//Loading new items
		if(view.getRech().getItemCaption(view.getRech().getValue())==null)
		{ liste= (List<Object[]>) model.listeIntervention();}
		else {
			String nom_client=view.getRech().getItemCaption(view.getRech().getValue());
			liste= (List<Object[]>) model.listeIntervention(nom_client); 
		}
		view.setIntervention_list(liste);
    	if(liste!=null)
    	{	
    	System.out.println("im not null");
    	 for (Object[] result : liste) {
    		 
    		 //11 Item
    		 //I.idintervention,I.objet,I.description,I.remarque,I.payement,I.priorité,I.etat,I.date_cloture,I.date_ajout,I.heuredebut_cloture,I.heurefin_cloture,C.nom_client FROM Intervention I, Client C    		 
    		 Intervention intervention= new Intervention();
    		 intervention.setIdintervention((Integer)result[0]);
    		 intervention.setObjet((String)result[1]);
    		 intervention.setDescription((String)result[2]);
    		 intervention.setRemarque((String)result[3]);
    		 intervention.setPayement((String)result[4]);
    		 intervention.setPriorité((String)result[5]);
    		 intervention.setEtat((String)result[6]);
    		 intervention.setDate_cloture((Date)result[7]);
    		 intervention.setDate_ajout((Date)result[8]);
    		 intervention.setHeuredebut_cloture((String)result[9]);
    		 intervention.setHeurefin_cloture((String)result[10]);
    		 Client client= new Client();
    		 client.setNom_client((String)result[11]);
    		 intervention.setClient_idclient((Integer)result[12]);
    		 view.Add_item(intervention,client);
    	
    		   
    	    }
    	} 
		return null;
	}
	public void list_client() {
		// TODO Auto-generated method stub
		list_client= client_model.ListeClient();
		Iterator<Client> iterator = list_client.iterator();
		while(iterator.hasNext())
		{
			Client client=iterator.next();
			view.getRech().addItem(client.getIdclient());
			view.getRech().setItemCaption(client.getIdclient(),client.getNom_client());
		}	
		
	} 
	@Override
	public  Void  Search() {
		// TODO Auto-generated method stub
		if(view.getRech().getItemCaption(view.getRech().getValue())!=null)
		{
			   
				
				
		}
		
		
		
		return null;
	}

	@Override
	public void load_ClientListe() {
		// TODO Auto-generated method stub
		
	}

	public InterventionListeView getView() {
		return view;
	}

	public void setView(InterventionListeView view) {
		this.view = view;
	}

	public InterventionDao getModel() {
		return model;
	}

	public void setModel(InterventionDao model) {
		this.model = model;
	}

}
