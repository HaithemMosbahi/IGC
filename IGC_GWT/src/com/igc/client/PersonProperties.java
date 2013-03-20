package com.igc.client;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.igc.shared.Person;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface PersonProperties extends PropertyAccess<Person> {
	
	 @Path("id")
	  ModelKeyProvider<Person> key();
	   
	 
	  ValueProvider<Person, String> fullName();
	  ValueProvider<Person, String> email();
	  ValueProvider<Person, String> university();
	  ValueProvider<Person, Date> registerDate();
	  ValueProvider<Person, String> gwtUser();
	  
	 // ValueProvider<Person, Date> occupation();	
	  
	
	   

}
