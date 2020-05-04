<%-- 
    Document   : login
    Created on : 11-nov-2019, 23:44:14
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

        <title>Login</title>
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: Arial, Helvetica, sans-serif;
            }
            body{

                 /*background-color:rgba(246, 246, 246, 0.600); 
                  background-image: url("https://cdn.pixabay.com/photo/2017/01/31/17/55/waves-2025984_960_720.png"); */
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
            }

            body *{
                width: 100%;
            }
            h1,h2, h3, p{
                margin-top: 20px;
                text-align: center;
                text-decoration:none;
            }
            p{
                height: 30px;
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
                width:400px;
                height: 45px;
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
                width:400px;
                height: 45px;
                display: block;
                padding: 10px;
                background-color: dodgerblue;
                color: white;
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
        <script>

            var ad=getElementById(request.getParameter("contra"));
            if(ad == "falso"){
                alert("Contraseña Incorrecta!!");
            }
        </script>
    </head>
    <body >
        <header id="header"><!--header-->
            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="mainmenu pull-left">
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <li><a href="index.jsp">Inicio</a></li>

                                </ul>
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <li><a href="registro.jsp"></i>Registrate</a></li>

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->	
        </header>


    
    <article>
        <h2>Iniciar Sesion </h2>        
        ${mensaje}
        <%
            String idContra=null;            
            String idPro=null;            
            idPro=request.getParameter("idProx");   
            idContra=request.getParameter("auxContra");
            String variable="";
            if(idPro!=null){
                variable="Login?idAux="+idPro+"";
            }else{
                variable="Login?idAux=-1";
            }
            if(idContra!=null){                                           
                variable+="&auxContra="+idContra+"&auxId="+request.getParameter("auxId")+"&auxCorreo="
                        +request.getParameter("auxCorreo")
                        +"&auxNombre="+request.getParameter("auxNombre")
                        +"&auxApellido="+request.getParameter("auxApellido");                
            }%>            
                            
        
        <form action=<%=variable%> method="POST">
            <input type="text" name="correo" id="usuario" placeholder="Ingrese Su Correo Electronico" autofocus>
            <input type="password" name="contrasena" id="email" placeholder="Ingrese Su Contraseña">
            <button type="submit">Ingresar</button>
            ${mensaje2}
        </form>
    </article>

    </body>
</html>