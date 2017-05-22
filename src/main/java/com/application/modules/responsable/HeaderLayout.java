package com.application.modules.responsable;

import com.application.model.dao.AuthenticationService;
import com.application.modules.commercial.CommercialMainPresenter;
import com.application.modules.intervenant.IntervenantMainPresenter;
import com.application.modules.login.LoginPresenter;
import com.application.modules.login.LoginView;
import com.application.modules.responsable.ResponsableMainPresenter;
import com.application.utils.RequestHolder;
import com.vaadin.terminal.ClassResource;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class HeaderLayout extends HorizontalLayout {

	private CommercialMainPresenter presenter;
	private ResponsableMainPresenter presenter2;
	
private IntervenantMainPresenter presenter3;
	
	
	public IntervenantMainPresenter getPresenter3() {
		return presenter3;
	}

	public void setPresenter3(IntervenantMainPresenter presenter3) {
		this.presenter3 = presenter3;
	}
	
	public void SetPresenter(ResponsableMainPresenter presenter)
	{
		this.presenter2=presenter;
		
	}
	
	public void SetPresenter(CommercialMainPresenter presenter)
	{
		this.presenter=presenter;
		
	}
	
	public HeaderLayout()
	{
		
		
		//LogoutLayout
		HorizontalLayout LogoutLayout = new HorizontalLayout();
		LogoutLayout.setSpacing(true);
		LogoutLayout.setMargin(true);
		
		//Logo
		Resource iconResource = new ThemeResource("img/logo.png");
		Embedded image = new Embedded(null,
			    iconResource);
		this.addComponent(image);
		
		
		//Logout Button and Label
		Label label=new Label("Bienvenue, ");
		LogoutLayout.addComponent(label);
		Button logout=new Button("Déconnexion");
		logout.addListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
				presenter2.logout();
		    }
		});
		
		LogoutLayout.addComponent(logout);
		
	    
		
		//Header Layout formatting
		this.addComponent(LogoutLayout);
		this.setComponentAlignment(LogoutLayout, Alignment.TOP_RIGHT);
		this.setComponentAlignment(image, Alignment.TOP_LEFT);
		this.setHeight("140px");
		this.setWidth("100%");
		this.setMargin(true);
	
		
	}
}
