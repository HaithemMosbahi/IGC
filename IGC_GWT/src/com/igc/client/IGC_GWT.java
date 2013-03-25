package com.igc.client;

import java.util.List;

import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragEndEvent;
import com.google.gwt.event.dom.client.DragEndHandler;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.igc.client.canvas.CanvasIgc;
import com.igc.client.chart.GwtPieChart;
import com.igc.client.resource.Ressource;
import com.igc.shared.Data;
import com.igc.shared.Person;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.event.SeriesItemOverEvent;
import com.sencha.gxt.chart.client.chart.event.SeriesSelectionEvent;
import com.sencha.gxt.chart.client.chart.event.SeriesItemOverEvent.SeriesItemOverHandler;
import com.sencha.gxt.chart.client.chart.event.SeriesSelectionEvent.SeriesSelectionHandler;
import com.sencha.gxt.chart.client.chart.series.BarSeries;
import com.sencha.gxt.chart.client.chart.series.PieSeries;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelConfig;
import com.sencha.gxt.chart.client.chart.series.SeriesRenderer;
import com.sencha.gxt.chart.client.chart.series.SeriesToolTipConfig;
import com.sencha.gxt.chart.client.chart.series.Series.LabelPosition;
import com.sencha.gxt.chart.client.draw.Gradient;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.Stop;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.widget.core.client.Resizable.Dir;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
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
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.ColorMenu;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IGC_GWT implements EntryPoint {

	private static final DataPropertyAccess dataAccess = GWT
			.create(DataPropertyAccess.class);
	private ChartServiceAsync get = GWT.create(ChartService.class);

	private Person LoggedInInfo = null;
	private Anchor signInLink = new Anchor("Sign In");
	private Button email = new Button("I don't have google account !");
	private Anchor signOutLink = new Anchor("Sign Out");
	private Anchor fbLink = new Anchor();
	private Anchor googleLink = new Anchor();
	private Anchor youtubeCh = new Anchor();
	private String testEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Anchor connectedUser = new Anchor();

	private Ressource resource = GWT.create(Ressource.class);
	private VerticalPanel vp = new VerticalPanel();
	private VerticalPanel vp22 = new VerticalPanel();

	private Ressource resourceImg = GWT.create(Ressource.class);
	private VerticalLayoutContainer con = new VerticalLayoutContainer();

	private ToolBar toolBar = new ToolBar();

	// Service
	final LoginServiceAsync loginService = GWT.create(LoginService.class);

	@Override
	public void onModuleLoad() {
		InitComponents();
	}

	public void InitComponents() {

		final HTML loginLabel = new HTML();
		Label Title = new Label("Google Web Toolkit Project");
		final Button openApp = new Button("Open GWT application");
		

		// openApp.addStyleDependentName("buttonStyle");
		openApp.getElement().setId("openbutton");
		CanvasIgc canvasIgc = new CanvasIgc();

		final VerticalPanel vp2 = new VerticalPanel();
		vp2.add(openApp);

		vp22.addStyleName("centerPanel");
		vp.add(Title);
		vp.add(canvasIgc.asWidget());
		vp2.add(openApp);
		vp22.add(vp);
		vp22.add(vp2);
		RootPanel.get().add(vp22);
		Title.addStyleName("titleStyle");
		vp2.addStyleName("openStyle");
		vp.setSpacing(10);
     
		// Event handler when user clicks the openApp button

		openApp.addClickHandler(new ClickHandler() {
			
			

			@Override
			public void onClick(ClickEvent event) 
			{
				loginService.login(
						com.google.gwt.core.client.GWT.getHostPageBaseURL(),
						new AsyncCallback<Person>() {

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

								Button closeButton = new Button("Close",
										new ClickHandler() {
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
								LoggedInInfo = result;
								signOutLink.setHref(LoggedInInfo.getLogoutUrl());
								if (LoggedInInfo.isLoggedIn()) {
									RootPanel.get().clear();
									Widget con = asWidget();
									Viewport viewport = new Viewport();
									viewport.add(con);
									RootPanel.get().add(viewport);

									Info.display("Welcome Message", "Welcome "
											+ LoggedInInfo.getNickname());

								} else {

									signInLink.setHref(LoggedInInfo
											.getLoginUrl());
									loginLabel
											.setHTML("<center><b>Please "
													+ signInLink
													+ " to your Google Account to access the IGC_GWT application <br/> (then click the button above once again)</b></center>");
									RootPanel.get().add(loginLabel);
									/*
									 * I dont have google account
									 *  vp.add(email);
									 * final Label errorMsg=new Label();
									 * errorMsg
									 * .addStyleName("serverResponseLabelError"
									 * ); email.addClickHandler(new
									 * ClickHandler() {
									 * 
									 * @Override public void onClick(ClickEvent
									 * event) { final DialogBox dialog=new
									 * DialogBox();
									 * dialog.setGlassEnabled(true);
									 * dialog.setAnimationEnabled(true);
									 * dialog.setTitle("Authentification");
									 * dialog.center();
									 * dialog.setSize("300","300"); final Label
									 * emailLabel=new
									 * Label("type your email : "); final
									 * TextField email=new TextField();
									 * HorizontalPanel Emailpanel=new
									 * HorizontalPanel();
									 * 
									 * 
									 * Emailpanel.add(email);
									 * 
									 * HorizontalPanel Emailbtn=new
									 * HorizontalPanel(); VerticalPanel vp=new
									 * VerticalPanel(); Button btnOk=new
									 * Button("open application"); Button
									 * btnCancel=new Button("Cancel");
									 * Emailbtn.add(btnOk);
									 * Emailbtn.add(btnCancel);
									 * vp.add(errorMsg); vp.add(Emailpanel);
									 * vp.add(Emailbtn); dialog.add(vp);
									 * dialog.show();
									 * btnCancel.addClickHandler(new
									 * ClickHandler() {
									 * 
									 * @Override public void onClick(ClickEvent
									 * event) { // TODO Auto-generated method
									 * stub dialog.hide(); } });
									 * btnOk.addClickHandler(new ClickHandler()
									 * {
									 * 
									 * @Override public void onClick(ClickEvent
									 * event) { if(email.getText()=="") {
									 * errorMsg
									 * .setText("this field cannot be empty !");
									 * } else
									 * if(!email.getText().matches(testEmail)) {
									 * errorMsg
									 * .setText("please type a valid email !");
									 * } else { dialog.hide();
									 * LoggedInInfo.setEmail(email.getText());
									 * RootPanel.get().clear();
									 * RootPanel.get().clear(); Widget con =
									 * asWidget(); Viewport viewport = new
									 * Viewport(); viewport.add(con);
									 * RootPanel.get().add(viewport);
									 * Info.display
									 * ("Welcome Message","Welcome "+
									 * LoggedInInfo.getEmail()); }
									 * 
									 * } });
									 * 
									 * } });
									 */

								}

							}
						});
			}
		});
		
			
		
	}

	// app's princoial widget

	public Widget asWidget() {

		final ContentPanel panelCenter = new ContentPanel();
		LinkAccordionLayout linkLayout = new LinkAccordionLayout();

		Label msg1 = new Label("Follow us on");
		Label msg2 = new Label("IGC © 2013");
		msg1.addStyleName("msg1Style");
		msg2.addStyleName("msg2Style");
		final ContentPanel center = new ContentPanel();
		ContentPanel north = new ContentPanel();
		ContentPanel west = new ContentPanel();
		ContentPanel east = new ContentPanel();
		ContentPanel south = new ContentPanel();
		Image imgFb = new Image(resource.fb());
		Image imgyb = new Image(resource.youtube());
		Image imgogle = new Image(resource.google());
		Image imgmail = new Image(resource.gmail());
		PushButton fbLink = new PushButton(imgFb);
		PushButton youtubeLink = new PushButton(imgyb);
		PushButton googleLink = new PushButton(imgogle);
		PushButton gmailLink = new PushButton(imgmail);
		HorizontalPanel hpSouth = new HorizontalPanel();
		fbLink.setTitle("Facebook page");
		gmailLink.setTitle("Mailing list");
		youtubeLink.setTitle("Youtube channel");
		googleLink.setTitle("G+ page");
		hpSouth.add(msg1);
		hpSouth.add(googleLink);
		hpSouth.add(fbLink);
		hpSouth.add(youtubeLink);
		hpSouth.add(gmailLink);
		hpSouth.add(msg2);
		CenterLayoutContainer centerLayout = new CenterLayoutContainer();
		centerLayout.add(hpSouth);

		south.add(centerLayout);

		fbLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// getURL("https://www.facebook.com/IGC.PageOfficielle?ref=ts&fref=ts");
				Window.open(
						"https://www.facebook.com/IGC.PageOfficielle?ref=ts&fref=ts",
						"_blank", "");

			}
		});
		googleLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// getURL("https://plus.google.com/u/0/communities/116594688349889921594");
				Window.open(
						"https://plus.google.com/u/0/communities/116594688349889921594",
						"_blank", "");
			}
		});
		youtubeLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// getURL("http://www.youtube.com/feed/UCa6qMsO2PQYYyLIvLnddc1w");
				Window.open(
						"http://www.youtube.com/feed/UCa6qMsO2PQYYyLIvLnddc1w",
						"_blank", "");

			}
		});

		gmailLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.open("mailto:insatgoogleclub@googlegroups.com",
						"_blank", "enabled");
			}
		});

		east.add(linkLayout);

		// north panel content

		Image imgLogo = new Image(resource.logo2());
		imgLogo.addStyleName("headerStyle");
		north.setHeaderVisible(false);
		HorizontalPanel hor = new HorizontalPanel();
		HorizontalPanel hp = new HorizontalPanel();
		Label userAcc = new Label(LoggedInInfo.getEmail());

		HTML txt = new HTML("&nbsp;|&nbsp;");

		userAcc.addStyleName("userEmail");

		hp.add(userAcc);
		hp.add(txt);
		hp.add(signOutLink);
		hp.addStyleName("authStyle");
		HorizontalPanel horpanel = new HorizontalPanel();
		horpanel.add(imgLogo);
		horpanel.add(hp);

		north.add(horpanel);

		ToolBar toolBar = new ToolBar();
		toolBar.addStyleName("toolbarStyle");
		TextButton btnhome = new TextButton();
		toolBar.add(btnhome);
		btnhome.addStyleName("toolbarStyle");
		btnhome.setIcon(resource.home());
		btnhome.setText("Home");
		toolBar.add(new SeparatorToolItem());
		TextButton btnadd = new TextButton();
		toolBar.add(btnadd);
		btnadd.setIcon(resource.logo());
		btnadd.setText("New user");
		btnadd.addStyleName("toolbarStyle");

		toolBar.add(new SeparatorToolItem());

		TextButton btnall = new TextButton();
		toolBar.add(btnall);
		btnall.setIcon(resource.users());
		btnall.setText("All users");

		toolBar.add(new SeparatorToolItem());

		TextButton btnstat = new TextButton();
		toolBar.add(btnstat);
		btnstat.setIcon(resource.stat());
		btnstat.setText("Statistics");
		toolBar.add(new SeparatorToolItem());

		Menu menuStat = new Menu();
		MenuItem itemStat1 = new MenuItem("Boys vs Girls");
		MenuItem itemStat2 = new MenuItem("Programming language");
		MenuItem itemStat3 = new MenuItem("Genre of Music");
		menuStat.add(itemStat1);
		menuStat.add(itemStat2);
		menuStat.add(itemStat3);
		btnstat.setMenu(menuStat);

		center.addStyleName("centerPanelStyle");

		TextButton item1 = new TextButton("View");

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

		panelCenter.setLayoutData(new VerticalLayoutData(1, 1));
		pa.add(toolBar);
		pa.add(panelCenter);
		panelCenter.setHeaderVisible(false);
		MarginData centerData = new MarginData();

		BorderLayoutData eastData = new BorderLayoutData(150);
		// eastData.setMargins(new Margins(0, 5, 0, 5));
		eastData.setCollapsible(true);

		con.setId("pcontainer");

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

						center.getElement().getStyle().clearBackgroundColor();
						center.getElement().getStyle()
								.setBackgroundColor("#" + color);
						colorMenu.hide(true);
					}
				});

		panelCenter.add(centerContent());

		// toolbar buttons ==> action handler

		btnall.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				panelCenter.clear();
				center.setHeadingText("All users");
				ListOfUsers allUsers = new ListOfUsers();
				con.collapse(LayoutRegion.SOUTH);
				panelCenter.add(allUsers.asWidget());

			}
		});

		btnhome.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				// panelCenter.clear();
				center.setHeadingText("Home");
				panelCenter.add(centerContent());
				pa.add(panelCenter);

			}
		});

		btnadd.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				panelCenter.clear();
				PersonForm widgetForm = new PersonForm(LoggedInInfo);
				con.collapse(LayoutRegion.SOUTH);
				ScrollPanel scrollpanel = new ScrollPanel();
				scrollpanel.add(widgetForm.asWidget());
				panelCenter.add(scrollpanel);
				center.setHeadingText("Save your personnel information");

			}
		});

		Button buttonwest2 = new Button("GWT Today !");
		Button buttonwest3 = new Button("Fun with GWT Canvas");
		buttonwest2.setWidth("146px");

		FlowPanel vpwest = new FlowPanel();
		vpwest.add(buttonwest2);
		vpwest.add(buttonwest3);
		west.add(vpwest);

		buttonwest2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// panelCenter.clear();
				GwtPieChart chart = new GwtPieChart();
				VerticalPanel vp = new VerticalPanel();
				vp.add(chart.asWidget());
				center.setHeadingText("GWT Today");
				panelCenter.add(vp);

			}
		});

		buttonwest3.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				panelCenter.clear();
				ContentPanel clayout = new ContentPanel();
				clayout.setPixelSize(420, 420);
				CanvasIgc canvasIgc2 = new CanvasIgc();
				clayout.add(canvasIgc2.asWidget());
				clayout.addStyleName("clayoutStyle");
				clayout.setResize(false);
				clayout.setHeadingText("GWT animation");
				panelCenter.add(clayout);
				center.setHeadingText("GWT Canvas Animation");

			}
		});

		itemStat1.addSelectionHandler(new SelectionHandler<Item>() {

			@Override
			public void onSelection(SelectionEvent<Item> event) {
				
				center.setHeadingText("Statistics");
				panelCenter.add(CategSexPanel());
			}
		});
		itemStat2.addSelectionHandler(new SelectionHandler<Item>() {

			@Override
			public void onSelection(SelectionEvent<Item> event) {
				center.setHeadingText("Statistics");
				panelCenter.add(ProgBarChartPanel());
			}
		});

		itemStat3.addSelectionHandler(new SelectionHandler<Item>() {

			@Override
			public void onSelection(SelectionEvent<Item> event) {
				center.setHeadingText("Statistics");
				panelCenter.add(MusicBarChartPanel());
			}
		});

		return simple;

	}

	public CenterLayoutContainer centerContent() {
		ContentPanel con = new ContentPanel();
		ContentPanel cp = new ContentPanel();
		cp.setCollapsible(true);
		cp.setBodyStyleName("pad-text");
		cp.setHeadingText("Advice");
		cp.add(new HTML(
				"<b>If you haven't played with GWT in a while, now is the time to start paying attention</b>"));
		cp.setWidth(200);

		ToolTipConfig config = new ToolTipConfig();
		config.setTitleHtml("Information");
		config.setBodyHtml("hey you can drag me :)");
		config.setMouseOffset(new int[] { 0, 0 });
		config.setAnchor(Side.TOP);
		cp.setToolTipConfig(config);

		Draggable d = new Draggable(cp);
		// center.add(cp,new MarginData(30, 50, 30, 50));

		ContentPanel cp2 = new ContentPanel();
		cp2.setCollapsible(true);
		cp2.setBodyStyleName("pad-text");
		cp2.setHeadingText("technologies used to build this app");
		cp2.add(new HTML(
				"<b><ul><li>Google web toolkit</li><li>Sencha GXT</li><li>Google app engine</li><li>Google DataStore</li><li>Objectify</li></ul></b>"));
		cp2.setWidth(230);

		ContentPanel cp3 = new ContentPanel();
		cp3.setCollapsible(true);
		cp3.setBodyStyleName("pad-text");
		cp3.setHeadingText(" GWT ?");
		cp3.add(new HTML(
				"<b>You are a java developper, you hate javascript and you  want to build complex web apps ==> the solution is GWT</b>"));
		cp3.setWidth(250);

		ContentPanel cp4 = new ContentPanel();
		cp4.setCollapsible(true);
		cp4.setBodyStyleName("pad-text");
		cp4.addStyleName("pad-text");
		cp4.setHeadingText("Source code");
		VerticalPanel sourcePanel = new VerticalPanel();
		HTML sourceTxt = new HTML("<b>Source code is hosted on github :</b>");
		Anchor sourceLink = new Anchor("Click here");
		sourceLink.setHref("https://github.com/HaithemOpeth/IGC.git");
		sourceLink.setTarget("_blank");
		sourcePanel.add(sourceTxt);
		sourcePanel.add(sourceLink);
		cp4.add(sourcePanel);
		cp4.setWidth(250);

		Draggable d2 = new Draggable(cp2);
		Draggable d3 = new Draggable(cp3);
		Draggable d4 = new Draggable(cp4);
		VerticalPanel vp1 = new VerticalPanel();
		VerticalPanel vp2 = new VerticalPanel();
		vp1.add(cp4);
		vp1.add(cp3);
		vp2.add(cp2);
		vp2.add(cp);

		vp1.setSpacing(50);
		vp2.setSpacing(50);

		final CenterLayoutContainer conCenter = new CenterLayoutContainer();
		Image imgCenter = new Image(resource.gwt());
		conCenter.add(imgCenter);

		HorizontalPanel centerPanel = new HorizontalPanel();
		centerPanel.add(vp1);
		centerPanel.add(imgCenter);
		centerPanel.add(vp2);
		centerPanel.setSpacing(5);
		conCenter.add(centerPanel);

		return conCenter;
	}

	public VerticalPanel CategSexPanel() {

		final ContentPanel panel = new FramedPanel();
		final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey());
		final VerticalPanel vppanel = new VerticalPanel();
		get.getCategSEx(new AsyncCallback<List<Data>>() {

			@Override
			public void onFailure(Throwable caught) {
				ContentPanel cpanel=new ContentPanel();
				HTML html=new HTML("<font color=\"red\"> error :"+caught.getMessage()+"</font>");
				cpanel.add(html);
				vppanel.add(cpanel);

			}

			@Override
			public void onSuccess(List<Data> result) {
				store.addAll(result);
				final Chart<Data> chart = new Chart<Data>();
				chart.setDefaultInsets(50);
				chart.setStore(store);
				chart.setShadowChart(true);

				Gradient slice1 = new Gradient("slice1", 45);
				slice1.addStop(new Stop(0, new RGB(148, 174, 10)));
				slice1.addStop(new Stop(100, new RGB(107, 126, 7)));
				chart.addGradient(slice1);

				Gradient slice2 = new Gradient("slice2", 45);
				slice2.addStop(new Stop(0, new RGB(17, 95, 166)));
				slice2.addStop(new Stop(100, new RGB(12, 69, 120)));
				chart.addGradient(slice2);

				final PieSeries<Data> series = new PieSeries<Data>();
				series.setAngleField(dataAccess.data1());
				series.addColor(slice1);
				series.addColor(slice2);
				series.setLabelConfig(null);
				series.setHighlighting(true);

				series.addSeriesItemOverHandler(new SeriesItemOverHandler<Data>() {

					@Override
					public void onSeriesOverItem(SeriesItemOverEvent<Data> event) {
						SeriesToolTipConfig<Data> tool = new SeriesToolTipConfig<Data>();
						tool.setTitleText(event.getItem().getName());
						tool.setBodyText(event.getValueProvider()
								.getValue(event.getItem()).toString());
						series.setToolTipConfig(tool);

					}
				});

				chart.addSeries(series);

				final Legend<Data> legend = new Legend<Data>();
				legend.setPosition(com.sencha.gxt.chart.client.chart.Chart.Position.RIGHT);
				legend.setItemHighlighting(true);
				legend.setItemHiding(true);
				series.setLegendValueProvider(dataAccess.name(),
						new LabelProvider<String>() {

							@Override
							public String getLabel(String item) {
								// TODO Auto-generated method stub
								return item;
							}
						});
				chart.setLegend(legend);

				ToggleButton donut = new ToggleButton("Donut");
				donut.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
					@Override
					public void onValueChange(ValueChangeEvent<Boolean> event) {
						if (event.getValue()) {
							series.setDonut(35);
						} else {
							series.setDonut(0);
						}
						chart.redrawChart();
					}
				});

				ToggleButton animation = new ToggleButton("Animate");
				animation
						.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
							@Override
							public void onValueChange(
									ValueChangeEvent<Boolean> event) {
								chart.setAnimated(event.getValue());
							}
						});
				animation.setValue(true, true);
				ToggleButton shadow = new ToggleButton("Shadow");
				shadow.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
					@Override
					public void onValueChange(ValueChangeEvent<Boolean> event) {
						chart.setShadowChart(event.getValue());
						chart.redrawChart();
					}
				});
				shadow.setValue(true);

				ToolBar toolBar = new ToolBar();
				toolBar.add(animation);
				toolBar.add(shadow);
				toolBar.add(donut);

				panel.getElement().getStyle().setMargin(10, Unit.PX);
				panel.setCollapsible(true);
				panel.setHeadingText("Boys vs Girls ?");
				panel.setPixelSize(520, 400);
				panel.setBodyBorder(true);

				final Resizable resize = new Resizable(panel, Dir.E, Dir.SE,
						Dir.S);
				resize.setMinHeight(400);
				resize.setMinWidth(400);

				panel.addExpandHandler(new ExpandHandler() {
					@Override
					public void onExpand(ExpandEvent event) {
						resize.setEnabled(true);
					}
				});
				panel.addCollapseHandler(new CollapseHandler() {
					@Override
					public void onCollapse(CollapseEvent event) {
						resize.setEnabled(false);
					}
				});

				new Draggable(panel, panel.getHeader()).setUseProxy(false);
				panel.add(chart);

				VerticalLayoutContainer layout = new VerticalLayoutContainer();
				panel.add(layout);

				toolBar.setLayoutData(new VerticalLayoutData(1, -1));
				layout.add(toolBar);

				chart.setLayoutData(new VerticalLayoutData(1, 1));
				layout.add(chart);

				vppanel.add(panel);

			}
		});

		return vppanel;

	}

	public VerticalPanel ProgBarChartPanel() {

		final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey());
		final VerticalPanel vppanel = new VerticalPanel();
		get.getFavProg(new AsyncCallback<List<Data>>() {

			@Override
			public void onFailure(Throwable caught) {
				ContentPanel cpanel=new ContentPanel();
				HTML html=new HTML("<font color=\"red\"> error :"+caught.getMessage()+"</font>");
				cpanel.add(html);
				vppanel.add(cpanel);


			}

			@Override
			public void onSuccess(List<Data> result) {

				store.addAll(result);

				final Chart<Data> chart = new Chart<Data>();
				chart.setStore(store);
				chart.setShadowChart(true);

				Gradient background = new Gradient("backgroundGradient", 0);
				background.addStop(0, new RGB("#ffffff"));
				background.addStop(100, new RGB("#eaf1f8"));
				chart.addGradient(background);
				chart.setBackground(background);

				NumericAxis<Data> axis = new NumericAxis<Data>();
				axis.setPosition(Position.BOTTOM);
				axis.addField(dataAccess.data1());
				TextSprite title = new TextSprite("Number of lovers ");
				title.setFontSize(18);
				axis.setTitleConfig(title);
				axis.setDisplayGrid(true);
				axis.setMinimum(0);
				axis.setMaximum(50);
				chart.addAxis(axis);

				CategoryAxis<Data, String> catAxis = new CategoryAxis<Data, String>();
				catAxis.setPosition(Position.LEFT);
				catAxis.setField(dataAccess.name());
				title = new TextSprite("Programming Language");
				title.setFontSize(18);
				catAxis.setTitleConfig(title);
				chart.addAxis(catAxis);

				final BarSeries<Data> bar = new BarSeries<Data>();
				bar.setYAxisPosition(Position.BOTTOM);
				bar.addYField(dataAccess.data1());
				bar.addColor(new RGB(148, 174, 10));
				bar.setHighlighting(true);
				chart.addSeries(bar);
				bar.addSeriesItemOverHandler(new SeriesItemOverHandler<Data>() {

					@Override
					public void onSeriesOverItem(SeriesItemOverEvent<Data> event) {
						SeriesToolTipConfig<Data> tool = new SeriesToolTipConfig<Data>();
						tool.setTitleText("lovers of "
								+ event.getItem().getName());
						tool.setBodyText(event.getValueProvider()
								.getValue(event.getItem()).toString());
						bar.setToolTipConfig(tool);

					}
				});

				ContentPanel panel = new FramedPanel();
				panel.getElement().getStyle().setMargin(10, Unit.PX);
				panel.setCollapsible(true);
				panel.setHeadingText("Prog.Lang Bar chart");
				panel.setPixelSize(700, 400);
				panel.setBodyBorder(true);

				final Resizable resize = new Resizable(panel, Dir.E, Dir.SE,
						Dir.S);
				resize.setMinHeight(400);
				resize.setMinWidth(400);

				panel.addExpandHandler(new ExpandHandler() {
					@Override
					public void onExpand(ExpandEvent event) {
						resize.setEnabled(true);
					}
				});
				panel.addCollapseHandler(new CollapseHandler() {
					@Override
					public void onCollapse(CollapseEvent event) {
						resize.setEnabled(false);
					}
				});
				new Draggable(panel, panel.getHeader()).setUseProxy(false);
				panel.add(chart);

				VerticalLayoutContainer layout = new VerticalLayoutContainer();
				panel.add(layout);

				toolBar.setLayoutData(new VerticalLayoutData(1, -1));
				layout.add(toolBar);

				chart.setLayoutData(new VerticalLayoutData(1, 1));
				layout.add(chart);

				vppanel.add(panel);
			}
		});

		return vppanel;
	}

	public VerticalPanel MusicBarChartPanel() {
		final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey());
		final VerticalPanel vppanel = new VerticalPanel();
		get.getFavMusic(new AsyncCallback<List<Data>>() {

			@Override
			public void onFailure(Throwable caught) {
				ContentPanel cpanel=new ContentPanel();
				HTML html=new HTML("<font color=\"red\"> error :"+caught.getMessage()+"</font>");
				cpanel.add(html);
				vppanel.add(cpanel);

			}

			@Override
			public void onSuccess(List<Data> result) {

				store.addAll(result);

				final Chart<Data> chart = new Chart<Data>();
				chart.setStore(store);
				chart.setShadowChart(true);

				NumericAxis<Data> axis = new NumericAxis<Data>();
				axis.setPosition(Position.BOTTOM);
				axis.addField(dataAccess.data1());
				TextSprite title = new TextSprite("Number of lovers ");
				title.setFontSize(18);
				axis.setTitleConfig(title);
				axis.setDisplayGrid(true);
				axis.setMinimum(0);
				axis.setMaximum(50);
				chart.addAxis(axis);

				CategoryAxis<Data, String> catAxis = new CategoryAxis<Data, String>();
				catAxis.setPosition(Position.LEFT);
				catAxis.setField(dataAccess.name());
				title = new TextSprite("Genre of music");
				title.setFontSize(18);
				catAxis.setTitleConfig(title);
				chart.addAxis(catAxis);

				final BarSeries<Data> bar = new BarSeries<Data>();
				bar.setYAxisPosition(Position.BOTTOM);
				bar.addYField(dataAccess.data1());
				bar.addColor(RGB.GREEN);
				SeriesLabelConfig<Data> config = new SeriesLabelConfig<Data>();
				config.setLabelPosition(LabelPosition.OUTSIDE);
				bar.setLabelConfig(config);
				chart.addSeries(bar);

				final RGB[] colors = { new RGB(213, 70, 121),
						new RGB(44, 153, 201), new RGB(146, 6, 157),
						new RGB(49, 149, 0), new RGB(249, 153, 0) };

				bar.setRenderer(new SeriesRenderer<Data>() {
					@Override
					public void spriteRenderer(Sprite sprite, int index,
							ListStore<Data> store) {
						double value = dataAccess.data1().getValue(
								store.get(index));
						sprite.setFill(colors[(int) Math.round(value) % 5]);
						sprite.redraw();
					}
				});

				ContentPanel panel = new FramedPanel();
				panel.getElement().getStyle().setMargin(10, Unit.PX);
				panel.setCollapsible(true);
				panel.setHeadingText("Genre of Music");
				panel.setPixelSize(700, 400);
				panel.setBodyBorder(true);

				final Resizable resize = new Resizable(panel, Dir.E, Dir.SE,
						Dir.S);
				resize.setMinHeight(400);
				resize.setMinWidth(400);

				panel.addExpandHandler(new ExpandHandler() {
					@Override
					public void onExpand(ExpandEvent event) {
						resize.setEnabled(true);
					}
				});
				panel.addCollapseHandler(new CollapseHandler() {
					@Override
					public void onCollapse(CollapseEvent event) {
						resize.setEnabled(false);
					}
				});

				new Draggable(panel, panel.getHeader()).setUseProxy(false);
				panel.add(chart);

				VerticalLayoutContainer layout = new VerticalLayoutContainer();
				panel.add(layout);

				toolBar.setLayoutData(new VerticalLayoutData(1, -1));
				layout.add(toolBar);

				chart.setLayoutData(new VerticalLayoutData(1, 1));
				layout.add(chart);

				vppanel.add(panel);
			}
		});

		return vppanel;
	}

}
