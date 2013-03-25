package com.igc.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.igc.client.ChartService;
import com.igc.shared.Data;
import com.igc.shared.Person;

import static com.googlecode.objectify.ObjectifyService.ofy;

@RemoteServiceRelativePath("get")
public class ChartServiceImpl extends RemoteServiceServlet implements ChartService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private List<Data> allSexCateg;
    private List<Data> allMusic;
    private List<Data> allProgLang;
	
	
	

	public List<Data> getAllSexCateg() {
		return allSexCateg;
	}

	public void setAllSexCateg(List<Data> allSexCateg) {
		this.allSexCateg = allSexCateg;
	}

	@Override
	public List<Data> getCategSEx() {
		ObjectifyService.register(Person.class);
		List<Person> all=new ArrayList<Person>();
		allSexCateg=new ArrayList<Data>();
		  

		Iterable<Key<Person>> allKeys = ofy().load().type(Person.class).keys();
 
      for (Key<Person> key : allKeys) {
		all.add(ofy().load().key(key).get());
	    }
   
      allSexCateg.add(new Data("Boys",0));
      allSexCateg.add(new Data("Girls",0));
			for (Person person : all) {
				if(person.getSex()!=null)
				{
					 if(person.getSex().equals("Homme"))
					 {
						 allSexCateg.get(0).setData1((allSexCateg.get(0).getData1())+1);
					 }
					 if(person.getSex().equals("Femme"))
					 {
						 allSexCateg.get(1).setData1((allSexCateg.get(1). getData1())+1);
					 }     
					
				}
				
			
		}
		 
		return allSexCateg;
	}

	@Override
	public List<Data> getFavMusic() {
		allMusic=new ArrayList<Data>();
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
		
		ObjectifyService.register(Person.class);
		List<Person> all=new ArrayList<Person>();
	
		  

		Iterable<Key<Person>> allKeys = ofy().load().type(Person.class).keys();
 
      for (Key<Person> key : allKeys) {
		all.add(ofy().load().key(key).get());
	    }
      
		if(all != null)
		{
			for (Person person : all) {
				if(person.getFavMusic()!=null)
				{ List<String> music=person.getFavMusic();
				for (String genre : music) {
					int i=isExist(genre, allMusic); 
					allMusic.get(i).setData1((allMusic.get(i).getData1())+1);
					
				}
				}
			}
		}
		return allMusic;
	}

	@Override
	public List<Data> getFavProg() {
		allProgLang=new ArrayList<Data>();
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
		ObjectifyService.register(Person.class);
		List<Person> all=new ArrayList<Person>();
	
		  

		Iterable<Key<Person>> allKeys = ofy().load().type(Person.class).keys();
 
      for (Key<Person> key : allKeys) {
		all.add(ofy().load().key(key).get());
	    }
  	if(all != null)
	{
		for (Person person : all) {
			if(person.getFavProgLang()!=null)
			{ List<String> prog=person.getFavProgLang();
			for (String genre : prog) {
				int i=isExist(genre,allProgLang); 
				allProgLang.get(i).setData1((allProgLang.get(i).getData1())+1);
			}
			}
		}
	}
      
		return allProgLang;
	}
	
	private int isExist(String genre,List<Data> alldata)
	{
		for (int i=0;i<alldata.size();i++) {
			if(alldata.get(i).getName().equals(genre))
				return i;
			}
		return -1;
	}

}
