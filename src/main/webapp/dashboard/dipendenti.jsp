<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<jsp:useBean id="dipendenti" scope="request" type="java.util.List<exm.sisinf.webpdm.model.Dipendente>"/>
<jsp:useBean id="bustepaga" scope="request" type="java.util.List<exm.sisinf.webpdm.model.BustaPaga>"/>

<html>
<head>
    <title>WEBPDM: Dashboard</title>
    <link rel="stylesheet" type="text/css" href="../assets/styles/dashboard/dipendenti.css">
    <script src="../assets/scripts/sortable.js"></script>
    <script>
        function modificaDipendente(id) {

            let data = {
                codiceFiscale: $("#codiceFiscale-dipendente-" + id).val(),
                mansioni: $("#mansioni-dipendente-" + id).val(),
            };

            $.ajax({
                type: "POST",
                url: "/dipendenti/modifica/" + id,
                contentType: "application/json",
                data: JSON.stringify(data),
                success: () => {
                    location.reload();
                },
            });
        }

        function eliminaDipendente(id) {
            $.ajax({
                type: "DELETE",
                url: "/dipendenti/elimina/" + id,
                success: () => {
                    location.reload();
                }
            });
        }

        function eliminaBustaPaga(id) {
            $.ajax({
                type: "DELETE",
                url: "/bustepaga/elimina/" + id,
                success: () => {
                    location.reload();
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="/adminnav"/>

<div class="dashboard-content">

    <h1>Gestione Dipendenti</h1>
    <div class="divider"></div>

    <%--LISTA (+ Modifica) Dipendenti--%>
    <h2 class="title">Lista Dipendenti</h2>
    <div class="lista">
        <table class="sortable">
            <tr>
                <th>Codice Fiscale</th>
                <th>Mansioni</th>
                <th class="sorttable_nosort"></th>
                <th class="sorttable_nosort"></th>
            </tr>

            <c:forEach var="dip" items="${dipendenti}">
                <tr>
                    <th>
                        <input id="codiceFiscale-dipendente-${dip.id}" name="codiceFiscaleDipendente"
                               type="text"
                               placeholder="Codice Fiscale..."
                               value="${dip.codiceFiscale}">
                    </th>
                    <th>
                        <textarea id="mansioni-dipendente-${dip.id}" name="mansioni"
                                  placeholder="Mansioni...">${dip.mansioni}</textarea>
                    </th>
                    <th>
                        <button class="modifica-dipendente"
                                onclick="modificaDipendente('${dip.id}')">
                            <i class="fa fa-save" style="color: white"></i>
                        </button>
                    </th>
                    <th>
                        <button class="elimina-dipendente" onclick="eliminaDipendente('${dip.id}')">
                            <i class="fa fa-trash" style="color: white"></i>
                        </button>
                    </th>

                </tr>
            </c:forEach>
            <% if (dipendenti.isEmpty()) { %>
            <tr>
                <td colspan="4">Non ci sono dipendenti</td>
            </tr>
            <% } %>
        </table>

    </div>
    <br>
    <div class="divider"></div>


    <%--AGGIUNTA DIPENDENTI--%>
    <h2 class="title">Aggiungi Dipendenti</h2>
    <div class="form-container">
        <form action="/dipendenti/add" method="post">
            <div class="input-container">
                <label>Codice Fiscale</label>
                <input name="codiceFiscale" type="text" placeholder="Codice Fiscale..." minlength="16" maxlength="16"
                       required>
            </div>

            <div class="input-container">
                <label>Quantita</label>
                <textarea name="mansioni" placeholder="Mansioni..." required></textarea>
            </div>

            <input type="submit" value="Aggiungi">
        </form>
    </div>
    <br>
    <%-- ****** BUSTE PAGA ****** --%>

    <h1>Gestione Buste Paga</h1>
    <div class="divider"></div>

    <%--LISTA Buste Paga--%>
    <h2 class="title">Lista Buste Paga</h2>
    <div class="lista">
        <table class="sortable">
            <tr>
                <th>Importo Emesso</th>
                <th>Data Emissione</th>
                <th>Dipendente CF</th>
                <th class="sorttable_nosort"></th>
            </tr>

            <c:forEach var="bp" items="${bustepaga}">
                <tr>
                    <th>
                        <input id="importoEmesso-bustapaga-${bp.id}" name="importoEmessoBustaPaga"
                               type="text"
                               placeholder="Importo Emesso..."
                               value="${bp.importoEmesso}" readonly>
                    </th>
                    <th>
                        <input id="dataEmissione-bustapaga-${bp.id}" name="dataEmissioneBustaPaga"
                               type="datetime-local"
                               value="${bp.dataEmissione}" readonly>
                    </th>
                    <th>
                        <input id="dipendente-bustapaga-${bp.id}" name="dipendenteBustaPaga"
                               type="text"
                               placeholder="Dipendente..."
                               value="${bp.dipendente.codiceFiscale}" readonly>
                    </th>
                    <th>
                        <button class="elimina-bustapaga" onclick="eliminaBustaPaga('${bp.id}')">
                            <i class="fa fa-trash" style="color: white"></i>
                        </button>
                    </th>

                </tr>
            </c:forEach>
            <% if (bustepaga.isEmpty()) { %>
            <tr>
                <td colspan="4">Non ci sono buste paga</td>
            </tr>
            <% } %>
        </table>

    </div>
    <br>
    <div class="divider"></div>


    <%--AGGIUNTA DIPENDENTI--%>
    <h2 class="title">Aggiungi Busta Paga</h2>
    <div class="form-container">
        <form action="/bustepaga/add" method="post">

            <div class="input-container">
                <label>Importo Emesso</label>
                <input name="importoEmesso" type="number" placeholder="Importo Emesso..." required>
            </div>

            <div class="input-container">
                <label>Data Emissione</label>
                <input name="dataEmissione" type="date" placeholder="Data Emissione..." required>
            </div>

            <div class="input-container">
                <label>Codice Fiscale</label>
                <input name="codiceFiscale" type="text" placeholder="Codice Fiscale..." required>
            </div>

            <input type="submit" value="Aggiungi">
        </form>
    </div>
</div>

</body>
</html>