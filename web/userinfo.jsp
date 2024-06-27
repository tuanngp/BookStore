<%-- 
    Document   : userinfo
    Created on : 03-03-2024, 20:39:19
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Order.OrderDetail" %>
<%@ page import="dao.OrderDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="account_assets/css/userinfo.css"/>
        <link rel="stylesheet" href="assets/css/bootstrap.css"/>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
    </head>
    <body>

        <jsp:include page="header.jsp"></jsp:include>
        <c:if test="${empty sessionScope.user}">
            <jsp:forward page="login.jsp"></jsp:forward>
        </c:if>    


        <div class="container">
            <div class="main-body">

                <!-- Breadcrumb -->
                <nav aria-label="breadcrumb" class="main-breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                    </ol>
                </nav>
                <!-- /Breadcrumb -->
                <c:set var="user" value="${sessionScope.user}"></c:set>
                    <div class="row gutters-sm">
                        <div class="col-md-4 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="d-flex flex-column align-items-center text-center">
                                        <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
                                        <div class="mt-3">
                                            <c:choose>
                                                <c:when test="${user.id==0}">
                                                    <p class="text-secondary mb-1">ID: ${user.username}</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p class="text-secondary mb-1">${user.username}</p>                                                    
                                                </c:otherwise>
                                            </c:choose>
                                        
                                        <p class="text-muted font-size-sm">Trạng thái: ${user.isActive?"Hoạt động":"Bị khóa"}</p>
                                        <p class="text-muted font-size-sm">${user.role=="admin"?"Quản trị viên":"Người dùng"}</p>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <a class="btn btn-info " href="logout">Đăng xuất</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex flex-column align-items-center text-center">
                                    <h4>Lịch sử đăng nhập</h4>
                                    <table border="0">
                                        <thead>
                                            <tr>
                                                <th>Log in</th>
                                                <th>Log out</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="log" items="${requestScope.logs}">
                                                <tr>
                                                    <td>${log.loginTime}</td>
                                                    <td>${empty log.logoutTime?"Đang đăng nhập":log.logoutTime}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>


                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-8">
                        <div class="card mb-3">
                            <div class="card-body">
                                <form action="userAdmin" method="POST">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Full Name</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" name="name" value="${user.name}" style="border: none; width: 100%;">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Email</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">

                                            <input type="email" name="email" value="${user.email}" style="border: none; width: 100%;" >
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Phone</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" name="phone" value="${user.phone}" pattern="[0]{1}[0-9]{9}" title="Phone number with 0 and remain 9 digit with 0-9" style="border: none; width: 100%;" >

                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Address</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">

                                            <input type="text" name="address" value="${user.address}" style="border: none; width: 100%;">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Password</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" name="password" value="${user.password}" style="border: none; width: 100%;">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <input type="hidden" name="action" value="update">
                                            <input type="hidden" name="type" value="user">
                                            <input type="hidden" name="userId" value="${user.id}">
                                            <input type="hidden" name="username" value="${user.username}">
                                            <input type="hidden" name="role" value="user">
                                            <input type="hidden" name="isActive" value="true">
                                            <!--<a class="btn btn-info " onclick="parentNode.submit()" >Edit</a>-->
                                            <input class="btn btn-info" type="submit" value="Edit">
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <p style="color: red">${sessionScope.status}</p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div class="row gutters-sm">
                            <div class="col-sm-12 mb-6">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <h6 class="d-flex align-items-center mb-3"><i class="material-icons text-info mr-2">Thông tin đặt hàng</i></h6>


                                        <c:forEach var="order" items="orders">
                                            <c:choose>
                                                <c:when test="${empty orders}">
                                                    <small>Chưa có đơn hàng nào</small>
                                                </c:when>
                                                <c:otherwise>
                                                    <% 
                                                        session = request.getSession();
                                                        entity.Account.User user = (entity.Account.User) session.getAttribute("user");
                                                        HashMap<Integer, List<OrderDetail>> orders = (HashMap<Integer, List<OrderDetail>>)request.getAttribute("orders");

                                                        for(int key : OrderDAO.getAllOrders(user.getId()).keySet()) {
                                                            List<OrderDetail> orderDetailsList = OrderDAO.getAllOrders(user.getId()).get(key);
//                                                            out.print(orderDetailsList);
                                                            for (OrderDetail o : orderDetailsList) {
                                                                out.print("Tiêu đề: " + o.getBook().getTitle());
                                                                out.print("   Số lượng: " + o.getQuantity());
                                                                out.print("   Giá: " + o.getPrice());
                                                                out.print("   Phí ship: 30.000");
                                                                out.print("   Tổng tiền: " + (o.getPrice()*o.getQuantity()+30000) );
                                                                out.print("<br>");
                                                            }
                                                        }
                                                    %>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%--<jsp:include page="footer.jsp"></jsp:include>--%>

    </body>
</html>
