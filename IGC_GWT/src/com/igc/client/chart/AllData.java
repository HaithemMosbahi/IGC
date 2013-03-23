package com.igc.client.chart;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.igc.client.CrudService;
import com.igc.client.CrudServiceAsync;
import com.igc.shared.Person;
import com.sencha.gxt.widget.core.client.form.CheckBox;

public class AllData {
	
	// call the asynchronous methode
	CrudServiceAsync crudService=GWT.create(CrudService.class);
	
 	
 	private List<Person> allPerson=new ArrayList<Person>();
	
	// if connection to the database failed the app will use list 
	public static final List<Data> allProg(){
		List<Data> data=new ArrayList<Data>();
		data.add(new Data("java", 200));
		data.add(new Data("ruby", 30));
		data.add(new Data("python", 90));
		data.add(new Data("sacala", 20));
		data.add(new Data("c#", 115));
		data.add(new Data("PHP", 100));
		return data;
	}
	
	public static final List<Data> sex(){
		List<Data> data=new ArrayList<Data>();
		data.add(new Data("Boys", 60));
		data.add(new Data("Girls", 40));
		return data;
	}
	public static final List<Data> allMusic(){
		List<Data> data=new ArrayList<Data>();
		data.add(new Data("Rock", 100));
		data.add(new Data("Classical", 60));
		data.add(new Data("Reggae", 30));
		data.add(new Data("Electronic", 10));
		data.add(new Data("Latin", 20));
		data.add(new Data("R&B", 30));
		data.add(new Data("Oriental", 70));
		data.add(new Data("Metal", 60));
		data.add(new Data("Pop", 90));
		data.add(new Data("Blues", 30));
		data.add(new Data("Rap", 40));
		data.add(new Data("Jazz", 10));
		return data;
	}
	
	

	public List<Data> getallMusic()
	{
		final List<Data> allMusic=new ArrayList<Data>();
		allMusic.add(new Data("Rock",0));
		allMusic.add(new Data("Classical",0));
		allMusic.add(new Data("Reggae",0));
		allMusic.add(new Data("Electronic",0));
		allMusic.add(new Data("Latin",0));
		allMusic.add(new Data("R&B",0));
		allMusic.add(new Data("Oriental",0));
		allMusic.add(new Data("Metal",0));
		allMusic.add(new Data("Pop",0));
		allMusic.add(new Data("Blues",0));
		allMusic.add(new Data("Rap",0));
		allMusic.add(new Data("Jazz",0));
		
		crudService.finddAll(new AsyncCallback<List<Person>>() {
			
			
			@Override
			public void onSuccess(List<Person> result) {
				          
				if(result != null)
				{
					for (Person person : result) {
						if(person.getFavMusic()!=null)
						{ List<String> music=person.getFavMusic();
						for (String genre : music) {
							int i=isExist(genre, allMusic); 
							allMusic.get(i).setData1((allMusic.get(i).getData1())+1);
						}
						}
					}
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// if connection to dataStore failed return static list
				//List<Data> allMusic=AllData.allMusic();
			
				
			}
		});
		return allMusic;
	}  
	
	
	public List<Data> getSex()
	{
		final List<Data> Sex=new ArrayList<Data>();
		Sex.add(new Data("Boys",0));
		Sex.add(new Data("Girls",0));
		crudService.finddAll(new AsyncCallback<List<Person>>() {

			@Override
			public void onFailure(Throwable caught) {
				// if connection to dataStore failed return static list
				//List<Data> Sex=AllData.sex();
			}

			@Override
			public void onSuccess(List<Person> result) {
				if(result != null)
				{
					for (Person person : result) {
						if(person.getSex()!=null)
						{
							 if(person.getSex().equals("Homme"))
							 {
								 Sex.get(0).setData1(Sex.get(0).getData1()+1);
							 }
							 if(person.getSex().equals("Femme"))
							 {
								 Sex.get(1).setData1((Sex.get(1). getData1())+1);
							 }     
							
						}
						
						
					}
				}
				else
				{
					List<Data> Sex=AllData.sex();
				}
				
				
		     
				
			}
		});
		return Sex;
	}
	
	public List<Data> getProgLang()
	{
		final List<Data> allProgLang=new ArrayList<Data>();
		allProgLang.add(new Data("Java",0));
		allProgLang.add(new Data("Ruby",0));
		allProgLang.add(new Data("Python",0));
		allProgLang.add(new Data("Scala",0));
		allProgLang.add(new Data("Groovy",0));
		allProgLang.add(new Data("PHP",0));
		allProgLang.add(new Data("C#",0));
		allProgLang.add(new Data("C/C++",0));
		allProgLang.add(new Data("JavaScript",0));
		allProgLang.add(new Data("Perl",0));
		allProgLang.add(new Data("Objective-C",0));
		allProgLang.add(new Data("other",0));
		
		crudService.finddAll(new AsyncCallback<List<Person>>() {

			@Override
			public void onFailure(Throwable caught) {
				
				// if connection to dataStore failed return static list
				
				//List<Data> allProgLang=AllData.allProg();
				
			}

			@Override
			public void onSuccess(List<Person> result) {
				if(result != null)
				{
					for (Person person : result) {
						if(person.getFavProgLang()!=null)
						{ List<String> prog=person.getFavProgLang();
						for (String genre : prog) {
							int i=isExist(genre,allProgLang); 
							allProgLang.get(i).setData1((allProgLang.get(i).getData1())+1);
						}
						}
					}
				}
				/*else
				{
					List<Data> allProgLang=AllData.allProg();
				}*/
				
				
			}
		});
		return allProgLang;
	}
	
	
	public int isExist(String genre,List<Data> alldata)
	{
		for (int i=0;i<alldata.size();i++) {
			if(alldata.get(i).getName().equals(genre))
				return i;
			}
		return -1;
	}
	
	public static final List<Data> getGWTlist()
	{
		List<Data> gwtList=new ArrayList<Data>();
		gwtList.add(new Data("Business application",80));
		gwtList.add(new Data("Content-rich Website",12));
		gwtList.add(new Data("Games",1));
		gwtList.add(new Data("Portlet",1));
		gwtList.add(new Data("Other",7));
		
		return gwtList;
	}
		
	

}
