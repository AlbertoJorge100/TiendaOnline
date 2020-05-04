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
@Entity(table = "ordenes")
public class Ordenes {
    @PrimaryKey
    @AutoIncrement
    @FieldName(name = "idOrden")
    public long idOrden;
    public Date Fecha;
    public long idCliente;
    public Date fechaEnvio;
    public Ordenes(){}
    public Ordenes(long i,Date f, long iC, Date fE){
        this.idOrden=i;
        this.Fecha=f;
        this.idCliente=iC;
        this.fechaEnvio=fE;
    }

    /**
     * @return the idOrden
     */
    public long getIdOrden() {
        return idOrden;
    }

    /**
     * @param idOrden the idOrden to set
     */
    public void setIdOrden(long idOrden) {
        this.idOrden = idOrden;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return Fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.Fecha = fecha;
    }

    /**
     * @return the idCliente
     */
    public long getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the fechaEnvio
     */
    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * @param fechaEnvio the fechaEnvio to set
     */
    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

   
}
