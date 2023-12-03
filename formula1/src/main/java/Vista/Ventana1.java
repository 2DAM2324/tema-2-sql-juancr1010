/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//TODO: Hay que hacer el ver detalles del piloto y la parte de generar informe
package Vista;

import Controlador.Controlador;
import java.awt.Color;
import java.awt.Font;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import java.util.ArrayList;
import Modelo.Piloto;
import Modelo.Coche;
import Modelo.EquipoCarreras;
import Modelo.Genera;
import Modelo.Informe;
import Modelo.Ingeniero;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;



/**
 *
 * @author Patricia Burgos
 */
public class Ventana1 extends javax.swing.JFrame {
    
    private Controlador miControlador;
    private ArrayList<Piloto> misPilotos;
    private ArrayList<EquipoCarreras> misEquipos;
    private ArrayList<Coche> misCoches;
    private ArrayList<Informe> misInformes;
    private ArrayList<Ingeniero> misIngenieros;
    private DefaultTableModel tableModelEquipos;
    private DefaultTableModel tableModelPilotos;
    private DefaultTableModel tableModelInformes;
    private DefaultTableModel tableModelCoches;
    private DefaultTableModel tableModelIngenieros;
    
    
    /**
     * Creates new form Ventana1
     */
    public Ventana1() throws IOException, FileNotFoundException, ClassNotFoundException, NotSerializableException, SAXException {
        initComponents();
        this.miControlador = new Controlador();
        this.misEquipos = new ArrayList<EquipoCarreras>();
        this.misPilotos = new ArrayList<Piloto>();
        this.misCoches = new ArrayList<Coche>();
        this.misInformes = new ArrayList<Informe>();
        this.misIngenieros = new ArrayList<Ingeniero>();
        
        tableModelEquipos = (DefaultTableModel)jTable_equipoCarreras.getModel();
        jTable_equipoCarreras.setModel(tableModelEquipos);
        
        tableModelPilotos = (DefaultTableModel)jTable_piloto.getModel();
        jTable_piloto.setModel(tableModelPilotos);
        
        tableModelInformes = (DefaultTableModel)jTable_informe.getModel();
        jTable_informe.setModel(tableModelInformes);
        
        tableModelCoches = (DefaultTableModel)jTable_coche.getModel();
        jTable_coche.setModel(tableModelCoches);
        
        tableModelIngenieros = (DefaultTableModel)jTable_ingeniero.getModel();
        jTable_ingeniero.setModel(tableModelIngenieros);
        
        this.traerDatosControladorVista();
        
        jButton_guardarModificacion_equipoCarreras.setVisible(false);
        
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
        
    }
    
    public void traerEquiposControladorVista(){
        this.misEquipos = this.miControlador.getEquipos();
    }
    
    public void traerPilotosControladorVista(){
        this.misPilotos = this.miControlador.getPilotos();
        jComboBox_anadirPiloto_EquiposCarreras.removeAllItems();
        for(Piloto p: this.misPilotos){
            jComboBox_anadirPiloto_EquiposCarreras.addItem(p.getIdPiloto());
        }
        
    }
    
    public void traerCochesControladorVista(){
        this.misCoches = this.miControlador.getCoches();
        jComboBox_coche_piloto.removeAllItems();
        for(Coche c: this.misCoches){
            jComboBox_coche_piloto.addItem(c.getIdCoche());
        }
        jComboBox_anadirCoche_Ingeniero.removeAllItems();
        for(Coche c: this.misCoches){
            jComboBox_anadirCoche_Ingeniero.addItem(c.getIdCoche());
        }
    }
    
    public void traerIngenierosControladorVista(){
        this.misIngenieros = this.miControlador.getIngenieros();
        jComboBox_anadirIngeniero_Coche.removeAllItems();
        for(Ingeniero i: this.misIngenieros){
            jComboBox_anadirIngeniero_Coche.addItem(i.getIdIngeniero());
        }
    }
    
    public void traerInformesControladorVista(){
        this.misInformes = this.miControlador.getInformes();
    }
    
   
    public void traerDatosControladorVista(){
        traerInformesControladorVista();
        traerIngenierosControladorVista();
        traerCochesControladorVista();
        traerPilotosControladorVista();
        traerEquiposControladorVista();
    }
    
    public void actualizarTablaEquipos(){
        this.tableModelEquipos.setRowCount(0);
        System.out.println("size controlador equipos: " + this.miControlador.getEquipos().size());
        this.miControlador.mostrarEquiposCarreras();
        for(EquipoCarreras equipo : this.miControlador.getEquipos()){
            Object[] row = {equipo.getIdEquipo(), equipo.getNombre()};
            tableModelEquipos.addRow(row);
        }
    }
    
    public void actualizarTablaPilotos(){
        this.tableModelPilotos.setRowCount(0);
        for(Piloto piloto : this.misPilotos){
            Object[] row = {piloto.getIdPiloto(), piloto.getNombre(), piloto.getEdad()};
            tableModelPilotos.addRow(row);
        }
    }
    
    public void actualizarTablaInformes(){
        this.tableModelInformes.setRowCount(0);
        for(Informe informe : this.misInformes){
            Object[] row = {informe.getIdInforme(), informe.getDescripcion()};
            tableModelInformes.addRow(row);
        }
    }
    
    public void actualizarTablaCoches(){
        this.tableModelCoches.setRowCount(0);
        for(Coche coche : this.misCoches){
            Object[] row = {coche.getIdCoche(), coche.getModelo(), coche.getMarca()};
            tableModelCoches.addRow(row);
        }
    }
    
    public void actualizarTablaIngenieros(){
        this.tableModelIngenieros.setRowCount(0);
        for(Ingeniero ingeniero : this.misIngenieros){
            Object[] row = {ingeniero.getIdIngeniero(), ingeniero.getFechaNacimiento(), ingeniero.getSueldo()};
            tableModelIngenieros.addRow(row);
        }
    }
    
