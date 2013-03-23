package com.igc.client.chart;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.event.SeriesItemOutEvent;
import com.sencha.gxt.chart.client.chart.event.SeriesItemOutEvent.SeriesItemOutHandler;
import com.sencha.gxt.chart.client.chart.event.SeriesItemOverEvent;
import com.sencha.gxt.chart.client.chart.event.SeriesItemOverEvent.SeriesItemOverHandler;
import com.sencha.gxt.chart.client.chart.series.PieSeries;
import com.sencha.gxt.chart.client.chart.series.Series.LabelPosition;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelConfig;
import com.sencha.gxt.chart.client.chart.series.SeriesToolTipConfig;
import com.sencha.gxt.chart.client.draw.Gradient;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.Stop;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextAnchor;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextBaseline;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.Resizable.Dir;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent.ExpandHandler;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;



public class PieChartExample implements IsWidget {
	
	private AllData allData=new AllData();

	 public interface DataPropertyAccess extends PropertyAccess<Data> {
		    ValueProvider<Data, Integer> data1();
		 
		    ValueProvider<Data, String> name();
		 
		    @Path("name")
		    ModelKeyProvider<Data> nameKey();
		  }
		 
		  private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);
		 
		  public Widget asWidget() {
			  
			 List<Data> allsex=allData.getSex();
		    final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey());
		    store.addAll(allsex);
		 
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
		    slice2.addStop(new Stop(0, new RGB(255, 136, 9)));
		    chart.addGradient(slice2);
		 
		    
		
		    final PieSeries<Data> series = new PieSeries<Data>();
		    series.setAngleField(dataAccess.data1());
		    series.addColor(slice1);
		    series.addColor(slice2);
		    series.addSeriesItemOverHandler(new SeriesItemOverHandler<Data>() {

				@Override
				public void onSeriesOverItem(SeriesItemOverEvent<Data> event) {
					// TODO Auto-generated method stub
					SeriesToolTipConfig<Data> tooltip=new SeriesToolTipConfig<Data>();
					
					tooltip.setTitleText(event.getItem().getName());
					tooltip.setBodyText(event.getValueProvider().getValue(event.getItem()).toString());
					series.setToolTipConfig(tooltip);
				}
			});
	
		    TextSprite textConfig = new TextSprite();
		    textConfig.setFont("Arial");
		    textConfig.setTextBaseline(TextBaseline.MIDDLE);
		    textConfig.setFontSize(18);
		    textConfig.setTextAnchor(TextAnchor.MIDDLE);
		    textConfig.setZIndex(15);
		    SeriesLabelConfig<Data> labelConfig = new SeriesLabelConfig<Data>();
		    labelConfig.setSpriteConfig(textConfig);
		    labelConfig.setLabelPosition(LabelPosition.START);
		    labelConfig.setValueProvider(dataAccess.name(), new StringLabelProvider<String>());
		    series.setLabelConfig(labelConfig);
		    series.setHighlighting(true);
		 
		    chart.addSeries(series);
		 
		    final Legend<Data> legend = new Legend<Data>();
		    legend.setPosition(com.sencha.gxt.chart.client.chart.Chart.Position.RIGHT);
		    legend.setItemHighlighting(true);
		    legend.setItemHiding(true);
		    series.setLegendValueProvider(dataAccess.name(),new LabelProvider<String>() {

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
		    animation.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		      @Override
		      public void onValueChange(ValueChangeEvent<Boolean> event) {
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
		 
		    ContentPanel panel = new FramedPanel();
		    panel.getElement().getStyle().setMargin(10, Unit.PX);
		    panel.setCollapsible(true);
		    panel.setHeadingText("Pie Chart");
	    panel.setPixelSize(520, 400);
		    panel.setBodyBorder(true);
		     
		    final Resizable resize = new Resizable(panel, Dir.E, Dir.SE, Dir.S);
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
		
		    return panel;
		  }
	
}
