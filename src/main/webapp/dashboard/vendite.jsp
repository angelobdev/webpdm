<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="prodotti" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Prodotto>"/>
<html>
<head>
    <title>WEBPDM: Dashboard</title>
    <link rel="stylesheet" type="text/css" href="../assets/styles/dashboard/vendite.css">

    <script>
        function registraVendita() {
            let prodottiInputs = $("#prodotti-selezionati tr td input").toArray();

            let mappaProdotti = new Map();

            prodottiInputs.forEach((input) => {
                if (input.value !== '0') {
                    let inputID = input.getAttribute("id");
                    let prodottoID = inputID.charAt(inputID.length - 1);
                    mappaProdotti[prodottoID] = input.value;
                }
            });

            console.log(mappaProdotti);

            // let data = {
            //     data: mappaProdotti,
            // }
            // console.log(data);

            $.ajax({
                type: "POST",
                url: "/vendite/add",
                contentType: "application/json; charset=UTF-8;",
                data: JSON.stringify(mappaProdotti),
                success: (data) => {
                    console.log(data);
                    console.log("OK");
                }
            })

        }
    </script>
</head>
<body>
<jsp:include page="/adminnav"/>

<div class="dashboard-content">

    <h1>Gestione Vendite</h1>

    <%--Lista--%>
    <div class="container lista">
        <h2 class="title">Lista Vendite</h2>
        <table>
            <thead>
            <th>ID</th>
            <th>Data</th>
            <th>Importo</th>
            <th>Prodotti</th>
            </thead>
            <tbody>
                <%--TODO: implementare--%>
                <tr>
                    <td colspan="4">Nessuna Vendita Registrata!</td>
                </tr>
            </tbody>
        </table>
    </div>

    <%--Registrazione--%>
    <div class="container registrazione">
        <h2 class="title">Registra Vendita</h2>
        <table>
            <thead>
            <th>Prodotto</th>
            <th>Prezzo/KG</th>
            <th>Quantita Disponibile</th>
            <th>Selezione Quantita</th>
            </thead>
            <tbody id="prodotti-selezionati">
            <c:forEach var="prod" items="${prodotti}">
                <tr>
                    <td>${prod.nome}</td>
                    <td>${prod.prezzoAlKg}€</td>
                    <td>${prod.quantitaStoccata}kg</td>
                    <td><input id="quantita-${prod.id}" type="number" value="0" placeholder="Quantita"></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="buttons">
            <button onclick="registraVendita()">Registra Vendita</button>
        </div>
    </div>

</div>

</body>
</html>
