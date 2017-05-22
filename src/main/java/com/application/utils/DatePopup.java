package com.application.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DatePopup extends VerticalLayout implements
        Property.ValueChangeListener {

    private PopupDateField datetime;
    
 
    
    public DatePopup() {
        setSpacing(true);

        datetime = new PopupDateField("");
        datetime.setWidth("120px");
        // Set the value of the PopupDateField to current date
        datetime.setValue(new java.util.Date());

        // Set the correct resolution
        datetime.setResolution(PopupDateField.RESOLUTION_DAY);

        // Add valuechangelistener
        datetime.addListener(this);
        datetime.setImmediate(true);

        addComponent(datetime);
    }

    public Boolean check() {
        // Get the new value and format it to the current locale
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
        Object value = datetime.getValue();
        if (value == null || !(value instanceof java.util.Date)) {
            return false;
        } 
        return true;
    } 
    
    public String getFieldString()
    {
    	if(datetime.getValue()!=null)
    	{	
    	    java.util.Date utilDate = (java.util.Date) datetime.getValue();
    	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    	    return sqlDate.toString();

    	}
    	return null;
    }
    public Date getDate()
    {
    	java.util.Date utilDate = (java.util.Date) datetime.getValue();
  	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
  	    return sqlDate;
    }
    
    public void setFieldString(Date date)
    {
    	this.datetime.setValue(date);
    }
	@Override

	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
    
}