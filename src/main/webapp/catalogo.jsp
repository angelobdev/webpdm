<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="prodotti" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Prodotto>"/>
<html>
<head>
    <title>WEBPDM: Catalogo</title>
    <link rel="stylesheet" type="text/css" href="assets/styles/catalogo.css"/>

    <script>
        function aggiungiProdotto(prodottoID) {
            let quantita = $("#prodotto-quantita-" + prodottoID).val();
            aggiungiProdottoAlCarrello(prodottoID, quantita);
        }
    </script>
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

            <button class="buy" onclick="aggiungiProdotto('${p.id}')">Acquista Ora</button>
            <input id="prodotto-quantita-${p.id}" type="number" value="1">
        </li>
    </c:forEach>


</ul>

</body>
</html>