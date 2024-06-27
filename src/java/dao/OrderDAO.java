package dao;

import context.DBcontext;
import entity.Account.User;
import entity.Order.CartItem;
import entity.Order.Order;
import entity.Order.OrderDetail;
import entity.Product.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDAO {

    public static void addOrder(User u, List<CartItem> cart) {

        try ( Connection conn = DBcontext.getConnection()) {
            float total = 0;
            for (CartItem c : cart) {
                total += c.getBook().getPrice() * c.getQuantity();
            }
            String sql = "insert into Orders(OrderAmount,UserId, ShippingMethodId ) "
                    + "VALUES (?,?,?)";
            PreparedStatement ps;
            ResultSet rs;
            ps = conn.prepareStatement(sql);
            ps.setFloat(1, total + 30000);
            ps.setInt(2, u.getId());
            ps.setInt(3, 1);

            ps.executeUpdate();

            String sql1 = "select top 1 OrderId from [Orders] order by OrderId desc";
            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();

            if (rs.next()) {
                int OrderId = rs.getInt(1);
                for (CartItem c : cart) {
                    String sql2 = "insert into OrderDetails(OrderId,BookId,Quantity,Price,DiscountId)  values(?,?,?,?,?)";
                    ps = conn.prepareStatement(sql2);
                    ps.setInt(1, OrderId);
                    ps.setInt(2, c.getBook().getId());
                    ps.setInt(3, c.getQuantity());
                    ps.setFloat(4, c.getBook().getPrice());
                    ps.setInt(5, 1);
                    ps.executeUpdate();
                }
            }

            String sql3 = "update Books set Quantity = Quantity - ? "
                    + "where BookId = ?";
            ps = conn.prepareStatement(sql3);
            for (CartItem c : cart) {
                ps.setInt(1, c.getQuantity());
                ps.setInt(2, c.getBook().getId());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Order> getOrders() {
        List<Order> list = new ArrayList<>();
        String sql = "select * from Orders";
        try ( Connection conn = DBcontext.getConnection()) {

            try ( Statement st = conn.createStatement()) {
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    list.add(new Order(rs.getInt(1),
                            rs.getString(2),
                            rs.getFloat(3),
                            rs.getInt(4),
                            rs.getInt(5)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<OrderDetail> getDetails(int OrderId) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select * from OrderDetails where OrderId = ?";
        try ( Connection conn = DBcontext.getConnection()) {
            try ( PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, OrderId);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(new OrderDetail(rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getFloat(5),
                            rs.getInt(6)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static HashMap<Integer, List<OrderDetail>> getAllOrders(int userId) {
        HashMap<Integer, List<OrderDetail>> hashMap = new HashMap<>();
        for (Order order : getOrderByID(userId)) {
            hashMap.put(order.getOrderId(), getDetails(order.getOrderId()));
        }
        return hashMap;
    }

    public static List<Order> getOrderByID(int UserId) {
        List<Order> list = new ArrayList<>();
        String sql = "select * from Orders where UserId = ?";
        try ( Connection conn = DBcontext.getConnection()) {

            try ( PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, UserId);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(new Order(rs.getInt(1),
                            rs.getString(2),
                            rs.getFloat(3),
                            rs.getInt(4),
                            rs.getInt(5)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
//         getDetails(6).forEach(p -> System.out.println(p));
//        getOrderByID(10).forEach(p -> System.out.println(p));
        for (int key : getAllOrders(4).keySet()) {
//            for (OrderDetail o : getAllOrders(10).get(key)) {
//                System.out.println(o.getBook().getTitle());
//                System.out.println(o.getQuantity());
//                System.out.println(o.getPrice());
//                System.out.println(o.getPrice()*o.getQuantity());
//            }
            System.out.println(getAllOrders(4).get(key));
        }
    }
}
