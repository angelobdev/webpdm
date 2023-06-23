<%@ page import="java.util.Date" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Ciao</title>
    <jsp:useBean id="title" scope="request" type="java.lang.String"/>
</head>
<body>
<h1>Benvenuto/a su ${title}</h1>
<h1>Ciao, sei gay!</h1>

<% for(int x = 0; x < 10; x++) {%>
    <a>Ciao</a>
<% } %>
<br>
<%=new Date().toString()%>

</body>
</html>