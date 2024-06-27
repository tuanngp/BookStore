<%-- 
    Document   : adminBook
    Created on : 29-02-2024, 10:40:08
    Author     : tuanngp
--%>
<%@ page isELIgnored ="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book manager</title>
    </head>

    <body>
        <%--<jsp:forward page="admin"></jsp:forward>--%>
        <section id="books">
            <a href="#bookForm"><button onclick="addBook()">Thêm sách</button></a>
            <h2>Danh sách sách</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tác giả</th>
                        <th>Nhà xuất bản</th>
                        <th>Tiêu đề</th>
                        <th>Thể loại</th>
                        <th>Mô tả</th>
                        <th>Số lượng</th>
                        <th>Giá</th>
                        <th>Hình ảnh</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Hiển thị dữ liệu sách từ máy chủ -->
                    <c:forEach var="book" items="${requestScope.books}">
                        <tr data-book-authod-id="${book.author.id}"
                            data-book-authod-name="${book.author.name}"
                            data-book-birthday="${book.author.birthday}"
                            data-book-bio="${book.author.bio}"
                            data-book-publisher-id="${book.publisher.publisherId}"
                            data-book-publisher-name="${book.publisher.publisherName}"
                            data-book-date-established="${book.publisher.dateEstablished}"
                            data-book-id="${book.id}"
                            data-book-title="${book.title}"
                            data-book-genre="${book.genre}"
                            data-book-description="${book.description}"
                            data-book-quantity="${book.quantity}"
                            data-book-price="${book.price}"
                            data-book-image="${book.image}">
                            <td>${book.id}</td>
                            <td>${book.author.name}</td>
                            <td>${book.publisher.publisherName}</td>
                            <td>${book.title}</td>
                            <td>${book.genre}</td>
                            <td>${book.description}</td>
                            <td>${book.quantity}</td>
                            <td>${book.price}</td>
                            <td><img src="${book.image}" alt="Book Image" style="width: 120px"></td>
                            <td>
                                <a href="#bookForm" style="padding: 20px;">
                                    <button onclick="editBook(${book.id})">Sửa</button>
                                </a>
                                <form action="bookAdmin" style="box-shadow: none">
                                    <input type="text" name="action" value="delete" hidden>
                                    <input name="pageBook" value="${i}" hidden="">
                                    <input type="text" name="bookId" value="${book.id}" hidden><br>
                                    <input type="text" name="authorId" value="${book.author.id}" hidden><br>
                                    <input type="text" name="publisherId" value="${book.publisher.publisherId}" hidden><br>
                                    <button onclick="parentNode.submit()">Xóa</button>
                                </form>

                                
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${totalPagesBook > 1}">
                <div class="pagination">
                    <c:forEach var="i" begin="1" end="${totalPagesBook}">
                        <c:choose>
                            <c:when test="${i == currentPageBook}">
                                <span>${i}</span>
                                <input name="pageBook" value="${i}" hidden="">
                            </c:when>
                            <c:otherwise>
                                <a href="?pageBook=${i}#books">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </c:if>

            <!-- Biểu mẫu thêm/sửa sách -->
            <div id="bookForm" style="display: none;">
                <h3>Biểu mẫu Sách</h3>
                <form action="bookAdmin">
                    <input type="text" name="action" id="action" hidden="">
                    <!--Author Information-->
                    <fieldset>
                        <legend>Author Information</legend>

                        <label for="authorId">Id: </label>
                        <input type="text" id="authorId" name="authorId" readonly=""><br>

                        <label for="authorName">Name:</label>
                        <input type="text" id="authorName" name="authorName" required><br>

                        <label for="birthday">Birthday:</label>
                        <input type="date" id="birthday" name="birthday" required><br>

                        <label for="bio">Bio:</label>
                        <textarea id="bio" name="bio" rows="4" cols="50" required></textarea><br>
                    </fieldset>

                    <!-- Publisher Information -->
                    <fieldset>
                        <legend>Publisher Information</legend>

                        <label for="publisherName">Id:</label>
                        <input type="text" id="publisherId" name="publisherId" readonly=""><br>

                        <label for="publisherName">Name:</label>
                        <input type="text" id="publisherName" name="publisherName" required><br>

                        <label for="establishedDate">Date Established:</label>
                        <input type="date" id="establishedDate" name="establishedDate" required><br>
                    </fieldset>

                    <!-- Book Information -->
                    <fieldset>
                        <legend>Book Information</legend>
                        <label for="id">Id: </label>
                        <input type="text" id="bookId" name="bookId" required readonly=""><br>

                        <label for="bookTitle">Title: </label>
                        <input type="text" id="bookTitle" name="bookTitle" required><br>

                        <label for="genre">Genre:</label>
                        <input type="text" id="genre" name="genre" required><br>

                        <label for="description">Description:</label>
                        <textarea id="description" name="description" rows="4" cols="50" required></textarea><br>

                        <label for="quantity">Quantity:</label>
                        <input type="text" id="quantity" name="quantity" required><br>

                        <label for="price">Price:</label>
                        <input type="number" id="price" name="price" step="5000" required><br>

                        <label for="image">Select Image :</label>
                        <input type="text" name="image" id="image" required>
                    </fieldset>

                    <button type="button" onclick="parentNode.submit()">Lưu</button>
                    <a href="#books"><button type="button" onclick="cancelBookForm()">Hủy</button></a>
                </form>
            </div>
        </section>
    </body>
    <script src="account_assets/js/main.js"></script>
</html>