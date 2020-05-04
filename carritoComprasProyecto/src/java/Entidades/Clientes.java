/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import anotaciones.AutoIncrement;
import anotaciones.Entity;
import anotaciones.FieldName;
import anotaciones.PrimaryKey;
import java.util.Date;

/**
 *
 * @author Alberto
 */
@Entity(table = "clientes")
public class Clientes {
    @PrimaryKey
    @FieldName(name = "idCliente")
    public long idCliente;
    public String Nombre;
    public String Apellido;
    public String Correo;
    public String Contrasena;
    public String tarjetaCredito="";
    public int idrol;
    public String CVV;

    public Clientes(){}
    public Clientes(long i,String n, String a, 
                    String co, String con,
                    String tc,String cv,int iR){
        this.idCliente=i;
        this.Nombre=n;
        this.Apellido=a;
        this.Correo=co;
        this.Contrasena=con;
        this.tarjetaCredito=tc;
        this.CVV=cv;
        this.idrol=iR;
    }

    public long getIdCliente() {
        return idCliente;
    }
    
    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     * @return the Correo
     */
    public String getCorreo() {
        return Correo;
    }

    /**
     * @param Correo the Correo to set
     */
    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    /**
     * @return the Contrasena
     */
    public String getContrasena() {
        return Contrasena;
    }

    /**
     * @param Contrasena the Contrasena to set
     */
    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    /**
     * @return the tarjetaCredito
     */
    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    /**
     * @param tarjetaCredito the tarjetaCredito to set
     */
    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    /**
     * @return the CVV
     */
    public String getCVV() {
        return CVV;
    }

    /**
     * @param CVV the CVV to set
     */
    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
    public void setIdRol(int i){
        this.idrol=i;
    }
    public int getIdRol(){
        return this.idrol;
    }
    
            
}

