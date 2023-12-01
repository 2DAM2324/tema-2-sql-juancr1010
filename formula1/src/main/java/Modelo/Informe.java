/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author johnm
 */
public class Informe {
    private String idInforme;
    private String descripcion;
    private Genera genera_en_informe;
    
    public Informe() {
        idInforme = "";
        descripcion = "";
        genera_en_informe = null;
    }

    public Informe(String idInforme, String descripcion, Genera genera_en_informe) {
        this.idInforme = idInforme;
        this.descripcion = descripcion;
        this.genera_en_informe = genera_en_informe;
    }

    public Informe(String idInforme, String descripcion) {
        this.idInforme = idInforme;
        this.descripcion = descripcion;
    }
    
    

    public String getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(String idInforme) {
        this.idInforme = idInforme;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Genera getGenera_en_informe() {
        return genera_en_informe;
    }

    public void setGenera_en_informe(Genera genera_en_informe) {
        this.genera_en_informe = genera_en_informe;
    }
    
    public void mostrarInforme(){
        System.out.println("\nIDInforme: " + this.idInforme + " Descripción: " + this.descripcion + "\nGENERA EN INFORME");
        System.out.println("\nFECHAGENERA: " + this.genera_en_informe.getFecha());
        
    }
    
}
