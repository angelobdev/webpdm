<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="utente" scope="request" class="exm.sisinf.webpdm.model.Utente"/>

<%--CSS--%>
<link rel="stylesheet" type="text/css" href="../assets/styles/main.css"/>
<link rel="stylesheet" type="text/css" href="../assets/styles/navigator.css"/>

<%--FontAwesome5--%>
<script
        src="https://use.fontawesome.com/releases/v5.1.0/js/all.js"
        data-keep-original-source="false"
></script>

<%--jQuery--%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<%-- Gestione carrello --%>
<% if (utente != null) { %>
<script>
    // Aggiunge un prodotto al carrello
    async function aggiungiProdottoAlCarrello(prdid, qta) {

        let data = {
            "prodottoID": prdid,
            "quantita": qta,
        }

        console.log(JSON.stringify(data));

        $.ajax({
            type: "POST",
            url: "/carrello/aggiungi/" + ${utente.id},
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify(data),
            async: true,
            success: () => {
                console.log("SUCCESS");
            },
            error: () => {
                console.error("ERROR");
            }

        });
    }

    // Ritorna il carrello dell'utente;
    async function getCarrelloUtente() {
        $.ajax({
            type: "GET",
            url: "/carrello/" + ${utente.id},
            dataType: "json",
            success: (data) => {

                if (data == []) {
                    $("#carrello-content").html("<p>Nessun Carrello Presente!</p>");
                } else {
                    $("#carrello-content").html("<div><b>Carrello presente!</b><p>ID: " + data["id"] + "</p><p>Utente: " + data["utente"]["username"] + "</p><p>Data Creazione: " + data["data_creazione"] + "</p></div>");
                }


            }
        });
    }

    getCarrelloUtente();
</script>
<% } %>

<%--Navigatore--%>
<nav>
    <%--Logo--%>
    <img src="https://img.freepik.com/premium-vector/vector-graphic-abstract-fish-logo-design-template_600800-223.jpg"
         class="logo" alt="logo">
    <a href="/"><h2 class="logo-titolo">La Perla del Mediterraneo</h2></a>

    <%--Routes--%>
    <div class="nav-routes">
        <a href="/">Home</a>
        <a href="/catalogo">Catalogo</a>
        <a href="/ricette">Ricette</a>
        <a href="/contatti">Contatti</a>
    </div>

    <div class="nav-last">
        <%--Menu Utente--%>
        <% if (utente.getUsername() != null) { %>

        <div class="dropdown">
            <button class="dropbtn">
                <i class="fa fa-user"></i>
                &nbsp;
                <%=utente.getNomeAzienda()%>
            </button>
            <div class="dropdown-content">
                <a href="/areaclienti">Area Clienti</a>
                <% if (utente.getRuolo().getGrado() > 99) { %>
                <a href="/dashboard">Dashboard</a>
                <% } %>
                <a class="logout" href="/logout">Logout</a>
            </div>
        </div>

        <div class="cart-button">
            <button onclick="openCart()">
                <i class="fa fa-shopping-cart"></i>
            </button>
        </div>

        <%--CARRELLO--%>
        <div id="carrello">
            <%--Bottone chiusura--%>
            <button class="close" onclick="closeCart()">
                <i class="fa fa-times"></i>
            </button>

            <h2>Carrello</h2>

            <div id="carrello-content">
                <p>Ciao</p>
            </div>


        </div>

        <%--Menu Accesso--%>
        <% } else { %>
        <a href="/login" class="login-button">Login</a>
        <% } %>
    </div>
</nav>

<%--Script Carrello--%>
<script>
    let carrelloDiv = document.getElementById("carrello");

    function openCart() {
        carrelloDiv.style.visibility = "visible";
    }

    function closeCart() {
        carrelloDiv.style.visibility = "hidden";
    }
</script>