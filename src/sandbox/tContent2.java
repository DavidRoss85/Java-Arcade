package sandbox;

import graphics.G;
import graphics.Panel;
import graphics.WinApp;

import java.awt.*;
import java.awt.event.MouseEvent;

public class tContent2 extends Panel {

    public tContent2(WinApp parentWindow){
        super();
    }

    public void mousePressed(MouseEvent me){
        this.parentWindow.changePanel(this.parentWindow.mainPanel);
    }

    public void paintComponent(Graphics g){
        G.clearScreen(g,Color.RED);
        g.setColor(Color.BLUE);
        g.fillOval(100,150,200,300);
    }
}

