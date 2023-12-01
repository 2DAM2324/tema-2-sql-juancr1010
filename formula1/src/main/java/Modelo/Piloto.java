/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Genera;
import Modelo.EquipoCarreras;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Modelo.Coche;

/**
 *
 * @author johnm
 */
public class Piloto {
    private String idPiloto;
    private String nombre;
    private int edad;
    private Genera genera_en_piloto;
    private EquipoCarreras equipo_piloto;
    private Coche coche_piloto;

    public Piloto() {
        this.idPiloto = "";
        this.nombre = "";
        this.edad = 0;
        this.genera_en_piloto = null;
        this.equipo_piloto = null;
        this.coche_piloto = null;
    }

    public Piloto(String idPiloto, String nombre, int edad) {
        this.idPiloto = idPiloto;
        this.nombre = nombre;
        this.edad = edad;
        this.genera_en_piloto = null;
        this.equipo_piloto = null;
        this.coche_piloto = null;
    }
    
    

    public Piloto(String idPiloto, String nombre, int edad, Genera genera_en_piloto, Coche coche_piloto) {
        this.idPiloto = idPiloto;
        this.nombre = nombre;
        this.edad = edad;
        this.genera_en_piloto = genera_en_piloto;
        this.coche_piloto = coche_piloto;
    }
    
    

    public Piloto(String idPiloto, String nombre, int edad, Genera genera_en_piloto, EquipoCarreras equipo_piloto, Coche coche_piloto) {
        this.idPiloto = idPiloto;
        this.nombre = nombre;
        this.edad = edad;
        this.genera_en_piloto = genera_en_piloto;
        this.equipo_piloto = equipo_piloto;
        this.coche_piloto = coche_piloto;
    }
    
    public Piloto(Piloto p){
        this.idPiloto = p.idPiloto;
        this.nombre = p.nombre;
        this.edad = p.edad;
        this.genera_en_piloto = p.genera_en_piloto;
        this.equipo_piloto = p.equipo_piloto;
    }

    public String getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(String idPiloto) {
        this.idPiloto = idPiloto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Genera getGenera_en_piloto() {
        return genera_en_piloto;
    }

    public void setGenera_en_piloto(Genera genera_en_piloto) {
        this.genera_en_piloto = genera_en_piloto;
    }

    public EquipoCarreras getEquipo_piloto() {
        return equipo_piloto;
    }

    public void setEquipo_piloto(EquipoCarreras equipo_piloto) {
        this.equipo_piloto = equipo_piloto;
    }

    public Coche getCoche_piloto() {
        return coche_piloto;
    }

    public void setCoche_piloto(Coche coche_piloto) {
        this.coche_piloto = coche_piloto;
    }
    
    
    
    public void mostrarPiloto(){
        System.out.println("\nIDPiloto: " + this.idPiloto + " Nombre: " + this.nombre + " Edad: " + this.edad + "\nGENERA EN PILOTO");
        this.genera_en_piloto.mostrarGenera();
        System.out.println("\nEQUIPO EN PILOTO" + "\nIDEquipo: " + this.equipo_piloto.getIdEquipo() );
        System.out.println("\nCOCHE EN PILOTO: " + this.coche_piloto.getIdCoche());
        
        
    }
    
    public void generarInformePiloto(String idInforme, String descripcion){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Genera genera = new Genera();
        Informe informe = new Informe(idInforme, descripcion);
        genera.setPiloto_genera(this);
        genera.setFecha(fechaActual.format(formato));
        genera.setInforme_genera(informe);
        informe.setGenera_en_informe(genera);
        genera.setInforme_genera(informe);
        this.setGenera_en_piloto(genera);
    }
    
}
