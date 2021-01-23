
package presentacion.utiles.olmjtable;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ButtonRender extends JButton implements TableCellRenderer {
  public ButtonRender(ImageIcon imageIcon) {
    setBackground(new Color(0, 0, 0,0));
    setIcon(imageIcon);
    setBorderPainted(false);
  }
   
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {    
      
    return this;
  }
}