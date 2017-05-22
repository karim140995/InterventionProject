package com.application.modules.intervenant;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.application.model.dao.ClientDao;
import com.application.model.dao.InterventionDao;
import com.application.model.dao.SecurityUtils;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import com.application.modules.commercial.TableActionsView;
import com.example.model.pojo.Client;
import com.example.model.pojo.Intervention;
import com.example.model.pojo.Users;
public class InterventionListePresenter implements InterventionListePresenterInterface {

	private InterventionListeView view;
	private InterventionDao model;
	private ClientDao client_model=new ClientDao();
	private List list_client;
	
	@Override
	public void load_table() {
		
				try {
			List<Object[]> liste=null;
			//Removing all items	
			InterventionListeView.intervention_liste.removeAllItems();
			//Loading new items
			if(view.getUsername()!=null)
			{
				Users user= new Users();
				user.setusername(view.getUsername());
				System.out.println("USER CALLED FROM INTERVENANT :"+view.getUsername());
				liste= (List<Object[]>) model.listeIntervention(user);
				if(liste!=null&&(!liste.isEmpty()))
				{	
				 for (Object[] result : liste) {
					 
					 //11 Item
					 //I.idintervention,I.objet,I.description,I.remarque,I.payement,I.priorité,I.etat,I.date_cloture,I.date_ajout,I.heuredebut_cloture,I.heurefin_cloture,C.nom_client FROM Intervention I, Client C    		 
					 Intervention intervention= new Intervention();
					 intervention.setIdintervention((Integer)result[0]);
					 intervention.setObjet((String)result[1]);
					 intervention.setDescription((String)result[2]);
					 intervention.setRemarque((String)result[3]);
					 intervention.setPayement((String)result[4]);
					 intervention.setPriorité((String)result[5]);
					 intervention.setEtat((String)result[6]);
					 intervention.setDate_cloture((Date)result[7]);
					 intervention.setDate_ajout((Date)result[8]);
					 intervention.setHeuredebut_cloture((String)result[9]);
					 intervention.setHeurefin_cloture((String)result[10]);
					 Client client= new Client();
					 client.setNom_client((String)result[11]);
					 intervention.setClient_idclient((Integer)result[12]);
					 view.Add_item(intervention,client);
				
					   
				    }
				} 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void list_client() {
		// TODO Auto-generated method stub
		list_client= client_model.ListeClient();
		Iterator<Client> iterator = list_client.iterator();
		while(iterator.hasNext())
		{
			Client client=iterator.next();
			view.getRech().addItem(client.getIdclient());
			view.getRech().setItemCaption(client.getIdclient(),client.getNom_client());
		}	
		
	} 
	@Override
	public void AfficherRapport() {
		
		
		String etat= view.intervention_liste.getContainerProperty(view.intervention_liste.getValue(),"Etat").getValue().toString();
		if(!etat.equals("Clôturée"))
		{
			
			view.ShowError();
			
		}
		else 
		{

			try {
					//Get the selected intervention num
					Integer id=Integer.parseInt(view.intervention_liste.getContainerProperty(view.intervention_liste.getValue(), "Num").toString());
					
					JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("C:/Users/DELL/jaspereports/Blank_A4.jasper");
					 Connection conn = getCon();
					 if(conn!=null)
					 {
			        // Parameters for report
			        Map<String, Object> parameters = new HashMap<String, Object>();
			        parameters.put("Num", id);
			        JasperPrint print = JasperFillManager.fillReport(jasperReport,
			                parameters, conn);
			 
			        // Make sure the output directory exists.
			        File outDir = new File("C:/jasperoutput");
			        outDir.mkdirs();
			 
			        // PDF Exportor.
			        JRPdfExporter exporter = new JRPdfExporter();
			 
			        ExporterInput exporterInput = new SimpleExporterInput(print);
			        // ExporterInput
			        exporter.setExporterInput(exporterInput);
			        
			        // ExporterOutput
			        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
			                "C:/jasperoutput/Intervention"+id.toString()+".pdf");
			        // Output
			        exporter.setExporterOutput(exporterOutput);
			 
			        //
			        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			        exporter.setConfiguration(configuration);
			        exporter.exportReport();
			        
			        //Display the created pdf file
			        String path="C:/jasperoutput/Intervention"+id.toString()+".pdf";
			        String filename=id.toString()+".pdf";
			        view.DisplayPdfReport(path, filename );
			        
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public Connection getCon() {
		
		Connection connection=null;
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
			   "jdbc:postgresql://localhost:5432/App_bd","postgres", "admin");
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return connection;
	}
	
	
	public InterventionListeView getView() {
		return view;
	}

	public void setView(InterventionListeView view) {
		this.view = view;
	}

	public InterventionDao getModel() {
		return model;
	}

	public void setModel(InterventionDao model) {
		this.model = model;
	}
}
