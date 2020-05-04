<%@page import="Entidades.Productos"%>
<%@page import="Utilerias.Url"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%//@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Angie´s Shop</title>
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
        <%!String variable;%>
        <%!List <Productos>lista =new ArrayList<Productos>();%>        
        <%
            Url url=new Url();
            //esta variable es para ver si viene la conexion al servidor abierta 
            String idConexion=null;
            idConexion=request.getParameter("idConexion");
            //si es nula entonces no hay conexion abierta
            if(idConexion==null){
                url.Listar(12,-1);
            }
            else{
                url.Listar(12,10);
            }            
            //lista=url.getLista();
            lista=url.getLista();
            int a=10;
            HttpSession sesion = request.getSession();
            sesion.removeAttribute("lst");
            request.getSession().setAttribute("lst",lista);
            String nombre=(String)request.getSession().getAttribute("nombres");  
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
                                    <li><a href="perfil.jsp"><i class="fa fa-user"> </i><%=nombre%></a></li>                                
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
        <section class="bienvenida" id="advertisement">
            <div class="container">
                ${mensaje}
            </div>
            
        </section>                                    
        <section id="advertisement">
            <div class="container">
                <img src="http://sheeve.com/wp-content/uploads/2015/05/ebay_banner_04.jpg" alt="" />
            </div>
        </section>

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-12 padding-right">
                        <div class="features_items"><!--features_items-->
                            <h2 class="title text-center">Nuestros Productos</h2>
                            <!-- -----------------------------------PRODUCTO 1 ------------------------------------------------------ -->
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">  
                                            <%variable="detalleProductoCliente.jsp?idPro=0";%>
                                            <a href=<%=variable%>><img src=<%=lista.get(0).getFotoProducto()%> alt="" /></a>                                                                          
                                            <h2>$<%=lista.get(0).getPrecio()%></h2>
                                            <p><%=lista.get(0).getNombreProducto(1)%></p>
                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><%%><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>                                        
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- -----------------------------------PRODUCTO 2 ------------------------------------------------------ -->
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <%variable="detalleProductoCliente.jsp?idPro=1";%>
                                            <a href=<%=variable%>><img src=<%=lista.get(1).getFotoProducto()%> alt="" /></a>
                                            <h2>$<%=lista.get(1).getPrecio()%></h2>
                                            <p><%= lista.get(1).getNombreProducto(1)%></p>                                        
                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><%%><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- -----------------------------------PRODUCTO 3 ------------------------------------------------------ -->
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                        <%variable="detalleProductoCliente.jsp?idPro=2";%>
                                        <a href=<%=variable%>><img src=<%=lista.get(2).getFotoProducto()%> alt="" /></a>
                                            <h2>$<%=lista.get(2).getPrecio()%></h2>
                                            <p><%= lista.get(2).getNombreProducto(1)%></p>                                        
                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- -----------------------------------PRODUCTO 4 ------------------------------------------------------ -->
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <%variable="detalleProductoCliente.jsp?idPro=3";%>
                                            <a href=<%=variable%>><img src=<%=lista.get(3).getFotoProducto()%> alt="" /></a>
                                            <h2>$<%=lista.get(3).getPrecio()%></h2>
                                            <p><%= lista.get(3).getNombreProducto(1)%></p>                                        
                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- -----------------------------------PRODUCTO 5 ------------------------------------------------------ -->
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <%variable="detalleProductoCliente.jsp?idPro=4";%>
                                            <a href=<%=variable%>><img src=<%=lista.get(4).getFotoProducto()%> alt="" /></a>
                                            <h2>$<%=lista.get(4).getPrecio()%></h2>
                                            <p><%= lista.get(4).getNombreProducto(1)%></p>

                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- -----------------------------------PRODUCTO 6 ------------------------------------------------------ -->
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <%variable="detalleProductoCliente.jsp?idPro=5";%>
                                            <a href=<%=variable%>><img src=<%=lista.get(5).getFotoProducto()%> alt="" /></a>
                                            <h2>$<%=lista.get(5).getPrecio()%></h2>
                                            <p><%= lista.get(5).getNombreProducto(1)%></p>                                        
                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>
                                        </div>
                                    </div>
                                </div>
                            </div>                
                            <!-- -----------------------------------PRODUCTO 7 ------------------------------------------------------ -->
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <%variable="detalleProductoCliente.jsp?idPro=6";%>
                                            <a href=<%=variable%>><img src=<%=lista.get(6).getFotoProducto()%> alt="" /></a>
                                            <h2>$<%=lista.get(6).getPrecio()%></h2>
                                            <p><%= lista.get(6).getNombreProducto(1)%></p>                                        
                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>
                                        </div>
                                    </div>
                                </div>
                            </div>    
                            <!-- -----------------------------------PRODUCTO 8 ------------------------------------------------------ -->
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <%variable="detalleProductoCliente.jsp?idPro=7";%>
                                            <a href=<%=variable%>><img src=<%=lista.get(7).getFotoProducto()%> alt="" /></a>
                                            <h2>$<%=lista.get(7).getPrecio()%></h2>
                                            <p><%= lista.get(7).getNombreProducto(1)%></p>                                            
                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>
                                        </div>
                                    </div>
                                </div>
                            </div>                
                            <!-- -----------------------------------PRODUCTO 9 ------------------------------------------------------ -->
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <%variable="detalleProductoCliente.jsp?idPro=8";%>
                                            <a href=<%=variable%>><img src=<%=lista.get(8).getFotoProducto()%> alt="" /></a>
                                            <h2>$<%=lista.get(8).getPrecio()%></h2>
                                            <p><%= lista.get(8).getNombreProducto(1)%></p>                                        
                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- -----------------------------------PRODUCTO 10 ------------------------------------------------------ -->
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <%variable="detalleProductoCliente.jsp?idPro=9";%>
                                            <a href=<%=variable%>><img src=<%=lista.get(9).getFotoProducto()%> alt="" /></a>
                                            <h2>$<%=lista.get(9).getPrecio()%></h2>
                                            <p><%= lista.get(9).getNombreProducto(1)%></p>                                        
                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- -----------------------------------PRODUCTO 11 ------------------------------------------------------ -->            
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <%variable="detalleProductoCliente.jsp?idPro=10";%>
                                            <a href=<%=variable%>><img src=<%=lista.get(10).getFotoProducto()%> alt="" /></a>
                                            <h2>$<%=lista.get(10).getPrecio()%></h2>
                                            <p><%= lista.get(10).getNombreProducto(1)%></p>                                        
                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- -----------------------------------PRODUCTO 12 ------------------------------------------------------ -->
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <%variable="detalleProductoCliente.jsp?idPro=11";%>
                                            <a href=<%=variable%>><img src=<%=lista.get(11).getFotoProducto()%> alt="" /></a>
                                            <h2>$<%=lista.get(11).getPrecio()%></h2>
                                            <p><%= lista.get(11).getNombreProducto(1)%></p>                                        
                                            <a href=<%=variable%> class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Añadir Al Carrito</a>
                                        </div>
                                    </div>
                                </div>
                            </div>                  
                            <!-- -----------------------------------FIN DE LOS PRODUCTOS ------------------------------------------------------ -->
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
    </body>
</html>