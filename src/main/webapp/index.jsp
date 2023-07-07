<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>WEBPDM: Index</title>
    <link rel="stylesheet" type="text/css" href="assets/styles/index.css" />
    <jsp:useBean id="title" scope="request" type="java.lang.String"/>
</head>
<body>
<jsp:include page="/nav"/>
<div class="container">
    <div class="todo page">
        <div class="titoloTodo">
            <h1><span id="todoList">TODO LIST</span></h1>
        </div>
        <div class="todoList1">
            <div class="elenco card">
                <h3>Lista delle cose da Fare:</h3>
                <ul id="lista">
                    <li id="elemento">
                        <input type="checkbox" name="object" id="object" value="1" />
                        <p>Fare il sito</p>
                        <button id="rimuovi" onclick="rimuovi()">
                            rimuovi
                        </button>
                    </li>
                </ul>
            </div>
            <div class="add card">
                <h3>Aggiungi:</h3>
                <input type="text" id="item" name="item\" />
                <button id="aggiungi" onclick="aggiunta()">Aggiungi</button>
            </div>
        </div>
    </div>
    <div class="meteoPage page">
        <h1>Meteo</h1>
        <p>Ti sei spostato alla pagine del <span id="meteoPage">Meteo</span></p>
    </div>
    <div class="wherePage page">
        <h1>WherePage</h1>
        <p>Ti sei spostato alla <span id="wherePage">wherePage</span></p>
    </div>
    <div class="infoPage page">
        <h1>InfoPage</h1>
        <p>Ti sei spostato alla <span id="infoPage">infoPage</span></p>
    </div>
</div>
</body>
</html>