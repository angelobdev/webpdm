<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="exm.sisinf.webpdm.model.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="prodotti" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Prodotto>"/>
<html>
<head>
    <title>WEBPDM: Catalogo</title>
    <link rel="stylesheet" type="text/css" href="assets/styles/catalogo.css"/>
</head>
<body>
<jsp:include page="/nav"/>

<div class="background"></div>

<h1>Catalogo</h1>

<%--LISTA--%>
<ul class="products">
    <c:forEach var="p" items="${prodotti}">
        <li class="container">
            <img src="${p.immagine}">

            <h1>${p.nome}</h1>

            <p class="desc">${p.descrizione}</p>

            <p class="prezzo">${p.prezzoAlKg}€/kg</p>
            <p class="quantita">Disponibilità: ${p.quantitaStoccata}pz</p>

            <button class="buy">Acquista Ora</button>
        </li>
    </c:forEach>


</ul>

</body>
</html>