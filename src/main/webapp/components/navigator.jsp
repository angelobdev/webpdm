<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="utente" scope="request" class="exm.sisinf.webpdm.model.Utente"/>
<jsp:useBean id="carrello" scope="request" class="exm.sisinf.webpdm.model.Carrello"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--CSS--%>
<link rel="stylesheet" type="text/css" href="../assets/styles/main.css"/>
<link rel="stylesheet" type="text/css" href="../assets/styles/navigator.css"/>

<%--FontAwesome5--%>
<script
        src="https://use.fontawesome.com/releases/v5.1.0/js/all.js"
        data-keep-original-source="false"
></script>

<%--Navigatore--%>
<nav>
    <%--Logo--%>
    <img src="https://img.freepik.com/premium-vector/vector-graphic-abstract-fish-logo-design-template_600800-223.jpg"
         class="logo" alt="logo">
    <a href="/"><h2 class="logo-titolo">La Perla del Mediterraneo</h2></a>

    <!--Routes-->
    <div class="nav-routes">
        <a href="/">Home</a>
        <a href="/catalogo">Catalogo</a>
        <a href="/ricette">Ricette</a>
        <a href="/contatti">Contatti</a>
    </div>

    <div class="nav-last">
        <%--Menu Utente--%>
        <% if (utente.getUsername() != null) { %>

        <div class="dropdown">
            <button class="dropbtn">
                <i class="fa fa-user"></i>
                &nbsp;
                <%=utente.getNomeAzienda()%>
            </button>
            <div class="dropdown-content">
                <a href="/areaclienti">Area Clienti</a>
                <% if (utente.getRuolo() != null && utente.getRuolo().getNome().equals("ROLE_ADMIN")) { %>
                <a href="/dashboard">Dashboard</a>
                <% } %>
                <a class="logout" href="/logout">Logout</a>
            </div>
        </div>

        <div class="cart-button">
            <button onclick="openCart()">
                <i class="fa fa-shopping-cart"></i>
            </button>
        </div>

        <div id="carrello">
            <%--Bottone chiusura--%>
            <button class="close" onclick="closeCart()">
                <i class="fa fa-times"></i>
            </button>

            <%--CARRELLO--%>
            <h2>Carrello</h2>

            <c:choose>
                <c:when test="${carrello == null}">
                    <p>Nessun carrello!</p>
                </c:when>
                <c:otherwise>
                    <p>Carrello presente:</p>
<%--                    ID: ${carrello.id}--%>
<%--                    ${carrello}--%>
                </c:otherwise>
            </c:choose>

<%--            <c:if test="${carrello == null}">--%>
<%--                <p>Nessun carrello!</p>--%>
<%--            </c:if>--%>
<%--            <c:if test="${carrello != null}">--%>
<%--                <p>Carrello presente!</p>--%>
<%--            </c:if>--%>

        </div>

        <%--Accesso--%>
        <% } else { %>
        <a href="/login" class="login-button">Login</a>
        <% } %>
    </div>
</nav>

<%--Script Carrello--%>
<script>
    let carrelloDiv = document.getElementById("carrello");

    function openCart() {
        carrelloDiv.style.visibility = "visible";
    }

    function closeCart() {
        carrelloDiv.style.visibility = "hidden";
    }
</script>