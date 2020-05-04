/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import javax.naming.Context;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author DARQ
 */
public class ConexionPool implements Conexion {
    //Declaracion de variables
    private Connection conn;
    
    public ConexionPool(){
        this.conn = null;
    }
    
   public void Conectar(){
       
   }

    @Override
    public Connection getConexion() {
        return this.conn;
    }

    @Override
    public void Desconectar() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            Logger.getLogger(ConexionPool.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
