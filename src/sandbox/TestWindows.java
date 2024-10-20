package sandbox;

import games.Collapse;
import global.UV;
import graphics.Panel;
import graphics.WinApp;
import menus.MainMenu;

public class TestWindows extends WinApp{
    //Multiple windows test:

    public static WinApp thisWindow;
    public static Panel firstPanel;
    public static int W = UV.screenWidth, H= UV.screenHeight;

    public TestWindows(){
        super("Main Window", W,H);
    }


    public static void main(String[] args){
        thisWindow = new TestWindows();
        firstPanel = new MainMenu(thisWindow);
        thisWindow.mainPanel = firstPanel;
        thisWindow.launch();
    }
}
