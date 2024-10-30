package sandbox;

import games.tetris.TetrisBasic;
import global.UV;
import graphics.G;
import graphics.WinApp;
import menus.MainMenu;

public class TestWindows extends WinApp{
    //Multiple windows test:

    public static int W = UV.screenWidth, H= UV.screenHeight;

    public TestWindows(){
        super("Main Window", W,H);
    }


    public static void main(String[] args){

        UV.mainWindow = new TestWindows();

        UV.mainMenuPanel = new MainMenu();

        UV.mainWindow.mainPanel = UV.mainMenuPanel;
        UV.mainWindow.launch();

//        UV.tetrisPanel=new TetrisBasic();
    }
}
