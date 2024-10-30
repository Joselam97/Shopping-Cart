<jsp:include page="layout/header.jsp" />

<h3>${title}</h3>

<ul class="list-group">
    <li class="list-group-item active">Menu de opciones</li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/productos">mostrar productos html</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/login.html">Login</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/carro/ver">Ver Carro</a></li>
</ul>

<jsp:include page="layout/footer.jsp" />