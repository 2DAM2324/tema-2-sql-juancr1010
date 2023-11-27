/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Modelo.EquipoCarreras;
import Modelo.Piloto;
import Modelo.Genera;
import Modelo.Informe;
import Modelo.Coche;
import Modelo.Ingeniero;
import java.sql.Statement;
/**
 *
 * @author johnm
 */
public class DatabaseConnection {
    private static Connection conn;
    private ArrayList<EquipoCarreras> equiposBD;
    private ArrayList<Piloto> pilotosBD;
    private ArrayList<Informe> informesBD;
    private ArrayList<Genera> generasBD;
    private ArrayList<Coche> cochesBD;
    private ArrayList<Ingeniero> ingenierosBD;
    static {
        try {
            // Cargar el driver de Oracle
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void connect() {
        // Cadena de conexión para Oracle
        String url = "jdbc:sqlite:D:\\Program Files\\SqliteStudio\\basededatos\\formula1.db";
        try{
             conn = DriverManager.getConnection(url);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }

    public DatabaseConnection() {
        conn = null;
    }
    
    public void insertarDatosEnTabla(String query){
        PreparedStatement sentencia = null;
        try{
            sentencia = this.conn.prepareStatement(query);
            
            sentencia.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(sentencia != null){
                    sentencia.close();
                }
            }
            catch(SQLException excptn){
                excptn.printStackTrace();
            }
        }
    }
    
    public void insertarEquipoCarreras(String nombre){
        String consulta = "INSERT INTO EquipoCarreras (nombre) VALUES (?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    sqlexcptn.printStackTrace();
                }
            }
        }
    }
    
    public void consultarTablaEquiposCarreras(){
        String cons = "SELECT * FROM equipoCarreras";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            consulta = conn.prepareStatement(cons);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                System.out.println("IDEquipo: " + resultado.getString(1));
                System.out.println("Nombre: " + resultado.getString(2));
            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    //TODO: Pasar los datos de la bd al controlador y posteriormente mostrar los datos en la vista
    
    public void relacionarEquiposConPilotos(){
        String cons = "SELECT * FROM Piloto";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            consulta = conn.prepareStatement(cons);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                for(Piloto p: this.pilotosBD){
                    if(p.getIdPiloto().equals(resultado.getString(1))){
                        for(EquipoCarreras e: this.equiposBD){
                            if(resultado.getString(4).equals(e.getIdEquipo())){
                                p.setEquipo_piloto(e);
                                e.getPilotos().add(p);
                                p.setEquipo_piloto(e);
                            }
                        }
                    }
                }
                
            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void relacionarCochesConPilotos(){
    
        String cons = "SELECT * FROM Piloto";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            consulta = conn.prepareStatement(cons);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                for(Piloto p: this.pilotosBD){
                    if(p.getIdPiloto().equals(resultado.getString(1))){
                        for(Coche c: this.cochesBD){
                            if(resultado.getString(5).equals(c.getIdCoche())){
                                p.setCoche_piloto(c);
                                c.setPiloto(p);
                                p.setCoche_piloto(c);
                                c.setPiloto(p);
                            }
                        }
                    }
                }
                
            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    
    }
    
    public void relacionarCochesConIngenieros(){
        String cons = "SELECT * FROM Taller";
        String idCoche = "", idIngeniero = "";
        
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            consulta = conn.prepareStatement(cons);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                idCoche = resultado.getString(2);
                idIngeniero = resultado.getString(3);
                
                for(Coche c: this.cochesBD){
                    if(idCoche.equals(c.getIdCoche())){
                        for(Ingeniero i: this.ingenierosBD){
                            if(idIngeniero.equals(i.getIdIngeniero())){
                                c.getIngenieros_coche().add(i);
                                i.getCoche_ingeniero().add(c);
                            }
                        }
                    }
                }
            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    
    }
    
    public void relacionarInformesConPilotos(){
        for(Genera g: this.generasBD){
            for(Piloto p: this.pilotosBD){
                if(g.getPiloto_genera().getIdPiloto().equals(p.getIdPiloto())){
                    for(Informe i: this.informesBD){
                        if(g.getInforme_genera().getIdInforme().equals(i.getIdInforme())){
                           p.setGenera_en_piloto(g);
                           i.setGenera_en_informe(g);
                        }
                    }
                }
            }
        }
    }
    
    
    public void traerIngenieros(){
        this.ingenierosBD = new ArrayList<Ingeniero>();
        
        String cons = "SELECT * FROM Ingeniero";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            consulta = conn.prepareStatement(cons);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                this.ingenierosBD.add(new Ingeniero(resultado.getString(1), resultado.getString(2), Double.parseDouble(resultado.getString(3))));
            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    public void traerCoches(){
        this.cochesBD = new ArrayList<Coche>();
        
        String cons = "SELECT * FROM Coche";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            consulta = conn.prepareStatement(cons);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                this.cochesBD.add(new Coche(resultado.getString(1), resultado.getString(3), resultado.getString(2)));
            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    //TODO: Debes cohesionar los informes con los pilotos, recuerda que genera guarda el informe y el piloto, no los IDs.
    public void traerGenera(){
        this.generasBD = new ArrayList<Genera>();
        
        String cons = "SELECT * FROM Genera";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            consulta = conn.prepareStatement(cons);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                Genera aux = new Genera();
                aux.setFecha(resultado.getString(1));
               
                for(Piloto p: this.pilotosBD){
                   if(resultado.getString(2).equals(p.getIdPiloto())){
                      aux.setPiloto_genera(p);
                   }
                }
               
                for(Informe i : this.informesBD){
                    if(resultado.getString(3).equals(i.getIdInforme())){
                        aux.setInforme_genera(i);
                    }
                }
                
                this.generasBD.add(aux);
               
            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    public void traerInformes(){
        this.informesBD = new ArrayList<Informe>();
        
        String cons = "SELECT * FROM Informe";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            consulta = conn.prepareStatement(cons);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                this.informesBD.add(new Informe(resultado.getString(1), resultado.getString(2)));
            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void traerPilotos(){
        this.pilotosBD = new ArrayList<Piloto>();
        
        String cons = "SELECT * FROM Piloto";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            consulta = conn.prepareStatement(cons);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                this.pilotosBD.add(new Piloto(resultado.getString(1), resultado.getString(2), Integer.parseInt(resultado.getString(3))));
            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    public void traerEquiposCarreras(){
        this.equiposBD = new ArrayList<EquipoCarreras>();
        
        String cons = "SELECT * FROM EquipoCarreras";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        
        try{
            consulta = conn.prepareStatement(cons);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                this.equiposBD.add(new EquipoCarreras(resultado.getString(1), resultado.getString(2)));
            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
        
    public static void cerrarConexion(){
        try{
            if(conn != null){
                conn.close();
            }
       }
       catch(SQLException e){
            e.printStackTrace();
       }
    }

    public ArrayList<EquipoCarreras> getEquiposBD() {
        return equiposBD;
    }

    public void setEquiposBD(ArrayList<EquipoCarreras> equiposBD) {
        this.equiposBD = equiposBD;
    }

    public ArrayList<Piloto> getPilotosBD() {
        return pilotosBD;
    }

    public void setPilotosBD(ArrayList<Piloto> pilotosBD) {
        this.pilotosBD = pilotosBD;
    }

    public ArrayList<Informe> getInformesBD() {
        return informesBD;
    }

    public void setInformesBD(ArrayList<Informe> informesBD) {
        this.informesBD = informesBD;
    }

    public ArrayList<Genera> getGenerasBD() {
        return generasBD;
    }

    public void setGenerasBD(ArrayList<Genera> generasBD) {
        this.generasBD = generasBD;
    }

    public ArrayList<Coche> getCochesBD() {
        return cochesBD;
    }

    public void setCochesBD(ArrayList<Coche> cochesBD) {
        this.cochesBD = cochesBD;
    }

    public ArrayList<Ingeniero> getIngenierosBD() {
        return ingenierosBD;
    }

    public void setIngenierosBD(ArrayList<Ingeniero> ingenierosBD) {
        this.ingenierosBD = ingenierosBD;
    }
        
    
    
    
    
}
