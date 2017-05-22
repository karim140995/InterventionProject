package com.application.utils;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class NativeSelection extends VerticalLayout  {

    private static final String[] priorities = new String[] { "Faible", "Modérée",
            "Elévée" };
    NativeSelect l ;
    public NativeSelection() {
        setSpacing(true);
        l = new NativeSelect("");
        Object obj=null;
        for (int i = 0; i < priorities.length; i++) {
        	obj=l.addItem(i);
            l.setItemCaption(i,priorities[i]);
        }
        l.setNullSelectionAllowed(false);
        l.setValue(1);
        l.setImmediate(true);
        addComponent(l);
    }

    public String value() {
    	return l.getItemCaption(l.getValue()).toString();
    }
    public void setValue(Object obj)
    {
    	l.setValue(obj);
    	
    }
    
    public void selectValue(String caption)
    {
    	if(caption=="Faible")
    	{
    		l.select(0);
    	}
    	if(caption=="Modérée")
    	{
    	   l.select(1);
    	}
    	if(caption=="Elévée")
    	{
    		l.select(2);
    	}
    	
    	
    }
    
}