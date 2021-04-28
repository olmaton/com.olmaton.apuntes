package presentacion;

import configuracion.Colores;
import configuracion.Constantes;
import presentacion.utiles.OlmLog;
import controlador.MovimientoController;
import java.util.ArrayList;
import entidades.Cuenta;
import entidades.Limit;
import entidades.Movimiento;
import entidades.Offset;
import entidades.Tipo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import modelo.dto.FiltroMovimientoDTO;
import presentacion.interfaces.ApuntesInterface;
import presentacion.interfaces.MovimientoFiltrarInterface;
import presentacion.interfaces.MovimientosInterface;
import presentacion.utiles.Botones;
import presentacion.utiles.DatePickerUtil;
import presentacion.utiles.DialogMessage;
import presentacion.utiles.Imagenes;
import presentacion.utiles.Imputs;
import presentacion.utiles.olmjtable.OlmJTableHead;
import presentacion.utiles.olmjtable.OlmCustomJTable;
import presentacion.utiles.olmjtable.OlmDefaultTableCellRenderer;

/**
 *
 * @author olmaton
 */
public class PnlMovimientos extends javax.swing.JPanel implements MovimientosInterface, KeyListener, MovimientoFiltrarInterface {

    private static PnlMovimientos instancia = null;

    MovimientoController controlador;
    ApuntesInterface vistaPadre;
    JdiMovimientosFiltros jdiMovimientosFiltros;

    private PnlMovimientos(ApuntesInterface vistaPadre) {
        initComponents();
        this.vistaPadre = vistaPadre;
        jdiMovimientosFiltros = new JdiMovimientosFiltros(vistaPadre.getFramePrincipal(), this);

        controlador = new MovimientoController(this);
        setStyles();
        setImputsFormat();
        limpiar();
        setKeyListener();
        //Se carga por única vez
        controlador.llenarLimits();

    }

    public static PnlMovimientos getInstancia(ApuntesInterface vistaPadre) {
        if (instancia == null) {
            instancia = new PnlMovimientos(vistaPadre);
        }
        //Se recarga cada vez que se regresa al panel
        instancia.recargarDatosIniciales();
        return instancia;
    }

    private void recargarDatosIniciales() {
        controlador.listarTipos();
        controlador.listarCuentas();
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

    private void setImputsFormat() {
        Imputs.setDecimal(txtMonto);
        Imputs.setInteger(txtCantidad);
        DatePickerUtil.setFechaHoraMediaNoche(dtpFechaHora);
        getDTM();
    }

    private DefaultTableModel getDTM() {
        OlmJTableHead head = new OlmJTableHead();

        head.addColumn("#", 60, SwingConstants.CENTER, new Color(230, 230, 230), Color.DARK_GRAY);
        head.addColumn("Nombre", 300);
        head.addColumn("Cuenta", 250, SwingConstants.CENTER);
        head.addColumn("Tipo", 200, SwingConstants.CENTER);
        head.addColumn("Fecha", 200, SwingConstants.CENTER);
        head.addColumn("Cantidad", 150, SwingConstants.CENTER);
        head.addColumn("Monto", 150, SwingConstants.RIGHT);
        head.addColumn("Actualizado", 200, SwingConstants.CENTER);
        head.addColumn("Presupuesto", 60);
        head.addColumn("-", 60);
        head.addColumn("-", 60);

        OlmDefaultTableCellRenderer render = new OlmDefaultTableCellRenderer();
        //render.setColorsAlternate(Color.RED, Color.black);
        render.setAlternate(true);
        render.setAlign(head.getAlign());
        render.setBackground(head.getBackground());
        render.setForeground(head.getForeground());

        final OlmCustomJTable olmTable = new OlmCustomJTable(tbLista, head, render);

        final JCheckBox chkPresupuestos = olmTable.addCheckBox(head.getSize() - 3);
        chkPresupuestos.addActionListener((ActionEvent e) -> {
            controlador.actualizarEsPresupuesto(Integer.valueOf(chkPresupuestos.getName()), chkPresupuestos.isSelected());
        });

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

        pnlFormulario = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        pnlControles = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboCuenta = new javax.swing.JComboBox<>();
        cboMovimientoTipo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        dtpFechaHora = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel7 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        chkEsPresupuesto = new javax.swing.JCheckBox();
        btnAgregarTipo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLista = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSalidas = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEntradas = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        cboOffset = new javax.swing.JComboBox<>();
        btnAnterior = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtEntradasPresupuesto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSalidasPresupuesto = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTotalPresupuesto = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        cboLimit = new javax.swing.JComboBox<>();
        lblTotal = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtTotalGeneral = new javax.swing.JTextField();

        pnlFormulario.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Nombre:");
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));

