<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>WEBPDM: Index</title>
    <jsp:useBean id="title" scope="request" type="java.lang.String"/>
</head>
<body>
<jsp:include page="/nav"/>

<h1>Benvenuto/a su ${title}</h1>

</body>
</html>