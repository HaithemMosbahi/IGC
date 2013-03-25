package com.igc.client.chart;


import java.util.ArrayList;
import java.util.List;

import com.igc.shared.Data;



public class AllData {

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
