<%--
  Created by IntelliJ IDEA.
  User: Bassit
  Date: 5/20/2024
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flip Card Click</title>
    <link rel="stylesheet" href="main.css" />
</head>
<body>
<div class="card">
    <div class="card__inner">
        <div class="card__face card__face--front">
            <h2>Card Front</h2>
        </div>
        <div class="card__face card__face--back">
            <div class="card__content">
                <div class="card__header">
<%--                    <img src="pp.jpg" alt="" class="pp" />--%>
                    <h2>Bassit Ilahi</h2>
                </div>
                <div class="card__body">
                    <h3>Java Wizard</h3>
                    <p>Lorem ipsum <strong>dolor</strong> sit amet, consectetur <strong>adipiscing</strong> elit. Sed id erat a magna lobortis dictum. Nunc est arcu, <strong>lacinia</strong> quis sapien placerat, <strong>laoreet</strong> tincidunt nulla.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<script src="main.js"></script>--%>
<script type="text/javascript">
    const card = document.querySelector(".card__inner");

    card.addEventListener("click", function (e) {
        card.classList.toggle('is-flipped');
    });
</script>

</body>
</html>


<style>

    :root {
        --primary: #FFCE00;
        --secondary: #FE4880;
        --dark: #212121;
        --light: #F3F3F3;
    }

    * {
        margin: 0;
        padding: 0;
    }

    body {
        font-family: montserrat, sans-serif;
        width: 100%;
        min-height: 100vh;
    }

    .card {
        margin: 100px auto 0;
        width: 400px;
        height: 600px;
        perspective: 1000px;
    }

    .card__inner {
        width: 100%;
        height: 100%;
        transition: transform 1s;
        transform-style: preserve-3d;
        cursor: pointer;
        position: relative;
    }

    .card__inner.is-flipped {
        transform: rotateY(180deg);
    }


    .card__face {
        position: absolute;
        width: 100%;
        height: 100%;
        -webkit-backface-visibility: hidden;
        backface-visibility: hidden;
        overflow: hidden;
        border-radius: 16px;
        box-shadow: 0px 3px 18px 3px rgba(0, 0, 0, 0.2);
    }

    .card__face--front {
        background-image: linear-gradient(to bottom right, var(--primary), var(--secondary));
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .card__face--front h2 {
        color: #FFF;
        font-size: 32px;
    }

    .card__face--back {
        background-color: var(--light);
        transform: rotateY(180deg);
    }

    .card__content {
        width: 100%;
        height: 100%;
    }

    .card__header {
        position: relative;
        padding: 30px 30px 40px;
    }

    .card__header:after {
        content: '';
        display: block;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-image: linear-gradient(to bottom left, var(--primary) 10%, var(--secondary) 115%);
        z-index: -1;
        border-radius: 0px 0px 50% 0px;
    }

    .pp {
        display: block;
        width: 128px;
        height: 128px;
        margin: 0 auto 30px;
        border-radius: 50%;
        background-color: #FFF;
        border: 5px solid #FFF;
        object-fit: cover;
    }

    .card__header h2 {
        color: #FFF;
        font-size: 32px;
        font-weight: 900;
        text-transform: uppercase;
        text-align: center;
    }

    .card__body {
        padding: 30px;
    }

    .card__body h3 {
        color: var(--dark);
        font-size: 24px;
        font-weight: 600;
        margin-bottom: 15px;
    }

    .card__body p {
        color: var(--dark);
        font-size: 18px;
        line-height: 1.4;
    }

</style>

