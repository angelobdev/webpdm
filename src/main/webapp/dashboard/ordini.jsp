<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="ordini" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Ordine>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WEBPDM: Dashboard</title>
    <link rel="stylesheet" type="text/css" href="../assets/styles/dashboard/ordini.css">
    <script>
        function eliminaOrdine(id) {
            $.ajax({
                type: "DELETE",
                url: "/ordini/elimina/" + id,
                success: () => {
                    console.log("OK");
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="/adminnav"/>

<div class="dashboard-content">

    <h1>Gestione Ordini</h1>
    <div class="divider"></div>

    <div class="container">
        <h2 class="title">Lista Ordini</h2>
        <table>
            <thead>
            <th>ID</th>
            <th>Data</th>
            <th>Importo</th>
            <th>Stato</th>
            <th></th>
            </thead>
            <tbody>
            <c:forEach var="ord" items="${ordini}">
                <tr>
                    <td>#${ord.id + 1000}</td>
                    <td>${ord.data}</td>
                    <td>${ord.importoTotale}</td>
                    <td>${ord.statoOridine}</td>
                    <td>
                        <button onclick="eliminaOrdine('${ord.id}')"><i class="fa fa-trash"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            <%if (ordini.isEmpty()) {%>
            <tr>
                <td colspan="5">Non ci sono ordini</td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>

</div>

</body>
</html>
