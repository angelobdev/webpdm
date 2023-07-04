<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="exm.sisinf.webpdm.model.Ruolo" %>
<jsp:useBean id="utente" scope="request" class="exm.sisinf.webpdm.model.Utente"/>
<link rel="stylesheet" type="text/css" href="../assets/styles/main.css"/>
<script
        src="https://use.fontawesome.com/releases/v5.1.0/js/all.js"
        data-keep-original-source="false"
></script>
<nav>
    <img src="https://img.freepik.com/premium-vector/vector-graphic-abstract-fish-logo-design-template_600800-223.jpg" class="logo">
    <h2 class="logo-titolo">La Perla del Mediterraneo</h2>

    <% if (utente.getUsername() == null) { %>
    <!-- Nav Routes (public) -->
    <div class="nav-routes">
        <a href="/">Home</a>
        <a href="/azienda">Azienda</a>
        <a href="/prodotti">Prodotti</a>
        <a href="/contatti">Contatti</a>
    </div>
    <% } else { %>
    <!-- Nav Routes (utente) -->
    <div class="nav-routes">
        <a href="/">Home</a>
        <a href="/content">Area Clienti</a>
        <% if (utente.getRuolo() != null && utente.getRuolo().getNome().equals(Ruolo.ERuolo.ROLE_ADMIN)) { %>
        <a href="/dashboard">Dashboard</a>
        <% } %>
    </div>
    <% } %>

    <div class="nav-last">
        <% if (utente.getUsername() != null) { %>
        <p>Ciao, <%=utente.getUsername()%>
        </p>
        <a href="/logout">Logout</a>
        <% } else { %>
        <a href="/login" class="login-button">Login</a>
        <% } %>
    </div>
</nav>