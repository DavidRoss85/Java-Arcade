package graphics;

/*This Class handles the window generation
* The content inside the window is passed into using the
* changePanel() method.
* Initialize mainPanel to be able to call it at anytime
* */


import javax.swing.*;
import java.awt.*;

public class WinApp extends JPanel {

    public JFrame FRAME;
    public String TITLE;
    public Panel mainPanel;
    public Dimension PREF_SIZE;

    public WinApp(String t, int width, int height){
        this.TITLE = t; PREF_SIZE = new Dimension(width,height);
    }

    public Dimension getPreferredSize(){return PREF_SIZE;}

    private void createAndShowGUI(){
        this.FRAME = new JFrame(this.TITLE);
        this.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainPanel.addKeyListener(this.mainPanel);
        this.FRAME.getContentPane().add(this.mainPanel);
        this.FRAME.setSize(PREF_SIZE);
        this.FRAME.setLocationRelativeTo(null);
//        this.FRAME.pack();
        this.FRAME.setVisible(true);
    }

    public void changePanel(Panel newPanel){
        this.FRAME.getContentPane().remove(0);
        newPanel.addMouseListener(newPanel);
        this.FRAME.getContentPane().add(newPanel);
        this.FRAME.revalidate();
        this.FRAME.repaint();
    }

    public void launch(){
        // add in the listeners first
        this.mainPanel.addMouseListener(mainPanel); // mouseListeners added to panel
        this.mainPanel.addMouseMotionListener(mainPanel);
        javax.swing.SwingUtilities.invokeLater(
                this::createAndShowGUI
        );
    }

}