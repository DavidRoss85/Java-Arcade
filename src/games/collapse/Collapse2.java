package games.collapse;

import global.UV;
import graphics.G;
import graphics.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Collapse2 extends Panel implements ActionListener {
    public final int nC = 13, nR =15; //number of columns and rows
    public final int W = 60, H =40; //width and height of each block
    public int xM=100, yM=100; //margins for collapsing
    public int bXM=100, bYM=100; //background margins for colors

    public Color highlight = Color.ORANGE;
    public Color[] color = {Color.BLACK,Color.RED,Color.BLUE, Color.GREEN,Color.ORANGE,Color.CYAN,
            Color.YELLOW,Color.PINK,Color.MAGENTA};
    public int[][] grid = new int[nC][nR], gridOverlay = new int[nC][nR];
    public int[] bottomRow = new int[nC];

    public G.V cellClicked = new G.V(-1,-1);
    public int nColor =3;
    public int bricksRemaining;
    public int tDelay = 5, time=0;
    public boolean gameOver = false;

    public Collapse2(){
        super();
        startNewGame();
        UV.uTimer.addActionListener(this);
    }

    public void startNewGame(){
        if(nColor>color.length){nColor = 3;}
        rndColors(nColor);
        xM=100;
        bricksRemaining = nR*nC;
    }
    public void raiseDifficulty(){
        if(nColor<color.length){
            nColor++;
        }
    }
    public void lowerDifficulty(){
        if(nColor>3){
            nColor--;
        }
    }
    public void showRemaining(Graphics g){
        String str = "Bricks remaining: " + bricksRemaining;
        if(noMorePlays()){str += "No More Plays left!";}
        g.setColor(Color.BLACK);
        g.drawString(str,50,25);
    }

    public boolean noMorePlays(){
        for(int r=0;r<nR;r++){
            for(int c=0;c<nC;c++){
                if(infectable(c,r)){return false;}
            }
        }
        return true;
    }

    public void paintComponent (Graphics g){

        UV.brickBackground.show(g);
        showGrid(g);
        showRemaining(g);
    }
    public void keyPressed(KeyEvent ke){
        int vk= ke.getKeyCode();

        if(vk==KeyEvent.VK_R){startNewGame();}
        if(vk==KeyEvent.VK_UP){raiseDifficulty();}
        if(vk==KeyEvent.VK_DOWN){lowerDifficulty();}
        if(vk==KeyEvent.VK_ESCAPE){
            UV.mainWindow.changePanel(UV.mainMenuPanel);
        }
    }
    public void mousePressed(MouseEvent me){
        int x = me.getX(), y = me.getY();
        if(x < xM || y < yM){return;}
        cellClicked.y = r(y); cellClicked.x = c(x);
    }

    public void mouseReleased(MouseEvent me){
        int x = me.getX(), y = me.getY();
        if(x < xM || y < yM){return;}
        int r = r(y), c = c(x);
        if(r<nR && c <nC && cellClicked.x==c && cellClicked.y==r){
            clearOverlay();
            crAction(c,r);
        }
        cellClicked.x=-1; cellClicked.y=-1;
    }
    public void mouseMoved(MouseEvent me){
        int x = me.getX(), y = me.getY();
        if(x < xM || y < yM){return;}
        int r = r(y), c = c(x);
        if(r<nR && c <nC){
            if(gridOverlay[c][r]!=1){
                clearOverlay();
                crHighlight(c,r);
            }
        }
    }

    public void clearOverlay(){
        for(int nX=0; nX<nC;nX++){
            for(int nY=0; nY<nR;nY++){
                gridOverlay[nX][nY]=0;
            }
        }
    }
    public void crAction(int c, int r){
        //System.out.println("("+c+","+r+")");
        if(infectable(c,r)){infect(c,r,grid[c][r]);}
    }
    public void crHighlight(int c, int r){
        if(infectable(c,r)){infectPath(c,r,grid[c][r],c,r);}
    }

    public boolean infectable(int c, int r){
        int v =  grid[c][r];
        if( v==0){return false;}
        if(r>0){if(grid[c][r-1]== v){return true;}}
        if(c>0){if(grid[c-1][r]== v){return true;}}
        if(r<nR-1){if(grid[c][r+1]== v){return true;}}
        if(c<nC-1){if(grid[c+1][r]== v){return true;}}
        return false;
    }

    public void infect(int c,int r, int v){
        if(grid[c][r]!= v){return;}
        grid[c][r]=0;
        bricksRemaining--;
        if(r>0){infect(c,r-1,v);}
        if(c>0){infect(c-1,r,v);}
        if(r<nR-1){infect(c,r+1,v);}
        if(c<nC-1){infect(c+1,r,v);}
    }

    public void infectPath(int c, int r, int v, int oldC, int oldR){
        if((grid[c][r]!= v) || (gridOverlay[c][r]==1)){return;}
        int red,green,blue;
        red=Math.min(color[v].getRed()+150,255);
        green=Math.min(color[v].getGreen()+150,255);
        blue=Math.min(color[v].getBlue()+150,255);
        highlight = new Color(red,green,blue);
        gridOverlay[c][r]=1; //Insert draw square code here
        //bricksRemaining--;
        if(r>0 && r-1!=oldR){infectPath(c,r-1,v,c,r);}
        if(c>0 && c-1!=oldC){infectPath(c-1,r,v,c,r);}
        if(r<nR-1 && r+1!=oldR){infectPath(c,r+1,v,c,r);}
        if(c<nC-1 && c+1!=oldC){infectPath(c+1,r,v,c,r);}
    }

    public int rowCanBubble(int c){
        for(int r=nR-1;r>0;r--){
            if(grid[c][r] == 0 && grid[c][r-1]!=0){return r;}
        }
        return nR;
    }

    public void bubble(){
        for(int c=0;c<nC;c++){
            int r=rowCanBubble(c);
            if(r<nR){
                int tmp = grid[c][r];
                //move entire section at once instead of individual blocks to make drops more realistic
                for(int n = r;n>0;n--){
                    grid[c][n]=grid[c][n-1];
                }
                grid[c][0]=tmp;
            }
        }
    }

    public boolean colIsEmpty(int c){
        for(int r=0;r<nR;r++){
            if(grid[c][r] !=0){return false;}
        }
        return true;
    }

    public void swapCol(int c1, int c2){ // swap column colors
        for(int r=0;r<nR;r++){
            int tmpCol = grid[c2][r];
            grid[c2][r]=grid[c1][r];
            grid[c1][r]=tmpCol;
        }
    }
    public void squeezeCols(){
        for(int c=1;c<nC/2;c++){
            if(colIsEmpty(c)&&!colIsEmpty(c-1)){swapCol(c,c-1);}
            if(colIsEmpty(nC-c-1)&&!colIsEmpty(nC-c)){swapCol(nC-c-1,nC-c);}
        }
        if(nC%2==1){
            //Wierd integer math to swap the middle column if it's an odd number of columns
            int x = (int)(Math.ceil(nC*.5));
            if(colIsEmpty(x-1)&&!colIsEmpty(x)){swapCol(x-1,x);}
        }
    }

    public boolean slideCol(){
        boolean res = false;
        for(int c=1;c<nC;c++){
            if(colIsEmpty(c-1)&&!colIsEmpty(c)){
                swapCol(c,c-1);
                res=true;
            }
        }
        return res;
    }

    public void rndColors(int k){
        for (int c=0; c<nC; c++){
            for(int r=0;r<nR;r++){
                grid[c][r]= G.rnd(k);
            }
            bottomRow[c]=0;
        }
    }

    public void showGrid(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, 0, getHeight(), Color.BLACK);
        g2d.setPaint(gradient);

        g2d.fillRect(bXM,bYM,W*nC,H*nR);
        for (int c=0; c<nC; c++){
            for(int r=0;r<nR;r++){

                //Color grid
                if(grid[c][r]!=0){
                    g.setColor(color[grid[c][r]]);
                    g.fillRect(x(c),y(r),W,H);
                    g.setColor(Color.BLACK);
                    g.drawRect(x(c),y(r),W,H);
                }
                //Color overlay for highlighting
                if(gridOverlay[c][r]==1){
                    g.setColor(highlight);
                    g.fillRect(x(c),y(r),W,H);
                    g.setColor(new Color(255,215,0));
                    g.drawRect(x(c),y(r),W,H);
                }
            }
            //Color bottom Row:
            g.setColor(color[bottomRow[c]]);
            g.fillRect(bXM+c*W,bYM+nR*H,W,H);
            g.setColor(Color.BLACK);
            g.drawRect(bXM+c*W,bYM+nR*H,W,H);
        }

    }
    public void addBlockToBottomRow(){
        for(int i=0;i<bottomRow.length;i++){
            if(bottomRow[i]==0){
                bottomRow[i]=G.rnd(nColor);
                return;
            }
        }
        popBottomRow();
    }
    public void popBottomRow(){
        for (int c=0; c<nC; c++) {
            if(grid[c][0]!=0){gameOver=true;return;}
            for (int r = 1; r < nR; r++) {
                grid[c][r-1]=grid[c][r];
            }
            grid[c][nR-1]=bottomRow[c];
            bottomRow[c]=0;
        }

    }
    public int x(int c){return xM + c * W;}
    public int y(int r){return yM + r * H;}
    private int c(int x){return (x-xM)/W;} //unsafe fixed in mouseClicked
    private int r(int y){return (y-yM)/H;}

    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        if(time>=tDelay){
            addBlockToBottomRow();
            time=0;
        }
        bubble();
        squeezeCols();
//        if(slideCol()){xM+=W/2;}
        repaint();
    }

}
