package src.main.java.com.onlinefoodorderingsystem.servlet;

import com.onlinefoodorderingsystem.model.Customer;
import com.onlinefoodorderingsystem.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("getCustomer".equals(action)) {
            long customerId = Long.parseLong(req.getParameter("id"));
            Customer customer = customerService.getCustomerById(customerId);
            if (customer != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(customer.toJson());
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("createCustomer".equals(action)) {
            // Extract Customer details from request
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            Customer customer = new Customer(id, name, email, password);
            customerService.createCustomer(customer);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Customer created successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("updateCustomer".equals(action)) {
            long customerId = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            Customer customer = new Customer(customerId, name, email, password);
            customerService.updateCustomer(customer);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Customer updated successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("deleteCustomer".equals(action)) {
            long customerId = Long.parseLong(req.getParameter("id"));
            customerService.deleteCustomer(customerId);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Customer deleted successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }
}
