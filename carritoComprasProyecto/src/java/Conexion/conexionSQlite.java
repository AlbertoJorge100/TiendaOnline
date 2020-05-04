/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alberto
 */
public class conexionSQlite implements Conexion{
    private final String Url="C:\\carritoComprasDB\\";
    private final String DB="carritoCompras.db";
    Connection conn=null;
    public conexionSQlite(){
        
    }
    public void Conectar(){
        try{
            Class.forName("org.sqlite.JDBC");
            conn=DriverManager.getConnection("jdbc:sqlite:"+this.Url+this.DB);
            conn.setAutoCommit(false);
        }catch(Exception e){
            System.out.println("error: "+ e.getMessage().toString());
        }
    }
    public Connection getConexion(){
        
        return conn;
    }
    public void Desconectar(){
        try{
            conn.close();
        }catch(Exception e){
            System.out.println("Error al cerrar la conexion: " + e.toString());
        }
    }
}
