/*
 <applet code="first" width=500 height=350>
 </applet>
*/


import java.awt.*;
import java.applet.*;

public class TwoWave10 extends Applet
{
    double recX, recY, recZ, cosX, sinX, cosY, sinY, cosZ, sinZ, phasa, recBet, ampl;
    int time, xOffset, yOffset, linePosition, regX, regY, speedOne, speedStop, redDotCounter;
    String s;
    int resultArrayX []=new int[60];
    int resultArrayY []=new int[60];
    int arrayNumber;
    boolean starting, lineMoving;
    Graphics newG;
    Image offScreen;
  //    Checkbox backEBox, backHBox, backSumBox, eBox, hBox, sumBox, resHBox, resEBox,resSumBox;
    Button speedInc, speedDec, XInc, XDec, YInc, YDec, ZInc, ZDec, recInc, recDec, phasaInc, phasaDec, amplInc, amplDec, stopButton, stepButton, startButton;


    public void update(Graphics g)
    {
        newG.setColor(Color.gray);
        newG.fillRect(0,0,500,360);
        newG.setColor(Color.white);
        s="rec = "+recBet;
        newG.drawString(s,320,215);//speedOne +/-
        s="Phasa = "+phasa;
        newG.drawString(s,320,255);//Phasing +/-
	//	s="Amp2 ="+(double)((int)(ampl*100))/100+"xAmp1";
	s="Amp2 ="+(double)(Math.round(ampl*100)) /100+"xAmp1";
	newG.drawString(s,320,295);
	s="Speed ="+speedOne;
	newG.drawString(s,320,335);
        drawGraph(newG);
        drawAxes(newG);
        g.drawImage(offScreen,0,0,null);
        repaint(20);
    }
    public void init()
    {
//        regularPoint=new Point();
        redDotCounter=0;
        setLayout(null);
	stopButton = new Button ("Pause");
	add (stopButton);
	stopButton.reshape(430,220,50,20);
	stepButton = new Button ("Step");
	startButton = new Button ("Start");
	stepButton.reshape(380,260,50,20); 
	startButton.reshape(440,260,50,20);
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
	speedInc.reshape(350,340,20,20);
	speedDec = new Button("-");
	add (speedDec);
	speedDec.reshape(320,340,20,20);
	recInc = new Button("+"); recDec = new Button("-"); phasaInc = new Button("+");
	phasaDec = new Button("-"); amplInc = new Button("+"); amplDec = new Button("-");
	add(recInc); add (recDec); add (phasaInc); add (phasaDec); add (amplInc); add(amplDec);
	recDec.reshape(320,220,20,20); recInc.reshape(350,220,20,20);
	phasaDec.reshape(320,260,20,20); phasaInc.reshape(350,260,20,20);
	amplDec.reshape(320,300,20,20); amplInc.reshape(350,300,20,20);
        arrayNumber=0;
        offScreen=createImage(500,360);
        newG=offScreen.getGraphics();
        setBackground(Color.gray);
        xOffset=100;
        yOffset=120;
        phasa=90;
	ampl=1;
        linePosition=80;
        lineMoving=false;
        speedOne=6;
/*        recX=0; cosX=1; sinX=0;
        recY=0; cosY=1; sinY=0;
        recZ=0; cosZ=1; sinZ=0;
*/
        recX=3e-1; cosX=Math.cos(recX); sinX=Math.sin(recX);
        recY=-3e-1; cosY=Math.cos(recY); sinY=Math.sin(recY);
        recZ=0; cosZ=1; sinZ=0;
        time=0;
        recBet=90;
    }

