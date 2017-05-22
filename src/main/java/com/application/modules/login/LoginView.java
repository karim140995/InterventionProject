package com.application.modules.login;

import org.springframework.security.authentication.BadCredentialsException;

import com.application.MyUI;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.BaseTheme;

public class LoginView extends VerticalLayout implements LoginViewInterface  {
		
	public final TextField loginField;
	public final PasswordField mp;
	private LoginPresenter loginpresenter;
	
	public  LoginView()
	{	
		VerticalLayout Main_layout= new VerticalLayout() ;
	    Panel panel = new Panel("Bienvenue au portail de gestion d'intervention :");
	    FormLayout form= new FormLayout();
	   
	    HorizontalLayout buttons= new HorizontalLayout();
	    
	    Resource LoginIcon= new ThemeResource("img/user.png");
		Resource PassIcon= new ThemeResource("img/lock.png");
		
	    loginField= new TextField("Saisir Login  ");
	    mp= new PasswordField("Saisir Mot de passe :   ");
	    
	    loginField.setIcon(LoginIcon);	    
	    mp.setIcon(PassIcon);
	    
	    Button submit= new Button("Se connecter");
	    Button commercial=new Button("Module commerciale");
	    Button responsable=new Button("Module responsable");
	    Button intervenant= new Button("Module intervenant");
	    Button annuler=new Button("Annuler");
	    
	    
	    annuler.addListener(new Button.ClickListener() {
	        public void buttonClick(ClickEvent event) {
	        	
	        	loginField.setValue("");
	        	mp.setValue("");
	        	
	        }
	    });
	    submit.addListener(new Button.ClickListener() {
	        public void buttonClick(ClickEvent event) {
	        	
	        	loginpresenter.login();
	        	
	        }
	    });
	    
	    

	    
	    form.setMargin(true);
	    form.setSpacing(true);
	    
	    form.addComponent(loginField);
	    form.addComponent(mp);
	    //buttons.addComponent(commercial);
	    //buttons.addComponent(responsable);
	    //buttons.addComponent(intervenant);
	    buttons.addComponent(annuler);
	    buttons.addComponent(submit);
	    buttons.setMargin(true);
	    buttons.setSpacing(true);
	    form.setWidth("100%");
	    //panel.setWidth("900px");
	    panel.addComponent(form);
        Main_layout.addComponent(panel);
        Main_layout.addComponent(buttons);
	    Main_layout.setWidth("500px");
	    this.setMargin(true,true,true,true);
	    this.addComponent(Main_layout);
	    Main_layout.setComponentAlignment(panel,Alignment.MIDDLE_CENTER);
	    Main_layout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
	    this.setComponentAlignment( Main_layout, Alignment.MIDDLE_CENTER);

	    this.setHeight("100%");
	    
	}

	@Override
	public void showBadCredientialsMessage() {
		// TODO Auto-generated method stub
		Notification notif = new Notification("Login invalide",
	            "",
	            Notification.TYPE_ERROR_MESSAGE);
		notif.setPosition(Window.Notification.POSITION_CENTERED_TOP);
		getWindow().showNotification(notif);
		
	}
	
	
	@Override
	public void showEnterLoginMessage() {
		// TODO Auto-generated method stub
		Notification notif = new Notification("Veuillez entrer votre login",
	            "",
	            Notification.TYPE_ERROR_MESSAGE);
		notif.setPosition(Window.Notification.POSITION_CENTERED_TOP);
		getWindow().showNotification(notif);
	}

	public LoginPresenter getLoginpresenter() {
		return loginpresenter;
	}

	public void setLoginpresenter(LoginPresenter loginpresenter) {
		this.loginpresenter = loginpresenter;
	}

}	
