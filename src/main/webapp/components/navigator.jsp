<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="exm.sisinf.webpdm.model.Ruolo" %>
<jsp:useBean id="utente" scope="request" class="exm.sisinf.webpdm.model.Utente"/>
<link rel="stylesheet" type="text/css" href="../assets/styles/main.css"/>
<script
        src="https://use.fontawesome.com/releases/v5.1.0/js/all.js"
        data-keep-original-source="false"
></script>
<nav>
    <img src="../assets/media/logo.png" class="logo">

    <% if (utente.getUsername() == null) { %>
    <!-- Nav Routes (public) -->
    <div class="nav-routes">
        <a href="/"><p><i class="fa fa-ship"></i></p>Home</a>
        <a href="/chisiamo"><p><i class="fas fa-paper-plane"></i></p>Chi siamo</a>
        <a href="/dovesiamo"><p><i class="fa fa-map-signs"></i></p>Dove siamo</a>
        <a href="/contatti"><p><i class="fa fa-phone"></i></p>Contatti</a>
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