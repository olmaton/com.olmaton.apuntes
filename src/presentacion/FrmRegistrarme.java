package presentacion;

import configuracion.Colores;
import controlador.RegistrarmeController;
import entidades.Usuario;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import presentacion.interfaces.RegistrarmeInterface;
import presentacion.utiles.Botones;
import presentacion.utiles.DialogMessage;
import presentacion.utiles.Imagenes;
import presentacion.utiles.JPanelFondo;

public class FrmRegistrarme extends javax.swing.JFrame  implements RegistrarmeInterface{

    RegistrarmeController  controlador;

    public FrmRegistrarme() {
        initComponents();
        setStyles();
        controlador = new RegistrarmeController(this);
    }

    private void setStyles() {
        this.setLocationRelativeTo(null);
        pnlVertical.setBackground(Colores.getPrincipal());
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
               
            }
        });
        setStyleBotones();
        setFondoBanner();
        txtContrasena1.setText("123");
        txtNombres.setText("Olmaton");
        txtEmail.setText("admin@olmaton.com");
        lblTitle.setForeground(Colores.getPrincipal());
        this.setIconImage(Imagenes.getImage("icon"));

    }

    private void setStyleBotones() {
        
        Botones.estiloBotonCancelar(btnIrLogin);
        Botones.estiloBotonAceptar(btnRegistrarme);
        Botones.estiloBotonCerrar(btnCerrar);
    }

    private void setFondoBanner() {        
        JPanelFondo pnlBanner = new JPanelFondo("/presentacion/imagenes/banner-registrarme.png");
        this.add(pnlBanner);        
        pnlBanner.setLocation(0, 0);
    }

    @Override
    public boolean validar() {
        if (txtNombres.getText().isEmpty()) {
            mostrarMensaje("Ingrese un nombre válido.", 2);
            txtNombres.requestFocus();
            return false;
        }
        
        if (txtEmail.getText().isEmpty()) {
            mostrarMensaje("Ingrese email válido.", 2);
            txtNombres.requestFocus();
            return false;
        }

        if (txtContrasena1.getText().isEmpty()) {
            mostrarMensaje("Ingrese contraseña.", 2);
            txtContrasena1.requestFocus();
            return false;
        }
        
         if (txtContrasena2.getText().isEmpty()) {
            mostrarMensaje("Repita contraseña.", 2);
            txtContrasena2.requestFocus();
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlVertical = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtNombres = new javax.swing.JTextField();
        txtContrasena1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnRegistrarme = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnIrLogin = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtContrasena2 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        pnlVertical.setBackground(new java.awt.Color(102, 153, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Nombres:");

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Contraseña:");

        btnRegistrarme.setText("REGISTRARME");
        btnRegistrarme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarmeActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(102, 102, 102));
        lblTitle.setText("Registrarme");

        btnIrLogin.setText("LOGIN");
        btnIrLogin.setToolTipText("");
        btnIrLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrLoginActionPerformed(evt);
            }
        });

        btnCerrar.setText("X");
        btnCerrar.setFocusable(false);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Email:");

        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Repitar contraseña:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCerrar))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombres)
                    .addComponent(txtEmail)
                    .addComponent(txtContrasena2)
                    .addComponent(txtContrasena1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnIrLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegistrarme, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(2, 2, 2)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(txtContrasena1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(2, 2, 2)
                .addComponent(txtContrasena2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarme, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIrLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout pnlVerticalLayout = new javax.swing.GroupLayout(pnlVertical);
        pnlVertical.setLayout(pnlVerticalLayout);
        pnlVerticalLayout.setHorizontalGroup(
            pnlVerticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVerticalLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlVerticalLayout.setVerticalGroup(
            pnlVerticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 329, Short.MAX_VALUE)
                .addComponent(pnlVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVertical, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIrLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrLoginActionPerformed
        irLogin();
    }//GEN-LAST:event_btnIrLoginActionPerformed

    private void btnRegistrarmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarmeActionPerformed
        controlador.registrarme();
    }//GEN-LAST:event_btnRegistrarmeActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        salir();
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnIrLogin;
    private javax.swing.JButton btnRegistrarme;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlVertical;
    private javax.swing.JPasswordField txtContrasena1;
    private javax.swing.JPasswordField txtContrasena2;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables

    private void salir() {
        System.exit(0);
    }

    private void irLogin() {
        new FrmLogin().setVisible(true);
        this.dispose();
    }
    
    
    @Override
    public void mostrarMensaje(String mensaje, int code) {
        DialogMessage.getInstancia().setMensaje(this, mensaje, code);
    }

    @Override
    public Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombres(txtNombres.getText());
        usuario.setEmail(txtEmail.getText());
        usuario.setPassword(txtContrasena1.getText());
        return usuario;
    }

    @Override
    public boolean contraseniasIguales() {
        return txtContrasena1.getText().trim().equals(txtContrasena2.getText().trim());
    }

    @Override
    public void irPrincipal() {
        new FrmPrincipal().setVisible(true);
        this.dispose();
    }

}
