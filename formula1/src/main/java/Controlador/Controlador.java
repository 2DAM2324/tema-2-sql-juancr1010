/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Coche;
import Modelo.EquipoCarreras;
import Modelo.Genera;
import Modelo.Informe;
import Modelo.Ingeniero;
import Modelo.Piloto;
import Modelo.DatabaseConnection;
import Vista.Ventana1;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;


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

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

/**
 *
 * @author johnm
 */
public class Controlador {
   private ArrayList<EquipoCarreras> equipos;
   private ArrayList<Piloto> pilotos;
   private ArrayList<Informe> informes;
   private ArrayList<Coche> coches;
   private ArrayList<Ingeniero> ingenieros;
   private DatabaseConnection db;

    public Controlador() {
        this.equipos = new ArrayList<EquipoCarreras>();
        this.pilotos = new ArrayList<Piloto>();
        this.informes = new ArrayList<Informe>();
        this.coches = new ArrayList<Coche>();
        this.ingenieros = new ArrayList<Ingeniero>();
        this.db = new DatabaseConnection();
        this.db.connect();
    }

   
   
    public Controlador(ArrayList<EquipoCarreras> equipos, ArrayList<Piloto> pilotos, ArrayList<Informe> informes, ArrayList<Coche> coches, ArrayList<Ingeniero> ingenieros) {
        this.equipos = equipos;
        this.pilotos = pilotos;
        this.informes = informes;
        this.coches = coches;
        this.ingenieros = ingenieros;
    }

    public ArrayList<EquipoCarreras> getEquipos() {
        return this.equipos;
    }

    public void setEquipos(ArrayList<EquipoCarreras> equipos) {
        this.equipos = equipos;
    }

    public ArrayList<Piloto> getPilotos() {
        return this.pilotos;
    }

    public void setPilotos(ArrayList<Piloto> pilotos) {
        this.pilotos = pilotos;
    }

    public ArrayList<Informe> getInformes() {
        return informes;
    }

    public void setInformes(ArrayList<Informe> informes) {
        this.informes = informes;
    }

    public ArrayList<Coche> getCoches() {
        return coches;
    }

    public void setCoches(ArrayList<Coche> coches) {
        this.coches = coches;
    }

    public ArrayList<Ingeniero> getIngenieros() {
        return ingenieros;
    }

    public void setIngenieros(ArrayList<Ingeniero> ingenieros) {
        this.ingenieros = ingenieros;
    }
    
    public void anadirPiloto(Piloto unPiloto){
        this.pilotos.add(unPiloto);
    }
    
    public void anadirEquipoCarreras(EquipoCarreras unEquipo){
        this.equipos.add(unEquipo);
    }
    
    public EquipoCarreras getUnEquipo(int pos){
        return this.equipos.get(pos);
    }
    
    
    public void mostrarEquiposCarreras(){
        for(int i = 0; i < this.equipos.size(); i++){
            System.out.println("Nombre: " + this.equipos.get(i).getNombre() + "\nID Equipo: " + this.equipos.get(i).getIdEquipo());
        }     
    }
    
    public void mostrarPilotos(){
        for(Piloto p: this.pilotos){
            System.out.println("Nombre: " + p.getNombre() + "\nID Piloto: " + p.getIdPiloto() + "\nEdad: " + p.getEdad());
        }
    }
   
    public void modificarPiloto(String id, String nombre, int edad, int pos){
        this.pilotos.get(pos).setIdPiloto(id);
        this.pilotos.get(pos).setNombre(nombre);
        this.pilotos.get(pos).setEdad(edad);
    }
    
    public void borrarPiloto(int pos){
        this.pilotos.remove(pos);
    }
    
    public void borrarInforme(int pos){
        this.informes.remove(pos);
    }
    
    public void borrarCoche(int pos){
        this.coches.remove(pos);
    }
    
    public void borrarIngeniero(int pos){
        this.ingenieros.remove(pos);
    }
    
    public void mostrarInformes(){
        for(Informe i : this.informes){
            System.out.println("ID: " + i.getIdInforme());
            System.out.println("Descripcion: " + i.getDescripcion());
        }
    }
    
    public void modificarEquipoCarreras(String id, String nombre, int pos){
        this.equipos.get(pos).setIdEquipo(id);
        this.equipos.get(pos).setNombre(nombre);
    }
    
    public void borrarEquipoCarreras(int pos){
        this.equipos.remove(pos);
    }
    
