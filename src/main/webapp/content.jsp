<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<html>
<head>
    <title>WEDPDM: Area Clienti</title>

    <style>


        .container {
            width: 100%;
            height:100%;
            display: flex;
            align-items: flex-start;
            justify-content: flex-start;
            flex-direction: column;
        }

        .profile {
            font-family: sans-serif;
            text-align: center;
            max-width: 200px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            padding: 20px;

        }

        .profile__image {


            width: 150px;
            height: 150px;
            object-fit: cover;
            border-radius: 50%;
            margin: 0 auto 20px auto;
            display: block;

        }

        .profile__name {
            font_size: 1.2em;
            font-weight: bold;

        }


    </style>
</head>
<body>
<jsp:include page="/nav"/>


<div class="container">
    <div class="profile">
        <img src="assets/media/famiglia.jpeg" alt="Profile Image" class="profile__image">
        <div class="profile__name"> Giuseppe Perla</div>
        <div class="profile__title"> Nome Azienda</div>
    </div>
</div>


<a href="/logout">Logout</a>
</body>
</html>