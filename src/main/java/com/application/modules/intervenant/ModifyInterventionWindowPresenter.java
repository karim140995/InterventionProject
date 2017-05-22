package com.application.modules.intervenant;

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
    		  InterventionListeView.intervention_liste.getContainerProperty(  view.getIntervention().getIdintervention(), "Date Cloture").setValue(view.date_cloture.getFieldString());
    		  InterventionListeView.intervention_liste.getContainerProperty(  view.getIntervention().getIdintervention(), "Heure début").setValue(view.heure_debut.getTime());
    		  InterventionListeView.intervention_liste.getContainerProperty(  view.getIntervention().getIdintervention(), "Heure fin").setValue(view.heure_fin.getTime());
    		  InterventionListeView.intervention_liste.getContainerProperty(  view.getIntervention().getIdintervention(), "Etat").setValue("Clôturée");

    		  //Update object
    		  view.getIntervention().setDate_cloture(view.date_cloture.getDate());
    		  view.getIntervention().setHeuredebut_cloture(view.heure_debut.getTime().toString());
    		  view.getIntervention().setHeurefin_cloture(view.heure_debut.getTime().toString());
    		  view.getIntervention().setEtat("Clôturée");
    		  
    		  
    		 System.out.println(view.getIntervention().getClient_idclient());
    		  
    		 
    		  model.updateIntervention(view.getIntervention());
    		  
    		  
    		  
    		  //Close modify Window 
    		  (view.subwindow.getParent()).removeWindow(view.subwindow);
    		  
    	    }
	}
	
	public boolean EmptyFieldsCheck()
	{
		boolean check=true;
		if(!view.date_cloture.check())
		{
			view.date_cloture.setComponentError(new UserError("Veuillez saisir la date cloture"));
			check=false;
		}
		if(view.heure_debut.getTime().equals(""))
		{
			view.heure_debut.setComponentError(new UserError("Veuillez sasir l'heure de début"));
			check=false;
		}
		if(view.heure_fin.getTime().equals(""))
		{
			view.heure_fin.setComponentError(new UserError("Veuillez sasir l'heure de fin"));
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
