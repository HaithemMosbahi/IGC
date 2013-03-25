package com.igc.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.igc.shared.Data;

@RemoteServiceRelativePath("get")
public interface ChartService extends RemoteService {
	
	List<Data> getCategSEx();
	List<Data> getFavMusic();
	List<Data> getFavProg();
	

}
