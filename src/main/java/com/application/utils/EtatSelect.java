package com.application.utils;

import java.util.Iterator;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;

public class EtatSelect extends VerticalLayout {


    private static final String[] priorities = new String[] { "Non affectée", "En cours",
            "Clôturée" };
    NativeSelect l ;
    public EtatSelect() {
        setSpacing(true);
        l = new NativeSelect("");
        Object obj=null;
        for (int i = 0; i < priorities.length; i++) {
            obj=l.addItem(i);
            l.setItemCaption(i,priorities[i]);
            

        }
        l.setNullSelectionAllowed(false);
        l.setValue(0);
        l.setHeight("25px");
        l.setImmediate(true);
        addComponent(l);        
    }

    public String value() {
    	return l.getItemCaption(l.getValue()).toString();
    }
    public void setvalue(Object obj)
    {
    	l.setValue(obj);
    	
    }
   
    public void selectValue(String caption)
    {
    	if(caption=="Non affectée")
    	{
    		l.select(0);
    	}
    	if(caption=="En cours")
    	{
    	   l.select(1);
    	}
    	if(caption=="Clôturée")
    	{
    		l.select(2);
    	}
    	
    	
    }
    
    
}