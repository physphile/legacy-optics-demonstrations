/*
 <applet code="first" width=500 height=350>
 </applet>
*/


import java.awt.*;
import java.applet.*;

public class first extends Applet
{
    double recX, recY, recZ, cosX, sinX, cosY, sinY, cosZ, sinZ, speed, stopSpeed;
    int time, xOffset, yOffset, regX, regY;
    String s;
    Graphics newG;
    Image offScreen;
    Checkbox backEBox, backHBox, backSumBox, eBox, hBox, sumBox, resHBox, resEBox,resSumBox;
    Button speedInc, speedDec, XInc, XDec, YInc, YDec, ZInc, ZDec, stopButton, stepButton, startButton;

    public void update(Graphics g)
    {
        newG.setColor(Color.gray);
        newG.fillRect(0,0,500,350);
        newG.setColor(Color.white);
        s="Speed = "+speed;
        newG.drawString(s,370,290);
        drawGraph(newG);
        drawAxes(newG);
        g.drawImage(offScreen,0,0,null);
        repaint(20);
    }

    public void init()
    {
        setLayout(null);
	stopButton = new Button ("Pause");
	add (stopButton);
	stopButton.reshape(400,250,50,20);
	stepButton = new Button ("Step");
	startButton = new Button ("Start");
	stepButton.reshape(360,230,50,20); 
	startButton.reshape(420,230,50,20);
	YInc = new Button("+");
	YDec = new Button("-");
	ZInc = new Button("+");
	ZDec = new Button("-");
	add (YInc); add (YDec); add (ZInc); add (ZDec);
	YInc.reshape(105,20,20,20); YDec.reshape(75,20,20,20);
	ZInc.reshape(20,150,20,20); ZDec.reshape(50,180,20,20);
	XInc = new Button("+");
	XDec = new Button("-");
	add (XInc); add (XDec);
	XInc.reshape(310,90,20,20); XDec.reshape(310,120,20,20);
	speedInc = new Button("+");
	add(speedInc);
	speedInc.reshape(430,300,20,20);
	speedDec = new Button("-");
	add (speedDec);
	speedDec.reshape(400,300,20,20);
        offScreen=createImage(500,350);
        newG=offScreen.getGraphics();
        setBackground(Color.gray);
        xOffset=100;
        yOffset=120;
        speed=10;
        recX=3e-1; cosX=Math.cos(recX); sinX=Math.sin(recX);
        recY=-3e-1; cosY=Math.cos(recY); sinY=Math.sin(recY);
        recZ=0; cosZ=1; sinZ=0;
        time=0;
    }

    public void paint(Graphics g)
    {
//        g.drawLine(0,381,500,381);

        update(g);
    }

    public boolean handleEvent(Event e)
    {
        if (e.target == stopButton && e.id == e.ACTION_EVENT)
	  {
	    add (stepButton); add(startButton); stopSpeed=speed; speed=0;
	    remove (stopButton); remove(speedInc); remove(speedDec);
       	    return true;
	  }else
	if (e.target == stepButton && e.id == e.ACTION_EVENT) {time++;return true;}else
      	if (e.target == startButton && e.id == e.ACTION_EVENT)
       	  {
	  	    remove (stepButton); remove (startButton);
       	    add (stopButton); speed=stopSpeed;  add(speedInc); add(speedDec);
       	    return true;
       	  }else
	if (e.target == speedInc && e.id == e.ACTION_EVENT)
	  {if (speed<50) speed++; repaint();return true;
	  }else
	if (e.target == speedDec && e.id == e.ACTION_EVENT)
	  {if (speed>0) speed--; repaint(); return true;
	  }else
	if (e.target == XDec && e.id == e.ACTION_EVENT)
	  {
	    if (recX<1) {recX=recX+1e-1; cosX=Math.cos(recX); sinX=Math.sin(recX); repaint();}return true;
	  }
	if (e.target == XInc && e.id == e.ACTION_EVENT)
	  {
        if (recX>-1){recX=recX-1e-1; cosX=Math.cos(recX); sinX=Math.sin(recX); repaint();}return true;
	  }else
	if (e.target == YInc && e.id == e.ACTION_EVENT)
	  {
	    if (recY<2) {recY=recY+1e-1; cosY=Math.cos(recY); sinY=Math.sin(recY); repaint();}return true;
	  }else
	if (e.target == YDec && e.id == e.ACTION_EVENT)
	  {
	    if (recY>-2){recY=recY-1e-1; cosY=Math.cos(recY); sinY=Math.sin(recY); repaint();}return true;
	  }else
	if (e.target == ZInc && e.id == e.ACTION_EVENT)
	  {
	    if(recZ<1){recZ=recZ+1e-1; cosZ=Math.cos(recZ); sinZ=Math.sin(recZ); repaint();}return true;
	  }else
	if (e.target == ZDec && e.id == e.ACTION_EVENT)
	  {
	    if (recZ>-1){recZ=recZ-1e-1; cosZ=Math.cos(recZ); sinZ=Math.sin(recZ); repaint();}return true;
	  }
	    return super.handleEvent(e);
    }

