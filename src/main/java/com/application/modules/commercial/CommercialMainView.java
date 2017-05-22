package com.application.modules.commercial;

import com.application.model.dao.InterventionDao;
import com.application.modules.commercial.HeaderLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class CommercialMainView extends VerticalLayout implements
TabSheet.SelectedTabChangeListener {
	
    // Icons for the table
    private static final ThemeResource icon1 = new ThemeResource(
            "img/note.png");
    private static final ThemeResource icon2 = new ThemeResource(
            "img/users.png");
    private static final ThemeResource icon3 = new ThemeResource(
            "img/email.png");

    public TabSheet t;
	public HeaderLayout header;
	private CommercialMainPresenter presenter;
	
	public CommercialMainView()
	{
	
		//Layouts
		this.setWidth("100%");
		header= new HeaderLayout();
		this.addComponent(header);
		this.setMargin(false);		
		
	     // Liste intervention tab
		 InterventionListePresenter InterventionPresenter= new InterventionListePresenter();
		 InterventionDao intervention_model= new InterventionDao();
		 InterventionPresenter.setModel(intervention_model);
	     InterventionListeView l1 = new InterventionListeView(InterventionPresenter);
	     InterventionPresenter.setView(l1);
	     
	     
	     l1.setMargin(true);
	     
	     // Client tab
	     ClientListPresenter ClientPresenter=new ClientListPresenter();
	     ClientListView ClientView= new ClientListView(ClientPresenter);
	     ClientPresenter.setView(ClientView);
	     ClientView.setMargin(true);
	        
	     t = new TabSheet();
	     t.setHeight("760px");
	     t.setWidth("100%");

	     t.addTab(l1, "Liste intervention", icon3);
	     t.addTab(ClientView, "Liste client", icon2);
	     t.addListener(this);
	     addComponent(t);	
	}

	@Override
	public void selectedTabChange(SelectedTabChangeEvent event) {
		// TODO Auto-generated method stub
		presenter.changeTab();
	}

	public CommercialMainPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(CommercialMainPresenter presenter) {
		this.presenter = presenter;
	}
}
