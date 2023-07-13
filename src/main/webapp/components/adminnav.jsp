<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="utente" scope="request" class="exm.sisinf.webpdm.model.Utente"/>

<%--CSS--%>
<link rel="stylesheet" type="text/css" href="../assets/styles/dashboard/main.css"/>

<%--FontAwesome5--%>
<script
        src="https://use.fontawesome.com/releases/v5.1.0/js/all.js"
        data-keep-original-source="false"
></script>

<jsp:include page="/nav"/>

<div class="admin-nav">
    <h1>Dashboard</h1>
    <div class="divider"></div>
    <a href="/dashboard/magazzino">Gestione Magazzino</a>
    <a href="/dashboard/dipendenti">Gestione Dipendenti</a>
    <a href="/dashboard/ordini">Gestione Ordini</a>
    <a href="/dashboard/acquisti">Gestione Acquisti</a>
    <a href="/dashboard/reclami">Gestione Reclami</a>

    <div class="spacer"></div>
    <div class="divider"></div>
    <p class="footer">Powered by F.A.M Group ©</p>
</div>