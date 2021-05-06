package presentacion;

import configuracion.Colores;
import controlador.MovimientoCuentaACuentaController;
import java.util.ArrayList;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Tipo;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import presentacion.interfaces.MovimientosCuentaACuentaInterface;
import presentacion.utiles.Botones;
import presentacion.utiles.DatePickerUtil;
import presentacion.utiles.DialogMessage;
import presentacion.utiles.Imputs;

/**
 *
 * @author olmaton
 */
public class JdiMovimientoCuentaACuenta extends javax.swing.JDialog implements MovimientosCuentaACuentaInterface, KeyListener {

    MovimientoCuentaACuentaController controlador;
    private boolean exito=false;
    public JdiMovimientoCuentaACuenta(Frame parent) {
        super(parent, true);        
        initComponents();
        this.setResizable(false);
        this.setTitle("Movimiento entre cuentas");
        setStyles();
        setImputsFormat();
        setKeyListener();
        this.setLocationRelativeTo(parent.getParent());
        controlador = new MovimientoCuentaACuentaController(this);
        recargarDatosIniciales();
        controlador.inicializar();
    }
    
    private void setImputsFormat() {
        Imputs.setDecimal(txtMonto);
        Imputs.setInteger(txtCantidad);
        DatePickerUtil.setFechaHoraMediaNoche(dtpFechaHora);        
    }
    
     private void recargarDatosIniciales() {
        controlador.listarTipos();
        controlador.listarCuentas();
    }
    
    void setKeyListener() {
        txtDescripcion.addKeyListener(this);
        setKeyListenerRecurrente(pnlFormulario);
    }

