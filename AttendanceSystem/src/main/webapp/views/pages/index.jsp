<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<!--
<a href="hello-servlet">Hello Servlet</a>
-->
</body>






<div class="hide">
    <!-- this section is the icons for the sidebar each path is the vector, svg, representation of the icons-->
    <svg>
        <symbol viewBox="-326.8 274.8 63.7 64.5" id="dashboard">
            <path d="M-282.1 306.7l-2.5-1.1 1.8-2.1c2.6-3.1 4.1-6.9 4.1-10.9v-1.2c0-9.2-7.5-16.8-16.8-16.8h-1.2c-9.2 0-16.8 7.6-16.8 16.8v1.2c0 4.2 1.6 8.3 4.6 11.5l1.9 2-2.5 1.2c-10.7 5.1-17.6 16.1-17.6 27.9 0 2.2 1.8 4.1 4.1 4.1s4.1-1.8 4.1-4.1c0-12.5 10.2-22.8 22.8-22.8.2 0 .4 0 .6-.1l.3-.1.3.1c.3 0 .5.1.6.1 12.5 0 22.8 10.2 22.8 22.8 0 2.2 1.8 4.1 4.1 4.1 2.2 0 4.1-1.8 4.1-4.1.1-12.5-7.3-23.6-18.8-28.5zm-4.7-14.4v.4c0 4.7-3.9 8.6-8.6 8.7h-.8c-4.8 0-8.6-3.9-8.6-8.7v-1.2c0-2.3.9-4.5 2.5-6.1 1.6-1.6 3.8-2.5 6.1-2.5h1.2c4.8 0 8.6 3.9 8.6 8.7v.6l-.4.1z"/>
        </symbol>

        <symbol viewBox="-324.7 285.2 59.4 43.7" id="takeAttendance">
            <path d="M-271.2 285.2h-47.6c-3.3 0-5.9 2.7-5.9 5.9v31.8c0 3.3 2.7 5.9 5.9 5.9h47.6c3.3 0 5.9-2.7 5.9-5.9v-31.8c0-3.3-2.6-5.9-5.9-5.9zm0 37.7h-47.6v-31.8h47.6v31.8zM-304.2 319.3c.5.3 1 .4 1.5.4s1-.1 1.5-.4l16.8-9.7c.9-.5 1.5-1.5 1.5-2.6s-.6-2-1.5-2.6l-16.8-9.7c-.9-.5-2-.5-3 0-.9.5-1.5 1.5-1.5 2.6v19.5c.1 1 .6 2 1.5 2.5zm4.5-16.9l8 4.6-8 4.6v-9.2z"/>
        </symbol>

        <symbol viewBox="0 137.8 612 516.4" id="studentList">
            <path d="M549.7 199.1v324.2h-63.4L415.6 594l-66.5-70.7H61.3V199.1h488.4m0-61.3H61.3C27 137.8 0 165.9 0 199.1v324.2c0 34.3 28.1 61.3 61.3 61.3h261.8l48.8 50.9c11.4 12.5 27 18.7 43.6 18.7h1c16.6 0 32.2-6.2 43.6-17.7l52-52h38.4c34.3 0 61.3-28.1 61.3-61.3V199.1c-.8-34.3-27.9-61.3-62.1-61.3z"/>
        </symbol>

        <symbol viewBox="-313 288.4 36.1 37.3" id="reports-lnk">
            <path d="M-312.1 309l8.8 4.6 1.8 10.7c.1.7.7 1.2 1.3 1.3h.3c.6 0 1.1-.3 1.4-.7l4.6-7.5 9.7 4.8c.2.1.5.2.7.2s.5-.1.7-.2c.5-.2.8-.7.9-1.2l4.8-30c.2-.9-.3-1.9-1.2-2.3-.7-.4-1.6-.4-2.2 0l-31.9 17.4c-.5.3-.8.8-.8 1.5.2.6.5 1.2 1.1 1.4zm27.4 9.3l-7.4-3.6 10.8-17.4-3.4 21zm-11.2-3.9l-3.1 5-1.1-6.3 12.9-12.8-8.7 14.1zm7.8-17.7l-14.1 13.8-5.8-3 19.9-10.8z"/>
        </symbol>
    </svg>
</div>

