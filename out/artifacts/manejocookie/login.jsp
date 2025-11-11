<%--
  Created by IntelliJ IDEA.
  User: cynth
  Date: 10/11/2025
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Usuario</title>
</head>
<body>
<h1>INICIO DE SESIÓN</h1>
<form action="${pageContext.request.contextPath}/login" method="post">
    <div>
        <label for="user">Usuario:</label>
        <input type="text" id="user" name="user" required>
    </div>
    <div>
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div>
        <input type="submit" value="INICIAR SESIÓN">
    </div>
</form>
</body>
</html>
