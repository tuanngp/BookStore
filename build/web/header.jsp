<%-- 
    Document   : header.jsp
    Created on : 28-02-2024, 20:30:36
    Author     : Tuanngp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--CSS-->
        <link rel="stylesheet" href="assets/css/header.css"/>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="assets/css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="assets/images/fevicon.png" type="image/gif" />

    </head>
    <body>
        <header class="header_section">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="logo" href="home"><img src="assets/images/logo.png" alt="logo"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <div class="dropdown" onmouseover="openDropdown()" onmouseout="closeDropdown()">
                                <button class="nav-link single-line btn-sub-menu" type="button" aria-expanded="false" 
                                        style="color:#000;
                                        background-color: rgb(248,249,250);
                                        border: none;">
                                    CATEGORIES
                                </button>
                                <ul id="dropdown-menu">
                                    <li>
                                        <a href="product"><button class="dropdown-item" type="button">All
                                                Book</button>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="product?action=newArrival">
                                            <button class="dropdown-item" type="button">New Arrivals</button>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="product?action=category&category=Genre&input=Fiction">
                                            <button class="dropdown-item" type="button">Fiction</button>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="product?action=category&category=Genre&input=Non-Fiction">
                                            <button class="dropdown-item" type="button">Non-Fiction</button>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="product?action=category&category=Genre&input=Mystery">
                                            <button class="dropdown-item">Mystery</button>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="product?action=category&category=Genre&input=Fantasy">
                                            <button class="dropdown-item" type="button">Fantasy</button>
                                        </a>
                                    </li>
                                    <li><a href="product?action=comingsoon">
                                            <button class="dropdown-item" type="button">Bibliography</button>
                                        </a>
                                    </li>

                            </div>
                        </li>


                        <li class="nav-item">
                            <a class="nav-link single-line" href="product">All BOOKS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link single-line" href="product?action=newArrival">NEW ARRIVALS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link single-line" href="faq.jsp">FAQ</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link single-line" href="contact.jsp">CONTACT</a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link single-line" href="about-us.jsp">ABOUT US</a>
                        </li>
                    </ul>
                    <div class="search_icon">
                        <div class="row">
                            <form action="product" method="get" style="display: flex; flex-direction: row; height: 32px; margin-bottom:0;">
                                <input type="hidden" name="action" value="search">
                                <input type="text"
                                       name="input"
                                       placeholder="Search..." 
                                       required>
                                <button type="submit" 
                                        class="btn btn-outline-primary"
                                        data-mdb-ripple-init>search</button>
                            </form>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${empty sessionScope.user}">
                            <div class="search_icon"><a href="registration.jsp"><img src="assets/images/eye-icon.png" alt=""><span class="padding_left_15">Register</span></a></div>
                            <div class="search_icon"><a href="login.jsp"><img src="assets/images/user-icon.png" alt=""><span class="padding_left_15">login</span></a></div> 
                            <br/>
                        </c:when>
                        <c:otherwise>
                            <div class="search_icon"><a href="user"><img src="assets/images/user-icon.png" alt=""><span class="padding_left_15">Account</span></a></div> 
                            <br/>
                        </c:otherwise>
                    </c:choose>

                    <div class="search_icon">
                        <img class="image_cart" src="assets/images/image-cart_90604.png" alt="Cart">
                        <c:choose>
                            <c:when test="${empty sessionScope.user}">
                                <a href="login.jsp">
                                    <c:if test="${not empty sessionScope.cart}">
                                        <div class="cart-overlay">
                                            ${sessionScope.cart.size()}
                                        </div>
                                    </c:if>
                                    <span class="padding_left_15">Cart</span>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="cart">
                                    <c:if test="${not empty sessionScope.cart}">
                                        <div class="cart-overlay">
                                            ${sessionScope.cart.size()}
                                        </div>
                                    </c:if>
                                    <span class="padding_left_15">Cart</span>
                                </a>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </nav>
        </header>
        <script src="account_assets/js/scripts.js"></script>
    </body>
</html>
