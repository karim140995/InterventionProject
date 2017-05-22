package com.application.modules.commercial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.application.utils.NativeSelection;
import com.example.model.pojo.Client;
import com.example.model.pojo.Intervention;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;

public class AddInterventionWindowView extends VerticalLayout{

	 public Window subwindow;
	 public TextField objet;
	 public TextField Payement;
	 public NativeSelection Priorité;
	 public ComboBox Nom_Client;
	 public TextArea description;
	 public TextArea remarque;
	 List<Client> list_client;
	 private AddInterventionWindowPresenter presenter;
	 
	 public void setPresenter(AddInterventionWindowPresenter presenter)
	 {
		 this.presenter=presenter;
		 
	 }
	 
	 AddInterventionWindowView(AddInterventionWindowPresenter presenter)
		{
		 	   //Init presenter
		 	   this.presenter=presenter;
		 	   this.presenter.setView(this);
		 	   
		       subwindow = new Window("Ajouter une intervention");
			   subwindow.setModal(true);
			   subwindow.addStyleName("foo");
			    // Configure the windws layout; by default a VerticalLayout
		        VerticalLayout layout = (VerticalLayout) subwindow.getContent();
		        layout.setMargin(true);
		        layout.setSpacing(true);
		        layout.setSizeUndefined();
			
		        //Add a new Intervention Form
				Form intervention_form=new Form();
				Resource CancelIcon= new ThemeResource("img/cancel.png");
				Resource AddIcon= new ThemeResource("img/ok.png");
				
				subwindow.addComponent(intervention_form);
				
				objet=new TextField("Objet :");
				Payement=new TextField("Payement :");
				Priorité=new NativeSelection();
				description = new TextArea("Description : ");
				description.setWidth("300px");
				remarque = new TextArea("Remarque : ");
				remarque.setWidth("300px");
				Nom_Client=new ComboBox("Client");
				Priorité.setCaption("Priorité :");
				//Load client list
			    presenter.list_client();
				
				
				
				//Set Values
				HorizontalLayout buttons= new HorizontalLayout();
				buttons.setSpacing(true);
				buttons.setMargin(true);
				Button enregistrer=new Button("Ajouter");
				enregistrer.addListener(new Button.ClickListener() {
				    public void buttonClick(ClickEvent event) {
				    	presenter.add();
				    }  
				    });
				
				Button annuler=new Button("Annuler");
				annuler.addListener(new Button.ClickListener() {
				    public void buttonClick(ClickEvent event) {
				    	(subwindow.getParent()).removeWindow(subwindow);
				    }  
				    });
				buttons.addComponent(enregistrer);
				buttons.addComponent(annuler);
				buttons.setComponentAlignment(enregistrer, Alignment.BOTTOM_RIGHT);
				buttons.setComponentAlignment(annuler, Alignment.BOTTOM_RIGHT);
				buttons.setWidth("50%");
				annuler.setIcon(CancelIcon);
				enregistrer.setIcon(AddIcon);
				layout.addComponent(buttons);
				layout.setComponentAlignment(buttons, Alignment.MIDDLE_RIGHT);
				
				
				intervention_form.getLayout().addComponent(objet);
				intervention_form.getLayout().addComponent(Payement);
				intervention_form.getLayout().addComponent(Priorité);
				intervention_form.getLayout().addComponent(description);
				intervention_form.getLayout().addComponent(remarque);
				intervention_form.getLayout().addComponent(Nom_Client);
				
		}

	 
}
	
	
	
