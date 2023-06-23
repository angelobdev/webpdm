<!DOCTYPE html>
<html>
<head>
    <title>Ciao</title>
    <jsp:useBean id="welcome" scope="request" class="java.lang.String"/>
</head>
<body>
<h1>Ciao, sei gay!</h1>
<p>${welcome}</p>
</body>
</html>