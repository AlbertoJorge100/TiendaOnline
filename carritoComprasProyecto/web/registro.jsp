<%-- 
    Document   : registro
    Created on : 08-nov-2019, 9:18:56
    Author     : Alberto
--%>

<%@page import="Utilerias.Url"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link rel="stylesheet" href="css/main.css">
        <title>Login</title>
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: Arial, Helvetica, sans-serif;
            }
            body{
                /* background-color:rgba(0,0,0,50); */
                /* background-color:rgba(12, 11, 11, 0.787); */
                /* background-image: url("https://previews.123rf.com/images/zven0/zven01509/zven0150900158/44906112-fondo-blanco-abstracto-con-l%C3%ADneas-suaves.jpg"); */
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
            }

            body *{
                width: 100%;
            }
            h1, h2,h3, p{
                margin-top: 20px;
                text-align: center;
            }
            h4{
                margin-top:20px;
                text-align: left;
                margin-left:200px;
            }
            p{
             
                height: 30px

            }
            .texto p{
                justify-content:center;
                text-align:justify;
                font-size:17;
            }
            form{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }
            form *{

                width: 300px;
                text-align: center;
                margin: 8px;
            }
            form input{
                width:325px;
                height: 38px;
                border-radius: 7px;
                border: 3px solid #e7e7e7;
                font-size: 16px;
            }
            .lista ul{
                text-align: center;
            }
            .lista ul li{
                padding: 5px;
            }
            button{                
                margin-top:10px;
                width:325px;
                height:45px;
                padding:10px;
                background-color:dodgerblue;
                color:white;
                text-decoration: none;
                font-weight: bold;
                font-size:18px;
                border-radius: 5px;
            }
            
            button:hover{
                opacity: 0.8;
            }
            /*------------------------------------------------------*/

        </style>
    </head>
    <body >
        <header id="header"><!--header-->
            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-3">

                            <div class="mainmenu pull-left">
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <li><a href="index.jsp"> Inicio </a></li>

                                </ul>
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <li><a href="login.jsp">Iniciar Sesion</a></li>

                                </ul>
                            </div>
                        </div>


                    </div>
                </div>
            </div><!--/header-middle-->	
        </header>
        
        <article >
            <h3>Registrate</h3>
            ${mensaje}
            <form method="POST" action="registrar">
                <input type="text"  name="nombre" id="nombre" placeholder="Nombres" autofocus>
                <input type="text" name="apellido" id="apellido" placeholder="Apellidos">
                <!-- <input type="text" name="" id="email" placeholder="Edad"> -->
                <input type="text" name="correo" id="correo" placeholder="Correo  ej: usuario@gmail.com">                
                <input type="password" name="contrasena" id="contrasena" placeholder="Contraseña">
                <input type="password" name="contrasena2" id="contrasena" placeholder="Repetir Contraseña">
                
                <div class="texto">
                    <p>Al crear una cuenta, aceptas nuestras<a href="#">Condiciones</a> 
                        de Uso y admites haber leído nuestro Aviso de <a href="#">Privacidad</a>.
                    </p>
                </div>
                <div class="texto">
                    ${mensaje2}
                </div>             
                <button type="submit">Registrar</button>
               
            </form>
        </article>

    </body>
</html>