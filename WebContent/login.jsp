<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" href="/img/icon.ico" />

<!-- CSS -->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/style-login.css" rel="stylesheet">

<title>PS3C</title>

</head>
<body>

<div class="container">
  
  <div class="row" id="pwd-container">
    <div class="col-md-4"></div>
    
    <div class="col-md-4">
      <section class="login-form">
        <form method="post" action="#" role="login">
          <img src="/img/icon.ico" class="img-responsive" alt=":)" />  
          <input type="text" name="email" placeholder="Usuario" required class="form-control input-lg" value="" />
          
          <input type="password" class="form-control input-lg" id="password" placeholder="Contraseña" required="" />
          
          
          <div class="pwstrength_viewport_progress"></div>
          
          
          <button type="submit" name="go" class="btn btn-lg btn-primary btn-block">Ingresar</button>
          <div>
            <a href="#">Crear cuenta</a>  <!--  or <a href="#">reset password</a>   -->
          </div>
          
        </form>
      
      </section>  
      </div>
      
      <div class="col-md-4"></div>

  </div>
  
  
</div>

<!-- Scripts -->
<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
<!-- Scripts -->

</body>

</html>