    public boolean comprobarSiEquipoExiste(EquipoCarreras unEquipo){
        boolean existe = false;
        for(int i = 0; i < this.equipos.size(); i++){
            if(unEquipo.getIdEquipo().equals(this.equipos.get(i).getIdEquipo())){
                existe = true;
            }
        }
        return existe;
    }
    
    public boolean comprobarSiPilotoExiste(Piloto unPiloto){
        boolean existe = false;
        for(int i = 0; i < this.pilotos.size(); i++){
            if(unPiloto.getIdPiloto().equals( this.pilotos.get(i).getIdPiloto())){
                existe = true;
            }
        }
        return existe;
    }
    
    public boolean comprobarSiInformeExiste(Informe unInforme){
        boolean existe = false;
        for(int i = 0; i < this.informes.size(); i++){
            if(unInforme.getIdInforme().equals(this.informes.get(i).getIdInforme())){
                existe = true;
            }
        }
        return existe;
    }
    
    public boolean comprobarSiCocheExiste(Coche unCoche){
        boolean existe = false;
        for(int i = 0; i < this.coches.size(); i++){
            if(unCoche.getIdCoche().equals(this.coches.get(i).getIdCoche())){
                existe = true;
            }
        }
        //programacion funcional java 8 (stream)
        //this.coches.stream().anyMatch(c -> unCoche.getIdCoche()== c.getIdCoche() );
        return existe;
    }
    
    public boolean comprobarSiIngenieroExiste(Ingeniero unIngeniero){
        boolean existe = false;
        for(int i = 0; i < this.ingenieros.size(); i++){
            if(unIngeniero.getIdIngeniero().equals(this.ingenieros.get(i).getIdIngeniero())){
                existe = true;
            }
        }
        return existe;
    }
    
    public void insertarEquipoCarreras(String nombre){
        this.db.insertarEquipoCarreras(nombre);
    }
    
    public void insertarPiloto(String nombre, int edad){
        this.db.insertarPiloto(nombre, edad);
    }
    
    public void modificarNombreEquipoCarreras(String idEquipo, String nombre){
        this.db.modificarNombreEquipoCarreras(idEquipo, nombre);
    }
    
    public void modificarNombrePiloto(String idPiloto, String nombre){
        this.db.modificarNombrePiloto(idPiloto, nombre);
    }
    
    public void modificarEdadPiloto(String idPiloto, int edad){
        this.db.modificarEdadPiloto(idPiloto, edad);
    }
    
    public void asignarPilotoAEquipoCarreras(String idPiloto, String idEquipo){
        this.db.asignarEquipoAPiloto(idPiloto, idEquipo);
    }
    
    public void eliminarEquipoCarreras(String idEquipoCarreras) throws SQLException{
        this.db.eliminarEquipoCarreras(idEquipoCarreras);
    }
    
    
    
