package graphics;

/*Content inside the window. Each game can be coded as a panel which can be swapped
in and out of the window.
* */


import javax.swing.*;
import java.awt.event.*;

public class Panel extends JPanel
    implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{

    public WinApp parentWindow; //The WinApp the panel is attached to

    public Panel(){
        //Add listeners
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);

        //Set Focus
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.grabFocus();
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
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {}
}
