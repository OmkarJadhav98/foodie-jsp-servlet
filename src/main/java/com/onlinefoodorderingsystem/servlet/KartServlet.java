package src.main.java.com.onlinefoodorderingsystem.servlet;

import com.onlinefoodorderingsystem.model.Kart;
import com.onlinefoodorderingsystem.service.KartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/kart")
public class KartServlet extends HttpServlet {
    private KartService kartService;

    @Override
    public void init() throws ServletException {
        kartService = new KartService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("getKart".equals(action)) {
            long kartId = Long.parseLong(req.getParameter("id"));
            Kart kart = kartService.getKartById(kartId);
            if (kart != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(kart.toJson());
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Kart not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("createKart".equals(action)) {
            // Extract Kart details from request
            long id = Long.parseLong(req.getParameter("id"));
            long customerId = Long.parseLong(req.getParameter("customerId"));

            Kart kart = new Kart(id, customerId);
            kartService.createKart(kart);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Kart created successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("updateKart".equals(action)) {
            long kartId = Long.parseLong(req.getParameter("id"));
            long customerId = Long.parseLong(req.getParameter("customerId"));

            Kart kart = new Kart(kartId, customerId);
            kartService.updateKart(kart);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Kart updated successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("deleteKart".equals(action)) {
            long kartId = Long.parseLong(req.getParameter("id"));
            kartService.deleteKart(kartId);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Kart deleted successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }
}
