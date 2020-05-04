/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

/**
 *
 * @author DARQ
 */
public class ConexionJDBC implements Conexion {
    
    //Declaracion de variables
    private Connection conn=null;
    
    private final String db="mydb";
    private final String url="jdbc:mysql://localhost:3306/"+this.db;
    private final String user="root";
    private final String pass="jorge_perez100";

    /**
     * Constructor para inicializar la conexion a una base de datos por JDBC
     * @param url
     * @param db
     * @param user
     * @param pass 
     */
  
 
    public ConexionJDBC(){
        //Inicializamos las variables
        
    }
  
    public void Conectar() {
        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection(url,user,pass);
            this.conn.setAutoCommit(false);
            System.setProperty("java.awt.headless", "true");
            Toolkit tk = Toolkit.getDefaultToolkit();
        } catch (Exception e) {
            System.out.println("Error en Conexion[JDBC: " + e.toString());
            
        } 
    }

    @Override
    public Connection getConexion(){
        return conn;
    }
   
    @Override
    public void Desconectar() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            System.out.println("Error en Conexion[JDBC: " + e.toString());
        }
    }
    
}
