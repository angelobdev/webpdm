<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="prodotti" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Prodotto>"/>
<html>
<head>
    <title>WEBPDM: Catalogo</title>
    <link rel="stylesheet" type="text/css" href="assets/styles/catalogo.css"/>

    <script>
        function aggiungiProdotto(prodottoID) {
            let quantitaInput = $("#prodotto-quantita-" + prodottoID)

            let quantitaMax = quantitaInput.attr('max');
            let quantitaValue = quantitaInput.val();

            if (quantitaValue <= quantitaMax) {
                aggiungiProdottoAlCarrello(prodottoID, quantitaValue);
            } else {
                alert("Non puoi comprare " + quantitaValue + "kg di questo prodotto!");
            }

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
            <p class="quantita">Disponibilità: ${p.quantitaStoccata}kg</p>

            <input id="prodotto-quantita-${p.id}" type="number" value="1" min="1" max="${p.quantitaStoccata}">
            <button class="buy" onclick="aggiungiProdotto('${p.id}')">Aggiungi al Carrello</button>
        </li>
    </c:forEach>


</ul>

</body>
</html>