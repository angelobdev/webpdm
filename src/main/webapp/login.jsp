<jsp:useBean id="error" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>WEBPDM: Login</title>
    <link rel="stylesheet" type="text/css" href="assets/styles/login.css">
</head>
<body>
<jsp:include page="/nav"/>

<% if (error != null && !error.isEmpty()) { %>
<p>Errore: ${error}</p>
<% } %>

<div class="login-container">
    <div class="header">
        <i class="fa fa-anchor" style="font-size: 2rem"></i>
        <h1 class="title">LOGIN</h1>
    </div>
    <form class="form-login" action="#" method="post">
        <div>
            <div class="icon-holder">
                <i class="fa fa-user"></i>
            </div>
            <input name="username" type="text" placeholder="Username" required>
        </div>
        <div>
            <div class="icon-holder">
                <i class="fa fa-lock"></i>
            </div>
            <input name="password" type="password" placeholder="Password" required>
        </div>
        <section class="help">
            <p><input type="checkbox"> Ricordami</p>
        </section>
        <button type="submit" class="button"><i class="fa fa-fish"></i>Login</button>
        <a href="#">Password dimenticata?</a>
        <div class="divider"></div>
        <div class="register">
            <p>Non sei registrato?</p><a href="/register">Registrati!</a>
        </div>
    </form>

</div>
</body>
</html>