    public void obtenerDatosBD(){
        this.db.traerInformes();
        this.db.traerCoches();
        this.db.traerIngenieros();
        this.db.traerPilotos();
        this.db.traerGenera();
        this.db.traerEquiposCarreras();
        this.db.relacionarCochesConIngenieros();
        this.db.relacionarCochesConPilotos();
        this.db.relacionarInformesConPilotos();
        this.db.relacionarEquiposConPilotos();
        this.informes = this.db.getInformesBD();
        this.ingenieros = this.db.getIngenierosBD();
        this.coches = this.db.getCochesBD();
        this.pilotos = this.db.getPilotosBD();
        this.equipos = this.db.getEquiposBD();
        
        mostrarEquiposCarreras();
        mostrarPilotos();
        mostrarInformes();
    }
    
    
    public void mostrarTablaEquiposCarrerasTerminal(){
        
        this.db.consultarTablaEquiposCarreras();
        
    }
    
    
    public void leerXML(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            documento = builder.parse(new File("formula1XML.xml"));

            // Recorrer el arbol
            
            Element root = documento.getDocumentElement();
            NodeList equiposCarreras = root.getElementsByTagName("equipo");
            
            
            ArrayList<EquipoCarreras> equipoXML = new ArrayList<EquipoCarreras>();
            System.out.println(equiposCarreras.getLength());

            for(int i = 0; i < equiposCarreras.getLength(); i++){
                Node equipoCarreras = equiposCarreras.item(i);
                Element elemento = (Element) equipoCarreras;
                
                //Leo los atributos básicos de cada equipo
                String idEquipo = elemento.getElementsByTagName("idEquipo").item(0).getChildNodes().item(0).getNodeValue();
                String nombre = elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue();
                EquipoCarreras nuevoEquipo = new EquipoCarreras(idEquipo, nombre);
                nuevoEquipo.mostrarEquipo();
                //System.out.println(elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue());
                //System.out.println(elemento.getElementsByTagName("precio").item(0).getChildNodes().item(0).getNodeValue());
                
                //Creo el arrayList de pilotos que voy a leer de cada equipo en el XML
                ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
                NodeList pilotosNodeList = elemento.getElementsByTagName("piloto");
                
                for(int j = 0; j < pilotosNodeList.getLength(); j++){
                    Node pilotoNode = pilotosNodeList.item(j);
                    if(pilotoNode.getNodeType() == Node.ELEMENT_NODE){
                        //Leo los atributos básicos de cada piloto.
                        Element pilotoElement = (Element) pilotoNode;
                        String idPiloto = "";
                        String nombrePiloto = "";
                        int edad = 0;
                        if(pilotoElement != null && pilotoElement.hasChildNodes()){
                           idPiloto = pilotoElement.getElementsByTagName("idPiloto").item(0).getChildNodes().item(0).getNodeValue();
                           nombrePiloto = pilotoElement.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue();
                           edad = Integer.parseInt(pilotoElement.getElementsByTagName("edad").item(0).getChildNodes().item(0).getNodeValue());
                        }
                        //Leo el coche de cada piloto.
                        Element cocheElement = (Element) pilotoElement.getElementsByTagName("coche").item(0);
                        String idCoche = "";
                        String marcaCoche = "";
                        String modeloCoche = "";
                        Coche coche = null;
                        if(cocheElement != null && cocheElement.hasChildNodes()){
                            idCoche = cocheElement.getElementsByTagName("idCoche").item(0).getChildNodes().item(0).getNodeValue();
                            marcaCoche = cocheElement.getElementsByTagName("marca").item(0).getChildNodes().item(0).getNodeValue();
                            modeloCoche = cocheElement.getElementsByTagName("modelo").item(0).getChildNodes().item(0).getNodeValue();
                            coche = new Coche(idCoche, marcaCoche, modeloCoche);
                        
                        //De cada coche leo sus ingenieros e introduzco los ingenieros en el coche y el coche en los ingenieros.
                            NodeList ingenierosNodeList = cocheElement.getElementsByTagName("ingeniero");

                            if(ingenierosNodeList != null){
                                for(int k = 0; k < ingenierosNodeList.getLength(); k++ ){
                                    Node ingenieroNode = ingenierosNodeList.item(k);
                                    Element ingenieroElement = (Element) ingenieroNode;
                                    String idIngeniero = ingenieroElement.getElementsByTagName("idIngeniero").item(0).getChildNodes().item(0).getNodeValue();
                                    String fechaNacimientoIngeniero = ingenieroElement.getElementsByTagName("fecha_de_nacimiento").item(0).getChildNodes().item(0).getNodeValue();
                                    double sueldoIngeniero = Double.parseDouble(ingenieroElement.getElementsByTagName("sueldo").item(0).getChildNodes().item(0).getNodeValue());
                                    Ingeniero ingeniero = new Ingeniero(idIngeniero, fechaNacimientoIngeniero, sueldoIngeniero);
                                    ingeniero.getCoche_ingeniero().add(coche);
                                    coche.getIngenieros_coche().add(ingeniero);
                                    if(comprobarSiIngenieroExiste(ingeniero) == false){
                                        this.ingenieros.add(ingeniero);
                                    }
                                }
                            }
                        }
                        if(coche != null && comprobarSiCocheExiste(coche) == false){
                            this.coches.add(coche);
                        }
                        
                        //Leo el genera que va a estar en cada piloto.
                        Element generaElement = (Element) pilotoElement.getElementsByTagName("genera").item(0);
                        String fecha = "";
                        String idInforme = "";
                        String descripcionInforme = "";
                        Informe informe = null;
                        Genera genera = null;
                        if(generaElement != null && generaElement.hasChildNodes()){
                            fecha = generaElement.getElementsByTagName("fecha").item(0).getChildNodes().item(0).getNodeValue();
                        
                            //Leo el informe que va a estar dentro del genera del piloto ya que los genera él.
                            Element informeElement = (Element) generaElement.getElementsByTagName("informe").item(0);
                            idInforme = informeElement.getElementsByTagName("idInforme").item(0).getChildNodes().item(0).getNodeValue();
                            descripcionInforme = informeElement.getElementsByTagName("descripcion").item(0).getChildNodes().item(0).getNodeValue();
                            informe = new Informe(idInforme, descripcionInforme);
                            genera = new Genera(fecha, informe);
                        }
                        
                        Piloto piloto = new Piloto(idPiloto, nombrePiloto, edad, genera, coche);
                        
                        if(coche != null){
                            coche.setPiloto(piloto);
                        }
                        //Asigno los valores que quedan pendientes por establecer que básicamente son los objetos.
                        
                        if(genera != null){
                            genera.setPiloto_genera(piloto);
                            informe.setGenera_en_informe(genera);
                            genera.setInforme_genera(informe);
                            informe.setGenera_en_informe(genera);
                            piloto.setGenera_en_piloto(genera);
                        }
                        
                        if(informe != null && comprobarSiInformeExiste(informe) == false ){
                            this.informes.add(informe);
                        }
                        
                        
                        
                        //TODO: Terminar de hacer cada equipo, hacer módulo para comprobar que no se duplican los datos e introducir los
                        //datos en los arrayLists del controlador
                        piloto.setEquipo_piloto(nuevoEquipo);
                        nuevoEquipo.getPilotos().add(piloto);
                        
                        if(piloto != null && comprobarSiPilotoExiste(piloto) == false){
                            this.pilotos.add(piloto);
                        }
                    }
                }
                if(comprobarSiEquipoExiste(nuevoEquipo) == false){
                    equipos.add(nuevoEquipo);
                }
            }
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        catch(SAXException saxe){
            saxe.printStackTrace();
        }
    }
    
    
    public void escribirXML(ArrayList<EquipoCarreras> equipos){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try{
            // Crear estructura del documento
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null, "xml", null);

            // Crear elemento raiz
            Element raiz = documento.createElement("equiposCarreras");
            documento.getDocumentElement().appendChild(raiz);
            
            // Crear nodos para cada objeto de cada entidad y colgarlos del arbol
            Element nodoEquipo = null;
            Element nodoDatos = null;
            Element nodoRaizPilotos = null;
            Element nodoPiloto = null;
            Element nodoGenera = null;
            Element nodoCoche = null;
            Element nodoInforme = null;
            Element nodoRaizIngenieros = null;
            Element nodoIngenieros = null;
            Text texto = null;

            // Escribo los datos
            for (EquipoCarreras equipo : equipos){
                nodoEquipo = documento.createElement("equipo");
                raiz.appendChild(nodoEquipo);
                
                nodoDatos = documento.createElement("idEquipo");
                nodoEquipo.appendChild(nodoDatos);
                texto = documento.createTextNode(equipo.getIdEquipo());
                nodoDatos.appendChild(texto);
                
                nodoDatos = documento.createElement("nombre");
                nodoEquipo.appendChild(nodoDatos);
                texto = documento.createTextNode(equipo.getNombre());
                nodoDatos.appendChild(texto);
                
                
                nodoRaizPilotos = documento.createElement("pilotos");
                nodoEquipo.appendChild(nodoRaizPilotos);
                
                for(Piloto piloto : equipo.getPilotos()){
                    
                    nodoPiloto = documento.createElement("piloto");
                    nodoRaizPilotos.appendChild(nodoPiloto);
                    
                    nodoDatos = documento.createElement("idPiloto");
                    nodoPiloto.appendChild(nodoDatos);
                    texto = documento.createTextNode(piloto.getIdPiloto());
                    nodoDatos.appendChild(texto);
                    
                    nodoDatos = documento.createElement("nombre");
                    nodoPiloto.appendChild(nodoDatos);
                    texto = documento.createTextNode(piloto.getNombre());
                    nodoDatos.appendChild(texto);
                    
                    nodoDatos = documento.createElement("edad");
                    nodoPiloto.appendChild(nodoDatos);
                    texto = documento.createTextNode(Integer.toString(piloto.getEdad()));
                    nodoDatos.appendChild(texto);
                    
                    nodoGenera = documento.createElement("genera");
                    nodoPiloto.appendChild(nodoGenera);
                    if(piloto.getGenera_en_piloto() != null){
                        nodoDatos = documento.createElement("fecha");
                        nodoGenera.appendChild(nodoDatos);
                        texto = documento.createTextNode(piloto.getGenera_en_piloto().getFecha());
                        nodoDatos.appendChild(texto);

                        nodoInforme = documento.createElement("informe");
                        nodoGenera.appendChild(nodoInforme);
                        nodoDatos = documento.createElement("idInforme");
                        nodoInforme.appendChild(nodoDatos);
                        texto = documento.createTextNode(piloto.getGenera_en_piloto().getInforme_genera().getIdInforme());
                        nodoDatos.appendChild(texto);
                        nodoDatos = documento.createElement("descripcion");
                        nodoInforme.appendChild(nodoDatos);
                        texto = documento.createTextNode(piloto.getGenera_en_piloto().getInforme_genera().getDescripcion());
                        nodoDatos.appendChild(texto);
                    }
                    nodoCoche = documento.createElement("coche");
                    nodoPiloto.appendChild(nodoCoche);
                    
                    if(piloto.getCoche_piloto() != null){
                        nodoDatos = documento.createElement("idCoche");
                        nodoCoche.appendChild(nodoDatos);
                        texto = documento.createTextNode(piloto.getCoche_piloto().getIdCoche());
                        nodoDatos.appendChild(texto);
                        nodoDatos = documento.createElement("modelo");
                        nodoCoche.appendChild(nodoDatos);
                        texto = documento.createTextNode(piloto.getCoche_piloto().getModelo());
                        nodoDatos.appendChild(texto);
                        nodoDatos = documento.createElement("marca");
                        nodoCoche.appendChild(nodoDatos);
                        texto = documento.createTextNode(piloto.getCoche_piloto().getMarca());
                        nodoDatos.appendChild(texto);

                        nodoRaizIngenieros = documento.createElement("ingenieros");
                        nodoCoche.appendChild(nodoRaizIngenieros);

                        for(Ingeniero ing: piloto.getCoche_piloto().getIngenieros_coche()){
                            nodoIngenieros = documento.createElement("ingeniero");
                            nodoRaizIngenieros.appendChild(nodoIngenieros);


                            nodoDatos = documento.createElement("idIngeniero");
                            nodoIngenieros.appendChild(nodoDatos);
                            texto = documento.createTextNode(ing.getIdIngeniero());
                            nodoDatos.appendChild(texto);
                            nodoDatos = documento.createElement("fecha_de_nacimiento");
                            nodoIngenieros.appendChild(nodoDatos);
                            texto = documento.createTextNode(ing.getFechaNacimiento());
                            nodoDatos.appendChild(texto);
                            nodoDatos = documento.createElement("sueldo");
                            nodoIngenieros.appendChild(nodoDatos);
                            texto = documento.createTextNode(Double.toString(ing.getSueldo()));
                            nodoDatos.appendChild(texto);
                        }
                    }        
                    
                    
                    
                }
               /* nodoDatos = documento.createElement("precio");
                nodoProducto.appendChild(nodoDatos);
                texto = documento.createTextNode(Double.toString(prod.getPrecio()));
                nodoDatos.appendChild(texto);*/
            }

            // Transformar el arbol en un fichero y escribirlo
            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File("formula1XML.xml"));
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultado);
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(TransformerConfigurationException tce){
            tce.printStackTrace();
        }
        catch(TransformerException te){
            te.printStackTrace();
        }
    }
