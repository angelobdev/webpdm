<%@ page import="exm.sisinf.webpdm.model.Coupon" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="utente" scope="request" type="exm.sisinf.webpdm.model.Utente"/>
<jsp:useBean id="ordini" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Ordine>"/>
<jsp:useBean id="couponJson" scope="request" type="java.lang.String"/>

<%
    List<Coupon> coupon = new ObjectMapper().readValue(couponJson, new TypeReference<List<Coupon>>() {
    });
%>

<html>
<head>
    <title>WEDPDM: Area Clienti</title>
    <link rel="stylesheet" type="text/css" href="assets/styles/areaclienti.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        function selezionaCoupon() {
            var couponSelector = document.getElementById("coupon-selector");
            var codiceSconto = couponSelector.options[couponSelector.selectedIndex].text;
            var coupon = getCouponByCodiceSconto(codiceSconto);

            // Aggiorna dinamicamente le informazioni del coupon
            $(".info h4:eq(0)").text("Sconto Applicato");
            $(".info p:eq(0)").text(coupon["scontoApplicato"].toFixed(2));
            $(".info h4:eq(1)").text("Importo Minimo di Acquisto");
            $(".info p:eq(1)").text(coupon["prezzoMinimo"].toFixed(2));
            $(".info h4:eq(2)").text("Data di Scadenza");
            $(".info p:eq(2)").text(new Date(coupon["dataScadenza"]).toLocaleDateString("it-IT"));
        }

        function getCouponByCodiceSconto(codiceSconto) {
            var couponList = ${couponJson};

            for (var i = 0; i < couponList.length; i++) {
                if (couponList[i]["codiceSconto"] === codiceSconto) {
                    return couponList[i];
                }
            }

            return null;
        }
    </script>
</head>
<body>
<jsp:include page="/nav"/>

<h1>Area Clienti</h1>
<h2>Bentornato/a, ${utente.username}</h2>
<div class="divider"></div>

<div class="container">
    <%--    GESTIONE ACCOUNT--%>
    <div class="card gestione-account-card">
        <h2 class="title">Gestione Account</h2>
        <div class="divider"></div>
        <div class="content gestione-account">

            <form action="#">
                <h2 class="title">Dati aziendali</h2>

                <div class="info-account">
                    <label>Partita IVA</label>
                    <textarea name="piva">${utente.partitaIVA}</textarea>
                </div>

                <div class="info-account">
                    <label>Nome Azienda</label>
                    <textarea name="nomeAzienda">${utente.nomeAzienda}</textarea>
                </div>

                <div class="info-account">
                    <label>Sede Aziendale</label>
                    <textarea name="sedeAziendale">${utente.sedeAziendale}</textarea>
                </div>

                <div class="divider"></div>

                <h2 class="title">Informazioni Account</h2>

                <div class="info-account">
                    <label>E-Mail</label>
                    <textarea name="email">${utente.email}</textarea>
                </div>

                <div class="info-account">
                    <label>Username</label>
                    <textarea name="username">${utente.username}</textarea>
                </div>

                <div class="info-account">
                    <label>Password</label>
                    <textarea
                            name="password">&#x2022;&#x2022;&#x2022;&#x2022;&#x2022;&#x2022;&#x2022;&#x2022;</textarea>
                </div>

                <div class="info-account">
                    <label>Avatar</label>
                    <textarea name="avatar">${utente.avatar}</textarea>
                </div>

                <div class="divider"></div>

                <input type="submit" value="Salva Informazioni">
            </form>
        </div>
    </div>

    <%--    COUPON--%>
    <div class="card">
        <h2 class="title">Coupon Disponibili</h2>
        <div class="divider"></div>
        <div class="content coupon">
            <% if (coupon.isEmpty()) {%>
            <p>Nessun coupon disponibile!</p>
            <% } else { %>
            <div class="selection">
                <h3>Seleziona Coupon</h3>
                <label>Codice Sconto</label>
                <select id="coupon-selector" onchange="selezionaCoupon()">
                    <% for (var cpn : coupon) { %>
                    <option><%=cpn.getCodiceSconto()%>
                    </option>
                    <%}%>
                </select>
            </div>

            <div class="divider"></div>

            <h3>Informazioni Coupon</h3>

            <div class="info prezzo">
                <h4>Sconto Applicato</h4>
                <p>${coupon.get(0).scontoApplicato}</p>
            </div>

            <div class="info prezzo">
                <h4>Importo Minimo di Acquisto</h4>
                <p>${coupon.get(0).prezzoMinimo}</p>
            </div>

            <div class="info">
                <h4>Data di Scadenza</h4>
                <p>${coupon.get(0).dataScadenza.toLocaleString()}</p>
            </div>
            <% } %>
        </div>
    </div>


    <%--    STORICO ORDINI--%>
    <div class="card ordini-card">
        <h2 class="title">Storico Ordini</h2>
        <div class="divider"></div>
        <div class="content ordini">

            ${ordini}

            <%--TODO: QUERY--%>
            <div class="ordine">
                <h4><i class="fa fa-caret-down"></i>&nbsp;Ordine #1001</h4>
                <ul class="lista-prodotti">
                    <li>prodotto 1</li>
                    <li>prodotto 2</li>
                </ul>
            </div>

            <p>Acquisti: ${acquisti}</p>

        </div>
    </div>
    <%--    FORM RECLAMI--%>
    <div class="card">
        <h2 class="title">Crea Reclamo</h2>
        <div class="divider"></div>
        <div class="content reclamo">
            <form action="#">
                <div class="input-container">
                    <label>Ordine</label>
                    <select name="ordine">
                        <option>Ordine 1</option>
                        <option>Ordine 2</option>
                    </select>
                </div>
                <div class="input-container">
                    <label>Segnalazione</label>
                    <textarea name="segnalazione" placeholder="Messaggio..."></textarea>
                </div>
                <div class="input-container">
                    <label>Data</label>
                    <input name="data" type="date" placeholder="Date..." id="data-reclamo" disabled>
                </div>
                <input type="submit" value="Invia Reclamo"> <%--QUA--%>
            </form>
        </div>
    </div>
</div>

<script>
    document.getElementById('data-reclamo').valueAsDate = new Date();
    selezionaCoupon();
</script>

</body>
</html>