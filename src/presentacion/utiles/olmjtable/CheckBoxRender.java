package presentacion.utiles.olmjtable;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class CheckBoxRender extends JCheckBox implements TableCellRenderer {

    public CheckBoxRender() {        
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            //System.out.println("CheckBoxRender "+": "+value);
            setSelected(((Boolean) value));   
            if(isSelected)setBackground(table.getSelectionBackground());
            else setBackground(table.getBackground());
        return this;
    }
}
