
package entity.Order;

import dao.BookDAO;
import entity.Product.Book;

public class OrderDetail {

    int OrderDetailId;
    int OrderId;
    Book book;
    int quantity;
    Float price;
    int discountId;
    public OrderDetail() {
    }

    public OrderDetail(int OrderDetailId, int OrderId, int bookId, int quantity, Float price, int discountId) {
        this.OrderDetailId = OrderDetailId;
        this.OrderId = OrderId;
        this.book = BookDAO.getBookById(bookId);
        this.quantity = quantity;
        this.price = price;
        this.discountId = discountId;
    }

    public OrderDetail(int OrderDetailId, int OrderId, int bookId, int quantity, Float price) {
        this.OrderDetailId = OrderDetailId;
        this.OrderId = OrderId;
        this.book = BookDAO.getBookById(bookId);
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(int OrderDetailId) {
        this.OrderDetailId = OrderDetailId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "OrderDetailId=" + OrderDetailId + ", OrderId=" + OrderId + ", book=" + book + ", quantity=" + quantity + ", price=" + price + ", discountId=" + discountId + '}';
    }

  

}
