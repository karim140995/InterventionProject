package com.application.modules.responsable;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.application.model.dao.AffectationDao;
import com.application.model.dao.IntervenantDao;
import com.application.model.dao.InterventionDao;
import com.example.model.pojo.Affectation;
import com.example.model.pojo.Intervenant;
import com.example.model.pojo.Intervention;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.vaadin.terminal.UserError;

public class AffectationWindowPresenter implements AffectationWindowPresenterInterface {

	private AffectationWindowView view;
	private Integer id;
	private IntervenantDao model=new IntervenantDao();
	private AffectationDao model2=new AffectationDao();
	private InterventionDao model3=new InterventionDao();
	
	public AffectationWindowPresenter(Integer id_intervention) {
		// TODO Auto-generated constructor stub
		this.id=id_intervention;
		
	}
	
	public static ClientResponse SendSimpleMessage(String email,String name) {
		
		
		String api_key="key-07a5425c21ae03c1e1921b98caa7b10b"; //Private key of your mailgun account
		String domain="sandbox50487332f2944f368f52325a0cea6844.mailgun.org"; //Domain name of your mailgun account
		
	    Client client = Client.create();
	    client.addFilter(new HTTPBasicAuthFilter("api",api_key));
	    WebResource webResource = client.resource("https://api.mailgun.net/v3/"+domain+"/messages");
	    MultivaluedMapImpl formData = new MultivaluedMapImpl();
	    formData.add("from", "Portail gestion d'intervention <postmaster@"+domain+">");
	    formData.add("to",name+" <"+email+">");
	    formData.add("subject", "Nouvelle intervention");
	    formData.add("html", "Bonjour, <br><br> Vous avez recu une nouvelle demande d'intervention, veuillez vous connectez au portail de gestion d'intervention pour plus de détails.");
	    return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
	                                        post(ClientResponse.class, formData);


	}
	
	

	@Override
	public void load_intervenant() {
		// TODO Auto-generated method stub
		
		List<Intervenant>liste =model.ListeIntervenant();
		
		if((liste!=null)&&(!liste.isEmpty()))
		{	
			Iterator<Intervenant> iterator = liste.iterator();
			while(iterator.hasNext())
			{
				Intervenant intervenant=iterator.next();
				view.getIntervenant().addItem(intervenant.getIdintervenant());
				view.getIntervenant().setItemCaption(intervenant.getIdintervenant(),intervenant.getNom()+" "+intervenant.getPrenom());
			}
		}


	}

	@Override
	public void affecter() {
		// TODO Auto-generated method stub
		boolean check=check();
		if(check)
		{
			Affectation affectation= new Affectation();
			affectation.setDescription(view.getdescription().getValue().toString());
			affectation.setIntervention_idintervention(id);
			affectation.setIntervenant_idintervenant(Integer.parseInt(view.getIntervenant().getValue().toString()));
			//Update intervention
			
			Intervention intervention=new Intervention();
			intervention.setEtat("En cours");
			intervention.setIdintervention(id);
  		    InterventionListeView.intervention_liste.getContainerProperty(id, "Etat").setValue("En cours");
  		    model3.updateEtatIntervention(intervention);
			model2.addAffectation(affectation);
			
			
			//Sending email
			Integer id=Integer.parseInt(view.getIntervenant().getValue().toString());
			String name=view.getIntervenant().getItemCaption(view.getIntervenant().getValue());
			System.out.println(id.toString());
			List result=model.EmailIntervenant(id);
			String email=result.iterator().next().toString();
			System.out.println(email);
			System.out.println(name);
			SendSimpleMessage(email,name);
			
  		  //Close Window 
  		  (view.subwindow.getParent()).removeWindow(view.subwindow);
		}
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		boolean check=true;
		if(view.getdescription().getValue().toString().equals(""))
		{
			check=false;
			view.getdescription().setComponentError(new UserError("Veuillez choisir un intervenant") );			
		}
		if(view.getIntervenant().getValue()==null)
		{
			check=false;
			view.getIntervenant().setComponentError(new UserError("Veuillez choisir un intervenant") );
		}
		return check;
		
	}

	public AffectationWindowView getView() {
		return view;
	}

	public void setView(AffectationWindowView view) {
		this.view = view;
	}

}
