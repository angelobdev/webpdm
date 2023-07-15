<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<html>
<head>
    <title>WEDPDM: Area Clienti</title>
    <link rel="stylesheet" type="text/css" href="assets/styles/areaclienti.css"/>
</head>
<body>
<jsp:include page="/nav"/>

<h1>Area Clienti</h1>
<h2>Bentornato/a, ${username}</h2>
<div class="divider"></div>

<div class="container">
    <%--    GESTIONE ACCOUNT--%>
    <div class="card gestione-account-card">
        <h3 class="title">Gestione Account</h3>
        <div class="divider"></div>
        <div class="content">
            <p>Contenuto</p>
            <div class="gestione_account">
                <h1> Nome e Cognome
                    <button>rinomina account</button>
                </h1>
                <h1> Partita Iva
                    <button> modifica Partita Iva</button>
                </h1>
                <h1> Password e Sicurezza
                    <button> modifica Parametri di Sicurezza</button>
                </h1>

            </div>
        </div>
    </div>


    <%--    STORICO ORDINI--%>
    <div class="card">
        <h3 class="title">Storico Ordini</h3>
        <div class="divider"></div>
        <div class="content ordini">

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
        <h3 class="title">Crea Reclamo</h3>
        <div class="divider"></div>
        <div class="content reclamo">
            <form action="#">
                <div class="input-container">
                    <label>E-Mail</label>
                    <input name="email :" type="email" placeholder="e-Mail...">
                </div>
                <div class="input-container">
                    <label>Segnalazione</label>
                    <input name="segnalazione :" type="text" placeholder="Messaggio...">
                </div>
                <div class="input-container">
                    <label>Data</label>
                    <input name="data : " type="date" placeholder="Date..." disabled>
                </div>
                <input type="submit" value="Invia Reclamo"> <%--QUA--%>
            </form>
        </div>
    </div>
    <%--    COUPON--%>
    <div class="card">
        <h3 class="title">Coupon Disponibili</h3>
        <div class="divider"></div>
        <div class="content">
            <p>Contenuto</p>
        </div>
    </div>
</div>

<script>

</script>

</body>
</html>