    public boolean handleEvent(Event e)
    {
        if (e.target == stopButton && e.id == e.ACTION_EVENT)
	  {
	    speedStop=speedOne; speedOne=0;
	    remove (stopButton); remove(speedInc); remove(speedDec);
	    add (stepButton); add(startButton); 
       	    return true;
	  }else
	if (e.target == stepButton && e.id == e.ACTION_EVENT) {time++; redDotCounter++; return true;}else
      	if (e.target == startButton && e.id == e.ACTION_EVENT)
       	  {
	  	    remove (stepButton); remove (startButton);
       	    add (stopButton); add(speedInc); add(speedDec);
	    speedOne=speedStop;
       	    return true;
       	  }else
	if (e.target == speedInc && e.id == e.ACTION_EVENT)
	  {if (speedOne<50) speedOne++; repaint();return true;
	  }else
	if (e.target == speedDec && e.id == e.ACTION_EVENT)
	  {if (speedOne>0) speedOne--; repaint(); return true;
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
	  }else
	if (e.target == recInc && e.id == e.ACTION_EVENT)
	  {
	    if (recBet<=180){recBet=recBet+5; arrayNumber=0; repaint();}return true;
	  }else
	if (e.target == recDec && e.id == e.ACTION_EVENT)
	  {
	    if (recBet>=-180){recBet=recBet-5; arrayNumber=0; repaint();}return true;
	  }else
	if (e.target == phasaInc && e.id == e.ACTION_EVENT)
	  {
	    if (phasa<=360){phasa=phasa+5; arrayNumber=0; repaint();}return true;
	  }else
	if (e.target == phasaDec && e.id == e.ACTION_EVENT)
	  {
	    if (phasa>=-360){phasa=phasa-5; arrayNumber=0; repaint();}return true;
	  }
	if (e.target == amplInc && e.id == e.ACTION_EVENT)
	  {
	    if (ampl<2){ampl=ampl+5e-2; arrayNumber=0; repaint();}return true;
	  }else
	if (e.target == amplDec && e.id == e.ACTION_EVENT)
	  {
	    if (ampl>0){ampl -=5e-2; arrayNumber=0; repaint();}return true;
	  }
	    return super.handleEvent(e);
    }



    public void paint(Graphics g)
    {
        g.drawLine(0,381,500,381);

        update(g);
    }

    public boolean mouseUp(java.awt.Event evt, int x, int y)
    {

        if ((x-100>linePosition-10)&(x-100<linePosition+10)&(y>270)&(y<340))
        {lineMoving=false;};
        return true;
    }

    public boolean mouseDown(java.awt.Event evt, int x, int y)
    {
        if ((x-100>linePosition-10)&(x-100<linePosition+10)&(y>240)&(y<340))
            {lineMoving=true;};
        return true;
    }

    public boolean mouseDrag(java.awt.Event evt, int x, int y)
    {
        if ((lineMoving)&&(x>100)&&(x<300)) linePosition=x-100;
        return true;
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
/*        realStartX=realStartX/2-50;
        realStartY=realStartY/2-50;
        realEndX=realEndX/2-50;
        realEndY=realEndY/2-50;
        realStartX=realStartX/(300-realStartZ)*600;
        realStartY=realStartY/(300-realStartZ)*600;
        realEndX=realEndX/(300-realEndZ)*600;
        realEndY=realEndY/(300-realEndZ)*600;
*/

        g.drawLine( (int) realStartX+xOffset, (int) -realStartY+yOffset, (int) realEndX+xOffset, (int) -realEndY+yOffset);
        regX=(int) realEndX+xOffset;
        regY=(int) -realEndY+yOffset;
    }

