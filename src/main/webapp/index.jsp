<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--  <title>JSP - Hello World</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1><%= "Hello World!" %></h1>--%>
<%--<br/>--%>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
<%--</body>--%>
<%--</html>--%>


<%--
  Created by IntelliJ IDEA.
  User: Bassit
  Date: 5/20/2024
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Text Animation</title>
</head>
<body>

<svg id="logo" width="749" height="122" viewBox="0 0 749 122" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path d="M73.431 103.248L73.4382 103.237L73.4452 103.225C76.2986 98.6812 77.716 93.6087 77.716 88.072C77.716 83.6559 76.7153 79.4801 74.7163 75.578C72.8277 71.705 70.1961 68.4399 66.8344 65.8099C65.4147 64.6667 63.9192 63.7059 62.3502 62.9307C65.2824 61.2415 67.8208 59.1163 69.9418 56.5461C73.6363 52.0901 75.412 46.5534 75.412 40.12C75.412 34.8808 74.0955 30.0965 71.432 25.835C68.736 21.5214 64.7339 18.2018 59.5753 15.8253C54.3607 13.3265 48.1687 12.132 41.088 12.132H5.08801H2.58801V14.632V115V117.5H5.08801H42.528C49.5292 117.5 55.717 116.3 61.0165 113.806L61.0259 113.802L61.0353 113.797C66.3779 111.229 70.5521 107.726 73.431 103.248ZM54.7329 75.5768L54.7469 75.5887L54.7611 75.6004C57.9526 78.2334 59.612 81.8544 59.612 86.776C59.612 91.6319 58.0269 95.1557 55.0297 97.6797C51.9744 100.253 47.5317 101.7 41.376 101.7H20.692V71.276H40.944C46.9507 71.276 51.4582 72.7933 54.7329 75.5768ZM40.224 55.476H20.692V27.932H39.936C45.9288 27.932 50.1073 29.2966 52.853 31.6501C55.5728 33.9814 57.02 37.2242 57.02 41.704C57.02 46.1837 55.5728 49.4266 52.853 51.7579C50.095 54.1219 46.002 55.476 40.224 55.476Z" stroke="white" stroke-width="5"/>
    <path d="M105.533 37.6726L105.525 37.6772L105.517 37.6819C99.4381 41.288 94.6628 46.3861 91.1966 52.9079C87.7067 59.3775 86.0045 66.8526 86.0045 75.256C86.0045 83.5739 87.7108 91.0845 91.1853 97.7267C94.6461 104.343 99.4151 109.539 105.49 113.246C111.569 116.955 118.36 118.796 125.801 118.796C132.99 118.796 139.391 117.223 144.919 113.99L144.925 113.986L144.931 113.983C147.953 112.192 150.589 110.138 152.821 107.815V115V117.5H155.321H168.569H171.069V115V36.088V33.588H168.569H155.321H152.821V36.088V42.9802C150.665 40.7619 148.121 38.8026 145.207 37.098C139.674 33.862 133.223 32.292 125.945 32.292C118.517 32.292 111.695 34.078 105.533 37.6726ZM140.65 51.9162L140.68 51.934L140.71 51.951C144.399 54.0097 147.335 57.014 149.524 61.0418C151.691 65.0294 152.821 69.7882 152.821 75.4C152.821 81.0058 151.693 85.8191 149.519 89.9122C147.327 93.9399 144.384 96.9987 140.68 99.154C137.049 101.227 133.019 102.276 128.537 102.276C124.059 102.276 120.033 101.229 116.404 99.1604C112.798 97.009 109.895 93.9503 107.698 89.9121C105.527 85.8245 104.397 80.965 104.397 75.256C104.397 69.6442 105.526 64.8854 107.693 60.8978C109.89 56.855 112.789 53.8492 116.385 51.7946C120.018 49.7183 124.051 48.668 128.537 48.668C133.003 48.668 137.022 49.7567 140.65 51.9162Z" stroke="white" stroke-width="5"/>
    <path d="M201.37 115.56L201.389 115.569L201.409 115.577C206.561 117.741 212.322 118.796 218.649 118.796C224.595 118.796 229.944 117.788 234.64 115.701L234.652 115.695L234.663 115.69C239.424 113.517 243.209 110.512 245.902 106.634C248.601 102.748 249.949 98.3592 249.949 93.544V93.519L249.949 93.494C249.844 88.2484 248.363 83.7615 245.318 80.2558C242.572 76.9876 239.228 74.5431 235.311 72.9501C231.614 71.3536 226.892 69.7857 221.182 68.2369C216.733 67.0064 213.278 65.9312 210.787 65.0116C208.579 64.071 206.762 62.9055 205.3 61.5373C204.178 60.3256 203.581 58.8132 203.581 56.824C203.581 54.4104 204.548 52.504 206.723 50.9289C208.949 49.3172 212.121 48.38 216.489 48.38C221.174 48.38 224.576 49.5247 226.978 51.5395L226.993 51.552L227.009 51.5643C229.517 53.602 230.889 56.261 231.131 59.734L231.293 62.06H233.625H246.729H249.357L249.226 59.4352C248.811 51.1341 245.657 44.3973 239.676 39.4842C233.748 34.6148 226.084 32.292 216.921 32.292C210.967 32.292 205.57 33.3511 200.783 35.5324C196.032 37.602 192.243 40.5022 189.543 44.2829L191.577 45.736L189.543 44.2829C186.848 48.056 185.477 52.2577 185.477 56.824C185.477 62.3449 186.891 67.0763 189.93 70.7911L189.956 70.8231L189.984 70.8543C192.904 74.1918 196.364 76.7635 200.348 78.546L200.384 78.562L200.42 78.5769C204.303 80.17 209.219 81.7384 215.135 83.2902L215.144 83.2927L215.154 83.2952C221.525 84.9118 226.02 86.5276 228.799 88.0909L228.826 88.1057L228.852 88.1198C230.058 88.7603 230.844 89.513 231.341 90.3362C231.837 91.1585 232.133 92.1962 232.133 93.544C232.133 96.0572 231.112 98.1333 228.792 99.912C226.486 101.68 223.15 102.708 218.505 102.708C213.521 102.708 209.724 101.556 206.906 99.4675C204.177 97.3761 202.73 94.7836 202.417 91.5732L202.197 89.316H199.929H186.393H183.739L183.898 91.9657C184.214 97.2354 185.911 101.977 188.995 106.124L189.004 106.136L189.014 106.149C192.089 110.178 196.239 113.303 201.37 115.56Z" stroke="white" stroke-width="5"/>
    <path d="M276.605 115.56L276.624 115.569L276.643 115.577C281.795 117.741 287.556 118.796 293.883 118.796C299.829 118.796 305.178 117.788 309.875 115.701L309.886 115.695L309.898 115.69C314.658 113.517 318.443 110.512 321.137 106.634C323.836 102.748 325.183 98.3592 325.183 93.544V93.519L325.183 93.494C325.078 88.2484 323.597 83.7615 320.552 80.2558C317.806 76.9876 314.463 74.5431 310.546 72.9501C306.849 71.3536 302.127 69.7857 296.416 68.2369C291.968 67.0064 288.513 65.9312 286.022 65.0116C283.813 64.071 281.997 62.9055 280.534 61.5373C279.412 60.3256 278.815 58.8132 278.815 56.824C278.815 54.4104 279.782 52.504 281.958 50.9289C284.183 49.3172 287.356 48.38 291.723 48.38C296.408 48.38 299.811 49.5247 302.213 51.5395L302.228 51.552L302.243 51.5643C304.751 53.602 306.123 56.261 306.365 59.734L306.528 62.06H308.859H321.963H324.592L324.46 59.4352C324.045 51.134 320.891 44.3973 314.91 39.4842C308.982 34.6148 301.318 32.292 292.155 32.292C286.201 32.292 280.805 33.3511 276.018 35.5324C271.266 37.602 267.478 40.5022 264.777 44.2829L266.811 45.736L264.777 44.2829C262.082 48.056 260.711 52.2577 260.711 56.824C260.711 62.3449 262.125 67.0763 265.164 70.7911L265.191 70.8231L265.218 70.8543C268.138 74.1918 271.598 76.7635 275.582 78.546L275.618 78.562L275.654 78.5769C279.538 80.17 284.453 81.7384 290.369 83.2902L290.379 83.2927L290.389 83.2952C296.76 84.9118 301.254 86.5276 304.034 88.0909L304.06 88.1057L304.086 88.1198C305.292 88.7603 306.078 89.513 306.575 90.3362C307.072 91.1585 307.367 92.1962 307.367 93.544C307.367 96.0572 306.346 98.1333 304.026 99.912C301.721 101.68 298.385 102.708 293.739 102.708C288.755 102.708 284.959 101.556 282.14 99.4675C279.412 97.3761 277.965 94.7836 277.652 91.5732L277.431 89.316H275.163H261.627H258.973L259.132 91.9657C259.448 97.2354 261.146 101.977 264.229 106.124L264.239 106.136L264.248 106.149C267.323 110.178 271.474 113.303 276.605 115.56Z" stroke="white" stroke-width="5"/>
    <path d="M356.786 36.088V33.588H354.286H341.182H338.682V36.088V115V117.5H341.182H354.286H356.786V115V36.088ZM339.846 22.4478C342.054 24.6557 344.807 25.772 347.95 25.772C351.018 25.772 353.685 24.639 355.791 22.4225C357.982 20.2188 359.09 17.4752 359.09 14.344C359.09 11.2128 357.982 8.46913 355.791 6.26546C353.685 4.04902 351.018 2.91599 347.95 2.91599C344.807 2.91599 342.054 4.03226 339.846 6.24022C337.638 8.44819 336.522 11.2008 336.522 14.344C336.522 17.4871 337.638 20.2398 339.846 22.4478Z" stroke="white" stroke-width="5"/>
    <path d="M415.695 103.912V101.412H413.195H403.547C399.729 101.412 397.721 100.665 396.792 99.8129C395.87 98.8694 395.103 96.9587 395.103 93.4V49.388H413.195H415.695V46.888V36.088V33.588H413.195H395.103V16.216V13.716H392.603H379.499H376.999V16.216V33.588H369.275H366.775V36.088V46.888V49.388H369.275H376.999V93.4C376.999 101.4 378.891 107.759 383.278 111.799C387.564 115.747 393.755 117.5 401.387 117.5H413.195H415.695V115V103.912Z" stroke="white" stroke-width="5"/>
    <path d="M483.067 14.632V12.132H480.567H467.463H464.963V14.632V115V117.5H467.463H480.567H483.067V115V14.632Z" stroke="white" stroke-width="5"/>
    <path d="M518.505 8.44V5.94H516.005H502.901H500.401V8.44V115V117.5H502.901H516.005H518.505V115V8.44Z" stroke="white" stroke-width="5"/>
    <path d="M550.47 37.6726L550.462 37.6772L550.454 37.6819C544.376 41.288 539.6 46.3861 536.134 52.9079C532.644 59.3775 530.942 66.8526 530.942 75.256C530.942 83.5739 532.648 91.0845 536.123 97.7267C539.584 104.343 544.353 109.539 550.428 113.246C556.506 116.955 563.297 118.796 570.738 118.796C577.927 118.796 584.329 117.223 589.856 113.99L589.862 113.986L589.868 113.983C592.89 112.192 595.526 110.138 597.758 107.815V115V117.5H600.258H613.506H616.006V115V36.088V33.588H613.506H600.258H597.758V36.088V42.9801C595.602 40.7619 593.058 38.8026 590.144 37.098C584.612 33.862 578.16 32.292 570.882 32.292C563.455 32.292 556.632 34.078 550.47 37.6726ZM585.587 51.9162L585.617 51.934L585.648 51.951C589.336 54.0097 592.272 57.014 594.461 61.0418C596.629 65.0293 597.758 69.7882 597.758 75.4C597.758 81.0058 596.631 85.8191 594.456 89.9122C592.265 93.9399 589.321 96.9986 585.618 99.154C581.986 101.227 577.956 102.276 573.474 102.276C568.996 102.276 564.97 101.229 561.342 99.1604C557.735 97.009 554.833 93.9503 552.636 89.9121C550.464 85.8244 549.334 80.965 549.334 75.256C549.334 69.6442 550.463 64.8854 552.631 60.8978C554.828 56.855 557.727 53.8492 561.322 51.7946C564.956 49.7183 568.989 48.668 573.474 48.668C577.94 48.668 581.959 49.7567 585.587 51.9162Z" stroke="white" stroke-width="5"/>
    <path d="M706.354 117.5H708.854V115V68.488C708.854 60.8862 707.405 54.2639 704.38 48.7281C701.475 43.2349 697.384 39.0702 692.117 36.327C686.924 33.5266 681.14 32.148 674.818 32.148C668.822 32.148 663.311 33.3191 658.33 35.7055L658.321 35.7099L658.312 35.7145C655.667 37.0089 653.311 38.6008 651.255 40.4892V8.44V5.94H648.755H635.651H633.151V8.44V115V117.5H635.651H648.755H651.255V115V71.368C651.255 63.5 653.173 57.9211 656.62 54.2205C660.2 50.4795 665.023 48.524 671.362 48.524C677.621 48.524 682.284 50.4396 685.661 54.0643L685.671 54.0748L685.681 54.0852C689.028 57.5956 690.895 62.8831 690.895 70.36V115V117.5H693.395H706.354Z" stroke="white" stroke-width="5"/>
    <path d="M743.364 36.088V33.588H740.864H727.76H725.26V36.088V115V117.5H727.76H740.864H743.364V115V36.088ZM726.424 22.4478C728.632 24.6557 731.385 25.772 734.528 25.772C737.596 25.772 740.263 24.639 742.369 22.4225C744.56 20.2188 745.668 17.4752 745.668 14.344C745.668 11.2128 744.56 8.46915 742.369 6.26546C740.263 4.04903 737.596 2.91599 734.528 2.91599C731.385 2.91599 728.632 4.03226 726.424 6.24022C724.216 8.44816 723.1 11.2008 723.1 14.344C723.1 17.4872 724.216 20.2398 726.424 22.4478Z" stroke="white" stroke-width="5"/>
