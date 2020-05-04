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

/**
 *
 * @author Alberto
 */
@Entity(table="facturas")
public class Facturas {
    @PrimaryKey
    @AutoIncrement
    @FieldName(name = "idFactura")
    public long idFactura;
    public long idOrden;
    public Double costoEnvio;
    public boolean facturado;
    public Double Total;
    public Facturas(){
        this.costoEnvio=0.0;
        this.Total=0.0;
    }
    public Facturas(long ifc, long iO, Double cE, boolean fct,Double ttl){
        this.idFactura=ifc;
        this.idOrden=iO;
        this.costoEnvio=cE;
        this.facturado=fct;
        this.Total=ttl;
    }
    public long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(long idFactura) {
        this.idFactura = idFactura;
    }

    public long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(long idOrden) {
        this.idOrden = idOrden;
    }

    public Double getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(Double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public boolean getFacturado() {
        return facturado;
    }

    public void setFacturado(boolean facturado) {
        this.facturado = facturado;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }
}
