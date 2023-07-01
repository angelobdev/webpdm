<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<html>
<head>
    <title>WEBPDM: Dashboard</title>
</head>
<body>
<jsp:include page="/nav"/>

<h1>Area Amministrativa</h1>
<h3>Ciao ${username}</h3>
<a href="/logout">Logout</a>
</body>
</html>