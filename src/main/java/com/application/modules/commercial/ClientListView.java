package com.application.modules.commercial;

import java.sql.Date;
import java.util.List;

import com.example.model.pojo.Client;
import com.example.model.pojo.License;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class ClientListView extends VerticalLayout {
	
	public static Table Client_liste;
	public static Table License_liste;
	private ClientListPresenter presenter;
	private List<Client> Client_list;
	private List<License> License_list;
	private ComboBox rech;
	
	public ClientListPresenter getPresenters() {
		return presenter;
	}
	public void setPresenters(ClientListPresenter presenters) {
		this.presenter = presenters;
	}
	public List<Client> getClient_list() {
		return Client_list;
	}
	public void setClient_list(List<Client> client_list) {
		Client_list = client_list;
	}
	public ComboBox getRech() {
		return rech;
	}
	public void setRech(ComboBox rech) {
		this.rech = rech;
		
	}
	
	
	public ClientListView(ClientListPresenter Clientpresenter)
	{
		this.presenter=Clientpresenter;
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
		HorizontalLayout panels= new HorizontalLayout();
		panels.setHeight("100%");
		panels.setWidth("100%");
		Panel panel_liste= new Panel("Liste des Clients");
		Panel panel_license=new Panel("Liste des licenses");
		panels.addComponent( panel_liste);
		panels.addComponent( panel_license);
		this.addComponent(panels);
		
		//Intervention Liste table
		Client_liste=new Table();
		Client_liste.setHeight("550px");
		License_liste=new Table();
		License_liste.setHeight("550px");

		Client_liste.setWidth("100%");
		License_liste.setWidth("100%");
		
		// selectable
		Client_liste.setSelectable(true);
		Client_liste.setMultiSelect(false);
		Client_liste.setImmediate(true); 
		
		License_liste.setSelectable(true);
		License_liste.setMultiSelect(false);
		License_liste.setImmediate(true);
		
		// turn on column reordering and collapsing
		Client_liste.setColumnReorderingAllowed(true);
		Client_liste.setColumnCollapsingAllowed(true);
		License_liste.setColumnReorderingAllowed(true);
		License_liste.setColumnCollapsingAllowed(true);
		
		//Client Table
		Client_liste.addContainerProperty("Num", String.class,  null);
		Client_liste.addContainerProperty("Nom client", String.class,  null);
		Client_liste.addContainerProperty("Contact", String.class,  null);
		Client_liste.addContainerProperty("Adresse", String.class,  null);
		Client_liste.addContainerProperty("Télephone", String.class,  null);
		
		//License Table
		License_liste.addContainerProperty("Num", String.class,  null);
		License_liste.addContainerProperty("Application", String.class,  null);
		License_liste.addContainerProperty("Date Expiration", Date.class,  null);
		
		
		panel_liste.addComponent(Client_liste);
		panel_license.addComponent(License_liste);
		
		rech.setImmediate(true);
		Property.ValueChangeListener listener = new Property.ValueChangeListener() {
		    public void valueChange(ValueChangeEvent event) {
		    	 presenter.load_ClientListe();
		    }
		};
		
		
		Client_liste.addListener(new ItemClickEvent.ItemClickListener() {
		    @Override
		    public void itemClick(ItemClickEvent itemClickEvent) {
		        System.out.println(itemClickEvent.getItemId().toString());
		        presenter.load_LicenseListe(itemClickEvent.getItemId());
		    }
		});
		
		rech.addListener(listener);
		
		//Class Layout properties
		this.setWidth("100%");
		this.setMargin(true);
		this.setSpacing(true);
		
		
		presenter.load_ClientListe();
		presenter.list_client();
		
	}
	
	public void AddClient(Client client)
	{
		 Client_liste.addItem(new Object[] {
				   client.getIdclient(),client.getNom_client(),client.getContact(),client.getAdresse(),client.getTel()},client.getIdclient());
		
	}
	public void AddLicense(License license)
	{
		
		License_liste.addItem(new Object[] {
				  license.getIdlicense(),license.getApplication(),license.getDate_expiration()},license.getIdlicense());
		
	}
	public List<License> getLicense_list() {
		return License_list;
	}
	public void setLicense_list(List<License> license_list) {
		License_list = license_list;
	}



}
