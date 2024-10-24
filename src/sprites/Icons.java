package sprites;

import graphics.I;

import java.awt.*;

public class Icons{

    public static class JavaCup implements I.Show{
        public final double HANDLEFACTOR=.5;
        public int x, y, width, height;
        public Color cupColor, liquidColor,lineColor;
        public int xM, yM, right, bottom;
        public int cupWidth, handleWidth, handleHeight;

        public JavaCup(int x, int y, int width, int height, Color cupColor, Color liquidColor,Color lineColor){
            this.x = x; this.y=y; this.width=width; this.height=height;
            this.cupColor=cupColor; this.liquidColor=liquidColor; this.lineColor=lineColor;
            xM=x+(width/2);
            yM=y+height/2;
            right=x+width;
            bottom=y+height;
            cupWidth=(int)(width*.8); handleWidth=(int)(width*.5); handleHeight = (int)(height*.4);

        }
        @Override
        public void show(Graphics g) {
            //g.setColor(Color.GREEN);
            //g.drawRect(x,y,width,height);
            g.setColor(cupColor);
            g.fillOval(x,y,cupWidth,height);
            g.fillRect(x,y+((int)(height*.2)),cupWidth,((int)(height*.3)));
            g.fillArc(x+((int)(width*.5)),y+((int)(height*.3)),handleWidth,handleHeight,200,270);
            g.setColor(liquidColor);
            g.fillOval(x,y,cupWidth,(int)(height*.4));
            g.setColor(lineColor);
            g.fillArc(x+(int)(cupWidth*.85),y+((int)(height*.4)),(int)(handleWidth*HANDLEFACTOR),(int)(handleHeight*HANDLEFACTOR),270,180);


        }
    }

}
