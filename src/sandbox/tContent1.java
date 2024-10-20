package sandbox;

import graphics.G;
import graphics.WinApp;

import java.awt.*;
import java.awt.event.MouseEvent;

public class tContent1 extends WinApp {

    public WinApp myDaddy;
    public WinApp content2;

    public tContent1(WinApp daddy){
        super("Content",1000,1000);
        this.myDaddy=daddy;
        content2= new tContent2(myDaddy);
    }

    public void mousePressed(MouseEvent me){
        System.out.println("content 1 clicked");
        myDaddy.cPTest2(content2);
    }

    public void paintComponent(Graphics g){
        G.clearScreen(g,Color.BLUE);
        g.setColor(Color.RED);
        g.fillOval(100,150,200,300);
    }
}
