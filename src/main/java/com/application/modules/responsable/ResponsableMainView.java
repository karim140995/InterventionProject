package com.application.modules.responsable;

import com.application.model.dao.InterventionDao;
import com.application.modules.commercial.ClientListPresenter;
import com.application.modules.commercial.ClientListView;
import com.application.modules.responsable.HeaderLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class ResponsableMainView extends VerticalLayout implements
TabSheet.SelectedTabChangeListener {
	
    // Icons for the table
    private static final ThemeResource icon1 = new ThemeResource(
            "img/note.png");
    private static final ThemeResource icon2 = new ThemeResource(
            "img/users.png");
    private static final ThemeResource icon3 = new ThemeResource(
            "img/email.png");
    private static final ThemeResource icon4 = new ThemeResource(
            "img/stats-icon.jpg");

    public TabSheet t;
	public HeaderLayout header;
	private ResponsableMainPresenter presenter;
	
	public ResponsableMainView()
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
	    
	     // Affectation tab
	    ListeAffectationPresenter AffectationPresenter= new ListeAffectationPresenter();
	    ListeAffectationView AffectationView= new ListeAffectationView(AffectationPresenter);
	    AffectationView.setMargin(true);
	     
	     //Client tab
	     ClientListPresenter ClientPresenter=new ClientListPresenter();
	     ClientListView ClientView= new ClientListView(ClientPresenter);
	     ClientPresenter.setView(ClientView);
	     ClientView.setMargin(true);
	     
	     //Statistique tab
	     StatMainPresenter stat_presenter= new StatMainPresenter();
	     StatMainView l4 = new StatMainView(stat_presenter);
	     
	     
	     t = new TabSheet();
	     t.setHeight("760px");
	     t.setWidth("100%");

	     t.addTab(l1, "Liste intervention", icon3);
	     t.addTab(AffectationView, "Liste affectation", icon1);
	     t.addTab(ClientView,"Liste Client",icon2);
	     t.addTab(l4,"Statistiques", icon4);
	     
	     t.addListener(this);
	     addComponent(t);	
	}

	@Override
	public void selectedTabChange(SelectedTabChangeEvent event) {
		// TODO Auto-generated method stub
		presenter.changeTab();
	}

	public ResponsableMainPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(ResponsableMainPresenter presenter) {
		this.presenter = presenter;
	}
}
