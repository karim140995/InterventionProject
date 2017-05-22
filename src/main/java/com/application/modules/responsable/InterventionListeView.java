package com.application.modules.responsable;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import com.application.model.dao.InterventionDao;
import com.example.model.pojo.Client;
import com.example.model.pojo.Intervention;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Runo;

public class InterventionListeView extends VerticalLayout implements InterventionViewInterface {

	public  static  Table intervention_liste;
	private  InterventionListePresenter  presenter ;
	private InterventionDao model;
	private List<Object[]> intervention_list;
	private ComboBox rech;
	
	public ComboBox getRech() {
		return rech;
	}

	public void setRech(ComboBox rech) {
		this.rech = rech;
	}

	public InterventionListePresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(InterventionListePresenter presenter) {
		this.presenter = presenter;
	}

	public InterventionListeView()
	{
		
	  presenter= new InterventionListePresenter();
	  this.presenter.setView(this);
	  init();
		
	}
	
	public InterventionListeView(InterventionListePresenter interventionPresenter)
	{
		
		this.presenter=interventionPresenter;
		this.presenter.setView(this); //Presenter will be used in init()
		init();
		
	}
	
	public void init()
	{
		        //Recherche TextField
				rech= new ComboBox("Rechercher un Client :");				
				this.addComponent(rech);
				this.setComponentAlignment(rech, Alignment.TOP_RIGHT);
				
				//Panels
				Panel panel_liste= new Panel("Liste des interventions");
				this.addComponent(panel_liste);
				
				//Intervention Liste table
			    intervention_liste=new Table();
				intervention_liste.setWidth("100%");
				
				// selectable
				intervention_liste.setSelectable(true);
				intervention_liste.setMultiSelect(false);
				intervention_liste.setImmediate(true); 
				
				// turn on column reordering and collapsing
				intervention_liste.setColumnReorderingAllowed(true);
				intervention_liste.setColumnCollapsingAllowed(true);
				
				intervention_liste.addContainerProperty("Num", String.class,  null);
				intervention_liste.addContainerProperty("Objet", String.class,  null);
				intervention_liste.addContainerProperty("Payement", String.class,  null);
				intervention_liste.addContainerProperty("Priorité", String.class,  null);
				intervention_liste.addContainerProperty("Etat", String.class,  null);
				intervention_liste.addContainerProperty("Description", String.class,  null);
				intervention_liste.addContainerProperty("Remarque", String.class,  null);
				intervention_liste.addContainerProperty("Date Ajout", String.class,  null);
				intervention_liste.addContainerProperty("Date Cloture", String.class,  null);
				intervention_liste.addContainerProperty("Heure début", String.class,  null);
				intervention_liste.addContainerProperty("Heure fin", String.class,  null);
				intervention_liste.addContainerProperty("Client", String.class,  null);
				intervention_liste.addContainerProperty("Action", VerticalLayout.class,  null);
				//Collapse description Column
				intervention_liste.setColumnCollapsed("Description", true);
				intervention_liste.setColumnCollapsed("Remarque", true);

				//Add some items to the table
				panel_liste.addComponent(intervention_liste);
				
				
				//Buttons
				Resource AddIcon= new ThemeResource("img/document-add.png");
				Resource ReloadIcon= new ThemeResource("img/reload.png");
				
				HorizontalLayout buttons= new HorizontalLayout();
				buttons.setSpacing(true);
				buttons.setMargin(true);
				
				Button ajouter=new Button("Affecter");
				Button actualiser=new Button();
				ajouter.setIcon(AddIcon);
				actualiser.setIcon(ReloadIcon);
				buttons.addComponent(ajouter);
				buttons.addComponent(actualiser);
				this.addComponent(buttons);
				
				ajouter.addListener(new Button.ClickListener() {
				    public void buttonClick(ClickEvent event) {
				    	
				    	if(intervention_liste.getValue()!=null)
				    	{	
				    		String etat=InterventionListeView.intervention_liste.getContainerProperty( intervention_liste.getValue(), "Etat").toString();
				    		if( etat.equals("En cours")||etat.equals("Clôturée"))
				    		{ShowNotification2();} 
				    		else {ShowClotureWindow(); }
				    	}
				    	else 
				    	{
				    		ShowNotification();
				    		
				    	}
				    }
				});
				
				actualiser.addListener(new Button.ClickListener() {
				    public void buttonClick(ClickEvent event) {
				    	presenter.load_table();
				    }
				});
				rech.setImmediate(true);
				Property.ValueChangeListener listener = new Property.ValueChangeListener() {
				    public void valueChange(ValueChangeEvent event) {
				    	 presenter.load_table();
				    }
				};

				rech.addListener(listener);
				
				//Class Layout properties
				this.setComponentAlignment(buttons, Alignment.MIDDLE_RIGHT);
				this.setWidth("100%");
				this.setMargin(true);
				this.setSpacing(true);
				presenter.load_table();
				presenter.list_client();
		
		
	}
	
	
	public void Delete_item(Integer id)
	{
		
		intervention_liste.removeItem(id);
		
	}
	