/*
    public void leerXMLPiloto(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            documento = builder.parse(new File("archivoXMLPiloto.xml"));

            // Recorrer el arbol
            NodeList pilotos = documento.getElementsByTagName("pilotos");
            
            ArrayList<Piloto> pilotoXML = new ArrayList<Piloto>();

            for(int i = 0; i < pilotos.getLength(); i++){
                Node piloto = pilotos.item(i);
                Element elemento = (Element) piloto;
                
                String idPiloto = elemento.getAttribute("idEquipoCarreras");
                String nombre = elemento.getAttribute("nombre");
                int edad = Integer.parseInt(elemento.getAttribute("edad"));
                //System.out.println(elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue());
                //System.out.println(elemento.getElementsByTagName("precio").item(0).getChildNodes().item(0).getNodeValue());
                
/*                ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
                NodeList pilotosNodeList = elemento.getElementsByTagName("piloto");
                
                for(int j = 0; j < pilotosNodeList.getLength(); j++){
                    Node pilotoNode = pilotosNodeList.item(j);
                    if(pilotoNode.getNodeType() == Node.ELEMENT_NODE){
                        Element pilotoElement = (Element) pilotoNode;
                        String idPiloto = pilotoElement.getAttribute("idPiloto");
                        String nombrePiloto = pilotoElement.getAttribute("nombre");
                        int edad = Integer.parseInt(pilotoElement.getAttribute("edad"));
                        
                    }
                }

            }
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        catch(SAXException saxe){
            saxe.printStackTrace();
        }
    }
 */   
    //TODO Debo hacer los distintos métodos para los eventos de la vista
}
