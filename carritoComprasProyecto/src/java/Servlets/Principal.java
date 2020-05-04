/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entidades.Menus;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ger
 */
@WebServlet(name="Principal",urlPatterns={"/Principal"})
public class Principal extends HttpServlet {

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
            PrintWriter io = response.getWriter();
            String accion = request.getParameter("accion");
            if(accion == null){
                HttpSession s = request.getSession();
                List<Menus> per = (List<Menus>)s.getAttribute("Permisos");
                List<Menus> MenuPrincipal = per.stream().filter(field -> field.getIdPadre()==0).collect(Collectors.toList());
                request.setAttribute("MenuPrincipal", MenuPrincipal);
                String op = request.getParameter("op");
                if(op!=null){
                    List<Menus> PermisosAsignados = per.stream().filter(field -> field.getIdPadre()==Integer.parseInt(op)).collect(Collectors.toList());
                    request.setAttribute("PermisosAsignados", PermisosAsignados);
                }
                request.getRequestDispatcher("perfil.jsp").forward(request, response);
            }else if(accion.equals("logout")){
                logout(request,response);
            }
    }
    
    
    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //AQUI ESTAMOS REMOVIENDO LAS VARIABLES DE SESION 
        
        HttpSession sesion = request.getSession();
        sesion.removeAttribute("Usuario");
        sesion.removeAttribute("Nombre");
        sesion.removeAttribute("Rol");
        sesion.removeAttribute("lista");
        sesion.removeAttribute("lst");
        sesion.removeAttribute("id");
        sesion.removeAttribute("nom");
        sesion.removeAttribute("ape");
        sesion.removeAttribute("idF");        
        sesion.invalidate();
        response.sendRedirect("index.jsp");
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
