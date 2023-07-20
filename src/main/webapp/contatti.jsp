<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>WEBPDM: Contatti</title>
    <link rel="stylesheet" type="text/css" href="assets/styles/contatti.css">

</head>
<body>
<jsp:include page="/nav"/>


<div class="container">

    <div class="background"></div>
    <h1 class="scritta"> Contattaci</h1>

    <div class="cards-container">

        <div class="contact-info">

            <a class="card" href="mailto:info@webpdm.com">
                <i class="fas fa-envelope fa-lg"></i>
                <p>info@webpdm.com</p>
            </a>

            <div class="card">
                <i class="fas fa-phone"></i>
                <p>+39 320 12345678</p>
            </div>

            <a class="card" href="https://goo.gl/maps/6nZ6Np17gdgJ2ywB7" target="_blank">
                <i class="fas fa-map-marker-alt fa-lg"></i>
                <p>San Lucido , Calabria </p>
            </a>

        </div>
    </div>

</div>

</body>
</html>