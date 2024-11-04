<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title> <!-- Título dinámico de la página que se establece desde el servidor -->

        <!-- Enlace a la hoja de estilos de Bootstrap desde CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">

        <!-- Script de Bootstrap para funcionalidad JavaScript, como el menú colapsable -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
    </head>
    <body>

        <!-- Barra de navegación principal de la aplicación -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid"> <!-- Contenedor con fluidez para mantener el contenido centrado -->

                <!-- Logo o nombre de la aplicación en la barra de navegación -->
                <a style="color: blue;" class="navbar-brand" href="#">Carrito de Compras</a>

                <!-- Botón de menú colapsable para pantallas pequeñas -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- Contenido colapsable de la barra de navegación -->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0"> <!-- Lista de navegación alineada a la izquierda -->

                        <!-- Enlace a la página de inicio -->
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/index.jsp">Home</a>
                        </li>

                        <!-- Enlace a la sección de usuarios -->
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/usuarios">Usuarios</a>
                        </li>

                        <!-- Enlace a la sección de productos -->
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/productos">Productos</a>
                        </li>

                        <!-- Enlace para ver el carrito de compras con el número de ítems -->
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/carro/ver">Ver carro (${carro.items.size()})</a>
                        </li>

                        <!-- Menú desplegable de cuenta de usuario -->
                        <li class="nav-item dropdown">

                            <!-- Muestra el nombre del usuario si está autenticado; de lo contrario, muestra "Cuenta" -->
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                ${not empty sessionScope.username ? sessionScope.username : "Cuenta"}
                            </a>

                            <!-- Opciones del menú desplegable -->
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li>

                                    <!-- Enlace para iniciar o cerrar sesión, según el estado de la autenticación -->
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/${not empty sessionScope.username ? "logout" : "login"}">
                                        ${not empty sessionScope.username ? "Logout" : "Login"}
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Contenedor principal de Bootstrap para centrar y alinear contenido de la página -->
        <div class="container">
