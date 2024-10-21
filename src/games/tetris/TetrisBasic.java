package games.tetris;

import global.UV;
import graphics.G;
import graphics.Panel;
import graphics.WinApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TetrisBasic extends Panel implements ActionListener {
    public static Timer timer;
    public static final int xM = 50, yM = 50;
    public static final int H =20, W =10, C = 25;
    public static Color[] color = {Color.RED,Color.GREEN,Color.BLUE,Color.ORANGE,Color.CYAN,Color.YELLOW,Color.MAGENTA,Color.BLACK,Color.PINK};
    public static Shape[] shapes = {Shape.Z,Shape.S,Shape.J, Shape.L,Shape.I,Shape.O,Shape.T};

    public static Shape shape;
    public static final int iBkColor = 7, zap=8;
    public static int[][] well = new int[W][H];

    public TetrisBasic() {
        super();
        startNewGame();
        timer = new Timer(30,  this);
//        timer.start();
    }

    public static int time=1, iShape =0;

    public void startNewGame(){
        shape=shapes[G.rnd(7)];
        clearWell();
    }
    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,5000,5000);
        /*time++;
        if(time==60){time=0; iShape= (iShape+1)%7;}
        if(time==30){shapes[iShape].rot();}
        shapes[iShape].show(g);*/
        time++;if(time==30){time=0;shape.drop();}
        unzapWell();
        showWell(g);
        shape.show(g);
    }

    public static void clearWell(){
        for(int x=0;x<W;x++){
            for(int y=0;y<H;y++){
                well[x][y]=iBkColor;
            }
        }
    }
    public static void showWell(Graphics g){
        for(int x=0;x<W;x++){
            for(int y=0;y<H;y++){
                g.setColor(color[well[x][y]]);
                int xx = xM + C * x, yy= yM + C * y;
                g.fillRect(xx,yy,C,C);
                g.setColor(Color.BLACK);
                g.drawRect(xx,yy,C,C);
            }
        }
    }
    public static void zapWell(){
        for(int y=0;y<H;y++){zapRow(y);}
    }
    public static void zapRow(int y){
        for(int x=0;x<W;x++){if(well[x][y]==iBkColor){return;}}
        for(int x=0;x<W;x++){well[x][y]=zap;}
    }
    public static void unzapWell(){
        for(int y=1;y<H;y++){
            for(int x=0;x<W;x++){
                if(well[x][y-1]!=zap && well[x][y]==zap){
                    well[x][y]=well[x][y-1];
                    well[x][y-1]=(y==1)? iBkColor: zap;
                }
            }
        }
    }
    public void keyPressed(KeyEvent ke){
        int vk = ke.getKeyCode();
        if(vk==KeyEvent.VK_LEFT){shape.slide(G.LEFT);}
        if(vk==KeyEvent.VK_RIGHT){shape.slide(G.RIGHT);}
        if(vk==KeyEvent.VK_UP){shape.safeRot();}
        if(vk==KeyEvent.VK_DOWN){shape.drop();}
        repaint();
        if(vk==KeyEvent.VK_ESCAPE){
            timer.stop();
            UV.mainWindow.changePanel(UV.mainMenuPanel);
        }else{
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){repaint();}

    //Removed to facilitate integration
//    public static void main(String[] args){
//        PANEL=new Tetris();
//        WinApp.launch();
//    }

    //------------------SHAPE----------------------
    public static class Shape{
        public static Shape Z, S, J, L, I, O, T;

        public G.V[] a = new G.V[4];
        public int iColor;
        public G.V loc = new G.V(4,0);

        static {
            Z=new Shape(new int[] {0,0, 1,0, 1,1, 2,1},0);
            S=new Shape(new int[] {0,1, 1,0, 1,1, 2,0},1);
            J=new Shape(new int[] {0,0, 0,1, 1,1, 2,1},2);
            L=new Shape(new int[] {0,1, 1,1, 2,1, 2,0},3);
            I=new Shape(new int[] {0,0, 1,0, 2,0, 3,0},4);
            O=new Shape(new int[] {0,0, 1,0, 0,1, 1,1},5);
            T=new Shape(new int[] {0,1, 1,0, 1,1, 2,1},6);

        }

        public static G.V temp = new G.V(0,0);

        public Shape(int[] xy, int iColor){
            this.iColor = iColor;
            for(int i=0;i<4;i++){
                a[i]=new G.V(xy[2*i],xy[2*i+1]);
            }
        }

        public void show(Graphics g){
            g.setColor(color[iColor]);
            for(int i = 0; i < 4; i++){g.fillRect(x(i),y(i),C,C);}
            g.setColor(Color.BLACK);
            for(int i = 0; i < 4; i++){g.drawRect(x(i),y(i),C,C);}
        }

        public int x(int i){return xM+C * (a[i].x+loc.x);}
        public int y(int i){return yM+C * (a[i].y+loc.y);}

        public void rot(){ //Unsafe - does not collision detect
            temp.set(0,0);
            for(int i=0; i<4; i++){
                a[i].set(-a[i].y,a[i].x);
                if(temp.x>a[i].x){temp.x=a[i].x;}
                if(temp.y>a[i].y){temp.y=a[i].y;}
            }

            temp.set(-temp.x,-temp.y);
            for(int i=0; i<4; i++){a[i].add(temp);}
        }

        public void safeRot(){
            rot(); //first assume we can rotate
            cdsSet();
            if(collisionDetected()){rot();rot();rot();}
        }

        public void drop(){
            this.cdsSet();
            this.cdsAdd(G.DOWN);
            if(this.collisionDetected()){
                copyToWell();
                zapWell();
                dropNewShape();
            }
            else{
                loc.add(G.DOWN);
            }
        }

        public void copyToWell(){
            for(int i=0;i<4;i++){
                well[a[i].x+loc.x][a[i].y+loc.y]=iColor;
            }
        }
        public static void dropNewShape(){
            shape=shapes[G.rnd(7)];
            shape.loc.set(4,0);
        }
        public static Shape cds = new Shape(new int[] {0,0, 0,0, 0,0, 0,0}, 0);
        public static boolean collisionDetected (){
            for(int i = 0; i<4; i++){
                G.V v = cds.a[i];
                if(v.x<0 ||v.x>=W||v.y<0||v.y>=H){return true;}
                if(well[v.x][v.y]!=iBkColor && well[v.x][v.y]!=zap){return true;}
            }
            return false;
        }

        public void cdsSet(){for(int i=0; i<4; i++){cds.a[i].set(a[i]);cds.a[i].add(loc);}}
        public void cdsGet(){for(int i=0; i<4; i++){a[i].set(cds.a[i]);}}
        public void cdsAdd(G.V v){for(int i=0; i<4; i++){cds.a[i].add(v);}}
        public void slide(G.V dx){
            cdsSet();
            cdsAdd(dx);
            if(collisionDetected()){return;}
            loc.add(dx);
        }
    }
}
