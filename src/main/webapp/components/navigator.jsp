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
         class="logo">
    <h2 class="logo-titolo">La Perla del Mediterraneo</h2>

    <!--Routes-->
    <div class="nav-routes">
        <a href="/">Home</a>
        <a href="/azienda">Azienda</a>
        <a href="/prodotti">Prodotti</a>
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

        <%--Accesso--%>
        <% } else { %>
        <a href="/login" class="login-button">Login</a>
        <% } %>
    </div>
</nav>