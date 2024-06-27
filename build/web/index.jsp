<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Product.Book" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Home</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- bootstrap css -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="assets/css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="assets/assets/images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="assets/css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <!-- owl stylesheets --> 
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <!-- header section start -->
        <jsp:include page="header.jsp" ></jsp:include>
        <!-- header section end -->

        <!-- banner section start -->
        <div class="banner_section" style="background-color: #3c0e0e;">
            <div class="container" >
                <img class="zoom-effect" src="assets/images/poster-book2.webp" style="padding-top: 90px" alt="">
            </div>
        </div>
        <!-- banner section end -->

        <!-- best books saler section start -->
        <div class="movies_section layout_padding" style="background-color: rgb(52, 41, 41) ;">
            <div class="container">
            <c:set var="books" value="${applicationScope.books}"/>
                <div class="movies_section_2 layout_padding" >
                    <h2 class="letest_text" >BEST SALER</h2>
                    <div class="seemore_bt"><a href="product?action=newArrival">See More</a></div>
                    <div class="movies_main">
                        <div class="iamge_movies_main">
                            <c:forEach var="book" items="${books}">
                                <div class="iamge_movies">
                                    <div class="image_3">
                                        <img src="${book.image}" class="image" style="width:100%" alt="Book image">
                                        <div class="middle">
                                            <div class="playnow_bt"><a href="cart?action=buy&id=${book.id}&quantity=1" class=" btn buy-button">Buy</a></div>
                                        </div>
                                    </div>
                                    <h1 class="code_text">${book.title}</h1>
                                    <p class="there_text">${book.price} VND</p>
                                    <div class="star_icon">
                                        <ul>
                                            <li><a href="#"><img src="assets/images/star-icon.png"></a></li>
                                            <li><a href="#"><img src="assets/images/star-icon.png"></a></li>
                                            <li><a href="#"><img src="assets/images/star-icon.png"></a></li>
                                            <li><a href="#"><img src="assets/images/star-icon.png"></a></li>
                                            <li><a href="#"><img src="assets/images/star-icon.png"></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </c:forEach>  

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- best books saler section end -->

        <!-- section start -->
        <div class="movies_section layout_padding">
            <div class="container">
                <c:set var="books" value="${applicationScope.books}"/>
                <div class="movies_section_2 layout_padding">
                    <h2 class="letest_text">NEW ARRIVALS</h2>
                    <div class="seemore_bt"><a href="product?action=newArrival">See More</a></div>
                    <div class="movies_main">
                        <div class="iamge_movies_main">
                            <c:forEach var="book" items="${books}">
                                <div class="iamge_movies">
                                    <div class="image_3">
                                        <img src="${book.image}" class="image" style="width:100%" alt="img">
                                        <div class="middle">
                                            <div class="playnow_bt"><a href="cart?action=buy&id=${book.id}&quantity=1" class=" btn buy-button">Buy</a></div>
                                        </div>
                                    </div>
                                    <h1 class="code_text">${book.title}</h1>
                                    <p class="there_text">${book.price} VND</p>
                                    <div class="star_icon">
                                        <ul>
                                            <li><a href="#"><img src="assets/images/star-icon.png"></a></li>
                                            <li><a href="#"><img src="assets/images/star-icon.png"></a></li>
                                            <li><a href="#"><img src="assets/images/star-icon.png"></a></li>
                                            <li><a href="#"><img src="assets/images/star-icon.png"></a></li>
                                            <li><a href="#"><img src="assets/images/star-icon.png"></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </c:forEach>  
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- movies section end -->

        <!-- cooming  section start -->
        <div class="cooming_section layout_padding">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="image_17">
                            <div class="image_17"><img src="assets/images/img-17.png" alt="img"></div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h1 class="number_text">01</h1>
                        <h1 class="Cooming_soon_taital">Cooming soon</h1>
                        <p class="long_text_1">It is a long established fact that a reader will be distracted by the readable content of a page when looking</p>
                        <div class="paly_bt"><a href="#">buy</a></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- cooming  section end -->
        
        <!-- footer  section start -->
        <%--<jsp:include page="footer.jsp"></jsp:include>--%>
    </body>
</html>