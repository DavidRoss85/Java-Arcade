package menus;

import global.UC;
import graphics.G;
import graphics.Panel;
import graphics.WinApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainMenu extends Panel implements ActionListener {

    public static Color textColor = UC.menuTextColor;
    public Point testPoint;
    public ArrayList<Point> points = new ArrayList<>();

    public MainMenu(WinApp parent){
        super(parent);
    }

    public void paintComponent(Graphics g){
        G.clearScreen(g,Color.BLACK);
        g.setColor(textColor);
        if(testPoint!=null) {
            for (Point point : points) {g.fillOval(point.x, point.y, 5, 5);}
        }
        repaint();
    }

    public void mousePressed(MouseEvent me){
        testPoint=me.getPoint();
        points.add(testPoint);
    }
    public void mouseDragged(MouseEvent me){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
