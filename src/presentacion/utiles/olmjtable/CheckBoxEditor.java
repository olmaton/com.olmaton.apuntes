package presentacion.utiles.olmjtable;

import java.awt.*;
import javax.swing.*;

public class CheckBoxEditor extends DefaultCellEditor {

    public JCheckBox checkBox;

    public CheckBoxEditor(JCheckBox checkBox) {
        super(checkBox);
        this.checkBox = new JCheckBox();
        this.checkBox.setContentAreaFilled(true);
        this.checkBox.setHorizontalAlignment(SwingConstants.CENTER);
        this.checkBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.checkBox.setFocusPainted(false);
        this.checkBox.setOpaque(true);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        //System.out.println("CheckBoxEditor " + row+"-"+isSelected + ": " + value);
        checkBox.setSelected(((Boolean) value));
        checkBox.setName(row + "");
        checkBox.setFocusable(false);       
        return checkBox;
    }
}
