package com.application.modules.admin;

import java.util.List;

import com.application.modules.intervenant.InterventionListePresenter;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window.Notification;

public class AdminListView extends VerticalLayout {
	
	TextField nickname;
	PasswordField password;
	NativeSelect role;
	TextField nom;
	TextField prenom;
	TextField email;
	Button submit;
	
	public  static  Table utilisateur_liste;
	private  AdminListPresenter  presenter ;
	public AdminListPresenter getPresenter() {
		return presenter;
	}
	public void setPresenter(AdminListPresenter presenter) {
		this.presenter = presenter;
	}
	
	
	public AdminListView(AdminListPresenter interventionPresenter)
	{
		
		this.presenter=interventionPresenter;
		this.presenter.setView(this); //Presenter will be used in init()
		init();
		
	}
	
	public void init()
	{
		HorizontalLayout panels= new HorizontalLayout();
		VerticalLayout list_layout= new VerticalLayout();
		VerticalLayout form_layout= new VerticalLayout();
		panels.setWidth("100%");
		panels.setMargin(true);
		
		//Panels 
		Panel list_panel= new Panel("Liste utilisateurs");
		Panel new_form= new Panel("Ajouter utilisateur");
		list_layout.addComponent(list_panel);
		form_layout.addComponent(new_form);
		panels.addComponent(list_layout);
		panels.addComponent(form_layout);
		
		//Table
		utilisateur_liste=new Table();
		utilisateur_liste.setWidth("100%");
		
		
		// selectable
		utilisateur_liste.setSelectable(true);
		utilisateur_liste.setMultiSelect(false);
		utilisateur_liste.setImmediate(true); 
		
		// turn on column reordering and collapsing
		utilisateur_liste.setColumnReorderingAllowed(true);
		utilisateur_liste.setColumnCollapsingAllowed(true);
		
		
		utilisateur_liste.addContainerProperty("Login", String.class,  null);
		utilisateur_liste.addContainerProperty("Mot de passe", String.class,  null);
		utilisateur_liste.addContainerProperty("Role", String.class,  null);
		list_panel.addComponent(utilisateur_liste);
		
		
		
		//Form new
		HorizontalLayout first_form=new HorizontalLayout();
		HorizontalLayout second_form=new HorizontalLayout();
		first_form.setMargin(true);
		first_form.setSpacing(true);
		first_form.setWidth("100%");
		nickname= new TextField("Login :");
		password= new PasswordField();
		role= new NativeSelect();
		role.addItem(1);
		role.setItemCaption(1, "ROLE_COMMERCIAL");
		role.addItem(2);
		role.setItemCaption(2, "ROLE_RESPONSABLE");
		role.addItem(3);
		role.setItemCaption(3, "ROLE_INTERVENANT");
		role.addItem(4);
		role.setItemCaption(4, "ROLE_ADMIN");
		role.setImmediate(true);
		role.setNullSelectionAllowed(false);
		role.setMultiSelect(false);
		role.select(1);
		password.setCaption("Mot de passe :");
		role.setCaption("Role :");
		first_form.addComponent(nickname);
		first_form.addComponent(password);
		first_form.addComponent(role);
		new_form.addComponent(first_form);
		nom= new TextField("");
		prenom=new TextField ("");
		email= new TextField("");
		nom.setReadOnly(true);
		prenom.setReadOnly(true);
		email.setReadOnly(true);
		nom.setCaption("");
		prenom.setCaption("");
		email.setCaption("");
		second_form.addComponent(nom);
		second_form.addComponent(prenom);
		second_form.addComponent(email);
		second_form.setMargin(true);
		second_form.setSpacing(true);
		second_form.setWidth("100%");
		
		
		role.addListener(new Property.ValueChangeListener() {

	        public void valueChange(ValueChangeEvent event) {
	        	
	        	Integer id= (Integer) role.getValue();
	        	if(id==3)
	        	{
	        		nom.setReadOnly(false);
	        		prenom.setReadOnly(false);
	        		email.setReadOnly(false);
	        		nom.setCaption("Nom intervenant  :");
	        		prenom.setCaption("Prénom intervenant  :");
	        		email.setCaption("Email intervenant  :");
	        	}
	        	else
	        	{
	        		nom.setReadOnly(true);
	        		prenom.setReadOnly(true);
	        		email.setReadOnly(true);
	        		nom.setCaption("");
	        		prenom.setCaption("");
	        		email.setCaption("");
	        		
	        	}
	        }   
	    }); 
		Resource CancelIcon= new ThemeResource("img/cancel.png");
		Resource AddIcon= new ThemeResource("img/ok.png");
		HorizontalLayout buttons= new HorizontalLayout();
		buttons.setSpacing(true);
		buttons.setMargin(true);
		Button enregistrer=new Button("Ajouter");
		enregistrer.addListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	presenter.ajoute_utilisateur();
		    }  
		    });
		
		Button annuler=new Button("Annuler");
		annuler.addListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    }  
		    });
		buttons.addComponent(enregistrer);
		buttons.addComponent(annuler);
		buttons.setComponentAlignment(enregistrer, Alignment.MIDDLE_RIGHT);
		buttons.setComponentAlignment(annuler, Alignment.MIDDLE_RIGHT);
		annuler.setIcon(CancelIcon);
		enregistrer.setIcon(AddIcon);
		
		new_form.addComponent(second_form);
		form_layout.addComponent(buttons);
		form_layout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
		
		
		//List buttons
		Resource Iconrefresh= new ThemeResource("img/reload.png");
		Resource Icondelete= new ThemeResource("img/trash.png");
		HorizontalLayout list_buttons= new HorizontalLayout();
		list_buttons.setSpacing(true);
		list_buttons.setMargin(true);
		Button refresh=new Button("");
		refresh.addListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	presenter.liste_utilisateur();
		    }  
		    });
		
		Button delete=new Button("");
		delete.setDescription("Supprimer utlisateur");
		refresh.setDescription("Rafraîchir");
		delete.addListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	presenter.supprimer_utilisateur();
		    }  
		    });
		list_buttons.addComponent(delete);
		list_buttons.addComponent(refresh);
		list_buttons.setComponentAlignment(delete, Alignment.MIDDLE_RIGHT);
		list_buttons.setComponentAlignment(refresh, Alignment.MIDDLE_RIGHT);
		refresh.setIcon(Iconrefresh);
		delete.setIcon(Icondelete);
		
		
		
		list_layout.addComponent(list_buttons);
		list_layout.setComponentAlignment(list_buttons, Alignment.BOTTOM_RIGHT);

		
	
		
		
		
		
		
		//Layout properties
		this.setWidth("100%");
		this.setHeight("100%");
		this.setMargin(true);
		this.setSpacing(true);
		presenter.liste_utilisateur();
		
		this.addComponent(panels);
	}
	public void add_Row(Object[] result) {
		// TODO Auto-generated method stub
		utilisateur_liste.addItem(new Object[] {
				(String)result[0],(String)result[1],(String)result[2]},(String)result[0]);
		
	}
	public void showErrorMessage() {
		// TODO	// TODO Auto-generated method stub
		Notification notif = new Notification("Erreur :",
	            "Login déja existant",
	            Notification.TYPE_ERROR_MESSAGE);
		notif.setPosition(Window.Notification.POSITION_CENTERED_TOP);
		getWindow().showNotification(notif);
		
		
	}
	
}
