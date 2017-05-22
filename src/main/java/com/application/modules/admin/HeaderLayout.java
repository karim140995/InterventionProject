package com.application.modules.admin;

import com.application.model.dao.AuthenticationService;
import com.application.modules.admin.AdminMainPresenter;
import com.application.modules.intervenant.IntervenantMainPresenter;
import com.application.modules.login.LoginPresenter;
import com.application.modules.login.LoginView;
import com.application.modules.responsable.ResponsableMainPresenter;
import com.vaadin.terminal.Resource;
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

    private AdminMainPresenter presenter4;
	
	
    
    
 
	
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
				presenter4.logout();
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

	public AdminMainPresenter getPresenter4() {
		return presenter4;
	}

	public void setPresenter4(AdminMainPresenter presenter4) {
		this.presenter4 = presenter4;
	}
}