    public void actualizarTablasVista(){
        
        jComboBox_anadirPiloto_EquiposCarreras.removeAllItems();
        for(Piloto p: this.misPilotos){
            jComboBox_anadirPiloto_EquiposCarreras.addItem(p.getIdPiloto());
        }
        
        jComboBox_coche_piloto.removeAllItems();
        for(Coche c: this.misCoches){
            jComboBox_coche_piloto.addItem(c.getIdCoche());
        }
        
        jComboBox_anadirIngeniero_Coche.removeAllItems();
        for(Ingeniero i: this.misIngenieros){
            jComboBox_anadirIngeniero_Coche.addItem(i.getIdIngeniero());
        }
        
        jComboBox_anadirCoche_Ingeniero.removeAllItems();
        for(Coche c: this.misCoches){
            jComboBox_anadirCoche_Ingeniero.addItem(c.getIdCoche());
        }
        
        actualizarTablaInformes();
        actualizarTablaIngenieros();
        actualizarTablaCoches();
        actualizarTablaPilotos();
        actualizarTablaEquipos();
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel_equipoCarreras = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_equipoCarreras = new javax.swing.JTable();
        jButton_modificar_equipoCarreras = new javax.swing.JButton();
        jButton_borrar_equipoCarreras = new javax.swing.JButton();
        jLabel_nombre_equipoCarreras = new javax.swing.JLabel();
        jTextField_nombre_equipoCarreras = new javax.swing.JTextField();
        jButton_guardar_equipoCarreras = new javax.swing.JButton();
        jButton_cancelar_equipoCarreras = new javax.swing.JButton();
        jButton_guardarModificacion_equipoCarreras = new javax.swing.JButton();
        jButton_detalles_EquipoCarreras = new javax.swing.JButton();
        jComboBox_anadirPiloto_EquiposCarreras = new javax.swing.JComboBox<>();
        jButton_anadirPiloto_EquipoCarreras = new javax.swing.JButton();
        jPanel_piloto = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_piloto = new javax.swing.JTable();
        jLabel_nombre_piloto = new javax.swing.JLabel();
        jLabel_edad_piloto = new javax.swing.JLabel();
        jTextField_nombre_piloto = new javax.swing.JTextField();
        jTextField_edad_piloto = new javax.swing.JTextField();
        jButton_aniadir_piloto = new javax.swing.JButton();
        jButton_modificar_piloto = new javax.swing.JButton();
        jButton_borrar_piloto = new javax.swing.JButton();
        jButton_guardarModificacion_pilotos = new javax.swing.JButton();
        jButton_cancelar_pilotos = new javax.swing.JButton();
        jButton_generarInforme_Piloto = new javax.swing.JButton();
        jTextField_descripcionInforme_piloto = new javax.swing.JTextField();
        jLabel_descripcionInforme_piloto = new javax.swing.JLabel();
        jComboBox_coche_piloto = new javax.swing.JComboBox<>();
        jButton_detalles_Piloto = new javax.swing.JButton();
        jButton_anadirCoche_piloto = new javax.swing.JButton();
        jPanel_informe = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_informe = new javax.swing.JTable();
        jLabel_descripcion_informe = new javax.swing.JLabel();
        jTextField_descripcion_informe = new javax.swing.JTextField();
        jButton_guardar_informe = new javax.swing.JButton();
        jButton_cancelar_informe = new javax.swing.JButton();
        jButton_borrar_informe = new javax.swing.JButton();
        jButton_modificar_informe = new javax.swing.JButton();
        jPanel_coche = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_coche = new javax.swing.JTable();
        jLabel_modelo_coche = new javax.swing.JLabel();
        jLabel_marca_coche = new javax.swing.JLabel();
        jTextField_marca_coche = new javax.swing.JTextField();
        jTextField_modelo_coche = new javax.swing.JTextField();
        jButton_guardar_coche = new javax.swing.JButton();
        jButton_cancelar_coche = new javax.swing.JButton();
        jButton_borrar_coche = new javax.swing.JButton();
        jButton_modificar_coche = new javax.swing.JButton();
        jButton_aniadir_coche = new javax.swing.JButton();
        jComboBox_anadirIngeniero_Coche = new javax.swing.JComboBox<>();
        jButton_anadirIngeniero_Coche = new javax.swing.JButton();
        jButton_detalles_Coche = new javax.swing.JButton();
        jPanel_ingeniero = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_ingeniero = new javax.swing.JTable();
        jLabel_fechaNac_ingeniero = new javax.swing.JLabel();
        jLabel_sueldo_ingeniero = new javax.swing.JLabel();
        jTextField_sueldo_ingeniero = new javax.swing.JTextField();
        jTextField_fechnac_ingeniero = new javax.swing.JTextField();
        jButton_guardar_ingeniero = new javax.swing.JButton();
        jButton_cancelar_ingeniero = new javax.swing.JButton();
        jButton_borrar_ingeniero = new javax.swing.JButton();
        jButton_modificar_ingeniero = new javax.swing.JButton();
        jButton_aniadir_ingeniero = new javax.swing.JButton();
        jComboBox_anadirCoche_Ingeniero = new javax.swing.JComboBox<>();
        jButton_anadirCoche_Ingeniero = new javax.swing.JButton();
        jButton_detalles_Ingeniero = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable_equipoCarreras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idEquipo", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable_equipoCarreras);
        if (jTable_equipoCarreras.getColumnModel().getColumnCount() > 0) {
            jTable_equipoCarreras.getColumnModel().getColumn(0).setResizable(false);
            jTable_equipoCarreras.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton_modificar_equipoCarreras.setText("Modificar");
        jButton_modificar_equipoCarreras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_modificar_equipoCarrerasMouseClicked(evt);
            }
        });
        jButton_modificar_equipoCarreras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_equipoCarrerasActionPerformed(evt);
            }
        });

        jButton_borrar_equipoCarreras.setText("Borrar");
        jButton_borrar_equipoCarreras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_borrar_equipoCarrerasMouseClicked(evt);
            }
        });
        jButton_borrar_equipoCarreras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_equipoCarrerasActionPerformed(evt);
            }
        });

        jLabel_nombre_equipoCarreras.setText("Nombre");

        jButton_guardar_equipoCarreras.setText("Añadir");
        jButton_guardar_equipoCarreras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_guardar_equipoCarrerasMouseClicked(evt);
            }
        });
        jButton_guardar_equipoCarreras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_equipoCarrerasActionPerformed(evt);
            }
        });

        jButton_cancelar_equipoCarreras.setText("Cancelar Modificación");
        jButton_cancelar_equipoCarreras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_cancelar_equipoCarrerasMouseClicked(evt);
            }
        });
        jButton_cancelar_equipoCarreras.setVisible(false);

        jButton_guardarModificacion_equipoCarreras.setText("Guardar Modificación");
        jButton_guardarModificacion_equipoCarreras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_guardarModificacion_equipoCarrerasMouseClicked(evt);
            }
        });

        jButton_detalles_EquipoCarreras.setText("Ver detalles de un equipo");
        jButton_detalles_EquipoCarreras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_detalles_EquipoCarrerasMouseClicked(evt);
            }
        });

        jComboBox_anadirPiloto_EquiposCarreras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_anadirPiloto_EquiposCarreras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_anadirPiloto_EquiposCarrerasActionPerformed(evt);
            }
        });
        jComboBox_anadirPiloto_EquiposCarreras.setVisible(false);
        /*if(this.miControlador.getPilotos() != null)
        for(Piloto p : this.miControlador.getPilotos()){
            if(p.getEquipo_piloto() == null){
                jComboBox_anadirPiloto_EquiposCarreras.addItem(p.getNombre());
            }
        }
    }*/

    jButton_anadirPiloto_EquipoCarreras.setText("Añadir Piloto al equipo");
    jButton_anadirPiloto_EquipoCarreras.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_anadirPiloto_EquipoCarrerasMouseClicked(evt);
        }
    });
    jButton_anadirPiloto_EquipoCarreras.setVisible(false);

    javax.swing.GroupLayout jPanel_equipoCarrerasLayout = new javax.swing.GroupLayout(jPanel_equipoCarreras);
    jPanel_equipoCarreras.setLayout(jPanel_equipoCarrerasLayout);
    jPanel_equipoCarrerasLayout.setHorizontalGroup(
        jPanel_equipoCarrerasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel_equipoCarrerasLayout.createSequentialGroup()
            .addGroup(jPanel_equipoCarrerasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanel_equipoCarrerasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel_equipoCarrerasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_detalles_EquipoCarreras))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel_equipoCarrerasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton_modificar_equipoCarreras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_borrar_equipoCarreras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_guardar_equipoCarreras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel_equipoCarrerasLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(jPanel_equipoCarrerasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_equipoCarrerasLayout.createSequentialGroup()
                            .addComponent(jComboBox_anadirPiloto_EquiposCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(jButton_anadirPiloto_EquipoCarreras)
                            .addGap(32, 32, 32))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_equipoCarrerasLayout.createSequentialGroup()
                            .addComponent(jLabel_nombre_equipoCarreras)
                            .addGap(56, 56, 56)
                            .addComponent(jTextField_nombre_equipoCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel_equipoCarrerasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton_cancelar_equipoCarreras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_guardarModificacion_equipoCarreras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(4, 4, 4)))))
            .addContainerGap(43, Short.MAX_VALUE))
    );
    jPanel_equipoCarrerasLayout.setVerticalGroup(
        jPanel_equipoCarrerasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel_equipoCarrerasLayout.createSequentialGroup()
            .addGroup(jPanel_equipoCarrerasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_equipoCarrerasLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jButton_guardar_equipoCarreras)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton_modificar_equipoCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton_borrar_equipoCarreras))
                .addGroup(jPanel_equipoCarrerasLayout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton_detalles_EquipoCarreras)
            .addGap(18, 18, 18)
            .addComponent(jButton_guardarModificacion_equipoCarreras)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel_equipoCarrerasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel_nombre_equipoCarreras)
                .addComponent(jTextField_nombre_equipoCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_cancelar_equipoCarreras))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel_equipoCarrerasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBox_anadirPiloto_EquiposCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_anadirPiloto_EquipoCarreras))
            .addContainerGap(150, Short.MAX_VALUE))
    );

    jTabbedPane.addTab("EquipoCarreras", jPanel_equipoCarreras);

    jTable_piloto.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "idPiloto", "Nombre", "Edad"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane2.setViewportView(jTable_piloto);
    if (jTable_piloto.getColumnModel().getColumnCount() > 0) {
        jTable_piloto.getColumnModel().getColumn(0).setResizable(false);
        jTable_piloto.getColumnModel().getColumn(1).setResizable(false);
        jTable_piloto.getColumnModel().getColumn(2).setResizable(false);
    }

    jLabel_nombre_piloto.setText("Nombre");

    jLabel_edad_piloto.setText("Edad");

    jButton_aniadir_piloto.setText("Añadir");
    jButton_aniadir_piloto.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_aniadir_pilotoMouseClicked(evt);
        }
    });

    jButton_modificar_piloto.setText("Modificar");
    jButton_modificar_piloto.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_modificar_pilotoMouseClicked(evt);
        }
    });

    jButton_borrar_piloto.setText("Borrar");
    jButton_borrar_piloto.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_borrar_pilotoMouseClicked(evt);
        }
    });

    jButton_guardarModificacion_pilotos.setText("Guardar Modificación");
    jButton_guardarModificacion_pilotos.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_guardarModificacion_pilotosMouseClicked(evt);
        }
    });
    jButton_guardarModificacion_pilotos.setVisible(false);

    jButton_cancelar_pilotos.setText("Cancelar Modificación");
    jButton_cancelar_pilotos.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_cancelar_pilotosMouseClicked(evt);
        }
    });
    jButton_cancelar_pilotos.setVisible(false);

    jButton_generarInforme_Piloto.setText("Generar Informe");
    jButton_generarInforme_Piloto.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_generarInforme_PilotoMouseClicked(evt);
        }
    });

    jTextField_descripcionInforme_piloto.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField_descripcionInforme_pilotoActionPerformed(evt);
        }
    });

    jLabel_descripcionInforme_piloto.setText("Descripción Informe");

    jComboBox_coche_piloto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jComboBox_coche_piloto.setVisible(false);

    jButton_detalles_Piloto.setText("Ver detalles de un piloto");
    jButton_detalles_Piloto.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_detalles_PilotoMouseClicked(evt);
        }
    });

    jButton_anadirCoche_piloto.setText("Añadir Coche al Piloto");
    jButton_anadirCoche_piloto.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_anadirCoche_pilotoMouseClicked(evt);
        }
    });
    jButton_anadirCoche_piloto.setVisible(false);

    javax.swing.GroupLayout jPanel_pilotoLayout = new javax.swing.GroupLayout(jPanel_piloto);
    jPanel_piloto.setLayout(jPanel_pilotoLayout);
    jPanel_pilotoLayout.setHorizontalGroup(
        jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel_pilotoLayout.createSequentialGroup()
            .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_pilotoLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel_nombre_piloto)
                        .addComponent(jLabel_edad_piloto)
                        .addComponent(jLabel_descripcionInforme_piloto)
                        .addComponent(jComboBox_coche_piloto, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(24, 24, 24)
                    .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField_descripcionInforme_piloto, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                        .addComponent(jTextField_nombre_piloto)
                        .addComponent(jTextField_edad_piloto)
                        .addComponent(jButton_anadirCoche_piloto))
                    .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_pilotoLayout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton_guardarModificacion_pilotos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_cancelar_pilotos, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)))
                        .addGroup(jPanel_pilotoLayout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(jButton_generarInforme_Piloto)
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addGroup(jPanel_pilotoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel_pilotoLayout.createSequentialGroup()
                            .addComponent(jButton_detalles_Piloto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(301, 301, 301)))
                    .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton_modificar_piloto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_aniadir_piloto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_borrar_piloto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addContainerGap(7, Short.MAX_VALUE))
    );
    jPanel_pilotoLayout.setVerticalGroup(
        jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel_pilotoLayout.createSequentialGroup()
            .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_pilotoLayout.createSequentialGroup()
                    .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_pilotoLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jButton_aniadir_piloto)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton_modificar_piloto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton_borrar_piloto))
                        .addGroup(jPanel_pilotoLayout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton_detalles_Piloto)))
                    .addGap(49, 49, 49)
                    .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_nombre_piloto)
                        .addComponent(jTextField_nombre_piloto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_edad_piloto)
                        .addComponent(jTextField_edad_piloto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel_pilotoLayout.createSequentialGroup()
                    .addGap(171, 171, 171)
                    .addComponent(jButton_guardarModificacion_pilotos)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton_cancelar_pilotos)))
            .addGap(18, 18, 18)
            .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jComboBox_coche_piloto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_anadirCoche_piloto))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
            .addGroup(jPanel_pilotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField_descripcionInforme_piloto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel_descripcionInforme_piloto)
                .addComponent(jButton_generarInforme_Piloto))
            .addGap(34, 34, 34))
    );

    jTabbedPane.addTab("Piloto", jPanel_piloto);

    jTable_informe.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "idInforme", "Descripción"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane3.setViewportView(jTable_informe);
    if (jTable_informe.getColumnModel().getColumnCount() > 0) {
        jTable_informe.getColumnModel().getColumn(0).setResizable(false);
        jTable_informe.getColumnModel().getColumn(1).setResizable(false);
    }

    jLabel_descripcion_informe.setText("Descripción");
    jLabel_descripcion_informe.setVisible(false);

    jTextField_descripcion_informe.setVisible(false);

    jButton_guardar_informe.setText("Guardar Modificación");
    jButton_guardar_informe.setVisible(false);
    jButton_guardar_informe.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_guardar_informeMouseClicked(evt);
        }
    });

    jButton_cancelar_informe.setText("Cancelar Modificación");
    jButton_cancelar_informe.setVisible(false);
    jButton_cancelar_informe.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_cancelar_informeMouseClicked(evt);
        }
    });

    jButton_borrar_informe.setText("Borrar");
    jButton_borrar_informe.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_borrar_informeMouseClicked(evt);
        }
    });

    jButton_modificar_informe.setText("Modificar");
    jButton_modificar_informe.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_modificar_informeMouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jPanel_informeLayout = new javax.swing.GroupLayout(jPanel_informe);
    jPanel_informe.setLayout(jPanel_informeLayout);
    jPanel_informeLayout.setHorizontalGroup(
        jPanel_informeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel_informeLayout.createSequentialGroup()
            .addGroup(jPanel_informeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_informeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel_informeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton_modificar_informe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_borrar_informe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel_informeLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel_descripcion_informe)
                    .addGap(24, 24, 24)
                    .addComponent(jTextField_descripcion_informe, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(38, 38, 38)
                    .addGroup(jPanel_informeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton_guardar_informe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_cancelar_informe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addContainerGap(43, Short.MAX_VALUE))
    );
    jPanel_informeLayout.setVerticalGroup(
        jPanel_informeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel_informeLayout.createSequentialGroup()
            .addGroup(jPanel_informeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_informeLayout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jButton_modificar_informe, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton_borrar_informe))
                .addGroup(jPanel_informeLayout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(49, 49, 49)
            .addGroup(jPanel_informeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel_informeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_descripcion_informe)
                    .addComponent(jTextField_descripcion_informe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_informeLayout.createSequentialGroup()
                    .addComponent(jButton_guardar_informe)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton_cancelar_informe)))
            .addContainerGap(200, Short.MAX_VALUE))
    );

    jTabbedPane.addTab("Informe", jPanel_informe);

    jTable_coche.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "idCoche", "Marca", "Modelo"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane4.setViewportView(jTable_coche);
    if (jTable_coche.getColumnModel().getColumnCount() > 0) {
        jTable_coche.getColumnModel().getColumn(0).setResizable(false);
        jTable_coche.getColumnModel().getColumn(1).setResizable(false);
        jTable_coche.getColumnModel().getColumn(2).setResizable(false);
    }

    jLabel_modelo_coche.setText("Modelo");

    jLabel_marca_coche.setText("Marca");

    jButton_guardar_coche.setText("Guardar Modificación");
    jButton_guardar_coche.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_guardar_cocheMouseClicked(evt);
        }
    });
    jButton_guardar_coche.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_guardar_cocheActionPerformed(evt);
        }
    });
    jButton_guardar_coche.setVisible(false);

    jButton_cancelar_coche.setText("Cancelar Modificación");
    jButton_cancelar_coche.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_cancelar_cocheMouseClicked(evt);
        }
    });
    jButton_cancelar_coche.setVisible(false);

    jButton_borrar_coche.setText("Borrar");
    jButton_borrar_coche.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_borrar_cocheMouseClicked(evt);
        }
    });

    jButton_modificar_coche.setText("Modificar");
    jButton_modificar_coche.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_modificar_cocheMouseClicked(evt);
        }
    });

    jButton_aniadir_coche.setText("Añadir");
    jButton_aniadir_coche.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_aniadir_cocheMouseClicked(evt);
        }
    });

    jComboBox_anadirIngeniero_Coche.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jComboBox_anadirIngeniero_Coche.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox_anadirIngeniero_CocheActionPerformed(evt);
        }
    });
    jComboBox_anadirIngeniero_Coche.setVisible(false);
    /*if(this.miControlador.getPilotos() != null)
    for(Piloto p : this.miControlador.getPilotos()){
        if(p.getEquipo_piloto() == null){
            jComboBox_anadirPiloto_EquiposCarreras.addItem(p.getNombre());
        }
    }
    }*/

    jButton_anadirIngeniero_Coche.setText("Añadir Ingeniero al coche");
    jButton_anadirIngeniero_Coche.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_anadirIngeniero_CocheMouseClicked(evt);
        }
    });
    jButton_anadirIngeniero_Coche.setVisible(false);

    jButton_detalles_Coche.setText("Ver detalles de un coche");
    jButton_detalles_Coche.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_detalles_CocheMouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jPanel_cocheLayout = new javax.swing.GroupLayout(jPanel_coche);
    jPanel_coche.setLayout(jPanel_cocheLayout);
    jPanel_cocheLayout.setHorizontalGroup(
        jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel_cocheLayout.createSequentialGroup()
            .addGroup(jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_cocheLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton_modificar_coche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_aniadir_coche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_borrar_coche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel_cocheLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel_cocheLayout.createSequentialGroup()
                            .addGroup(jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel_modelo_coche)
                                .addComponent(jLabel_marca_coche))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_modelo_coche, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                .addComponent(jTextField_marca_coche)))
                        .addGroup(jPanel_cocheLayout.createSequentialGroup()
                            .addComponent(jComboBox_anadirIngeniero_Coche, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                            .addComponent(jButton_anadirIngeniero_Coche)))
                    .addGap(38, 38, 38)
                    .addGroup(jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton_guardar_coche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_cancelar_coche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel_cocheLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton_detalles_Coche)))
            .addContainerGap(43, Short.MAX_VALUE))
    );
    jPanel_cocheLayout.setVerticalGroup(
        jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel_cocheLayout.createSequentialGroup()
            .addGroup(jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_cocheLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jButton_aniadir_coche)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton_modificar_coche, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton_borrar_coche))
                .addGroup(jPanel_cocheLayout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButton_detalles_Coche)
            .addGap(43, 43, 43)
            .addGroup(jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel_modelo_coche)
                .addComponent(jTextField_modelo_coche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_guardar_coche))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel_marca_coche)
                .addComponent(jTextField_marca_coche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_cancelar_coche))
            .addGap(18, 18, 18)
            .addGroup(jPanel_cocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBox_anadirIngeniero_Coche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_anadirIngeniero_Coche))
            .addContainerGap(113, Short.MAX_VALUE))
    );

    jTabbedPane.addTab("Coche", jPanel_coche);

    jTable_ingeniero.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "idIngeniero", "Fecha de Nacimiento", "Sueldo"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane5.setViewportView(jTable_ingeniero);
    if (jTable_ingeniero.getColumnModel().getColumnCount() > 0) {
        jTable_ingeniero.getColumnModel().getColumn(0).setResizable(false);
        jTable_ingeniero.getColumnModel().getColumn(1).setResizable(false);
        jTable_ingeniero.getColumnModel().getColumn(2).setResizable(false);
    }

    jLabel_fechaNac_ingeniero.setText("Fecha Nacimiento");

    jLabel_sueldo_ingeniero.setText("Sueldo");

    jButton_guardar_ingeniero.setText("Guardar Modificación");
    jButton_guardar_ingeniero.setVisible(false);
    jButton_guardar_ingeniero.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_guardar_ingenieroMouseClicked(evt);
        }
    });

    jButton_cancelar_ingeniero.setText("Cancelar Modificación");
    jButton_cancelar_ingeniero.setVisible(false);
    jButton_cancelar_ingeniero.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_cancelar_ingenieroMouseClicked(evt);
        }
    });

    jButton_borrar_ingeniero.setText("Borrar");
    jButton_borrar_ingeniero.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_borrar_ingenieroMouseClicked(evt);
        }
    });
    jButton_borrar_ingeniero.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_borrar_ingenieroActionPerformed(evt);
        }
    });

    jButton_modificar_ingeniero.setText("Modificar");
    jButton_modificar_ingeniero.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_modificar_ingenieroMouseClicked(evt);
        }
    });

    jButton_aniadir_ingeniero.setText("Añadir");
    jButton_aniadir_ingeniero.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_aniadir_ingenieroMouseClicked(evt);
        }
    });

    jComboBox_anadirCoche_Ingeniero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jComboBox_anadirCoche_Ingeniero.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox_anadirCoche_IngenieroActionPerformed(evt);
        }
    });
    jComboBox_anadirCoche_Ingeniero.setVisible(false);
    /*if(this.miControlador.getPilotos() != null)
    for(Piloto p : this.miControlador.getPilotos()){
        if(p.getEquipo_piloto() == null){
            jComboBox_anadirPiloto_EquiposCarreras.addItem(p.getNombre());
        }
    }
    }*/

    jButton_anadirCoche_Ingeniero.setText("Añadir coche al ingeniero");
    jButton_anadirCoche_Ingeniero.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_anadirCoche_IngenieroMouseClicked(evt);
        }
    });
    jButton_anadirCoche_Ingeniero.setVisible(false);

    jButton_detalles_Ingeniero.setText("Ver detalles de un ingeniero");
    jButton_detalles_Ingeniero.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton_detalles_IngenieroMouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jPanel_ingenieroLayout = new javax.swing.GroupLayout(jPanel_ingeniero);
    jPanel_ingeniero.setLayout(jPanel_ingenieroLayout);
    jPanel_ingenieroLayout.setHorizontalGroup(
        jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel_ingenieroLayout.createSequentialGroup()
            .addGap(27, 27, 27)
            .addGroup(jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ingenieroLayout.createSequentialGroup()
                    .addGroup(jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_detalles_Ingeniero))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton_modificar_ingeniero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_aniadir_ingeniero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_borrar_ingeniero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel_ingenieroLayout.createSequentialGroup()
                    .addGroup(jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel_fechaNac_ingeniero)
                        .addComponent(jLabel_sueldo_ingeniero))
                    .addGap(24, 24, 24)
                    .addGroup(jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField_fechnac_ingeniero, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                        .addComponent(jTextField_sueldo_ingeniero))
                    .addGap(38, 38, 38)
                    .addGroup(jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton_guardar_ingeniero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_cancelar_ingeniero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel_ingenieroLayout.createSequentialGroup()
                    .addComponent(jComboBox_anadirCoche_Ingeniero, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(44, 44, 44)
                    .addComponent(jButton_anadirCoche_Ingeniero)))
            .addContainerGap(22, Short.MAX_VALUE))
    );
    jPanel_ingenieroLayout.setVerticalGroup(
        jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel_ingenieroLayout.createSequentialGroup()
            .addGroup(jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ingenieroLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jButton_aniadir_ingeniero)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton_modificar_ingeniero, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton_borrar_ingeniero))
                .addGroup(jPanel_ingenieroLayout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton_detalles_Ingeniero)))
            .addGap(49, 49, 49)
            .addGroup(jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel_fechaNac_ingeniero)
                .addComponent(jTextField_fechnac_ingeniero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_guardar_ingeniero))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel_sueldo_ingeniero)
                .addComponent(jTextField_sueldo_ingeniero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_cancelar_ingeniero))
            .addGap(20, 20, 20)
            .addGroup(jPanel_ingenieroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBox_anadirCoche_Ingeniero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_anadirCoche_Ingeniero))
            .addContainerGap(118, Short.MAX_VALUE))
    );

    jTabbedPane.addTab("Ingeniero", jPanel_ingeniero);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane)
            .addContainerGap())
    );

    jTabbedPane.getAccessibleContext().setAccessibleName("Ciudad");

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_borrar_ingenieroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_ingenieroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_borrar_ingenieroActionPerformed

    private void jButton_guardar_equipoCarrerasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_guardar_equipoCarrerasMouseClicked
        // TODO add your handling code here:
        
        String idEquipo = "a";
        String nombre = "";
        //idEquipo = jTextField_idEquipo_equipoCarreras.getText();
        nombre = jTextField_nombre_equipoCarreras.getText();
        //EquipoCarreras unEquipo = new EquipoCarreras(idEquipo, nombre);
        
        if(nombre.equals("")){
            JOptionPane.showMessageDialog(null, "ERROR: no debe dejar los campos en blanco, abortando...");
        }else{
            this.miControlador.insertarEquipoCarreras(nombre);
        }
        //this.miControlador.mostrarEquiposCarreras();
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablaEquipos();
        //jTextField_idEquipo_equipoCarreras.setText("");
        jTextField_nombre_equipoCarreras.setText("");
        
        
    }//GEN-LAST:event_jButton_guardar_equipoCarrerasMouseClicked
    
    
    private void jButton_modificar_equipoCarrerasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_modificar_equipoCarrerasMouseClicked
        // TODO add your handling code here:
     this.traerPilotosControladorVista();
     int fila = jTable_equipoCarreras.getSelectedRow();
     
    if(fila !=-1){
        jButton_guardarModificacion_equipoCarreras.setVisible(true);
        jComboBox_anadirPiloto_EquiposCarreras.setVisible(true);
        jButton_anadirPiloto_EquipoCarreras.setVisible(true);
        jButton_cancelar_equipoCarreras.setVisible(true);
       //System.out.println("FILA: " + fila);
        //jTextField_idEquipo_equipoCarreras.setText(miControlador.getUnEquipo(fila).getIdEquipo());
        jTextField_nombre_equipoCarreras.setText(miControlador.getUnEquipo(fila).getNombre());
    }else{
        JOptionPane.showMessageDialog(null, "Por favor seleccione un equipo de carreras");
    }
    
    }//GEN-LAST:event_jButton_modificar_equipoCarrerasMouseClicked

    private void jButton_guardarModificacion_equipoCarrerasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_guardarModificacion_equipoCarrerasMouseClicked
        // TODO add your handling code here:
        int fila = jTable_equipoCarreras.getSelectedRow();
        
        if(fila != -1){
            String nombre = "", idEquipo = this.misEquipos.get(fila).getIdEquipo();
            //idEquipo = jTextField_idEquipo_equipoCarreras.getText();
            nombre = jTextField_nombre_equipoCarreras.getText();

            if(nombre.equals("")){
               JOptionPane.showMessageDialog(null, "ERROR: no debe dejar los campos en blanco");
            }else{
                this.miControlador.modificarNombreEquipoCarreras(idEquipo, nombre);
            }

            this.miControlador.obtenerDatosBD();
            this.traerDatosControladorVista();
            this.actualizarTablaEquipos();
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un equipo de carreras");
        }
        this.jButton_guardarModificacion_equipoCarreras.setVisible(false);
        this.jComboBox_anadirPiloto_EquiposCarreras.setVisible(false);
        this.jButton_anadirPiloto_EquipoCarreras.setVisible(false);
       // jTextField_idEquipo_equipoCarreras.setText("");
        jTextField_nombre_equipoCarreras.setText("");
     
    }//GEN-LAST:event_jButton_guardarModificacion_equipoCarrerasMouseClicked

    private void jButton_borrar_equipoCarrerasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_borrar_equipoCarrerasMouseClicked
        // TODO add your handling code here:
        int fila = jTable_equipoCarreras.getSelectedRow();
        
        String idEquipo = this.misEquipos.get(fila).getIdEquipo();
        
        try {
            this.miControlador.eliminarEquipoCarreras(idEquipo);
            //this.misEquipos.remove(fila);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR: Ha sucedido un error inesperado...");
        }
        
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablaEquipos();
    }//GEN-LAST:event_jButton_borrar_equipoCarrerasMouseClicked

    private void jButton_borrar_equipoCarrerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_equipoCarrerasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_borrar_equipoCarrerasActionPerformed

    private void jButton_modificar_equipoCarrerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_equipoCarrerasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_modificar_equipoCarrerasActionPerformed

    private void jButton_guardar_equipoCarrerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardar_equipoCarrerasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_guardar_equipoCarrerasActionPerformed

    private void jButton_detalles_EquipoCarrerasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_detalles_EquipoCarrerasMouseClicked
        // TODO add your handling code here:
        int fila = jTable_equipoCarreras.getSelectedRow();
        String cadena = "Los Pilotos que pertenecen a este equipo son:\n";
        if(this.miControlador.getEquipos().get(fila).getPilotos() != null){
            for(Piloto p : this.miControlador.getEquipos().get(fila).getPilotos()){
                if(p != null){
                    cadena += "ID: " + p.getIdPiloto() + " Nombre: " + p.getNombre() + "\n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, cadena);
    }//GEN-LAST:event_jButton_detalles_EquipoCarrerasMouseClicked

    private void jComboBox_anadirPiloto_EquiposCarrerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_anadirPiloto_EquiposCarrerasActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox_anadirPiloto_EquiposCarrerasActionPerformed

    private void jButton_anadirPiloto_EquipoCarrerasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_anadirPiloto_EquipoCarrerasMouseClicked

        // TODO add your handling code here:
        int fila = jTable_equipoCarreras.getSelectedRow();
        boolean pilotoConEquipo = false;
        
        if(fila != -1){
            for(Piloto p : this.misPilotos){

                Object selectedValue = this.jComboBox_anadirPiloto_EquiposCarreras.getSelectedItem();

                if(selectedValue != null){
                    if(p.getIdPiloto().equals(selectedValue)){
                        System.out.println("Equipo: " + p.getEquipo_piloto());
                        if(p.getEquipo_piloto() == null){
                            this.miControlador.asignarPilotoAEquipoCarreras(p.getIdPiloto(), this.misEquipos.get(fila).getIdEquipo());
                            this.miControlador.obtenerDatosBD();
                            this.traerDatosControladorVista();
                            this.actualizarTablaEquipos();
                        }else{
                            pilotoConEquipo = true;
                        }
                    }
                }
            }
            if(pilotoConEquipo){
                JOptionPane.showMessageDialog(null, "ERROR:\nEl piloto ya se encuentra en un equipo");
            }
        }else{
                JOptionPane.showMessageDialog(null, "Seleccione un equipo al que añadir el piloto");
        }
    }//GEN-LAST:event_jButton_anadirPiloto_EquipoCarrerasMouseClicked

    private void jButton_cancelar_equipoCarrerasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cancelar_equipoCarrerasMouseClicked
        // TODO add your handling code here:
        this.jButton_guardarModificacion_equipoCarreras.setVisible(false);
        this.jComboBox_anadirPiloto_EquiposCarreras.setVisible(false);
        this.jButton_anadirPiloto_EquipoCarreras.setVisible(false);
        jButton_cancelar_equipoCarreras.setVisible(false);
        //jTextField_idEquipo_equipoCarreras.setText("");
        jTextField_nombre_equipoCarreras.setText("");
    }//GEN-LAST:event_jButton_cancelar_equipoCarrerasMouseClicked

    private void jButton_aniadir_pilotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_aniadir_pilotoMouseClicked
        // TODO add your handling code here:
        String nombre = "";
        int edad = 0;
        Boolean abortarOperacion = false;
        nombre = jTextField_nombre_piloto.getText();
        try{
            edad = Integer.parseInt(jTextField_edad_piloto.getText());
        }
        catch(NumberFormatException nfe){
            //nfe.printStackTrace();
            abortarOperacion = true;
            JOptionPane.showMessageDialog(null, "ERROR: Ha introducido una edad no válida");
        }
        
        if(nombre.equals("")){
            JOptionPane.showMessageDialog(null, "ERROR: no debe dejar los campos en blanco, abortando...");
        }else{
            if(!abortarOperacion){
                this.miControlador.insertarPiloto(nombre, edad);
            }
        }
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
        //jTextField_idPiloto_piloto.setText("");
        jTextField_nombre_piloto.setText("");
        jTextField_edad_piloto.setText("");
    }//GEN-LAST:event_jButton_aniadir_pilotoMouseClicked

    private void jButton_guardarModificacion_pilotosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_guardarModificacion_pilotosMouseClicked
        // TODO add your handling code here:
        int fila = jTable_piloto.getSelectedRow();
        Boolean abortarOperacion = false;
        String idPiloto = "";
        String nombre = "";
        int edad = 0;
        try{
            edad = Integer.parseInt(jTextField_edad_piloto.getText());
        }
        catch(NumberFormatException nfe){
            //nfe.printStackTrace();
            abortarOperacion = true;
            JOptionPane.showMessageDialog(null, "ERROR: Ha introducido una edad no válida");
        }
        idPiloto = this.misPilotos.get(fila).getIdPiloto();
        nombre = jTextField_nombre_piloto.getText();
        //edad = Integer.parseInt(jTextField_edad_piloto.getText());
        
        
        if(nombre.equals("")){
            JOptionPane.showMessageDialog(null, "ERROR: no debe introducir datos en blanco, abortando...");
        }else{
        
            if(!abortarOperacion){
                
                if(fila != -1){
                    this.miControlador.modificarNombrePiloto(idPiloto, nombre);
                    this.miControlador.modificarEdadPiloto(idPiloto, edad);
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un Piloto..");
                }
            }
        }
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
        
        this.jButton_guardarModificacion_pilotos.setVisible(false);
        this.jComboBox_coche_piloto.setVisible(false);
        this.jButton_anadirCoche_piloto.setVisible(false);
        this.jButton_cancelar_pilotos.setVisible(false);
        //jTextField_idPiloto_piloto.setText("");
        jTextField_nombre_piloto.setText("");
        jTextField_edad_piloto.setText("");
    }//GEN-LAST:event_jButton_guardarModificacion_pilotosMouseClicked

    private void jButton_cancelar_pilotosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cancelar_pilotosMouseClicked
        // TODO add your handling code here:
        this.jButton_guardarModificacion_pilotos.setVisible(false);
        this.jComboBox_coche_piloto.setVisible(false);
        this.jButton_anadirCoche_piloto.setVisible(false);
        this.jButton_cancelar_pilotos.setVisible(false);
        //jTextField_idPiloto_piloto.setText("");
        jTextField_nombre_piloto.setText("");
        jTextField_edad_piloto.setText("");
    }//GEN-LAST:event_jButton_cancelar_pilotosMouseClicked

    private void jTextField_descripcionInforme_pilotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_descripcionInforme_pilotoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_descripcionInforme_pilotoActionPerformed

    private void jButton_modificar_pilotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_modificar_pilotoMouseClicked
     // TODO add your handling code here:
     int fila = jTable_piloto.getSelectedRow();
    
     if(fila != -1){
        jButton_guardarModificacion_pilotos.setVisible(true);
        jComboBox_coche_piloto.setVisible(true);
        jButton_anadirCoche_piloto.setVisible(true);
        jButton_cancelar_pilotos.setVisible(true);
       //System.out.println("FILA: " + fila);
        //jTextField_idPiloto_piloto.setText(miControlador.getPilotos().get(fila).getIdPiloto());
        jTextField_nombre_piloto.setText(miControlador.getPilotos().get(fila).getNombre());
        jTextField_edad_piloto.setText(Integer.toString(miControlador.getPilotos().get(fila).getEdad()));
     }else{
         JOptionPane.showMessageDialog(null, "Por favor seleccione un piloto");
     }
    }//GEN-LAST:event_jButton_modificar_pilotoMouseClicked

    private void jButton_anadirCoche_pilotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_anadirCoche_pilotoMouseClicked
        // TODO add your handling code here:
        int fila = jTable_piloto.getSelectedRow();
        boolean CocheConPiloto = false;
        
        if(fila != -1){
            for(Coche c : this.misCoches){

                Object selectedValue = this.jComboBox_coche_piloto.getSelectedItem();

                if(selectedValue != null){
                    if(c.getIdCoche().equals(selectedValue)){
                        if(c.getPiloto() == null){
                           this.miControlador.asignarCocheAPiloto(c.getIdCoche(), this.misPilotos.get(fila).getIdPiloto());
                           this.miControlador.obtenerDatosBD();
                            this.traerDatosControladorVista();
                            this.actualizarTablasVista();
                        }else{
                            CocheConPiloto = true;
                        }
                    }
                }
            }
            if(CocheConPiloto){
                JOptionPane.showMessageDialog(null, "ERROR:\nEl coche está siendo usado por otro piloto");
            }
        }else{
                JOptionPane.showMessageDialog(null, "Seleccione el piloto al que añadirle el coche");
        }
    }//GEN-LAST:event_jButton_anadirCoche_pilotoMouseClicked

    private void jButton_borrar_pilotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_borrar_pilotoMouseClicked
        // TODO add your handling code here:
        int fila = jTable_piloto.getSelectedRow();
        
        try {
            this.miControlador.eliminarPiloto(this.misPilotos.get(fila).getIdPiloto());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR: Ha sucedido un error inesperado...");
        }
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
        
        
        //this.misEquipos.remove(fila);
        
        
    }//GEN-LAST:event_jButton_borrar_pilotoMouseClicked

    private void jButton_generarInforme_PilotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_generarInforme_PilotoMouseClicked
        // TODO add your handling code here:
        int fila = jTable_piloto.getSelectedRow();
       
        String descripcion = jTextField_descripcionInforme_piloto.getText();
        
        if(descripcion == null){
            JOptionPane.showMessageDialog(null, "ERROR: no debe dejar los campos en blanco, abortando...");
        }else{
            if(fila != -1){
                if(this.miControlador.getPilotos().get(fila).getGenera_en_piloto() == null){

                   this.miControlador.generarInformePiloto(this.miControlador.getPilotos().get(fila).getIdPiloto(), descripcion);
                    
                }else{
                    if(this.miControlador.getPilotos().get(fila).getGenera_en_piloto().getInforme_genera() == null){
                        this.miControlador.generarInformePiloto(this.miControlador.getPilotos().get(fila).getIdPiloto(), descripcion);
                    }else{
                        JOptionPane.showMessageDialog(null, "ERROR: no es posible generar más de un informe en un piloto");
                    }
                }

                this.miControlador.obtenerDatosBD();
                this.traerDatosControladorVista();
                this.actualizarTablasVista();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR: debe seleccionar un piloto al que generar un informe");
            }
        }
        jTextField_descripcionInforme_piloto.setText("");
    }//GEN-LAST:event_jButton_generarInforme_PilotoMouseClicked

    private void jButton_modificar_informeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_modificar_informeMouseClicked
        // TODO add your handling code here:
        int fila = jTable_informe.getSelectedRow();
        if(fila != -1){
            //jLabel_idInforme_informe.setVisible(true);
            //jTextField_idInforme_informe.setVisible(true);
            jLabel_descripcion_informe.setVisible(true);
            jTextField_descripcion_informe.setVisible(true);
            jButton_cancelar_informe.setVisible(true);
            jButton_guardar_informe.setVisible(true);
            //jTextField_idInforme_informe.setText(this.miControlador.getInformes().get(fila).getIdInforme());
            jTextField_descripcion_informe.setText(this.miControlador.getInformes().get(fila).getDescripcion());
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un informe");
        }
    }//GEN-LAST:event_jButton_modificar_informeMouseClicked

    private void jButton_detalles_PilotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_detalles_PilotoMouseClicked
        // TODO add your handling code here:
        int fila = jTable_piloto.getSelectedRow();
        String cadena = "El piloto usa el coche: \n";
        
        if(this.miControlador.getPilotos().get(fila).getCoche_piloto() != null){
            cadena += "ID: " + this.miControlador.getPilotos().get(fila).getCoche_piloto().getIdCoche() + "\n";
            
        }else{
            cadena = "El piloto no tiene asignado ningún coche.\n";
        }
        if(this.miControlador.getPilotos().get(fila).getGenera_en_piloto() != null){
            if(this.miControlador.getPilotos().get(fila).getGenera_en_piloto().getInforme_genera() != null){
                cadena += "Informe generado por el piloto: \n";
                cadena += this.miControlador.getPilotos().get(fila).getGenera_en_piloto().getInforme_genera().getIdInforme() + "\n";
            }else{
                cadena += "El piloto no ha generado ningún informe aun.";
            }
        }else{
            cadena += "El piloto no ha generado ningún informe aun.";
        }
        JOptionPane.showMessageDialog(null, cadena);
    }//GEN-LAST:event_jButton_detalles_PilotoMouseClicked

    private void jButton_guardar_informeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_guardar_informeMouseClicked
        // TODO add your handling code here:
        int fila = jTable_informe.getSelectedRow();
        
        //String idInforme = jTextField_idInforme_informe.getText();
        String descripcion = jTextField_descripcion_informe.getText();
        
        
        
        //Informe unInforme = new Informe(idInforme, descripcion);
        
        if(descripcion.equals("")){
            JOptionPane.showMessageDialog(null, "ERROR: no debe dejar los campos en blanco, abortando...");
        }else{
            if(fila != -1){
                this.miControlador.modificarDescripcionInforme(descripcion, this.misInformes.get(fila).getIdInforme());
            }else{
                JOptionPane.showMessageDialog(null, "ERROR: Debe seleccionar un informe");
            }
        }
        
        
        
        jLabel_descripcion_informe.setVisible(false);
        jTextField_descripcion_informe.setVisible(false);
        jButton_cancelar_informe.setVisible(false);
        jButton_guardar_informe.setVisible(false);
        
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
    }//GEN-LAST:event_jButton_guardar_informeMouseClicked

    private void jButton_cancelar_informeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cancelar_informeMouseClicked
        // TODO add your handling code here:
        //jLabel_idInforme_informe.setVisible(false);
        //jTextField_idInforme_informe.setVisible(false);
        jLabel_descripcion_informe.setVisible(false);
        jTextField_descripcion_informe.setVisible(false);
        jButton_cancelar_informe.setVisible(false);
        jButton_guardar_informe.setVisible(false);
        //jTextField_idInforme_informe.setText("");
        jTextField_descripcion_informe.setText("");
    }//GEN-LAST:event_jButton_cancelar_informeMouseClicked

    private void jButton_borrar_informeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_borrar_informeMouseClicked
        // TODO add your handling code here:
        int fila = jTable_informe.getSelectedRow();
        this.misInformes.get(fila).getGenera_en_informe().setInforme_genera(null);
        
        if(fila != -1){
            try {
                this.miControlador.eliminarInforme(this.misInformes.get(fila).getIdInforme());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR: Ha sucedido un error inesperado...");
            }
        }
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
    }//GEN-LAST:event_jButton_borrar_informeMouseClicked

    private void jButton_aniadir_cocheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_aniadir_cocheMouseClicked
        // TODO add your handling code here:
        String modelo = "";
        String marca = "";
        
        modelo = jTextField_modelo_coche.getText();
        marca = jTextField_marca_coche.getText();
        
        if(modelo.equals("") || marca.equals("")){
            JOptionPane.showMessageDialog(null, "ERROR: no debe dejar campos en blanco, abortando...");
        }else{
            this.miControlador.insertarCoche(marca, modelo);
        }
        
        //this.miControlador.mostrarEquiposCarreras();
        
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
        //jTextField_idPiloto_piloto.setText("");
        jTextField_marca_coche.setText("");
        jTextField_modelo_coche.setText("");
    }//GEN-LAST:event_jButton_aniadir_cocheMouseClicked

    private void jButton_guardar_cocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardar_cocheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_guardar_cocheActionPerformed

    private void jButton_modificar_cocheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_modificar_cocheMouseClicked
        // TODO add your handling code here:
        int fila = jTable_coche.getSelectedRow();
        
        if(fila != -1){
            jButton_cancelar_coche.setVisible(true);
            jButton_guardar_coche.setVisible(true);
            jComboBox_anadirIngeniero_Coche.setVisible(true);
            jButton_anadirIngeniero_Coche.setVisible(true);
            //jTextField_idCoche_coche.setText(this.miControlador.getCoches().get(fila).getIdCoche());
            jTextField_marca_coche.setText(this.miControlador.getCoches().get(fila).getMarca());
            jTextField_modelo_coche.setText(this.miControlador.getCoches().get(fila).getModelo());
        }else{
            JOptionPane.showMessageDialog(null, "Por favor seleccione un coche");
        }
    }//GEN-LAST:event_jButton_modificar_cocheMouseClicked

    private void jButton_guardar_cocheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_guardar_cocheMouseClicked
        // TODO add your handling code here:
        int fila = jTable_coche.getSelectedRow();
        
        //String idCoche = jTextField_idCoche_coche.getText();
        String marca = jTextField_marca_coche.getText();
        String modelo = jTextField_modelo_coche.getText();

        if(marca.equals("") || modelo.equals("")){
            JOptionPane.showMessageDialog(null, "ERROR: no debe dejar campos en blanco, abortando...");
        }else{
            if(fila != -1){
                this.miControlador.modificarMarcaCoche(this.misCoches.get(fila).getIdCoche(), marca);
                this.miControlador.modificarModeloCoche(this.misCoches.get(fila).getIdCoche(), marca);
            }
        }
        jButton_cancelar_coche.setVisible(false);
        jButton_guardar_coche.setVisible(false);
        jComboBox_anadirIngeniero_Coche.setVisible(false);
        jButton_anadirIngeniero_Coche.setVisible(false);
        //jTextField_idCoche_coche.setText("");
        jTextField_modelo_coche.setText("");
        jTextField_marca_coche.setText("");
        
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
    }//GEN-LAST:event_jButton_guardar_cocheMouseClicked

    private void jButton_cancelar_cocheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cancelar_cocheMouseClicked
        // TODO add your handling code here:
        //jTextField_idCoche_coche.setText("");
        jTextField_modelo_coche.setText("");
        jTextField_marca_coche.setText("");
        jButton_cancelar_coche.setVisible(false);
        jButton_guardar_coche.setVisible(false);
        jComboBox_anadirIngeniero_Coche.setVisible(false);
        jButton_anadirIngeniero_Coche.setVisible(false);
    }//GEN-LAST:event_jButton_cancelar_cocheMouseClicked

    private void jButton_borrar_cocheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_borrar_cocheMouseClicked
        // TODO add your handling code here:
        int fila = jTable_coche.getSelectedRow();
        
        if(fila != -1){
            try {
                this.miControlador.eliminarCoche(this.misCoches.get(fila).getIdCoche());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR: Ha sucedido un error inesperado...");
            }
        }
        
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
    }//GEN-LAST:event_jButton_borrar_cocheMouseClicked

    private void jComboBox_anadirIngeniero_CocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_anadirIngeniero_CocheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_anadirIngeniero_CocheActionPerformed

    private void jButton_anadirIngeniero_CocheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_anadirIngeniero_CocheMouseClicked
        // TODO add your handling code here:
        int fila = jTable_coche.getSelectedRow();
        if(fila != -1){
            for(Ingeniero i : this.misIngenieros){

                Object selectedValue = this.jComboBox_anadirIngeniero_Coche.getSelectedItem();

                if(selectedValue != null){
                    if(i.getIdIngeniero().equals(selectedValue)){
                       if(!this.miControlador.comprobarSiIngenieroEstaACargoDeCoche(i.getIdIngeniero(), this.misCoches.get(fila).getIdCoche())){
                            this.miControlador.crearTaller(this.misCoches.get(fila).getIdCoche(), i.getIdIngeniero());
                       }else{
                           JOptionPane.showMessageDialog(null, "ERROR: No puede asignar un ingeniero que ya está asignado...");
                       }
                    }


                }
            }
        }else{
                JOptionPane.showMessageDialog(null, "Seleccione el coche al que añadirle el ingeniero");
        }
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
    }//GEN-LAST:event_jButton_anadirIngeniero_CocheMouseClicked

    private void jButton_detalles_CocheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_detalles_CocheMouseClicked
        // TODO add your handling code here:
        int fila = jTable_coche.getSelectedRow();
        String cadena = "El coche está siendo usado por el piloto: \n";
        if(this.miControlador.getCoches().get(fila).getPiloto() != null){
            cadena += "ID: " + this.miControlador.getCoches().get(fila).getPiloto().getIdPiloto()+ "\n";
        }else{
            cadena = "El coche no está siendo usado por ningún piloto\n";
        }
        cadena += "Ingenieros que están a cargo de este coche: \n";
        for(Ingeniero i : this.miControlador.getCoches().get(fila).getIngenieros_coche()){
            cadena += i.getIdIngeniero() + "\n";
        }
        JOptionPane.showMessageDialog(null, cadena);
    }//GEN-LAST:event_jButton_detalles_CocheMouseClicked

    private void jButton_aniadir_ingenieroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_aniadir_ingenieroMouseClicked
        // TODO add your handling code here:
