<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<jsp:useBean id="prodotti" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Prodotto>"/>

<html>
<head>
    <title>WEBPDM: Dashboard</title>
    <link rel="stylesheet" type="text/css" href="../assets/styles/dashboard/magazzino.css">
    <script src="../assets/scripts/sortable.js"></script>
    <script>

        // Pulisce l'area di ricerca
        function pulisciRicerca() {
            document.getElementById("risultato").style.display = "none";
            document.getElementById("nomeProdottoCercato").value = "";
        }

        // Cerca un prodotto all'interno del database (per nome)
        function cercaProdotto(event) {

            var nomeProdottoCercato = $("#nomeProdottoCercato").val();
            console.log("Cerco " + nomeProdottoCercato);

            $.ajax({
                type: "GET",
                url: "/prodotti/cerca/" + nomeProdottoCercato,
                success: (data) => {

                    document.getElementById("risultato").style.display = "block";
                    document.getElementById("lista-risultati").innerHTML = '';

                    data.forEach((value) => {
                        let item = document.createElement("li");

                        let nome = document.createElement("p");
                        nome.innerHTML = value["nome"] + ", " + value["prezzoAlKg"] + "€/kg : " + value["quantitaStoccata"] + "pz";
                        item.appendChild(nome);

                        document.getElementById("lista-risultati").appendChild(item);
                    });
                }
            });
        }

        // Modifica prodotto esistente
        function modificaProdotto(id) {

            let datiProdotto = {
                nome: $("#nome-prodotto-" + id).val(),
                prezzoAlKg: $("#prezzoKg-prodotto-" + id).val(),
                dataArrivo: $("#dataArrivo-prodotto-" + id).val(),
                quantitaStoccata: $("#quantitaStoccata-prodotto-" + id).val(),
                descrizione: $("#descrizione-prodotto-" + id).val(),
                immagine: $("#immagine-prodotto-" + id).val()
            };

            console.log("Oggetto: ", datiProdotto);

            $.ajax({
                type: "POST",
                url: "/prodotti/modifica/" + id,
                contentType: "application/json",
                data: JSON.stringify(datiProdotto),
                success: () => {
                    location.reload();
                },
            });
        }

        // Elimina prodotto dal database
        function eliminaProdotto(id) {
            $.ajax({
                type: "DELETE",
                url: "/prodotti/delete/" + id,
                success: () => {
                    location.reload();
                }
            });
        }

    </script>
</head>
<body>
<jsp:include page="/adminnav"/>

<div class="dashboard-content">

    <h1>Gestione Magazzino</h1>

    <%--AREA DI RICERCA--%>
    <h2 class="title">Ricerca prodotti</h2>
    <div class="ricerca">
        <div class="search-area">
            <form id="ricerca-form" onsubmit="cercaProdotto(); return false;">
                <div class="search-box">
                    <input id="nomeProdottoCercato" name="nomeProdotto" type="text" placeholder="Prodotto...">
                    <button type="submit"><i class="fa fa-search"></i></button>
                    <button onclick="pulisciRicerca(); return false;"><i class="fa fa-broom"></i></button>
                </div>
            </form>
        </div>
        <div class="search-result">
            <div id="risultato" style="display: none">
                <ul id="lista-risultati">
                </ul>
            </div>
        </div>
    </div>
    <div class="divider"></div>

    <%--LISTA (+ Modifica) Prodotti--%>
    <h2 class="title">Lista Prodotti</h2>
    <div class="lista">
        <table class="sortable">
            <tr>
                <th>Nome</th>
                <th>€/kg</th>
                <th>Data Arrivo</th>
                <th>Quantità</th>
                <th>Descrizione</th>
                <th>Immagine</th>
                <th class="sorttable_nosort"></th>
                <th class="sorttable_nosort"></th>
            </tr>
            <c:forEach var="p" items="${prodotti}">
                <tr>
                    <th>
                        <input id="nome-prodotto-${p.id}" name="nome" type="text" placeholder="Nome..."
                               value="${p.nome}">
                    </th>
                    <th>
                        <input id="prezzoKg-prodotto-${p.id}" name="prezzoAlKg" type="number" placeholder="Nome..."
                               value="${p.prezzoAlKg}">
                    </th>
                    <th>
                        <input id="dataArrivo-prodotto-${p.id}" name="dataArrivo" type="datetime-local"
                               placeholder="Nome..."
                               value="${p.dataArrivo}">
                    </th>
                    <th>
                        <input id="quantitaStoccata-prodotto-${p.id}" name="quantitaStoccata" type="number"
                               placeholder="Nome..."
                               value="${p.quantitaStoccata}">
                    </th>
                    <th>
                        <textarea id="descrizione-prodotto-${p.id}" name="descrizione">${p.descrizione}</textarea>
                    </th>
                    <th>
                        <textarea id="immagine-prodotto-${p.id}" name="immagine">${p.immagine}</textarea>
                    </th>
                    <th>
                        <button class="modifica-prodotto" onclick="modificaProdotto('${p.id}')">
                            <i class="fa fa-save" style="color: white"></i>
                        </button>
                    </th>
                    <th>
                        <button class="delete-prodotto" onclick="eliminaProdotto('${p.id}')">
                            <i class="fa fa-trash" style="color: white"></i>
                        </button>
                    </th>

                </tr>
            </c:forEach>
            <% if (prodotti.isEmpty()) { %>
            <tr>
                <td colspan="8">Non ci sono prodotti</td>
            </tr>
            <% } %>
        </table>

    </div>
    <br>
    <div class="divider"></div>

    <%--AGGIUNTA PRODOTTI--%>
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
                <textarea name="descrizione" placeholder="Descrizione prodotto" required>
                </textarea>
            </div>

            <div class="input-container">
                <label>Link Immagine</label>
                <textarea name="immagine" placeholder="Link Immagine" required>
                </textarea>
            </div>

            <input type="submit" value="Aggiungi">
        </form>

    </div>

    <div class="divider"></div>
</div>

</body>
</html>