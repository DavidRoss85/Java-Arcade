package graphics;

import java.awt.*;

public interface I {
    public interface Area{
        public boolean clicked(int x, int y);
        public boolean hovered(int x, int y);
    }
    public interface Show{
        public void show(Graphics g);
    }
}
