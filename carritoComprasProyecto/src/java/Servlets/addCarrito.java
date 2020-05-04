/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Conexion.ConexionJDBC;
import Conexion.conexionSQlite;
import Entidades.Ordenes;
import Entidades.Productos;
import Operaciones.Operaciones;
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
public class addCarrito extends HttpServlet {
    
    private HttpSession Sesion;
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
            List <Productos>lista=new ArrayList<Productos>();
            
            //ESTA LISTA ESTARA NULA SI ES PRIMER PRODUCTO QUE INGRESAMOS AL CARRITO
            ArrayList<Productos> lista2=(ArrayList<Productos>)request.getSession().getAttribute("lista");           
            Long id=Long.parseLong(request.getParameter("idProducto"));
            //ESTA ES LA LISTA DE PRODUCTOS QUE VIENEN EN LAVARIABLE DE SESION
            List <Productos>lst=(List<Productos>)request.getSession().getAttribute("lst");
            Productos aux=null;
            int i=0;
            boolean enc=false;
            //BUSQUEDA DEL PRODUCTO EN LA LISTA DE SESION
            while(i<lst.size()&&!enc){
                if(lst.get(i).getIdProducto()==id){
                    enc=true;
                    aux=lst.get(i);
                }else{
                    i++;
                }
            }
            if(lista2==null){
                //AQUI LA LISTA ESTA VACIA Y REQUERIMOS CREAR UNA NUEVA ORDEN EN EL SERVIDOR 
                try{
                    ConexionJDBC conn=new ConexionJDBC();                            
                    Ordenes orden=new Ordenes();
                    orden.setFecha(new Date());
                    orden.setFechaEnvio(new Date());
                    orden.setIdCliente((long)request.getSession().getAttribute("idC"));                            
                    conn.Conectar();
                    Operaciones.abrirConexion(conn);
                    Operaciones.iniciarTransaccion();
                    Operaciones.insertar(orden);                    
                    Operaciones.commit();                                         
                    lista2=new ArrayList<Productos>();
                    //AÑADIENDO PRODUCTO ALA LISTA DE SESION DEL CARRITO
                    String cns[][]= Operaciones.consultar("select max(f.idFactura),max(o.idOrden) from facturas f,ordenes o",(List)new ArrayList<Object>());                    
                    lista2.add(aux);
                    request.getSession().setAttribute("lista",lista2);
                    request.getSession().setAttribute("idF",Long.parseLong(cns[0][0]));                    
                    request.getSession().setAttribute("idO",Long.parseLong(cns[1][0]));
                    response.sendRedirect("carrito.jsp");    
                }catch(Exception e){
                    out.println("<h1> error al obtener idFactura -> "+e+"</h1>");
                }
                finally{
                    try{
                        Operaciones.cerrarConexion();
                    }catch(Exception e){
                        System.out.println("no se puede cerrar la conexion Orden -> " +e);
                    }

                }
                
            }else{
                //AÑADIENDO PRODUCTO ALA LISTA DE SESION DEL CARRITO 
                //EN CASO QUE SE EL SEGUNDO O N PRODUCTO DE LA LISTA
                lista2.add(aux);
                request.getSession().setAttribute("lista",lista2);
                response.sendRedirect("carrito.jsp");    
            }
            
        }
    }
    public void Eliminar(ArrayList <Productos> p, int i){
        
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
