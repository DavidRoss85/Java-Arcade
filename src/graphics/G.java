package graphics;

import global.UV;

import java.awt.*;
import java.util.Random;

public class G {

    public static int W = UV.screenWidth;
    public static int H = UV.screenHeight;

    //**Clear the background by painting everything one color**
    public static void clearScreen(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0, W,H);
    }
    public static void clearScreen(Graphics g, Color c){
        g.setColor(c);
        g.fillRect(0,0, W,H);
    }
    public static void clearScreen(Graphics g, int x, int y, int width, int height ){
        g.setColor(Color.WHITE);
        g.fillRect(x,y,width,height);
    }
    public static void clearScreen(Graphics g, int x, int y, int width, int height,Color c ){
        g.setColor(c);
        g.fillRect(x,y,width,height);
    }
    //**********************************

    public static Random RND = new Random();
    public static int rnd(int max){return RND.nextInt(max);}
    public static Color rndColor(){return new Color(rnd(256),rnd(256),rnd(256)); }

}
