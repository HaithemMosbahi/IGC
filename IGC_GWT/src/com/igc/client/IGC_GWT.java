package com.igc.client;


import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d.TextBaseline;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Text;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.AttachDetachException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.HasVerticalAlignment.VerticalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.igc.client.canvas.CanvasIgc;
import com.igc.client.chart.BarChart;
import com.igc.client.chart.GwtPieChart;
import com.igc.client.chart.MusicBarChart;
import com.igc.client.chart.PieChartExample;
import com.igc.client.resource.Ressource;
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
import com.sencha.gxt.core.client.Style.HorizontalAlignment;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.Style.VerticalAlignment;
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
import com.sencha.gxt.widget.core.client.ContentPanel.ContentPanelAppearance;
import com.sencha.gxt.widget.core.client.FramedPanel.FramedPanelAppearance;
import com.sencha.gxt.widget.core.client.Resizable.Dir;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.box.ProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.SplitButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent.CheckChangeHandler;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent.ExpandHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
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
	private Button email=new Button("I don't have google account !");
	private Anchor signOutLink = new Anchor("Sign Out");
	private Label loginLabel = new Label("Please sign in to your Google Account to access the IGC_GWT application.");
	private Anchor fbLink=new Anchor();
	private Anchor googleLink=new Anchor();
	private Anchor youtubeCh=new Anchor();
	private String testEmail ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Anchor connectedUser = new Anchor();
	
	private Ressource resource = GWT.create(Ressource.class);
	private VerticalPanel vp=new VerticalPanel();
	
	private Ressource resourceImg=GWT.create(Ressource.class);
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
		
		
		Label Title=new Label("Google Web Toolkit Project");
		TextButton openApp=new TextButton("open demo application");
		  ToolTipConfig config = new ToolTipConfig();
		    config.setTitleHtml("Information");
		    config.setBodyHtml("Sign in using your Google Apps account");
		    config.setMouseOffset(new int[] {0, 0});
		    config.setAnchor(Side.LEFT);
		    openApp.setToolTipConfig(config);
		CanvasIgc canvasIgc=new CanvasIgc();
		
		vp.add(Title);
		vp.add(canvasIgc.asWidget());
		vp.add(openApp);
		
		RootPanel.get().add(vp);
		vp.addStyleName("centerPanel");
		Title.addStyleName("titleStyle");
		openApp.addStyleName("openStyle");
		openApp.setSize("40px", "25px");
		vp.setSpacing(10);
		vp.setHorizontalAlignment(HasAlignment.ALIGN_CENTER);
		
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
						    
						    dialogBox.setText("Erreur");

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
		/*					 vp.add(email);
							 final Label errorMsg=new Label();
							 errorMsg.addStyleName("serverResponseLabelError");
							 email.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									final DialogBox dialog=new DialogBox();
									dialog.setGlassEnabled(true);
									dialog.setAnimationEnabled(true);
									dialog.setTitle("Authentification");
									dialog.center();
									dialog.setSize("300","300");
									final Label emailLabel=new Label("type your email : ");
								    final TextField email=new TextField();
								    HorizontalPanel Emailpanel=new HorizontalPanel();
							
				
								    Emailpanel.add(email);
								    
								    HorizontalPanel Emailbtn=new HorizontalPanel();
								    VerticalPanel vp=new VerticalPanel();
								    Button btnOk=new Button("open application");
								    Button btnCancel=new Button("Cancel");
								    Emailbtn.add(btnOk);
								    Emailbtn.add(btnCancel);
								    vp.add(errorMsg);
								   vp.add(Emailpanel);
								   vp.add(Emailbtn);
								   dialog.add(vp);
								   dialog.show();
								   btnCancel.addClickHandler(new ClickHandler() {
									
									@Override
									public void onClick(ClickEvent event) {
										// TODO Auto-generated method stub
										dialog.hide();
									}
								});
								   btnOk.addClickHandler(new ClickHandler() {
									
									@Override
									public void onClick(ClickEvent event) {
										if(email.getText()=="")
										{
											errorMsg.setText("this field cannot be empty !");
										}
										else
											if(!email.getText().matches(testEmail))
											{
												errorMsg.setText("please type a valid email !");
											}
											else
											{
												dialog.hide();
												LoggedInInfo.setEmail(email.getText());
												RootPanel.get().clear();
												 RootPanel.get().clear();
											        Widget con = asWidget();
												    Viewport viewport = new Viewport();
												    viewport.add(con);
												    RootPanel.get().add(viewport);
											    Info.display("Welcome Message","Welcome "+LoggedInInfo.getEmail());
											}
										
									}
								});
								    
								}
							});
							 */
			
						}
						
					}
				});
			}
		});
	}
	
	
	//open a hyper;ink in new tab
	public static native String getURL(String url)/*-{
	return $wnd.open(url, 
	'target=_blank')
	}-*/;
	
	// app's princoial widget
	
	    public Widget asWidget() {
	    
	    	LinkAccordionLayout linkLayout=new LinkAccordionLayout();
	    	
	    	final ContentPanel center = new ContentPanel();
			ContentPanel north = new ContentPanel();
			ContentPanel west = new ContentPanel();
			ContentPanel east = new ContentPanel();
			ContentPanel south = new ContentPanel();
			Image imgFb=new Image(resource.fb());
			
			
			
			
			Image imgyb=new Image(resource.youtube());
			Image imgogle=new Image(resource.google());
			PushButton fbLink=new PushButton(imgFb);
			PushButton youtubeLink=new PushButton(imgyb);
			PushButton googleLink=new PushButton(imgogle);
			HorizontalPanel hpSouth=new HorizontalPanel();
			hpSouth.add(googleLink);
			hpSouth.add(fbLink);
			hpSouth.add(youtubeLink);
			hpSouth.add(fbLink);
			CenterLayoutContainer centerLayout=new CenterLayoutContainer();
			centerLayout.add(hpSouth);
			
			south.add(centerLayout);
		
			fbLink.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
				
					getURL("https://www.facebook.com/IGC.PageOfficielle?ref=ts&fref=ts");
					
				}
			});
			googleLink.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					getURL("https://plus.google.com/u/0/communities/116594688349889921594");
					
				}
			});
			youtubeLink.addClickHandler(new ClickHandler() {
	
	@Override
	public void onClick(ClickEvent event) {
		getURL("http://www.youtube.com/feed/UCa6qMsO2PQYYyLIvLnddc1w");
		
	}
});
			
			
			east.add(linkLayout);
			
			 // north panel content
			
		    Image imgLogo=new Image(resource.logo2());
		   imgLogo.addStyleName("headerStyle");
		    north.setHeaderVisible(false);
		    HorizontalPanel hor=new HorizontalPanel();
		    HorizontalPanel hp=new HorizontalPanel();
		   Label userAcc=new Label(LoggedInInfo.getEmail());
		 
		   HTML txt=new HTML("&nbsp;|&nbsp;");
		   
		   userAcc.addStyleName("userEmail");
		    
		    hp.add(userAcc);
		    hp.add(txt);
		    hp.add(signOutLink);
		    hp.addStyleName("authStyle");
		    HorizontalPanel horpanel=new HorizontalPanel();
		    horpanel.add(imgLogo);
		    horpanel.add(hp);
		
		    north.add(horpanel);

			ToolBar toolBar = new ToolBar();
			
			TextButton btnhome = new TextButton();
			toolBar.add(btnhome);
			btnhome.setIcon(resource.home());
			btnhome.setText("Home");
			toolBar.add(new SeparatorToolItem());
			TextButton btnadd = new TextButton();
			toolBar.add(btnadd);
			btnadd.setIcon(resource.logo());
			btnadd.setText("add user");

			
			
			toolBar.add(new SeparatorToolItem());
			
			TextButton btnall = new TextButton();
			toolBar.add(btnall);
			btnall.setIcon(resource.stat());
			btnall.setText("All users");
			
			toolBar.add(new SeparatorToolItem());

			
			TextButton btnstat = new TextButton();
			toolBar.add(btnstat);
			btnstat.setIcon(resource.stat());
			btnstat.setText("Statistics");
			toolBar.add(new SeparatorToolItem());

			TextButton item1 = new TextButton("Modify");

			Menu menu = new Menu();

			CheckMenuItem menuItem = new CheckMenuItem("hide east panel");

			menu.add(menuItem);

			CheckMenuItem menuItem2 = new CheckMenuItem("hide west panel");
			menu.add(menuItem2);
			
			CheckMenuItem menuItem3 = new CheckMenuItem("hide south panel");
			menu.add(menuItem3);
			
			item1.setMenu(menu);

		
			menu.add(new SeparatorMenuItem());

			

			MenuItem color = new MenuItem("change background");
			menu.add(color);

			final ColorMenu colorMenu = new ColorMenu();

			color.setSubMenu(colorMenu);

			toolBar.add(item1);

		
			final BorderLayoutContainer con = new BorderLayoutContainer();

			menuItem.addCheckChangeHandler(new CheckChangeHandler<CheckMenuItem>() {

				@Override
				public void onCheckChange(CheckChangeEvent<CheckMenuItem> event) {
					if (event.getItem().isChecked()) {
						con.collapse(LayoutRegion.EAST);
					} else {
						con.expand(LayoutRegion.EAST);
					}

				}
			});

			menuItem2
					.addCheckChangeHandler(new CheckChangeHandler<CheckMenuItem>() {

						@Override
						public void onCheckChange(
								CheckChangeEvent<CheckMenuItem> event) {
							if (event.getItem().isChecked()) {
								con.collapse(LayoutRegion.WEST);
							} else {
								con.expand(LayoutRegion.WEST);
							}

						}
					});
			
			menuItem3
			.addCheckChangeHandler(new CheckChangeHandler<CheckMenuItem>() {

				@Override
				public void onCheckChange(
						CheckChangeEvent<CheckMenuItem> event) {
					if (event.getItem().isChecked()) {
						con.collapse(LayoutRegion.SOUTH);
					} else {
						con.expand(LayoutRegion.SOUTH);
					}

				}
			});

			con.setBorders(true);




			center.setHeadingText("GWT Demo application");

		

			BorderLayoutData northData = new BorderLayoutData(100);
			northData.setMargins(new Margins(5));
			northData.setCollapsible(true);
			northData.setSplit(true);

			BorderLayoutData westData = new BorderLayoutData(150);
			westData.setCollapsible(true);
		
			westData.setCollapseMini(true);
			westData.setMargins(new Margins(0, 5, 0, 5));

			// center.add(toolBar);
			final VerticalLayoutContainer pa = new VerticalLayoutContainer();
			pa.setBorders(true);
			

			center.add(pa);
			toolBar.setLayoutData(new VerticalLayoutData(1, -1));
			final ContentPanel panelCenter=new ContentPanel();
			panelCenter.setLayoutData(new VerticalLayoutData(1, 1));
			pa.add(toolBar);
			pa.add(panelCenter);
			panelCenter.setHeaderVisible(false);
			MarginData centerData = new MarginData();

			BorderLayoutData eastData = new BorderLayoutData(150);
			//eastData.setMargins(new Margins(0, 5, 0, 5));
			eastData.setCollapsible(true);
		

			BorderLayoutData southData = new BorderLayoutData(100);
			southData.setMargins(new Margins(5));
			southData.setCollapsible(true);
			southData.setCollapseMini(true);

			con.setNorthWidget(north, northData);
			con.setWestWidget(west, westData);
			con.setCenterWidget(center, centerData);
			con.setEastWidget(east, eastData);
			con.setSouthWidget(south, southData);

			SimpleContainer simple = new SimpleContainer();
			simple.add(con, new MarginData(0, 20, 20, 0));

			
			panelCenter.getElement().getStyle().setBackgroundColor("white");
			colorMenu.getPalette().addValueChangeHandler(
					new ValueChangeHandler<String>() {

						@Override
						public void onValueChange(ValueChangeEvent<String> event) {
							String color = event.getValue();
						
							
							center.getElement().getStyle().setBackgroundColor("#" + color);
							//conCenter.getElement().getStyle().setBackgroundColor("#" + color);
							colorMenu.hide(true);
						}
					});
			
		    panelCenter.add(centerContent());
		    
		    // toolbar buttons ==> action handler
		   
			btnall.addSelectHandler(new SelectHandler() {
				
				@Override
				public void onSelect(SelectEvent event) {
					panelCenter.clear();
					center.setHeadingText("users Information");
					ListOfUsers allUsers=new ListOfUsers();
					panelCenter.add(allUsers.asWidget());
					
					
				}
			});
			
			btnhome.addSelectHandler(new SelectHandler() {
				
				@Override
				public void onSelect(SelectEvent event) {
					//panelCenter.clear();
					center.setHeadingText("Home");
					panelCenter.add(centerContent());
					pa.add(panelCenter);
					
				}
			});
			
			btnstat.addSelectHandler(new SelectHandler() {
				
				@Override
				public void onSelect(SelectEvent event) {
					// clear the center contentpanel
					panelCenter.clear();
				    center.setHeadingText("Statistics");
				    
				    //create tabs panel
				   

			    	 TabPanel advanced = new TabPanel();
			       
			         advanced.setAnimScroll(true);
			         advanced.setTabScroll(true);
			         advanced.setCloseContextMenu(true);
			         advanced.setCloseContextMenu(true);
				  
				  PieChartExample p=new PieChartExample();
				  BarChart ch1=new BarChart();
				  MusicBarChart ch=new MusicBarChart();
			    advanced.add(p.asWidget(), "Boys vs Girls");
			    advanced.add(ch1.asWidget(), "Programming language");
			    advanced.add(ch.asWidget(), "Genre of music");
				    panelCenter.add(advanced);
					
				}
			});
			
			btnadd.addSelectHandler(new SelectHandler() {
				
				@Override
				public void onSelect(SelectEvent event) {
					panelCenter.clear();
					PersonForm widgetForm=new PersonForm(LoggedInInfo);
			          
					panelCenter.add(widgetForm.asWidget(),new MarginData(1,100,100,1));
					center.setHeadingText("Save your personnel Information");
					
				}
			});
			
			Button buttonwest=new Button("About this application");
		   Button buttonwest2=new Button("GWT today");
		   buttonwest2.setWidth("140px");
			
			FlowPanel vpwest =new FlowPanel();
			vpwest.add(buttonwest);
			vpwest.add(buttonwest2);
			west.add(vpwest);
			
			buttonwest.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					panelCenter.add(west_Contpanel());
					
				}
			});
			
             buttonwest2.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					panelCenter.clear();
					GwtPieChart chart=new GwtPieChart();
					
					panelCenter.add(chart.asWidget());
					
				}
			});
			
			

			
			return simple;
	    	
	    
	    }
	    public CenterLayoutContainer centerContent()
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
	        	 
	        	    
	        	
	        	    Draggable d2 = new Draggable(cp2);
	        	    Draggable d3 = new Draggable(cp3);
	        	    Draggable d4= new Draggable(cp4);
	        	    VerticalPanel vp1=new VerticalPanel();
	        	    VerticalPanel vp2=new VerticalPanel();
	        	    vp1.add(cp3);
	        	    vp1.add(cp4);
	        	    vp2.add(cp);
	        	    vp2.add(cp2);
	        	  
	        	    vp1.setSpacing(50);
	        	    vp2.setSpacing(50);
	        	    
	        	    final CenterLayoutContainer conCenter = new CenterLayoutContainer();
		   		     Image imgCenter=new Image(resource.gwt());
		   		    conCenter.add(imgCenter);
	        	    
	   
	        	    
	        	  
	         
	        	    HorizontalPanel centerPanel=new HorizontalPanel();
	        	    centerPanel.add(vp1);
	        	    centerPanel.add(imgCenter);
	        	    centerPanel.add(vp2);
	        	    centerPanel.setSpacing(20);
	        	    conCenter.add(centerPanel);
	        
	    	return conCenter;
	    }
	    
	 
	    public HorizontalPanel west_Contpanel()
	    {
	    	
	    		final CenterLayoutContainer con=new CenterLayoutContainer();
	    		Button btn=new Button("Google web toolkit");
	    		
	    		Button gae=new Button("Google app engine");
	    		
	    		Button obj=new Button("Objectify");
	    		
	    		
	    		Button gxt=new Button("sencha GXT");
	    	
	    		
	    		final ContentPanel panel=new ContentPanel();
	    		panel.setPixelSize(200, 300);
	    		
	    	
	    		final Label text=new Label("Technologies used in this app: click in and i will show you some ifno");
	    		panel.add(text);
	    		
	    		VerticalPanel vp=new VerticalPanel();
	    		vp.add(gae);
	    		vp.add(obj);
	    		
	    		//Button b=new Button("<h2> GWT </h2>");
	    		
	    		 VerticalPanel vp2=new VerticalPanel();
	    			vp2.add(gxt);
	    			vp2.add(btn);
	    		
	    		

	    		btn.addMouseOverHandler(new MouseOverHandler() {
	    			
	    			@Override
	    			public void onMouseOver(MouseOverEvent event) {
	    				text.setText("Google Web Toolkit (GWT) is a development toolkit for building and optimizing complex browser-based applications");
	    				
	    				
	    			}
	    		});
	              gae.addMouseOverHandler(new MouseOverHandler() {
	    			
	    			@Override
	    			public void onMouseOver(MouseOverEvent event) {
	    				text.setText("Google App Engine (GAW) is a platform as a service (PaaS), cloud computing platform for developing and hosting web applications in Google-managed data centers");
	    				
	    				
	    			}
	    		});
	              
	obj.addMouseOverHandler(new MouseOverHandler() {
	    			
	    			@Override
	    			public void onMouseOver(MouseOverEvent event) {
	    				text.setText("Objectify is a Java data access API specifically designed for the Google App Engine datastore");
	    				
	    				
	    			}
	    		});
	
	gxt.addMouseOverHandler(new MouseOverHandler() {
		
		@Override
		public void onMouseOver(MouseOverEvent event) {
			text.setText("Sencha GXT is a Java library for building rich internet applications with Google Web Toolkit (GWT)");
			
			
		}
	});
	    		 
	    			
	    		
	    		HorizontalPanel hp=new HorizontalPanel();
	    		hp.add(vp);
	    		hp.add(panel);
	    		hp.add(vp2);
	    	
	    		
	    	  
	    		return hp;
	    	}
	    	
	    
		
	
}
