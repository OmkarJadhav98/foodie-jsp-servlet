package src.main.java.com.onlinefoodorderingsystem.servlet;

import com.onlinefoodorderingsystem.model.Menu;
import com.onlinefoodorderingsystem.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private MenuService menuService;

    @Override
    public void init() throws ServletException {
        menuService = new MenuService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("getMenu".equals(action)) {
            long menuId = Long.parseLong(req.getParameter("id"));
            Menu menu = menuService.getMenuById(menuId);
            if (menu != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(menu.toJson());
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Menu not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("createMenu".equals(action)) {
            // Extract Menu details from request
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            long restaurantId = Long.parseLong(req.getParameter("restaurantId"));

            Menu menu = new Menu(id, name, price, restaurantId);
            menuService.createMenu(menu);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Menu created successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("updateMenu".equals(action)) {
            long menuId = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            long restaurantId = Long.parseLong(req.getParameter("restaurantId"));

            Menu menu = new Menu(menuId, name, price, restaurantId);
            menuService.updateMenu(menu);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Menu updated successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("deleteMenu".equals(action)) {
            long menuId = Long.parseLong(req.getParameter("id"));
            menuService.deleteMenu(menuId);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Menu deleted successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }
}
