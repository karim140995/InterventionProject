package com.application.modules.login;

import org.springframework.security.authentication.BadCredentialsException;

import com.application.model.dao.AuthenticationService;
import com.application.model.dao.SecurityUtils;
import com.application.modules.admin.AdminMainPresenter;
import com.application.modules.admin.AdminMainView;
import com.application.modules.commercial.CommercialMainPresenter;
import com.application.modules.commercial.CommercialMainView;
import com.application.modules.intervenant.IntervenantMainPresenter;
import com.application.modules.intervenant.IntervenantMainView;
import com.application.modules.responsable.ResponsableMainPresenter;
import com.application.modules.responsable.ResponsableMainView;
import com.application.utils.RequestHolder;
import com.vaadin.ui.Window.Notification;

public class LoginPresenter implements LoginPresenterInterface {
	
	private LoginView view;
	private AuthenticationService model;

	@Override
	public void login() {
		// TODO Auto-generated method stub
		
    	String loginField_str=(String)view.loginField.getValue();
    	String mp_str= (String)view.mp.getValue();
    	
    	if( mp_str.equals("") || loginField_str.equals(""))
    	{
    		 view.showEnterLoginMessage();
    		
    	}
    	
    	else //Authenticate user
    		
    	{
    		
    		AuthenticationService authHandler = new AuthenticationService();

    		try {

    			authHandler.handleAuthentication((String)view.loginField.getValue(),(String)view.mp.getValue(), RequestHolder.getRequest());
    			if(SecurityUtils.isLoggedIn())
    			{
    				if(SecurityUtils.hasRole("ROLE_COMMERCIAL"))
    				{
    					openCommercialModule();
    				}
    				else if(SecurityUtils.hasRole("ROLE_RESPONSABLE"))
    				{
    					openResponsableModule();
    				}
    				else if(SecurityUtils.hasRole("ROLE_INTERVENANT"))
    				{
    					 openIntervenantModule();
    				}
    				else if(SecurityUtils.hasRole("ROLE_ADMIN"))
    				{
    					openAdminModule();
    				}
    			}	

    		} catch (BadCredentialsException e) {
    			

    			view.showBadCredientialsMessage();

    		}
    		
    	}
		
	}

	@Override
	public void openCommercialModule() {
		// TODO Auto-generated method stub
		CommercialMainPresenter presenter = new CommercialMainPresenter();
		CommercialMainView layout =new CommercialMainView();
		layout.setPresenter(presenter);
		presenter.setView(layout);
		layout.header.SetPresenter(presenter);
		view.getWindow().setContent(layout);
	}
	
	public LoginPresenter(LoginView view, AuthenticationService model) {
		this.view = view;
		this.setModel(model);
	}

	public AuthenticationService getModel() {
		return model;
	}

	public void setModel(AuthenticationService model) {
		this.model = model;
	}

	public void openResponsableModule() {
		// TODO Auto-generated method stub
		ResponsableMainPresenter presenter = new ResponsableMainPresenter();
		ResponsableMainView layout =new ResponsableMainView();
		layout.setPresenter(presenter);
		presenter.setView(layout);
		layout.header.SetPresenter(presenter);
		view.getWindow().setContent(layout);
		
	}

	public void openIntervenantModule() {
		// TODO Auto-generated method stub
		IntervenantMainPresenter presenter = new IntervenantMainPresenter();
		IntervenantMainView layout =new IntervenantMainView();
		layout.setPresenter(presenter);
		presenter.setView(layout);
		layout.header.setPresenter3(presenter);
		view.getWindow().setContent(layout);
		
	}
	
	

	public void openAdminModule() {
		// TODO Auto-generated method stub
		AdminMainPresenter presenter = new AdminMainPresenter();
		AdminMainView layout =new AdminMainView();
		layout.setPresenter(presenter);
		presenter.setView(layout);
		layout.header.setPresenter4(presenter);
		view.getWindow().setContent(layout);
		
	}


}
