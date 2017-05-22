package com.application.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.vaadin.addon.JFreeChartWrapper;

import com.application.model.dao.StatDao;
import com.vaadin.terminal.StreamResource.StreamSource;

public class PieChartSource implements StreamSource {
	
	private StatDao model=new StatDao();

	
	@Override
	public InputStream getStream() {
		// TODO Auto-generated method stub
	
			//Pie chart for the interventions
			
			try {
				System.out.println("GETSTREAM IS BEING USED");
				Integer nb_na=((Long) model.NonAffectéeIntervention().iterator().next()).intValue();
				Integer nb_c=((Long) model.CloturéIntervention().iterator().next()).intValue();
				Integer nb_ec=((Long) model.EncoursIntervention().iterator().next()).intValue();
				
				
				 DefaultPieDataset dataset = new DefaultPieDataset( );
				  dataset.setValue("En cours", nb_ec );
				  dataset.setValue("Non affectée", nb_na );

				  JFreeChart chart = ChartFactory.createPieChart(
				     "Etat des Interventions",   // chart title
				     dataset,          // data
				     true,             // include legend
				     true,
				     false);
				  
				  chart.setBackgroundPaint(null);
				  
				  int width = 640;   /* Width of the image */
				  int height = 480;  /* Height of the image */ 
				  File pieChart = new File( "C:/jchart/PieChart.jpeg" ); 
				  ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
				  

			        JFreeChartWrapper wrapper = new JFreeChartWrapper(chart, JFreeChartWrapper.RenderingMode.PNG);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
		
		
		
		
		
		return null;
	}

}
