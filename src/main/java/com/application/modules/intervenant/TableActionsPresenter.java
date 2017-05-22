package com.application.modules.intervenant;

import com.application.model.dao.AffectationDao;
import com.application.model.dao.InterventionDao;
import com.example.model.pojo.Client;
import com.example.model.pojo.Intervention;
import com.vaadin.ui.Table;

public class TableActionsPresenter implements TableActionsPresenterInterface {
	
	private TableActionsView view ;
	private InterventionListeView liste_view;
	private InterventionDao model;
	private Integer id;
	private Intervention intervention;
	//private Client client; //Will be used to update
	
	@Override
	public void showDescription() {
		// TODO Auto-generated method stub
		view.ShowDescriptionWindow();
	}	

	@Override
	public void delete_row() {
		// TODO Auto-generated method stub
		try{
			AffectationDao model_affectation = new AffectationDao();
		    model_affectation.DropCascade(id);
		    Intervention intervention= new Intervention();
		    intervention.setIdintervention(id);
		    intervention.setEtat("Non affectée");
			model.updateEtatIntervention(intervention);
			liste_view.Delete_item(id);
		}
		catch (Exception e){
			view.ShowErrorMessage();
		}
		
	}

	public TableActionsView getView() {
		return view;
	}

	public void setView(TableActionsView view) {
		this.view = view;
	}

	public InterventionDao getModel() {
		return model;
	}

	public void setModel(InterventionDao model) {
		this.model = model;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InterventionListeView getListe_view() {
		return liste_view;
	}

	public void setListe_view(InterventionListeView liste_view) {
		this.liste_view = liste_view;
	}

	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}


	
	
	
}
