package com.application.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Select;

public class TimePicker extends HorizontalLayout {
	
	private  ComboBox heure;
	private  ComboBox minute;
	
	public void Setcaption(String caption)
	{
		heure.setCaption(caption);
		
	}

	public TimePicker()
	{
		heure= new ComboBox("");
		minute= new ComboBox ("");
		heure.setNullSelectionAllowed(false);
		minute.setNullSelectionAllowed(false);
		heure.setWidth("60px");
		minute.setWidth("60px");
		this.setSpacing(true);
		//Setting hour items
		for(int i=0; i<24;i++)
		{	
			String item="";
			if(Integer.toString(i).length()<2)
			{
			   item= '0'+ Integer.toString(i);
				
			}
			else 
			{
				item=Integer.toString(i);
			}	
			Object obj=heure.addItem(Integer.parseInt(item));
			heure.setItemCaption(Integer.parseInt(item), item);
		}
		//Setting minute and seconds items
		for(int i=0;i<60;i++)
		{
			String item="";
			if(Integer.toString(i).length()<2)
			{
			   item= '0'+ Integer.toString(i);
				
			}
			else 
			{
				item=Integer.toString(i);
			}	
			Object obj=minute.addItem(Integer.parseInt(item));
			minute.setItemCaption(Integer.parseInt(item), item);
			
		}
	  this.addComponent(heure);
	  this.addComponent(minute);		
	}
	
  public String getTime()
  {		
	    
	  if(heure.getValue()!=null&&(minute.getValue()!=null))
	  {
	    return heure.getItemCaption(heure.getValue()).toString()+":"+minute.getItemCaption(minute.getValue()).toString();
	  } 
	  return "";	  
		  
  }

public ComboBox getHeure() {
	return heure;
}

public void setHeure(ComboBox heure) {
	this.heure = heure;
}

public ComboBox getMinute() {
	return minute;
}

public void setMinute(ComboBox minute) {
	this.minute = minute;
}

}
