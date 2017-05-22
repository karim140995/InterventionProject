package com.application.modules.responsable;

import java.sql.Date;
import java.util.List;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class ListeAffectationView extends VerticalLayout {
	
	public static Table affectation_table;
	private ListeAffectationPresenter presenter;
	private List<Object[]> affectation_list;
	private ComboBox rech;
	
	public ComboBox getRech() {
		return rech;
	}

	public void setRech(ComboBox rech) {
		this.rech = rech;
	}

	public ListeAffectationView(ListeAffectationPresenter presenter)
	{
		this.presenter=presenter;
		presenter.setView(this);
		
		  //Recherche TextField
		rech= new ComboBox("Rechercher un Intervenant :");				
		this.addComponent(rech);
		this.setComponentAlignment(rech, Alignment.TOP_RIGHT);
		
		//Panels
		Panel panel_liste= new Panel("Liste des Affectation en cours");
		this.addComponent(panel_liste);
		
		//Intervention Liste table
		affectation_table=new Table();
		affectation_table.setWidth("100%");
		affectation_table.setHeight("500px");
		
		// selectable
		affectation_table.setSelectable(true);
		affectation_table.setMultiSelect(false);
		affectation_table.setImmediate(true); 
		
		// turn on column reordering and collapsing
		affectation_table.setColumnReorderingAllowed(true);
		affectation_table.setColumnCollapsingAllowed(true);
		
		affectation_table.addContainerProperty("Num", Integer.class,  null);
		affectation_table.addContainerProperty("Description", String.class,  null);
		affectation_table.addContainerProperty("Intervenant", String.class,  null);
		affectation_table.addContainerProperty("Date cloture", Date.class,  null);
		affectation_table.addContainerProperty("Heure début", String.class,  null);
		affectation_table.addContainerProperty("Heure fin", String.class,  null);
		affectation_table.addContainerProperty("Client", String.class,  null);

		panel_liste.addComponent(affectation_table);
			
		
		//Buttons
		Resource AddIcon= new ThemeResource("img/document-delete.png");
		Resource ReloadIcon= new ThemeResource("img/reload.png");
		
		HorizontalLayout buttons= new HorizontalLayout();
		buttons.setSpacing(true);
		buttons.setMargin(true);
		
		Button supprimer=new Button("Supprimer");
		Button actualiser=new Button();
		supprimer.setIcon(AddIcon);
		actualiser.setIcon(ReloadIcon);
		buttons.addComponent(supprimer);
		buttons.addComponent(actualiser);
		this.addComponent(buttons);
		
		supprimer.addListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	presenter.delete_intervention();
		    }
		});
		
		actualiser.addListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
				presenter.Load_list();

		    }
		});
		rech.setImmediate(true);
		Property.ValueChangeListener listener = new Property.ValueChangeListener() {
		    public void valueChange(ValueChangeEvent event) {
		    }
		};

		rech.addListener(listener);
		
		//Class Layout properties
		this.setComponentAlignment(buttons, Alignment.MIDDLE_RIGHT);
		this.setWidth("100%");
		this.setMargin(true);
		this.setSpacing(true);
		
		presenter.Load_list();
		
	}

	public List<Object[]> getAffectation_list() {
		return affectation_list;
	}

	public void setAffectation_list(List<Object[]> affectation_list) {
		this.affectation_list = affectation_list;
	}
	
	public void add_row(Object[] result)
	{
		// a.idaffectation,a.description,i.nom,i.prenom,int.date_cloture,heuredebut_cloture,heurefin_cloture,c.nom_client
		affectation_table.addItem(new Object[] {	 
				(Integer)result[0],(String)result[1],(String)result[2]+" "+(String)result[3],(Date)result[4],(String)result[5],(String)result[6],(String)result[7] },(Integer)result[0]);
		
		
	}
	

}
