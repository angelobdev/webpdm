<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<html>
<head>
    <title>WEDPDM: Area Clienti</title>
</head>
<body>
<jsp:include page="/nav"/>


<h1>Area Utente</h1>
<h3>Ciao ${username}</h3>
<a href="/logout">Logout</a>
</body>
</html>