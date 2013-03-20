package com.igc.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.igc.shared.Person;

public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<Person> callback);

}
