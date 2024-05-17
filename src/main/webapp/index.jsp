<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
<title>Home</title>
</head>
<body>

<div class="formbold-main-wrapper">
<div class="formbold-form-wrapper">

        <img class="icon" src="view/images/registerIcon.jpg">

        <div class="formbold-form-title">
                <h2 class="">Web Registration Project</h2>
        </div>

        <form action="/registerButtonPressed" method="GET">
                <button class="home-btn" type="submit">Register Now</button>
        </form>

        <form action="/loginButtonPressed" method="GET">
                <button class="login-btn" type="submit">Login Now</button>
        </form>
</div>
</div>

</body>
</html>

<style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
        * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
        }
        body {
                font-family: 'Inter', sans-serif;
        }

        .formbold-main-wrapper {
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 48px;
                height:950px;
                margin: 0 auto;
                background-image: linear-gradient(to bottom right, #9aee83, #b87ee0);

        }

        .formbold-form-wrapper {
                margin: 0 auto;
                max-width: 570px;
                width: 100%;
                background: white;
                padding: 40px;
                border-style: solid;
                border-color: white;
                border-radius: 50px;
                box-shadow: 20px 20px 50px 15px grey;
        }

        .formbold-form-title {
                margin-bottom: 30px;
        }

        .formbold-form-title h2 {
                font-weight: 600;
                font-size: 28px;
                line-height: 34px;
                color: #4c18ad
        }

        .formbold-form-title p {
                font-size: 16px;
                line-height: 24px;
                color: #536387;
                margin-top: 12px;
        }

        .formbold-input-flex > div {
                width: 50%;
        }

        .home-btn {
                font-size: 16px;
                border-radius: 5px;
                padding: 14px 25px;
                border: none;
                font-weight: 500;
                background-color: #6a64f1;
                color: white;
                cursor: pointer;
                margin-top: 25px;
        }

        .home-btn:hover {
                /*box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);*/
                box-shadow: 20px 20px 50px 10px pink inset;
        }

        .login-btn {
                font-size: 16px;
                border-radius: 5px;
                padding: 14px 35px;
                border: none;
                font-weight: 500;
                background-color: #6a64f1;
                color: white;
                cursor: pointer;
                margin-top: 25px;
        }

        .login-btn:hover {
                /*box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);*/
                box-shadow: 20px 20px 50px 10px pink inset;

        }

        .icon {
                width: 60px;
                height: 60px;
                margin-left: -12px;
        }
</style>