</svg>

<%--<script src="logoPathLength.js"></script>--%>

<%--<script>--%>
<%--    paste this into a js file with name logoPathLength.js--%>
<%--    const logo = document.querySelectorAll("#logo path");--%>
<%--    for(let i=0; i<logo.length; i++){--%>
<%--        console.log(`Letter ${i} is ${logo[i].getTotalLength()}`);--%>
<%--    }    --%>
<%--</script>--%>

</body>
</html>

<style>

    *{
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }

    body{
        width: 100%;
        height: 100vh;
        background-color: rgb(32, 35,48);
    }

    #logo{
        position: absolute;
        top:50%;
        left: 50%;
        transform: translate(-50%, -50%);
        animation: fill 0.5s ease forwards 5.0s;
    }

    #logo path:nth-child(1){
        stroke-dasharray: 585px;
        stroke-dashoffset: 585px;
        animation: line-anim 2s ease forwards;
    }
    #logo path:nth-child(2){
        stroke-dasharray: 493px;
        stroke-dashoffset: 493px;
        animation: line-anim 2s ease forwards 0.3s;
    }
    #logo path:nth-child(3){
        stroke-dasharray: 443px;
        stroke-dashoffset: 443px;
        animation: line-anim 2s ease forwards 0.6s;
    }
    #logo path:nth-child(4){
        stroke-dasharray: 443px;
        stroke-dashoffset: 443px;
        animation: line-anim 2s ease forwards 0.9s;
    }
    #logo path:nth-child(5){
        stroke-dasharray: 275px;
        stroke-dashoffset: 275px;
        animation: line-anim 2s ease forwards 1.2s;
    }
    #logo path:nth-child(6){
        stroke-dasharray: 335px;
        stroke-dashoffset: 335px;
        animation: line-anim 2s ease forwards 1.5s;
    }
    #logo path:nth-child(7){
        stroke-dasharray: 247px;
        stroke-dashoffset: 247px;
        animation: line-anim 2s ease forwards 1.8s;
    }
    #logo path:nth-child(8){
        stroke-dasharray: 260px;
        stroke-dashoffset: 260px;
        animation: line-anim 2s ease forwards 2.1s;
    }
    #logo path:nth-child(9){
        stroke-dasharray: 493px;
        stroke-dashoffset: 493px;
        animation: line-anim 2s ease forwards 2.4s;
    }
    #logo path:nth-child(10){
        stroke-dasharray: 492px;
        stroke-dashoffset: 492px;
        animation: line-anim 2s ease forwards 2.7s;
    }
    #logo path:nth-child(11){
        stroke-dasharray: 275px;
        stroke-dashoffset: 275px;
        animation: line-anim 2s ease forwards 3.0s;
    }

    @keyframes line-anim {
        to{
            stroke-dashoffset: 0;
        }
    }

    @keyframes fill {
        from{
            fill:transparent;
        }
        to{
            fill: white;
        }
    }


</style>
