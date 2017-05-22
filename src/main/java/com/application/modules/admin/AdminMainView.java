package com.application.modules.admin;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class AdminMainView extends VerticalLayout implements
TabSheet.SelectedTabChangeListener {
	
    // Icons for the table
    private static final ThemeResource icon1 = new ThemeResource(
            "img/users.png");

    public TabSheet t;
	public HeaderLayout header;
	private AdminMainPresenter presenter;
	
	public AdminMainView()
	{
	
		//Layouts
		this.setWidth("100%");
		this.setHeight("100%");
		header= new HeaderLayout();
		this.addComponent(header);
		this.setMargin(false);		
		
		//Admin List
		AdminListPresenter presenter = new AdminListPresenter();
		AdminListView view= new AdminListView(presenter);
		presenter.setView(view);
		view.setMargin(true);
		
		
	     t = new TabSheet();
	     t.setHeight("760px");
	     t.setWidth("100%");

	     t.addTab(view, "Liste utilisateurs", icon1);
	     t.addListener(this);
	     addComponent(t);	
	}

	@Override
	public void selectedTabChange(SelectedTabChangeEvent event) {
		// TODO Auto-generated method stub
		presenter.changeTab();
	}

	public AdminMainPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(AdminMainPresenter presenter) {
		this.presenter = presenter;
	}
}
