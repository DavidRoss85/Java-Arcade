package graphics;

import global.UV;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class G {

    public static int W = UV.screenWidth;
    public static int H = UV.screenHeight;


    //**Clear the background by painting everything one color**
    public static void clearScreen(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0, W,H);
    }
    public static void clearScreen(Graphics g, Color c){
        g.setColor(c);
        g.fillRect(0,0, W,H);
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

    public static BrickWall generateBrickWall(int numX, int numY){

        BrickWall brickwall = new BrickWall();
        Brick brick;

        Color brickColor = new Color(103, 17, 17);
        int cVariance = 40;
        int cR, cG, cB;
        int brickW = 40, brickH = 20;

        for(int y=0;y<numY;y++){
            int xOffset = (y%2)*brickW/2;
            for(int x=-1;x<numX;x++){
                cR=rnd(cVariance);
                brickColor = new Color(73+cR,17,17);
                brick = new Brick(x*brickW+xOffset,y*brickH,brickW,brickH,brickColor);
                brickwall.add(brick);

            }
        }
        return brickwall;
    }

    public static Random RND = new Random();
    public static int rnd(int max){return RND.nextInt(max);}
    public static Color rndColor(){return new Color(rnd(256),rnd(256),rnd(256)); }

    public static V LEFT = new V(-1,0);
    public static V RIGHT = new V(1,0);
    public static V UP = new V(0,-1);
    public static V DOWN = new V(0,1);



    //-----------------------V------------------------
    public static class V implements Serializable {
        public static Transform T = new Transform();
        public int x,y;

        public V(int x, int y){this.set(x,y);}

        public void set(int x, int y){this.x = x; this.y = y;}
        public void set(V v){x=v.x; y=v.y;}
        public void add(V v){x += v.x; y += v.y;} // vector addition
        public void blend(V v, int nBlend){
            set((nBlend*x + v.x)/(nBlend+1),(nBlend*y + v.y)/(nBlend+1));
        }
        //Transforms
        public void setT(V v){set(v.tx(),v.ty());}
        public int tx(){return x*T.n/T.d+T.dx;}
        public int ty(){return y*T.n/T.d+T.dy;}
        //----------------Transform-----------------
        public static class Transform{
            int dx, dy, n, d;
            public void set(VS oVS, VS nVS){
                setScale(oVS.size.x,oVS.size.y,nVS.size.x,nVS.size.y);
                dx=setOff(oVS.loc.x,nVS.loc.x);
                dy=setOff(oVS.loc.y,nVS.loc.y);
            }

            public void setScale(int oW, int oH, int nW, int nH){
                n=(nW>nH)? nW:nH;
                d=(oW>oH)? oW:oH;
            }
            public int setOff(int oX, int nX){return -oX*n/d+nX;}
        }

    }

    //-----------------------VS-----------------------
    public static class VS implements Serializable{
        public V loc, size;

        public VS(int x, int y, int w, int h){
            loc = new V(x,y); size = new V(w,h);
        }

        public void fill(Graphics g, Color c){
            g.setColor(c); g.fillRect(loc.x,loc.y,size.x,size.y);
        }
        public boolean hit(int x, int y){
            return loc.x<=x && loc.y<=y && x<=(loc.x+size.x) && y<=(loc.y+size.y);
        }
        public int xL(){return loc.x;}
        public int xH(){return loc.x + size.x;}
        public int xM(){return (loc.x + loc.x + size.x)/2;}
        public int yL(){return loc.y;}
        public int yH(){return loc.y + size.y;}
        public int yM(){return (loc.y + loc.y + size.y)/2;}

    }

    //-----------------------Brick----------------------------
    public static class Brick implements I.Show {
        int x, y, width, height;
        public Color color;
        public final int MAX_SPOT_SIZE = 5;
        public Spot[] spots = new Spot[5];

        public Brick(int x, int y, int width, int height,Color color){
            this.x=x;this.y=y;this.width=width;this.height=height;
            this.color=color;
            for(int n=0;n<spots.length;n++){
                Spot spot = new Spot(rnd(this.width),rnd(this.height),rnd(MAX_SPOT_SIZE),rnd(MAX_SPOT_SIZE));
                spots[n] = spot;
            }
        }

        @Override
        public void show(Graphics g) {
            g.setColor(color);
            g.fillRect(x,y,width,height);
            g.setColor(Color.BLACK);
            g.drawRect(x,y,width,height);
            for (Spot spot : spots) {
                g.fillOval(spot.x+x, spot.y+y, spot.w, spot.h);
            }
        }
        //-----------------Spot---------------------------
        public static class Spot{
            int x, y, w, h;
            public Spot(int x,int y,int w,int h){
                this.x=x;this.y=y;this.h=h;this.w=w;
            }
        }
    }

    //-----------------BrickWall---------------------------------
    public static class BrickWall extends ArrayList<Brick> implements I.Show{

        public BrickWall(){}

        public void addABrick(Brick brick){
            this.add(brick);
        }

        @Override
        public void show(Graphics g) {
            for(Brick b: this){
                g.setColor(b.color);
                g.fillRect(b.x,b.y,b.width,b.height);
                g.setColor(Color.BLACK);
                g.drawRect(b.x,b.y,b.width,b.height);
                for (Brick.Spot spot : b.spots) {
                    g.fillOval(spot.x+b.x, spot.y+b.y, spot.w, spot.h);
                }
            }
        }
    }
}
