package com.application.modules.responsable;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.vaadin.addon.JFreeChartWrapper;

import com.application.model.dao.ClientDao;
import com.application.model.dao.IntervenantDao;
import com.application.model.dao.StatDao;
import com.example.model.pojo.Client;
import com.example.model.pojo.Intervenant;
import com.vaadin.ui.Alignment;

public class StatMainPresenter implements StatMainPresenterInterface {
	
	private StatDao model=new StatDao();
	private StatMainView view;
	private IntervenantDao model2=new IntervenantDao();
	private ClientDao model3=new ClientDao();
	
	public StatMainView getView() {
		return view;
	}
	public void setView(StatMainView view) {
		this.view = view;
	} 
	public JFreeChartWrapper intervention_stat()
	{
		JFreeChart chart;
		try {

			Integer nb_na=((Long) model.NonAffectéeIntervention().iterator().next()).intValue();
			Integer nb_c=((Long) model.CloturéIntervention().iterator().next()).intValue();
			Integer nb_ec=((Long) model.EncoursIntervention().iterator().next()).intValue();
			
			
			 DefaultPieDataset dataset = new DefaultPieDataset( );
			  dataset.setValue("En cours", nb_ec );
			  dataset.setValue("Non affectée", nb_na );
			  

			  chart = ChartFactory.createPieChart(
			     "",   // chart title
			     dataset,          // data
			     true,             // include legend
			     true,
			     false);
			  
			  chart.setBackgroundPaint(null);
			  //Change color 
			  PiePlot plot = (PiePlot) chart.getPlot();
			  plot.setSectionPaint("Non affectée", new Color(79, 148, 205));
			  
			  
			  int width = 640;   /* Width of the image */
			  int height = 480;  /* Height of the image */ 
			  File pieChart = new File( "C:/jchart/PieChart.jpeg" ); 
			  ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
			  JFreeChartWrapper wrapper = new JFreeChartWrapper(chart, JFreeChartWrapper.RenderingMode.PNG);
			  
			  return wrapper;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
		
	}
	public JFreeChartWrapper intervenant_stat()
	{
		try {
			JFreeChart chart;
			DefaultCategoryDataset dataset = 
				      new DefaultCategoryDataset( );  
			List<Object[]> liste=null;
			liste= (List<Object[]>) model.Topintervenant();
			
			if(liste!=null)
			{	
				 System.out.println("Liste not null");
				 for (Object[] result : liste) {
					 
					 dataset.addValue((Long)result[0], (String)result[1]+" "+(String)result[2], (String)result[1]+" "+(String)result[2]);   
	
				 }
			

			  JFreeChart barChart = ChartFactory.createBarChart(
				         "",           
				         "Nom de l'intervenant",            
				         "Intervention effectué",            
				         dataset,          
				         PlotOrientation.VERTICAL,           
				         false, true, false);

					  barChart.setBackgroundPaint(null);
					  
					  //Change color and remove gradiant
					  CategoryPlot plot = barChart.getCategoryPlot();
					  BarRenderer renderer = (BarRenderer) plot.getRenderer();
					  renderer.setBarPainter(new StandardBarPainter());
					  Color color = new Color(79, 148, 205);
					  renderer.setSeriesPaint(1, color);

					  
					  int width = 640;   /* Width of the image */
					  int height = 480;  /* Height of the image */ 
					  File barChart_file = new File( "C:/jchart/BarChart.jpeg" ); 
					  ChartUtilities.saveChartAsJPEG( barChart_file , barChart , width , height );
					  JFreeChartWrapper wrapper = new JFreeChartWrapper(barChart, JFreeChartWrapper.RenderingMode.PNG);
					  return wrapper;
					  
			}		  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			  

		return null;		
		
	}
	public void load_intervenant() {
		// TODO Auto-generated method stub
		
		List<Intervenant>liste =model2.ListeIntervenant();
		
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
	public void load_client() {
		// TODO Auto-generated method stub
		List<Client>list_client= model3.ListeClient();
		Iterator<Client> iterator = list_client.iterator();
		while(iterator.hasNext())
		{
			Client client=iterator.next();
			view.getClient().addItem(client.getIdclient());
			view.getClient().setItemCaption(client.getIdclient(),client.getNom_client());
		}	
		
	} 
	public JFreeChartWrapper Specific_intervenant_stat()
	{
	   JFreeChart chart;

		try {
			Integer id=Integer.parseInt(view.getIntervenant().getValue().toString());
			System.out.println(id);
			Integer nb_ec=((Long) model.EncoursIntervention(id).iterator().next()).intValue();
			Integer nb_c=((Long) model.CloturéIntervention(id).iterator().next()).intValue();
			
			 DefaultPieDataset dataset = new DefaultPieDataset( );
			  dataset.setValue("En cours ("+nb_ec+")", nb_ec );
			  dataset.setValue("Clôturée("+nb_c+")", nb_c );
			  

			  chart = ChartFactory.createPieChart(
			     "",   // chart title
			     dataset,          // data
			     true,             // include legend
			     true,
			     false);
			  
 
		      
			  //Change color 
			  PiePlot plot = (PiePlot) chart.getPlot();
			  plot.setSectionPaint("Clôturée("+nb_c+")", new Color(79, 148, 205));

			  
			  int width = 640;   /* Width of the image */
			  int height = 480;  /* Height of the image */ 
			  File pieChart = new File( "C:/jchart/PieChart.jpeg" ); 
			  ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
			  JFreeChartWrapper wrapper = new JFreeChartWrapper(chart, JFreeChartWrapper.RenderingMode.PNG);
			  
			  return wrapper;
			
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public JFreeChartWrapper Specific_client_stat()
	{
		
		JFreeChart chart;

		try {
			
			Integer id=Integer.parseInt(view.getClient().getValue().toString());
			Integer nb_c=((Long) model.ClientCloturéIntervention(id).iterator().next()).intValue();
			Integer nb_ec=((Long) model.ClientEncoursIntervention(id).iterator().next()).intValue();
			
			 DefaultPieDataset dataset = new DefaultPieDataset( );
			  dataset.setValue("En cours("+nb_ec+")", nb_ec );
			  dataset.setValue("Clôturée("+nb_c+")", nb_c );
			  

			  chart = ChartFactory.createPieChart(
			     "",   // chart title
			     dataset,          // data
			     true,             // include legend
			     true,
			     false);
			  

		      
			  //Change color 
			  PiePlot plot = (PiePlot) chart.getPlot();
			  plot.setSectionPaint("Clôturée("+nb_c+")", new Color(79, 148, 205));

			  
			  int width = 640;   /* Width of the image */
			  int height = 480;  /* Height of the image */ 
			  File pieChart = new File( "C:/jchart/PieChart.jpeg" ); 
			  ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
			  JFreeChartWrapper wrapper = new JFreeChartWrapper(chart, JFreeChartWrapper.RenderingMode.PNG);
			  
			  return wrapper;
					  
					  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			  

		return null;	
		
	}
	
	
}
