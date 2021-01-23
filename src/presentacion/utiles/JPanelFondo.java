package presentacion.utiles;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author olmaton
 */
public class JPanelFondo extends JPanel {

    private final Image img;
    private final ImageIcon imgIcon;
    public JPanelFondo(String path) {
        this.imgIcon = new ImageIcon(getClass().getResource(path));
        this.img = imgIcon.getImage();
        Dimension size = new Dimension(this.img.getWidth(null), this.img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }   

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
