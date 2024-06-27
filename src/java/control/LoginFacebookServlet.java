//
//package control;
//
//import java.io.IOException;
//
//import com.restfb.types.User;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import context.RestFB;
//@WebServlet("/login-facebook")
//public class LoginFacebookServlet extends HttpServlet {
//
//  protected void doGet(HttpServletRequest request, HttpServletResponse response)
//      throws ServletException, IOException {
//    String code = request.getParameter("code");
//    
//    if (code == null || code.isEmpty()) {
//      RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
//      dis.forward(request, response);
//    } else {
//      String accessToken = RestFB.getToken(code);
//      User user = RestFB.getUserInfo(accessToken);
//      request.setAttribute("id", user.getId());
//      request.setAttribute("name", user.getName());
//      RequestDispatcher dis = request.getRequestDispatcher("newjsp.jsp");
//      dis.forward(request, response);
//    }
//    
//  }
//  protected void doPost(HttpServletRequest request, HttpServletResponse response)
//      throws ServletException, IOException {
//    doGet(request, response);
//  }
//}