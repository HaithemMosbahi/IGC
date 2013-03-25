package com.igc.server;

import java.util.ArrayList;
import java.util.List;


import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
import com.igc.client.CrudService;
import com.igc.shared.Data;
import com.igc.shared.Person;


import static com.googlecode.objectify.ObjectifyService.ofy;

@RemoteServiceRelativePath("crud")
public class CrudServiceImpl extends RemoteServiceServlet
                 implements CrudService
{
	
	private List<Person> allPerson;
	
	public List<Person> getAllPerson() {
		return allPerson;
	}

	public void setAllPerson(List<Person> allPerson) {
		this.allPerson = allPerson;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Person save(Person person) {
		ObjectifyService.register(Person.class);
		ofy().save().entity(person);
		
		return person;
	}

	@Override
	public List<Person> finddAll() {
		// TODO Auto-generated method stub
		ObjectifyService.register(Person.class);
		allPerson=new ArrayList<Person>();

		Iterable<Key<Person>> allKeys = ofy().load().type(Person.class).keys();
 
      for (Key<Person> key : allKeys) {
		allPerson.add(ofy().load().key(key).get());
	}
     System.out.println("fuckkkkkkkkkk "+allPerson.size());
     Query<Person> q = ofy().load().type(Person.class).filter("sex", "Homme");
     for (Person car: q) {
         System.out.println("fuckkkkkkkkkkkk "+car.getFirstName());
     }

		return allPerson;
	}


}
