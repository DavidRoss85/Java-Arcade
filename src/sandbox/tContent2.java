package sandbox;

import graphics.G;
import graphics.WinApp;

import java.awt.*;
import java.awt.event.MouseEvent;

public class tContent2 extends WinApp {

    public WinApp myDaddy;
    public WinApp content3;

    public tContent2(WinApp daddy){
        super("Content",1000,1000);
        this.myDaddy=daddy;
//        content3= new tMain(myDaddy);
    }

    public void mousePressed(MouseEvent me){
        myDaddy.cPTest2(myDaddy.PANEL);
    }

    public void paintComponent(Graphics g){
        G.clearScreen(g,Color.RED);
        g.setColor(Color.BLUE);
        g.fillOval(100,150,200,300);
    }
}

