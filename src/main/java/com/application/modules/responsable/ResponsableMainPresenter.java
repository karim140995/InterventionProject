package com.application.modules.responsable;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           

import com.application.model.dao.AuthenticationService;
import com.application.modules.login.LoginPresenter;
import com.application.modules.login.LoginView;
import com.application.utils.RequestHolder;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;

public class ResponsableMainPresenter implements ResponsableMainPresenterInterface {
	
	private ResponsableMainView view;
	private AuthenticationService model;
	
	
	@Override
	public void changeTab() {
		// TODO Auto-generated method stub
		Tab tab = view.t.getTab(view.t.getSelectedTab());
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		model = new AuthenticationService();
		model.handleLogout(RequestHolder.getRequest());
		
        //Login form
        LoginView view= new LoginView();
        LoginPresenter presenter= new LoginPresenter(view,model);
        view.setLoginpresenter(presenter);
        this.view.getWindow().setContent(view);
	}

	public ResponsableMainView getView() {
		return view;
	}

	public void setView(ResponsableMainView view) {
		this.view = view;
	}


}
