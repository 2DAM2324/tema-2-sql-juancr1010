/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Result;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

import java.util.ArrayList;
import Modelo.Piloto;


/**
 *
 * @author johnm
 */
public class EquipoCarreras {
    private String idEquipo;
    private String nombre;
    private ArrayList<Piloto> pilotos;

    public EquipoCarreras() {
        idEquipo = "";
        nombre = "";
        pilotos = new ArrayList<Piloto>();
    }

    public EquipoCarreras(String idEquipo, String nombre) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.pilotos = new ArrayList<Piloto>();
    }
    
    public EquipoCarreras(String idEquipo, String nombre, ArrayList<Piloto> pilotos) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.pilotos = pilotos;
    }
    
    public EquipoCarreras(EquipoCarreras ec){
        this.idEquipo = ec.idEquipo;
        this.nombre = ec.nombre;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Piloto> getPilotos() {
        return pilotos;
    }

    public void setPilotos(ArrayList<Piloto> pilotos) {
        this.pilotos = pilotos;
    }
    
    public void mostrarEquipo(){
        System.out.println("\nIDEquipo: " + this.idEquipo + " Nombre: " + this.nombre + "\nPILOTOS EN EQUIPO");
        for(int i  = 0; i < this.pilotos.size(); i++){
            this.pilotos.get(i).mostrarPiloto();
        }
    }
    
    
    
}
