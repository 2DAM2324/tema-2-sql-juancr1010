/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Controlador;

import Modelo.Coche;
import Modelo.EquipoCarreras;
import Modelo.Informe;
import Modelo.Ingeniero;
import Modelo.Piloto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author johnm
 */
public class ControladorTest {
    
    public ControladorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void obtenerDatosBD(){
        Controlador contr = new Controlador();
        contr.obtenerDatosBD();
    }
    
    @Test
    public void insertarEquipoCarreras(){
        Controlador contr = new Controlador();
        contr.insertarEquipoCarreras("TestInsertarEquipoCarreras");
    }
    
    @Test
    public void insertarPiloto(){
        Controlador contr = new Controlador();
        contr.insertarPiloto("TestPiloto", 23);
    }
    
    @Test
    public void insertarCoche(){
        Controlador contr = new Controlador();
        contr.insertarCoche("TestMarcaCoche", "TestModeloCoche");
    }
    
    @Test
    public void insertarIngeniero(){
        Controlador contr = new Controlador();
        contr.insertarIngeniero("2000-10-12", 25000);
    }
    
    @Test
    public void generarInforme(){
        Controlador contr = new Controlador();
        contr.insertarPiloto("TestGenerarInforme", 26);
        contr.obtenerDatosBD();
        String idPiloto = contr.getPilotos().get(contr.getPilotos().size()-1).getIdPiloto();
        
        contr.generarInformePiloto(idPiloto, "TestGenerarInforme");
    }
    
    @Test
    public void modificarEquipoCarreras(){
        Controlador contr = new Controlador();
        
        contr.insertarEquipoCarreras("TestNuevoEquipoCarrerasModificable");
        contr.obtenerDatosBD();
        String idEquipoCarreras = contr.getEquipos().get(contr.getEquipos().size()-1).getIdEquipo();
        
        
        contr.modificarNombreEquipoCarreras(idEquipoCarreras, "TestNuevoNombreEquipoCarreras");
    }
    
    @Test
    public void modificarPiloto(){
        Controlador contr = new Controlador();
        contr.insertarPiloto("TestModificarPiloto", 23);
        contr.obtenerDatosBD();
        String idPiloto = contr.getPilotos().get(contr.getPilotos().size()-1).getIdPiloto();
        
        
        contr.modificarNombrePiloto(idPiloto, "TestNuevoModificarPiloto");
        contr.modificarEdadPiloto(idPiloto, 38);
    }
    
    @Test
    public void modificarInforme(){
        Controlador contr = new Controlador();
        contr.insertarPiloto("TestModificarInforme", 23);
        contr.obtenerDatosBD();
        String idPiloto = contr.getPilotos().get(contr.getPilotos().size()-1).getIdPiloto();
        
        contr.generarInformePiloto(idPiloto, "TestModificarInforme");
        contr.obtenerDatosBD();
        contr.modificarDescripcionInforme("TestNuevoModificarInforme", contr.getInformes().get(contr.getInformes().size()-1).getIdInforme());
        
        contr.modificarEdadPiloto(idPiloto, 38);
    }
    
    @Test
    public void modificarCoche(){
        Controlador contr = new Controlador();
        contr.insertarCoche("TestModificarMarcaCoche", "TestModificarModeloCoche");
        contr.obtenerDatosBD();
        String idCoche = contr.getCoches().get(contr.getCoches().size()-1).getIdCoche();
        
        
        contr.modificarMarcaCoche(idCoche, "TestNuevoModificarMarcaCoche");
        contr.modificarModeloCoche(idCoche, "TestNuevoModificarModeloCoche");
    }
    
    @Test
    public void modificarIngeniero(){
        Controlador contr = new Controlador();
        contr.insertarIngeniero("2000-10-12", 25000);
        contr.obtenerDatosBD();
        String idIngeniero = contr.getIngenieros().get(contr.getIngenieros().size()-1).getIdIngeniero();
        
        
        contr.modificarFechaIngeniero(idIngeniero, "2000-03-15");
        contr.modificarSueldoIngeniero(idIngeniero, 20000);
    }
    
