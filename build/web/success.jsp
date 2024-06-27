<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="assets/images/icon-thanh-cong.png"/>
        <title>Payment success</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 10px 10px;
                box-sizing: border-box;
            }

            h1 {
                color: yellowgreen;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }

            thead {
                background-color: #f2f2f2;
            }

            p {
                font-weight: bold;
                margin-top: 10px;
            }
            
            .home{
               text-decoration: none; 
            }
            .home button{
                font-size: 16px;
                padding: 10px 20px;
                background-color: #3498db;
                color: #ffffff;
                border: none;
                cursor: pointer;
            }

        </style>
    </head>

    <body>
        <h1>Đơn hàng của bạn đã được đặt thành công!</h1>

        <c:if test="${not empty cart}">
            <h2>Chi tiết đơn hàng:</h2>
            <table border="2px">
                <thead>
                    <tr>
                        <th>Sách</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Tạm tính</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cart}">
                        <tr>
                            <td>${item.book.title}</td>
                            <td>${item.book.price}</td>
                            <td>${item.quantity}</td>
                            <td>${item.book.price * item.quantity}</td>


                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p>ShipCode : ${shipCode}</p>
            <p>Tổng tiền: ${totalAmount} VND</p>
        </c:if>


        <!-- Add this code where you want the Home button -->
        <div>
            <a class="home" href="index.jsp">
                <button>
                    Home
                </button>
            </a>
        </div>

    </body>
</html>