//        String idIngeniero = "";
        String fechaNac = "";
        double sueldo = 0.0;
        Boolean abortarOperacion = false;
        //idIngeniero = jTextField_idIngeniero_ingeniero.getText();
        fechaNac = jTextField_fechnac_ingeniero.getText();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        formatoFecha.setLenient(false);
        
        try{
            Date fecha = formatoFecha.parse(fechaNac);
        }
        catch(ParseException pe){
            JOptionPane.showMessageDialog(null, "ERROR: debe introducir una fecha válida (yyyy-MM-dd)");
            abortarOperacion = true;
        }
        
        try{
            sueldo = Double.parseDouble(jTextField_sueldo_ingeniero.getText());
        }
        catch(NumberFormatException nfe){
            //nfe.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR: debes introducir un sueldo que sea un número real");
            abortarOperacion = true;
        }
        if(sueldo < 0.0){
            JOptionPane.showMessageDialog(null, "ERROR: no debe introducir un sueldo negativo...");
        }else{
            if(!abortarOperacion){
                this.miControlador.insertarIngeniero(fechaNac, sueldo);
            }
        }
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
        //jTextField_idIngeniero_ingeniero.setText("");
        jTextField_fechnac_ingeniero.setText("");
        jTextField_sueldo_ingeniero.setText("");
        
    }//GEN-LAST:event_jButton_aniadir_ingenieroMouseClicked

    private void jButton_modificar_ingenieroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_modificar_ingenieroMouseClicked
        // TODO add your handling code here:
        int fila = jTable_ingeniero.getSelectedRow();
        
        if(fila != -1){
            jButton_cancelar_ingeniero.setVisible(true);
            jButton_guardar_ingeniero.setVisible(true);
            jComboBox_anadirCoche_Ingeniero.setVisible(true);
            jButton_anadirCoche_Ingeniero.setVisible(true);
            //jTextField_idIngeniero_ingeniero.setText(this.miControlador.getIngenieros().get(fila).getIdIngeniero());
            jTextField_fechnac_ingeniero.setText(this.miControlador.getIngenieros().get(fila).getFechaNacimiento());
            jTextField_sueldo_ingeniero.setText(Double.toString(this.miControlador.getIngenieros().get(fila).getSueldo()));
        }else{
            JOptionPane.showMessageDialog(null, "Por favor seleccione un ingeniero");
        }
    }//GEN-LAST:event_jButton_modificar_ingenieroMouseClicked

    private void jComboBox_anadirCoche_IngenieroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_anadirCoche_IngenieroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_anadirCoche_IngenieroActionPerformed

    private void jButton_anadirCoche_IngenieroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_anadirCoche_IngenieroMouseClicked
        // TODO add your handling code here:
        int fila = jTable_ingeniero.getSelectedRow();
        
        if(fila != -1){
            for(Coche c : this.misCoches){

                Object selectedValue = this.jComboBox_anadirCoche_Ingeniero.getSelectedItem();

                if(selectedValue != null){
                    if(c.getIdCoche().equals(selectedValue)){
                       if(!this.miControlador.comprobarSiIngenieroEstaACargoDeCoche(this.misIngenieros.get(fila).getIdIngeniero(), c.getIdCoche())){
                            this.miControlador.crearTaller(c.getIdCoche(), this.misIngenieros.get(fila).getIdIngeniero());
                       }else{
                           JOptionPane.showMessageDialog(null, "ERROR: No puede asignar un ingeniero que ya está asignado...");
                       }
                    }


                }
            }
        }else{
                JOptionPane.showMessageDialog(null, "Seleccione el coche al que añadirle el ingeniero");
        }
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
    }//GEN-LAST:event_jButton_anadirCoche_IngenieroMouseClicked

    private void jButton_guardar_ingenieroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_guardar_ingenieroMouseClicked
        // TODO add your handling code here:
        int fila = jTable_ingeniero.getSelectedRow();
        Boolean abortarOperacion = false;
        
        //String idIngeniero = jTextField_idIngeniero_ingeniero.getText();
        String fecha = jTextField_fechnac_ingeniero.getText();
        String sueldoCadena = jTextField_sueldo_ingeniero.getText();
        double sueldo = 0.0;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        formatoFecha.setLenient(false);
        
        try{
            Date fechaFormato = formatoFecha.parse(fecha);
        }
        catch(ParseException pe){
            JOptionPane.showMessageDialog(null, "ERROR: debe introducir una fecha válida (yyyy-MM-dd)");
            abortarOperacion = true;
        }
        
        try{
            sueldo = Double.parseDouble(sueldoCadena);
        }
        catch(NumberFormatException nfe){
           // nfe.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR: debes introducir un número real como sueldo");
            abortarOperacion = true;
        }
        
        if(fila != -1){
            if(sueldo < 0.0){
                JOptionPane.showMessageDialog(null, "ERROR: no debe introducir sueldos negativos, abortando...");
            }else{

                if(!abortarOperacion){
                    this.miControlador.modificarFechaIngeniero(this.misIngenieros.get(fila).getIdIngeniero(), fecha);
                    this.miControlador.modificarSueldoIngeniero(this.misIngenieros.get(fila).getIdIngeniero(), sueldo);
                }
            }
        }
        jButton_cancelar_ingeniero.setVisible(false);
        jButton_guardar_ingeniero.setVisible(false);
        jComboBox_anadirCoche_Ingeniero.setVisible(false);
        jButton_anadirCoche_Ingeniero.setVisible(false);
       // jTextField_idIngeniero_ingeniero.setText("");
        jTextField_fechnac_ingeniero.setText("");
        jTextField_sueldo_ingeniero.setText("");
        
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
    }//GEN-LAST:event_jButton_guardar_ingenieroMouseClicked

    private void jButton_cancelar_ingenieroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cancelar_ingenieroMouseClicked
        // TODO add your handling code here:
        jButton_cancelar_ingeniero.setVisible(false);
        jButton_guardar_ingeniero.setVisible(false);
        jComboBox_anadirCoche_Ingeniero.setVisible(false);
        jButton_anadirCoche_Ingeniero.setVisible(false);
        //jTextField_idIngeniero_ingeniero.setText("");
        jTextField_fechnac_ingeniero.setText("");
        jTextField_sueldo_ingeniero.setText("");
    }//GEN-LAST:event_jButton_cancelar_ingenieroMouseClicked

    private void jButton_detalles_IngenieroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_detalles_IngenieroMouseClicked
        // TODO add your handling code here:
        int fila = jTable_ingeniero.getSelectedRow();
        String cadena ="";
        cadena += "Coches de los que está a cargo este ingeniero: \n";
        if(this.miControlador.getIngenieros().get(fila).getCoche_ingeniero() != null){
            for(Coche c : this.miControlador.getIngenieros().get(fila).getCoche_ingeniero()){
                cadena += c.getIdCoche() + "\n";
            }
        }else{
            cadena = "Este ingeniero no está a cargo de ningún coche";
        }
        JOptionPane.showMessageDialog(null, cadena);
    }//GEN-LAST:event_jButton_detalles_IngenieroMouseClicked

    private void jButton_borrar_ingenieroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_borrar_ingenieroMouseClicked
        // TODO add your handling code here:
        int fila = jTable_ingeniero.getSelectedRow();
        
        if(fila != -1){
            try {
                this.miControlador.eliminarIngeniero(this.misIngenieros.get(fila).getIdIngeniero());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR: Ha sucedido un error inesperado...");
            }
        }
        
       
        this.miControlador.obtenerDatosBD();
        this.traerDatosControladorVista();
        this.actualizarTablasVista();
    }//GEN-LAST:event_jButton_borrar_ingenieroMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_anadirCoche_Ingeniero;
    private javax.swing.JButton jButton_anadirCoche_piloto;
    private javax.swing.JButton jButton_anadirIngeniero_Coche;
    private javax.swing.JButton jButton_anadirPiloto_EquipoCarreras;
    public javax.swing.JButton jButton_aniadir_coche;
    public javax.swing.JButton jButton_aniadir_ingeniero;
    private javax.swing.JButton jButton_aniadir_piloto;
    public javax.swing.JButton jButton_borrar_coche;
    private javax.swing.JButton jButton_borrar_equipoCarreras;
    private javax.swing.JButton jButton_borrar_informe;
    public javax.swing.JButton jButton_borrar_ingeniero;
    private javax.swing.JButton jButton_borrar_piloto;
    private javax.swing.JButton jButton_cancelar_coche;
    private javax.swing.JButton jButton_cancelar_equipoCarreras;
    public javax.swing.JButton jButton_cancelar_informe;
    private javax.swing.JButton jButton_cancelar_ingeniero;
    private javax.swing.JButton jButton_cancelar_pilotos;
    private javax.swing.JButton jButton_detalles_Coche;
    private javax.swing.JButton jButton_detalles_EquipoCarreras;
    private javax.swing.JButton jButton_detalles_Ingeniero;
    private javax.swing.JButton jButton_detalles_Piloto;
    private javax.swing.JButton jButton_generarInforme_Piloto;
    private javax.swing.JButton jButton_guardarModificacion_equipoCarreras;
    private javax.swing.JButton jButton_guardarModificacion_pilotos;
    private javax.swing.JButton jButton_guardar_coche;
    private javax.swing.JButton jButton_guardar_equipoCarreras;
    public javax.swing.JButton jButton_guardar_informe;
    private javax.swing.JButton jButton_guardar_ingeniero;
    public javax.swing.JButton jButton_modificar_coche;
    private javax.swing.JButton jButton_modificar_equipoCarreras;
    private javax.swing.JButton jButton_modificar_informe;
    public javax.swing.JButton jButton_modificar_ingeniero;
    private javax.swing.JButton jButton_modificar_piloto;
    private javax.swing.JComboBox<String> jComboBox_anadirCoche_Ingeniero;
    private javax.swing.JComboBox<String> jComboBox_anadirIngeniero_Coche;
    private javax.swing.JComboBox<String> jComboBox_anadirPiloto_EquiposCarreras;
    private javax.swing.JComboBox<String> jComboBox_coche_piloto;
    private javax.swing.JLabel jLabel_descripcionInforme_piloto;
    private javax.swing.JLabel jLabel_descripcion_informe;
    private javax.swing.JLabel jLabel_edad_piloto;
    private javax.swing.JLabel jLabel_fechaNac_ingeniero;
    private javax.swing.JLabel jLabel_marca_coche;
    private javax.swing.JLabel jLabel_modelo_coche;
    private javax.swing.JLabel jLabel_nombre_equipoCarreras;
    private javax.swing.JLabel jLabel_nombre_piloto;
    private javax.swing.JLabel jLabel_sueldo_ingeniero;
    private javax.swing.JPanel jPanel_coche;
    private javax.swing.JPanel jPanel_equipoCarreras;
    private javax.swing.JPanel jPanel_informe;
    private javax.swing.JPanel jPanel_ingeniero;
    private javax.swing.JPanel jPanel_piloto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane;
    public javax.swing.JTable jTable_coche;
    private javax.swing.JTable jTable_equipoCarreras;
    private javax.swing.JTable jTable_informe;
    public javax.swing.JTable jTable_ingeniero;
    private javax.swing.JTable jTable_piloto;
    public javax.swing.JTextField jTextField_descripcionInforme_piloto;
    private javax.swing.JTextField jTextField_descripcion_informe;
    public javax.swing.JTextField jTextField_edad_piloto;
    private javax.swing.JTextField jTextField_fechnac_ingeniero;
    private javax.swing.JTextField jTextField_marca_coche;
    private javax.swing.JTextField jTextField_modelo_coche;
    public javax.swing.JTextField jTextField_nombre_equipoCarreras;
    public javax.swing.JTextField jTextField_nombre_piloto;
    private javax.swing.JTextField jTextField_sueldo_ingeniero;
    // End of variables declaration//GEN-END:variables
    /*private ArrayList<Ciudad> ciudades;
    private Ciudad ciudad_modificar;
    private DefaultTableModel table_model_ciudad;    
    private Boolean modif_ciudad;
    private int id_ciudad;
    
    private ArrayList<Biblioteca> bibliotecas;
    private Biblioteca biblioteca_modificar;
    private DefaultTableModel table_model_biblioteca;    
    private Boolean modif_biblioteca;
    private int id_biblioteca;
    
    private ArrayList<Libro> libros;
    private Libro libro_modificar;
    private DefaultTableModel table_model_libro;    
    private Boolean modif_libro;
    private int id_libro;
    
    private ArrayList<Persona> personas;
    private Persona persona_modificar;
    private DefaultTableModel table_model_persona;    
    private Boolean modif_persona;
    private int id_persona;
*/
}
