/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Piloto;
import Modelo.Informe;

/**
 *
 * @author johnm
 */
public class Genera {
    private String fecha;
    private Piloto piloto_genera;
    private Informe informe_genera;

    public Genera() {
        this.fecha = "";
        this.piloto_genera = null;
        this.informe_genera = null;
        
    }

    public Genera(String idInforme, Informe informe_genera) {
        this.fecha = idInforme;
        this.informe_genera = informe_genera;
    }
    
    
    
    public Genera(String idInforme, Piloto piloto_genera, Informe informe_genera) {
        this.fecha = idInforme;
        this.piloto_genera = piloto_genera;
        this.informe_genera = informe_genera;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Piloto getPiloto_genera() {
        return piloto_genera;
    }

    public void setPiloto_genera(Piloto piloto_genera) {
        this.piloto_genera = piloto_genera;
    }

    public Informe getInforme_genera() {
        return informe_genera;
    }

    public void setInforme_genera(Informe informe_genera) {
        this.informe_genera = informe_genera;
    }
    
    public void mostrarGenera(){
        System.out.println("\nFecha Genera: " + this.fecha + "\nPILOTO EN GENERA");
        System.out.println("\nIDPiloto: " + this.piloto_genera.getIdPiloto());
        System.out.println("\nINFORME EN GENERA");
        this.informe_genera.mostrarInforme();
                
    }
    
}
