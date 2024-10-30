<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Formulario Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>

<body>

<div class="container">

<h1>Formulario Producto</h1>

<form action="${pageContext.request.contextPath}/productos/form" method="post">
    <div class="row mb-2">
        <label for="nombre" class="col-form-label col-sm-2">Nombre</label>

        <div class="col-sm-4">
            <input type="text" name="nombre" id="nombre" value="${producto.nombre}" class="form-control">
        </div>
        <c:if test="${errores != null && errores.containsKey('nombre')}">
            <div style="color:red;">${errores.nombre}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="precio" class="col-form-label col-sm-2">Precio</label>
        <div class="col-sm-4">
            <input type="number" name="precio" id="precio" value="${producto.precio > 0? producto.precio: ""}" class="form-control">
        </div>
        <c:if test="${errores != null && not empty errores.precio}">
             <div style="color:red;">${errores.precio}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="sku" class="col-form-label col-sm-2">Sku</label>
        <div class="col-sm-4">
            <input type="text" name="sku" id="sku" value="${producto.sku}" class="form-control">
        </div>
        <c:if test="${errores != null && not empty errores.sku}">
             <div style="color:red;">${errores.sku}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="fecha_registro" class="col-form-label col-sm-2">Fecha Registro</label>
        <div class="col-sm-4">
            <input class="form-control" type="date" name="fecha_registro" id="fecha_registro" value="${producto.fechaRegistro != null? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): ""}">
        </div>
        <c:if test="${errores != null && not empty errores.categoria}">
             <div style="color:red;">${errores.categoria}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="categoria" class="col-form-label col-sm-2"></label>
        <div class="col-sm-4">
            <select name="categoria" id="categoria" class="form-select">
                <option value="">---- seleccionar ----</option>
                <c:forEach items="${categorias}" var="c">
                <option value="${c.id}" ${c.id.equals(producto.categoria.id)? "selected": ""}>${c.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <c:if test="${errores != null && not empty errores.fecha_registro}">
             <div style="color:red;">${errores.fecha_registro}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <div>
             <input class="btn btn-primary" type="submit" value="${producto.id != null && producto.id>0? "Editar": "Crear"}">
        </div>
    </div>

    <input type="hidden" name="id" value="${producto.id}">

</form>
</div>
</body>
</html>