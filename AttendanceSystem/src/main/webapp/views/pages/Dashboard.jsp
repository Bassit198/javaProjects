<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to external CSS file -->

    <!-- Fontawesome CDN Link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>

<nav>
    <div class="navbar">
        <div class="logo"><a href="#">Attendance System</a></div>
        <ul class="menu">
            <li><a href="#Home">Home</a></li>
            <li><a href="#About">About</a></li>
            <li><a href="#Category">Category</a></li>
            <li><a href="#Contact">Contact</a></li>
            <li><a href="#Feedback">Feedback</a></li>
        </ul>
    </div>
</nav>

<div class="button">
    <a href="#Home"><i class="fas fa-arrow-up"></i></a>
</div>


<div class="form-container">
    <form action="dashboard" method="post"> <!-- Action points to the Servlet -->
        <label for="role">Role:</label>
        <input type="text" id="role" name="role" required>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" value="Submit">
    </form>
</div>

</body>

</html>


<style>@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');


:root {
    --primary: #ed1c5b;
    --dark: #1a1a1a;
    --light: #fefefe;
}



/*------------------------------------------------------------navbar style*/
*{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}
nav{
    position: fixed;
    left: 0;
    top: 0;
    width: 100%;
    height: 75px;
    background: #2980b9;
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
}
nav .navbar{
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 100%;
    max-width: 90%;
    background: #2980b9;
    margin: auto;
}
nav .navbar .logo a{
    color: #fff;
    font-size: 27px;
    font-weight: 600;
    text-decoration: none;
}
nav .navbar .menu{
    display: flex;
}
.navbar .menu li{
    list-style: none;
    margin: 0 15px;
}
.navbar .menu li a{
    color: #fff;
    font-size: 17px;
    font-weight: 500;
    text-decoration: none;
}
section{
    display: flex;
    height: 100vh;
    width: 100%;
    align-items: center;
    justify-content: center;
    color: #96c7e8;
    font-size: 70px;
}
#Home{
    background: #fff;
}
#About{
    background: #f2f2f2;
}
#Category{
    background: #e6e6e6;
}
#Latest{
    background: #fff;
}
#Contact{
    background: #f2f2f2;
}
#Feedback{
    background: #e6e6e6;
}
.button a{
    position: fixed;
    bottom: 20px;
    right: 20px;
    color: #fff;
    background: #2980b9;
    padding: 7px 12px;;
    font-size: 18px;
    border-radius: 6px;
    box-shadow: rgba(0, 0, 0, 0.15);
}

/*------------------------------------------------------------form style*/
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.form-container {
    background-color: white;
    padding: 80px;
    border-radius: 8px;
    box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
    width: 500px;
}

label {
    display: block;
    margin-bottom: 8px;
}

input[type="text"], input[type="email"], input[type="password"] {
    width: 100%;
    padding: 8px;
    margin-bottom: 16px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    padding: 10px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    width: 100%;
}

input[type="submit"]:hover {
    background-color: #45a049;
}








</style>