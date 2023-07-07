<jsp:useBean id="error" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>WEBPDM: Registrazione</title>
    <link rel="stylesheet" type="text/css" href="assets/styles/register.css">
</head>
<body>
<jsp:include page="/nav"/>

<% if (error != null && !error.isEmpty()) { %>
<p>Errore: ${error}</p>
<% } %>

<video autoplay muted loop id="myVideo">
    <source src="assets/media/background.mp4" type="video/mp4">
</video>

<div class="register-container">
    <div class="header">
        <i class="fa fa-anchor" style="color:white;font-size: 2rem"></i>
        <h1 class="title">Registrazione</h1>
    </div>
    <form class="form-register" action="#" method="post">

        <!-- PIVA -->
        <div>
            <div class="icon-holder">
                <i class="fa fa-briefcase"></i>
            </div>
            <input name="piva" type="text" placeholder="Partita IVA" required>
        </div>

        <!-- Ragione Sociale -->
        <div>
            <div class="icon-holder">
                <i class="fa fa-file-signature"></i>
            </div>
            <input name="nome" type="text" placeholder="Ragione Sociale" required>
        </div>

        <!-- Sede -->
        <div>
            <div class="icon-holder">
                <i class="fa fa-map-pin"></i>
            </div>
            <input name="sede" type="text" placeholder="Sede" required>
        </div>

        <!-- Username -->
        <div>
            <div class="icon-holder">
                <i class="fa fa-user"></i>
            </div>
            <input name="username" type="text" placeholder="Username" required>
        </div>

        <!-- Password -->
        <div>
            <div class="icon-holder">
                <i class="fa fa-lock"></i>
            </div>
            <input name="password" type="password" placeholder="Password" required>
        </div>

        <br>

        <button type="submit" class="button"><i class="fa fa-fish" style="font-size: 1.3rem; color:white;"></i>&nbsp;REGISTRATI</button>

        <div class="divider"></div>

        <div class="register">
            <p>Sei già registrato? <a href="/login">Torna al Login!</a></p>
        </div>
    </form>

</div>
</body>
</html>