/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.utiles;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.metal.MetalToggleButtonUI;

/**
 *
 * @author olmaton
 */
public class BotonesEstilos {

    public static void botonSwitch(JToggleButton btn, Color selected, Color unselected, Color foregroundSelected, Color foregroundUnselected) {
        btn.setBackground(unselected);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(selected);
                btn.setForeground(foregroundSelected);
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(unselected);
                if (btn.isSelected()) {
                    btn.setForeground(foregroundSelected);
                } else {
                    btn.setForeground(foregroundUnselected);
                }
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            }
        });

        btn.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return selected;
            }
        });

        btn.addChangeListener((ChangeEvent e) -> {
            if (btn.isSelected()) {
                btn.setForeground(foregroundSelected);
            } else {
                btn.setForeground(foregroundUnselected);
            }
        });

    }

    public static void boton(JButton btn, Color background, Color backgroundEntered, Color foreground, Color foregroundEntered) {
        btn.setForeground(foreground);
        btn.setBackground(background);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(backgroundEntered);
                btn.setForeground(foregroundEntered);
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(background);
                btn.setForeground(foreground);
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        btn.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //Component button = e.getComponent();
                Font font = btn.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                btn.setFont(font.deriveFont(attributes));
            }

            @Override
            public void focusLost(FocusEvent e) {
                //Component button = e.getComponent();
                Font font = btn.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, null);
                btn.setFont(font.deriveFont(attributes));
            }
        });
    }
}
