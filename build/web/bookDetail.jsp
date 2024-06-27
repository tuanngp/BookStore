<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>DBI</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- bootstrap css -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/bootstrap.css"/>
        <!-- fevicon -->
        <link rel="icon" href="assets/assets/images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="assets/css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <!-- owl stylesheets --> 
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />

        <link href="assets/css/prettyPhoto.css" rel="stylesheet">
        <link href="assets/css/price-range.css" rel="stylesheet">
        <link href="assets/css/bookDetail.css" rel="stylesheet">  

    </head>

    <body>
        <!-- header section start -->
        <jsp:include page="header.jsp" ></jsp:include>
        <c:set var="book" value="${requestScope.book}"></c:set>
            <div id="product_detail" class="container">
                <div class="row">                  
                    <div class="col-sm-12 padding-right">
                        <div class="product-details"> 
                            <div class="row">                          
                                <div class="col-sm-5">                                                  
                                    <div id="carouselExample" class="carousel slide">
                                        <div class="carousel-inner">
                                            <div class="carousel-item active">
                                                <img src="${book.image}" class="d-block w-100" alt="..." width="200">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-7">
                                    <div class="product-information"><!--/product-information-->                            
                                        <h2 class="detail_title">${book.title}</h2>                               
                                        <h3>Tác giả: ${book.author.name}</h3>
                                        <h3 class="detail_title">Price: ${book.price} VND</h3>
                                        <h3>Nhà xuất bản: ${book.publisher.getPublisherName()}</h3>

                                        <h3 class="detail_title">Mô tả sản phẩm</h3>
                                        <p id="short-description">${book.description}</p>
                                        <a href="#details" data-toggle="tab">Xem thêm...</a>
                                        <br>
                                        <h3>Số lượng: </h3>
                                        <form id="cart" action="cart" method="get">
                                            <div class="input-group mb-3">
                                                <button class="btn btn-quantity" onclick="quantityChange(-1)" type="button">-</button>
                                                <input type="text" value="1" id="quantityInput" name="quantity"> <!-- Add name attribute for the quantity -->
                                                <button class="btn btn-quantity" onclick="quantityChange(1)" type="button">+</button>
                                            </div>

                                            <div class="d-grid gap-2 d-md-block">
                                                <button class="btn btn-default cart" type="submit" id="buy-button" name="action" value="buy">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    Thêm vào giỏ
                                                </button>
                                                <input type="hidden" name="id" value="${book.id}">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>      
                    </div>
                </div>
            </div>  
        <!-- cooming  section end -->
        <jsp:include page="footer.jsp"></jsp:include>
        <!-- copyright section end -->
        <script>
            var i = 1;
            function quantityChange(n) {
                var quantity = document.getElementById("quantityInput");
                i = i + n;
                if (i < 1) {

                    i = 1;
                }
                quantity.value = i;
            }

        </script>
    </body>
</html>