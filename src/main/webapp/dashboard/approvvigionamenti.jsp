<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<jsp:useBean id="approvvigionamenti" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Approvvigionamento>"/>

<html>
<head>
    <title>WEBPDM: Dashboard</title>
    <link rel="stylesheet" type="text/css" href="../assets/styles/dashboard/approvvigionamenti.css">
    <script src="../assets/scripts/sortable.js"></script>
    <script>

        // Pulisce l'area di ricerca
        function pulisciRicerca() {
            document.getElementById("risultato").style.display = "none";
            document.getElementById("nomeApprovvigionamentoCercato").value = "";
        }

        // Cerca un approvvigionamento all'interno del database (per nome)
        function cercaApprovvigionamento(event) {

            var nomeApprovvigionamentoCercato = $("#nomeApprovvigionamentoCercato").val();
            console.log("Cerco " + nomeApprovvigionamentoCercato);

            $.ajax({
                type: "GET",
                url: "/approvvigionamenti/cerca/" + nomeApprovvigionamentoCercato,
                success: (data) => {

                    document.getElementById("risultato").style.display = "block";
                    document.getElementById("lista-risultati").innerHTML = '';

                    data.forEach((value) => {
                        let item = document.createElement("li");

                        let nome = document.createElement("p");
                        nome.innerHTML = value["nomeProdotto"] + ", " + value["quantita"] + "pz";
                        item.appendChild(nome);

                        document.getElementById("lista-risultati").appendChild(item);
                    });
                }
            });
        }

        // Modifica approvvigionamento esistente
        function modificaApprovvigionamento(id) {

            let datiApprovvigionamento = {
                nomeProdotto: $("#nomeProdotto-approvvigionamento-" + id).val(),
                quantita: $("#quantita-approvvigionamento-" + id).val(),
            };

            console.log("Oggetto: ", datiApprovvigionamento);

            $.ajax({
                type: "POST",
                url: "/approvvigionamenti/modifica/" + id,
                contentType: "application/json",
                data: JSON.stringify(datiApprovvigionamento),
                success: () => {
                    location.reload();
                },
            });
        }

        // Elimina approvvigionamento dal database
        function eliminaApprovvigionamento(id) {
            $.ajax({
                type: "DELETE",
                url: "/approvvigionamenti/elimina/" + id,
                success: () => {
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

    <h1>Gestione Approvvigionamenti</h1>

    <%--AREA DI RICERCA--%>
    <h2 class="title">Ricerca approvvigionamenti</h2>
    <div class="ricerca">
        <div class="search-area">
            <form id="ricerca-form" onsubmit="cercaApprovvigionamento(); return false;">
                <div class="search-box">
                    <input id="nomeApprovvigionamentoCercato" name="nomeApprovvigionamento" type="text"
                           placeholder="Approvvigionamento...">
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
    <h2 class="title">Lista Approvvigionamenti</h2>
    <div class="lista">
        <table class="sortable">
            <tr>
                <th>Nome</th>
                <th>Quantità</th>
                <th class="sorttable_nosort"></th>
                <th class="sorttable_nosort"></th>
            </tr>

            <c:forEach var="app" items="${approvvigionamenti}">
                <tr>
                    <th>
                        <input id="nomeProdotto-approvvigionamento-${app.id}" name="nomeApprovvigionamento"
                               type="text"
                               placeholder="Nome..."
                               value="${app.nomeProdotto}">
                    </th>
                    <th>
                        <input id="quantita-approvvigionamento-${app.id}" name="quantita" type="number"
                               placeholder="Nome..."
                               value="${app.quantita}">
                    </th>
                    <th>
                        <button class="modifica-approvvigionamento"
                                onclick="modificaApprovvigionamento('${app.id}')">
                            <i class="fa fa-save" style="color: white"></i>
                        </button>
                    </th>
                    <th>
                        <button class="elimina-approvvigionamento" onclick="eliminaApprovvigionamento('${app.id}')">
                            <i class="fa fa-trash" style="color: white"></i>
                        </button>
                    </th>

                </tr>
            </c:forEach>
            <% if (approvvigionamenti.isEmpty()) { %>
            <tr>
                <td colspan="4">Non ci sono approvvigionamenti</td>
            </tr>
            <% } %>
        </table>

    </div>
    <br>
    <div class="divider"></div>

    <%--AGGIUNTA PRODOTTI--%>
    <h2 class="title">Aggiungi Approvvigionamenti</h2>
    <div class="form-container">
        <form action="/approvvigionamenti/add" method="post">
            <div class="input-container">
                <label>Nome</label>
                <input name="nomeProdotto" type="text" placeholder="Nome Approvvigionamento..." required>
            </div>

            <div class="input-container">
                <label>Quantita</label>
                <input name="quantita" type="number" placeholder="Quantità..." required>
            </div>

            <input type="submit" value="Aggiungi">
        </form>

    </div>

    <div class="divider"></div>
</div>

</body>
</html>