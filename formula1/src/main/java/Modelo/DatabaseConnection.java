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

    public static void connect() throws SQLException {
        // Cadena de conexión para Oracle
        String url = "jdbc:sqlite:D:\\Program Files\\SqliteStudio\\basededatos\\formula1.db";
        try{
             conn = DriverManager.getConnection(url);
        }
        catch(SQLException e){
            throw e;
        }
        
    }

    public DatabaseConnection() {
        conn = null;
    }
    
    /**
     * @brief Este método fue una prueba que nunca salió adelante, se encuentra en desarrollo
     * @author Juan Cabello Rodríguez
     */
    public void insertarDatosEnTabla(String query) throws SQLException{
        PreparedStatement sentencia = null;
        try{
            sentencia = this.conn.prepareStatement(query);
            
            sentencia.executeUpdate();
            
        }
        catch(SQLException e){
            throw e;
        }
        finally{
            try{
                if(sentencia != null){
                    sentencia.close();
                }
            }
            catch(SQLException excptn){
                throw excptn;
            }
        }
    }

    /**
     * @brief Inserta un Equipo de Carreras en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void insertarEquipoCarreras(String nombre) throws SQLException{
        String consulta = "INSERT INTO EquipoCarreras (nombre) VALUES (?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
    }

    /**
     * @brief Inserta un Coche en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void insertarCoche(String marca, String modelo) throws SQLException{
        String consulta = "INSERT INTO Coche (marca, modelo) VALUES (?, ?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, marca);
            sentencia.setString(2, modelo);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
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
    

    
    /**
     * @brief Inserta un Piloto en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void insertarPiloto(String nombre, int edad) throws SQLException{
        String consulta = "INSERT INTO Piloto (nombre, edad) VALUES (?, ?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.setInt(2, edad);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
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
    
    /**
     * @brief Inserta un Ingeniero en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void insertarIngeniero(String fecha, double sueldo) throws SQLException{
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
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
    }
    
    /**
     * @brief Modifica el nombre de un Equipo de Carreras en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void modificarNombreEquipoCarreras(String idEquipo, String nombre) throws SQLException{
        String consulta = "UPDATE EquipoCarreras SET nombre = ? " + "WHERE idEquipoCarreras = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.setString(2, idEquipo);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
    }
    
    /**
     * @brief Modifica la fecha de nacimiento de un Ingeniero en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void modificarFechaIngeniero(String idIngeniero, String fecha) throws SQLException{
        String consulta = "UPDATE Ingeniero SET FechaNacimiento = ? " + "WHERE idIngeniero = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, fecha);
            sentencia.setString(2, idIngeniero);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
    }
    
    /**
     * @brief Modifica el sueldo de un Ingeniero en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void modificarSueldoIngeniero(String idIngeniero, double sueldo) throws SQLException{
        String consulta = "UPDATE Ingeniero SET Sueldo = ? " + "WHERE idIngeniero = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setDouble(1, sueldo);
            sentencia.setString(2, idIngeniero);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
    }
    
    /**
     * @brief Modifica la marca de un coche en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void modificarMarcaCoche(String idCoche, String marca) throws SQLException{
        String consulta = "UPDATE Coche SET marca = ? " + "WHERE idCoche = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, marca);
            sentencia.setString(2, idCoche);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
    }
    
    /**
     * @brief Modifica el modelo de un coche en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void modificarModeloCoche(String idCoche, String modelo) throws SQLException{
        String consulta = "UPDATE Coche SET modelo = ? " + "WHERE idCoche = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, modelo);
            sentencia.setString(2, idCoche);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
    }
    
    /**
     * @brief Modifica la descripción de un informe en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void modificarDescripcionInforme(String idInforme, String descripcion) throws SQLException{
        String consulta = "UPDATE Informe SET descripcion = ? " + "WHERE idInforme = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, descripcion);
            sentencia.setString(2, idInforme);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
    }
    
    /**
     * @brief Modifica el nombre de un piloto en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void modificarNombrePiloto(String idPiloto, String nombre) throws SQLException{
        String consulta = "UPDATE Piloto SET nombre = ? " + "WHERE idPiloto = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.setString(2, idPiloto);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
    }
    /**
     * @brief Modifica la edad de un piloto en la base de datos
     * @author Juan Cabello Rodríguez
     */
    public void modificarEdadPiloto(String idPiloto, int edad) throws SQLException{
        String consulta = "UPDATE Piloto SET edad = ? " + "WHERE idPiloto = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setInt(1, edad);
            sentencia.setString(2, idPiloto);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
    }
    
    /**
     * @brief Asigna un equipo a un piloto en la BD.
     * @author Juan Cabello Rodríguez
     */
    public void asignarEquipoAPiloto(String idPiloto, String idEquipo) throws SQLException{
        String consulta = "UPDATE Piloto SET idEquipoCarreras = ? " + "WHERE idPiloto = ?";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, idEquipo);
            sentencia.setString(2, idPiloto);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
    }
    
    /**
     * @brief Crea un taller en la BD, es decir la relación entre un coche y un ingeniero
     * @author Juan Cabello Rodríguez
     */
    public void crearTaller(String idCoche, String idIngeniero) throws SQLException{
        String consulta = "INSERT INTO Taller (idCoche, idIngeniero) VALUES (?, ?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, idCoche);
            sentencia.setString(2, idIngeniero);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
        
    }
    
    /**
     * @brief Asigna un coche a un piloto en la BD.
     * @author Juan Cabello Rodríguez
     */
    public void asignarCocheAPiloto(String idCoche, String idPiloto) throws SQLException{
        PreparedStatement sentencia = null;
        
        String consultaPiloto = "UPDATE Piloto SET idCoche = ? " + "WHERE idPiloto = ?";
        
        try{
            sentencia = conn.prepareStatement(consultaPiloto);
            sentencia.setString(1, idCoche);
            sentencia.setString(2, idPiloto);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }catch(SQLException sqlexcptn){
                    throw sqlexcptn;
                }
            }
        }
        
    }
    
    /**
     * @brief Consulta los equipos de carreras y los muestra por pantalla
     * @author Juan Cabello Rodríguez
     */
    public void consultarTablaEquiposCarreras() throws SQLException{
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
            throw sqle;
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
    }
    
    /**
     * @brief Elimina un equipo de carreras de la BD
     * @post Debe deshacer la asignación con el Piloto
     * @author Juan Cabello Rodríguez
     */
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
            
            conn.rollback();
            throw sqle;
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
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
            
            conn.rollback();
            throw sqle;
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
        
    }
    
    /**
     * @brief Elimina un equipo de carreras de la BD
     * @post Debe deshacer la relación con el Coche
     * @author Juan Cabello Rodríguez
     */
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
            
            conn.rollback();
            throw sqle;
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
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
            conn.rollback();
            throw sqle;
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
        
    }
    /**
     * @brief Elimina un coche de la base de datos
     * @post Deben quedar resueltas las relaciones tanto con el piloto como con los ingenieros
     * @author Juan Cabello Rodríguez
     */
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
            conn.rollback();
            throw sqle;
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
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
            conn.rollback();
            throw sqle;
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
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
            conn.rollback();
            throw sqle;
        }
        finally{
            if(cons != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
        
    }
    
    /**
     * @brief Obtiene un informe de un piloto
     * @author Juan Cabello Rodríguez
     */
    public String obtenerInformePiloto(String idPiloto) throws SQLException{
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
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                    resultado.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
        System.out.println("IDINFORME: " + idInforme);
        return idInforme;
        
    }
   
    /**
    * @brief Elimina un piloto de la BD
    * @post Elimina el informe generado por el piloto y deshace la relación con el coche asignado
    * @author Juan Cabello Rodríguez
    */
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
            
            conn.rollback();
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
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
            conn.rollback();
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
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
            conn.rollback();
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
       
    }
    
    /**
    * @brief Relaciona los equipos con los pilotos
    * @post Pilotos con sus respectivos equipos
    * @author Juan Cabello Rodríguez
    */
    public void relacionarEquiposConPilotos() throws SQLException{
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
            throw sqle;
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
    }
    
    /**
    * @brief Relaciona los coches con los pilotos
    * @post Pilotos con su respectivo coche
    * @author Juan Cabello Rodríguez
    */
    public void relacionarCochesConPilotos() throws SQLException{
    
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
            throw sqle;
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
    
    }
    
    /**
    * @brief Relaciona los coches con los ingenieros
    * @post Coches con sus respectivos ingenieros
    * @author Juan Cabello Rodríguez
    */
    public void relacionarCochesConIngenieros() throws SQLException{
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
            throw sqle;
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
    
    }
    
    /**
    * @brief Relaciona los informes con los pilotos
    * @post Pilotos con sus respectivos informes
    * @author Juan Cabello Rodríguez
    */
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
    
    /**
    * @brief Genera un informe del piloto
    * @post Queda la relación establecida entre ese informe y ese piloto
    * @author Juan Cabello Rodríguez
    */
    public void generarInformePiloto(String idPiloto, String descripcion) throws SQLException{
    
        String consulta = "INSERT INTO Informe (descripcion) VALUES (?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conn.prepareStatement(consulta);
            sentencia.setString(1, descripcion);
            sentencia.executeUpdate();
        }
        catch(SQLException sqle){
            throw sqle;
        }
        finally{
           if(sentencia != null){
                try{
                   sentencia.close();
               }catch(SQLException sqlexcptn){
                   throw sqlexcptn;
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
            throw sqle;
        }
        finally{
           if(sentencia != null){
                try{
                   sentencia.close();
               }catch(SQLException sqlexcptn){
                   throw sqlexcptn;
               }
           }
        }
        
        
    }
    
    /**
    * @brief Elimina un informe
    * @post Deshace la relación del piloto con ese informe
    * @author Juan Cabello Rodríguez
    */
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
            
            conn.rollback();
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
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
            
            conn.rollback();
            throw sqle;
        }
        finally{
            if(sentencia != null){
                try{
                    sentencia.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
    }
    
    /**
    * @brief Trae los ingenieros de la BD
    * @post No están establecidas las relaciones
    * @author Juan Cabello Rodríguez
    */
    public void traerIngenieros() throws SQLException{
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
            throw sqle;
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
        
    }
    
    /**
    * @brief Trae los coches de la BD
    * @post No están establecidas las relaciones
    * @author Juan Cabello Rodríguez
    */
    public void traerCoches() throws SQLException{
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
            throw sqle;
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
        
    }
    
    /**
    * @brief Trae los genera de la BD
    * @post Se encarga de relacionar los informes y los pilotos
    * @author Juan Cabello Rodríguez
    */
    public void traerGenera() throws SQLException{
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
            throw sqle;
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
        
    }
    
    /**
    * @brief Trae los informes de la BD
    * @post No están establecidas las relaciones
    * @author Juan Cabello Rodríguez
    */
    public void traerInformes() throws SQLException{
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
            throw sqle;
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
    }
    
    /**
    * @brief Trae los pilotos de la BD
    * @post No están establecidas las relaciones
    * @author Juan Cabello Rodríguez
    */
    public void traerPilotos() throws SQLException{
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
            throw sqle;
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
        
    }
    
    /**
    * @brief Trae los equipos de carreras de la BD
    * @post No están establecidas las relaciones
    * @author Juan Cabello Rodríguez
    */
    public void traerEquiposCarreras() throws SQLException{
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
            throw sqle;
        }
        finally{
            if(consulta != null){
                try{
                    consulta.close();
                    resultado.close();
                }
                catch(SQLException e){
                    throw e;
                }
            }
        }
        
    }
        
    public static void cerrarConexion() throws SQLException{
        try{
            if(conn != null){
                conn.close();
            }
       }
       catch(SQLException e){
            throw e;
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
