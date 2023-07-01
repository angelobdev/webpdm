<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<link rel="stylesheet" type="text/css" href="../assets/styles/main.css"/>
<nav>
    <p>WEBPDM</p>
    <div class="nav-routes">
        <a href="/">Home</a>
        <a href="content">AREA_UTENTE</a>
        <a href="dashboard">AREA_ADMIN</a>
    </div>
    <div class="nav-last">
        <% if (!username.isEmpty()) {%>
        <p>Ciao, ${username}</p>
        <a href="/logout">Logout</a>
        <% } else { %>
        <a href="/login">Login</a>
        <p>o</p>
        <a href="/register">Registrati</a>
        <% } %>
    </div>
</nav>