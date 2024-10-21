package sandbox;

import global.UV;
import graphics.G;
import graphics.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class StatChecker extends Panel implements ActionListener {

    public Timer timer;
    public int ticks;
    public long lastTime=System.currentTimeMillis();
    public long nowTime=System.currentTimeMillis();
    public int secondsElapsed = 0;
    int ticksPerSec = 0;

    public StatChecker(){
        timer = new Timer(0,this);
//        timer.start();
    }
    public void paintComponent(Graphics g){


        ticks++;
        nowTime=System.currentTimeMillis();
        if(nowTime>lastTime+1000){
            lastTime=nowTime;
            secondsElapsed++;
             ticksPerSec = ticks/secondsElapsed;
        }

        G.clearScreen(g);
        UV.brickBackground.show(g);
        Font gFont = new Font("Courier New",Font.BOLD,20);
        g.setColor(Color.BLACK);
        g.setFont(gFont);
        g.drawString("Ticks: " + ticks,400,380);
        g.drawString("Timer: " + secondsElapsed ,400,400);
        g.drawString("Time: " + nowTime ,400,420);
        g.drawString("Ticks/s: " + ticksPerSec ,400,440);
        repaint();
    }

    public void keyPressed(KeyEvent ke){
        int vk= ke.getKeyCode();

        if(vk==KeyEvent.VK_ESCAPE){
            UV.mainWindow.changePanel(UV.mainMenuPanel);timer.stop();
        }else{
            timer.start();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
