/*
 <applet code="first" width=500 height=350>
 </applet>
*/


import java.awt.*;
import java.applet.*;

public class Cristall extends Applet
{
    double recX, recY, recZ, cosX, sinX, cosY, sinY, cosZ, sinZ, phasa, phasa2, recBet, recCristall, ampl;
    int time, xOffset, yOffset, linePosition, regX, regY, redDotCounter, yellowDotCounter, speedOne, speedStop;
    String s;
    int resultArrayX []=new int[60];
    int resultArrayY []=new int[60];
    int resultAfterX []=new int[60];
    int resultAfterY []=new int[60];
    int arrayNumber;
    boolean starting, lineMoving;
    Graphics newG;
    Image offScreen;
    Checkbox backEBox, backHBox, backSumBox, eBox, hBox, sumBox, resHBox, resEBox,resSumBox;
    Button speedInc, speedDec, XInc, XDec, YInc, YDec, ZInc, ZDec, recInc, recDec, phasaInc, phasaDec, amplInc, amplDec, stopButton, stepButton, startButton, recCristallInc, recCristallDec, thickCristallInc, thickCristallDec;
    String listCristallThick[] = { "0", "1/16", "1/8", "3/16", "1/4", "5/16", "3/8", "7/16", "1/2", "9/16", "5/8", "11/16", "3/4", "13/16", "7/8", "15/16", "1"};

    public void update(Graphics g)
    {
        g.drawImage(offScreen,0,0,null);
        newG.setColor(Color.gray);
        newG.fillRect(0,0,650,360);
        newG.setColor(Color.white);
        s="rec = "+recBet;
        newG.drawString(s,320,215);//speedOne +/-
        s="Phasa = "+phasa;
        newG.drawString(s,320,255);//Phasing +/-
	s="Amp2 ="+(double)(Math.round(ampl*100))/100+"xAmp1";
	newG.drawString(s,320,295);
	s="Speed ="+speedOne;
	newG.drawString(s,320,335);
	s = "crystal rec ="+(int)recCristall;
	newG.drawString(s,450,215);
if (phasa2 >=0)
	s = "thick= "+listCristallThick[(int)(phasa2/22.5)]+" lambda";
else s = "thick= - "+listCristallThick[(int)(-phasa2/22.5)]+" lambda";

	newG.drawString(s,450,255);
	drawGraph(newG);
        drawAxes(newG);
        repaint(20);
    }
    public void init()
    {   
	redDotCounter = 0;
	yellowDotCounter = 0;
	setLayout(null);
	stopButton = new Button ("Pause");
	add (stopButton);
	stopButton.reshape(450,290,50,20);
	stepButton = new Button ("Step");
	startButton = new Button ("Start");
	stepButton.reshape(420,320,50,20); 
	startButton.reshape(480,320,50,20);
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
	recCristallDec = new Button("-"); recCristallInc = new Button("+");
	thickCristallInc = new Button("+"); thickCristallDec = new Button("-");
	add(recCristallInc); add(recCristallDec); add(thickCristallInc); add(thickCristallDec);
	recCristallInc.reshape(480, 220, 20, 20);
	recCristallDec.reshape(450, 220, 20, 20);
	thickCristallInc.reshape(480,260,20, 20);
	thickCristallDec.reshape(450,260,20, 20);
        arrayNumber=0;
        offScreen=createImage(650,360);
        newG=offScreen.getGraphics();
        setBackground(Color.gray);
        xOffset=100;
        yOffset=120;
        phasa=90;
	phasa2=45;
        linePosition=80;
        lineMoving=false;
        speedOne=6;
        recX=3e-1; cosX=Math.cos(recX); sinX=Math.sin(recX);
        recY=-5e-1; cosY=Math.cos(recY); sinY=Math.sin(recY);
        recZ=0; cosZ=1; sinZ=0;
        time=0;
        recBet=90;
	recCristall=30;
	ampl = 1;
    }

    public void paint(Graphics g)
    {
        g.drawLine(0,381,500,381);
        update(g);
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
	  }else
	if (e.target == recCristallInc && e.id == e.ACTION_EVENT)
	  {
	      if (recCristall<360){recCristall +=5; arrayNumber=0; repaint();}return true;
	  }else
	if (e.target == recCristallDec && e.id == e.ACTION_EVENT)
	  {
	      if (recCristall>-360){recCristall -=5; arrayNumber=0; repaint();}return true;
	  }else
	if (e.target == thickCristallDec && e.id == e.ACTION_EVENT)
	  {
	      if (phasa2>-360){phasa2 -=22.5; arrayNumber =0; repaint();}return true;
	  }else
        if (e.target == thickCristallInc && e.id == e.ACTION_EVENT)
	  {
	      if (phasa2 < 360){phasa2 +=22.5; arrayNumber =0; repaint();}return true;
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
        g.drawLine( (int) realStartX+xOffset, (int) -realStartY+yOffset, (int) realEndX+xOffset, (int) -realEndY+yOffset);
        regX=(int) realEndX+xOffset;
        regY=(int) -realEndY+yOffset;
    }

