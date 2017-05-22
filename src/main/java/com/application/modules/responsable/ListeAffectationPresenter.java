package com.application.modules.responsable;

import java.util.List;

import com.application.model.dao.AffectationDao;

public class ListeAffectationPresenter implements ListeAffectationPresenterInterface{
	
	private ListeAffectationView view;
	private AffectationDao model=new AffectationDao();
	
	public ListeAffectationView getView() {
		return view;
	}
	public void setView(ListeAffectationView view) {
		this.view = view;
	}

	public void Load_list()
	{
		List<Object[]> liste=null;
		//Removing all items	
		view.affectation_table.removeAllItems();
		if(view.getRech().getItemCaption(view.getRech().getValue())==null)
		{ liste= (List<Object[]>) model.listAffectation();}
		else {
			String nom_client=view.getRech().getItemCaption(view.getRech().getValue());
			System.out.println(nom_client);
			liste= (List<Object[]>) model.listAffectation();
		}
		
		view.setAffectation_list(liste);
		if(liste!=null)
		{
		  for (Object[] result : liste) {
			view.add_row(result);
	
		   }
			
		}
	}
  public void delete_intervention()
  {
	  Integer id= (Integer)view.affectation_table.getValue();
	  System.out.println(id);
	  model.deleteAffectation(id);
	  view.affectation_table.removeItem(view.affectation_table.getValue());
  }
	

}
