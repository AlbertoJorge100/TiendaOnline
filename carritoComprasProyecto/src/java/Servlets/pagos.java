/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Conexion.ConexionJDBC;
import Conexion.conexionSQlite;
import Entidades.Clientes;
import Entidades.FacturaProductos;
import Entidades.Facturas;
import Entidades.Ordenes;
import Entidades.Productos;
import Operaciones.Operaciones;
import Utilerias.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alberto
 */
public class pagos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /*TODO output your page here. You may use following sample code.*/
            try{
                boolean enc=false;
                String tC=null;
                tC=(String)request.getSession().getAttribute("tC");
                if(tC.equals("")){
                    String tarjetaCredito=request.getParameter("tarjeta");
                    String CVV=request.getParameter("CVV");
                    if(!tarjetaCredito.equals("") && !CVV.equals("")){
                        if(validarTarjeta(tarjetaCredito)){
                            if(validarCVV(CVV)){//paso los filtros y se actualizara la tarjeta de credito del cliente                                
                                long idC=(long)request.getSession().getAttribute("idC");
                                System.out.println("este es el id -> "+idC);
                                Clientes auxl=new Clientes();
                                auxl.setTarjetaCredito(Hash.generarHash(tarjetaCredito,Hash.SHA512));
                                auxl.setCVV(Hash.generarHash(tarjetaCredito,Hash.SHA512));                                 
                                ConexionJDBC conn=new ConexionJDBC();
                                conn.Conectar();
                                Operaciones.abrirConexion(conn);
                                Operaciones.iniciarTransaccion();                                                
                                Operaciones.actualizar(idC,auxl);                                
                                enc=true;
                            }else{
                                request.setAttribute("mensaje",
                                "<p style='color:red'>CVV incorrecto !!</p>");
                                String url="confirmacion.jsp?ttl="+request.getParameter("total2");
                                RequestDispatcher rd=request.getRequestDispatcher(url);                        
                                rd.forward(request,response); 
                            }
                            
                        }else{
                            request.setAttribute("mensaje",
                            "<p style='color:red'>Por favor ingresa una tarjeta de credito valida !!</p>");
                            String url="confirmacion.jsp?ttl="+request.getParameter("total2");
                            RequestDispatcher rd=request.getRequestDispatcher(url);                        
                            rd.forward(request,response); 
                        }
                        //codificar para insertar ala base de datos 
                    }else{
                        request.setAttribute("mensaje",
                        "<p style='color:red'>Ingresa tu tarjeta de Credito y tu clave secreta no dejes campos vacios !!</p>");
                        String url="confirmacion.jsp?ttl="+request.getParameter("total2");
                        RequestDispatcher rd=request.getRequestDispatcher(url);                        
                        rd.forward(request,response); 
                    }
                }                               
                long idF=(long)request.getSession().getAttribute("idF");
                long idO=(long)request.getSession().getAttribute("idO");
                List <Productos> lst=(List<Productos>)request.getSession().getAttribute("lista");
                if(!enc){//validar si se abrio anteriormente la conexion al ingresar la tarjeta de credito del cliente
                    ConexionJDBC conn=new ConexionJDBC();
                    conn.Conectar();
                    Operaciones.abrirConexion(conn);
                    Operaciones.iniciarTransaccion();
                }                
                for(int i=0;i<lst.size();i++){
                    FacturaProductos fct1=new FacturaProductos();
                    fct1.setIdFactura(idF);
                    fct1.setIdProducto(lst.get(i).getIdProducto());                    
                    Operaciones.insertar(fct1);
                } 
                Double total=(Double)Double.parseDouble(request.getParameter("total2"));
                System.out.println("este es el total: -> "+total);
                Facturas f=new Facturas();
                f.setIdOrden(idO); //el double parse double convierte a dato primitivo, (Double)es casteo a dato Objeto
                f.setTotal(total);
                f.setCostoEnvio(0.0);
                f.setFacturado(true);                
                Operaciones.actualizar(idF,f);
                Operaciones.commit();//commit final 
                HttpSession sesion = request.getSession();
                sesion.removeAttribute("lista");                
                response.sendRedirect("perfil.jsp");                
            }      
            catch(Exception e){                        
                out.println("<h1>"+e+"</h1>");
            }
            finally{
                try{
                    Operaciones.cerrarConexion();
                }catch(Exception e){
                    out.println("<h1>error al cerrar la conexion</h1>");
                }
            }         
        }
    }
    public void consultarIdFactura(){
        try{
            ConexionJDBC conn=new ConexionJDBC();
            conn.Conectar();
            Operaciones.abrirConexion(conn);
            Facturas f=new Facturas();
            f.setTotal(150.0);
            f.setCostoEnvio(0.0);
            Operaciones.iniciarTransaccion();
            Operaciones.actualizar(95, f);
        }catch(Exception e){
            System.out.println("error al llamar el idFactura -> " + e);
            
        }
        finally{
            try{
                Operaciones.cerrarConexion();
            }catch(Exception e){
                System.out.println("no se puede cerrar la conexion -> "+ e);
            }
        }
        
    }
    private boolean validarTarjeta(String tC){        
        int i=0,cont=0;
        while(i<tC.length()){
            if((int)tC.charAt(i)>=48 && (int)tC.charAt(i)<=57){
                cont++;
            }i++;
        }        
        if(cont==16 && i==16 ){
            return true;
        }else{
            return false;
        }
    }
    private boolean validarCVV(String tC){        
        int i=0,cont=0;
        while(i<tC.length()){
            if((int)tC.charAt(i)>=48 && (int)tC.charAt(i)<=57){
                cont++;
            }i++;
        }
        if(cont==3 && i==3 ){
            return true;
        }else{
            return false;
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
