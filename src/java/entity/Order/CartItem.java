/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.Order;

import entity.Product.Book;

/**
 *
 *
 */
public class CartItem {
    private Book book;
    private int quantity;

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
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
    
    public void increaseQuantity(int i) {
        if (this.quantity - i >= 0) {
            this.quantity += i;
        }else {
            this.quantity = 1;
        }
    }
    
    public void descreaseQuantity(int i) {
        if (this.quantity - i < 0) {
            this.quantity = 1;
        }else {
            this.quantity -= i;
        }
    }
}
