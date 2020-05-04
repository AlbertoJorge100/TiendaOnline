<%-- 
    Document   : historial
    Created on : 20-dic-2019, 15:52:27
    Author     : alberto_angie
--%>


<%@page import="Conexion.ConexionJDBC"%>
<%@page import="Utilerias.Listar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Date"%>
<%@page import="Entidades.Clientes"%>
<%@page import="Utilerias.Url"%>
<%@page import="Conexion.conexionSQlite"%>
<%@page import="Entidades.Productos"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Angie´s Store</title>
        <link href="/your-path-to-fontawesome/css/fontawesome.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">        
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    </head><!--/head-->

    <body>
        <%/*!Date fecha=new Date();%>
        
        <%!Clientes c1=new Clientes();%>        
        <%!String variable;%>    
        <%!String nombre;%>
        <%!String apellido;%>
        <%             
            /*nombre=(String)request.getSession().getAttribute("nom");
            apellido=(String)request.getSession().getAttribute("ape");            
            */
        %>
        <header id="header"><!--header-->

            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>
                            <div class="mainmenu pull-left">
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <li><a href="perfil.jsp"><i class="fa fa-home"></i> Inicio</a></li>
                                    <li><a href="perfil.jsp"><i class="fa fa-user"> </i> <%=nombre+" "+apellido%></a></li>                                
                                    <li class="dropdown"><a href="#" class="active">Tienda<i class="fa fa-angle-down"></i></a>
                                        <ul role="menu" class="sub-menu">
                                            <li><a href="shop.html" class="active">Productos</a></li>
                                            <li><a href="product-details.html">Detalle Producto</a></li> 
                                            <li><a href="carrito.jsp">ver Carrito Compras</a></li> 
                                            <li><a href="login.html">Cerrar Sesion</a></li> 
                                        </ul>
                                    </li>                                
                                </ul>
                            </div>
                        </div>
                        <form class=" col-sm-5">
                            <div class="search_box pull-right">
                                <input type="text" placeholder="Buscar"/>
                                <a class="boton" type="submit" method="POST" action="#" href="https://www.google.com">Busqueda</a>	
                            </div>
                        </form>
                        <div class="col-sm-3">
                            <div class="shop-menu pull-right">
                                <ul class="nav navbar-nav"> 
                                    <li><a href="carrito.jsp"><i class="fa fa-shopping-cart"></i> ver Carrito</a></li>
                                    <li><a href="Principal?accion=logout"><i class="fa fa-lock"></i> Cerrar Sesion</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->	
        </header>
        <br>
        <br>
        <br>
        <section id="cart_items">
            <div class="container">
                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>                                          
                            <tr class="cart_menu">
                                <td class="image">Articulos</td>
                                <td class="description"> Nombre de Producto</td>							
                                <td class="quantity">Cantidad</td>                                                        
                                <td class="total">Costo</td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                            //ArrayList<Productos> lista=(ArrayList<Productos>)request.getSession().getAttribute("lista");
                            //for(int i=0; i< lista.size();i++){%>
                                <tr>                                                                                                        
                                    
                                    <td class="cart_description">                                                            
                                        
                                        <p>Web ID: 1089772</p>
                                    </td>                                                        							
                                    <td class="cart_quantity">
                                        <div class="cart_quantity_button">									
                                            <input class="cart_quantity_input" type="text" name="quantity" value="1" autocomplete="off" size="2">									
                                        </div>
                                    </td>
                                    <td class="cart_total">
                                        
                                    </td>
                                    <td class="cart_delete">
                                        
                                    </td>
                                </tr>
                            <%//}%>                            
                        </tbody>                        
                    </table>                    
                </div>                 
                <a href="perfil.jsp" class="btn btn-default add-to-cart" margin-right="10px">Seguir Comprando</a>
                <a href="confirmacion.jsp?id=10" class="btn btn-default add-to-cart"> Pagos $</a>
                
            </div>
        </section> 
        <footer class="">
            <div class="pie_pagina">
                <a href="#"><i class="fab fa-2x"></i> Nuestras Redes Sociales</a>
                <a href="#"><i class="fa fa-facebook"></i>Angie's Store</a>
                <a href="#"><i class="fa fa-twitter"></i> @angieStore19</a>
                <a href="#"><i class="fa fa-google-plus"></i> 11Angie's_Store</a>
                <a href="#"><i class="fa fa-instagram"></i> angie's_Store19</a>
                <a href="#"><i class="fa fa-youtube"></i> Angie's Store El Salvador</a>
                <a href="#"><i class="fab fa-gmail"></i> angieConsulta@AngieStore.com</a>
                <hr>
                <p>angie's Store &copy; Derechos Reservados</p>
            </div>
        </footer>

        <script src="js/jquery.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>