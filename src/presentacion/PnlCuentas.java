
package presentacion;

import configuracion.Colores;
import presentacion.utiles.OlmLog;
import controlador.CuentaController;
import java.util.ArrayList;
import entidades.Cuenta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import presentacion.interfaces.ApuntesInterface;
import presentacion.interfaces.CuentasInterface;
import presentacion.utiles.Botones;
import presentacion.utiles.DialogMessage;
import presentacion.utiles.Imagenes;
import presentacion.utiles.olmjtable.OlmDefaultTableCellRenderer;
import presentacion.utiles.olmjtable.OlmJTableHead;
import presentacion.utiles.olmjtable.OlmCustomJTable;

/**
 *
 * @author olmaton
 */
public class PnlCuentas extends javax.swing.JPanel implements CuentasInterface, KeyListener{

    private static PnlCuentas instancia = null; 
    
    CuentaController controlador;
    ApuntesInterface vistaPadre;
    private PnlCuentas(ApuntesInterface vistaPadre) {
        initComponents();
        this.vistaPadre = vistaPadre;
        controlador = new CuentaController(this);
        setStyles(); 
        setKeyListener();
    }
    
    public static PnlCuentas getInstancia(ApuntesInterface vistaPadre) {
        if (instancia == null) {
            instancia = new PnlCuentas(vistaPadre);
        }
        instancia.recargarDatos();
        return instancia;
    }

    private void recargarDatos(){
        controlador.listar();
    }

    private void setStyles() {
        pnlControles.setBackground(Colores.getGris());
        Botones.estiloBotonGuardar(btnGuardar);
        Botones.estiloBotonCancelar(btnCancelar);
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
    
    private DefaultTableModel getDTM() {  
        
        OlmJTableHead head = new OlmJTableHead();        
        head.addColumn("#", 60, SwingConstants.CENTER,new Color(230, 230, 230),Color.DARK_GRAY);
        head.addColumn("Nombre", 600);
        head.addColumn("Descripción", 800);
        head.addColumn("Actualizado", 200,SwingConstants.CENTER);
        head.addColumn("-", 60);
        head.addColumn("-", 60);
        
        OlmDefaultTableCellRenderer render = new OlmDefaultTableCellRenderer();        
        render.setAlternate(true);
        render.setAlign(head.getAlign());     
        render.setBackground(head.getBackground());
        render.setForeground(head.getForeground());
        
        final OlmCustomJTable olmTable = new OlmCustomJTable(tbLista,head,render);

        final JButton btnEditar = olmTable.addButton(head.getSize()-2, Imagenes.getImage("edit"));
        btnEditar.addActionListener((ActionEvent e) -> {
            controlador.setEditar(Integer.valueOf(btnEditar.getName()));
        });

        final JButton btnEliminar = olmTable.addButton(head.getSize()-1,Imagenes.getImage("delete"));        
        btnEliminar.addActionListener((ActionEvent e) -> {            
            controlador.eliminar(Integer.valueOf(btnEliminar.getName()));
        });
        return olmTable.getDefaultTableModel();
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFormulario = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        pnlControles = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLista = new javax.swing.JTable();

        pnlFormulario.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Nombre:");

        jLabel4.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(3);
        jScrollPane2.setViewportView(txtDescripcion);

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
                .addContainerGap(82, Short.MAX_VALUE)
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
                    .addComponent(pnlControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        controlador.procesar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        controlador.inicializar();
    }//GEN-LAST:event_btnCancelarActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlControles;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JTable tbLista;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    

    @Override
    public void listar(ArrayList<Cuenta> lista) {
        try {            
            DefaultTableModel dtm = getDTM(); 
            Object datosTabla[] = new Object[dtm.getColumnCount()];
            for (int i = 0; i < lista.size(); i++) {
                datosTabla[0] = (i+1);
                datosTabla[1] = lista.get(i).getNombre();
                datosTabla[2] = lista.get(i).getDescripcion();
                datosTabla[3] = lista.get(i).getActualizadoFormato();
                dtm.addRow(datosTabla);
            }
        } catch (Exception e) {
            OlmLog.getInstancia().error(e.getMessage(),this.getClass().getName() + ".listar");
            mostrarMensaje(e.getMessage(), 3);
        }
    }
    
    
    @Override
    public boolean validar(){
        if(txtNombre.getText().trim().isEmpty()){
            mostrarMensaje("Ingrese nombre.", 1);
            txtNombre.requestFocus();
            txtNombre.selectAll();
            return false;
        }
        return true;
    }

    @Override
    public Cuenta getNuevo() {        
        Cuenta nuevo = new Cuenta();
        nuevo.setNombre(txtNombre.getText().trim());
        nuevo.setDescripcion(txtDescripcion.getText().trim());
        return nuevo;        
    }

    @Override
    public void inicializar() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtNombre.requestFocus();
    }

    @Override
    public void setEditar(Cuenta cuenta) {
        txtNombre.setText(cuenta.getNombre());
        txtDescripcion.setText(cuenta.getDescripcion());
        txtNombre.requestFocus();
        txtNombre.selectAll();
    }

    @Override
    public boolean confirmar(String mensaje) {
        return DialogMessage.getInstancia().confirmar(vistaPadre.getFramePrincipal(),mensaje);
    }
    
    @Override
    public void mostrarMensaje(String mensaje, int code) {
        DialogMessage.getInstancia().setMensaje(vistaPadre.getFramePrincipal(), mensaje, code);
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
