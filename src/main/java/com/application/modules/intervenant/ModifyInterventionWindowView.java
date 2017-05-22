package com.application.modules.intervenant;


import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.application.utils.DatePopup;
import com.application.utils.EtatSelect;
import com.application.utils.NativeSelection;
import com.application.utils.TimePicker;
import com.example.model.pojo.Client;
import com.example.model.pojo.Intervention;
public class ModifyInterventionWindowView  extends VerticalLayout {
	
	 public Window subwindow;
	 public DatePopup date_cloture;
	 public TimePicker heure_debut;
	 public TimePicker heure_fin;
	 
	 private Intervention intervention;
	 
	 private ModifyInterventionWindowPresenter presenter;
	 
	 ModifyInterventionWindowView(Intervention intervention)
		{
		 		
		 		this.setIntervention(intervention);
		 
			    subwindow = new Window("Cloturer une intervention");
			    subwindow.setModal(true);
			    subwindow.setStyleName("white");
			    // Configure the windws layout; by default a VerticalLayout
		        VerticalLayout layout = (VerticalLayout) subwindow.getContent();
		        layout.setMargin(true);
		        layout.setSpacing(true);
		        layout.setSizeUndefined();
			
		        //Add a new Intervention Form
				Form intervention_form=new Form();
				Resource CancelIcon= new ThemeResource("img/cancel.png");
				Resource AddIcon= new ThemeResource("img/ok.png");
				
				subwindow.addComponent(intervention_form);
			
			    date_cloture= new DatePopup();
			    date_cloture.setCaption("Date clôture :");
			    heure_debut= new TimePicker();
			    heure_debut.setCaption("Heure début :");
			    heure_fin= new TimePicker();
			    heure_fin.setCaption("Heure fin :");
				//Set Values
				//Date_ajout.setFieldString(intervention.getDate_ajout());
				HorizontalLayout buttons= new HorizontalLayout();
				buttons.setSpacing(true);
				buttons.setMargin(true);
				Button enregistrer=new Button("Cloturer");
				enregistrer.addListener(new Button.ClickListener() {
				    public void buttonClick(ClickEvent event) {
				    	presenter.save();
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
				layout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);
				
				
				intervention_form.getLayout().addComponent(date_cloture);
				intervention_form.getLayout().addComponent(heure_debut);
				intervention_form.getLayout().addComponent(heure_fin);
				
		}

	public ModifyInterventionWindowPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(ModifyInterventionWindowPresenter presenter) {
		this.presenter = presenter;
	}

	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}
	 
}
