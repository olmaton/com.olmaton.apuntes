package presentacion.utiles;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author olmaton
 */
public class Imagenes {

    public static Image getImage(String url) {
        return new ImageIcon(ClassLoader.getSystemResource("presentacion/imagenes/" + url + ".png")).getImage();
    }
    
    public static ImageIcon getIcon(String url) {
        return new ImageIcon(ClassLoader.getSystemResource("presentacion/imagenes/" + url + ".png"));
    }
    
    public static ImageIcon getIconSize(String url, int w,int h) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("presentacion/imagenes/" + url + ".png"));
        icon = new ImageIcon(icon.getImage().getScaledInstance(w, h,  Image.SCALE_SMOOTH));
        return icon; 
    }
}
