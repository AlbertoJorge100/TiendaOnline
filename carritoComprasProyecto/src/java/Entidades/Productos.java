/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import anotaciones.AutoIncrement;
import anotaciones.Entity;
import anotaciones.FieldName;
import anotaciones.NotNull;
import anotaciones.PrimaryKey;
import javax.management.relation.Relation;
import javax.persistence.ForeignKey;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Null;

/**
 *
 * @author Alberto
 */
@Entity(table = "productos" )
public class Productos {
    @PrimaryKey
    @AutoIncrement
    @FieldName(name = "idProducto")
    
    public long idProducto;
    public String nombreProducto;
    public Double Precio;       
    public String Marca;
    public Double Tamano;
    public int Existencias;
    public String fotoProducto;
    public long idCategoria;
    public Productos(){}
    public Productos(long idProducto,String nombreProducto,Double Precio, long idCategoria, String Marca,Double Tamano, int Existencias, String fotoProducto){
        this.idProducto=idProducto;
        this.nombreProducto=nombreProducto;
        this.Precio=Precio;
        this.idCategoria=idCategoria;
        this.Marca=Marca;
        this.Tamano=Tamano;
        this.Existencias=Existencias;
        this.fotoProducto=fotoProducto;
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
     * @return the nombreProducto
     */
    public String getNombreProducto(int ctrl) {       
        switch(ctrl){
            case 1:{
                if(this.nombreProducto.length()>=25){
                    return this.nombreProducto.substring(0,25);
                }
                else{
                    return this.nombreProducto;
                }
            }
            case 2:{
                return this.nombreProducto;
                /*if(this.nombreProducto.length()>=50){
                    return this.nombreProducto.substring(0,40);
                }
                else{
                    return this.nombreProducto;
                }*/
            }
            default:{
                return this.nombreProducto;           
            }
        } 
       
    }

    /**
     * @param nombreProducto the nombreProducto to set
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * @return the Precio
     */
    public Double getPrecio() {
        return Precio;
    }

    /**
     * @param Precio the Precio to set
     */
    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }

    /**
     * @return the idCategoria
     */
    public long getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return the Marca
     */
    public String getMarca() {
        return Marca;
    }

    /**
     * @param Marca the Marca to set
     */
    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    /**
     * @return the Tamano
     */
    public Double getTamano() {
        return Tamano;
    }

    /**
     * @param Tamano the Tamano to set
     */
    public void setTamano(Double Tamano) {
        this.Tamano = Tamano;
    }

    /**
     * @return the Existencias
     */
    public int getExistencias() {
        return Existencias;
    }

    /**
     * @param Existencias the Existencias to set
     */
    public void setExistencias(int Existencias) {
        this.Existencias = Existencias;
    }

    /**
     * @return the fotoProducto
     */
    public String getFotoProducto() {
        return fotoProducto;
    }

    /**
     * @param fotoProducto the fotoProducto to set
     */
    public void setFotoProducto(String fotoProducto) {
        this.fotoProducto = fotoProducto;
    }
    
}
