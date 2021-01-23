package presentacion.utiles;

import configuracion.Colores;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JToggleButton;

/**
 *
 * @author olmaton
 */
public class Botones {
    public static void estiloBotonMenu(JToggleButton btn){
        BotonesEstilos.botonSwitch(btn,Colores.getPrincipal(), Colores.getMenuSuperiorBoton(), Color.white,Color.BLACK);
    }
    
    public static void iconoBotonMenu(JToggleButton btn){
        btn.setIcon(Imagenes.getIconSize(btn.getText(), 32, 32));        
    }
    
    public static void estiloBotonCancelar(JButton btn){
        BotonesEstilos.boton(btn, Colores.getGris(),Colores.getGrisOscuro(), Color.DARK_GRAY, Color.DARK_GRAY);
    }
    
    public static void estiloBotonGuardar(JButton btn){
        BotonesEstilos.boton(btn, Colores.getPrincipalOscuro(),Colores.getPrincipal(), Color.WHITE, Color.WHITE);
    }
    
    public static void estiloBotonAceptar(JButton btn){
        BotonesEstilos.boton(btn, Colores.getPrincipalOscuro(),Colores.getPrincipal(), Color.WHITE, Color.WHITE);
    }
    
    public static void estiloBotonCerrar(JButton btn){
        BotonesEstilos.boton(btn,Colores.getGris(), Colores.getGrisOscuro(), Color.GRAY, Color.BLACK);
    }
}
