package presentacion;

import configuracion.Constantes;
import configuracion.Colores;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import presentacion.interfaces.ApuntesInterface;
import presentacion.interfaces.PrincipalInterface;
import presentacion.utiles.Botones;

/**
 *
 * @author olmaton
 */
public class PnlApuntes extends javax.swing.JPanel implements ApuntesInterface, ComponentListener {

    String menuActivo = "";
    PrincipalInterface principalInterface;
    JPanel panelActivo;

    public PnlApuntes(PrincipalInterface principalInterface) {
        initComponents();
        inicializarEstilos();
        this.principalInterface = principalInterface;
        pnlContenedorApuntes.addComponentListener(this);
        evaluarMenu(Constantes.APUNTES_MOVIMIENTOS);
    }

    private void inicializarEstilos() {
        pnlMenuSuperior.setBackground(Colores.getMenuLateral());
        funcionalidadMenu();
    }

    private void funcionalidadMenu() {
        tbtnMovimientos.setText(Constantes.APUNTES_MOVIMIENTOS);
        tbtnCuentas.setText(Constantes.APUNTES_CUENTAS);
        Enumeration<AbstractButton> enumeration = btgMenuSuperior.getElements();
        while (enumeration.hasMoreElements()) {
            JToggleButton button = (JToggleButton) enumeration.nextElement();
            Botones.estiloBotonMenu(button);
            Botones.iconoBotonMenu(button);
            button.addActionListener((ActionEvent e) -> {
                evaluarMenu(button.getText());
            });
        }       
    }

    private void evaluarMenu(String nombreMenu) {
        if (menuActivo.equals(nombreMenu)) {
            return;
        }   

        switch (nombreMenu) {
            case Constantes.APUNTES_MOVIMIENTOS: {
                tbtnMovimientos.setSelected(true);
                panelActivo = PnlMovimientos.getInstancia(this);
                break;
            }

            case Constantes.APUNTES_CUENTAS: {
                tbtnCuentas.setSelected(true);
                panelActivo = PnlCuentas.getInstancia(this);
                break;
            }

            default: {
                tbtnMovimientos.setSelected(true);
                panelActivo = PnlMovimientos.getInstancia(this);
                break;
            }
        }
        
        //System.out.println("Habilitado: "+panelActivo.isEnabled());
        //if(!panelActivo.isEnabled()) return;
        
        menuActivo = nombreMenu;
        cargarPanel(panelActivo);
    }

    void cargarPanel(JPanel panel) {
        pnlContenedorApuntes.removeAll();
        panel.setSize(pnlContenedorApuntes.getSize());
        panel.setLocation(0, 0);
        panel.setVisible(true);
        pnlContenedorApuntes.add(panel);
        pnlContenedorApuntes.updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgMenuSuperior = new javax.swing.ButtonGroup();
        pnlMenuSuperior = new javax.swing.JPanel();
        tbtnMovimientos = new javax.swing.JToggleButton();
        tbtnCuentas = new javax.swing.JToggleButton();
        pnlContenedorApuntes = new javax.swing.JPanel();

        pnlMenuSuperior.setBackground(new java.awt.Color(204, 204, 255));

        btgMenuSuperior.add(tbtnMovimientos);
        tbtnMovimientos.setText("Movimientos");

        btgMenuSuperior.add(tbtnCuentas);
        tbtnCuentas.setText("Cuentas");

        javax.swing.GroupLayout pnlMenuSuperiorLayout = new javax.swing.GroupLayout(pnlMenuSuperior);
        pnlMenuSuperior.setLayout(pnlMenuSuperiorLayout);
        pnlMenuSuperiorLayout.setHorizontalGroup(
            pnlMenuSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbtnMovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tbtnCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(852, Short.MAX_VALUE))
        );
        pnlMenuSuperiorLayout.setVerticalGroup(
            pnlMenuSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMenuSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbtnMovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtnCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlContenedorApuntes.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout pnlContenedorApuntesLayout = new javax.swing.GroupLayout(pnlContenedorApuntes);
        pnlContenedorApuntes.setLayout(pnlContenedorApuntesLayout);
        pnlContenedorApuntesLayout.setHorizontalGroup(
            pnlContenedorApuntesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlContenedorApuntesLayout.setVerticalGroup(
            pnlContenedorApuntesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenuSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlContenedorApuntes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenuSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlContenedorApuntes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgMenuSuperior;
    private javax.swing.JPanel pnlContenedorApuntes;
    private javax.swing.JPanel pnlMenuSuperior;
    private javax.swing.JToggleButton tbtnCuentas;
    private javax.swing.JToggleButton tbtnMovimientos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentResized(ComponentEvent e) {
        if (panelActivo != null) {
            panelActivo.setSize(pnlContenedorApuntes.getSize());
            pnlContenedorApuntes.updateUI();
        }

    }

    @Override
    public void componentMoved(ComponentEvent e) {
        System.out.println("componentMoved");
    }

    @Override
    public void componentShown(ComponentEvent e) {
        System.out.println("componentShown");
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        System.out.println("componentHidden");
    }

    @Override
    public JFrame getFramePrincipal() {
        return principalInterface.getFrame();
    }

    @Override
    public void cambiarVentana(String destino) {
        evaluarMenu(destino);
    }
}
