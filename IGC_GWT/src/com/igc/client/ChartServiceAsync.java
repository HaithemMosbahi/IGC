package com.igc.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.igc.shared.Data;

public interface ChartServiceAsync {

	void getCategSEx(AsyncCallback<List<Data>> callback);

	void getFavMusic(AsyncCallback<List<Data>> callback);

	void getFavProg(AsyncCallback<List<Data>> callback);

}
