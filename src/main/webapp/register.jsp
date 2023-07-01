<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>WEBPDM: Registrazione</title>
</head>
<body>
<jsp:include page="/nav"/>

<h1>Registrazione</h1>
<form action="#" method="post">
    <!-- Info Azienda -->
    <input name="piva" type="text" placeholder="Partita IVA...">
    <br>
    <input name="nome" type="text" placeholder="Nome (Ragione Sociale)...">
    <br>
    <input name="sede" type="text" placeholder="Sede...">
    <br>
    <!-- Info Accesso -->
    <input name="username" type="text" placeholder="Username...">
    <br>
    <input name="password" type="password" placeholder="Password...">
    <br>
    <input type="submit" value="Submit"/>
    <input type="reset" value="Reset"/>
</form>
</body>
</html>