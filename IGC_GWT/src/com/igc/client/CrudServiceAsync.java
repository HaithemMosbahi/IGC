package com.igc.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.igc.shared.Person;

public interface CrudServiceAsync {

	void finddAll(AsyncCallback<List<Person>> callback);

	void save(Person person, AsyncCallback<Person> callback);

}
