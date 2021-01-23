package configuracion;

import java.awt.Color;

/**
 *
 * @author olmaton
 */
public class Colores {
    //General
    private static final int[] PRINCIPAL = {93,164,236};
    private static final int[] PRINCIPAL_OSCURO = {65,126,187};
    
    private static final int[] GRIS = new int[]{230, 230, 230};
    private static final int[] GRIS_OSCURO = new int[]{210, 210, 210};
    
    //Menus
    private static final int[] MENU_SUPERIOR = {251,253,255};
    private static final int[] MENU_SUPERIOR_BOTON = {230,230,230};
    
    //Alertas
    private static final int[] ALERT_0 = new int[]{51,204,0};
    private static final int[] ALERT_1 = new int[]{0,204,255};
    private static final int[] ALERT_2 = new int[]{255,153,102};
    private static final int[] ALERT_3 = new int[]{255,102,102};
    private static final int[] ALERT_0_DARK = new int[]{51,175,59};
    private static final int[] ALERT_1_DARK = new int[]{0,181,234};
    private static final int[] ALERT_2_DARK = new int[]{241,127,70};
    private static final int[] ALERT_3_DARK = new int[]{230,73,73};
    
    public static Color getPrincipal(){
        return new Color(PRINCIPAL[0],PRINCIPAL[1],PRINCIPAL[2]);
    }
    
    public static Color getPrincipalOscuro(){
        return new Color(PRINCIPAL_OSCURO[0],PRINCIPAL_OSCURO[1],PRINCIPAL_OSCURO[2]);
    }
    
    public static Color getMenuLateral(){
        return new Color(MENU_SUPERIOR[0],MENU_SUPERIOR[1],MENU_SUPERIOR[2]);
    }
    
    public static Color getMenuSuperiorBoton(){
        return new Color(MENU_SUPERIOR_BOTON[0],MENU_SUPERIOR_BOTON[1],MENU_SUPERIOR_BOTON[2]);
    }
    
    public static Color getGris(){
        return new Color(GRIS[0],GRIS[1],GRIS[2]);
    }
    
    public static Color getGrisOscuro(){
        return new Color(GRIS_OSCURO[0],GRIS_OSCURO[1],GRIS_OSCURO[2]);
    }
    
     public static Color getAlert0() {
        return new Color(ALERT_0[0], ALERT_0[1], ALERT_0[2], 255);
    }
        
    public static Color getAlert1() {
        return new Color(ALERT_1[0], ALERT_1[1], ALERT_1[2], 255);
    }
    
    public static Color getAlert2() {
        return new Color(ALERT_2[0], ALERT_2[1], ALERT_2[2], 255);
    }
    
    public static Color getAlert3() {
        return new Color(ALERT_3[0], ALERT_3[1], ALERT_3[2], 255);
    }
    
    public static Color getAlert0Dark() {
        return new Color(ALERT_0_DARK[0], ALERT_0_DARK[1], ALERT_0_DARK[2], 255);
    }
        
    public static Color getAlert1Dark() {
        return new Color(ALERT_1_DARK[0], ALERT_1_DARK[1], ALERT_1_DARK[2], 255);
    }
    
    public static Color getAlert2Dark() {
        return new Color(ALERT_2_DARK[0], ALERT_2_DARK[1], ALERT_2_DARK[2], 255);
    }
    
    public static Color getAlert3Dark() {
        return new Color(ALERT_3_DARK[0], ALERT_3_DARK[1], ALERT_3_DARK[2], 255);
    }
}
