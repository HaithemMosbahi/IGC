package com.igc.server;

import java.util.logging.Logger;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.igc.client.LoginService;
import com.igc.shared.Person;

public class LoginServiceImpl extends RemoteServiceServlet implements 
                       LoginService
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3962669142010512638L;
    Logger logger;
	
	@Override
	public Person login(String requestUri) {
		UserService userService=UserServiceFactory.getUserService();
		User user=userService.getCurrentUser();
		Person person=new Person();
		if(user != null)
		{
			
			person.setLoggedIn(true);
			person.setEmail(user.getEmail());
			person.setNickname(user.getNickname());
			person.setLogoutUrl(userService.createLogoutURL(requestUri));
		}
		else
		{
			person.setLoggedIn(false);
			person.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return person;
	}

}
