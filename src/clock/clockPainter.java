package clock;

import java.awt.*;            
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;


public class clockPainter extends Frame{

	public static void main(String[] args) throws Exception{ // 1
		clockPainter myDolly = new clockPainter("Dolly");
	    myDolly.setSize(600, 600);
	    myDolly.setVisible(true);
	    myDolly.go();
	  }

	clockPainter(String title) { // 2
	    super(title);
	    addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent ev) {
	        System.exit(0);
	      }
	    });
	  }
	  public void go() throws Exception{
	    while(true) { 
	      repaint();                    // invoke update
	      Thread.sleep(1000);
	      angle = angle + angleRotate;
	    }
	  }
	//Vars for declaring
	  int xStart = 200, yStart = 100;
	  int diameterHigh = 40, diameterMiddle = 4, diameterAround = 120, handsDiameter = 30;

	  // Vars for calculate
	  int yLow = yStart + diameterHigh + diameterMiddle;
	  int xCenter = xStart + diameterAround / 2;
	  int xHigh = xCenter - diameterHigh / 2;
	  int xMiddle = xCenter - diameterMiddle / 2, yMiddle = yStart + diameterHigh;
	  int xLeftHand = xCenter - diameterMiddle / 2 - handsDiameter;
	  int xRightHand = xCenter + diameterMiddle / 2;
	  int yHands = yStart + diameterHigh + diameterMiddle / 2 - handsDiameter / 2;
	  
	  double angleRotate = 0.05;
	  double angle = 0;
	  
	  public void update(Graphics g) { // УВАГА НЕ ЗАБУТИ ЗМІНИТИ!!!!! при анімації
	    int w = getSize().width, h = getSize().height;
	    BufferedImage bi = (BufferedImage) createImage(w, h);
	    Graphics2D big = bi.createGraphics(); // 3
	    //////////////////////////////////////////////////////
	    Line2D.Double start = new Line2D.Double(xCenter, yLow + diameterAround, xCenter, yLow);
	    Ellipse2D.Double around = new Ellipse2D.Double(xCenter - diameterAround, yLow, diameterAround * 2, diameterAround * 2);
	    Ellipse2D.Double leftHand = new Ellipse2D.Double(xLeftHand, yHands, handsDiameter, handsDiameter);
	    Ellipse2D.Double rigthHand = new Ellipse2D.Double(xRightHand, yHands, handsDiameter, handsDiameter);

	    GeneralPath clock = new GeneralPath(start);
	    clock.append(around, false);
	    
	    big.rotate(angle, xCenter, yLow + diameterAround);
	    big.draw(clock);
	    ////////////////////////////////////////
	    g.drawImage(bi, 0, 0, this);
	  }

}
