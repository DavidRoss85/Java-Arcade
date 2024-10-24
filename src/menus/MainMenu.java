package menus;

import global.UC;
import global.UV;
import graphics.G;
import graphics.I;
import graphics.Panel;
import sprites.Icons;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainMenu extends Panel implements ActionListener {

    //************Testing only. Delete later***************
    public int tempCount = 0 ;
    public Point testPoint;
    public ArrayList<Point> points = new ArrayList<>();

    //*******************************************************

    public Icons.JavaCup javaCup = new Icons.JavaCup(200,200,100,100,Color.WHITE,Color.RED,Color.BLACK);
    public Color textColor = UC.menuFontColor;
    public int textSize = UC.menuFontSize;
    public Color menuBackColor = UC.menuBackColor;
    public ArrayList<MenuItem> menuChoices = new ArrayList<>();
    public int mnuX = 400, mnuY = 400;

    public MainMenu(){

        Font fontStyle = new Font("Courier New", Font.PLAIN,textSize);
        menuChoices.add(new MenuItem("Tetris II", mnuX,mnuY,fontStyle,UV.tetrisPanel));
        menuChoices.add(new MenuItem("Collapse II", mnuX,mnuY+10+textSize,fontStyle,UV.collapsePanel));
        menuChoices.add(new MenuItem("Stats", mnuX,mnuY+((10+textSize)*2),fontStyle,UV.statPanel));
        menuChoices.add(new MenuItem("Tetris I", mnuX,mnuY+((10+textSize)*3),fontStyle,UV.oldTetris));
        menuChoices.add(new MenuItem("Collapse", mnuX,mnuY+((10+textSize)*4),fontStyle,UV.oldCollapse));
    }

    public void paintComponent(Graphics g){
        G.clearScreen(g,menuBackColor);

        showLogo(g,300,200,100,100);
        for(int n=0;n<menuChoices.size() ;n++){
            menuChoices.get(n).show(g);
        }
        //testing
        g.setColor(textColor);
        if(testPoint!=null) {
            for (Point point : points) {g.fillOval(point.x, point.y, 5, 5);}
        }
        repaint();
    }

    public void showLogo(Graphics g,int x, int y, int w, int h){
        Font logoFont = new Font("Courier New", Font.BOLD,40);

        javaCup.x=x;
        javaCup.y=y;
        javaCup.show(g);

        g.setColor(Color.WHITE);
        g.setFont(logoFont);
        g.drawString("Java Arcade",x+100,y+40);
    }

    public void mousePressed(MouseEvent me){
        //testing
        for(int n=0;n<menuChoices.size();n++){
            if(menuChoices.get(n).clicked(me.getX(),me.getY())){
                UV.mainWindow.changePanel(menuChoices.get(n).pointsTo);
            }
        }
        testPoint=me.getPoint();
        points.add(testPoint);
    }
    public void mouseMoved(MouseEvent me){
        //Highlight menu on hover
        for(int n=0;n<menuChoices.size();n++){
            if(menuChoices.get(n).hovered(me.getX(),me.getY())){
                menuChoices.get(n).highlight();
            }else{
                menuChoices.get(n).unHighlight();
            }
        }
    }

    public void keyPressed(KeyEvent ke){
        int vk = ke.getKeyCode();

        if(vk==KeyEvent.VK_1){UV.mainWindow.changePanel(UV.tetrisPanel);}
        if(vk==KeyEvent.VK_2){UV.mainWindow.changePanel(UV.collapsePanel);}
        if(vk==KeyEvent.VK_3 || vk==KeyEvent.VK_ENTER){UV.mainWindow.changePanel(UV.statPanel);}

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    //-----------------------------MenuItem------------------------------------------
    public static class MenuItem implements I.Area, I.Show{

        public String text;
        public int left, right, top, bottom, width, height;
        public Font font = new Font("Courier New", Font.PLAIN,UC.menuFontSize);
        public Color fontColor = UC.menuFontColor;
        public Panel pointsTo = UV.mainMenuPanel;
        public boolean highlighted = false;

        public MenuItem(String text,int x, int y){
            this.left =x; this.top=y;
            this.width= (int)(this.font.getSize() * text.length() / 1.7);
            this.height = this.font.getSize();
            this.right=x+width;this.bottom = y+height;
            this.text = text;
        }
        public MenuItem(String text,int x, int y, Font font){
            this.text = text;
            this.left =x; this.top=y;
            this.font = font;
            this.width = (int)(this.font.getSize() * text.length() / 1.7);
            this.height = font.getSize();
            this.right=x+width;this.bottom = y+height;
        }
        public MenuItem(String text,int x, int y, Font font, Panel pointsTo){
            this.text = text;
            this.left =x; this.top=y;
            this.font = font;
            this.width = (int)(this.font.getSize() * text.length() / 1.7);
            this.height = font.getSize();
            this.right=x+width;this.bottom = y+height;
            this.pointsTo = pointsTo;
        }
        public MenuItem(String text,int x, int y, int width,int height){
            this.text = text;
            this.left =x; this.top=y;
            this.width=width; this.height =height;
            this.right=x+width;this.bottom = y+height;
        }
        public MenuItem(String text,int x, int y, int width,int height, Font font){
            this.text = text;
            this.left =x; this.top=y;
            this.width=width; this.height =height;
            this.right=x+width;this.bottom = y+height;
            this.font = font;
        }

        public void highlight(){
            this.highlighted =true;
        }
        public void unHighlight(){
            this.highlighted =false;
        }


        @Override
        public void show(Graphics g) {
            if(highlighted){g.setColor(Color.RED);g.fillRect(left,top,width,height);}
            g.setColor(fontColor);
            g.setFont(font);
//            g.drawRect(left,top,width,height);
            g.drawString(text,left,bottom);
        }

        @Override
        public boolean clicked(int x, int y) {
            return (x >= left) && (x <= right) && (y >= top) && (y <= bottom);
        }

        @Override
        public boolean hovered(int x, int y) {
            return (x >= left) && (x <= right) && (y >= top) && (y <= bottom);
        }
    }
}
