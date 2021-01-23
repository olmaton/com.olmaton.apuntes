package presentacion;

import configuracion.Configuracion;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import presentacion.interfaces.PrincipalInterface;
import presentacion.utiles.Imagenes;
import servicios.Sesion;


/**
 *
 * @author olmaton
 */
public class FrmPrincipal extends javax.swing.JFrame implements ComponentListener,PrincipalInterface{
        
    JPanel panelActivo;
    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle(Configuracion.APP_NOMBRE);
        cargarCuentas();
        pnlContenedor.addComponentListener(this);
        this.setIconImage(Imagenes.getImage("icon"));
    }
    
    private void cargarCuentas(){
        pnlContenedor.removeAll();
        panelActivo = new PnlApuntes(this);
        panelActivo.setSize(pnlContenedor.getSize());
        panelActivo.setVisible(true);
        pnlContenedor.add(panelActivo);        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenedor = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuCambiarUsuario = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivo");

        mnuCambiarUsuario.setText("Cambiar de usuario");
        mnuCambiarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCambiarUsuarioActionPerformed(evt);
            }
        });
        jMenu1.add(mnuCambiarUsuario);

        mnuSalir.setText("Salir");
        mnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalirActionPerformed(evt);
            }
        });
        jMenu1.add(mnuSalir);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuSalirActionPerformed

    private void mnuCambiarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCambiarUsuarioActionPerformed
        cambiarDeUsuario();
    }//GEN-LAST:event_mnuCambiarUsuarioActionPerformed
  
    private void cambiarDeUsuario(){
        Sesion.getInstancia().setUsuario(null);
        new FrmLogin().setVisible(true);
        this.dispose();
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
        panelActivo.setSize(pnlContenedor.getSize());
        pnlContenedor.updateUI();
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
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mnuCambiarUsuario;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JPanel pnlContenedor;
    // End of variables declaration//GEN-END:variables

    @Override
    public JFrame getFrame() {
        return this;
    }
}
