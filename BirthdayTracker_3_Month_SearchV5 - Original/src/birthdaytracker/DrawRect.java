package birthdaytracker;

import javax.swing.*;
import java.awt.*;

public class DrawRect extends JPanel {
    // paintComponent is built in method?
    public void paintComponent(Graphics g) { // take an object class of Graphics
        super.paintComponent(g);

        g.drawRect(426,301,157,110);

    }
}