	public void Add_item( Intervention intervention, Client client)
	{

		   TableActionsView Actions_view= new TableActionsView();
		   TableActionsPresenter Actions_presenter= new TableActionsPresenter();
		   InterventionDao Actions_model= new InterventionDao();
		   Actions_presenter.setView(Actions_view);
		   Actions_presenter.setModel(Actions_model);
		   Actions_presenter.setId(intervention.getIdintervention());
		   Actions_presenter.setListe_view(this);
		   Actions_presenter.setIntervention(intervention);
		   Actions_view.setPresenter(Actions_presenter);
			
		   intervention_liste.addItem(new Object[] {
				    intervention.getIdintervention(),intervention.getObjet(),intervention.getPayement(),intervention.getPriorité(),intervention.getEtat(),intervention.getDescription(),intervention.getRemarque(),intervention.getDate_ajout(),intervention.getDate_cloture(),intervention.getHeuredebut_cloture(),intervention.getHeurefin_cloture(),client.getNom_client(), Actions_view},intervention.getIdintervention());
		
	}
	
	public void ShowNotification()
	{
		Notification notif = new Notification("Veuillez sélectionner une intervention",
	            "",
	            Notification.TYPE_HUMANIZED_MESSAGE);
		notif.setPosition(Window.Notification.POSITION_CENTERED_TOP);
		getWindow().showNotification(notif);
		
	}
	
	public void ShowNotification2()
	{
		Notification notif = new Notification("Cette intervention est déja cloturée ou en cours",
	            "",
	            Notification.TYPE_HUMANIZED_MESSAGE);
		notif.setPosition(Window.Notification.POSITION_CENTERED_TOP);
		getWindow().showNotification(notif);
		
	}
	public Table getIntervention_liste() {
		return intervention_liste;
	}

	public void setIntervention_liste(Table intervention_liste) {
		this.intervention_liste = intervention_liste;
	}

	public InterventionDao getModel() {
		return model;
	}

	public void setModel(InterventionDao model) {
		this.model = model;
	}

	public List<Object[]> getIntervention_list() {
		return intervention_list;
	}

	public void setIntervention_list(List<Object[]> intervention_list) {
		this.intervention_list = intervention_list;
	}

	@Override
	public void ShowClotureWindow() {
		// TODO Auto-generated method stub
		Integer id_intervention=Integer.parseInt(intervention_liste.getValue().toString());
		System.out.println(id_intervention.toString());
		AffectationWindowPresenter presenter= new AffectationWindowPresenter(id_intervention);
    	AffectationWindowView view= new AffectationWindowView(presenter);
    	presenter.setView(view);
    	view.setPresenter(presenter);
    	getWindow().addWindow(view.subwindow);
		
	}
	
	
}
