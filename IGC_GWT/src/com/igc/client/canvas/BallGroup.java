package com.igc.client.canvas;

import com.google.gwt.canvas.dom.client.Context2d;

import java.util.ArrayList;

public class BallGroup {
  final double width;
  final double height;
  Ball[] balls;
  
  public BallGroup(double width, double height) {
    this.width = width;
    this.height = height;
    ArrayList<Ball> ballsArrayList = new ArrayList<Ball>(0);
    
    // init balls (values from Google's homepag
 /*   ballsArrayList.add(new Ball(100, 100, 0, 2, "#0000000"));
    ballsArrayList.add(new Ball(88, 95, 0, 2, "#0000000"));
    ballsArrayList.add(new Ball(76, 94, 0, 2, "#0000000"));
    ballsArrayList.add(new Ball(68, 100, 0, 2, "#0000000"));
    ballsArrayList.add(new Ball(58, 110, 0, 2, "#0000000"));
    ballsArrayList.add(new Ball(57, 125, 0, 2, "#0000000"));
    ballsArrayList.add(new Ball(64, 136, 0, 2, "#0000000"));
    ballsArrayList.add(new Ball(76, 145, 0, 2, "#0000000"));
    ballsArrayList.add(new Ball(88, 145, 0, 2, "#0000000"));
    ballsArrayList.add(new Ball(100, 142, 0, 2, "#0000000"));
    
    ballsArrayList.add(new Ball(120, 100, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(120, 110, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(120, 120, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(120, 130, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(120, 140, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(130, 140, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(140, 140, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(150, 140, 0, 4, "#0000000"));
    
    ballsArrayList.add(new Ball(170, 100, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(170, 110, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(170, 120, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(170, 130, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(170, 140, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(182, 140, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(195, 140, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(195, 130, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(195, 120, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(195, 110, 0, 4, "#0000000"));
    ballsArrayList.add(new Ball(195, 100, 0, 4, "#0000000"));
 
    ballsArrayList.add(new Ball(100, 200, 0, 10, "#ed9d33"));
    ballsArrayList.add(new Ball(100, 200, 0, 10, "#ed9d33"));
    ballsArrayList.add(new Ball(100, 200, 0, 10, "#ed9d33"));
    ballsArrayList.add(new Ball(100, 200, 0, 10, "#ed9d33"));
    ballsArrayList.add(new Ball(100, 200, 0, 10, "#ed9d33"));
    ballsArrayList.add(new Ball(100, 200, 0, 10, "#ed9d33"));*/
   
    
    // I
    ballsArrayList.add(new Ball(110, 0, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(110, 6, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(110, 12, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(110, 18, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(110, 24, 0, 4, "#000000"));
    
    // N
    ballsArrayList.add(new Ball(125, 0, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(125, 6, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(125, 12, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(125, 18, 0,4, "#000000"));
    ballsArrayList.add(new Ball(125, 24, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(130, 6, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(135, 12, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(140, 18, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(145, 24, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(145, 18, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(145, 12, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(145, 6, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(145, 0, 0, 4, "#000000"));
    
    //S
    ballsArrayList.add(new Ball(172, 2, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(166, 0, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(160, 2, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(158, 5, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(160, 10, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(167, 14, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(172, 16, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(170, 21, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(165, 24, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(160, 22, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(156, 20, 0, 3.5, "#000000"));
    
    
    //A
    ballsArrayList.add(new Ball(196, 0, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(192, 6, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(200, 6, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(188, 12, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(192, 16, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(200, 16, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(204, 12, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(184, 18, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(208, 18, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(180, 24, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(212, 24, 0, 4, "#000000"));

    //T
    ballsArrayList.add(new Ball(230, 0, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(225, 0, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(235, 0, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(220, 0, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(240, 0, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(230, 6, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(230, 12, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(230, 18, 0, 4, "#000000"));
    ballsArrayList.add(new Ball(230, 24, 0, 4, "#000000"));
    
    //Google
  ballsArrayList.add(new Ball(202, 83, 0, 9, "#ed9d33"));
    ballsArrayList.add(new Ball(348, 88, 0, 9, "#d44d61"));
    ballsArrayList.add(new Ball(256, 74, 0, 9, "#4f7af2"));
    ballsArrayList.add(new Ball(214, 64, 0, 9, "#ef9a1e"));
    ballsArrayList.add(new Ball(265, 41, 0, 9, "#4976f3"));
    ballsArrayList.add(new Ball(300, 83, 0, 9, "#269230"));
    ballsArrayList.add(new Ball(294, 64, 0, 9, "#1f9e2c"));
    ballsArrayList.add(new Ball(45, 93, 0, 9, "#1c48dd"));
    ballsArrayList.add(new Ball(268, 57, 0, 9, "#2a56ea"));
    ballsArrayList.add(new Ball(73, 88, 0, 9, "#3355d8"));
    ballsArrayList.add(new Ball(294, 11, 0, 9, "#36b641"));
    ballsArrayList.add(new Ball(235, 67, 0, 9, "#2e5def"));
    ballsArrayList.add(new Ball(353, 47, 0, 8, "#d53747"));
    ballsArrayList.add(new Ball(336, 57, 0, 8, "#eb676f"));
    ballsArrayList.add(new Ball(208, 46, 0, 8, "#f9b125"));
    ballsArrayList.add(new Ball(321, 75, 0, 8, "#de3646"));
    ballsArrayList.add(new Ball(8, 65, 0, 8, "#2a59f0"));
    ballsArrayList.add(new Ball(180, 86, 0, 8, "#eb9c31"));
    ballsArrayList.add(new Ball(146, 71, 0, 8, "#c41731"));
    ballsArrayList.add(new Ball(145, 54, 0, 8, "#d82038"));
    ballsArrayList.add(new Ball(246, 39, 0, 8, "#5f8af8"));
    ballsArrayList.add(new Ball(169, 74, 0, 8, "#efa11e"));
    ballsArrayList.add(new Ball(273, 104, 0, 8, "#2e55e2"));
    ballsArrayList.add(new Ball(248, 125, 0, 8, "#4167e4"));
    ballsArrayList.add(new Ball(294, 46, 0, 8, "#0b991a"));
    ballsArrayList.add(new Ball(267, 119, 0, 8, "#4869e3"));
    ballsArrayList.add(new Ball(78, 72, 0, 8, "#3059e3"));
    ballsArrayList.add(new Ball(294, 28, 0, 8, "#10a11d"));
    ballsArrayList.add(new Ball(117, 88, 0, 8, "#cf4055"));
    ballsArrayList.add(new Ball(137, 85, 0, 8, "#cd4359"));
    ballsArrayList.add(new Ball(14, 76, 0, 8, "#2855ea"));
    ballsArrayList.add(new Ball(331, 85, 0, 8, "#ca273c"));
    ballsArrayList.add(new Ball(25, 87, 0, 8, "#2650e1"));
    ballsArrayList.add(new Ball(233, 51, 0, 8, "#4a7bf9"));
    ballsArrayList.add(new Ball(73, 18, 0, 8, "#3d65e7"));
    ballsArrayList.add(new Ball(327, 40, 0, 6, "#f47875"));
    ballsArrayList.add(new Ball(319, 49, 0, 6, "#f36764"));
    ballsArrayList.add(new Ball(256, 86, 0, 6, "#1d4eeb"));
    ballsArrayList.add(new Ball(244, 93, 0, 6, "#698bf1"));
    ballsArrayList.add(new Ball(194, 37, 0, 6, "#fac652"));
    ballsArrayList.add(new Ball(97, 61, 0, 6, "#ee5257"));
    ballsArrayList.add(new Ball(105, 80, 0, 6, "#cf2a3f"));
    ballsArrayList.add(new Ball(42, 9, 0, 6, "#5681f5"));
    ballsArrayList.add(new Ball(10, 32, 0, 6, "#4577f6"));
    ballsArrayList.add(new Ball(166, 60, 0, 6, "#f7b326"));
    ballsArrayList.add(new Ball(266, 93, 0, 6, "#2b58e8"));
    ballsArrayList.add(new Ball(178, 39, 0, 6, "#facb5e"));
    ballsArrayList.add(new Ball(100, 70, 0, 6, "#e02e3d"));
    ballsArrayList.add(new Ball(343, 37, 0, 6, "#f16d6f"));
    ballsArrayList.add(new Ball(59, 10, 0, 6, "#507bf2"));
    ballsArrayList.add(new Ball(27, 14, 0, 6, "#5683f7"));
    ballsArrayList.add(new Ball(233, 121, 0, 6, "#3158e2"));
    ballsArrayList.add(new Ball(123, 37, 0, 6, "#f0696c"));
    ballsArrayList.add(new Ball(6, 43, 0, 6, "#3769f6"));
    ballsArrayList.add(new Ball(63, 67, 0, 6, "#6084ef"));
    ballsArrayList.add(new Ball(6, 51, 0, 6, "#2a5cf4"));
    ballsArrayList.add(new Ball(108, 41, 0, 6, "#f4716e"));
    ballsArrayList.add(new Ball(169, 46, 0, 6, "#f8c247"));
    ballsArrayList.add(new Ball(137, 42, 0, 6, "#e74653"));
    ballsArrayList.add(new Ball(318, 63, 0, 6, "#ec4147"));
    ballsArrayList.add(new Ball(226, 105, 0, 5, "#4876f1"));
    ballsArrayList.add(new Ball(101, 51, 0, 5, "#ef5c5c"));
    ballsArrayList.add(new Ball(226, 113, 0, 5, "#2552ea"));
    ballsArrayList.add(new Ball(17, 22, 0, 5, "#4779f7"));
    ballsArrayList.add(new Ball(232, 98, 0, 5, "#4b78f1"));
    
    // C
    ballsArrayList.add(new Ball(132, 110, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(127, 105, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(122, 105, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(117, 110, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(114, 115, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(114, 120, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(117, 125, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(122, 129, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(127, 128, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(132, 124, 0, 3.5, "#000000"));
    
    // L
    ballsArrayList.add(new Ball(145, 105, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(145, 111, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(145, 117, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(145, 122, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(147, 128, 0, 3.5, "#000000"));
    
   /* ballsArrayList.add(new Ball(130, 128, 0, 4, "#f7b326"));
    ballsArrayList.add(new Ball(135, 128, 0, 4, "#f7b326"));
    ballsArrayList.add(new Ball(140, 128, 0, 4, "#f7b326"));
    */
    
    // U
    //ballsArrayList.add(new Ball(155, 105, 0, 4, "#f7b326"));
    //ballsArrayList.add(new Ball(155, 111, 0, 4, "#f7b326"));
    ballsArrayList.add(new Ball(160, 117, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(160, 122, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(160, 128, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(165, 128, 0, 3, "#000000"));
    ballsArrayList.add(new Ball(170, 128, 0, 3, "#000000"));
    ballsArrayList.add(new Ball(175, 128, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(175, 122, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(175, 117, 0, 3.5, "#000000"));
   // ballsArrayList.add(new Ball(170, 111, 0, 4, "#f7b326"));
    //ballsArrayList.add(new Ball(170, 105, 0, 4, "#f7b326"));
    
    // B
    
    ballsArrayList.add(new Ball(188, 105, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(188, 111, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(188, 117, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(188, 122, 0, 3.5, "#000000"));
    ballsArrayList.add(new Ball(188, 128, 0, 3.5, "#000000"));
   /* ballsArrayList.add(new Ball(190, 104, 0, 4, "#f7b326"));
    ballsArrayList.add(new Ball(195, 103, 0, 4, "#f7b326"));
    ballsArrayList.add(new Ball(198, 104, 0, 4, "#f7b326"));
    ballsArrayList.add(new Ball(203, 107, 0, 4, "#f7b326"));
    ballsArrayList.add(new Ball(203, 111, 0, 4, "#f7b326"));
    ballsArrayList.add(new Ball(203, 114, 0, 4, "#f7b326"));*/
    ballsArrayList.add(new Ball(199, 118, 0, 3, "#000000"));
    ballsArrayList.add(new Ball(193, 118, 0, 3, "#000000"));
    ballsArrayList.add(new Ball(202, 122, 0, 3, "#000000"));
    ballsArrayList.add(new Ball(202, 125, 0, 3, "#000000"));
    ballsArrayList.add(new Ball(200, 128, 0, 3, "#000000"));
    ballsArrayList.add(new Ball(195, 128, 0, 3, "#000000"));
    ballsArrayList.add(new Ball(190, 128, 0, 3, "#000000"));
    
   

   
  

    
    // adjust sizes for this demo
    double scale = 0.70f;
    for (int i = ballsArrayList.size() - 1; i >= 0; i--) {
      Ball ball = ballsArrayList.get(i);
      ball.pos.x = width / 2 - scale * 180 + scale * ball.pos.x;
      ball.pos.y = height / 2 - scale * 65 + scale * ball.pos.y;
      ball.radius = scale * ball.radius;
      ball.startRadius = scale * ball.startRadius;
      ball.startPos.x = ball.pos.x;
      ball.startPos.y = ball.pos.y;
    }
    
    // add balls to array
    balls = new Ball[ballsArrayList.size()];
    for (int i = ballsArrayList.size() - 1; i >= 0; i--) {
      Ball ball = ballsArrayList.get(i);
      balls[i] = ball;
    }
  }
  
  public void update(double mouseX, double mouseY) {
    Vector d = new Vector(0, 0);
    for (int i = balls.length - 1; i >= 0; i--) {
      Ball ball = balls[i];
      d.x = mouseX - ball.pos.x;
      d.y = mouseY - ball.pos.y;
      if (d.magSquared() < 100*100) {
        ball.goal = Vector.sub(ball.pos, d);
      } else {
        ball.goal.set(ball.startPos);
      }
      
      ball.update();
    }
  }
  
  public void draw(Context2d context) {
    for(int i = balls.length - 1; i >= 0; i--) {
      balls[i].draw(context);
    }
  }}