<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario Login</title>
</head>
<body>

<h1>Iniciar Sesion</h1>

<form action="/44_Session/login" method="post">
    <div>
       <label for="username">Username</label>
       <div>
           <input type="text" name="username" id="username">
       </div>
    </div>

    <div>
        <label for="password">Password</label>
        <div>
            <input type="password" name="password" id="password">
        </div>
    </div>

    <div>
        <input type="submit" value="Login">
    </div>
</form>

</body>
</html>