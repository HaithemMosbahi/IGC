package com.igc.client.canvas;


import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.GestureStartEvent;
import com.google.gwt.event.dom.client.GestureStartHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.user.client.Timer;

public class CanvasIgc implements IsWidget {
	
	  static final String upgradeMessage = "Your browser does not support the HTML5 Canvas. Please upgrade your browser to view this demo.";
	  Canvas canvas;
	  Canvas backBuffer;
	  LogoGroup logoGroup;
	  BallGroup ballGroup;
	  Lens lens;
	  
	  // mouse positions relative to canvas
	  int mouseX, mouseY;

	  //timer refresh rate, in milliseconds
	  static final int refreshRate = 25;
	  
	  // canvas size, in px
	  static final int height = 400;
	  static final int width = 400;
	  
	  final CssColor redrawColor = CssColor.make("rgba(255,255,255,0.6)");
	  Context2d context;
	  Context2d backBufferContext;
	  

	@Override
	public Widget asWidget() {
		canvas = Canvas.createIfSupported();
	    backBuffer = Canvas.createIfSupported();
	    if (canvas == null) {
	      RootPanel.get().add(new Label(upgradeMessage));
	    }

	    // init the canvases
	    Label label=new Label();
	    label.setText("insat");
	    label.setPixelSize(100, 100);
	   
	    canvas.setWidth(width + "px");
	    canvas.setHeight(height + "px");
	    canvas.setCoordinateSpaceWidth(width);
	    canvas.setCoordinateSpaceHeight(height);
	    backBuffer.setCoordinateSpaceWidth(width);
	    backBuffer.setCoordinateSpaceHeight(height);
	    context = canvas.getContext2d();
	    backBufferContext = backBuffer.getContext2d();
	    
	    // init the objects
	    logoGroup = new LogoGroup(width, height, 18, 165);
	    ballGroup = new BallGroup(width, height);
	    lens = new Lens(35, 15, width, height, new Vector(320, 150), new Vector(1, 1));

	    // init handlers
	    initHandlers();
	    
	    // setup timer
	    final Timer timer = new Timer() {
	      @Override
	      public void run() {
	        doUpdate();
	      }
	    };
	    timer.scheduleRepeating(refreshRate);
	    return canvas;
	}
	
	  void doUpdate() {
		    // update the back canvas
		    backBufferContext.setFillStyle(redrawColor);
		    backBufferContext.fillRect(0, 0, width, height);
		    logoGroup.update(mouseX, mouseY);
		    ballGroup.update(mouseX, mouseY);
		    logoGroup.draw(backBufferContext);
		    ballGroup.draw(backBufferContext);

		    // update the front canvas
		    lens.update();
		    lens.draw(backBufferContext, context);
		  }
		  
		  void initHandlers() {
		    canvas.addMouseMoveHandler(new MouseMoveHandler() {
		      public void onMouseMove(MouseMoveEvent event) {
		        mouseX = event.getRelativeX(canvas.getElement());
		        mouseY = event.getRelativeY(canvas.getElement());
		      }
		    });

		    canvas.addMouseOutHandler(new MouseOutHandler() {
		      public void onMouseOut(MouseOutEvent event) {
		        mouseX = -200;
		        mouseY = -200;
		      }
		    });

		    canvas.addTouchMoveHandler(new TouchMoveHandler() {
		      public void onTouchMove(TouchMoveEvent event) {
		        event.preventDefault();
		        if (event.getTouches().length() > 0) {
		          Touch touch = event.getTouches().get(0);
		          mouseX = touch.getRelativeX(canvas.getElement());
		          mouseY = touch.getRelativeY(canvas.getElement());
		        }
		        event.preventDefault();
		      }
		    });

		    canvas.addTouchEndHandler(new TouchEndHandler() {
		      public void onTouchEnd(TouchEndEvent event) {
		        event.preventDefault();
		        mouseX = -200;
		        mouseY = -200;
		      }
		    });

		    canvas.addGestureStartHandler(new GestureStartHandler() {
		      public void onGestureStart(GestureStartEvent event) {
		        event.preventDefault();
		      }
		    });
		  }
	}