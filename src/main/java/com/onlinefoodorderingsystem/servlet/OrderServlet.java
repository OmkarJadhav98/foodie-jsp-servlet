package src.main.java.com.onlinefoodorderingsystem.servlet;

import com.onlinefoodorderingsystem.model.Order;
import com.onlinefoodorderingsystem.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("getOrder".equals(action)) {
            long orderId = Long.parseLong(req.getParameter("id"));
            Order order = orderService.getOrderById(orderId);
            if (order != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(order.toJson());
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("createOrder".equals(action)) {
            // Extract Order details from request
            long id = Long.parseLong(req.getParameter("id"));
            long customerId = Long.parseLong(req.getParameter("customerId"));
            long restaurantId = Long.parseLong(req.getParameter("restaurantId"));
            double totalAmount = Double.parseDouble(req.getParameter("totalAmount"));

            Order order = new Order(id, customerId, restaurantId, totalAmount);
            orderService.createOrder(order);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Order created successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Implement update logic
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Implement delete logic
    }
}
