package nonmerci;

import javax.swing.*;
import java.awt.*;

/**
 * Created by William on 23/11/2014.
 */
public class fondPanel extends JPanel {
    private Image img;

    public fondPanel(Image img){
        this.img = img;
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}