    public void drawGraph(Graphics g)
    {
        int firstX, secondX, firstY, secondY;
        time = time +speedOne;
	redDotCounter+=speedOne;
        if (time>359){ time=0; starting=false;}
        double oldY, newY, oldBack, newBack,ddZ,ddY, circSecondX,circSecondY, circResultX, circResultY, tmpEn, maxEn, minEn;
	minEn=0; maxEn=0;
        ddZ=Math.sin(recBet/180*Math.PI);
        ddY=Math.cos(recBet/180*Math.PI);
        oldY=0;oldBack=0;
        for (double i=1; i<200; i=i+1)
        {
            newY=Math.cos(((double)time-i*4)/180*Math.PI);
            newBack=Math.cos(((double)time+phasa-i*4)/180*Math.PI)*ampl;
            g.setColor(Color.green);
            drawMyLine(i-1, oldY*60, 0, i, newY*60, 0, g);
            firstX=regX;
            firstY=regY;
            g.setColor(Color.blue);
            drawMyLine(i-1, oldBack*ddY*60, oldBack*ddZ*60, i, newBack*ddY*60, newBack*ddZ*60, g);
            secondX=regX;
            secondY=regY;
	    tmpEn=(newY+newBack*ddY)*(newY+newBack*ddY)*20+(newBack*ddZ)*(newBack*ddZ)*20;
        if (i != 1){
            g.setColor(Color.black);
            g.drawLine((int)i+99,339-(int)((oldY+oldBack*ddY)*(oldY+oldBack*ddY)*20+(oldBack*ddZ)*(oldBack*ddZ)*20),(int)i+100, 339-(int)(tmpEn));
//            g.drawLine((int)i+99,339-(int)(oldY*oldY*40+oldBack*oldBack*40),(int)i+100, 339-(int)(newY*newY*40+newBack*newBack*40));
	    if (tmpEn > maxEn) maxEn=tmpEn; else
		if (tmpEn < minEn) minEn=tmpEn;
	    }else {minEn=tmpEn; maxEn=tmpEn;}
            if (i == linePosition)
            {
                g.setColor(Color.white);
                g.fillOval((int)i+96, 336-(int)((oldY+oldBack*ddY)*(oldY+oldBack*ddY)*20+(oldBack*ddZ)*(oldBack*ddZ)*20), 8, 8);
//                g.fillOval((int)i+96, 336-(int)(oldY*oldY*40+oldBack*oldBack*40), 8, 8);
                g.fillOval(firstX-4,firstY-4,8,8);
                g.fillOval(secondX-4,secondY-4,8,8);
            }
	    //	}else {minEn=tmpEn; maxEn=tmpEn;}
            oldBack=newBack;
            oldY=newY;
            if (i%3==0)
            {
                g.setColor(Color.green);
                drawMyLine(i, 0, 0, i, newY*60, 0, g);
                g.setColor(Color.blue);
                drawMyLine(i-1, 0, 0, i-1, newBack*ddY*60, newBack*ddZ*60, g);
            }



        }
	g.setColor(Color.white);
	g.drawLine(100,339-(int)((maxEn+minEn)/2),300,339-(int)((maxEn+minEn)/2));
        newY=Math.cos(((double)time-linePosition*4)/180*Math.PI);
        g.setColor(Color.green);
        g.drawLine(419,120,419,120-(int)(newY*70));
        g.drawLine(420,120,420,120-(int)(newY*70));
        g.drawLine(422,120,422,120-(int)(newY*70));
        newBack=Math.cos(((double)time+phasa-linePosition*4)/180*Math.PI)*ampl;
        circSecondX=newBack*70.0*Math.sin(recBet/180*Math.PI);
        circSecondY=newBack*70.0*Math.cos(recBet/180*Math.PI);
        g.setColor(Color.blue);
        g.drawLine(419,119,419-(int)circSecondX,119-(int)circSecondY);
        g.drawLine(420,120,420-(int)circSecondX,120-(int)circSecondY);
        g.drawLine(418,118,418-(int)circSecondX,118-(int)circSecondY);
        circResultX=newBack*70.0*Math.sin(recBet/180*Math.PI);
        circResultY=(circSecondY+newY*70);

	if (redDotCounter >= 10) 
    { redDotCounter = 0;
        if (arrayNumber<60)
        {
            resultArrayX[arrayNumber]=(int)circResultX;
            resultArrayY[arrayNumber]=(int)circResultY;
            arrayNumber++;
        }
        else
        {
            for (int tmp=0; tmp<59; tmp++)
            {
                resultArrayX[tmp]=resultArrayX[tmp+1];
                resultArrayY[tmp]=resultArrayY[tmp+1];
            }
            resultArrayX[59]=(int)circResultX;
            resultArrayY[59]=(int)circResultY;
        }
    }


        g.setColor(Color.red);
        for (int tmp=0; tmp<arrayNumber-1; tmp++) g.fillOval(418-resultArrayX[tmp],118-resultArrayY[tmp],5,5);
        g.drawLine(419,119,419-(int)circResultX,119-(int)circResultY);
        g.drawLine(420,120,420-(int)circResultX,120-(int)circResultY);
        g.drawLine(421,121,421-(int)circResultX,121-(int)circResultY);
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
        g.drawLine(100+linePosition,240,100+linePosition,340);
        drawMyLine(linePosition,-70,0,linePosition,70,0,g);
        drawMyLine(linePosition,0,-70,linePosition,0,70,g);
	g.setColor(Color.green);
	g.drawLine(40,55,40,105);
	g.drawString("E1",25,60);
	g.setColor(Color.blue);
	g.drawLine(40,105,20,80);
	g.drawString("E2",10,80);
	g.setColor(Color.black);
	g.drawLine(40,105,10,135);
	g.drawLine(40,105,80,105);
	g.drawString("k",75,104);
	g.setColor(Color.white);
	g.drawString("0",96,350);
	g.drawString("y",290,350);
	g.drawString("S",90,230);
	g.drawString("<S>",75,310);
	g.drawString("x",340,115);
	g.drawString("z",425,45);
	g.drawString("z",45,60);
	g.drawString("x",19,133);
	g.drawString("y",75,115);
	g.drawString("0",45,115);
	g.setColor(Color.black);
	g.drawString("=",400,15);
	g.drawString("+",430,15);
	g.setColor(Color.green);
	g.drawString("E1",410,15);
	g.setColor(Color.blue);
	g.drawString("E2",440,15);
	g.setColor(Color.red);
	g.drawString("E",390,15);
    }
}
