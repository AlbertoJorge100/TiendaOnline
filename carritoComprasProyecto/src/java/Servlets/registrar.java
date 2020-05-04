/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;
import Conexion.ConexionJDBC;
import Entidades.Clientes;
import Operaciones.Operaciones;
import Utilerias.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alberto
 */
public class registrar extends HttpServlet {
    private ConexionJDBC conn=new ConexionJDBC();
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
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            String correo=request.getParameter("correo");
            String contrasena=request.getParameter("contrasena");
            String contrasena2=request.getParameter("contrasena2");
            if(!nombre.equals("")&&!apellido.equals("")&&!correo.equals("")
                &&!contrasena.equals("")&&!contrasena2.equals("")){//VALIDACION DE CAMPOS SI ESTAN VACIOS 
                if(comprobacionCorreo(correo)){//LLAMADA ALA FUNCION QUE EXAMINA SI EXISTE @ Y . EN EL CORREO
                    this.conn.Conectar();//CONECTAMOS LA INSTANCIA UNICA Y EXCLUSIVA VEZ
                    Operaciones.abrirConexion(this.conn);//ABRIMOS CONEXION UNICA Y EXCLUSIVA VEZ
                    if(!consultaCorreo(correo)){
                        String contra=evaluarContrasena(contrasena);
                        if(contra.equals("")){
                            if(contrasena.equals(contrasena2)){
                                String nom="";
                                long idC=0;
                                try{
                                    //HA PASADO TODOS LOS FILTROS Y SERA INGRESADO ALA BD
                                    Clientes cliente=new Clientes();
                                    cliente.setNombre(nombre);
                                    cliente.setApellido(apellido);
                                    cliente.setCorreo(correo);
                                    cliente.setContrasena(Hash.generarHash(contrasena,Hash.SHA512));
                                    cliente.setIdRol(1);                                                                                                            
                                    Operaciones.iniciarTransaccion();
                                    Operaciones.insertar(cliente);
                                    Operaciones.commit();
                                    nom=calcularNombres(nombre,apellido);//obtenemos el primer nombre y el primer apellido
                                    //AQUI SELECCIONAMOS EL ID DEL CLIENTE QUE NOS GENERO EL SERVIDOR MYSQL PARA LA VARIABLE DE SESION 
                                    String rs[][]= Operaciones.consultar("select last_insert_id()",(List)new ArrayList<Object>());                                                            
                                    if(rs!=null){
                                        idC=Long.parseLong(rs[0][0]);
                                    }                                                                        
                                    else{
                                        System.out.println("error al consultar el ultimo id insertado!!");
                                    }
                                }catch(Exception e){
                                    System.out.println("error interno en el servidor -> " + e);
                                    request.setAttribute("mensaje", "<p style='color:dodgerblue'>:( Error interno del servidor rogamos nos disculpes estamos trabajando para solucionarlo</p>");
                                    RequestDispatcher rd=request.getRequestDispatcher("registro.jsp");                
                                    rd.forward(request, response);   

                                }
                                finally{
                                    try{//CERRAMOS CONEXION PRINCIPAL CUARTA OPCION
                                        //Operaciones.cerrarConexion();
                                        //System.out.println("Operacion cerrada con exito Principal!!");
                                        request.setAttribute("idConexion","prb");//este id es para que la conexion se cierre en el perfil.jsp
                                        request.getSession().setAttribute("idC",idC);
                                        request.getSession().setAttribute("nombres",nom);                                                                
                                        request.setAttribute("mensaje", "<img src='https://media.istockphoto.com/vectors/welcome-banner-with-colorful-confetti-vector-id869869322'");
                                        RequestDispatcher rd=request.getRequestDispatcher("perfil.jsp");                
                                        rd.forward(request, response);                                           
                                    }catch(Exception e){
                                        System.out.println("error al cerrar la conexion -> " + e);
                                        request.setAttribute("mensaje", "<p style='color:dodgerblue'>:( Error interno del servidor rogamos nos disculpes estamos trabajando para solucionarlo</p>");
                                        RequestDispatcher rd=request.getRequestDispatcher("registro.jsp");                
                                        rd.forward(request, response); 
                                    }
                                }
                            }else{
                                try{
                                    Operaciones.cerrarConexion();//CERRAMOS CONEXION PRINCIPAL TERCERA OPCION
                                    request.setAttribute("mensaje", "<p style='color:red'>Contraseñas no coinciden!!</p>");
                                    RequestDispatcher rd=request.getRequestDispatcher("registro.jsp");
                                    rd.forward(request,response);
                                    System.out.println("Operacion cerrada con exito!!");
                                }catch(Exception e){
                                    System.out.println("no se puede cerrar la conexion principal -> " +e);
                                }                                 
                            }
                        }else{
                            try{
                                Operaciones.cerrarConexion();//CERRAMOS CONEXION PRINCIPAL SEGUNDA OPCION
                                request.setAttribute("mensaje2", "<p style='color:red'>Contraseña rechazada!! "+contra+"</p></br>");
                                RequestDispatcher rd=request.getRequestDispatcher("registro.jsp");
                                rd.forward(request,response);
                                System.out.println("Operacion cerrada con exito!!");
                            }catch(Exception e){
                                System.out.println("no se puede cerrar la conexion principal -> " +e);
                            }   
                        }
                    }else{
                        try{
                            Operaciones.cerrarConexion();//CERRAMOS CONEXION PRINCIPAL PRIMERA OPCION
                            request.setAttribute("mensaje","<p style='color:red'>Este correo ya esta siendo utilizado por un usuario!!</ṕ>");
                            RequestDispatcher rd=request.getRequestDispatcher("registro.jsp");
                            rd.forward(request,response);
                            System.out.println("Operacion cerrada con exito!!");
                        }catch(Exception e){
                            System.out.println("no se puede cerrar la conexion principal -> " +e);
                        }                        
                    }               
                }else{                    
                    request.setAttribute("mensaje", "<p style='color:red'>Ingrese un correo valido!!</p>");
                    RequestDispatcher rd=request.getRequestDispatcher("registro.jsp");                
                    rd.forward(request, response); 
                }                               
            }else{
                request.setAttribute("mensaje", "<p style='color:red'>No deje campos vacios </p>");
                RequestDispatcher rd=request.getRequestDispatcher("registro.jsp");                
                rd.forward(request, response);                                
            }            
        }
    }
    /**                                                                                                                                                                                                                                                                                                                                                                                                                                         
     * calcularemos los nombres para retornar el primer nombre y el primer apellido
     * @param nombres
     * @param apellidos
     * @return 
     */
    private String calcularNombres(String nombres, String apellidos){    
        int i=0, j=0;
        boolean enc=false;
        while(i<nombres.length() && !enc){
            if(nombres.charAt(i)==' '){
                enc=true;
            }else{
                i++;
            }
        }
        enc=false;
        while(j<apellidos.length() && !enc){
            if(apellidos.charAt(j)==' '){
                enc=true;
            }else{
                j++;
            }
            
        }
        
        return nombres.substring(0,i)+" "+apellidos.substring(0,j);
    }
    /**
     * comprobaremos el correo electronico si lleva arroba y punto 
     * @param co
     * @return 
     */
    private boolean comprobacionCorreo(String co){
        int i=0;        
        boolean enc=false,comprobacion=false;
        while(i<co.length()&&!enc){
            if(co.charAt(i)=='@'){
                enc=true;
            }else{
                i++;
            }
        }
        if(enc){
            while(i<co.length()&&!comprobacion){
                if(co.charAt(i)=='.'){
                    comprobacion=true;
                }else{
                    i++;
                }
            }
        }
        return comprobacion;
    }
    /*
        CONSULTA ALA BASE DE DATOS SI EXISTE EL CORREO QUE INGRESO EL CLIENTE
        SI EL CORREO EXISTE, NO PODRA REGISTRAR ESE CORREO
    */
    private boolean consultaCorreo(String correo){        
        boolean res=false;
        try{                                               
            String sql="select Correo from clientes  where Correo=?";            
            List <Object>params=new ArrayList<Object>();            
            params.add(correo);                        
            //LA APERTURA DE CONEXION LA REALIZAMOS EN LAS VALIDACIONES PRINCIPALES
            String resultado[][]=Operaciones.consultar(sql, params);//CONSULTAMOS                        
            System.out.println(correo);
            if(resultado!=null){
                res=true;
            }            
        }catch(Exception e){
            System.out.println("el correo no existe -> "+ e);
            return false;            
        }
        /*finally{ EL CIERRE DE ESTA CONEXION LO REALIZAMOS EN TRY CATCH PRINCIPAL
            try{
                Operaciones.cerrarConexion();
            }catch(Exception e){
                System.out.println("no se puede cerrar la conexion en consultaCorreo -> " + e);
            }
        }*/
        return res;
    }
    /**
     * esta funcion es para evaluar la contraseña y que cumpla con los requisitos 
     * entre mayusculas minusculas caracteres especiales
     * @param contra
     * @return 
     */
    private String evaluarContrasena(String contra){
        List <Integer>lst=new ArrayList<>(); 
        int i=0,j=0,i2=0,j2=0,i3=0,j3=0,cont=0;
        boolean encd=false;
        String concatenacion="";        
        int lng=contra.length();//longitud de la contraseña
        //MAYUSCULAS
        while(i<lng){//VEREMOS SI EXISTE ALGUNA LETRA MAYUSCULA SI EXISTE DEVOLVERA EL CONTADOR >1 DE LO CONTRARO 0
            if(Character.isUpperCase(contra.charAt(i))){
                j++;
            }
            i++;
        }
        //espacio ! " # $ % & ' () * + ,-. /
        while(i2<lng){
            if((int)contra.charAt(i2)>=32 && (int)contra.charAt(i2)<=47){               
                j2++;                
            }
            i2++;
        }
        //NUMEROS
        while(i3<lng){
            if((int)contra.charAt(i3)>=48&&(int)contra.charAt(i3)<=57){
                j3++;
            }
            i3++;
        }                            
        lst.add(j);
        lst.add(j2);
        lst.add(j3);        
        while(cont<lst.size()&&!encd){
            if(lst.get(cont)<1){
                encd=true;
            }else{
                cont++;
            }
        }
        if(encd){
            for(int ix=0;ix<3;ix++){        
                if(lst.get(ix)<1){            
                    concatenacion =concatenacion+" "+calcular(ix);            
                }            
            }       
            return "porfavor añada "+concatenacion;
        }else{
            return "";
        }
    }


    private String calcular(int nm){
        switch(nm){
            case 0:{
                return "una letra mayuscula";
            }
            case 1:{
                return "un caracter especial: esp ! \" # $ % & ' () * + , - . / ";
            }
            case 2:{
                return "un numero";
            }
            default:{
                return "";
            }
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
