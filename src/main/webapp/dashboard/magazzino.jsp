<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="exm.sisinf.webpdm.model.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<jsp:useBean id="prodotti" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Prodotto>"/>
<html>
<head>
    <title>WEBPDM: Dashboard</title>
    <link rel="stylesheet" type="text/css" href="../assets/styles/dashboard/magazzino.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="../assets/scripts/sortable.js"></script>
</head>
<body>
<jsp:include page="/adminnav"/>

<div class="dashboard-content">

    <h1>Magazzino</h1>

    <div class="divider"></div>

    <h2>Lista Prodotti</h2>
    <div class="lista">
        <table class="sortable">
            <tr>
                <th>Nome</th>
                <th>Prezzo al KG</th>
                <th>Data Arrivo</th>
                <th>Quantità</th>
            </tr>
            <% for (Prodotto p : prodotti) { %>
            <tr>
                <th><%=p.getNome()%>
                </th>
                <th><%=p.getPrezzoKg()%>
                </th>
                <th><%=p.getDataArrivo().toLocaleString()%>
                </th>
                <th><%=p.getQuantita()%>
                </th>
            </tr>
            <% } %>
        </table>

    </div>

    <div class="divider"></div>

    <h2>Aggiungi un prodotto</h2>
    <div class="aggiunta-prodotti">
        <p>Aggiunta</p>
    </div>

</div>

</body>
</html>