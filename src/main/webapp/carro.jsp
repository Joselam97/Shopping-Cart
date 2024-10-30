<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de Compras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
        <h1>Carro de Compras</h1>

        <c:choose>

        <c:when test="${sessionScope.carro == null || sessionScope.carro.items.isEmpty()}">
        <div class="alert alert-warning">Lo sentimos no hay productos en el carro de compras!</div>
        </c:when>

        <c:otherwise>

        <form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="post">

            <table class="table table-hover table-striped">
                <tr>
                    <th>id</th>
                    <th>nombre</th>
                    <th>precio</th>
                    <th>cantidad</th>
                    <th>total</th>
                    <th>borrar</th>
                </tr>

                <c:forEach items="${carro.items}" var="item">
                <tr>
                    <td>${item.producto.id}</td>
                    <td>${item.producto.nombre}</td>
                    <td>${item.producto.precio}</td>
                    <td><input type="text" size="4" name="cant_${item.producto.id}" value="${item.cantidad}" /></td>
                    <td>${item.importe}</td>
                    <td><input type="checkbox" value="${item.producto.id}" name="deleteProductos" /></td>
                </tr>
                </c:forEach>
                <tr>
                    <td colspan="5" style="text-align: right">Total:</td>
                    <td>${carro.total}</td>
                </tr>
            </table>
            <a class="btn btn btn-primary" href="javascript:document.formcarro.submit();">Actualizar</a>
        </form>

        </c:otherwise>
        </c:choose>

        <div class="my-2">
             <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}/index.html">volver</a>
             <a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/productos">seguir comprando</a>
        </div>
</div>
</body>
</html>