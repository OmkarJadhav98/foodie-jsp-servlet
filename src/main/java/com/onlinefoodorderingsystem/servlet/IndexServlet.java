package src.main.java.com.onlinefoodorderingsystem.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<html><body>");
        resp.getWriter().write("<h1>Welcome to the Online Food Ordering System</h1>");
        resp.getWriter().write("<p>Use the following endpoints:</p>");
        resp.getWriter().write("<ul>");
        resp.getWriter().write("<li><a href='/address?action=getAddress&id=1'>Address</a></li>");
        resp.getWriter().write("<li><a href='/contact?action=getContact&id=1'>Contact</a></li>");
        resp.getWriter().write("<li><a href='/customer?action=getCustomer&id=1'>Customer</a></li>");
        resp.getWriter().write("<li><a href='/deliveryExecutive?action=getDeliveryExecutive&id=1'>Delivery Executive</a></li>");
        resp.getWriter().write("<li><a href='/kart?action=getKart&id=1'>Kart</a></li>");
        resp.getWriter().write("<li><a href='/menu?action=getMenu&id=1'>Menu</a></li>");
        resp.getWriter().write("<li><a href='/order?action=getOrder&id=1'>Order</a></li>");
        resp.getWriter().write("<li><a href='/restaurant?action=getRestaurant&id=1'>Restaurant</a></li>");
        resp.getWriter().write("</ul>");
        resp.getWriter().write("</body></html>");
    }
}
