package com.igc.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.igc.shared.Data;
import com.igc.shared.Person;


@RemoteServiceRelativePath("crud")
public interface CrudService extends RemoteService {
	Person save(Person person);
	List<Person> finddAll();

}
