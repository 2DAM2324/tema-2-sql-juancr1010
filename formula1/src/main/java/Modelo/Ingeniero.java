/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Coche;
import java.util.ArrayList;

/**
 *
 * @author johnm
 */
public class Ingeniero {
    private String idIngeniero;
    private String fechaNacimiento;
    private double sueldo;
    private ArrayList<Coche> coche_ingeniero;

    public Ingeniero() {
        this.idIngeniero = "";
        this.fechaNacimiento = "";
        this.sueldo = 0.0;
        this.coche_ingeniero = new ArrayList<Coche>();
    }

    public Ingeniero(String idIngeniero, String fechaNacimiento, double sueldo, ArrayList<Coche> coche_ingeniero) {
        this.idIngeniero = idIngeniero;
        this.fechaNacimiento = fechaNacimiento;
        this.sueldo = sueldo;
        this.coche_ingeniero = coche_ingeniero;
    }

    public Ingeniero(String idIngeniero, String fechaNacimiento, double sueldo) {
        this.idIngeniero = idIngeniero;
        this.fechaNacimiento = fechaNacimiento;
        this.sueldo = sueldo;
        this.coche_ingeniero = new ArrayList<Coche>();  
    }
    
    
    
    public Ingeniero(Ingeniero i){
        this.idIngeniero = i.idIngeniero;
        this.fechaNacimiento = i.fechaNacimiento;
        this.sueldo = i.sueldo;
    }

    public String getIdIngeniero() {
        return idIngeniero;
    }

    public void setIdIngeniero(String idIngeniero) {
        this.idIngeniero = idIngeniero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public ArrayList<Coche> getCoche_ingeniero() {
        return coche_ingeniero;
    }

    public void setCoche_ingeniero(ArrayList<Coche> coche_ingeniero) {
        this.coche_ingeniero = coche_ingeniero;
    }
    
    public void mostrarIngeniero(){
        
        System.out.println("\nIDIngeniero: " + this.idIngeniero + " FechaNac: " + this.fechaNacimiento + " Sueldo: " + this.sueldo 
                + "\nCOCHES EN INGENIERO");
        
        for(int i = 0; i < this.coche_ingeniero.size(); i++){
            System.out.println(this.coche_ingeniero.get(i).getIdCoche());
        }
    
    }
    
    
}