    void setKeyListenerRecurrente(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) {
                c.addKeyListener(this);
            } else if (c instanceof JComboBox) {
                c.addKeyListener(this);
            } else if (c instanceof Container) {
                setKeyListenerRecurrente((Container) c);
            }
        }
    }

    private void setStyles() {

        pnlControles.setBackground(Colores.getGris());
        Botones.estiloBotonGuardar(btnGuardar);
        Botones.estiloBotonCancelar(btnCancelar);
        DatePickerUtil.setFechaHoraStyle(dtpFechaHora);
    }

    private void llenarComboCuentasOrigen(ArrayList<Cuenta> lista) {
        cboCuenta.removeAllItems();
        for (Cuenta cuenta : lista) {
            cboCuenta.addItem(cuenta);
        }
    }

    private void llenarComboCuentasDestino(ArrayList<Cuenta> lista) {
        cboCuentaDestino.removeAllItems();
        for (Cuenta cuenta : lista) {
            cboCuentaDestino.addItem(cuenta);
        }
        if(lista.size()>1){
            cboCuentaDestino.setSelectedIndex(1);
        }
    }

    private void llenarCombosCuentas(ArrayList<Cuenta> lista) {
        llenarComboCuentasOrigen(lista);
        llenarComboCuentasDestino(lista);
    }

    

    private void llenarComboTiposOrigen(ArrayList<Tipo> lista) {
        cboMovimientoTipo.removeAllItems();
        for (Tipo tipo : lista) {
            cboMovimientoTipo.addItem(tipo);
        }
    }

    private void llenarComboTiposDestino(ArrayList<Tipo> lista) {
        cboMovimientoTipoDestino.removeAllItems();
        for (Tipo tipo : lista) {
            cboMovimientoTipoDestino.addItem(tipo);
        }
        if(lista.size()>1){
            cboMovimientoTipoDestino.setSelectedIndex(1);
        }
    }

    private void agregaTipo() {
        JdiMovimientosTipo jdiMovimientoTipo = new JdiMovimientosTipo((java.awt.Frame) this.getParent());
        jdiMovimientoTipo.setLocationRelativeTo(this);
        jdiMovimientoTipo.setVisible(true);
        controlador.listarTipos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        pnlFormulario = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        dtpFechaHora = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel7 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        chkEsPresupuesto = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cboCuenta = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cboMovimientoTipo = new javax.swing.JComboBox<>();
        btnAgregarTipo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnAgregarTipoDestino = new javax.swing.JButton();
        cboMovimientoTipoDestino = new javax.swing.JComboBox<>();
        cboCuentaDestino = new javax.swing.JComboBox<>();
        pnlControles = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de movimiento"));

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Nombre:");

        txtNombre.setText("Este es un nombre de ejemplo");

        jLabel4.setText("Descripción:");

        txtDescripcion.setColumns(3);
        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(2);
        txtDescripcion.setText("Esta es una descripcion de movimiento. Texto de ejemplo de inserción de texto.");
        jScrollPane2.setViewportView(txtDescripcion);

        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Fecha y hora:");

        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Monto:");

        txtMonto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMonto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMonto.setText("12.89");
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Cantidad:");

        txtCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setText("1");
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });

        chkEsPresupuesto.setText("Es presupuesto");

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtpFechaHora, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMonto)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidad)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(chkEsPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(2, 2, 2)
                .addComponent(dtpFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(2, 2, 2)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkEsPresupuesto)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuenta de origen"));

        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Cuenta:");

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Tipo movimiento");

        btnAgregarTipo.setText("+");
        btnAgregarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboCuenta, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cboMovimientoTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(2, 2, 2)
                .addComponent(cboCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMovimientoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuenta de destino"));

        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Tipo movimiento");

        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Cuenta:");

        btnAgregarTipoDestino.setText("+");
        btnAgregarTipoDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTipoDestinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboCuentaDestino, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cboMovimientoTipoDestino, 0, 219, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarTipoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(2, 2, 2)
                .addComponent(cboCuentaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMovimientoTipoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarTipoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlControles.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setMnemonic('G');
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControlesLayout = new javax.swing.GroupLayout(pnlControles);
        pnlControles.setLayout(pnlControlesLayout);
        pnlControlesLayout.setHorizontalGroup(
            pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlControlesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlControlesLayout.setVerticalGroup(
            pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        controlador.inicializar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        controlador.procesar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO addColumn your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO addColumn your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnAgregarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTipoActionPerformed
        agregaTipo();
    }//GEN-LAST:event_btnAgregarTipoActionPerformed

    private void btnAgregarTipoDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTipoDestinoActionPerformed
        agregaTipo();
    }//GEN-LAST:event_btnAgregarTipoDestinoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarTipo;
    private javax.swing.JButton btnAgregarTipoDestino;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Cuenta> cboCuenta;
    private javax.swing.JComboBox<Cuenta> cboCuentaDestino;
    private javax.swing.JComboBox<Tipo> cboMovimientoTipo;
    private javax.swing.JComboBox<Tipo> cboMovimientoTipoDestino;
    private javax.swing.JCheckBox chkEsPresupuesto;
    private com.github.lgooddatepicker.components.DateTimePicker dtpFechaHora;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlControles;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarMensaje(String mensaje, int code) {
        DialogMessage.getInstancia().setMensaje((Frame) this.getParent(), mensaje, code);
    }

    @Override
    public boolean validar() {
        
        if (cboCuenta.getSelectedIndex() < 0) {
            mostrarMensaje("Seleccione una cuenta origen válida.", 1);
            cboCuenta.requestFocus();
            return false;
        }
        
        if (cboCuentaDestino.getSelectedIndex() < 0) {
            mostrarMensaje("Seleccione una cuenta destino válida.", 1);
            cboCuentaDestino.requestFocus();
            return false;
        }
        
        if (cboCuentaDestino.getSelectedIndex() ==cboCuenta.getSelectedIndex()) {
            mostrarMensaje("La cuenta de destino no puede ser la misma que la de origen.", 1);
            cboCuentaDestino.requestFocus();
            return false;
        }
        
        if (cboMovimientoTipo.getSelectedIndex() ==cboMovimientoTipoDestino.getSelectedIndex()) {
            mostrarMensaje("La tipo de movimiento de la cuenta de destino no puede ser el mismo que el origen.", 1);
            cboMovimientoTipoDestino.requestFocus();
            return false;
        }

        if (txtNombre.getText().trim().isEmpty()) {
            mostrarMensaje("Ingrese nombre.", 1);
            txtNombre.requestFocus();
            return false;
        }

        if (txtMonto.getText().trim().isEmpty()) {
            mostrarMensaje("Ingrese monto.", 1);
            txtMonto.requestFocus();
            return false;
        }

        if (txtCantidad.getText().trim().isEmpty()) {
            mostrarMensaje("Ingrese cantidad.", 1);
            txtCantidad.requestFocus();
            return false;
        }

        if (dtpFechaHora.datePicker.getDate() == null) {
            mostrarMensaje("Seleccione una fecha válida.", 1);
            dtpFechaHora.datePicker.getComponentDateTextField().requestFocus();
            return false;
        }

        if (dtpFechaHora.timePicker.getTime() == null) {
            mostrarMensaje("Seleccione una hora válida.", 1);
            dtpFechaHora.timePicker.getComponentTimeTextField().requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public Movimiento getMovimientoBase() {
        Movimiento nuevo = new Movimiento();
        nuevo.setNombre(txtNombre.getText().trim());
        nuevo.setDescripcion(txtDescripcion.getText().trim());
        nuevo.setCantidad(Integer.valueOf(txtCantidad.getText()));
        nuevo.setValor_unitario_egreso(Double.valueOf(txtMonto.getText()));
        nuevo.setValor_unitario_ingreso(Double.valueOf(txtMonto.getText()));
        nuevo.setFecha_hora(dtpFechaHora.getDateTimePermissive());        
        nuevo.setEs_presupuesto(chkEsPresupuesto.isSelected() ? 1 : 0);
        return nuevo;
    }

    @Override
    public void limpiar() {
        txtNombre.setText("");
        txtNombre.requestFocus();
        txtDescripcion.setText("");
        txtMonto.setText("0.00");
        txtCantidad.setText("1");
        txtNombre.requestFocus();
        chkEsPresupuesto.setSelected(false);
    }
    
    @Override
    public Cuenta getCuentaOrigen() {
        return (Cuenta) cboCuenta.getSelectedItem();
    }

    @Override
    public Cuenta getCuentaDestino() {
        return (Cuenta) cboCuentaDestino.getSelectedItem();
    }
    @Override
    public Tipo  getTipoMovimientoOrigen() {
        return (Tipo ) cboMovimientoTipo.getSelectedItem();
    }

    @Override
    public Tipo  getTipoMovimientoDestino() {
        return (Tipo ) cboMovimientoTipoDestino.getSelectedItem();
    }

    @Override
    public void llenarCuentas(ArrayList<Cuenta> lista) {
        llenarCombosCuentas(lista);
    }

    @Override
    public void llenarTipos(ArrayList<Tipo> lista) {
        llenarComboTiposOrigen(lista);
        llenarComboTiposDestino(lista);
        
    }

    @Override
    public void finalizar(boolean exito) {
        this.exito = exito;
        this.dispose();
    }

    public boolean isExito() {
        return exito;
    }

    
    
    
    

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_G) {
            controlador.procesar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
