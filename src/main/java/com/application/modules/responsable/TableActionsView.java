package com.application.modules.responsable;


import com.application.model.dao.InterventionDao;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window.Notification;

public class TableActionsView extends VerticalLayout {
	
	private final Button btn_modfier;
	private final Button btn_supprimer;
	private TableActionsPresenter presenter;
	
	
	public TableActionsView()
	{
		HorizontalLayout OptionsLayout= new HorizontalLayout();
		OptionsLayout.setWidth("100%");
		OptionsLayout.setSpacing(true);
		
		
		Resource CancelIcon= new ThemeResource("img/document-delete.png");
		Resource ModifyIcon= new ThemeResource("img/document-txt.png");
		
		 btn_modfier= new Button();
		 btn_supprimer= new Button();
		
		OptionsLayout.addComponent(btn_modfier);
		OptionsLayout.addComponent(btn_supprimer);
		btn_supprimer.setIcon(CancelIcon);
		btn_modfier.setIcon(ModifyIcon);
		btn_supprimer.setDescription("Refuser");
		btn_modfier.setDescription("Modifier");
		
		btn_modfier.addListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	presenter.showDescription();
		    }
	    });

		btn_supprimer.addListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	
		    	presenter.delete_row();
		    }
	    });
		
		this.addComponent(OptionsLayout);
	}
	
	public void ShowDescriptionWindow()
	{
	  ModifyInterventionWindowView FormWindow=new ModifyInterventionWindowView(presenter.getIntervention());
	  ModifyInterventionWindowPresenter presenter = new ModifyInterventionWindowPresenter();
	  InterventionDao model= new InterventionDao();
	  FormWindow.setPresenter(presenter);
	  presenter.setView(FormWindow);
	  presenter.setModel(model);
   	  getWindow().addWindow(FormWindow.subwindow);
		
	}
	

	public void ShowErrorMessage()
	{
		
		Notification notif = new Notification("Internal Server error :",
	            "Item cannot be deleted",
	            Notification.TYPE_ERROR_MESSAGE);
		notif.setPosition(Window.Notification.POSITION_CENTERED_TOP);
		getWindow().showNotification(notif);
	}

	public TableActionsPresenter getPresenter() {
		return presenter;
	}


	public void setPresenter(TableActionsPresenter presenter) {
		this.presenter = presenter;
	}
}
