package com.igc.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dev.jjs.impl.GwtAstBuilder;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.LayoutData;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.ExpandMode;
import com.sencha.gxt.widget.core.client.container.MarginData;

public class LinkAccordionLayout implements IsWidget {

	
	private Anchor gwtTutorial=new Anchor("GWT sample app");
	private Anchor gwt_app=new Anchor("GWT and Google app engine");
	private Anchor gwt_demo=new Anchor("GWT showcase");
	private Anchor gxt=new Anchor("GXT");
	private Anchor smartGWT=new Anchor("Smart GWT");
	private Anchor Ext=new Anchor("GWT-EXT");
	private Anchor tools=new Anchor("Tools and librarie");
	private Anchor objectify=new Anchor("Objectify");
	
	 private ContentPanel panel;
	 
	  public Widget asWidget() {
		  gwtTutorial.setHref("https://developers.google.com/web-toolkit/doc/latest/tutorial/gettingstarted");
		  gwtTutorial.setTarget("_blank");
		  gwt_demo.setHref("http://gwt.googleusercontent.com/samples/Showcase/Showcase.html");
		  gwt_demo.setTarget("_blank");
		  gxt.setHref("http://www.sencha.com/examples/");
		  gxt.setTarget("_blank");
		  smartGWT.setHref("http://www.smartclient.com/smartgwt/showcase/#main");
		  smartGWT.setTarget("_blank");
		  Ext.setHref("http://www.gwt-ext.com/demo/");
		  Ext.setTarget("_blank");
		  gwt_app.setHref("https://developers.google.com/web-toolkit/doc/1.6/tutorial/appengine?hl=en");
		  gwt_app.setTarget("_blank");
		  tools.setHref("https://developers.google.com/web-toolkit/tools");
		  tools.setTarget("_blank");
		  objectify.setHref("https://code.google.com/p/objectify-appengine/");
		  objectify.setTarget("_blank");
	    if (panel == null) {
	      panel = new ContentPanel();
	      panel.setHeadingText("Useful Links");
	      panel.setBodyBorder(false);
	      panel.setPixelSize(150, 200);
	 
	 
	      AccordionLayoutContainer con = new AccordionLayoutContainer();
	      con.setExpandMode(ExpandMode.SINGLE_FILL);
	      panel.add(con);
	 
	      AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance> create(AccordionLayoutAppearance.class);
	 
	      ContentPanel cp = new ContentPanel(appearance);
	      cp.setAnimCollapse(false);
	      cp.setHeadingText("GWT Tutorials");
	      con.add(cp);
	      //con.setActiveWidget(cp);
	 
	      VerticalPanel vp = new VerticalPanel();
	     
	      vp.add(gwtTutorial);
	      
	      vp.add(gwt_demo);
	      vp.add(tools);
	      cp.add(vp);
	    
	      
	 
	  
	 
	      cp = new ContentPanel(appearance);
	      cp.setAnimCollapse(false);
	      cp.setHeadingText("GWT Libraries");
	      VerticalPanel vp2=new VerticalPanel();
	      vp2.add(gxt);
	      vp2.add(smartGWT);
	      vp2.add(Ext);
	      cp.add(vp2);
	      con.add(cp);
	      
	      cp = new ContentPanel(appearance);
	      cp.setAnimCollapse(false);
	      cp.setBodyStyleName("pad-text");
	      cp.setHeadingText("Google app engine");
	      VerticalPanel vp3=new VerticalPanel();
	      vp3.add(gwt_app);
	      vp3.add(objectify);
	      cp.add(vp3);
	      con.add(cp);
	 
	     
	 
	    }
	    return panel;
	  }
	 
	
	 
}
