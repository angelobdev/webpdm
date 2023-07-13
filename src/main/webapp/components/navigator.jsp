<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="exm.sisinf.webpdm.model.Ruolo" %>
<jsp:useBean id="utente" scope="request" class="exm.sisinf.webpdm.model.Utente"/>

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
    <h2 class="logo-titolo">La Perla del Mediterraneo</h2>

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
                <%=utente.getNome()%>
            </button>
            <div class="dropdown-content">
                <a href="/content">Area Clienti</a>
                <% if (utente.getRuolo() != null && utente.getRuolo().getNome().equals(Ruolo.ERuolo.ROLE_ADMIN)) { %>
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


        </div>

        <%--Accesso--%>
        <% } else { %>
        <a href="/login" class="login-button">Login</a>
        <% } %>
    </div>
</nav>

<%--Script Carrello--%>
<script>
    let carrello = document.getElementById("carrello");

    function openCart() {
        carrello.style.visibility = "visible";
    }

    function closeCart() {
        carrello.style.visibility = "hidden";
    }
</script>