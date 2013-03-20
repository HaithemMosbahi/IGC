package com.igc.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.igc.shared.Person;


@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	public Person login(String requestUri);

}
