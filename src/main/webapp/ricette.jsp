<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>WEBPDM: Ricette</title>
    <link rel="stylesheet" type="text/css" href="assets/styles/ricette.css"/>
</head>
<body>
<jsp:include page="/nav"/>

<div class="slider-container">
    <div class="left-slide">
        <div style="background-color: #579dc9">
            <h1>  Orata con Patate </h1>
            <p> Il piatto preferito di Giuseppe Perla, lo preparava sempre dopo una giornata di pesca ai
                suoi due figli che ne andavano ghiotti, all'orata con patate è legata anche una legenda
                del luogo, dove chi mai pescasse "l'orata c'allumina" avrebbe giovato di ogni ricchezza
                infatti le patate simboleggiano l'oro.

            </p>
        </div>
        <div style="background-color: #579dc9">
            <h1> Cozze al limone  </h1>
            <p> Piatto tipico del luogo, una ricetta che nasce nel 1890 quando in seguito ad una tragedia
                che inquinò le acque del luogo per un breve periodo, gli abitanti per non sentirsi male
                abbinavano alle cozze il limone cosi da renderle più digeribili, dando vita ad un piatto
                squisito
            </p>
        </div>
        <div style="background-color: #579dc9">
            <h1> Ostriche al limone  </h1>
            <p> "U piatto di Baruni" cosi si vuole che lo si chiami nella tradizione del posto, questo
                  nome deriva dal fatto che era un piatto ricco, che non tutti potevano permettersi
                    perchè dove aver mangiato i Baroni, ne ricavavano anche stupende Perle di mare
            </p>
        </div>
        <div style="background-color: #579dc9">
            <h1> Pesce spada ai cetrioli  </h1>
            <p>  una ricetta dalle origini sconosciute, ma continua a vivere portando armoni a
                   e sorrisi in tavola </p>
        </div>
    </div>
    <div class="right-slide">
        <div
                style="
            background-image: url('assets/media/pesce4.jpeg');
          "
        ></div>
        <div
                style="
            background-image: url('assets/media/pesce3.jpeg');
          "
        ></div>
        <div
                style="
            background-image: url('assets/media/pesce2.jpeg');
          "
        ></div>
        <div
                style="
            background-image: url('assets/media/pesce1.jpeg');
          "
        ></div>
    </div>
    <div class="action-buttons">
        <button class="down-button">
            <i class="fas fa-arrow-down"></i>
        </button>
        <button class="up-button">
            <i class="fas fa-arrow-up"></i>
        </button>
    </div>
</div>

<script>
    const sliderContainer = document.querySelector(".slider-container");
    const slideRight = document.querySelector(".right-slide");
    const slideLeft = document.querySelector(".left-slide");
    const upButton = document.querySelector(".up-button");
    const downButton = document.querySelector(".down-button");
    const slidesLength = slideRight.querySelectorAll("div").length;

    let activeSlideIndex = 0;

    slideLeft.style.top = "-" + ((slidesLength - 1) * 95) + "vh";

    const changeSlide = (direction) => {

        const sliderHeight = sliderContainer.clientHeight;

        if (direction === "up") {
            activeSlideIndex++;
            if (activeSlideIndex > slidesLength - 1) activeSlideIndex = 0;
        } else if (direction === "down") {
            activeSlideIndex--;
            if (activeSlideIndex < 0) activeSlideIndex = slidesLength - 1;
        }

        slideRight.style.transform = ("translateY(-" + (activeSlideIndex * sliderHeight) + "px)");
        slideLeft.style.transform = ("translateY(" + (activeSlideIndex * sliderHeight) + "px)");

        clearInterval(sliderInterval);
    };

    upButton.addEventListener("click", () => changeSlide("up"));
    downButton.addEventListener("click", () => changeSlide("down"));

    let sliderInterval = window.setInterval(() => {
        changeSlide("up");
    }, 5000);

</script>


</body>
</html>