package com.application;


import com.application.MyUI;
import com.application.model.dao.AuthenticationService;
import com.application.modules.login.LoginPresenter;
import com.application.modules.login.LoginView;
import com.application.utils.RequestHolder;
import com.vaadin.Application;
import com.vaadin.ui.Window;



/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
public class MyUI extends Application   {
	
	public static  Window w = new Window();
    

    @Override
    public void init() {
    	

    	 setMainWindow(w);
         w.setSizeFull();
         w.setTheme("reindeer");
         //Login form
         LoginView view= new LoginView();
         AuthenticationService model= new AuthenticationService();
         LoginPresenter presenter= new LoginPresenter(view,model);
         view.setLoginpresenter(presenter);
         w.setContent(view);
         
         
    }	

	public static void logout() {

		AuthenticationService authHandler = new AuthenticationService();
		authHandler.handleLogout(RequestHolder.getRequest());

 
        //Login form
        LoginView view= new LoginView();
        AuthenticationService model= new AuthenticationService();
        LoginPresenter presenter= new LoginPresenter(view,model);
        view.setLoginpresenter(presenter);
        w.setContent(view);

	}
    

    }