    @Test
    public void borrarEquipoCarreras(){
        Controlador contr = new Controlador();
        
        contr.insertarEquipoCarreras("TestEliminarEquipoCarreras");
        contr.obtenerDatosBD();
        String idEquipoCarreras = contr.getEquipos().get(contr.getEquipos().size()-1).getIdEquipo();
        
        
        try {
            contr.eliminarEquipoCarreras(idEquipoCarreras);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void borrarPiloto(){
        Controlador contr = new Controlador();
        
       contr.insertarPiloto("TestEliminarPiloto", 23);
        contr.obtenerDatosBD();
        String idPiloto = contr.getPilotos().get(contr.getPilotos().size()-1).getIdPiloto();
        
        
        contr.eliminarPiloto(idPiloto);
    }
    
    @Test
    public void borrarInforme(){
        Controlador contr = new Controlador();
        
       contr.insertarPiloto("TestEliminarInforme", 23);
       contr.obtenerDatosBD();
       
       String idPiloto = contr.getPilotos().get(contr.getPilotos().size()-1).getIdPiloto();
        
       contr.generarInformePiloto(idPiloto, "TestEliminarInforme"); 
       String idInforme = contr.getInformes().get(contr.getInformes().size()-1).getIdInforme();
       contr.eliminarInforme(idInforme);
    }
    
    @Test
    public void borrarCoche(){
        Controlador contr = new Controlador();
        
       contr.insertarCoche("TestEliminarMarcaCoche", "TestEliminarModeloCoche");
       contr.obtenerDatosBD();
       
       String idCoche = contr.getCoches().get(contr.getCoches().size()-1).getIdCoche();
       
       contr.eliminarCoche(idCoche);
    }
    
    @Test
    public void borrarIngeniero(){
        Controlador contr = new Controlador();
        
       contr.insertarIngeniero("2000-10-12", 25000);
       contr.obtenerDatosBD();
       
       String idIngeniero = contr.getIngenieros().get(contr.getIngenieros().size()-1).getIdIngeniero();
       
       contr.eliminarIngeniero(idIngeniero);
    }
    
    @Test
    public void relacionEquipoPiloto(){
       Controlador contr = new Controlador();
        
       contr.insertarEquipoCarreras("TestRelacionEquipoPiloto");
       contr.insertarPiloto("TestRelacionEquipoPiloto", 32);
       contr.obtenerDatosBD();
       String idPiloto = contr.getPilotos().get(contr.getPilotos().size()-1).getIdPiloto();
       String idEquipo = contr.getEquipos().get(contr.getEquipos().size()-1).getIdEquipo();
       
       contr.asignarPilotoAEquipoCarreras(idPiloto, idEquipo);
       
       contr.eliminarPiloto(idPiloto);
        try {
            contr.eliminarEquipoCarreras(idEquipo);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
    }
    
    @Test
    public void relacionCochePiloto(){
       Controlador contr = new Controlador();
        
       contr.insertarPiloto("TestRelacionEquipoPiloto", 32);
       contr.insertarCoche("TestMarcaRelacion", "TestModeloRelacion");
       contr.obtenerDatosBD();
       String idPiloto = contr.getPilotos().get(contr.getPilotos().size()-1).getIdPiloto();
       String idCoche = contr.getCoches().get(contr.getCoches().size()-1).getIdCoche();
       
       contr.asignarPilotoAEquipoCarreras(idPiloto, idCoche);
       
       contr.eliminarPiloto(idPiloto);
       contr.eliminarCoche(idCoche);
       
    }

    @Test
    public void relacionCocheIngeniero(){
       Controlador contr = new Controlador();
        
       contr.insertarCoche("TestMarca", "TestModelo");
       contr.insertarIngeniero("2000-02-14", 65);
       contr.obtenerDatosBD();
       String idCoche = contr.getCoches().get(contr.getCoches().size()-1).getIdCoche();
       String idIngeniero = contr.getIngenieros().get(contr.getIngenieros().size()-1).getIdIngeniero();
       
       contr.crearTaller(idCoche, idIngeniero);
       
       contr.eliminarCoche(idCoche);
       contr.eliminarIngeniero(idIngeniero);
    }
    
    
    
    
    

}
