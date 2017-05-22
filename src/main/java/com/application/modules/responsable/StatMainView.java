package com.application.modules.responsable;



import org.vaadin.addon.JFreeChartWrapper;

import com.application.model.dao.StatDao;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class StatMainView extends VerticalLayout  {
	
	private final StatMainPresenter presenter;
	private final StatDao model=new StatDao();
	private final Table table= new Table();
	
	private JFreeChartWrapper pieChart;
	private JFreeChartWrapper barChart;
	private JFreeChartWrapper SpecificIntervenantChart;
	private JFreeChartWrapper SpecificClientChart;

	private ComboBox intervenant ;
	private ComboBox client;
	
	
	public ComboBox getClient() {
		return client;
	}



	public void setClient(ComboBox client) {
		this.client = client;
	}



	public ComboBox getIntervenant() {
		return intervenant;
	}



	public void setIntervenant(ComboBox intervenant) {
		this.intervenant = intervenant;
	}



	public StatMainView(StatMainPresenter presenter)
    {
			HorizontalLayout charts_layout= new HorizontalLayout();
			charts_layout.setSpacing(true);
			charts_layout.setMargin(true);
	        Panel etat_panel=new Panel("Etat des Interventions :");
	        Panel allintervenant_panel=new Panel("Rendement :");
			this.setSpacing(true);
			charts_layout.setWidth("100%");
			this.presenter=presenter;
			presenter.setView(this);
			this.setMargin(true);
			pieChart = presenter.intervention_stat();
			pieChart.setWidth("100%");
		    barChart = presenter.intervenant_stat();
			barChart.setWidth("100%");
			
			etat_panel.addComponent(pieChart);
			allintervenant_panel.addComponent(barChart);
			charts_layout.addComponent(etat_panel);
			charts_layout.addComponent(allintervenant_panel);
			this.addComponent(charts_layout);
			HorizontalLayout numbers= new HorizontalLayout();
			numbers.setWidth("100%");
			numbers.setMargin(true);
			Integer nb_na=((Long) model.NonAffectéeIntervention().iterator().next()).intValue();
			Integer nb_c=((Long) model.CloturéIntervention().iterator().next()).intValue();
			Integer nb_ec=((Long) model.EncoursIntervention().iterator().next()).intValue();
			
			Resource okIcon= new ThemeResource("img/ok.png");
			Resource ReloadIcon= new ThemeResource("img/reload.png");
			Resource deleteIcon= new ThemeResource("img/cancel.png");
			table.addContainerProperty("Nombre Interventions cloturées", Integer.class, 0);
			table.setColumnIcon("Nombre Interventions cloturées", okIcon);
			table.addContainerProperty("Nombre Interventions en cours", Integer.class, 0);
			table.setColumnIcon("Nombre Interventions en cours", ReloadIcon);
			table.addContainerProperty("Nombre Interventions non affectées", Integer.class, 0);
			table.setColumnIcon("Nombre Interventions non affectées", deleteIcon);
			table.addItem(new Object[]{nb_c,nb_ec,nb_na},1);
	        table.setPageLength(2);

	        numbers.addComponent(table);
	        numbers.setHeight("100%");
	        numbers.setComponentAlignment(table, Alignment.BOTTOM_CENTER);
	        
	        HorizontalLayout specific_charts_layout= new HorizontalLayout();
	        specific_charts_layout.setSpacing(true);
	        specific_charts_layout.setMargin(true);
	        //specific intervenant chart
	        Panel intervenant_panel=new Panel("Statistique Intervenant :");
	        HorizontalLayout intervenant_panel_layout= new HorizontalLayout();
	        intervenant_panel_layout.setWidth("100%");
	        intervenant=new ComboBox();
			intervenant.setNullSelectionAllowed(false);
			intervenant.setImmediate(true);
			intervenant.setCaption("Choisir un nom d'intervenant :");
			intervenant_panel_layout.addComponent(intervenant);  
			intervenant_panel_layout.setComponentAlignment(intervenant, Alignment.TOP_RIGHT);
	        presenter.load_intervenant();
	        intervenant.setValue(intervenant.getItemIds().iterator().next());
	    	SpecificIntervenantChart = presenter.Specific_intervenant_stat();

	        intervenant_panel.addComponent(intervenant_panel_layout);
	
			Property.ValueChangeListener listener = new Property.ValueChangeListener() {
			    public void valueChange(ValueChangeEvent event) {
			    	 SpecificIntervenantChart.setVisible(true);
			     	 intervenant_panel.removeComponent(SpecificIntervenantChart);
			    	 SpecificIntervenantChart = presenter.Specific_intervenant_stat();
			    	 intervenant_panel.addComponent(SpecificIntervenantChart);
			    	
			    }
			};
	        intervenant.addListener(listener); 
	    	intervenant_panel.addComponent(SpecificIntervenantChart);
	    	SpecificIntervenantChart.setVisible(false);
	        
	    	
	    	
	    	//Specific client chart
	        Panel client_panel=new Panel("Intervention par Client :");
	        HorizontalLayout client_panel_layout= new HorizontalLayout();
	        client_panel_layout.setWidth("100%");
	        client=new ComboBox();
			client.setNullSelectionAllowed(false);
			client.setImmediate(true);
			client.setCaption("Choisir un nom de client :");
	        presenter.load_client();
			client_panel_layout.addComponent(client);  
			client_panel_layout.setComponentAlignment(client, Alignment.TOP_RIGHT);
	        
	        client_panel.addComponent(client_panel_layout);
	        
	        client.setValue(client.getItemIds().iterator().next());
	    	SpecificClientChart = presenter.Specific_client_stat();

	        Property.ValueChangeListener listener2 = new Property.ValueChangeListener() {
			    public void valueChange(ValueChangeEvent event) {
			    	
			    	 SpecificClientChart.setVisible(true);
			     	 client_panel.removeComponent(SpecificClientChart);
			     	 SpecificClientChart = presenter.Specific_client_stat();
			    	 client_panel.addComponent(SpecificClientChart);
			    }
			};
	        client.addListener(listener2); 
	        
	    	client_panel.addComponent(SpecificClientChart);
	    	SpecificClientChart.setVisible(false);
	        
	        
	        specific_charts_layout.addComponent(intervenant_panel);
	        specific_charts_layout.addComponent(client_panel);
	        
	        specific_charts_layout.setWidth("100%");
	        this.addComponent(specific_charts_layout);
			this.addComponent(numbers);
			

    }
		
	




}
