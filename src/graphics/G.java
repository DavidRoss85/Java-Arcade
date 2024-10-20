package graphics;

import global.UV;

import java.awt.*;

public class G {

    //**Clear the background by painting everything one color**
    public static void clearScreen(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0, UV.currentScreenWidth,UV.currentScreenHeight);
    }
    public static void clearScreen(Graphics g, Color c){
        g.setColor(c);
        g.fillRect(0,0, UV.currentScreenWidth,UV.currentScreenHeight);
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


}
