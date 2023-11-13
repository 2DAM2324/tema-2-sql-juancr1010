/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.formula1;

import Vista.Ventana1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotSerializableException;
import org.xml.sax.SAXException;

/**
 *
 * @author johnm
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, NotSerializableException, SAXException{
        // TODO code application logic here
        
        Ventana1 miVentana = new Ventana1();
        miVentana.setVisible(true);
    }
    
}
