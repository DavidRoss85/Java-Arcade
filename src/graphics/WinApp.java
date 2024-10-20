package graphics;

import global.UC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WinApp extends JPanel
        implements MouseListener, MouseMotionListener, KeyListener {

//    public static JFrame FRAME;
//    public static JFrame FRAME2;
//    public static String TITLE = "No Name";
//    public static WinApp PANEL;
//    public static Dimension PREF_SIZE = new Dimension(UC.defaultScreenWidth,UC.defaultScreenHeight);
//
//    public WinApp(String title, int width, int height){
//        TITLE = title; PREF_SIZE = new Dimension(width,height);
//    }
//
//    public Dimension getPreferredSize(){return PREF_SIZE;}
//
//    private static void createAndShowGUI(){
//        FRAME = new JFrame(TITLE);
//        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        FRAME.addKeyListener(PANEL);
//        FRAME.getContentPane().add(PANEL);
//        FRAME.pack();
//        FRAME.setVisible(true);
//        FRAME2 = new JFrame(TITLE + " 2");
//        FRAME2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        FRAME2.addKeyListener(PANEL);
//        FRAME2.getContentPane().add(PANEL);
//        FRAME2.pack();
//        FRAME2.setVisible(true);
//    }
//
//    public static void launch(){
//        // add in the listeners first
//        PANEL.addMouseListener(PANEL); // mouseListeners added to panel
//        PANEL.addMouseMotionListener(PANEL);
//
//        javax.swing.SwingUtilities.invokeLater(
//                new Runnable(){ public void run(){createAndShowGUI();} }
//        );
//    }


    public JFrame FRAME;
    public String TITLE = "No Name";
    public WinApp PANEL;
    public Dimension PREF_SIZE = new Dimension(UC.defaultScreenWidth,UC.defaultScreenHeight);

    public WinApp(String t, int width, int height){
        this.TITLE = t; PREF_SIZE = new Dimension(width,height);
    }

    public Dimension getPreferredSize(){return PREF_SIZE;}

    private void createAndShowGUI(){
        this.FRAME = new JFrame(this.TITLE);
        this.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.FRAME.addKeyListener(this.PANEL);
        this.FRAME.getContentPane().add(this.PANEL);
        this.FRAME.pack();
        this.FRAME.setVisible(true);
    }

    public void launch(){
        // add in the listeners first
        this.PANEL.addMouseListener(PANEL); // mouseListeners added to panel
        this.PANEL.addMouseMotionListener(PANEL);

        javax.swing.SwingUtilities.invokeLater(
                new Runnable(){ public void run(){createAndShowGUI();} }
        );
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}

//public JFrame FRAME;
//public String TITLE = "No Name";
//public WinApp PANEL = new WinApp(this.TITLE,100,100);
//public Dimension PREF_SIZE = new Dimension(UC.defaultScreenWidth,UC.defaultScreenHeight);
//
//public WinApp(String t, int width, int height){
//    this.TITLE = t; PREF_SIZE = new Dimension(width,height);
//}
//
//public Dimension getPreferredSize(){return PREF_SIZE;}
//
//private void createAndShowGUI(){
//    this.FRAME = new JFrame(TITLE);
//    this.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    this.FRAME.addKeyListener(PANEL);
//    this.FRAME.pack();
//    this.FRAME.setVisible(true);
//}
//
//public void launch(){
//    // add in the listeners first
//    this.PANEL.addMouseListener(PANEL); // mouseListeners added to panel
//    this.PANEL.addMouseMotionListener(PANEL);
//
//    javax.swing.SwingUtilities.invokeLater(
//            new Runnable(){ public void run(){createAndShowGUI();} }
//    );
//}