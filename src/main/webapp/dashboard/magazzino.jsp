<%@ taglib prefix="http" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<jsp:useBean id="prodotti" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Prodotto>"/>
<html>
<head>
    <title>WEBPDM: Dashboard</title>
    <link rel="stylesheet" type="text/css" href="../assets/styles/dashboard/magazzino.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="../assets/scripts/sortable.js"></script>
    <script>
        // delete the user from the database
        function eliminaProdotto(deleteURL) {
            $.ajax({
                type: "DELETE",
                url: deleteURL,
                success: (data) => {
                    location.reload();
                }
            });
            location.reload();
        }
    </script>
</head>
<body>
<jsp:include page="/adminnav"/>

<div class="dashboard-content">

    <h1>Gestione Magazzino</h1>
    <div class="divider"></div>

    <h2 class="title">Lista Prodotti</h2>
    <div class="lista">
        <table class="sortable">
            <tr>
                <th>Nome</th>
                <th>Prezzo al KG</th>
                <th>Data Arrivo</th>
                <th>Quantità</th>
                <th></th>
            </tr>
            <c:forEach var="p" items="${prodotti}">
                <tr>
                    <th>${p.nome}
                    </th>
                    <th>${p.prezzoAlKg}
                    </th>
                    <th>${p.dataArrivo}
                    </th>
                    <th>${p.quantitaInMagazzino}
                    </th>
                    <th>
                        <spring:url value="/prodotti/delete/${p.id}" var="deleteUrl"/>
                        <button class="delete-prodotto" onclick="eliminaProdotto('${deleteUrl}')">
                            <i class="fa fa-trash" style="color: white"></i>
                        </button>
                    </th>
                </tr>
            </c:forEach>
        </table>

    </div>
    <br>
    <div class="divider"></div>


    <h2 class="title">Aggiungi Prodotti</h2>

    <div class="form-container">

        <form action="/prodotti/add" method="post">

            <div class="input-container">
                <label>Nome</label>
                <input name="nome" type="text" placeholder="Nome prodotto" required>
            </div>

            <div class="input-container">
                <label>Prezzo al KG</label>
                <input name="prezzoKg" type="number" placeholder="€/KG" required>
            </div>

            <div class="input-container">
                <label>Data di Arrivo</label>
                <input name="dataArrivo" type="date" placeholder="Data di arrivo" required>
            </div>

            <div class="input-container">
                <label>Quantita</label>
                <input name="quantita" type="number" placeholder="Quantità" required>
            </div>

            <div class="input-container">
                <label>Descrizione</label>
                <input name="descrizione" type="text" size="512" placeholder="Descrizione prodotto" class="wide"
                       required>
            </div>

            <div class="input-container">
                <label>Link Immagine</label>
                <input name="immagine" type="text" size="512" placeholder="Link Immagine" class="wide" required>
            </div>

            <input type="submit" value="Aggiungi">

        </form>

    </div>

    <br>
    <div class="divider"></div>


</div>

</body>
</html>