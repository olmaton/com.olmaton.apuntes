package presentacion.utiles.olmjtable;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author olmaton
 */
public class OlmDefaultTableCellRenderer extends DefaultTableCellRenderer {
    Color color1 = new Color(253, 253, 253);
    Color color2 = new Color(240, 240, 240);
    boolean colorIntercalado=false;
    HashMap<Integer, Color> background = new HashMap();
    HashMap<Integer, Color> foreground = new HashMap();
    HashMap<Integer, Integer> align = new HashMap();
    HashMap<Integer, Integer> font = new HashMap();
    
    public void setAlign(HashMap<Integer, Integer> align){
        this.align = align;
    }
    
    public void setBackground(HashMap<Integer, Color> colors){
        this.background = colors;
    }
    
    public void setForeground(HashMap<Integer, Color> foreground){
        this.foreground = foreground;
    }
    
    public void setFont(HashMap<Integer, Integer> font){
        this.font = font;
    }
    
    public OlmDefaultTableCellRenderer() {     
    }
    
    public void setColorsAlternate(Color color1, Color color2) {
       this.color1 = color1;
       this.color2 = color2;
    }

    public void setAlternate(boolean colorIntercalado) {
        this.colorIntercalado = colorIntercalado;
    }    
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
              
        if (!isSelected) {
            c.setBackground(Color.WHITE);
            
            if (colorIntercalado) {
                if(row%2==0){
                    c.setBackground(color1);
                }else{
                    c.setBackground(color2);
                }
            }
            
            if (background.containsKey(column)) {
                c.setBackground(background.get(column));
            }
        }
        
        if(align.containsKey(column)){
            ((DefaultTableCellRenderer)c).setHorizontalAlignment(align.get(column));
        }
        
        if(font.containsKey(column)){
            c.setFont(c.getFont().deriveFont(font.get(column)));
        }
        
        c.setForeground(Color.BLACK);
        if(foreground.containsKey(column)){
            c.setForeground(foreground.get(column));
        }
       
        return c;
    }
}