    public void drawMyLine(double startX, double startY, double startZ, double endX, double endY, double endZ, Graphics g)
    {
        double realStartX, realStartY, realEndX, realEndY, realStartZ, realEndZ;
        realStartX=startX;
        realStartY=startY*cosX-startZ*sinX;
        realStartZ=startY*sinX+startZ*cosX;
        realEndX=endX;
        realEndY=endY*cosX-endZ*sinX;
        realEndZ=endY*sinX+endZ*cosX;
        realStartX=realStartX*cosY+realStartZ*sinY;
        realStartZ=-realStartZ*sinY+realStartZ*cosY;
        realEndX=realEndX*cosY+realEndZ*sinY;
        realEndZ=-realEndZ*sinY+realEndZ*cosY;
        realStartX=realStartX*cosZ-realStartY*sinZ;
        realStartY=realStartX*sinZ+realStartY*cosZ;
        realEndX=realEndX*cosZ-realEndY*sinZ;
        realEndY=realEndX*sinZ+realEndY*cosZ;
//        realStartX=realStartX/2-50;
//        realStartY=realStartY/2-50;
//        realEndX=realEndX/2-50;
//        realEndY=realEndY/2-50;
//        realStartX=realStartX/(300-realStartZ)*600;
//        realStartY=realStartY/(300-realStartZ)*600;
//        realEndX=realEndX/(300-realEndZ)*600;
//        realEndY=realEndY/(300-realEndZ)*600;
        g.drawLine( (int) realStartX+xOffset, (int) -realStartY+yOffset, (int) realEndX+xOffset, (int) -realEndY+yOffset);
        regX=(int)realEndX+xOffset;
        regY=(int)-realEndY+yOffset;
    }

    public void drawGraph(Graphics g)
    {
        int firstX, firstY, secondX, secondY;
	double minEn, maxEn,tmpEn;
        time=time+(int)speed;
        if (time>359) time=time-360;
        double oldY, newY;
        oldY=0; minEn=0; maxEn=0;
        for (double i=1; i<200; i=i+1)
        {
            newY=Math.cos(((double)time-i*4)/180*Math.PI);
            g.setColor(Color.green);
            drawMyLine(i, 0, 0, i, newY*60, 0, g);
            firstX=regX;
            firstY=regY;
            g.setColor(Color.yellow);
            drawMyLine(i, 0, 0, i, 0, newY*60, g);
            secondX=regX;
            secondY=regY;
		tmpEn=newY*newY*70;
	    if (i != 1){
            g.setColor(Color.red);
            g.drawLine((int)i+99,339-(int)(oldY*oldY*70),(int)i+100, 339-(int)(tmpEn));
	    if (tmpEn > maxEn) maxEn=tmpEn; else
		if (tmpEn < minEn) minEn=tmpEn;
	    }else {minEn=tmpEn; maxEn=tmpEn;}
            if (i == 80)
            {
                g.setColor(Color.white);
                g.fillOval((int)i+96, 336-(int)(newY*newY*70), 8, 8);
                g.fillOval(firstX-4,firstY-4,8,8);
                g.fillOval(secondX-4,secondY-4,8,8);
            }
            oldY=newY;
        }
	g.setColor(Color.white);
	g.drawLine(100,339-(int)Math.round((maxEn+minEn)/2),300,339-(int)Math.round((maxEn+minEn)/2));

        newY=Math.cos(((double)time-80*4)/180*Math.PI);
        g.setColor(Color.green);
        g.drawLine(419,120,419,120-(int)(newY*70));
        g.drawLine(420,120,420,120-(int)(newY*70));
        g.drawLine(421,120,421,120-(int)(newY*70));
        g.setColor(Color.yellow);
        g.drawLine(420,119,420-(int)(newY*70),119);
        g.drawLine(420,120,420-(int)(newY*70),120);
        g.drawLine(420,121,420-(int)(newY*70),121);
    }

    public void drawAxes(Graphics g)
    {
        g.setColor(Color.black);
        drawMyLine(0,0,0,200,0,00,g);
        drawMyLine(0,0,0,0,100,0,g);
        drawMyLine(0,0,0,0,0,100,g);
        g.drawOval(350,50,140,140);
        g.drawLine(345,120,495,120);
        g.drawLine(420,45,420,195);
        g.drawLine(100,220,100,340);
        g.drawLine(100,340,300,340);
        g.setColor(Color.red);
        g.drawLine(180,240,180,340);
        drawMyLine(80,-70,0,80,70,0,g);
        drawMyLine(80,0,-70,80,0,70,g);
	g.setColor(Color.green);
	g.drawLine(40,50,40,100);
	g.drawString("E",30,55);
	g.setColor(Color.yellow);
	g.drawLine(40,100,10,130);
	g.drawString("H",1,130);
	g.setColor(Color.black);
	g.drawLine(40,100,80,100);
	g.drawString("k",75,95);
	g.setColor(Color.white);
	g.drawString("S",90,230);
	g.drawString("<S>",75,307);
	g.drawString("x",340,115);
	g.drawString("z",425,45);
	g.drawString("z",45,55);
	g.drawString("x",15,130);
	g.drawString("y",75,115);
	g.drawString("0",45,115);
	g.drawString("0",96,345);
	g.drawString("y",290,345);

    }
}