        txtNombre.setText("Este es un nombre de ejemplo");

        jLabel4.setText("Descripción:");

        txtDescripcion.setColumns(3);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(2);
        txtDescripcion.setText("Esta es una descripcion de movimiento. Texto de ejemplo de inserción de texto.");
        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
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

        jLabel5.setText("Cuenta:");
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));

        jLabel6.setText("Tipo movimiento");
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));

        jLabel9.setText("Fecha y hora:");
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));

        jLabel7.setText("Monto:");
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));

        txtMonto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMonto.setText("12.89");
        txtMonto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });

        jLabel8.setText("Cantidad:");
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));

        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setText("1");
        txtCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });

        chkEsPresupuesto.setText("Es presupuesto");

        btnAgregarTipo.setText("+");
        btnAgregarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTipoActionPerformed(evt);
            }
        });

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
                    .addComponent(pnlControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboCuenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(chkEsPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(cboMovimientoTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(2, 2, 2)
                .addComponent(cboCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(2, 2, 2)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMovimientoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(chkEsPresupuesto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Movimientos"));

        txtTotal.setEditable(false);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setBackground(new java.awt.Color(235, 255, 255));
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalKeyReleased(evt);
            }
        });

        jLabel2.setText("Total:");

        txtSalidas.setEditable(false);
        txtSalidas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSalidas.setBackground(new java.awt.Color(255, 251, 243));
        txtSalidas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSalidas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSalidasKeyReleased(evt);
            }
        });

        jLabel10.setText("Egresos:");

        txtEntradas.setEditable(false);
        txtEntradas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntradas.setBackground(new java.awt.Color(231, 255, 238));
        txtEntradas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtEntradas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntradasKeyReleased(evt);
            }
        });

        jLabel11.setText("Ingresos:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSalidas)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTotal)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );

        btnSiguiente.setText("Siguiente >");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        cboOffset.setActionCommand("olmaton offset");
        cboOffset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboOffsetActionPerformed(evt);
            }
        });

        btnAnterior.setText("< Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Presupuestos"));

        jLabel12.setText("Ingresos:");

        txtEntradasPresupuesto.setEditable(false);
        txtEntradasPresupuesto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntradasPresupuesto.setBackground(new java.awt.Color(231, 255, 238));
        txtEntradasPresupuesto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtEntradasPresupuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntradasPresupuestoKeyReleased(evt);
            }
        });

        jLabel13.setText("Egresos:");

        txtSalidasPresupuesto.setEditable(false);
        txtSalidasPresupuesto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSalidasPresupuesto.setBackground(new java.awt.Color(255, 251, 243));
        txtSalidasPresupuesto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSalidasPresupuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSalidasPresupuestoKeyReleased(evt);
            }
        });

        jLabel14.setText("Total:");

        txtTotalPresupuesto.setEditable(false);
        txtTotalPresupuesto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalPresupuesto.setBackground(new java.awt.Color(235, 255, 255));
        txtTotalPresupuesto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotalPresupuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalPresupuestoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEntradasPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSalidasPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTotalPresupuesto)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSalidasPresupuesto)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEntradasPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );

        cboLimit.setActionCommand("olmaton limit");
        cboLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLimitActionPerformed(evt);
            }
        });

        lblTotal.setText("Total de registros: 5000");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Total general"));

        txtTotalGeneral.setEditable(false);
        txtTotalGeneral.setBackground(new java.awt.Color(255, 240, 255));
        txtTotalGeneral.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotalGeneral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalGeneral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalGeneralKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTotalGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(txtTotalGeneral)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAnterior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboOffset, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSiguiente)
                            .addComponent(cboOffset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnterior)
                            .addComponent(lblTotal)))
                    .addComponent(pnlFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        listar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKeyReleased

    private void txtSalidasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalidasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalidasKeyReleased

    private void txtEntradasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntradasKeyReleased

    private void txtEntradasPresupuestoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradasPresupuestoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntradasPresupuestoKeyReleased

    private void txtSalidasPresupuestoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalidasPresupuestoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalidasPresupuestoKeyReleased

    private void txtTotalPresupuestoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalPresupuestoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPresupuestoKeyReleased

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        jdiMovimientosFiltros.setLocationRelativeTo(pnlFormulario);
        jdiMovimientosFiltros.setVisible(true);
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO addColumn your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO addColumn your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        controlador.procesar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        controlador.inicializar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cboLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLimitActionPerformed
        //System.out.println(evt);
        //System.out.println(cboLimit.isValid());
        if (cboLimit.isValid()) {
            listar();
        }
    }//GEN-LAST:event_cboLimitActionPerformed

    private void cboOffsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboOffsetActionPerformed
        //System.out.println(evt);
        //System.out.println(cboOffset.isValid());
        if (cboOffset.isValid()) {
            listarOffset();
        }
    }//GEN-LAST:event_cboOffsetActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        siguienteAnterior(1);
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        siguienteAnterior(-1);
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnAgregarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTipoActionPerformed
        agregaTipo();
    }//GEN-LAST:event_btnAgregarTipoActionPerformed

    private void txtTotalGeneralKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalGeneralKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalGeneralKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarTipo;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<Cuenta> cboCuenta;
    private javax.swing.JComboBox<Limit> cboLimit;
    private javax.swing.JComboBox<Tipo> cboMovimientoTipo;
    private javax.swing.JComboBox<Offset> cboOffset;
    private javax.swing.JCheckBox chkEsPresupuesto;
    private com.github.lgooddatepicker.components.DateTimePicker dtpFechaHora;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlControles;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JTable tbLista;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtEntradas;
    private javax.swing.JTextField txtEntradasPresupuesto;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSalidas;
    private javax.swing.JTextField txtSalidasPresupuesto;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalGeneral;
    private javax.swing.JTextField txtTotalPresupuesto;
    // End of variables declaration//GEN-END:variables

    private void agregaTipo() {
        JdiMovimientosTipo jdiMovimientoTipo = new JdiMovimientosTipo(vistaPadre.getFramePrincipal());
        jdiMovimientoTipo.setLocationRelativeTo(this);
        jdiMovimientoTipo.setVisible(true);
        controlador.listarTipos();
    }

    private void listarOffset() {
        controlador.listarOffset();
    }

    private void habilitarSiguienteAnterior() {
        btnAnterior.setEnabled(false);
        btnSiguiente.setEnabled(false);
        if (cboOffset.getSelectedIndex() < 0 || cboOffset.getItemCount() == 1) {
            return;
        }

        if (cboOffset.getSelectedIndex() > 0) {
            btnAnterior.setEnabled(true);
        }

        if (cboOffset.getSelectedIndex() < cboOffset.getItemCount() - 1) {
            btnSiguiente.setEnabled(true);
        }
    }

    private void siguienteAnterior(int adicion) {
        int idx = cboOffset.getSelectedIndex() + adicion;
        if (idx < 0) {
            return;
        }
        if (idx >= cboOffset.getItemCount()) {
            return;
        }
        cboOffset.setSelectedIndex(idx);
    }

    @Override
    public void listar(ArrayList<Movimiento> lista) {

        try {
            DefaultTableModel dtm = getDTM();
            Object datosTabla[] = new Object[dtm.getColumnCount()];
            for (int i = 0; i < lista.size(); i++) {
                datosTabla[0] = (i + 1);
                datosTabla[1] = lista.get(i).getNombre();
                datosTabla[2] = lista.get(i).getCuenta().getNombre();
                datosTabla[3] = lista.get(i).getTipo().getNombre();
                datosTabla[4] = lista.get(i).getFechaHoraFormato();
                datosTabla[5] = lista.get(i).getCantidad();
                datosTabla[6] = lista.get(i).getMontoFormato();
                datosTabla[7] = lista.get(i).getActualizadoFormato();
                datosTabla[8] = lista.get(i).getEs_presupuesto() > 0;
                dtm.addRow(datosTabla);
            }
            habilitarSiguienteAnterior();
        } catch (Exception e) {
            OlmLog.getInstancia().error(e.getMessage(), this.getClass().getName() + ".listar");
            mostrarMensaje(e.getMessage(), 3);
        }
    }

    @Override
    public boolean validar() {
        if (cboCuenta.getItemCount() == 0) {
            mostrarMensaje("Registre por lo menos una cuenta.", 1);
            vistaPadre.cambiarVentana(Constantes.APUNTES_CUENTAS);
            return false;
        }

        if (cboCuenta.getSelectedIndex() < 0) {
            mostrarMensaje("Seleccione una cuenta válida.", 1);
            cboCuenta.requestFocus();
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
    public Movimiento getNuevo() {
        Movimiento nuevo = new Movimiento();
        nuevo.setNombre(txtNombre.getText().trim());
        nuevo.setDescripcion(txtDescripcion.getText().trim());
        nuevo.setCantidad(Integer.valueOf(txtCantidad.getText()));
        nuevo.setValor_unitario_egreso(Double.valueOf(txtMonto.getText()));
        nuevo.setValor_unitario_ingreso(Double.valueOf(txtMonto.getText()));
        nuevo.setFecha_hora(dtpFechaHora.getDateTimePermissive());
        nuevo.setCuenta((Cuenta) cboCuenta.getSelectedItem());
        nuevo.setTipo((Tipo) cboMovimientoTipo.getSelectedItem());
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
    public void setEditar(Movimiento movimiento) {
        txtNombre.setText(movimiento.getNombre());
        txtDescripcion.setText(movimiento.getDescripcion());
        txtMonto.setText(movimiento.getMontoFormato());
        txtCantidad.setText(movimiento.getCantidad() + "");
        dtpFechaHora.setDateTimePermissive(movimiento.getFecha_hora());
        chkEsPresupuesto.setSelected(movimiento.getEs_presupuesto() > 0);

        for (int i = 0; i < cboCuenta.getItemCount(); i++) {
            if (cboCuenta.getItemAt(i).getId() == movimiento.getCuenta().getId()) {
                cboCuenta.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < cboMovimientoTipo.getItemCount(); i++) {
            if (cboMovimientoTipo.getItemAt(i).getId() == movimiento.getTipo().getId()) {
                cboMovimientoTipo.setSelectedIndex(i);
                break;
            }
        }

        txtNombre.requestFocus();
        txtNombre.selectAll();
    }

    @Override
    public void llenarCuentas(ArrayList<Cuenta> lista) {
        cboCuenta.removeAllItems();
        for (Cuenta cuenta : lista) {
            cboCuenta.addItem(cuenta);
        }
    }

    @Override
    public void llenarTipos(ArrayList<Tipo> lista) {
        cboMovimientoTipo.removeAllItems();
        for (Tipo tipo : lista) {
            cboMovimientoTipo.addItem(tipo);
        }
    }

    @Override
    public void llenarCuentasFiltro(ArrayList<Cuenta> lista) {
        jdiMovimientosFiltros.llenarCuentasFiltro(lista);
    }

    @Override
    public void llenarTiposFiltro(ArrayList<Tipo> lista) {
        jdiMovimientosFiltros.llenarTiposFiltro(lista);
    }

    @Override
    public FiltroMovimientoDTO getFiltro() {
        FiltroMovimientoDTO sf = jdiMovimientosFiltros.getFiltro();
        sf.setBuscar(txtBuscar.getText());
        sf.setLimit(((Limit) cboLimit.getSelectedItem()).getCantidad());
        if (cboOffset.getSelectedIndex() > 0) {
            sf.setOffset(((Offset) cboOffset.getSelectedItem()).getFactor() * sf.getLimit());
        }
        return sf;
    }

    @Override
    public void llenarTotales(String entradas, String salidas, String total, String entradas_pre, String salidas_pre, String total_pre) {
        txtEntradas.setText(entradas);
        txtSalidas.setText(salidas);
        txtTotal.setText(total);

        txtEntradasPresupuesto.setText(entradas_pre);
        txtSalidasPresupuesto.setText(salidas_pre);
        txtTotalPresupuesto.setText(total_pre);

    }

    @Override
    public void llenarTotalGeneral(String total) {
        txtTotalGeneral.setText(total);
    }

    @Override
    public int getLimit() {
        return ((Limit) cboLimit.getSelectedItem()).getCantidad();
    }

    @Override
    public int getOffset() {
        if (cboOffset.getSelectedIndex() > 0) {
            return ((Offset) cboOffset.getSelectedItem()).getFactor();
        } else {
            return 0;
        }
    }

    @Override
    public void listar() {
        controlador.listar();
    }

    @Override
    public void llenarLimits(ArrayList<Limit> lista) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLimit.getModel();
        model.removeAllElements();
        for (Limit tipo : lista) {
            cboLimit.addItem(tipo);
        }
    }

    @Override
    public void llenarOffsets(ArrayList<Offset> lista) {
        cboOffset.removeAllItems();
        for (Offset tipo : lista) {
            cboOffset.addItem(tipo);
        }
        habilitarSiguienteAnterior();
    }

    @Override
    public void setLabelTotal(String label) {
        lblTotal.setText(label);
    }

    @Override
    public void mostrarMensaje(String mensaje, int code) {
        DialogMessage.getInstancia().setMensaje(vistaPadre.getFramePrincipal(), mensaje, code);
    }

    @Override
    public boolean confirmar(String mensaje) {
        return DialogMessage.getInstancia().confirmar(vistaPadre.getFramePrincipal(), mensaje);
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
