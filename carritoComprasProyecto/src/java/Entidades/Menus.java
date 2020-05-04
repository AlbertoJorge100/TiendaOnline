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
 * @author Ger
 */
@Entity(table = "menus")
public class Menus {
    @PrimaryKey
    @AutoIncrement
    @FieldName(name = "IdMenu")
    private int IdMenu;
    private String Menu;
    private String Descripcion;
    private String Url;
    private int IdPadre;
    
        public Menus(){}
    public Menus(int Idmenu, String Menu, String Descripcion, String Url ,int Idpadre){
        this.IdMenu = Idmenu;
        this.Menu = Menu;
        this.Descripcion = Descripcion;
        this.Url = Url;
        this.IdPadre = Idpadre; 
    }
    /**
     * @return the Idmenu
     */
    public int getIdmenu() {
        return IdMenu;
    }

    /**
     * @param Idmenu the Idmenu to set
     */
    public void setIdmenu(int Idmenu) {
        this.IdMenu = Idmenu;
    }

    /**
     * @return the Menus
     */
    public String getMenu() {
        return Menu;
    }

    /**
     * @param Menu the Menus to set
     */
    public void setMenu(String Menu) {
        this.Menu = Menu;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the Url
     */
    public String getUrl() {
        return Url;
    }

    /**
     * @param Url the Url to set
     */
    public void setUrl(String Url) {
        this.Url = Url;
    }

    /**
     * @return the IdPadre
     */
    public int getIdPadre() {
        return IdPadre;
    }

    /**
     * @param IdPadre the IdPadre to set
     */
    public void setIdPadre(int IdPadre) {
        this.IdPadre = IdPadre;
    }

}
