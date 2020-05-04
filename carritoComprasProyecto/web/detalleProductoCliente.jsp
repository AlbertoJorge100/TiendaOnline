<%-- 
    Document   : detalleProductoCliente
    Created on : 18-nov-2019, 13:50:31
    Author     : Alberto
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="Entidades.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Product Details | E-Shopper</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">

    </head><!--/head-->

    <body>
        <%!Productos pro=new Productos();%>        
        <%!Date fecha=new Date();%>
        <%!String nombre;%>

        <%             
            List <Productos> lista=(List<Productos>)request.getSession().getAttribute("lst");                                
            nombre=(String)request.getSession().getAttribute("nombres");                     
            switch(Integer.parseInt(request.getParameter("idPro"))){
                case 0:{
                    pro=lista.get(0);
                    break;
                }
                case 1:{
                    pro=lista.get(1);
                    break;
                }
                case 2:{
                    pro=lista.get(2);
                    break;
                }
                case 3:{
                    pro=lista.get(3);
                    break;
                }
                case 4:{
                    pro=lista.get(4);
                    break;
                }
                case 5:{
                    pro=lista.get(5);
                    break;
                }
                case 6:{
                    pro=lista.get(6);
                    break;
                }
                case 7:{
                    pro=lista.get(7);
                    break;
                }
                case 8:{
                    pro=lista.get(8);
                    break;
                }
                case 9:{
                    pro=lista.get(9);
                    break;
                }
                case 10:{
                    pro=lista.get(10);
                    break;
                }
                case 11:{
                    pro=lista.get(11);
                    break;
                }
                default:{
                    
                    break;
                }
                
            }
        %>
        <header>
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
                                    <li class="dropdown"><a href="#" class="active"><i class="fa fa-shopping-cart"></i> Tienda<i class="fa fa-angle-down"></i></a>
                                        <ul role="menu" class="sub-menu">
                                            <li><a href="shop.html" class="active">Productos</a></li>
                                            <li><a href="product-details.html">Detalle Producto</a>
                                            <li><a href="addCarrito?idCerrar=10">Cerrar Sesion</a></li> 
                                        </ul>
                                    </li>
                                    <li><a href="#"><i class="fa fa-user"></i> <%=nombre%></a></li>

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
                                    <li><a href="cart.html"><i class="fa fa-shopping-cart"></i> ver Carrito</a></li>
                                    <li><a href="Principal?accion=logout"><i class="fa fa-lock"></i> Cerrar Sesion</a></li>
                                </ul>
                            </div>
                        </div>	
                    </div>
                </div>
            </div>
        </header><!--/header-->
            <br>
            <br>
            <br>
            <section>
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12 padding-right">
                            <div class="product-details"><!--product-details-->
                                <div class="col-sm-5">
                                    <div class="view-product">
                                        <img src=<%=pro.getFotoProducto()%> alt="" width:200px height:300px />

                                    </div>

                                </div>
                                <div class="col-sm-7">
                                    <div class="product-information"><!--/product-information-->
                                        <!--<img src= class="newarrival" width:200px height:300px alt="" />-->
                                        <h3><%=pro.getNombreProducto(2)%></h3>
                                        <p>Web ID: 1089772</p>

                                        <span>
                                            <span> $ <%=pro.getPrecio()%></span>
                                            <label>Cantidad:</label>
                                            <input type="submit" value="1" />

                                            <%!String variable;%>
                                            <%variable="addCarrito?idProducto="+pro.getIdProducto()+"";%>
                                            <a href=<%=variable%>>
                                                <button type="button" class="btn btn-fefault cart" >
                                                    <i class="fa fa-shopping-cart   "></i>
                                                    Añadir al Carrito
                                                </button>
                                            </a>
                                        </span>
                                        <p><b>Existencias: </b><%=pro.getExistencias()%></p>
                                        <p><b>Condicion: </b> Nuevo</p>
                                        <p><b>Garantia: </b> 3 Meses</p>
                                        <p><b>Costo de Envio: </b> Gratis</p>

                                        <!--<a href=""><img src=<%=pro.getFotoProducto()%> class="share img-responsive"  alt="" /></a>-->
                                    </div><!--/product-information-->
                                </div>
                            </div><!--/product-details-->

                            <div class="category-tab shop-details-tab">
                               <!--<div class="col-sm-12">
                                    <ul class="nav nav-tabs">
                                        <li><a href="#details" data-toggle="tab"></a></li>
                                        <li><a href="#companyprofile" data-toggle="tab"></a></li>
                                        <li><a href="index.jsp" data-toggle="tab">Seguir Comprando</a></li>
                                        <li class="active"><a href="index.jsp" data-toggle="tab">seguir comprando</a></li>
                                    </ul>
                                </div>-->
                                <div class="tab-content">
                                    <!--<div class="tab-pane fade" id="details" >
                                        <div class="col-sm-3">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="images/home/gallery1.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Easy Polo Black Edition</p>
                                                        <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>-->

                                    <div class="tab-pane fade active in" id="reviews" >
                                        <div class="col-sm-12">
                                            <ul>
                                                <li><a href=""><i class="fa fa-user"></i><%=nombre%></a></li>
                                                <li><a href=""><i class="fa fa-calendar-o"></i><%=new Date()%></a></li>
                                            </ul>
                                            <p><b>Informacion Adicional</b></p>
                                            <p><%=pro.getNombreProducto(2) + "  " +pro.getMarca() + "  " +pro.getExistencias()%> Nuevo en caja: Un artículo completamente nuevo, que no fue utilizado ni tiene desgaste (incluidos los hechos a mano) en su envase original (como la caja o la bolsa originales) y/o con las etiquetas originales.</p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
            <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>