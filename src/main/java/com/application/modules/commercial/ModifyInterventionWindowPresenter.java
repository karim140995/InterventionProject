package com.application.modules.commercial;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.application.model.dao.InterventionDao;
import com.vaadin.terminal.UserError;

public class ModifyInterventionWindowPresenter implements ModifyInterventionWindowInterface {

	private ModifyInterventionWindowView view;
	private InterventionDao model;
	
	
	public void setModel(InterventionDao model)
	{
		this.model=model;	
	}
	@Override
	public void save() {
		// TODO Auto-generated method stub
		
    	Boolean check= EmptyFieldsCheck();
    	  if(check)
    	    {
    		  //Update row items
    		  InterventionListeView.intervention_liste.getContainerProperty(  view.getIntervention().getIdintervention(), "Objet").setValue(view.objet.getValue());
    		  InterventionListeView.intervention_liste.getContainerProperty(  view.getIntervention().getIdintervention(), "Payement").setValue(view.Payement.getValue());
    		  InterventionListeView.intervention_liste.getContainerProperty(  view.getIntervention().getIdintervention(), "Priorité").setValue(view.Priorité.value());
    		  InterventionListeView.intervention_liste.getContainerProperty(  view.getIntervention().getIdintervention(), "Description").setValue(view.description.getValue());
    		  InterventionListeView.intervention_liste.getContainerProperty(  view.getIntervention().getIdintervention(), "Remarques").setValue(view.remarque.getValue());
    		  //Update object
   
    		  view.getIntervention().setObjet(view.objet.getValue().toString());
    		  view.getIntervention().setPayement(view.Payement.getValue().toString());
    		  view.getIntervention().setPriorité(view.Priorité.value());
    		  view.getIntervention().setDescription(view.description.getValue().toString());
    		  view.getIntervention().setRemarque(view.remarque.getValue().toString());
    		  
    		 System.out.println(view.getIntervention().getClient_idclient());
    		  
    		 
    		  model.updateIntervention(view.getIntervention());
    		  
    		  
    		  
    		  //Close modify Window 
    		  (view.subwindow.getParent()).removeWindow(view.subwindow);
    	    }
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
		if(view.description.getValue().equals(""))
		{
			view.description.setComponentError(new UserError("Veuillez saisir ce champ"));
			check=false;
		}
		if (!(check))
		{
			return false;
		}
		return true;
		
	} 
	

	public ModifyInterventionWindowView getView() {
		return view;
	}

	public void setView(ModifyInterventionWindowView view) {
		this.view = view;
	}

}
