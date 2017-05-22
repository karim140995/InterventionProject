package com.application.modules.responsable;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window.Notification;

public class AffectationWindowView extends VerticalLayout {
	
	private AffectationWindowPresenter presenter;
	public Window subwindow;
	private TextArea description;
	
	public TextArea getdescription() {
		return description;
	}

	public void setdescription(TextArea description) {
		this.description = description;
	}

	private NativeSelect intervenant;
	
	public NativeSelect getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(NativeSelect intervenant) {
		this.intervenant = intervenant;
	}

	private Button comfirmer;
	private Button annuler;
	
	public AffectationWindowView(AffectationWindowPresenter presenter)
	{
		
		this.presenter=presenter;
		this.presenter.setView(this);
		
		subwindow = new Window("Affecter une intervention");
		subwindow.setModal(true);
		subwindow.setStyleName("white");
		VerticalLayout layout = (VerticalLayout) subwindow.getContent();
		layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeUndefined();
		//Icons
		Resource CancelIcon= new ThemeResource("img/cancel.png");
		Resource AddIcon= new ThemeResource("img/ok.png");
		
		
		//Form
		Form affectation_form=new Form();
		subwindow.addComponent(affectation_form);
		
		description=new TextArea("Travail demandé :");
		description.setHeight("150px");
		description.setWidth("300px");
	    intervenant= new NativeSelect();
		intervenant.setCaption("Nom intervenant :");
		
		HorizontalLayout buttons= new HorizontalLayout();
		buttons.setSpacing(true);
		buttons.setMargin(true);
		Button enregistrer=new Button("Envoyer");
		enregistrer.addListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	 presenter.affecter();
		    }  
		    });
		
		Button annuler=new Button("Annuler");
		annuler.addListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	(subwindow.getParent()).removeWindow(subwindow);
		    }  
		    });
		buttons.addComponent(enregistrer);
		buttons.addComponent(annuler);
		buttons.setComponentAlignment(enregistrer, Alignment.BOTTOM_RIGHT);
		buttons.setComponentAlignment(annuler, Alignment.BOTTOM_RIGHT);
		buttons.setWidth("50%");
		annuler.setIcon(CancelIcon);
		enregistrer.setIcon(AddIcon);
		layout.addComponent(buttons);
		layout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
		
		
		affectation_form.getLayout().addComponent(description);
		affectation_form.getLayout().addComponent(intervenant);

		presenter.load_intervenant();
		intervenant.setNullSelectionAllowed(false);
				
		
		
	}

	public AffectationWindowPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(AffectationWindowPresenter presenter) {
		this.presenter = presenter;
	}

}
