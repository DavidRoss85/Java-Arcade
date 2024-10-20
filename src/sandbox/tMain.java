package sandbox;

import graphics.G;
import graphics.WinApp;
import graphics.Panel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class tMain extends Panel {

    public Panel content1;

    public tMain(WinApp parentWindow){
        super(parentWindow);
       content1 = new tContent1(parentWindow);

    }
    public void mousePressed(MouseEvent me){
        this.parentWindow.changePanel(content1);
    }

    public void paintComponent(Graphics g){
        G.clearScreen(g,Color.WHITE);
        g.setColor(Color.GREEN);
        g.fillOval(200,200,200,200);
    }
}
