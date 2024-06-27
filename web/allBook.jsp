<%@ page import="entity.Product.Book" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <!-- site metas -->
        <title>All books</title>

        <!-- bootstrap css -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="assets/css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="assets/images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="assets/css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <!-- owl stylesheets --> 
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="assets/css/allBook.css"/>


    </head>
    <body>


        <!--header-begin-->
        <jsp:include page="header.jsp" ></jsp:include>
            <!--header-end-->

            <!-- table left start -->
            <div class="row" style="background-color: #3c0e0e" >  
                <div class="col-lg-2">
                    <!--ALL CATEGORIERS -->
                    <div class="table-left">
                        <div class="list_categories">
                            <h2>ALL CATEGORIES</h2>
                            <ul>
                                <li>
                                    <a href="product">All Book</a>
                                </li>
                                <li>
                                    <a href="product?action=newArrival">
                                        New Arrivals
                                    </a>
                                </li>
                                <li>
                                    <a href="product?action=category&category=Genre&input=Fiction">Fiction</a>
                                </li>
                                <li>
                                    <a href="product?action=category&category=Genre&input=Non-Fiction">Non-Fiction</a>
                                </li>
                                <li>
                                    <a href="product?action=category&category=Genre&input=Mystery">Mystery</a>
                                </li>
                                <li>
                                    <a href="product?action=category&category=Genre&input=Fantasy">Fantasy</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!--AUTHOR -->
                    <div class="table-left ">
                        <div class="list_categories">
                            <h2>AUTHOR</h2>

                            <ul>
                                <c:set var="authors" value="${requestScope.authors}"></c:set>

                                <c:forEach var="author" items="${authors}">
                                    <li>
                                        <a href="product?action=category&category=AuthorId&input=${author.id}">${author.name} </a>
                                    </li>
                                </c:forEach>
                            </ul>

                        </div>
                    </div>
                <!--ALL CATEGORIERS -->
                <div class="table-left ">
                    <div class="Price_categories">
                        <h2>PRICE RANGE</h2>
                        <ul>
                            <li class="checkbox-container"><span>
                                    <input type="checkbox" id="author9Checkbox" name="myCheckbox">
                                    <label for="author9Checkbox"><a href="#">author 1</a></label>
                                </span></li>
                        </ul>
                    </div>                            
                </div>
                <!--ALL CATEGORIERS -->
                <div class="table-left ">
                    <div class="list_categories">
                        <h2>FORMAT</h2>
                        <ul>
                            <li class="checkbox-container">
                                <span>
                                    <input type="checkbox" id="author17Checkbox" name="myCheckbox">
                                    <label for="author17Checkbox">author 1</label>
                                </span>
                            </li>
                            <li class="checkbox-container">
                                <span>
                                    <input type="checkbox" id="author18Checkbox" name="myCheckbox">
                                    <label for="author18Checkbox">author 2</label>
                                </span>
                            </li>
                        </ul>
                    </div>                            
                </div>
            </div >
            <c:set var="books" value="${requestScope.books}" />
            <c:set var="count" value="0" />
            <c:set var="totalPages" value="${requestScope.totalPage}" />
            <c:set var="currentPage" value="${requestScope.indexPage}" />
            <div class="col-lg-10" style="background-color: #FFF">

                <div class="dropdown" onmouseover="openDropdown()" onmouseout="closeDropdown()">
                    <button class="btn btn-secondary dropdown-toggle" type="button" aria-expanded="false">
                        SORT BY
                    </button>
                    <ul id="dropdown-menu">
                        <li><a href="product?action=asctitle"><button class="dropdown-item" type="button">A -> Z</button></a></li>
                        <li><a href="product?action=desctitle"><button class="dropdown-item" type="button">Z -> A</button></a></li>
                        <li><a href="product?action=ascprice"><button class="dropdown-item" type="button">PRICE ASC</button></a></li>
                        <li><a href="product?action=descprice"><button class="dropdown-item" type="button">PRICE DESC</button></a></li>
                        <li><a href="product?action=ascdate"><button class="dropdown-item" type="button">LASTEST</button></a></li>
                        <li><a href="product?action=descdate"><button class="dropdown-item" type="button">OLDEST</button></a></li>  
                    </ul>
                </div>
                <!-- Inside the table where you display books -->
                <div class="row">
                    <c:forEach var="book" items="${books}">
                        <div class="col-lg-4">
                            <!-- Display book details here -->

                            <div class="book-container">
                                <div class="image-container">
                                    <img class="book-image" src="${book.image}" alt="${book.title} Image" width="200">
                                    <div class="overlay">
                                        <div class="overlay-content">
                                            <a href="cart?action=buy&id=${book.id}&quantity=1" class=" btn buy-button">Buy</a>
                                            <form action="detail" method="get">
                                                <input type="hidden" name="bookId" value="${book.id}">
                                                <button type="submit" class="btn btn-primary view-details-btn">View Details</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <a href="detail?bookId=${book.id}"><h2>${book.title}</h2></a>
                                <p>Price: ${book.price} VND</p>
                                <!-- Add more book details as needed -->
                            </div>

                        </div>

                        <c:set var="count" value="${count + 1}" />

                        <c:if test="${count % 3 == 0}">
                        </div><div class="row">
                        </c:if>
                    </c:forEach>
                </div>

                <!-- Pagination -->
                <div style="display: flex; justify-content: center; margin: 20px;">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item ${currentPage==i?"active":""}">
                                    <a class="page-link"  href="product?page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

        <!-- copyright section start -->
        <jsp:include page="footer.jsp" ></jsp:include>
        <!-- copyright section end -->

        <script>
            $('#datepicker').datepicker({
                uiLibrary: 'bootstrap4'
            });
        </script>

    </body>
</html>