<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">

        <img class="icon" src="${pageContext.request.contextPath}/view/images/registerIcon.jpg">

        <form action="/account" method="POST">
            <div class="formbold-form-title">

                <h2 class="">Web Registration Project</h2>
            </div>

            <div class="formbold-input-flex">
                <div>
                    <label for="email" class="formbold-form-label"> Email* </label>
                    <input
                            type="email"
                            name="email"
                            id="email"
                            class="formbold-form-input"
                            required
                    />
                </div>
                <div>
                    <label for="password" class="formbold-form-label"> Password* </label>
                    <input
                            type="password"
                            name="password"
                            id="password"
                            class="formbold-form-input"
                            required
                    />
                </div>
            </div>

            <button class="submit-btn" type="submit">Login</button>
        </form>

        <a href="${pageContext.request.contextPath}/">
            <button class="cancel-btn" type="submit">Cancel</button>
        </a>

    </div>
</div>


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

    .formbold-input-flex {
        display: flex;
        gap: 20px;
        margin-bottom: 15px;
    }

    .formbold-input-flex > div {
        width: 50%;
    }

    .formbold-form-input {
        text-align: left;
        width: 100%;
        padding: 13px 22px;
        border-radius: 5px;
        border: 1px solid #dde3ec;
        background: #ffffff;
        font-weight: 500;
        font-size: 16px;
        color: #536387;
        outline: none;
        resize: none;
    }

    .formbold-form-input:focus {
        border-color: #6a64f1;
        box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
    }

    .formbold-form-label {
        color: #536387;
        font-size: 14px;
        line-height: 24px;
        display: block;
        margin-bottom: 10px;
    }

    .submit-btn {
        font-size: 16px;
        border-radius: 5px;
        padding: 14px 53px;
        border: none;
        font-weight: 500;
        background-color: #6a64f1;
        color: white;
        cursor: pointer;
        margin-top: 25px;
    }

    .submit-btn:hover {
        /*box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);*/
        box-shadow: 20px 20px 50px 10px pink inset;
    }

    .cancel-btn {
        font-size: 16px;
        border-radius: 5px;
        padding: 14px 48px;
        border: none;
        font-weight: 500;
        background-color: #9d1616;
        color: white;
        cursor: pointer;
        margin-top: 5px;

    }

    .cancel-btn:hover {
        /*box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);*/
        box-shadow: 20px 20px 50px 10px pink inset;
    }

    .icon {
        width: 60px;
        height: 60px;
        margin-left: -12px;
    }
</style>


</body>
</html>



