package com.igc.client;


import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d.TextBaseline;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.igc.client.canvas.CanvasIgc;
import com.igc.client.chart.BarChart;
import com.igc.client.chart.MusicBarChart;
import com.igc.client.chart.PieChartExample;
import com.igc.shared.Person;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.series.PieSeries;
import com.sencha.gxt.chart.client.chart.series.Series.LabelPosition;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelConfig;
import com.sencha.gxt.chart.client.draw.Gradient;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.Stop;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextAnchor;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.DateWrapper.Unit;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.Resizable.Dir;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.box.ProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.SplitButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent.ExpandHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.ColorMenu;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.tips.ToolTip;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IGC_GWT implements EntryPoint {
	
	
	private Person LoggedInInfo= null;
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	private Label loginLabel = new Label("Please sign in to your Google Account to access the IGC_GWT application.");
	private Anchor fbLink=new Anchor();
	private Anchor googleLink=new Anchor();
	private Anchor youtubeCh=new Anchor();
	private Anchor connectedUser = new Anchor();
	 private VerticalLayoutContainer con = new VerticalLayoutContainer();
     
	    private ToolBar toolBar = new ToolBar();
	
	// Service 
	final LoginServiceAsync loginService=GWT.create(LoginService.class);
	
	  
	@Override
	public void onModuleLoad() {
		 InitComponents();
	}
	
	public void InitComponents()
	{
		Label Title=new Label("Google Web Toolkit Demo Application");
		TextButton openApp=new TextButton("open demo application");
		  ToolTipConfig config = new ToolTipConfig();
		    config.setTitleHtml("Information");
		    config.setBodyHtml("Sign in using your Google Apps account");
		    config.setMouseOffset(new int[] {0, 0});
		    config.setAnchor(Side.LEFT);
		    openApp.setToolTipConfig(config);
		CanvasIgc canvasIgc=new CanvasIgc();
		final VerticalPanel vp=new VerticalPanel();
		vp.add(Title);
		vp.add(canvasIgc.asWidget());
		vp.add(openApp);
		RootPanel.get().add(vp);
		
		//Event handler when user clicks the openApp button
		
		openApp.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
			
				loginService.login(com.google.gwt.core.client.GWT.getHostPageBaseURL(), new AsyncCallback<Person>() {

					@Override
					public void onFailure(Throwable caught) {
						final DialogBox dialogBox = createDialogBox();
					    dialogBox.setGlassEnabled(true);
					    dialogBox.setAnimationEnabled(true);
						
					}
					  private DialogBox createDialogBox() {
						    // Create a dialog box and set the caption text
						    final DialogBox dialogBox = new DialogBox();
						    
						    dialogBox.setText("Erroooor");

						    // Create a table to layout the content
						    VerticalPanel dialogContents = new VerticalPanel();
						    dialogContents.setSpacing(4);
						    dialogBox.setWidget(dialogContents);

						    // Add some text to the top of the dialog
						   
						    Button closeButton = new Button("Close",new ClickHandler() {
						          public void onClick(ClickEvent event) {
						            dialogBox.hide();
						          }
						        });
						    dialogContents.add(closeButton);
						   
						    // Return the dialog box
						    return dialogBox;
						  }

					@Override
					public void onSuccess(Person result) {
						LoggedInInfo=result;
						signOutLink.setHref(LoggedInInfo.getLogoutUrl());
						if(LoggedInInfo.isLoggedIn())
						{
							RootPanel.get().clear();
							 final ProgressMessageBox box = new ProgressMessageBox("Please wait", "Loading items...");
						        box.setProgressText("Initializing...");
						        box.show();
						       ;
						 
						        final Timer t = new Timer() {
						          float i;
						          
						 
						          @Override
						          public void run() {
						            box.updateProgress(i / 100,  "{0}% Complete");
						            i += 20;
						            if (i > 100) {
						              cancel();
						              Info.display("Message", "Items were loaded");
						            }
						          }
						        };
						        t.scheduleRepeating(500);
						        Widget con = asWidget();
							    Viewport viewport = new Viewport();
							    viewport.add(con);
							    RootPanel.get().add(viewport);
							
						    Info.display("Welcome Message","Welcome "+LoggedInInfo.getNickname());
							  
						}
						else
						{
							 
							 signInLink.setHref(LoggedInInfo.getLoginUrl());
							 vp.add(loginLabel);
							 vp.add(signInLink);
						}
						
					}
				});
			}
		});
	}
	
	// app's princopal widget
	
	    public Widget asWidget() {
	    	
	    	
		  
	    final BorderLayoutContainer con = new BorderLayoutContainer();
	    con.setBorders(true);
	    
	    
	    
	    Label headerText=new Label("Insat Google Club");
	    headerText.addStyleName("headerStyle");
	    
	    Image img=new Image("igc.png");
	    img.addStyleName("imgStyle");
	   
	    ContentPanel north = new ContentPanel();
	    ContentPanel west = new ContentPanel();
	    final ContentPanel center = new ContentPanel();
	    north.setHeaderVisible(false);
	    north.addStyleName("northStyle");
	   
	    center.setHeadingText("Google Web ToolKit Demo Application"); 
	    center.setCollapsible(true);
	    VerticalLayoutContainer p = new VerticalLayoutContainer();
	    p.setBorders(true);
	    p.getElement().getStyle().setBackgroundColor("white");
	    toolBar.setLayoutData(new VerticalLayoutData(1, -1));
	    p.add(toolBar);
	    center.add(p);
	 
	    VerticalPanel vpk = new VerticalPanel();
	    vpk.setSpacing(10);
	    vpk.add(center);
	 
	     VerticalPanel vp=new VerticalPanel();
	     
	    
	     
	     TextButton btn1=new TextButton("Home sweet home");
	     TextButton btn=new TextButton("Create new account");
	     TextButton btn2=new TextButton("show all users");
	     TextButton btn3=new TextButton("Statistiques");
	     
	     vp.add(btn1);
	     vp.add(btn);
	     vp.add(btn2);
	     vp.add(btn3);
	     
	     west.add(vp);
	     center.add(centerContent());
	     
	     btn1.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				center.clear();
				center.setHeadingText("Home");
				center.add(centerContent());
			}
		});
	     
	     btn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				PersonForm widgetForm=new PersonForm(LoggedInInfo);
	          
				center.add(widgetForm.asWidget(),new MarginData(30, 50, 30, 50));
				center.setHeadingText("User Information");
				
			}
		});
	     
	     btn2.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
			center.clear();
			center.setHeadingText("All users");
			ListOfUsers allUsers=new ListOfUsers();
			center.add(allUsers.asWidget());
				
			}
		});
	     
	     btn3.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				// clear the center contentpanel
			    center.clear();
			    
			    //create tabs panel
			   
			  
			  
			   // BarChart ch=new BarChart();
			  MusicBarChart ch=new MusicBarChart();
		
			    center.add(ch.asWidget());
				
			}
		});
	 
	    center.setResize(false);
	 
	    ContentPanel east = new ContentPanel();
	    ContentPanel south = new ContentPanel();
	    south.setHeaderVisible(false);
	   
	    HorizontalPanel hor=new HorizontalPanel();
	    DecoratorPanel decPanel=new DecoratorPanel();
	    HorizontalPanel hp=new HorizontalPanel();
	    connectedUser.setText("@"+LoggedInInfo.getEmail());
	    FieldLabel userAccount=new FieldLabel(null,"@"+LoggedInInfo.getEmail().substring(0,LoggedInInfo.getEmail().indexOf("@") ));
	    
	    ToolTipConfig config = new ToolTipConfig();
	    config.setTitleHtml("Information");
	    config.setBodyHtml("you are connected to this app by your google account");
	    config.setMouseOffset(new int[] {0, 0});
	    config.setAnchor(Side.BOTTOM);
	  userAccount.setToolTipConfig(config);
	   
	    
	    hp.add(userAccount);
	    hp.add(signOutLink);
	   hp.setSpacing(10);
	    hor.add(headerText);
	    hor.add(img);
	    hor.add(hp);
	    
	    north.add(hor);
	    ToolBar toolbar=new ToolBar();
	    Button b=new Button("new account");
	    toolbar.add(b);
	    north.addTool(toolbar);
	   
	   
	 
	    
	    BorderLayoutData northData = new BorderLayoutData(120);
	    northData.setMargins(new Margins(0));
	    
	    //northData.setCollapsible(true);
	    //northData.setSplit(true);
	 
	    BorderLayoutData westData = new BorderLayoutData(150);
	    westData.setCollapsible(true);
	    westData.setSplit(true);
	    westData.setCollapseMini(false);
	    westData.setMargins(new Margins(0, 5, 0, 5));
	 
	 
	    MarginData centerData = new MarginData();
	    

	    	 
	    	    
	    	    
	    BorderLayoutData eastData = new BorderLayoutData(150);
	    eastData.setMargins(new Margins(0, 5, 0, 5));
	    eastData.setCollapsible(true);
	    eastData.setSplit(true);
	 
	    BorderLayoutData southData = new BorderLayoutData(100);
	    southData.setMargins(new Margins(5));
	    southData.setCollapsible(true);
	    southData.setCollapseMini(true);
	 
	    con.setNorthWidget(north, northData);
	    con.setWestWidget(west, westData);
	    con.setCenterWidget(vpk, centerData);
	    con.setEastWidget(east, eastData);
	    con.setSouthWidget(south, southData);
	 
	    SimpleContainer simple = new SimpleContainer();
	    simple.add(con,new MarginData(5, 30, 30, 5));
	 
	    return simple;
	  }

	
	    public VerticalPanel centerContent()
	    {
	    	ContentPanel con=new ContentPanel();
	        ContentPanel cp = new ContentPanel();
	        cp.setCollapsible(true);
	        cp.setBodyStyleName("pad-text");
	        cp.setHeadingText("Advice");
	        cp.add(new Label("If you haven't played with GWT in a while, now is the time to start paying attention"));
	        cp.setWidth(200);
	        
	        ToolTipConfig config = new ToolTipConfig();
		    config.setTitleHtml("Information");
		    config.setBodyHtml("hey you can drag me :)");
		    config.setMouseOffset(new int[] {0, 0});
		    config.setAnchor(Side.TOP);
		  cp.setToolTipConfig(config);
	     
	        Draggable d = new Draggable(cp);
	       // center.add(cp,new MarginData(30, 50, 30, 50));
	     
	        
	      
	         ContentPanel cp2 = new ContentPanel();
	        	    cp2.setCollapsible(true);
	        	    cp2.setBodyStyleName("pad-text");
	        	    cp2.setHeadingText(" GWT ?");
	        	    cp2.add(new Label("You are a java developper, you hate javascript and you  want to build complex web apps  : GWT can do that for you :D"));
	        	    cp2.setWidth(300);
	        	    
	        	    ContentPanel cp3 = new ContentPanel();
	        	    cp3.setCollapsible(true);
	        	    cp3.setBodyStyleName("pad-text");
	        	    cp3.setHeadingText(" GWT ?");
	        	    cp3.add(new Label("You are a java developper, you hate javascript and you  want to build complex web apps  : GWT can do that for you :D"));
	        	    cp3.setWidth(300);
	        	    
	        	    ContentPanel cp4 = new ContentPanel();
	        	    cp4.setCollapsible(true);
	        	    cp4.setBodyStyleName("pad-text");
	        	    cp4.addStyleName("pad-text");
	        	    cp4.setHeadingText("About this demo");
	        	    cp4.add(new Label("you hate javascript and you  want to build complex web apps  : GWT can do that for you :D"));
	        	    cp4.setWidth(300);
	        	 
	        	    // add image in the center
	        	    Image img2=new Image("gwtapp.jpg");
	        	
	        	    Draggable d2 = new Draggable(cp2);
	        	    Draggable d3 = new Draggable(cp3);
	        	    Draggable d4= new Draggable(cp4);
	        	    HorizontalPanel hppp=new HorizontalPanel();
	        	    HorizontalPanel hp3=new HorizontalPanel();
	        	    hp3.add(cp3);
	        	    hp3.add(cp4);
	        	    hppp.add(cp);
	        	    hppp.add(img2);
	        	    hppp.add(cp2);
	        	    
	        	    VerticalPanel centerPanel=new VerticalPanel();
	        	    centerPanel.add(hppp);
	        	    centerPanel.add(hp3);
	        
	    	return centerPanel;
	    }
	    	
	
	
	public ToolBar toolBar()
	{
		ToolBar toolBar=new ToolBar();
    	
    	Menu menu = new Menu();
       
        CheckMenuItem menuItem = new CheckMenuItem("I Like Cats");
        menuItem.setChecked(true);
        menu.add(menuItem);
     
   
        menu.add(new SeparatorMenuItem());
     
        MenuItem radios = new MenuItem("Radio Options");
        menu.add(radios);
     
        Menu radioMenu = new Menu();
       
        CheckMenuItem r = new CheckMenuItem("Blue Theme");
        r.setGroup("radios");
        r.setChecked(true);
        radioMenu.add(r);
     
        r = new CheckMenuItem("Gray Theme");
        r.setGroup("radios");
        radioMenu.add(r);
        radios.setSubMenu(radioMenu);
     
        MenuItem date = new MenuItem("Choose a Date");
        menu.add(date);
        
  
   
      MenuItem color = new MenuItem("Choose a Color");
      menu.add(color);
   
      final ColorMenu colorMenu = new ColorMenu();
      colorMenu.getPalette().addValueChangeHandler(new ValueChangeHandler<String>() {
   
        @Override
        public void onValueChange(ValueChangeEvent<String> event) {
          String color = event.getValue();
          Info.display("Color Changed", "You selected " + color);
          colorMenu.hide(true);
        }
      });
   
      color.setSubMenu(colorMenu);
   
   
   
      toolBar.add(new SeparatorToolItem());
   
      SplitButton splitItem = new SplitButton("Split Button");
    
   
      menu = new Menu();
   
      MenuItem item = new MenuItem();
      item.setHTML("<b>Bold</b>");
      menu.add(item);
   
      item = new MenuItem();
      item.setHTML("<i>Italic</i>");
      menu.add(item);
   
      item = new MenuItem();
      item.setHTML("<u>Underline</u>");
      menu.add(item);
   
      splitItem.setMenu(menu);
   
      toolBar.add(splitItem);
   
      toolBar.add(new SeparatorToolItem());
   
      ToggleButton toggle = new ToggleButton("Toggle");
      toggle.setValue(true);
      toolBar.add(toggle);
   
      toolBar.add(new SeparatorToolItem());
   
      TextButton scrollerButton = new TextButton("Scrolling Menu");
   
      Menu scrollMenu = new Menu();
   
      scrollMenu.setMaxHeight(200);
      for (int i = 0; i < 40; i++) {
        scrollMenu.add(new MenuItem("Item " + i));
      }
   
      scrollerButton.setMenu(scrollMenu);
   
      toolBar.add(scrollerButton);
   
      toolBar.add(new SeparatorToolItem());
      toolBar.add(new FillToolItem());
     return toolBar;
     
	}
	
	
	
	
	

	
}
