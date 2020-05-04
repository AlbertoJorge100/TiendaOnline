package Servlets;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Conexion.ConexionJDBC;
import Entidades.Clientes;
import Operaciones.Operaciones;
import Utilerias.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alberto
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {  
    private int num=0;
    private String Nm;
    private String Nm1;
    private String Ap;
    private String Cn;
    private String Correo;
    private String Contrasena;
    private long idC;
    //private Clientes aux=null;
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");            
        try (PrintWriter out = response.getWriter()) {
            this.Correo=request.getParameter("correo");
            this.Contrasena=request.getParameter("contrasena");
            if(!Correo.equals("") && !Contrasena.equals("")){//validacion si esta vacio algun campo
                String cntr=null;                
                cntr=request.getParameter("auxContra"); //recibe una contraseña si en caso el usuario la olvido la almacena aqui
                System.out.println("este es el correooo -> "+request.getParameter("auxCorreo"));
                if(cntr!=null && !this.Correo.equals(request.getParameter("auxCorreo"))){
                    System.out.println("este es el correooo22 -> "+request.getParameter("auxCorreo"));
                    cntr=null;
                }
                if(cntr==null){//si cntr es nula entonces el usuario ingreso su contraseña correctamente
                    System.out.println("es nulo: prueba  "+new Date());                    
                    buscarPrincipal(request,response);
                    
                }else{//de lo contrario la contraseña la almacenamos en una variable request para ya no consultar ala BD
                    ///ingreso una contraseña incorrecta para el usuario
                    System.out.println("no es nulo: prueba");
                    if(request.getParameter("auxCorreo").equals(this.Correo)){//validamos si es el mismo correo procedemos
                        System.out.println("esta es la contra: "+request.getParameter("auxContra"));
                        if(request.getParameter("auxContra").equals(Hash.generarHash(this.Contrasena,Hash.SHA512))){//si es la contraseña valida procedemos 
                            request.getSession().setAttribute("idC",request.getParameter("auxId"));
                            request.getSession().setAttribute("nombres", request.getParameter("auxNombre")+" "+request.getParameter("auxApellido"));
                            int opcion=0;
                            opcion=Integer.parseInt(request.getParameter("idAux"));
                            if(opcion!=-1){                        
                                String variable="detalleProductoCliente.jsp?idPro="+opcion+"";
                                RequestDispatcher rd=request.getRequestDispatcher(variable);
                                rd.forward(request,response);
                            }else{
                                request.setAttribute("idConexion","5");//este id es para que la conexion se cierre en el perfil.jsp
                                RequestDispatcher rd=request.getRequestDispatcher("perfil.jsp");                        
                                rd.forward(request,response); 
                            }
                        }else{//si la contraseña es invalida nuevamente la almacenamos en una variable request para no consultar la BD                            
                            System.out.println("contraseña incorrecta-> ");         
                            String nombre=request.getParameter("auxNombre");
                            String apellido=request.getParameter("auxApellido");
                            request.setAttribute("mensaje", "<p style='font-size:20px'>"+nombre+" "+apellido+"</p><p style='color:red'> has ingresado una contraseña incorrecta!!</p>");                                                        
                            request.setAttribute("mensaje2", "<p><a href='#'>Olvidastes tu contraseña?</a></p>");
                            this.num=0;                                                                                               
                            String url="login.jsp?auxContra="+request.getParameter("auxContra")                                    
                                    +"&auxId="+request.getParameter("auxId")+"&auxCorreo="+request.getParameter("auxCorreo")
                                    +"&auxNombre="+nombre+"&auxApellido="+apellido;
                            System.out.println("nombres2 -> "+nombre+" "+apellido);
                            System.out.println(this.Cn);
                            cntr=null;
                            RequestDispatcher rd=request.getRequestDispatcher(url);
                            rd.forward(request,response);
                        }
                    }else{//si no es el mismo correo entonces consultamos la BD
                        buscarPrincipal(request,response);                                    
                    }
                }
                
            }else{                
                request.setAttribute("mensaje", "<p style='color:red'>No deje campos vacios!! </p>");
                RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
                rd.forward(request,response);                        
            }
        }
    }
    /*
        funcion para calcular el primer nombre y el primer apellido de el cliente        
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
            
        }//retornamos el primer nombre y el primer apellido         
        return nombres.substring(0,i)+" "+apellidos.substring(0,j);
    }
    private String calcularNombres2(String nombres){        
        int i=0, j=0;
        boolean enc=false;        
        while(i<nombres.length() && !enc){
            if(nombres.charAt(i)==' '){
                enc=true;
            }else{
                i++;
            }
        }
        return nombres.substring(0,i);
    }
    /*
        buscamos el cliente en la base de datos si existe retornara una instancia Clientes
        si no existe retornara null
    */
    private Clientes Buscar(String co, String con){        
        Clientes aux=null;
        try{
            //BUSQUEDA DEL CLIENTE             
            ConexionJDBC conn=new ConexionJDBC();
            conn.Conectar();
            Operaciones.abrirConexion(conn);
            if(conn.getConexion()!=null){
                //OPTIMIZANDO LA CONSULTA PARA NO RALENTIZAR EL SERVIDOR
                String sql = "select c.idCliente,c.Nombre,c.Apellido,c.Contrasena ,c.tarjetaCredito from clientes c where c.Correo=?";            
                List<Object> param = new ArrayList<Object>();
                param.add(co);
                //SI DE AQUI NO EXISTE EL CORREO DIRECTAMENTE SE VA AL CATCH PERO SI EXISTE PASA AL IF 
                String[][] rs = Operaciones.consultar(sql, param);     
                //SI EXISTE EL CORREO PERO NO SON IGUALES LAS CONTRASEÑAS ENTONCES SE PASA AL ELSE Y RETORNA NULL           
                if(rs[3][0].equals(Hash.generarHash(con, Hash.SHA512))){               
                    aux=new Clientes();
                    aux.setIdCliente(Long.parseLong(rs[0][0]));
                    aux.setNombre(rs[1][0]);
                    aux.setApellido(rs[2][0]);  
                    aux.setTarjetaCredito(rs[4][0]);
                    //return aux;
                }else{   
                    /*el usuario existe pero ingreso una contraseña incorrecta  con estos atributos 
                      capturaremos el nombre apellido y contraseña del usuario 
                      para almacenarlos en una variable request para luego que intente ingresar la contraseña 
                      correcta entonces se valide a travez de las variables request
                    */
                    this.Nm=rs[1][0];
                    this.Ap=rs[2][0];
                    this.Nm1=calcularNombres(this.Nm,this.Ap);
                    this.Nm=calcularNombres2(this.Nm);
                    this.Ap=calcularNombres2(this.Ap);
                    this.idC=Long.parseLong(rs[0][0]);
                    System.out.println("holaaaa"+this.Nm+" "+this.Ap);
                    this.Cn=rs[3][0];
                    this.num=1;              
                } 
            }else{
                this.num=2;
            }
            
            //return new Clientes(Long.parseLong(rs[0][0]), rs[1][0], rs[2][0],rs[3][0], rs[4][0],rs[5][0], Integer.parseInt(rs[6][0]==null?"0":rs[6][0]),Integer.parseInt(rs[7][0]==null?"0":rs[7][0]));                         
        }catch (Exception ex){            
            System.out.println("no existe -> " + ex);                    
        }
        
        if(aux==null){           
            try{
                Operaciones.cerrarConexion();
                System.out.println("conexion cerrada exitosamente!!");
            }catch(Exception e){
                System.out.println("no se puede cerrar la conexion buscar -> " +e);
            }
        
        }
        return aux;   
    }
    /*
        esta metodo es el principal que se encarga de todas las gestiones. y conectar los resultados de la busqueda 
        del cliente con las posibles excepciones o el acceso directo al sistema
    */
    private void buscarPrincipal(HttpServletRequest request, HttpServletResponse response)throws IOException,
        SQLException, ServletException{
        Clientes aux=null;
        try{                    
            //buscamos si el cliente existe en la bd a travez de la funcion Buscar
            aux=Buscar(this.Correo,this.Contrasena);          
            request.getSession().setAttribute("idC",aux.getIdCliente());
            request.getSession().setAttribute("nombres", calcularNombres(aux.getNombre(),aux.getApellido()));       
            request.getSession().setAttribute("tC",aux.getTarjetaCredito());
            int opcion=0;
            opcion=Integer.parseInt(request.getParameter("idAux"));
            //this.num=2;
            if(opcion!=-1){                        
                String variable="detalleProductoCliente.jsp?idPro="+opcion+"";
                RequestDispatcher rd=request.getRequestDispatcher(variable);
                rd.forward(request,response);
            }else{
                request.setAttribute("idConexion","5");//este id es para que la conexion se cierre en el perfil.jsp
                RequestDispatcher rd=request.getRequestDispatcher("perfil.jsp");                        
                rd.forward(request,response); 
            }            
        }catch(Exception e){
            switch(this.num){
                case 0:{
                    request.setAttribute("mensaje", "<p style='color:red'> El usuario no existe en nuestra web!!</p>");
                    System.out.println("Usuario no existe -> "+e);
                    RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
                    rd.forward(request,response);    
                    break;
                }
                case 1:{
                    System.out.println("contraseña incorrecta-> "+e);                                
                    request.setAttribute("mensaje", "<p style='font-size:20px'>"+this.Nm1+"</p><p style='color:red'> has ingresado una contraseña incorrecta!!</p>");                    
                    request.setAttribute("mensaje2", "<p><a href='#'>Olvidastes tu contraseña?</a></p>");
                    this.num=0;                                                   
                    String url="login.jsp?auxContra="+this.Cn+"&auxId="+this.idC+"&auxCorreo="+this.Correo+"&auxNombre="+this.Nm+"&auxApellido="+this.Ap;
                    System.out.println(url);
                    RequestDispatcher rd=request.getRequestDispatcher(url);
                    rd.forward(request,response);    
                    break;
                }
                case 2:{
                    request.setAttribute("mensaje", "<p style='color:dodgerblue'>:( Error interno del servidor rogamos nos disculpes estamos trabajando para solucionarlo</p>");
                    RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
                    rd.forward(request,response); 
                    break;
                }
            }                                                                                                               
        }
    }
    /*
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
        String usuario = request.getParameter("correo");
        String clave = request.getParameter("contrasena");
        PrintWriter io = response.getWriter();
        if(usuario==null){
            usuario="";}
        if(clave==null){
            clave="";}

        try{
            //Establecemos la conexion a la base de datos
            Conexion.ConexionJDBC con = new ConexionJDBC();
            con.Conectar();
            Operaciones.abrirConexion(con);
            //Operaciones.iniciarTransaccion();
            if(con.getConexion() == null){
                request.setAttribute("mensaje", "<p style='color:red'>No conectado a la base de datos</p>");
                request.setAttribute("error", 1);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else{
                HttpSession sesion = request.getSession();
                //consulto si ese usuario existe
                List <Clientes> lista=new ArrayList<Clientes>();
                lista=Operaciones.getTodos(new Clientes());
                loginValidacion login=new loginValidacion();
                login.setLista(lista);
                String co=Hash.generarHash(request.getParameter("contrasena"), Hash.SHA256);
                Clientes aux = login.Buscar(usuario,co);
                
                if(aux != null){                    
                    sesion.setAttribute("Usuario",aux.getIdCliente());
                    sesion.setAttribute("Nombre",aux.getNombre()+" "+aux.getApellido());
                    sesion.setAttribute("Rol", aux.getIdRol());
                    //AQUI YA PASO TODOS LOS FILTROS 
                    List<Menus> permisos = getPermisos(aux.getIdRol());
                    sesion.setAttribute("Permisos",permisos);
                    response.sendRedirect("Principal");                    
                }else{               
                    request.setAttribute("error", 2);
                    request.setAttribute("mensaje", "<p style='color:red'>Credenciales Incorrectas "+co+"</p>");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
            //Operaciones.commit();
        }catch(Exception ex){
            Operaciones.rollback();
            io.print(ex);
        }finally{
            try{
                Operaciones.cerrarConexion();
            }catch(SQLException ex){
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null ,ex);
            }
        }           
    }
    
    
    
    private List<Menus> getPermisos(Integer idrol) throws SQLException{
        List<Menus> permisos = new ArrayList();
        try{
            String sql = "select * from menus where idMenu in (select idMenu from permisos where idRol = ?)";
            List<Object> param = new ArrayList();
            param.add(idrol);
            String[][] rs = Operaciones.consultar(sql, param);
            for(int i=0; i<rs[0].length;i++){
                Menus m;
                m = new Menus(Integer.parseInt(rs[0][i]), rs[1][i], rs[2][i], rs[3][i], Integer.parseInt(rs[4][i]==null?"0":rs[4][i]));
                permisos.add(m);
            }
        }catch (Exception ex){
            Operaciones.rollback();
        }
        return permisos;
    }*/
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo(){
        return "Short description";
    }// </editor-fold>  
}
