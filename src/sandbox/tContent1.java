package sandbox;

import graphics.G;
import graphics.Panel;
import graphics.WinApp;

import java.awt.*;
import java.awt.event.MouseEvent;

public class tContent1 extends Panel {

    public Panel content2;

    public tContent1(WinApp parentWindow){
        super();
        content2= new tContent2(parentWindow);
    }

    public void mousePressed(MouseEvent me){
        parentWindow.changePanel(content2);
    }

    public void paintComponent(Graphics g){
        G.clearScreen(g,Color.BLUE);
        g.setColor(Color.RED);
        g.fillOval(100,150,200,300);
    }
}
