<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>

<div class="container">

<h1>Listado de Productos</h1>

<c:if test="${username.present}">
    <div class="alert alert-info">Hola ${username.get()}, bienvenido!</div>
    <a class="btn btn-primary my-2" href="${pageContext.request.contextPath}/productos/form">crear [+]</a>
</c:if>

<table class="table table-hover table-striped">

    <tr>
        <th>id</th>
        <th>nombre</th>
        <th>tipo</th>
        <c:if test="${username.present}">
        <th>precio</th>
        <th>agregar</th>
        <th>editar</th>
        <th>eliminar</th>
        </c:if>
    </tr>

    <c:forEach items="${productos}" var="p">

    <tr>
        <td>${p.id}</td>
        <td>${p.nombre}</td>
        <td>${p.categoria.nombre}</td>

        <c:if test="${username.isPresent()}">
        <td>${p.precio}</td>
        <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}">Agregar al carro</a></td>
        <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/productos/form?id=${p.id}">Editar</a></td>
        <td><a class="btn btn-sm btn-danger" onclick="return confirm('esta seguro que desea eliminar?');"
        href="${pageContext.request.contextPath}/productos/eliminar?id="${p.id}">Eliminar</a></td>
        </c:if>
    </tr>
    </c:forEach>

</table>
<p>${applicationScope.mensaje}</p>
<p>${requestScope.mensaje}</p>

</div>

</body>
</html>