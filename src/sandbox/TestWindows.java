package sandbox;

import global.UV;
import graphics.G;
import graphics.WinApp;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TestWindows extends WinApp{
    //Multiple windows test:

    public static WinApp ourWindow ;
    public static WinApp firstPanel;


    public TestWindows(){
        super("Test Windows", UV.currentScreenWidth,UV.currentScreenHeight);
        System.out.println("Test Windows Loaded");
    }


    public static void main(String[] args){
        ourWindow = new TestWindows();
        firstPanel = new tMain(ourWindow);
        ourWindow.PANEL = firstPanel;
        ourWindow.launch();
    }
}
