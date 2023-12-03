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
import java.util.ArrayList;
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

}
