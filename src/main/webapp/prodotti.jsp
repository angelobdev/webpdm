<%@ page import="exm.sisinf.webpdm.model.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="prodotti" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Prodotto>"/>
<html>
<head>
    <title>WEBPDM: Prodotti</title>
</head>
<body>
<jsp:include page="/nav"/>

<h1>Prodotti</h1>

${prodotti}

<ul>
    <% for (int i = 0; i < prodotti.size(); i++) { %>
    <li>
        <p>${prodotti.get(0).nome}</p>
    </li>
    <% } %>
</ul>


</body>
</html>