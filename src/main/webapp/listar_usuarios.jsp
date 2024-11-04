<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/header.jsp" />

<h3>${title}: Listado de Usuarios</h3>

<c:if test="${username.present}">
    <a class="btn btn-primary my-2" href="${pageContext.request.contextPath}/usuarios/form_usuarios">Crear Usuario [+]</a>
</c:if>

<table class="table table-hover table-striped">
    <tr>
        <th>id</th>
        <th>username</th>
        <th>email</th>
        <c:if test="${username.present}">
            <th>editar</th>
            <th>eliminar</th>
        </c:if>
    </tr>
    <c:forEach items="${usuarios}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.email}</td>
            <c:if test="${username.present}">
                <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/usuarios/form_usuarios?id=${u.id}">Editar</a></td>
                <td><a class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro que desea eliminar?');"
                       href="${pageContext.request.contextPath}/usuarios/eliminar?id=${u.id}">Eliminar</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>

<jsp:include page="layout/footer.jsp" />