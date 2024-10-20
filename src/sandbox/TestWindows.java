package sandbox;

import global.UV;
import graphics.G;
import graphics.WinApp;
import java.awt.*;

public class TestWindows extends WinApp{
    //Multiple windows test:
    
    public static WinApp ourWindow ;
    public static WinApp nextWindow;
    public TestWindows(){
        super("Test Windows", UV.currentScreenWidth,UV.currentScreenHeight);
    }

    public void paintComponent(Graphics g){
        G.clearScreen(g,Color.RED);
        g.setColor(Color.RED);
        g.fillOval(100,150,200,300);
    }

    public static void main(String[] args){
        System.out.println("TestWindows is running");
        ourWindow = new WinApp("Test 1",10,10);
        nextWindow = new WinApp("Test 2",10,10);

        ourWindow.PANEL = new WinApp("AA",100,100);
        ourWindow.launch();

        nextWindow.PANEL= new TestWindows();
        nextWindow.launch();
    }
}
