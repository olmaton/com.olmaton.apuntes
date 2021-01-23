/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import configuracion.Colores;
import controlador.TipoController;
import entidades.Tipo;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import presentacion.interfaces.IMovimientoTipo;
import presentacion.utiles.Botones;
import presentacion.utiles.DialogMessage;
import presentacion.utiles.Imagenes;
import presentacion.utiles.OlmLog;
import presentacion.utiles.olmjtable.OlmCustomJTable;
import presentacion.utiles.olmjtable.OlmDefaultTableCellRenderer;
import presentacion.utiles.olmjtable.OlmJTableHead;

/**
 *
 * @author olmaton
 */
public class JdiMovimientosTipo extends javax.swing.JDialog implements IMovimientoTipo {

    TipoController controlador;

    public JdiMovimientosTipo(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        setStyles();
        this.setTitle("Tipos de movimientos");
        controlador = new TipoController(this);
        controlador.listar();
    }

    private void setStyles() {
        pnlAgregar.setBackground(Colores.getGris());
        Botones.estiloBotonGuardar(btnGuardar);
        //Botones.estiloBotonCancelar(btnCancelar);
    }

    private DefaultTableModel getDTM() {
        OlmJTableHead head = new OlmJTableHead();

        head.addColumn("#", 60, SwingConstants.CENTER, new Color(230, 230, 230), Color.DARK_GRAY);
        head.addColumn("Nombre", 500);
        head.addColumn("Tipo", 220, SwingConstants.CENTER);
        head.addColumn("-", 60);
        head.addColumn("-", 60);

        OlmDefaultTableCellRenderer render = new OlmDefaultTableCellRenderer();
        //render.setColorsAlternate(Color.RED, Color.black);
        render.setAlternate(true);
        render.setAlign(head.getAlign());
        render.setBackground(head.getBackground());
        render.setForeground(head.getForeground());

        final OlmCustomJTable olmTable = new OlmCustomJTable(tbLista, head, render);
        
        final JButton btnEditar = olmTable.addButton(head.getSize() - 2, Imagenes.getImage("edit"));
        btnEditar.addActionListener((ActionEvent e) -> {
            controlador.setEditar(Integer.valueOf(btnEditar.getName()));
        });

        final JButton btnEliminar = olmTable.addButton(head.getSize() - 1, Imagenes.getImage("delete"));
        btnEliminar.addActionListener((ActionEvent e) -> {
            controlador.eliminar(Integer.valueOf(btnEliminar.getName()));
        });
        return olmTable.getDefaultTableModel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLista = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        rbtnIngreso = new javax.swing.JRadioButton();
        rbtnEgreso = new javax.swing.JRadioButton();
        pnlAgregar = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbLista);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Nombre:");

        buttonGroup1.add(rbtnIngreso);
        rbtnIngreso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbtnIngreso.setSelected(true);
        rbtnIngreso.setText("Ingreso");

        buttonGroup1.add(rbtnEgreso);
        rbtnEgreso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbtnEgreso.setText("Egreso");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre)
                .addGap(18, 18, 18)
                .addComponent(rbtnIngreso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnEgreso)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbtnIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbtnEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAgregar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAgregarLayout = new javax.swing.GroupLayout(pnlAgregar);
        pnlAgregar.setLayout(pnlAgregarLayout);
        pnlAgregarLayout.setHorizontalGroup(
            pnlAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlAgregarLayout.setVerticalGroup(
            pnlAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        controlador.procesar();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlAgregar;
    private javax.swing.JRadioButton rbtnEgreso;
    private javax.swing.JRadioButton rbtnIngreso;
    private javax.swing.JTable tbLista;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarMensaje(String mensaje, int code) {
        DialogMessage.getInstancia().setMensaje((JFrame)this.getParent(), mensaje, code);
    }
    
    @Override
    public boolean confirmar(String mensaje) {
        return DialogMessage.getInstancia().confirmar((JFrame)this.getParent(), mensaje);
    }

    @Override
    public Tipo getNuevo() {
        Tipo nuevo = new Tipo();
        nuevo.setNombre(txtNombre.getText());
        nuevo.setSigno(rbtnIngreso.isSelected()?1:-1);
        return nuevo;
    }

    @Override
    public void listar(ArrayList<Tipo> lista) {
        try {
            DefaultTableModel dtm = getDTM();
            Object datosTabla[] = new Object[dtm.getColumnCount()];
            for (int i = 0; i < lista.size(); i++) {
                datosTabla[0] = (i + 1);
                datosTabla[1] = lista.get(i).getNombre();
                datosTabla[2] = lista.get(i).getSignoFormat();
                dtm.addRow(datosTabla);
            }
        } catch (Exception e) {
            OlmLog.getInstancia().error(e.getMessage(), this.getClass().getName() + ".listar");
            mostrarMensaje(e.getMessage(), 3);
        }
    }

    @Override
    public void setEditar(Tipo tipo) {
        txtNombre.setText(tipo.getNombre());
        rbtnIngreso.setSelected(tipo.getSigno()>0);
        rbtnEgreso.setSelected(tipo.getSigno()<0);
    }

    @Override
    public boolean validar() {
        if(txtNombre.getText().trim().isEmpty()){
            mostrarMensaje("Ingrese nombre.", 1);
            txtNombre.requestFocus();
            return false;
        }
        return true;
    }
    
    

    @Override
    public void limpiar() {
        txtNombre.setText("");
        txtNombre.requestFocus();
    }
}