    public void drawGraph(Graphics g)
    {
        int firstX, secondX, firstY, secondY;
        time = time + speedOne;
	redDotCounter +=speedOne;

        if (time>359){ time=0; starting=false;}
        double oldY, newY, oldBack, newBack,ddZ,ddY, circSecondX,circSecondY, circResultX, circResultY, alpha, betta, crisEY, crisEBack,sinCr,cosCr,sinCrBet,cosCrBet, newEnPad, minEnPad, maxEnPad, newEnProsh, minEnProsh, maxEnProsh;
	minEnProsh=0; maxEnProsh=0; minEnPad=0; maxEnPad=0;
        ddZ=Math.sin(recBet/180*Math.PI);
        ddY=Math.cos(recBet/180*Math.PI);
	sinCr=Math.sin(recCristall/180*Math.PI);
	cosCr=Math.cos(recCristall/180*Math.PI);
	sinCrBet=Math.sin((recCristall-recBet)/180*Math.PI);
	cosCrBet=Math.cos((recCristall-recBet)/180*Math.PI);
        oldY=0;oldBack=0;circResultX=0;circResultY=0;
        for (double i=1; i<80; i=i+1)
        {
            newY=Math.cos(((double)time-i*6)/180*Math.PI);
            newBack=Math.cos(((double)time+phasa-i*6)/180*Math.PI)*ampl;
            circResultX=newBack*70.0*ddZ;
	    circSecondY=newBack*70.0*ddY;
            circResultY=(circSecondY+newY*70);
	    circSecondX=oldBack*70*ddZ;
	    circSecondY=oldBack*70*ddY+oldY*70;
	    newEnPad=(newY+newBack*ddY)*(newY+newBack*ddY)*20+(newBack*ddZ)*(newBack*ddZ)*20;

	    if (i ==1){oldY=newY; oldBack=newBack; minEnPad=newEnPad; maxEnPad=newEnPad;}
	    else {if(newEnPad > maxEnPad) maxEnPad=newEnPad; else if(newEnPad < minEnPad) minEnPad = newEnPad;}

	    g.setColor(Color.red);
	    drawMyLine(i-1,(int)circSecondY,(int)circSecondX,i,(int)circResultY,(int)circResultX,g);
	    if (i%3 == 0)
	      {
		drawMyLine(i,0,0,i,(int)circResultY,(int)circResultX,g);
	      }

            g.setColor(Color.black);
            g.drawLine((int)i+99,339-(int)((oldY+oldBack*ddY)*(oldY+oldBack*ddY)*20+(oldBack*ddZ)*(oldBack*ddZ)*20),(int)i+100, 339-(int)(newEnPad));
	    oldBack=newBack;
	    oldY=newY;

        }
	g.setColor(Color.white);
	maxEnPad=(maxEnPad+minEnPad)/2;
	g.drawLine(100,339-(int)Math.round(maxEnPad),180,339-(int)Math.round(maxEnPad));
	//	alpha=Math.atan(circResultX/circResultY);
	//	if (circResultY < 0) alpha=alpha+Math.PI;
	//	betta=recCristall-alpha/Math.PI*180;

	circSecondY=0;
	circSecondX=0;
	for (double i=90; i<200; i++)
	{
            newY=Math.cos(((double)time-i*6)/180*Math.PI)*cosCr;
            newBack=Math.cos(((double)time+phasa-i*6)/180*Math.PI)*cosCrBet*ampl;
	    crisEY = Math.cos(((double)time+phasa2-i*6)/180*Math.PI)*sinCr;
	    crisEBack = Math.cos(((double)time+phasa+phasa2-i*6)/180*Math.PI)*sinCrBet*ampl;
	    
            circResultX=(newBack+newY)*70.0*cosCr+(crisEY+crisEBack)*70*sinCr;
	    //	    circSecondY=newBack*70.0*ddY;
            circResultY=(newBack+newY)*70.0*sinCr-(crisEY+crisEBack)*70*cosCr;
	    newEnProsh=circResultX*circResultX/4900*20+circResultY*circResultY/4900*20;
	    if (i == 90) {circSecondX = circResultX; circSecondY = circResultY; minEnProsh=newEnProsh; maxEnProsh=newEnProsh;}
	    else{
	    g.setColor(Color.black);
            g.drawLine((int)i+99,339-(int)(circSecondX*circSecondX/4900*20+circSecondY*circSecondY/4900*20),(int)i+100, 339-(int)(newEnProsh));

if(newEnProsh >maxEnProsh) maxEnProsh=newEnProsh; else if (newEnProsh < minEnProsh) minEnProsh=newEnProsh;}
	    //	    circSecondX=oldBack*70*ddZ;
	    //	    circSecondY=oldBack*70*ddY+oldY*70;
	    g.setColor(Color.yellow);
	    drawMyLine(i-1,(int)circSecondX,(int)circSecondY,i,(int)circResultX,(int)circResultY,g);
	    if (i%3 == 0)
	      {
		drawMyLine(i,0,0,i,(int)circResultX,(int)circResultY,g);
	      }

	    circSecondX=circResultX; circSecondY=circResultY;
	}

	maxEnProsh=(maxEnProsh+minEnProsh)/2;
	g.setColor(Color.white);
	g.drawLine(190,339-(int)Math.round(maxEnProsh),300,339-(int)Math.round(maxEnProsh));
	//	tmmmp="rec "+(int)(recCristall-recBet);
	//	g.drawString(tmmmp,200,200);

	//draw second circle...
        newY=Math.cos(((double)time-90*6)/180*Math.PI)*cosCr;
        newBack=Math.cos(((double)time+phasa-90*6)/180*Math.PI)*cosCrBet*ampl;
        crisEY = Math.cos(((double)time+phasa2-90*6)/180*Math.PI)*sinCr;
        crisEBack = Math.cos(((double)time+phasa+phasa2-90*6)/180*Math.PI)*sinCrBet*ampl;

	circSecondX = newY+newBack;
      	circSecondY = crisEY+crisEBack;
        circResultX=circSecondX*cosCr;
        circResultY=circSecondX*sinCr;
        g.setColor(Color.green);
        g.drawLine(569,120,569-(int)(70*circResultY),120-(int)(circResultX*70));
        g.drawLine(570,120,570-(int)(70*circResultY),120-(int)(circResultX*70));
        g.drawLine(571,120,571-(int)(70*circResultY),120-(int)(circResultX*70));
        circResultX=circSecondY*70*sinCr;
        circResultY=circSecondY*70.0*cosCr;
        g.setColor(Color.blue);
        g.drawLine(569,119,569+(int)circResultY,120-(int)circResultX);
        g.drawLine(570,120,570+(int)circResultY,120-(int)circResultX);
        g.drawLine(568,118,568+(int)circResultY,120-(int)circResultX);

	//        circResultX=newBack*70.0*Math.sin(recBet/180*Math.PI);
	//        circSecondY=newBack*70.0*Math.cos(recBet/180*Math.PI);
        circResultX=(newBack+newY)*70.0*cosCr+(crisEY+crisEBack)*70*sinCr;

        circResultY=(newBack+newY)*70.0*sinCr-(crisEY+crisEBack)*70*cosCr;
	//        circResultY=(circSecondY+newY*70);
	if (redDotCounter >= 10)
    {
        if (arrayNumber<60)
        {
            resultAfterY[arrayNumber]=(int)circResultX;
            resultAfterX[arrayNumber]=(int)circResultY;
	    //            arrayNumber++;
        }
        else
        {
            for (int tmp=0; tmp<59; tmp++)
            {
                resultAfterX[tmp]=resultAfterX[tmp+1];
                resultAfterY[tmp]=resultAfterY[tmp+1];
            }
            resultAfterY[59]=(int)circResultX;
            resultAfterX[59]=(int)circResultY;
        }
    }
        g.setColor(Color.yellow);
        for (int tmp=0; tmp<arrayNumber-1; tmp++) g.fillOval(568-resultAfterX[tmp],118-resultAfterY[tmp],5,5);
        g.drawLine(569,119,569-(int)circResultY,119-(int)circResultX);
        g.drawLine(570,120,570-(int)circResultY,120-(int)circResultX);
        g.drawLine(571,121,571-(int)circResultY,121-(int)circResultX);


	//draw First circle...

        newY=Math.cos(((double)time-80*6)/180*Math.PI);
        newBack=Math.cos(((double)time+phasa-80*6)/180*Math.PI)*ampl;
	circSecondX = newY*cosCr+newBack*cosCrBet;
      	circSecondY = newY*sinCr+newBack*sinCrBet;
        circResultX=circSecondX*cosCr;
        circResultY=circSecondX*sinCr;
        g.setColor(Color.green);
        g.drawLine(419,120,419-(int)(70*circResultY),120-(int)(circResultX*70));
        g.drawLine(420,120,420-(int)(70*circResultY),120-(int)(circResultX*70));
        g.drawLine(421,120,421-(int)(70*circResultY),120-(int)(circResultX*70));
        circResultX=circSecondY*70*sinCr;
        circResultY=circSecondY*70.0*cosCr;
        g.setColor(Color.blue);
        g.drawLine(419,119,419+(int)circResultY,120-(int)circResultX);
        g.drawLine(420,120,420+(int)circResultY,120-(int)circResultX);
        g.drawLine(418,118,418+(int)circResultY,120-(int)circResultX);
        circResultX=newBack*70.0*Math.sin(recBet/180*Math.PI);
        circSecondY=newBack*70.0*Math.cos(recBet/180*Math.PI);
        circResultY=(circSecondY+newY*70);
	if (redDotCounter >= 10)
      {redDotCounter =0;
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
        double cristCos,cristSin;
        cristCos=Math.cos(recCristall/180*Math.PI);
	cristSin=Math.sin(recCristall/180*Math.PI);
	int yCo = (int)(85*Math.sin((recCristall+45)/180*Math.PI));
	int xCo = (int)(85*Math.cos((recCristall+45)/180*Math.PI));
        g.setColor(Color.black);
        drawMyLine(0,0,0,200,0,00,g);
        drawMyLine(0,0,0,0,100,0,g);
        drawMyLine(0,0,0,0,0,100,g);
        g.drawOval(500,50,140,140);
        g.drawLine(495,120,645,120);
        g.drawLine(570,45,570,195);
        g.drawOval(350,50,140,140);
        g.drawLine(345,120,495,120);
        g.drawLine(420,45,420,195);
        g.drawLine(100,220,100,340);
        g.drawLine(100,340,300,340);
	g.setColor(Color.white);
	for (int count=180; count<=190; count+=2)
	g.drawLine(count,340,count,240);
	g.drawLine(180,240,190,240);
	g.drawLine(570,120,570-(int)(75*cristSin),120-(int)(75*cristCos));
	g.drawLine(420,120,420-(int)(75*cristSin),120-(int)(75*cristCos));


drawMyLine(85,0,0,85,(int)(100*cristCos),(int)(100*cristSin),g);

	g.setColor(Color.white);
	drawMyLine(80,xCo,yCo, 90,yCo, -xCo,g);
	drawMyLine(80, yCo, -xCo, 90, -xCo, -yCo,g);
	drawMyLine(80,-xCo,-yCo,90,-yCo,xCo,g);
	drawMyLine(80,-yCo,xCo,90,xCo,yCo,g);
	drawMyLine(90,xCo,yCo, 80,yCo, -xCo,g);
	drawMyLine(90, yCo, -xCo, 80, -xCo, -yCo,g);
	drawMyLine(90,-xCo,-yCo,80,-yCo,xCo,g);
	drawMyLine(90,-yCo,xCo,80,xCo,yCo,g);
	drawMyLine(80,xCo,yCo, 90,xCo, yCo,g);
	drawMyLine(80, yCo, -xCo, 90, yCo, -xCo,g);
	drawMyLine(80,-xCo,-yCo,90,-xCo,-yCo,g);
	drawMyLine(80,-yCo,xCo,90,-yCo,xCo,g);

	drawMyLine(80,xCo,yCo, 80,yCo, -xCo,g);
	drawMyLine(80, yCo, -xCo, 80, -xCo, -yCo,g);
	drawMyLine(80,-xCo,-yCo,80,-yCo,xCo,g);
	drawMyLine(80,-yCo,xCo,80,xCo,yCo,g);
	drawMyLine(90,xCo,yCo, 90,yCo, -xCo,g);
	drawMyLine(90, yCo, -xCo, 90, -xCo, -yCo,g);
	drawMyLine(90,-xCo,-yCo,90,-yCo,xCo,g);
	drawMyLine(90,-yCo,xCo,90,xCo,yCo,g);
g.setColor(Color.black);
drawMyLine(85,0,0,85,(int)(100*cristSin),-(int)(100*cristCos),g);
	g.drawLine(570,120,570+(int)(75*cristCos),120-(int)(75*cristSin));
	g.drawLine(420,120,420+(int)(75*cristCos),120-(int)(75*cristSin));

	g.drawLine(40,55,40,105);
	g.drawLine(40,105,10,135);
	g.drawLine(40,105,80,105);

	g.drawString("k",75,104);
	g.setColor(Color.white);
	g.drawString("0",96,350);
	g.drawString("y",290,350);
	g.drawString("S",90,230);
	g.drawString("<S>",80,310);
	g.drawString("x",340,115);
	g.drawString("z",425,45);
	g.drawString("z",45,60);
	g.drawString("x",19,133);
	g.drawString("y",75,115);
	g.drawString("0",40,115);
	g.drawString("x",495,115);
	g.drawString("z",575,45);
	g.setColor(Color.red);
	g.drawString("E in",410,30);
	g.setColor(Color.yellow);
	g.drawString("E out",555,30);
    }
}
