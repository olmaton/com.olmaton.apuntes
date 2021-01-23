package presentacion;

import com.github.lgooddatepicker.components.DateTimePicker;
import configuracion.Colores;
import entidades.Cuenta;
import entidades.Tipo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.dto.FiltroMovimientoDTO;
import presentacion.interfaces.MovimientoFiltrarInterface;
import presentacion.utiles.Botones;
import presentacion.utiles.DatePickerUtil;

/**
 *
 * @author olmaton
 */
public class JdiMovimientosFiltros extends javax.swing.JDialog {

  MovimientoFiltrarInterface vistaPadre;
    public JdiMovimientosFiltros(java.awt.Frame parent,MovimientoFiltrarInterface vistaPadre) {
        super(parent, false);
        initComponents();    
        this.setResizable(false);
        this.setTitle("Filtro de movimientos");
        setStyles();
        this.vistaPadre = vistaPadre;
    }
    
    private void setStyles() {
        pnlControles.setBackground(Colores.getGris());
        Botones.estiloBotonGuardar(btnGuardar);
        Botones.estiloBotonCancelar(btnCancelar);
        DatePickerUtil.setFechaHoraStyle(dtpFechaHoraInicio);
        DatePickerUtil.setFechaHoraStyle(dtpFechaHoraFin);
    }
    
    public FiltroMovimientoDTO getFiltro(){
        FiltroMovimientoDTO nuevo = new FiltroMovimientoDTO();
        nuevo.setCuenta(getFiltroCuenta());
        nuevo.setTipo(getFiltroTipo());
        nuevo.setMovimientoOPresupueso(getMovimientosOPresuespuestos());   
        nuevo.setFechaHoraFin(getFechaHoraFin());
        nuevo.setFechaHoraInicio(getFechaHoraInicio());
        return nuevo;
    }
    
    public void llenarCuentasFiltro(ArrayList<Cuenta> lista){
        cboCuentaFiltro.removeAllItems();
        Cuenta todos = new Cuenta(0);
        todos.setNombre("Todas");
        cboCuentaFiltro.addItem(todos);
        for (Cuenta cuenta : lista) {
            cboCuentaFiltro.addItem(cuenta);
        }
    }
    
     public void llenarTiposFiltro(ArrayList<Tipo> lista) {
        cboMovimientoTipoFiltro.removeAllItems();
        Tipo todos = new Tipo(0);
        todos.setNombre("Todos");
        cboMovimientoTipoFiltro.addItem(todos);
        for (Tipo tipo : lista) {
            cboMovimientoTipoFiltro.addItem(tipo);
        }
    }
     
    public Cuenta getFiltroCuenta() {
        return (Cuenta)cboCuentaFiltro.getSelectedItem();
    }
    
    public Tipo getFiltroTipo() {
        return (Tipo)cboMovimientoTipoFiltro.getSelectedItem();
    }
    
    public int getMovimientosOPresuespuestos() {
        int idx = cboMovimientosOPresuespuestos.getSelectedIndex();
      switch (idx) {
          case 0:
              return -1;
          case 1:
              return 0;
          default:
              return 1;
      }
    }
    
    public LocalDateTime getFechaHoraInicio(){
        if(dtpFechaHoraInicio.getTimePicker().getTime()==null&&dtpFechaHoraInicio.getDatePicker().getDate()==null){
            return null;
        }
        
        if(dtpFechaHoraInicio.getTimePicker().getTime()!=null&&dtpFechaHoraInicio.getDatePicker().getDate()!=null){
            return dtpFechaHoraInicio.getDateTimePermissive();
        }
        
        return dtpFechaHoraInicio.getDateTimePermissive();
    }
    
    public LocalDateTime getFechaHoraFin(){
        if(dtpFechaHoraFin.getTimePicker().getTime()==null&&dtpFechaHoraFin.getDatePicker().getDate()==null){
            return null;
        }
        
        if(dtpFechaHoraFin.getTimePicker().getTime()!=null&&dtpFechaHoraFin.getDatePicker().getDate()!=null){
            return dtpFechaHoraFin.getDateTimePermissive();
        }
        
        return dtpFechaHoraFin.getDateTimePermissive();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlControles = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        dtpFechaHoraFin = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboCuentaFiltro = new javax.swing.JComboBox<>();
        dtpFechaHoraInicio = new com.github.lgooddatepicker.components.DateTimePicker();
        cboMovimientosOPresuespuestos = new javax.swing.JComboBox();
        cboMovimientoTipoFiltro = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnCancelar.setText("Cerrar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setMnemonic('G');
        btnGuardar.setText("Filtrar");
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

        jLabel3.setText("Desde:");
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));

        jLabel9.setText("Movimientos o presupuestos:");
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));

        jLabel5.setText("Cuenta:");
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));

        jLabel6.setText("Tipo movimiento:");
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));

        jLabel7.setText("Hasta:");
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));

        cboMovimientosOPresuespuestos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Sólo movimientos", "Sólo presupuestos", " " }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboCuentaFiltro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboMovimientoTipoFiltro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboMovimientosOPresuespuestos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtpFechaHoraFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtpFechaHoraInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(2, 2, 2)
                .addComponent(cboCuentaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMovimientoTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(2, 2, 2)
                .addComponent(cboMovimientosOPresuespuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(dtpFechaHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(2, 2, 2)
                .addComponent(dtpFechaHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        vistaPadre.listar();
    }//GEN-LAST:event_btnGuardarActionPerformed
 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Cuenta> cboCuentaFiltro;
    private javax.swing.JComboBox<Tipo> cboMovimientoTipoFiltro;
    private javax.swing.JComboBox cboMovimientosOPresuespuestos;
    private com.github.lgooddatepicker.components.DateTimePicker dtpFechaHoraFin;
    private com.github.lgooddatepicker.components.DateTimePicker dtpFechaHoraInicio;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel pnlControles;
    // End of variables declaration//GEN-END:variables
}
