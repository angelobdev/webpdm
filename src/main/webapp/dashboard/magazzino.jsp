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

        function modificaProdotto(id) {
            let form = $("#modifica-prodotto-form-" + id);

            console.log("Modifico " + id);

            let datiProdotto = {
                nome: form.find('input[name="nome"]').val(),
                prezzoAlKg: form.find('input[name="prezzoAlKg"]').val(),
                dataArrivo: form.find('input[name="dataArrivo"]').val(),
                quantitaStoccata: form.find('input[name="quantitaStoccata"]').val(),
                descrizione: form.find('textarea[name="descrizione"]').val(),
                immagine: form.find('textarea[name="immagine"]').val()
            };

            console.log("Oggetto: ", datiProdotto);

            $.ajax({
                type: "POST",
                url: "/prodotti/modifica/" + id,
                contentType: "application/json",
                data: JSON.stringify(datiProdotto),
                success: (data) => {
                    console.log("CIAO");
                },
                error: (xhr) => {
                    console.log("error");
                }
            });
        }


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

        function pulisciRicerca() {
            document.getElementById("risultato").style.display = "none";
        }

        function cercaProdotto(event) {

            var nomeProdottoCercato = $("#nomeProdottoCercato").val();
            console.log("Cerco " + nomeProdottoCercato);

            $.ajax({
                type: "GET",
                url: "/prodotti/cerca/" + nomeProdottoCercato,
                success: (data) => {
                    console.log(data);
                    document.getElementById("risultato").style.display = "block";

                    document.getElementById("lista-risultati").innerHTML = '';
                    data.forEach((value) => {

                        let item = document.createElement("li");

                        let nome = document.createElement("p");
                        nome.innerHTML = value["nome"] + ", " + value["prezzoAlKg"] + "€/kg : " + value["quantitaStoccata"] + "pz";
                        item.appendChild(nome);

                        document.getElementById("lista-risultati").appendChild(item);

                    })
                },
                error: (xhr, ajaxOptions, thrownError) => {
                    if (xhr.status === 404) {
                        console.log("Non trovato");
                        document.getElementById("risultato").style.display = "block";
                        document.getElementById("lista-risultati").innerHTML = "Nessun risultato!";
                    }
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="/adminnav"/>

<div class="dashboard-content">

    <h1>Gestione Magazzino</h1>

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
                    <form action="" id="modifica-prodotto-form-${p.id}">
                        <th>
                            <input name="nome" type="text" placeholder="Nome..." value="${p.nome}">
                        </th>
                        <th>
                            <input name="prezzoAlKg" type="number" placeholder="Nome..." value="${p.prezzoAlKg}">
                        </th>
                        <th>
                            <input name="dataArrivo" type="datetime-local" placeholder="Nome..."
                                   value="${p.dataArrivo}">
                        </th>
                        <th>
                            <input name="quantitaStoccata" type="number" placeholder="Nome..."
                                   value="${p.quantitaStoccata}">
                        </th>
                        <th>
                            <textarea name="descrizione">${p.descrizione}</textarea>
                        </th>
                        <th>
                            <textarea name="immagine">${p.immagine}</textarea>
                        </th>
                        <th>
                            <button class="modifica-prodotto" type="submit"
                                    onclick="modificaProdotto('${p.id}'); return false;">
                                <i class="fa fa-trash" style="color: white"></i>
                            </button>
                        </th>
                        <th>
                            <spring:url value="/prodotti/delete/${p.id}" var="deleteUrl"/>
                            <button class="delete-prodotto" onclick="eliminaProdotto('${deleteUrl}')">
                                <i class="fa fa-trash" style="color: white"></i>
                            </button>
                        </th>
                    </form>
                </tr>
            </c:forEach>
            <% if (prodotti.isEmpty()) { %>
            <tr>
                <td colspan="5">Non ci sono prodotti</td>
            </tr>
            <% } %>
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