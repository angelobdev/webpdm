<%@ page import="exm.sisinf.webpdm.model.Coupon" %>
<%@ page import="exm.sisinf.webpdm.model.Ruolo" %>
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

    <script>
        function salvaUtente() {

            let data = {
                partitaIVA: $("#utente-piva").val(),
                nomeAzienda: $("#utente-nomeAzienda").val(),
                sedeAziendale: $("#utente-sedeAziendale").val(),
                email: $("#utente-email").val(),
                username: $("#utente-username").val(),
                password: $("#utente-password").val(),
                avatar: $("#utente-avatar").val(),
            };

            $.ajax({
                type: "POST",
                url: "/utenti/modifica/" + ${utente.id},
                data: JSON.stringify(data),
                contentType: "application/json; charset=UTF-8;",
                success: (data) => {
                    console.log("OK");
                }
            });

        }
    </script>
</head>
<body>
<jsp:include page="/nav"/>

<div class="header">
    <div class="avatar">
        <% String avatar = utente.getAvatar().isEmpty() ? "https://cdn.reviewwave.com/site/img/avatars/no-profile-picture.png" : utente.getAvatar(); %>
        <img src="<%=avatar%>" alt="avatar">
    </div>
    <div>
        <h1>Area Clienti</h1>
        <h2>Ciao, ${utente.nomeAzienda}</h2>
    </div>
</div>

<div class="container">
    <%--    GESTIONE ACCOUNT--%>
    <div class="card gestione-account-card">
        <h2 class="title">Gestione Account</h2>
        <div class="content gestione-account">

            <div class="info-account-container">
                <h2 class="title">Dati aziendali</h2>

                <div class="info-account">
                    <label>Partita IVA</label>
                    <textarea name="piva" id="utente-piva">${utente.partitaIVA}</textarea>
                </div>

                <div class="info-account">
                    <label>Nome Azienda</label>
                    <textarea name="nomeAzienda" id="utente-nomeAzienda">${utente.nomeAzienda}</textarea>
                </div>

                <div class="info-account">
                    <label>Sede Aziendale</label>
                    <textarea name="sedeAziendale" id="utente-sedeAziendale">${utente.sedeAziendale}</textarea>
                </div>

                <div class="divider"></div>

                <h2 class="title">Informazioni Account</h2>

                <div class="info-account">
                    <label>E-Mail</label>
                    <textarea name="email" id="utente-email">${utente.email}</textarea>
                </div>

                <div class="info-account">
                    <label>Username</label>
                    <textarea name="username" id="utente-username" readonly>${utente.username}</textarea>
                </div>

                <div class="info-account">
                    <label>Password</label>
                    <textarea id="utente-password"
                              name="password">&#x2022;&#x2022;&#x2022;&#x2022;&#x2022;&#x2022;&#x2022;&#x2022;</textarea>
                </div>

                <div class="info-account">
                    <label>Avatar</label>
                    <textarea name="avatar" id="utente-avatar">${utente.avatar}</textarea>
                </div>

                <div class="divider"></div>

                <button onclick="salvaUtente()">Salva Modifiche</button>
            </div>
        </div>
    </div>

    <%--    COUPON--%>
    <div class="card">
        <h2 class="title">Coupon Disponibili</h2>
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
                <p></p>
            </div>

            <div class="info prezzo">
                <h4>Importo Minimo di Acquisto</h4>
                <p></p>
            </div>

            <div class="info">
                <h4>Data di Scadenza</h4>
                <p></p>
            </div>
            <% } %>
        </div>
    </div>


    <%--    STORICO ORDINI--%>
    <div class="card ordini-card">
        <h2 class="title">Storico Ordini</h2>
        <div class="content ordini">
            <ul>
                <c:forEach var="ordine" items="${ordini}">
                    <li>
                        <h4>Ordine N.${ordine.id + 1000} effettuato in data ${ordine.data.toLocaleString()}</h4>
                        <table>
                            <thead>
                            <th>Nome Prodotto</th>
                            <th>Quantita</th>
                            <th>Sub-Totale</th>
                            </thead>
                            <tbody>
                            <c:forEach var="cp" items="${ordine.carrello.carrelloProdotti}">
                                <tr>
                                    <td>${cp.prodotto.nome}</td>
                                    <td>${cp.quantita}kg</td>
                                    <td>${cp.prodotto.prezzoAlKg * cp.quantita}&euro;</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td>...</td>
                                <td>...</td>
                                <td>...</td>
                            </tr>
                            <tr>
                                <td><b>Totale:</b></td>
                                <td></td>
                                <td>${ordine.importoTotale}&euro;</td>
                            </tr>
                            </tbody>
                        </table>
                        <br>
                        <div class="divider"></div>
                    </li>
                </c:forEach>
            </ul>

        </div>
    </div>
    <%--    FORM RECLAMI--%>
    <div class="card">
        <h2 class="title">Crea Reclamo</h2>
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