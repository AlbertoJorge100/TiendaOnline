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
@Entity(table="facturaProductos")
public class FacturaProductos {
    @PrimaryKey
    @AutoIncrement
    @FieldName(name = "idfacturaProducto")
    // LA LLAVE SE LLAMA idfacturaProducto CUANDO DEBIO LLAMARSE idFacturaProducto
    public long idfacturaProducto;
    public long idFactura;
    public long idProducto;
    public long Cantidad;
    
    public FacturaProductos(){}
    public FacturaProductos(long ifp, long ifc, long ip,long c){
        this.idfacturaProducto=ifp;
        this.idFactura=ifc;
        this.idProducto=ip;
        this.Cantidad=c;
        
    }
    /**
     * @return the idFacturaProductos
     */
    public long getIdFacturaProductos() {
        return idfacturaProducto;
    }

    /**
     * @param idFacturaProductos the idFacturaProductos to set
     */
    public void setIdFacturaProductos(long idFacturaProductos) {
        this.idfacturaProducto = idFacturaProductos;
    }

    /**
     * @return the idFactura
     */
    public long getIdFactura() {
        return this.idFactura;
    }

    /**
     * @param idFactura the idFactura to set
     */
    public void setIdFactura(long idFactura) {
        this.idFactura = idFactura;
    }

    /**
     * @return the idProducto
     */
    public long getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the Cantidad
     */
    public long getCantidad() {
        return Cantidad;
    }

    /**
     * @param Cantidad the Cantidad to set
     */
    public void setCantidad(long Cantidad) {
        this.Cantidad = Cantidad;
    }

    
}
