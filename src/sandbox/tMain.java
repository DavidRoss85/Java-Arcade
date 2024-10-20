package sandbox;

import graphics.G;
import graphics.WinApp;

import java.awt.*;
import java.awt.event.MouseEvent;

public class tMain extends WinApp {

    public WinApp myDaddy;
    public WinApp content1;

    public tMain(WinApp daddy){
        super("Content",1000,1000);
        this.myDaddy=daddy;
        content1 = new tContent1(myDaddy);
    }
    public void mousePressed(MouseEvent me){
        myDaddy.cPTest2(content1);
    }

    public void paintComponent(Graphics g){
        G.clearScreen(g,Color.WHITE);
        g.setColor(Color.GREEN);
        g.fillOval(200,200,200,200);
    }
}
