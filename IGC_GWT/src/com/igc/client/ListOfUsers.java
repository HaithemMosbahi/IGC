package com.igc.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.igc.shared.Person;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.state.client.GridStateHandler;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.Resizable.Dir;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.grid.CellSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.selection.CellSelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.CellSelectionChangedEvent.CellSelectionChangedHandler;
import com.sencha.gxt.widget.core.client.tips.QuickTip;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;


public class ListOfUsers implements IsWidget {
	
	private ContentPanel root;
	private static final PersonProperties props = GWT.create(PersonProperties.class);
	private CrudServiceAsync crudService=GWT.create(CrudService.class);
	private List<Person> allPerson=new ArrayList<Person>();
	

	@Override
	public Widget asWidget() {
	    if (root == null) {
	    	root=new ContentPanel();
	  
	    	crudService.finddAll(new AsyncCallback<List<Person>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(List<Person> result) {
					  final ColumnConfig<Person, String> firstn = new ColumnConfig<Person, String>(props.firstName(), 100, "First name");
					  final ColumnConfig<Person, String> lastn = new ColumnConfig<Person, String>(props.lastName(), 100, "Last name");
				      final ColumnConfig<Person, String> emailCol = new ColumnConfig<Person, String>(props.email(), 170, "Email");
				      final ColumnConfig<Person, Date> registerdateCol = new ColumnConfig<Person, Date>(props.registerDate(), 150, "Registration date");
				      final ColumnConfig<Person, String> universityCol = new ColumnConfig<Person, String>(props.university(), 100, "Faculty/University");
				      final ColumnConfig<Person, String> occupCol = new ColumnConfig<Person, String>(props.occupation(), 100, "Occupation");
				      final ColumnConfig<Person, String> gwtCol = new ColumnConfig<Person, String>(props.gwtUser(), 80, "GWT User");
				      registerdateCol.setCell(new DateCell(com.google.gwt.i18n.client.DateTimeFormat.getFormat("MM/dd/yyyy HH:mm:ss")));
				      gwtCol.setCell(new AbstractCell<String>() {
				  
						@Override
						public void render(
								com.google.gwt.cell.client.Cell.Context context,
								String value, SafeHtmlBuilder sb) {
							if(value != null)
							{
								
						
							  String style = "style='color: " + (value.equals("No") ? "red" : "green") + "'";
					          
					            sb.appendHtmlConstant("<span " + style + ">" + value + "</span>");
					          }
							else
							{
								 sb.appendHtmlConstant("<span color:red> -- </span>");
							}
						}
						
				        });
				      
				      java.util.List<ColumnConfig<Person, ?>> l = new ArrayList<ColumnConfig<Person, ?>>();
				      l.add(firstn);
				      l.add(lastn);
				      l.add(emailCol);
				      l.add(registerdateCol);
				      l.add(universityCol);
				      l.add(occupCol);
				      l.add(gwtCol);
				      
				    
				      ColumnModel<Person> cm = new ColumnModel<Person>(l);
				     
				      ListStore<Person> store = new ListStore<Person>(props.key());
				      store.addAll(result);
				     
				   
				      root.setHeadingText("List of users");
				      
				      root.setPixelSize(820, 350);
				      root.addStyleName("margin-10");
				       
				      ToolButton info = new ToolButton(ToolButton.QUESTION);
				      ToolTipConfig config = new ToolTipConfig("Info", "This examples includes resizable panel, reorderable columns and grid state.");
				      config.setMaxWidth(225);
				      info.setToolTipConfig(config);
				      root.addTool(info);
				       
				      new Resizable(root, Dir.E, Dir.SE, Dir.S);
				 
				      final Grid<Person> grid = new Grid<Person>(store, cm);
				     
				   
				      grid.getView().setStripeRows(true);
				      grid.getView().setColumnLines(true);
				      grid.setBorders(false);
				      
				 
				      grid.setColumnReordering(true);
				      grid.setStateful(true);
				      grid.setStateId("gridExample");

				    
				 
				      ToolBar toolBar = new ToolBar();
				      toolBar.add(new LabelToolItem("Selection Mode: "));
				 
				      SimpleComboBox<String> type = new SimpleComboBox<String>(new StringLabelProvider<String>());
				      type.setTriggerAction(TriggerAction.ALL);
				      type.setEditable(false);
				      type.setWidth(100);
				      type.add("Row");
				      type.add("Cell");
				      type.setValue("Row");
				      // we want to change selection model on select, not value change which fires on blur
				      type.addSelectionHandler(new SelectionHandler<String>() {
				 
				        @Override
				        public void onSelection(SelectionEvent<String> event) {
				          boolean cell = event.getSelectedItem().equals("Cell");
				          if (cell) {
				            CellSelectionModel<Person> c = new CellSelectionModel<Person>();
				            c.addCellSelectionChangedHandler(new CellSelectionChangedHandler<Person>() {
				 
				              @Override
				              public void onCellSelectionChanged(CellSelectionChangedEvent<Person> event) {
				 
				              }
				            });
				 
				            grid.setSelectionModel(c);
				          } else {
				            grid.setSelectionModel(new GridSelectionModel<Person>());
				          }
				        }
				      });
				      type.addValueChangeHandler(new ValueChangeHandler<String>() {
				 
				        @Override
				        public void onValueChange(ValueChangeEvent<String> event) {
				 
				        }
				      });
				      toolBar.add(type);
				 
				      VerticalLayoutContainer con = new VerticalLayoutContainer();
				      root.setWidget(con);
				 
				      con.add(toolBar, new VerticalLayoutData(1, -1));
				      con.add(grid, new VerticalLayoutData(1, 1));
				 
				      // needed to enable quicktips (qtitle for the heading and qtip for the
				      // content) that are setup in the change GridCellRenderer
				      new QuickTip(grid);
					
				}
			});
	
	 
	     
	  }
	
	 

	    return root;
		
	  }}