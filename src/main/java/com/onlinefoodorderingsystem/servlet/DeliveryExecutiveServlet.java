package src.main.java.com.onlinefoodorderingsystem.servlet;

import com.onlinefoodorderingsystem.model.DeliveryExecutive;
import com.onlinefoodorderingsystem.service.DeliveryExecutiveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deliveryExecutive")
public class DeliveryExecutiveServlet extends HttpServlet {
    private DeliveryExecutiveService deliveryExecutiveService;

    @Override
    public void init() throws ServletException {
        deliveryExecutiveService = new DeliveryExecutiveService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("getDeliveryExecutive".equals(action)) {
            long deliveryExecutiveId = Long.parseLong(req.getParameter("id"));
            DeliveryExecutive deliveryExecutive = deliveryExecutiveService.getDeliveryExecutiveById(deliveryExecutiveId);
            if (deliveryExecutive != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(deliveryExecutive.toJson());
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Delivery Executive not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("createDeliveryExecutive".equals(action)) {
            // Extract Delivery Executive details from request
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");

            DeliveryExecutive deliveryExecutive = new DeliveryExecutive(id, name, phone);
            deliveryExecutiveService.createDeliveryExecutive(deliveryExecutive);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Delivery Executive created successfully\"}");
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
