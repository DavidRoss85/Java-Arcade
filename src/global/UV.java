package global;

import games.collapse.Collapse2;
import games.collapse.CollapseBasic;
import games.tetris.Tetris2;
import games.tetris.TetrisBasic;
import graphics.G;
import graphics.Panel;
import graphics.WinApp;
import menus.MainMenu;
import sandbox.StatChecker;
import sandbox.TestWindows;

import java.util.ArrayList;

public class UV {
    public static int screenWidth = UC.defaultWidth;
    public static int screenHeight = UC.defaultHeight;

    public static WinApp mainWindow;
    public static Panel mainMenuPanel;
    public static Panel tetrisPanel = new Tetris2();
    public static Panel collapsePanel = new Collapse2();
    public static Panel statPanel = new StatChecker();
    public static Panel oldTetris = new TetrisBasic();
    public static Panel oldCollapse = new CollapseBasic();

    public static G.BrickWall brickBackground = G.generateBrickWall(25,47);

//    public static ArrayList<MainMenu.MenuItem> mainMenuItems = new ArrayList<>();
}