<header class="main-head">
    <nav class="head-nav">
        <ul class="menu">
            <li>
                <a href="dashboard"> <!-- Add link to where clicking the below icon will take you-->
                    <svg class="dashboard"> <!-- add image for icon-->
                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#dashboard"></use>
                    </svg><span>Dashboard</span></a>
            </li>
            <li>
                <a href="takeattendance"> <!-- Add link to where clicking the below icon will take you-->
                    <svg class="takeAttendance"> <!-- add image for icon-->
                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#takeAttendance"></use>
                    </svg><span>Take Attendance</span></a>
            </li>
            <li>
                <a href="studentList"> <!-- Add link to where clicking the below icon will take you-->
                    <svg class="studentList"> <!-- add image for icon-->
                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#studentList"></use>
                    </svg><span>Student List</span></a>
            </li>
            <li>
                <a href="reports"> <!-- Add link to where clicking the below icon will take you-->
                    <svg class="reports-lnk"> <!-- add image for icon-->
                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#reports-lnk"></use>
                    </svg><span>Reports</span></a>
            </li>
        </ul>
    </nav>
</header> <!-- //main-head -->
<div class="wrap-all-the-things"></div>

</html>


<style>
    @import url(https://fonts.googleapis.com/css?family=Roboto:300);


    :root {
        --primary: #ed1c5b;
        --dark: #1a1a1a;
        --light: #fefefe;
    }


    html,
    body {
        margin: 0;
        padding: 0;
        overflow-x: hidden;
        font-family: Roboto;
        height: 100%;
    }

    .hide {
        display: none;
    }

    .head-nav li:hover {
        background-color: var(--dark);
    }
    .head-nav li:hover:before {
        background-color: var(--dark);
    }
    .head-nav li:hover svg {
        background: var(--dark);
        fill: var(--primary);
    }
    .head-nav li:hover a {
        color: var(--primary);
    }
    .head-nav li:before {
        position: absolute;
        left: -0.925rem;
        top: 0;
        width: 0.925rem;
        height: 100%;
        content: "";
        background: var(--primary);
        z-index: 1;
        transition: background 400ms;
    }
    .head-nav ul {
        margin-top: 1.85rem;
        margin-right: 0;
        margin-bottom: 0;
        margin-left: 0;
        padding: 0;
        list-style: none;
    }
    .head-nav li {
        position: relative;
        clear: both;
        width: 100%;
        padding: 0;
        transition: background 400ms;
    }
    .head-nav li a {
        display: flex;
        padding-top: 2vh;
        padding-bottom: 2vh;
    }
    .head-nav a {
        align-items: center;
        width: 100%;
        color: var(--light);
        text-decoration: none;
        transition: color 400ms;
    }
    .head-nav a span {
        position: relative;
        display: block;
        z-index: 0;
        font-family: Roboto;
        text-indent: -20em;
        white-space: nowrap;
        transition: text-indent 400ms ease-in-out;
    }
    .head-nav svg {
        position: relative;
        max-width: 80px;
        max-height: 30px;
        z-index: 1;
        fill: var(--light);
        background: var(--primary);
        transition: 400ms;
    }
    @media screen and (max-width: 768px) {
        .head-nav ul {
            margin-top: 0.23125rem;
        }
        .head-nav svg {
            max-width: 20px;
        }
    }

    .main-head {
        position: fixed;
        bottom: 0;
        width: 83px;
        height: 100%;
        z-index: 1;
        background: var(--primary);
        transition: width 400ms;
    }
    .main-head:hover {
        width: 436px;
    }
    .main-head:hover + .wrap-all-the-things {
        transform: translateX(336px);
        max-width: 100%;
        opacity: 0.4;
    }
    .main-head:hover .head-nav li:nth-of-type(1) span {
        transition-delay: 100ms;
    }
    .main-head:hover .head-nav li:nth-of-type(2) span {
        transition-delay: 200ms;
    }
    .main-head:hover .head-nav li:nth-of-type(3) span {
        transition-delay: 300ms;
    }
    .main-head:hover .head-nav li:nth-of-type(4) span {
        transition-delay: 400ms;
    }
    .main-head:hover .head-nav span {
        text-indent: 0;
    }
    @media screen and (max-width: 768px) {
        .main-head {
            width: 70px;
            padding: 0 0.925rem;
        }
        .main-head .head-nav {
            padding-left: 7px;
        }
        .main-head:hover .head-nav span {
            text-indent: 1em;
        }
    }

    .wrap-all-the-things {
        min-height: 100%;
        height: 100%;
        padding-left: 140px;
        margin-top: 0;
        /*background-image: url(https://download.unsplash.com/photo-1428976365951-b70e0fa5c551);*/
        background-size: cover;
        background-position: center center;
        transition: transform 400ms, opacity 400ms;
    }
    @media screen and (max-width: 480px) {
        .wrap-all-the-things {
            padding-left: 70px;
        }
    }


</style>