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

/**
 *
 * @author johnm
 */
public class DatabaseConnection {
    Connection conn = null;
    static {
        try {
            // Cargar el driver de Oracle
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        // Cadena de conexión para Oracle
        String url = "jdbc:sqlite:D:\\Program Files\\SqliteStudio\\basededatos\\formula1.db";
        try{
             this.conn = DriverManager.getConnection(url);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }

    public DatabaseConnection() {
        
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
        
    public void cerrarConexion(){
        try{
            if(conn != null){
                conn.close();
            }
       }
       catch(SQLException e){
            e.printStackTrace();
       }
    }
        
    
    
    
    
}
