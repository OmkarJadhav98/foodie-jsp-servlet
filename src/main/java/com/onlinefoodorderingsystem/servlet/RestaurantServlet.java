package src.main.java.com.onlinefoodorderingsystem.servlet;

import com.onlinefoodorderingsystem.model.Restaurant;
import com.onlinefoodorderingsystem.service.RestaurantService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet {
    private RestaurantService restaurantService;

    @Override
    public void init() throws ServletException {
        restaurantService = new RestaurantService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("getRestaurant".equals(action)) {
            long restaurantId = Long.parseLong(req.getParameter("id"));
            Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
            if (restaurant != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(restaurant.toJson());
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Restaurant not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("createRestaurant".equals(action)) {
            // Extract Restaurant details from request
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            String address = req.getParameter("address");

            Restaurant restaurant = new Restaurant(id, name, address);
            restaurantService.createRestaurant(restaurant);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Restaurant created successfully\"}");
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
