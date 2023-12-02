/*
 <applet code="first" width=500 height=350>
 </applet> javabottom
*/


import java.awt.*;
import java.applet.*;
import java.net.URL;

public class StayWave10 extends Applet
{
    double recX, recY, recZ, cosX, sinX, cosY, sinY, cosZ, sinZ, speedRight, waveRight, waveLeft;
    int time, xOffset, yOffset, speedLeft, linePosition, regX, regY, stopSpeed;
    String s;
    boolean starting, lineMoving;
    Checkbox vectorE, vectorH, vectorResult, resE, resS;
    Graphics newG;
    Image offScreen;
    Checkbox backEBox, backHBox, backSumBox, eBox, hBox, sumBox, resHBox, resEBox,resSumBox;
    Button speedInc, speedDec, XInc, XDec, YInc, YDec, ZInc, ZDec, stopButton, stepButton, startButton;
    URL vectorEURL, vectorHURL, vectorResultURL;

    public void update(Graphics g)
    {
        newG.setColor(Color.gray);
        newG.fillRect(0,0,500,350);
        newG.setColor(Color.white);
        s="Speed = "+speedLeft;
        newG.drawString(s,370,295);//SpeedLeft +/-
        if (vectorE.getState()) {remove(resE); remove(resS); starting=false; drawGraphE(newG);}
        else if (vectorH.getState()) {remove(resE); remove(resS); starting=false;drawGraphH(newG);}
        else {if (!starting) {add(resE); add(resS);starting=true;} drawGraphResult(newG);}
//        if (!starting) drawGraph(newG);
//        else drawStartGraph(newG);
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
        speedLeft=10;
        speedRight=10;
        linePosition=80;
        lineMoving=false;
        recX=3e-1; cosX=Math.cos(recX); sinX=Math.sin(recX);
        recY=-3e-1; cosY=Math.cos(recY); sinY=Math.sin(recY);
        recZ=0; cosZ=1; sinZ=0;
        time=0;
        waveRight=4;
        waveLeft=4;
        starting=false;
        setLayout(null);
        CheckboxGroup g = new CheckboxGroup();
        vectorE = new Checkbox("Vector E", g, false);
        vectorH = new Checkbox("Vector H", g, false);
        vectorResult = new Checkbox("Result", g, true);
        add(vectorE);
        add(vectorH);
        add(vectorResult);
        vectorE.reshape(0, 360, 100, 20);
        vectorH.reshape(101, 360, 100, 20);
        vectorResult.reshape(202, 360, 100, 20);
        CheckboxGroup g1 = new CheckboxGroup();
        resS = new Checkbox("S",g1, false);
        resE = new Checkbox("Energy",g1, true);
//        add(resE); add(resS);
        resE.reshape(10, 250, 70, 20);
        resS.reshape(10, 270, 70, 20);
	try
	  {
	    vectorEURL = new URL(getCodeBase(),"staywaveopis.html");
	    vectorHURL = new URL(getCodeBase(),"swvech.html");
	    vectorResultURL = new URL(getCodeBase(),"swvecres.html");
	  }catch(Exception e){}
      }

