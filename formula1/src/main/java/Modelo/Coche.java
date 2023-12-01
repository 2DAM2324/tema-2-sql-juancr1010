/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Piloto;
import Modelo.Ingeniero;
import java.util.ArrayList;

/**
 *
 * @author johnm
 */
public class Coche {
    private String idCoche;
    private String modelo;
    private String marca;
    private Piloto piloto;
    private ArrayList<Ingeniero> ingenieros_coche;

    public Coche() {
        idCoche = "";
        modelo = "";
        marca = "";
    }

    public Coche(String idCoche, String modelo, String marca) {
        this.idCoche = idCoche;
        this.modelo = modelo;
        this.marca = marca;
        this.ingenieros_coche = new ArrayList<Ingeniero>();
    }
    
    public Coche(Coche c){
        this.idCoche = c.idCoche;
        this.modelo = c.modelo;
        this.marca = c.marca;
    }

    public String getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(String idCoche) {
        this.idCoche = idCoche;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public ArrayList<Ingeniero> getIngenieros_coche() {
        return ingenieros_coche;
    }

    public void setIngenieros_coche(ArrayList<Ingeniero> ingenieros_coche) {
        this.ingenieros_coche = ingenieros_coche;
    }
    
    public void mostrarCoche(){
        System.out.println("\nIDCoche: " + this.idCoche + " Marca: " + this.marca + " Modelo: " + this.modelo + "\nINGENIEROS EN COCHE");
        for(int i = 0; i < this.ingenieros_coche.size(); i++){
            this.ingenieros_coche.get(i).mostrarIngeniero();    
        }
    }
    
    
    
}
