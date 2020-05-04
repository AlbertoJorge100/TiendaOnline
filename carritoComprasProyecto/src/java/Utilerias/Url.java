/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

import Conexion.ConexionJDBC;
import Conexion.conexionSQlite;
import Entidades.Productos;
import Operaciones.Operaciones;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alberto
 */
public class Url {
    private String url;
    private List<Productos> lista;
    private ConexionJDBC conn=null;
    public Url(){
        lista=new ArrayList<Productos>(); 
    }
    public void Listar(int n,int idC){        
        Productos p=new Productos();
        try{//aprovechar la conexion que traemos desde que se registra el cliente o desde que inicia sesion 
            //si idC es menor a cero no existe una conexion abierta entonces la creamos
            List <Object> parametros=new ArrayList<Object>();
            String resultados[][]=null;
            if(idC<0){                
                conn=new ConexionJDBC();
                conn.Conectar(); 
                Operaciones.abrirConexion(conn);
            }            
            int aleatorio=0,i=0;            
            do{
                aleatorio=(int)((Math.random()*100)+5);                
                if(aleatorio<=100){
                    String sql="select  p.nombreProducto, p.Precio,p.fotoProducto from productos p where p.idProducto between ? and ?";
                    parametros.add(aleatorio);//paso de parametros ala logica de negocios
                    parametros.add(aleatorio+(n-1));                                                              
                    resultados=Operaciones.consultar(sql,parametros);
                    if(resultados!=null){                        
                        while(i<n){
                            Productos aux=new Productos();
                            aux.setIdProducto((long)aleatorio+i);//id
                            aux.setNombreProducto(resultados[0][i]);
                            aux.setPrecio(Double.parseDouble(resultados[1][i]));
                            aux.setFotoProducto(resultados[2][i]);                                                        
                            lista.add(aux);
                            i++;
                        }
                    }else{
                        System.out.println("error al acceder alos productos -> ");
                    }
                }                  
            }while(aleatorio>=100);   
        }catch(Exception e){
            System.out.println("error al conectar con el Url: -> "+ e);
        }
        finally{
            try{
                Operaciones.cerrarConexion();
            }
            catch(Exception e){
                System.out.println("no se puede cerrar la conexion");
            }
        }
  
    }
 
    private boolean buscarProducto(long id){
        boolean enc=false;
        int i=0;
        while(i<lista.size()&& !enc){
            if(lista.get(i).getIdProducto()==id){
                enc=true;
            }else{
                i++;
            }
        }
        return enc;
    }
    public List<Productos> getLista(){
        return this. lista;
    }
    
}