    public boolean handleEvent(Event e)
    {
        if (e.target == stopButton && e.id == e.ACTION_EVENT)
	  {
	    add (stepButton); add(startButton); stopSpeed=speedLeft; speedLeft=0;
	    remove (stopButton); remove(speedInc); remove(speedDec);
       	    return true;
	  }else
	if (e.target == stepButton && e.id == e.ACTION_EVENT) {time++;return true;}else
      	if (e.target == startButton && e.id == e.ACTION_EVENT)
       	  {
	  	    remove (stepButton); remove (startButton);
       	    add (stopButton); speedLeft=stopSpeed; add(speedInc); add(speedDec);
       	    return true;
       	  }else
	if (e.target == speedInc && e.id == e.ACTION_EVENT)
	  {if (speedLeft<50) speedLeft++; repaint();return true;
	  }else
	if (e.target == speedDec && e.id == e.ACTION_EVENT)
	  {if (speedLeft>0) speedLeft--; repaint(); return true;
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
        if (e.target == vectorH && e.id == e.ACTION_EVENT)
	  {
	    getAppletContext().showDocument(vectorHURL,"javabottom");
	    return super.handleEvent(e);
	  }else
        if (e.target == vectorE && e.id == e.ACTION_EVENT)
	  {
	    getAppletContext().showDocument(vectorEURL,"javabottom");
	    return super.handleEvent(e);
	  }else
        if (e.target == vectorResult && e.id == e.ACTION_EVENT)
	  {
	    getAppletContext().showDocument(vectorResultURL,"javabottom");
	    return super.handleEvent(e);
	  }
	    return super.handleEvent(e);
    }


    public void paint(Graphics g)
    {
        g.drawLine(0,355,500,355);

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
        realEndY=realEndY/(300-realEndZ)*600;*/


        g.drawLine( (int) realStartX+xOffset, (int) -realStartY+yOffset, (int) realEndX+xOffset, (int) -realEndY+yOffset);
        regX=(int)realEndX+xOffset;
        regY=-(int)realEndY+yOffset;
    }

    public void drawGraphE(Graphics g)
    {
        int firstX, firstY, secondX, secondY, thirdX, thirdY;
        time=time+speedLeft;
        double oldY, newY, oldBack, newBack, oldEnergy, newEnergy, maxEnergy, minEnergy;
        oldY=0;oldBack=0; maxEnergy=0; minEnergy=0;
        for (double i=1; i<200; i=i+1)
        {
            newY=Math.cos(((double)time-i*4)/180*Math.PI);
            newBack=Math.cos(((double)time-(400-i)*4)/180*Math.PI);
            newEnergy=newY+newBack;
            newEnergy*=newEnergy;
            oldEnergy=oldY+oldBack;
            oldEnergy*=oldEnergy;
            g.setColor(Color.green);
            drawMyLine(i-1, oldY*60, 0, i, newY*60, 0, g);
            firstX=regX;
            firstY=regY;
            g.setColor(Color.blue);
            drawMyLine(i-1, oldBack*60, 0, i, newBack*60, 0, g);
            secondX=regX;
            secondY=regY;
            g.setColor(Color.white);
            drawMyLine(i-1, (oldBack+oldY)*60, 0, i, (newBack+newY)*60, 0, g);
            thirdX=regX;
            thirdY=regY;
	    if (i != 1){
            g.setColor(Color.red);
            g.drawLine((int)i+99,339-(int)(oldEnergy*20),(int)i+100, 339-(int)(newEnergy*20));
	    if (newEnergy > maxEnergy) maxEnergy=newEnergy; else
		if (newEnergy < minEnergy) minEnergy=newEnergy;
	    }else {minEnergy=newEnergy; maxEnergy=newEnergy;}


            if (i == linePosition)
            {
                g.setColor(Color.white);
                g.fillOval((int)i+96, 336-(int)(oldEnergy*20), 8, 8);
                g.fillOval(firstX-4,firstY-4,8,8);
                g.fillOval(secondX-4,secondY-4,8,8);
                g.fillOval(thirdX-4,thirdY-4,8,8);
            }
            oldBack=newBack;
            oldY=newY;
            if (i%3==0)
            {
                g.setColor(Color.green);
                drawMyLine(i, 0, 0, i, newY*60, 0, g);
                g.setColor(Color.blue);
                drawMyLine(i-1, oldBack*60, 0, i-1, 0, 0, g);
            }

        }
        newY=Math.cos(((double)time-linePosition*4)/180*Math.PI);
        newBack=Math.cos(((double)time-(400-linePosition)*4)/180*Math.PI);
	g.setColor(Color.white);
	maxEnergy=(maxEnergy+minEnergy)*10;
	g.drawLine(100,339-(int)(maxEnergy),300,339-(int)(maxEnergy));
	for (int count=417; count<=423; count++) g.drawLine(count,120,count,120-(int)((newY+newBack)*70));
        g.setColor(Color.green);
        g.drawLine(419,120,419,120-(int)(newY*70));
        g.drawLine(420,120,420,120-(int)(newY*70));
        g.drawLine(422,120,422,120-(int)(newY*70));

        g.setColor(Color.blue);
        g.drawLine(419,120,419,120-(int)(newBack*70));
        g.drawLine(420,120,420,120-(int)(newBack*70));
        g.drawLine(418,120,418,120-(int)(newBack*70));
        if (time>359) time=time-360;
    }

    public void drawGraphH(Graphics g)
    {
        int firstX, firstY, secondX, secondY, thirdX, thirdY;
        time=time+speedLeft;
        double oldY, newY, oldBack, newBack, oldEnergy, newEnergy, minEnergy, maxEnergy;
        oldY=0;oldBack=0; minEnergy=0; maxEnergy=0;
        for (double i=1; i<200; i=i+1)
        {
            newY=Math.cos(((double)time-i*4)/180*Math.PI);
            newBack=-Math.cos(((double)time-(400-i)*4)/180*Math.PI);
            newEnergy=newY+newBack;
            newEnergy*=newEnergy;
            oldEnergy=oldY+oldBack;
            oldEnergy*=oldEnergy;
            g.setColor(Color.yellow);
            drawMyLine(i-1, 0, oldY*60, i, 0, newY*60, g);
            firstX=regX;
            firstY=regY;
            g.setColor(Color.cyan);
            drawMyLine(i-1, 0, oldBack*60, i, 0, newBack*60, g);
            secondX=regX;
            secondY=regY;
            g.setColor(Color.black);
            drawMyLine(i-1, 0, (oldBack+oldY)*60, i, 0, (newBack+newY)*60, g);
            thirdX=regX;
            thirdY=regY;
	    if (i != 1){
            g.setColor(Color.red);
            g.drawLine((int)i+99,339-(int)(oldEnergy*20),(int)i+100, 339-(int)(newEnergy*20));
	    if (newEnergy > maxEnergy) maxEnergy=newEnergy; else
		if (newEnergy < minEnergy) minEnergy=newEnergy;
	    }else {minEnergy=newEnergy; maxEnergy=newEnergy;}
            if (i == linePosition)
            {
                g.setColor(Color.white);
                g.fillOval((int)i+96, 336-(int)(oldEnergy*20), 8, 8);
                g.fillOval(firstX-4,firstY-4,8,8);
                g.fillOval(secondX-4,secondY-4,8,8);
                g.fillOval(thirdX-4,thirdY-4,8,8);
            }
            oldBack=newBack;
            oldY=newY;
            if (i%3==0)
            {
                g.setColor(Color.yellow);
                drawMyLine(i, 0, 0, i, 0, newY*60, g);
                g.setColor(Color.cyan);
                drawMyLine(i-1, 0, oldBack*60, i-1, 0, 0, g);
            }

        }
	g.setColor(Color.white);
	maxEnergy=(maxEnergy+minEnergy)*10;
	g.drawLine(100,339-(int)(maxEnergy),300,339-(int)(maxEnergy));
        newY=Math.cos(((double)time-linePosition*4)/180*Math.PI);
        newBack=-Math.cos(((double)time-(400-linePosition)*4)/180*Math.PI);
	g.setColor(Color.black);
	for (int count=117; count<=123; count++) g.drawLine(420,count,420-(int)((newY+newBack)*70),count);
        g.setColor(Color.yellow);
        g.drawLine(420,118,420-(int)(newY*70),118);
        g.drawLine(420,119,420-(int)(newY*70),119);
        g.drawLine(420,120,420-(int)(newY*70),120);
        g.setColor(Color.cyan);
        g.drawLine(420,120,420-(int)(newBack*70),120);
        g.drawLine(420,121,420-(int)(newBack*70),121);
        g.drawLine(420,122,420-(int)(newBack*70),122);
        if (time>359) time=time-360;
    }

    public void drawGraphResult(Graphics g)
    {
        int firstX, firstY, secondX, secondY;
        time=time+speedLeft;
        double oldH, newH, oldE, newE, temp, newEnergy, minEnergy, maxEnergy;
        oldH=0;oldE=0;maxEnergy=0;minEnergy=0;
        for (double i=1; i<200; i=i+1)
        {
            newE=Math.cos(((double)time-i*4)/180*Math.PI);
            temp=Math.cos(((double)time-(400-i)*4)/180*Math.PI);
            newH=newE-temp;
            newE+=temp;
            g.setColor(Color.white);
            drawMyLine(i-1, oldE*40, 0, i, newE*40, 0, g);
            firstX=regX;
            firstY=regY;
	    g.setColor(Color.black);
            drawMyLine(i-1, 0, oldH*40, i, 0, newH*40, g);
            secondX=regX;
            secondY=regY;
	    newEnergy = newE*newE*25+newH*newH*25;
	    if (i == linePosition)
	      {
		g.setColor(Color.white);
                g.fillOval(firstX-4,firstY-4,8,8);
                g.fillOval(secondX-4,secondY-4,8,8);
	      }
      if (i != 1){
	  if (resE.getState())
	  {
            g.setColor(Color.white);
            g.drawLine((int)i+99,339-(int)(oldE*oldE*25),(int)i+100, 339-(int)(newE*newE*25));
	    g.setColor(Color.black);
            g.drawLine((int)i+99,339-(int)(oldH*oldH*25),(int)i+100, 339-(int)(newH*newH*25));
            g.setColor(Color.red);
	    if (newEnergy > maxEnergy) maxEnergy=newEnergy; else
		if (newEnergy < minEnergy) minEnergy = newEnergy;
            g.drawLine((int)i+99,339-(int)(oldH*oldH*25+oldE*oldE*25),(int)i+100, 339-(int)(newEnergy));
            if (i == linePosition)
            {
                g.setColor(Color.white);
                g.fillOval((int)i+96, 336-(int)(oldH*oldH*25+oldE*oldE*25), 8, 8);
		g.fillOval((int)i+96, 336-(int)(oldH*oldH*25),8,8);
		g.fillOval((int)i+96, 336-(int)(oldE*oldE*25),8,8);
            }
	  }else
	  { g.setColor(Color.red);
	    g.drawLine((int)i+99, 310-(int)(oldE*oldH*30),(int)i+100,310-(int)(newE*newH*30));
	    if (i == linePosition)
	      {
		g.setColor(Color.white);
		g.fillOval((int)i+96, 306-(int)(oldE*oldH*30),8,8);
	      }
	  }
      } else {minEnergy=newEnergy; maxEnergy = newEnergy;}
            oldE=newE;
            oldH=newH;
            if (i%3==0)
            {
                g.setColor(Color.black);
                drawMyLine(i, 0, 0, i, 0, newH*40, g);
                g.setColor(Color.white);
                drawMyLine(i-1, newE*40, 0, i-1, 0, 0, g);
            }

        }
            newE=Math.cos(((double)time-linePosition*4)/180*Math.PI);
            temp=Math.cos(((double)time-(400-linePosition)*4)/180*Math.PI);
            newH=newE-temp;
            newE+=temp;
        g.setColor(Color.red);
	maxEnergy=(maxEnergy+minEnergy)/2;
	if (resE.getState()) g.drawLine(100,339-(int)(maxEnergy),300,339-(int)(maxEnergy));
	g.setColor(Color.white);
        g.drawLine(419,120,419,120-(int)(newE*40));
        g.drawLine(420,120,420,120-(int)(newE*40));
        g.drawLine(422,120,422,120-(int)(newE*40));
        g.setColor(Color.black);
        g.drawLine(420,119,420-(int)(newH*40),119);
        g.drawLine(420,122,420-(int)(newH*40),122);
        g.drawLine(420,121,420-(int)(newH*40),121);
        if (time>359) time=time-360;
    }


    public void drawAxes(Graphics g)
    {
      g.setColor(Color.black);
	if (vectorResult.getState() && resS.getState()) 
	  {
		g.drawLine(100,310,300,310);
		g.setColor(Color.white);
		g.drawString("0",92,317);
		g.drawString("y",290,317);
	  }
	else 
	  {
		g.drawLine(100,340,300,340);
		g.setColor(Color.white);
		g.drawString("0",96,347);
		g.drawString("y",290,347);
	  }
        g.setColor(Color.black);
        drawMyLine(0,0,0,200,0,00,g);
        drawMyLine(0,0,0,0,100,0,g);
        drawMyLine(0,0,0,0,0,100,g);
        g.drawOval(350,50,140,140);
        g.drawLine(345,120,495,120);
	g.drawLine(420,45,420,195);
        g.drawLine(100,220,100,340);
	//        g.drawLine(100,340,300,340);
        g.setColor(Color.red);
        g.drawLine(100+linePosition,240,100+linePosition,340);
        drawMyLine(linePosition,-70,0,linePosition,70,0,g);
        drawMyLine(linePosition,0,-70,linePosition,0,70,g);
	g.setColor(Color.green);
	g.drawLine(40,5,40,55);
	g.drawString("E1",25,12);
	g.setColor(Color.blue);
	g.drawLine(330,5,330,55);
	g.drawString("E2",315,12);
	g.setColor(Color.yellow);
	g.drawLine(40,55,10,85);
	g.drawString("H1",1,85);
	g.setColor(Color.cyan);
	g.drawLine(330,55,360,25);
	g.drawString("H2",360,20);
	g.setColor(Color.white);
	g.drawLine(40,55,80,55);
	g.drawString("k",80,50);
	g.setColor(Color.black);
	g.drawLine(290,55,330,55);
	g.drawString("k",285,55);

        if (vectorE.getState()) {
	g.drawString("=",180,15);
	g.drawString("+",210,15);
	g.setColor(Color.green);
	g.drawString("E1",190,15);
	g.setColor(Color.blue);
	g.drawString("E2",220,15);
	g.setColor(Color.white);
	g.drawString("E",170,15);  
     	g.drawString("We",80,230);
	g.drawString("<We>",70,300); 
}
        else if (vectorH.getState()) {
	g.drawString("H",170,15);
	g.drawString("=",180,15);
	g.drawString("+",210,15);
	g.setColor(Color.yellow);
	g.drawString("H1",190,15);
	g.setColor(Color.cyan);
	g.drawString("H2",220,15);
	g.setColor(Color.white);  
    	g.drawString("Wh",80,230);
	g.drawString("<Wh>",70,300); 
}
        else{ 
	g.drawString("=",180,15);
	g.drawString("+",210,15);
	g.drawString("H",170,30);
	g.drawString("=",180,30);
	g.drawString("+",210,30);
	g.setColor(Color.yellow);
	g.drawString("H1",190,30);
	g.setColor(Color.cyan);
	g.drawString("H2",220,30);


	g.setColor(Color.green);
	g.drawString("E1",190,15);
	g.setColor(Color.blue);
	g.drawString("E2",220,15);
	g.setColor(Color.white);
	g.drawString("E",170,15); 
	g.setColor(Color.black);
if (resE.getState()){ 
	g.setColor(Color.white);
    	g.drawString("W",85,230);
	g.drawString("<W>",75,300);
      }else{
	g.setColor(Color.white);
	g.drawString("S",85,230);
      }
	}
	g.drawString("x",340,115);
	g.drawString("z",425,45);
	g.drawString("z",45,12);
	g.drawString("x",18,85);
	g.drawString("y",80,65);
	g.drawString("0",45,65);


    }
}
