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
import java.sql.Date;
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

    public void insertarCoche(String marca, String modelo){
        String consulta = "INSERT INTO Coche (marca, modelo) VALUES (?, ?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, marca);
            sentencia.setString(2, modelo);
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
    

    
    
    public void insertarPiloto(String nombre, int edad){
        String consulta = "INSERT INTO Piloto (nombre, edad) VALUES (?, ?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.setInt(2, edad);
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
    
    public void insertarIngeniero(String fecha, double sueldo){
        //Date fechaFinal = fec
        String consulta = "INSERT INTO Ingeniero (FechaNacimiento, Sueldo) VALUES (?, ?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, fecha);
            sentencia.setDouble(2, sueldo);
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
    
    public void modificarNombreEquipoCarreras(String idEquipo, String nombre){
        String consulta = "UPDATE EquipoCarreras SET nombre = ? " + "WHERE idEquipoCarreras = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.setString(2, idEquipo);
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
    
    public void modificarFechaIngeniero(String idIngeniero, String fecha){
        String consulta = "UPDATE Ingeniero SET FechaNacimiento = ? " + "WHERE idIngeniero = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, fecha);
            sentencia.setString(2, idIngeniero);
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
    
    public void modificarSueldoIngeniero(String idIngeniero, double sueldo){
        String consulta = "UPDATE Ingeniero SET Sueldo = ? " + "WHERE idIngeniero = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setDouble(1, sueldo);
            sentencia.setString(2, idIngeniero);
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
    
    
    public void modificarMarcaCoche(String idCoche, String marca){
        String consulta = "UPDATE Coche SET marca = ? " + "WHERE idCoche = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, marca);
            sentencia.setString(2, idCoche);
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

    public void modificarModeloCoche(String idCoche, String modelo){
        String consulta = "UPDATE Coche SET modelo = ? " + "WHERE idCoche = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, modelo);
            sentencia.setString(2, idCoche);
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
    
    public void modificarDescripcionInforme(String idInforme, String descripcion){
        String consulta = "UPDATE Informe SET descripcion = ? " + "WHERE idInforme = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, descripcion);
            sentencia.setString(2, idInforme);
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
    
    public void modificarNombrePiloto(String idPiloto, String nombre){
        String consulta = "UPDATE Piloto SET nombre = ? " + "WHERE idPiloto = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.setString(2, idPiloto);
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
    
    public void modificarEdadPiloto(String idPiloto, int edad){
        String consulta = "UPDATE Piloto SET edad = ? " + "WHERE idPiloto = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setInt(1, edad);
            sentencia.setString(2, idPiloto);
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
    
    public void asignarEquipoAPiloto(String idPiloto, String idEquipo){
        String consulta = "UPDATE Piloto SET idEquipoCarreras = ? " + "WHERE idPiloto = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, idEquipo);
            sentencia.setString(2, idPiloto);
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
    
    public void crearTaller(String idCoche, String idIngeniero){
        String consulta = "INSERT INTO Taller (idCoche, idIngeniero) VALUES (?, ?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, idCoche);
            sentencia.setString(2, idIngeniero);
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
    
    public void asignarCocheAPiloto(String idCoche, String idPiloto){
        PreparedStatement sentencia = null;
        
        String consultaPiloto = "UPDATE Piloto SET idCoche = ? " + "WHERE idPiloto = ?";
        
        try{
            sentencia = conn.prepareStatement(consultaPiloto);
            sentencia.setString(1, idCoche);
            sentencia.setString(2, idPiloto);
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
    
    public void eliminarEquipoCarreras(String idEquipoCarreras) throws SQLException{
        String cons = "UPDATE Piloto SET idEquipoCarreras = NULL " + "WHERE idEquipoCarreras = ?";
        String consultaDelete = "DELETE FROM EquipoCarreras WHERE idEquipoCarreras = ?";
        PreparedStatement sentencia = null;
        
        try{
            conn.setAutoCommit(false);
            sentencia = conn.prepareStatement(cons);
            sentencia.setString(1, idEquipoCarreras);
            sentencia.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
        try{
            conn.setAutoCommit(false);
            sentencia = conn.prepareStatement(consultaDelete);
            sentencia.setString(1, idEquipoCarreras);
            sentencia.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    public void eliminarIngeniero(String idIngeniero) throws SQLException{
        String cons = "DELETE FROM Taller WHERE idIngeniero = ?";
        String consultaDelete = "DELETE FROM Ingeniero WHERE idIngeniero = ?";
        PreparedStatement sentencia = null;
        
        try{
            conn.setAutoCommit(false);
            sentencia = conn.prepareStatement(cons);
            sentencia.setString(1, idIngeniero);
            sentencia.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
        try{
            conn.setAutoCommit(false);
            sentencia = conn.prepareStatement(consultaDelete);
            sentencia.setString(1, idIngeniero);
            sentencia.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    public void eliminarCoche(String idCoche) throws SQLException{
        
        String cons = "DELETE FROM Taller WHERE idCoche = ?";
        String consultaPiloto = "UPDATE Piloto SET idCoche = NULL " + "WHERE idCoche = ?";
        String consultaDelete = "DELETE FROM Coche WHERE idCoche = ?";
        PreparedStatement sentencia = null;
        
        try{
            conn.setAutoCommit(false);
            sentencia = conn.prepareStatement(cons);
            sentencia.setString(1, idCoche);
            sentencia.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
        try{
            conn.setAutoCommit(false);
            sentencia = conn.prepareStatement(consultaPiloto);
            sentencia.setString(1, idCoche);
            sentencia.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
        try{
            conn.setAutoCommit(false);
            sentencia = conn.prepareStatement(consultaDelete);
            sentencia.setString(1, idCoche);
            sentencia.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    
    public String obtenerInformePiloto(String idPiloto){
        String consulta = "SELECT * FROM Genera WHERE idPiloto = " + idPiloto;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        String idInforme = "";
        //System.out.println("AAAAAAAAAAAAAAAAAAAIDPILOTO: " + idPiloto);
        
        try{
            sentencia = conn.prepareStatement(consulta);
            resultado = sentencia.executeQuery();
            
            while(resultado.next()){
                System.out.println("AAAAAAAAAAAAAARESULTADO: " + resultado.getString(3));
                if(resultado.getString(3) != null){ 
                    idInforme = resultado.getString(3);
                }
            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                    resultado.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println("IDINFORME: " + idInforme);
        return idInforme;
        
    }
    
    public void eliminarPiloto(String idPiloto) throws SQLException{
        
        String idInforme = obtenerInformePiloto(idPiloto);
        //System.out.println("AAAAAAAAAAAAAAAidINFORME " + idInforme);
        String consultaDeleteGenera = "DELETE FROM Genera WHERE idPiloto = ?";
        String consultaDeleteInforme = "DELETE FROM Informe WHERE idInforme = ?";
        String consultaDeletePiloto = "DELETE FROM Piloto WHERE idPiloto = ?";
        PreparedStatement sentencia = null;
        
        try{
            conn.setAutoCommit(false);
            sentencia = conn.prepareStatement(consultaDeleteGenera);
            sentencia.setString(1, idPiloto);
            sentencia.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
        try{
            if(!idInforme.equals("")){
                conn.setAutoCommit(false);
                sentencia = conn.prepareStatement(consultaDeleteInforme);
                sentencia.setString(1, idInforme);
                sentencia.executeUpdate();

                conn.commit();
                conn.setAutoCommit(true);
            }
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
        try{
                conn.setAutoCommit(false);
                sentencia = conn.prepareStatement(consultaDeletePiloto);
                sentencia.setString(1, idPiloto);
                sentencia.executeUpdate();

                conn.commit();
                conn.setAutoCommit(true);
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
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
                    if(resultado.getString(1) != null && resultado.getString(4) != null){
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
            //System.out.println("resultado: " + resultado.getString(1));
            //System.out.println("resultado2: " + resultado.getString(5));
            while(resultado.next()){
                System.out.println("resultado: " + resultado.getString(1));
                System.out.println("resultado2: " + resultado.getString(5));
            
                for(Piloto p: this.pilotosBD){
                    if(resultado.getString(1) != null && resultado.getString(5) != null){
                        //System.out.println("entro");
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
    
    public void generarInformePiloto(String idPiloto, String descripcion){
    
        String consulta = "INSERT INTO Informe (descripcion) VALUES (?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, descripcion);
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
        
        this.traerInformes();
        
        String idInforme = this.informesBD.get(this.informesBD.size()-1).getIdInforme();
        
        String consultaGenera = "INSERT INTO Genera (idPiloto, idInforme) VALUES (?, ?)";
        
        try{
            sentencia = conn.prepareStatement(consultaGenera);
            sentencia.setString(1, idPiloto);
            sentencia.setString(2, idInforme);
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
    
    public void eliminarInforme(String idInforme) throws SQLException{
        
        //System.out.println("AAAAAAAAAAAAAAAidINFORME " + idInforme);
        String consultaDeleteGenera = "DELETE FROM Genera WHERE idInforme = ?";
        String consultaDeleteInforme = "DELETE FROM Informe WHERE idInforme = ?";
        //String consultaDeletePiloto = "DELETE FROM Piloto WHERE idPiloto = ?";
        PreparedStatement sentencia = null;
        
        try{
            conn.setAutoCommit(false);
            sentencia = conn.prepareStatement(consultaDeleteGenera);
            sentencia.setString(1, idInforme);
            sentencia.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
        try{
            if(!idInforme.equals("")){
                conn.setAutoCommit(false);
                sentencia = conn.prepareStatement(consultaDeleteInforme);
                sentencia.setString(1, idInforme);
                sentencia.executeUpdate();

                conn.commit();
                conn.setAutoCommit(true);
            }
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            conn.rollback();
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
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
