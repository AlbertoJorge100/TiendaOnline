<%-- 
    Document   : confirmacion
    Created on : 21-nov-2019, 22:38:47
    Author     : Alberto
--%>

<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="Entidades.Productos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Angie´s Shop</title>
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
    </head>
    <body>
        <%!Date fecha=new Date();%>                
        <%!String variable;%>
        <%!String nombre;%>
        <%!String apellido;%>
        <%
            
            nombre=(String)request.getSession().getAttribute("nombres");
            
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
                                    <li><a href="perfil.jsp"><i class="fa fa-user"> </i> <%=nombre%></a></li>                                
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
        <section id="do_action">               
            <div class="container">                
                
                <div class="heading">
                    <h3>Gracias Por Preferirnos <%=nombre%></h3>
                    <p>Al presionar confirmar compra inmediatamente se te incluiran los cargos en tu tarjeta de credito </p>
                    ${mensaje}    
                </div>                                
                <%
                    Double sum=(Double)Double.parseDouble(request.getParameter("ttl"));
                    //casteo (Double) porque necesitamos un tipo de dato objeto
                    String url="pagos?total2="+sum;
                %>
                <%System.out.println("esto estoy mandando->"+sum);%>
                <form method="POST" action=<%=url%> class="row">
                    <%
                        String tC=null;
                        tC=(String)request.getSession().getAttribute("tC");
                        if(tC.equals("")){
                    %>
                        <div class="col-sm-12">
                            <div class="chose_area">						
                                <ul class="user_info">
                                    <li class="single_field zip-field">
                                        <label>Tarjeta Credito:</label>
                                        <input  type="text" name="tarjeta" placeholder="Ingresa tu numero de tarjeta sin guiones">
                                    </li>
                                    <li class="single_field zip-field">
                                        <label>CVV:</label>
                                        <input  type="password" name="CVV" placeholder="Ingresa tu CVV">
                                    </li>
                                </ul>
                            </div>
                        </div>
                    <%}%>
                    <div class="col-sm-9">
                        <div class="total_area">
                            <ul>
                                <li>subTotal $ <span><%=sum%></span></li>
                                <li>Impuestos <span>$0.0</span></li>
                                <li>Costo de Envio <span>Free</span></li>
                                <li >Total <span id="total"><%=sum%></span></li>
                            </ul>
                            
                            <button type="submit" class="btn btn-default update">
                                Confirmar Compra   
                            </button>   
                            <!--<a class="btn btn-default update" href="">Confirmar Compra</a>-->							
                        </div>                       
                    </div>
                </form>
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
    </body>
</html>
