package com.igc.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;
import org.omg.CORBA.PUBLIC_MEMBER;

import sun.misc.Perf.GetPerfAction;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.LocalizableResource.Description;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.igc.shared.Person;
import com.sencha.gxt.core.client.Style.HideMode;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent.ParseErrorHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.FormPanelHelper;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.TimeField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.info.Info;

public class PersonForm implements IsWidget {
	private Person peson=null;
	private List<String> music=new ArrayList<String>();
	private List<String> prog=new ArrayList<String>();
	final CrudServiceAsync crudService=GWT.create(CrudService.class);
	
	
	
	 private static final int COLUMN_FORM_WIDTH = 700;
	  private VerticalPanel vp;
	  
	  
	 
	  public PersonForm(Person peson) {
		super();
		this.peson = peson;
	}



	public Person getPeson() {
		return peson;
	}



	public void setPeson(Person peson) {
		this.peson = peson;
	}



	public Widget asWidget() {
	    if (vp == null) {
	      vp = new VerticalPanel();
	      vp.setSpacing(10);
	      createColumnForm();
	    
	    }
	    return vp;
	  }
	 
	
	
	 
	  public void createColumnForm() {
	    FramedPanel panel = new FramedPanel();
	    panel.setHeadingText("Form Example");
	    panel.setWidth(COLUMN_FORM_WIDTH);

	    HtmlLayoutContainer con = new HtmlLayoutContainer(getTableMarkup());
	    panel.setWidget(con);
	 
	    int cw = (COLUMN_FORM_WIDTH / 2) - 12;
	 
	    final TextField firstName = new TextField();
	    firstName.setEmptyText("Enter your first name...");
	    firstName.setAllowBlank(false);
	  
	    firstName.setWidth(cw);
	    con.add(new FieldLabel(firstName, "First Name"), new HtmlData(".fn"));
	 
	    final TextField lastName = new TextField();
	    lastName.setEmptyText("Enter your last name...");
	    lastName.setWidth(cw);
	    lastName.setAllowBlank(false);
	    con.add(new FieldLabel(lastName, "Last Name"), new HtmlData(".ln"));
	 
	    final TextField company = new TextField();
	    company.setWidth(cw);
	   
	    con.add(new FieldLabel(company, "University"), new HtmlData(".company"));
	 
	    TextField email = new TextField();
	    email.setWidth(cw);
	    email.setText(this.peson.getEmail());
	    email.setReadOnly(true);
	    con.add(new FieldLabel(email, "Email"), new HtmlData(".email"));
	 
	    final TextField occup = new TextField();
	    occup.setWidth(cw);
	    con.add(new FieldLabel(occup, "Occupation (student,engineer,.."), new HtmlData(".occup"));
	 
	    final Radio radio1 = new Radio();
	    radio1.setBoxLabel("Homme");
	 
	    final Radio radio2 = new Radio();
	    radio2.setBoxLabel("Femme");
	 
	    HorizontalPanel hp = new HorizontalPanel();
	    hp.add(radio1);
	    hp.add(radio2);
	 
	    con.add(new FieldLabel(hp, "Sex"), new HtmlData(".user"));
	 
	    ToggleGroup group = new ToggleGroup();
	    group.add(radio1);
	    group.add(radio2);
	    
	    //handler for prog changed
	    ValueChangeHandler<Boolean> progHandler=new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				
				CheckBox check=(CheckBox) event.getSource();
				 if(event.getValue())
				    	prog.add(check.getBoxLabel());
				 else
					 prog.remove(check.getBoxLabel());
			}
		};
	    
	    //handler for music changed
	    ValueChangeHandler<Boolean> musicHandler=new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				CheckBox check=(CheckBox) event.getSource();
				Info.display("Music Changed ", check.getBoxLabel()+ " "+(event.getValue() ? "selected":"not selected"));				
			    if(event.getValue())
			    	music.add(check.getBoxLabel());
			    else
					 music.remove(check.getBoxLabel());
			}
		};
		final CheckBox check1=new CheckBox();
	   check1.setBoxLabel("Rock");
	   check1.addValueChangeHandler(musicHandler);
	   final CheckBox check2=new CheckBox();
	    check2.setBoxLabel("Classical");
	    check2.addValueChangeHandler(musicHandler);
	    final CheckBox check3=new CheckBox();
	     check3.setBoxLabel("Blues");
	     check3.addValueChangeHandler(musicHandler);
	     final CheckBox check4=new CheckBox();
	     check4.setBoxLabel("Rap");
	     check4.addValueChangeHandler(musicHandler);
	     final  CheckBox check5=new CheckBox();
	     check5.setBoxLabel("Pop");
	     check5.addValueChangeHandler(musicHandler);
	     final CheckBox check6=new CheckBox();
	     check6.setBoxLabel("Metal");
	     check6.addValueChangeHandler(musicHandler);
	     
	     final CheckBox check7=new CheckBox();
	     check7.setBoxLabel("Oriental");
	     check7.addValueChangeHandler(musicHandler);
	     
	     final CheckBox check8=new CheckBox();
	     check8.setBoxLabel("Reggae");
	     check8.addValueChangeHandler(musicHandler);
	     
	     final CheckBox check9=new CheckBox();
	     check9.setBoxLabel("Electronic");
	     check9.addValueChangeHandler(musicHandler);
	     
	     final CheckBox check10=new CheckBox();
	     check10.setBoxLabel("Jazz");
	     check10.addValueChangeHandler(musicHandler);
	     
	     final CheckBox check11=new CheckBox();
	     check11.setBoxLabel("R&B");
	     check11.addValueChangeHandler(musicHandler);
	     
	     final CheckBox check12=new CheckBox();
	     check12.setBoxLabel("Latin");
	     check12.addValueChangeHandler(musicHandler);
	    
	    HorizontalPanel h1=new HorizontalPanel();
	    HorizontalPanel h2=new HorizontalPanel();
	    HorizontalPanel h3=new HorizontalPanel();
	    VerticalPanel v1=new VerticalPanel();
	 h1.add(check1);h1.add(check2);h1.add(check3);h1.add(check10);
	 h2.add(check4);h2.add(check5);h2.add(check6);h2.add(check11);
	 h3.add(check7);h3.add(check8);h3.add(check9);h3.add(check12);
	 
	 v1.add(h1); v1.add(h2); v1.add(h3);
	 final FieldSet fieldSet2 = new FieldSet();
	    fieldSet2.setHeadingText("Your favorite genre of music");
	    fieldSet2.setCollapsible(true);
	    fieldSet2.add(v1);
	    fieldSet2.collapse();
	    con.add(new FieldLabel(fieldSet2,"Music"), new HtmlData(".music"));
	 
	 // Prog langage :
	 final CheckBox ch1=new CheckBox();
	 ch1.setBoxLabel("Java");
	 ch1.addValueChangeHandler(progHandler);
	 
	 final CheckBox ch2=new CheckBox();
	 ch2.setBoxLabel("C/C++");
	 ch2.addValueChangeHandler(progHandler);
	 
	 final CheckBox ch3=new CheckBox();
	 ch3.setBoxLabel("Python");
	 ch3.addValueChangeHandler(progHandler);
	 
	 final CheckBox ch4=new CheckBox();
	 ch4.setBoxLabel("Ruby");
	 ch4.addValueChangeHandler(progHandler);
	 
	 final CheckBox ch5=new CheckBox();
	 ch5.setBoxLabel("C#");
	 ch5.addValueChangeHandler(progHandler);
	 
	 final CheckBox ch6=new CheckBox();
	 ch6.setBoxLabel("Scala");
	 ch6.addValueChangeHandler(progHandler);
	 
	 final CheckBox ch7=new CheckBox();
	 ch7.setBoxLabel("Objective-C");
	 ch7.addValueChangeHandler(progHandler);
	 
	 final CheckBox ch8=new CheckBox();
	 ch8.setBoxLabel("Groovy");
	 ch8.addValueChangeHandler(progHandler);
	 
	 final CheckBox ch9=new CheckBox();
	 ch9.setBoxLabel("PHP");
	 ch9.addValueChangeHandler(progHandler);
	 
	 final CheckBox ch10=new CheckBox();
	 ch10.setBoxLabel("JavaScript");
	 ch10.addValueChangeHandler(progHandler);
	 
	 final CheckBox ch11=new CheckBox();
	 ch11.setBoxLabel("Perl");
	 ch11.addValueChangeHandler(progHandler);
	 
	 final CheckBox ch12=new CheckBox();
	 ch12.setBoxLabel("other");
	 ch12.addValueChangeHandler(progHandler);
	 
	  HorizontalPanel h4=new HorizontalPanel();
	    HorizontalPanel h5=new HorizontalPanel();
	    HorizontalPanel h6=new HorizontalPanel();
	    VerticalPanel v2=new VerticalPanel();
	    h4.add(ch1);h4.add(ch2);h4.add(ch3);h4.add(ch4);
	    h5.add(ch5);h5.add(ch6);h5.add(ch7);h5.add(ch8);
	    h6.add(ch9);h6.add(ch10);h6.add(ch11);h6.add(ch12);
	    
	    v2.add(h4); v2.add(h5); v2.add(h6);
	
	    final FieldSet fieldSet = new FieldSet();
	    fieldSet.setHeadingText("Your favorite programming language");
	    fieldSet.setCollapsible(true);
	    fieldSet.add(v2);
	    fieldSet.collapse();
	    
	    con.add(new FieldLabel(fieldSet,"Prog.Language"), new HtmlData(".prog"));
	    
	    final Radio radio3 = new Radio();
	    radio3.setBoxLabel("Yes");
	 
	    final Radio radio4 = new Radio();
	    radio4.setBoxLabel("No");
	 
	    HorizontalPanel hp6 = new HorizontalPanel();
	    hp6.add(radio3);
	    hp6.add(radio4);
	 
	    con.add(new FieldLabel(hp6, "GWT user ?"), new HtmlData(".comment"));
	 
	    final TextArea desription=new TextArea();
	    desription.setPixelSize(300, 80);
	    con.add(new FieldLabel(desription, "Comment"), new HtmlData(".gwt"));
	    
	    TextButton btnsave=new TextButton("Save");
	    TextButton btncancel=new TextButton("Cancel");
	 
	    panel.addButton(btnsave);
	    panel.addButton(btncancel);
	    
	  
	    
	    btnsave.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
			   getPeson().setFirstName(firstName.getText());
			   getPeson().setLastName(lastName.getText());
			   getPeson().setUniversity(company.getText());
			   if(radio1.getValue())
			   {
				   getPeson().setSex("Homme");
			   }
			   if(radio2.getValue())
				   getPeson().setSex("Femme");
			   getPeson().setOccupation(occup.getCurrentValue());
			   getPeson().setComment(desription.getCurrentValue());
			   getPeson().setFavMusic(music);
			   getPeson().setFavProgLang(prog);
			   if(radio3.getValue())
				   getPeson().setGwtUser("Yes");
			   if(radio4.getValue())
			   getPeson().setGwtUser("No");
			   Date date=new Date();
			  
			   getPeson().setRegisterDate(date);
			   if((getPeson().getFirstName()=="")||(getPeson().getLastName()=="")||(getPeson().getFirstName().equals("Enter your first name..."))||(getPeson().getLastName().equals("Enter your last name...")))
			   {
				   AlertMessageBox d = new AlertMessageBox("Alert", "Your first & last name are required fields");
			    
			        d.show();
			   }
			   else
			   {
				   
			  
			   crudService.save(getPeson(),new AsyncCallback<Person>() {

				   
				@Override
				public void onFailure(Throwable caught) {
					
					
				}

				@Override
				public void onSuccess(Person result) {
					final AutoProgressMessageBox box = new AutoProgressMessageBox("Progress", "Saving your data, please wait...");
			        box.setProgressText("Saving...");
			        box.auto();
			        box.show();
			        firstName.setText("");
					lastName.setText("");
					occup.setText("");
					radio1.setValue(false);
					radio2.setValue(false);
					radio3.setValue(false);
					radio4.setValue(false);
					desription.setText("");
					company.setText("");
				     ch1.setValue(false); ch2.setValue(false); ch3.setValue(false); ch4.setValue(false); ch5.setValue(false);
				     ch6.setValue(false); ch7.setValue(false); ch8.setValue(false); ch9.setValue(false); ch10.setValue(false);
				     ch11.setValue(false); ch12.setValue(false); 
				     check1.setValue(false);  check2.setValue(false);  check3.setValue(false);  check4.setValue(false);  check5.setValue(false);
				     check6.setValue(false);  check7.setValue(false);  check8.setValue(false);  check9.setValue(false);  check10.setValue(false);
				     check11.setValue(false);  check12.setValue(false);
				
					music=new ArrayList<String>();
					prog=new ArrayList<String>();
			        Timer t = new Timer() {
			          @Override
			          public void run() {
			            Info.display("Message", "Your data was saved");
			            box.hide();
			          }
			        };
			        t.schedule(300);
					
				}
			}); }
				
			}
		});
	    
        btncancel.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				// TODO Auto-generated method stub
				firstName.setText("");
				lastName.setText("");
				occup.setText("");
				radio1.setValue(false);
				radio2.setValue(false);
				radio3.setValue(false);
				radio4.setValue(false);
				desription.setText("");
				company.setText("");
				
			     ch1.setValue(false); ch2.setValue(false); ch3.setValue(false); ch4.setValue(false); ch5.setValue(false);
			     ch6.setValue(false); ch7.setValue(false); ch8.setValue(false); ch9.setValue(false); ch10.setValue(false);
			     ch11.setValue(false); ch12.setValue(false); 
			     check1.setValue(false);  check2.setValue(false);  check3.setValue(false);  check4.setValue(false);  check5.setValue(false);
			     check6.setValue(false);  check7.setValue(false);  check8.setValue(false);  check9.setValue(false);  check10.setValue(false);
			     check11.setValue(false);  check12.setValue(false);
				
				music=new ArrayList<String>();
				prog=new ArrayList<String>();
				
				
				
			}
		});
	 
	 
      
	    
	    // need to call after everything is constructed
	    List<FieldLabel> labels = FormPanelHelper.getFieldLabels(panel);
	    for (FieldLabel lbl : labels) {
	      lbl.setLabelAlign(LabelAlign.TOP);
	    }
	 
	    vp.add(panel);
	    
	  }
	 
	  
	  
	 
	  private native String getTableMarkup() /*-{
	    return [ '<table width=100% cellpadding=0 cellspacing=0>',
	        '<tr><td class=fn width=50%></td><td class=ln width=50%></td></tr>',
	        '<tr><td class=company></td><td class=email></td></tr>',
	        '<tr><td class=occup></td><td class=user></td></tr>',
	        '<tr><center><td class=music></td></center><td class=prog></td></tr>',
	       '<tr><td class=gwt></td><td class=comment></td></tr>',
	         '</table>'
	 
	    ].join("");
	  }-*/;
	 
	
}
	
