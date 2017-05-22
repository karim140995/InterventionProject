package com.application.modules.commercial;

import java.util.Iterator;
import java.util.List;

import com.application.model.dao.ClientDao;
import com.application.model.dao.LicenseDao;
import com.example.model.pojo.Client;
import com.example.model.pojo.License;

public class ClientListPresenter implements ClientListPresenterInterface {

	
	private ClientListView view;
	private final ClientDao model=new ClientDao();
	private final LicenseDao model_license=new LicenseDao();
	private List list_client;

	@Override
	public void load_LicenseListe(Object obj) {
		
		view.License_liste.removeAllItems();
		List<License> liste=null;
		if(obj!=null)
		{
			Integer num= Integer.parseInt(obj.toString());
			System.out.println(num);
			liste=model_license.ListeLicense(num);
			view.setLicense_list(liste);
			if(liste!=null)
			{
				for (License result : liste) {
					License license= new License();
					license=result;
					view.AddLicense(license);
				}
				
			}
			
		}
		
		
	}

	@Override
	public void load_ClientListe() {
		// TODO Auto-generated method stub
		
		List<Client> liste=null;
		
		//Removing all items	
		view.Client_liste.removeAllItems();
		
		if(view.getRech().getItemCaption(view.getRech().getValue())==null)
		{ liste= (List<Client>) model.ListeClient();}
		else {
			String nom_client=view.getRech().getItemCaption(view.getRech().getValue());
			liste= (List<Client>) model.ListeClient(nom_client); 
		}
		view.setClient_list(liste);
		
		if(liste!=null)
		{
			for (Client result : liste) {
				Client client= new Client();
				client=result;
				view.AddClient(client);
			}
			
		}
		
		
	}
	public void list_client() {
		// TODO Auto-generated method stub
		list_client= model.ListeClient();
		Iterator<Client> iterator = list_client.iterator();
		while(iterator.hasNext())
		{
			Client client=iterator.next();
			view.getRech().addItem(client.getIdclient());
			view.getRech().setItemCaption(client.getIdclient(),client.getNom_client());
		}	
		
	} 

	public ClientListView getView() {
		return view;
	}

	public void setView(ClientListView view) {
		this.view = view;
	}

	@Override
	public void Search() {
		// TODO Auto-generated method stub
		
	}

}
