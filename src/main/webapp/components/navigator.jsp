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
        <a href="/"><i class="fa fa-ship"></i>Home</a>
        <a href="/chisiamo"><i class="fas fa-paper-plane"></i>Chi siamo</a>
        <a href="/dovesiamo"><i class="fa fa-map-signs"></i>Dove siamo</a>
        <a href="/contatti"><i class="fa fa-phone"></i>Contatti</a>
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
<video autoplay muted loop id="myVideo">
    <source src="../assets/media/background.mp4" type="video/mp4">
</video>